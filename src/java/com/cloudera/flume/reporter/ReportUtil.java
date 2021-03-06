/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.flume.reporter;

import java.util.Map.Entry;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ReportUtil {

  /**
   * Convert a flume report in to a jettison JSONObject.
   * 
   * @param rpt
   * @return
   * @throws JSONException
   */
  public static JSONObject toJSONObject(ReportEvent rpt) throws JSONException {
    JSONObject ret = new JSONObject();

    for (Entry<String, Long> e : rpt.getAllLongMetrics().entrySet()) {
      ret.put(e.getKey(), e.getValue());
    }

    for (Entry<String, Double> e : rpt.getAllDoubleMetrics().entrySet()) {
      ret.put(e.getKey(), e.getValue());
    }

    for (Entry<String, String> e : rpt.getAllStringMetrics().entrySet()) {
      ret.put(e.getKey(), e.getValue());
    }
    return ret;
  }
}
