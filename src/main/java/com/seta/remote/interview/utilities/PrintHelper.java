package com.seta.remote.interview.utilities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.seta.remote.interview.models.entity.Customer;
import com.seta.remote.interview.models.entity.Product;

public class PrintHelper {

    public static <E> String printLogContent(List<E> listObject) {
        StringBuilder stringBuilder = new StringBuilder();
        for (E ele : listObject) {
            stringBuilder.append("\n\t")
                    .append(ele.toString()).append("\n\t");
        }

        return stringBuilder.toString();
    }

    // Edit Map key-value printing
    public static <E, F> String formatMapObAndListOb(Map<E, List<F>> inputMap, String nameKey, String nameValues) {
        List<String> resultList = inputMap.entrySet().stream()
                .map(entry -> {
                    String keyString = String.format("%s: %s", nameKey, getKeyString(entry.getKey()));
                    List<String> valuesString = entry.getValue().stream()
                            .map(value -> String.format("{ %s: %s }", nameValues, value))
                            .collect(Collectors.toList());

                    return String.join("\n", "\t" + keyString, String.join(", ", "\t" + valuesString));
                })
                .collect(Collectors.toList());

        return String.join("\n\n", resultList);
    }

    public static <E, F> String formatMapObAndOb(Map<E, F> inputMap, String nameKey, String nameValues) {
        return inputMap.entrySet().stream()
                .map(entry -> {
                    String keyString = String.format("%s: %s", nameKey, getKeyString(entry.getKey()));
                    String valueString = String.format("{ %s: %s }", nameValues, entry.getValue());

                    return String.join(" - ", "\t" + keyString, valueString);
                })
                .collect(Collectors.joining("\n\n"));
    }

    // Custome
    private static String getKeyString(Object key) {
        if (key instanceof Customer) {
            return String.valueOf(((Customer) key).getId());
        }
        return String.valueOf(key);
    }
}
