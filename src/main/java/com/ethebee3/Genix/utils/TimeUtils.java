package com.ethebee3.Genix.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtils {

    // Function to convert a single time unit string like "4m" to seconds
    public static int formatStringToTime(String stringTimestamp) {
        if (stringTimestamp == null || stringTimestamp.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        // Remove any whitespace
        stringTimestamp = stringTimestamp.trim();

        // Extract the number and the unit (e.g., 4m or 3w)
        int number;
        String timeUnit;

        // If the last two characters are letters (like 'mo' for months), treat them as one unit
        if (stringTimestamp.length() > 1 && Character.isLetter(stringTimestamp.charAt(stringTimestamp.length() - 2))) {
            number = Integer.parseInt(stringTimestamp.substring(0, stringTimestamp.length() - 2));
            timeUnit = stringTimestamp.substring(stringTimestamp.length() - 2);
        } else {
            number = Integer.parseInt(stringTimestamp.substring(0, stringTimestamp.length() - 1));
            timeUnit = stringTimestamp.substring(stringTimestamp.length() - 1);
        }

        // Conversion factors to seconds
        int seconds = 0;
        switch (timeUnit) {
            case "y": // years
                seconds = number * 365 * 24 * 60 * 60; // 365 days in a year
                break;
            case "mo": // months
                seconds = number * 30 * 24 * 60 * 60; // Average days in a month
                break;
            case "w": // weeks
                seconds = number * 7 * 24 * 60 * 60; // 7 days in a week
                break;
            case "d": // days
                seconds = number * 24 * 60 * 60; // 24 hours in a day
                break;
            case "h": // hours
                seconds = number * 60 * 60;
                break;
            case "m": // minutes
                seconds = number * 60;
                break;
            case "s": // seconds
                seconds = number;
                break;
            default:
                throw new IllegalArgumentException("Unknown time unit: " + timeUnit);
        }

        return seconds;
    }

    // Function to handle compound strings like "4m3w"
    public static int parseCompoundTime(String compoundTimestamp) {
        if (compoundTimestamp == null || compoundTimestamp.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        // Regex pattern to match numbers followed by time units (e.g., "4m", "3w", "1mo")
        Pattern pattern = Pattern.compile("(\\d+)([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(compoundTimestamp);

        int totalSeconds = 0;

        while (matcher.find()) {
            String numberString = matcher.group(1); // The number (e.g., "4" in "4m")
            String unit = matcher.group(2); // The time unit (e.g., "m" in "4m" or "mo" in "4mo")

            int number = Integer.parseInt(numberString);

            // Convert the matched part to seconds and add it to the total
            totalSeconds += formatStringToTime(numberString + unit);
        }

        return totalSeconds;
    }

    public static int getCurrentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

}