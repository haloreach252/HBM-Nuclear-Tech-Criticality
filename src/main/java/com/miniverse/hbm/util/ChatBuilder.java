package com.miniverse.hbm.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ChatBuilder {

    private MutableComponent text;
    private MutableComponent last;

    private ChatBuilder(String text) {
        this.text = Component.literal(text);
        this.last = this.text;
    }

    public static ChatBuilder start(String text) {
        return new ChatBuilder(text);
    }

    public static ChatBuilder startTranslation(String key, Object... args) {
        ChatBuilder builder = new ChatBuilder("").nextTranslation(key, args);
        return builder;
    }

    public ChatBuilder next(String text) {
        MutableComponent append = Component.literal(text);
        this.last.append(append);
        this.last = append;
        return this;
    }

    public ChatBuilder nextTranslation(String key, Object... args) {
        MutableComponent append = Component.translatable(key, args);
        this.last.append(append);
        this.last = append;
        return this;
    }

    public ChatBuilder color(ChatFormatting format) {
        this.last.setStyle(this.last.getStyle().withColor(format));
        return this;
    }

    /**
     * Recursively goes over all components added to the root and sets their style color.
     */
    public ChatBuilder colorAll(ChatFormatting format) {
        List<MutableComponent> list = new ArrayList<>();
        list.add(this.text);
        ListIterator<MutableComponent> it = list.listIterator();
        while (it.hasNext()) {
            MutableComponent component = it.next();
            component.setStyle(component.getStyle().withColor(format));
            for (Component child : component.getSiblings()) {
                if (child instanceof MutableComponent) {
                    it.add((MutableComponent) child);
                }
            }
        }
        return this;
    }

    public MutableComponent flush() {
        return this.text;
    }
}
