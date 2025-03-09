package com.miniverse.hbm.util;

import com.miniverse.hbm.util.Tuple.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

/**
 * A more lightweight, punctual version of the vanilla profiler.
 */
public class TimeAnalyzer {

    /* Instead of writing to the hashmap outright, we write it to a list
       since using a hashmap during analysis would add unnecessary overhead. */
    private static final List<Pair<String, Long>> deltas = new ArrayList<>();
    private static String currentSection = "";
    private static long sectionStartTime = 0;

    public static void startCount(String section) {
        currentSection = section;
        sectionStartTime = System.nanoTime();
    }

    public static void endCount() {
        long delta = System.nanoTime() - sectionStartTime;
        deltas.add(new Pair<>(currentSection, delta));
    }

    public static void startEndCount(String section) {
        endCount();
        startCount(section);
    }

    public static void dump() {
        HashMap<String, Long> aggregatedTimes = new HashMap<>();

        for (Pair<String, Long> delta : deltas) {
            Long total = aggregatedTimes.get(delta.getKey());
            if (total == null) {
                total = 0L;
            }
            total += delta.getValue();
            aggregatedTimes.put(delta.getKey(), total);
        }

        long totalTime = 0;

        for (Entry<String, Long> entry : aggregatedTimes.entrySet()) {
            totalTime += entry.getValue();
            String time = String.format(Locale.US, "%,d", entry.getValue());
            System.out.println(entry.getKey() + ": " + time + "ns");
        }

        System.out.println("Total time passed: " + String.format(Locale.US, "%,d", totalTime)
                + "ns (" + (totalTime / 1_000_000_000) + "s)");

        currentSection = "";
        sectionStartTime = 0;
        deltas.clear();
    }
}
