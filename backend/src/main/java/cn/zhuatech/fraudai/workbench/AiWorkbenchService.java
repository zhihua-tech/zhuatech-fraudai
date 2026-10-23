/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.workbench;

import static cn.zhuatech.fraudai.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);
    keys(r, "asOf", "windowSeconds", "maxCount", "maxTotal", "transactions");
    var asOf = instant(text(r, "asOf", 40));
    int seconds = integer(r, "windowSeconds", 1, 86400), max = integer(r, "maxCount", 1, 500);
    var maxTotal = money(r, "maxTotal");
    var start = asOf.minusSeconds(seconds);
    Set<String> ids = new HashSet<>();
    Map<List<String>, List<Map<String, Object>>> groups =
        new TreeMap<>(
            Comparator.<List<String>, String>comparing(k -> k.get(0)).thenComparing(k -> k.get(1)));
    int excluded = 0;
    for (var t : rows(r, "transactions", 1, 500)) {
      keys(t, "id", "account", "currency", "at", "amount");
      unique(ids, id(t, "id"));
      String account = id(t, "account"), currency = text(t, "currency", 3);
      if (!currency.matches("[A-Z]{3}")) throw new IllegalArgumentException("币种格式错误");
      var at = instant(text(t, "at", 40));
      money(t, "amount");
      if (at.isAfter(asOf)) throw new IllegalArgumentException("不能包含复核时点之后的交易");
      if (!at.isAfter(start)) {
        excluded++;
        continue;
      }
      groups.computeIfAbsent(List.of(account, currency), k -> new ArrayList<>()).add(t);
    }
    List<Map<String, Object>> out = new ArrayList<>();
    for (var e : groups.entrySet()) {
      var tx = e.getValue();
      BigDecimal total = BigDecimal.ZERO;
      List<String> members = new ArrayList<>();
      for (var t : tx) {
        total = total.add(money(t, "amount"));
        members.add(t.get("id").toString());
      }
      Collections.sort(members);
      out.add(
          Map.of(
              "account",
              e.getKey().get(0),
              "currency",
              e.getKey().get(1),
              "count",
              tx.size(),
              "total",
              total,
              "countExceeded",
              tx.size() > max,
              "amountExceeded",
              total.compareTo(maxTotal) > 0,
              "transactionIds",
              members));
    }
    return Map.of(
        "windowStartExclusive",
        start.toString(),
        "windowEndInclusive",
        asOf.toString(),
        "excludedBeforeWindow",
        excluded,
        "accounts",
        out,
        "automaticBlock",
        false);
  }
}
