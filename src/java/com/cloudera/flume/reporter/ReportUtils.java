package com.cloudera.flume.reporter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ReportUtils {

  @SuppressWarnings("unchecked")
  public static Map<String, Reportable> noChildren() {
    return (Map<String, Reportable>) Collections.EMPTY_MAP;
  }

  public static Map<String, Reportable> subReports(Reportable... rtbls) {
    Map<String, Reportable> map = new HashMap<String, Reportable>();
    for (Reportable r : rtbls) {
      map.put(r.getName(), r);
    }
    return map;
  }

  /**
   * Get a hierarchically flattened metrics report
   * 
   * @param r
   * @return
   */
  public static ReportEvent getFlattenedReport(Reportable r) {
    ReportEvent rpt = r.getMetrics();
    Map<String, Reportable> subs = r.getSubMetrics();
    for (Entry<String, Reportable> e : subs.entrySet()) {
      String name = e.getKey();
      ReportEvent subRpt = getFlattenedReport(e.getValue());
      rpt.mergeWithPrefix(name + ".", subRpt);
    }
    return rpt;
  }

}
