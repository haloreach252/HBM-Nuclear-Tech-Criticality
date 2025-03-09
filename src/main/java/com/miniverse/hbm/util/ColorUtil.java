package com.miniverse.hbm.util;

import com.mojang.blaze3d.platform.TextureUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ColorUtil {

    /**
     * Returns the BufferedImage for the given item stack.
     * <p>
     * This method obtains the ModelResourceLocation via the item model shaper and then constructs
     * a ResourceLocation pointing to "textures/items/<name>.png". Note that in modern Minecraft,
     * many item textures reside in a texture atlas so this approach works for many (but not all) items.
     *
     * @param stack the item stack to retrieve the texture from.
     * @return the BufferedImage for the item texture.
     * @throws IOException if the texture cannot be read.
     */
    @OnlyIn(Dist.CLIENT)
    public static BufferedImage getImageFromStack(ItemStack stack) throws IOException {
        // Retrieve the model resource location for the item.
        ModelResourceLocation mrl = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getModelLocation(stack);
        String domain = mrl.getNamespace();
        String path = mrl.getPath();
        // If the model path starts with "item/", remove that prefix.
        if (path.startsWith("item/")) {
            path = path.substring(5);
        }
        ResourceLocation loc = new ResourceLocation(domain, "textures/items/" + path + ".png");
        return ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(loc).getInputStream());
    }

    /**
     * Computes the average RGB color from the opaque pixels of the item texture.
     *
     * @param stack the item stack.
     * @return the average color as an RGB integer.
     */
    @OnlyIn(Dist.CLIENT)
    public static int getAverageColorFromStack(ItemStack stack) {
        try {
            BufferedImage tex = getImageFromStack(stack);
            int r = 0;
            int g = 0;
            int b = 0;
            int pixels = 0;
            for (int i = 0; i < tex.getWidth(); i++) {
                for (int j = 0; j < tex.getHeight(); j++) {
                    Color pixel = new Color(tex.getRGB(i, j), true);
                    if (pixel.getAlpha() == 255) {
                        r += pixel.getRed();
                        g += pixel.getGreen();
                        b += pixel.getBlue();
                        pixels++;
                    }
                }
            }
            int avgR = r / pixels;
            int avgG = g / pixels;
            int avgB = b / pixels;
            return (avgR << 16) | (avgG << 8) | avgB;
        } catch (Exception ex) {
            return 0xFFFFFF;
        }
    }

    /**
     * Computes the median brightness color from the item texture.
     *
     * @param stack the item stack.
     * @return the median brightness color as an RGB integer.
     */
    @OnlyIn(Dist.CLIENT)
    public static int getMedianBrightnessColorFromStack(ItemStack stack) {
        try {
            BufferedImage tex = getImageFromStack(stack);
            HashMap<Integer, Color> brightMap = new HashMap<>();
            List<Integer> brightnesses = new ArrayList<>();
            for (int i = 0; i < tex.getWidth(); i++) {
                for (int j = 0; j < tex.getHeight(); j++) {
                    Color pixel = new Color(tex.getRGB(i, j), true);
                    int brightness = pixel.getRed() * pixel.getRed() + pixel.getGreen() * pixel.getGreen() + pixel.getBlue() * pixel.getBlue();
                    brightnesses.add(brightness);
                    brightMap.put(brightness, pixel); // Overlap possible; acceptable for our purposes.
                }
            }
            Collections.sort(brightnesses);
            int median = brightnesses.get(brightnesses.size() / 2);
            Color medianColor = brightMap.get(median);
            return medianColor.getRGB();
        } catch (Exception ex) {
            return 0xFFFFFF;
        }
    }

    /**
     * Determines whether a given color is considered "colorful" – that is,
     * not too dark and not too close to gray.
     *
     * @param hex the RGB color.
     * @return true if the color is colorful; false otherwise.
     */
    public static boolean isColorColorful(int hex) {
        Color color = new Color(hex);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), new float[3]);
        return hsb[1] > 0.25f && hsb[2] > 0.25f;
    }

    /**
     * Scales the color so that its highest RGB component equals the given limit.
     *
     * @param hex   the RGB color.
     * @param limit the maximum value for the highest component.
     * @return the amplified color.
     */
    public static int amplifyColor(int hex, int limit) {
        Color color = new Color(hex);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int max = Math.max(Math.max(1, r), Math.max(g, b));
        r = r * limit / max;
        g = g * limit / max;
        b = b * limit / max;
        return new Color(r, g, b).getRGB();
    }

    /**
     * Amplifies the color using 255 as the limit.
     *
     * @param hex the RGB color.
     * @return the amplified color.
     */
    public static int amplifyColor(int hex) {
        return amplifyColor(hex, 255);
    }

    /**
     * Lightens the given color by a specified percentage.
     * A percentage of 1 (100%) will yield white.
     *
     * @param hex     the original color.
     * @param percent the percentage to lighten (0.0 - 1.0).
     * @return the lightened color.
     */
    public static int lightenColor(int hex, double percent) {
        Color color = new Color(hex);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        r = (int) (r + (255 - r) * percent);
        g = (int) (g + (255 - g) * percent);
        b = (int) (b + (255 - b) * percent);
        return new Color(r, g, b).getRGB();
    }

    /**
     * Converts a color into HSB and returns the brightness component.
     *
     * @param hex the RGB color.
     * @return the brightness (0.0 - 1.0).
     */
    public static double getColorBrightness(int hex) {
        Color color = new Color(hex);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), new float[3]);
        return hsb[2];
    }

    public static HashMap<String, Integer> nameToColor = new HashMap<String, Integer>() {{
        put("black", 1973019);
        put("red", 11743532);
        put("green", 3887386);
        put("brown", 5320730);
        put("blue", 2437522);
        put("purple", 8073150);
        put("cyan", 2651799);
        put("silver", 11250603);
        put("gray", 4408131);
        put("pink", 14188952);
        put("lime", 4312372);
        put("yellow", 14602026);
        put("lightblue", 6719955);
        put("magenta", 12801229);
        put("orange", 15435844);
        put("white", 15790320);
    }};

    /**
     * Returns a dye color for the given item stack.
     * <p>
     * In modern Minecraft, the ore dictionary is no longer available so we check if the item is a DyeItem
     * and then return its dye color's firework color.
     *
     * @param stack the item stack.
     * @return the dye color as an RGB integer, or 0 if not applicable.
     */
    public static int getColorFromDye(ItemStack stack) {
        if (stack.getItem() instanceof DyeItem) {
            DyeColor dye = ((DyeItem) stack.getItem()).getDyeColor();
            return dye.getFireworkColor();
        }
        return 0;
    }
}
