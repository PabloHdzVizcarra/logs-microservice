package com.pablohdz.debug.infra;

import com.pablohdz.debug.domain.ErrorLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerOperations {
    public ControllerOperations() {
    }

    public Map<String, String> queryToMap(String query) {
        if (query == null) {
            return null;
        }

        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
           String[] entry = param.split("=");
           if (entry.length > 1) {
               result.put(entry[0], entry[1]);
           } else {
               result.put(entry[0], "");
           }
        }
        return result;
    }

    public String mapListToString(List<ErrorLog> list) {
        StringBuilder errors = new StringBuilder();

        list.forEach(errorLog -> {
            String error = String.format(
                "\"%s\": \"%s\",",
                errorLog.getService(), errorLog.getMessage());
            errors.append(error);
        });

        return cleanResponse(errors.toString());
    }

    private String cleanResponse(String data) {
        String deleteComma = data.substring(0, data.length() - 1);
        return String.format("{%s}", deleteComma);
    }
}
