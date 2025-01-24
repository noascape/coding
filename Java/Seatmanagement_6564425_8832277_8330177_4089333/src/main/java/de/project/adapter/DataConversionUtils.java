package de.project.adapter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataConversionUtils {

    // Converts a list of strings to a comma-separated string
    public static String listToCommaSeparatedString(List<String> list) {
        return String.join(",", list);
    }

    // Converts a comma-separated string to a list of strings
    public static List<String> commaSeparatedStringToList(String data) {
        return Arrays.stream(data.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
