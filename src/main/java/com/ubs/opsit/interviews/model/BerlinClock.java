package com.ubs.opsit.interviews.model;

import java.util.HashMap;
import java.util.Map;

public class BerlinClock {

    public static final int SECONDS = 0;
    public static final int HOURS_MAJOR = 1;
    public static final int HOURS_MINOR = 2;
    public static final int MINUTES_MAJOR = 3;
    public static final int MINUTES_MINOR = 4;

    private Map<Integer, String[]> lines = new HashMap<>();

    public BerlinClock() {
        init();
    }

    public Map<Integer, String[]> getLines() {
        return lines;
    }

    private void init() {
        lines.put(SECONDS, new String[1]);
        lines.put(HOURS_MAJOR, new String[4]);
        lines.put(HOURS_MINOR, new String[4]);
        lines.put(MINUTES_MAJOR, new String[11]);
        lines.put(MINUTES_MINOR, new String[4]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String[] line : lines.values()) {
            for (String lamp : line) {
                builder.append(lamp);
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    public enum Color {
        RED("R"),
        YELLOW("Y"),
        OFF("O");

        private String colorCode;

        Color(String value) {
            colorCode = value;
        }

        public String code() {
            return colorCode;
        }
    }
}

