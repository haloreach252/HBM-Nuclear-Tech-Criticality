package com.miniverse.hbm.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.resources.language.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class I18nUtil {

    /**
     * Simple wrapper for I18n, for consistency.
     * @param s the translation key
     * @param args translation parameters
     * @return the localized string
     */
    public static String resolveKey(String s, Object... args) {
        return I18n.get(s, args);
    }

    /**
     * Wrapper for I18n but cuts up the result using NTM's line break character ($)
     * @param s the translation key
     * @param args translation parameters
     * @return an array of localized lines split by '$'
     */
    public static String[] resolveKeyArray(String s, Object... args) {
        return resolveKey(s, args).split("\\$");
    }

    /**
     * The same as autoBreak, but it also respects NTM's break character ($) for manual line breaking in addition to the automatic ones.
     * @param font the font renderer instance
     * @param text the text to break
     * @param width the maximum width in pixels
     * @return a list of strings representing the broken lines
     */
    public static List<String> autoBreakWithParagraphs(Font font, String text, int width) {
        String[] paragraphs = text.split("\\$");
        List<String> lines = new ArrayList<>();
        for (String paragraph : paragraphs) {
            lines.addAll(autoBreak(font, paragraph, width));
        }
        return lines;
    }

    /**
     * Turns one string into a list of strings, cutting sentences up to fit within the defined width when rendered.
     * @param font the font renderer instance
     * @param text the text to break
     * @param width the maximum width in pixels
     * @return a list of broken lines
     */
    public static List<String> autoBreak(Font font, String text, int width) {
        List<String> lines = new ArrayList<>();
        // Split the text by spaces.
        String[] words = text.split(" ");
        if (words.length == 0) {
            return lines;
        }
        // Start with the first word.
        lines.add(words[0]);
        // Initial indent is the width of the first word.
        int indent = font.width(words[0]);

        for (int w = 1; w < words.length; w++) {
            // Add the width of the next word plus a space.
            indent += font.width(" " + words[w]);

            // If the new width is within bounds, append the word.
            if (indent <= width) {
                String last = lines.get(lines.size() - 1);
                lines.set(lines.size() - 1, last + " " + words[w]);
            } else {
                // Otherwise, start a new line and reset the indent.
                lines.add(words[w]);
                indent = font.width(words[w]);
            }
        }

        return lines;
    }
}
