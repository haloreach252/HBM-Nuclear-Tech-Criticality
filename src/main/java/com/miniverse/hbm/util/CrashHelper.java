package com.miniverse.hbm.util;

import com.miniverse.hbm.inventory.recipes.loader.SerializableRecipe;

/**
 * CrashHelper provides utility methods for retrieving details about modified recipes.
 *
 * <p>In the original mod (Forge 1.7.10), this class registered a crash callable that added a list of modified
 * recipe file names to crash reports using FMLCommonHandler and ICrashCallable. In Forge 1.20.1, that registration
 * mechanism no longer exists. Instead, you can call {@link #getModifiedRecipeDetails()} from your crash reporting
 * code to include the modified recipes information in your crash reports.</p>
 */
public class CrashHelper {

    /**
     * Returns a string containing the filenames of all modified recipes.
     *
     * @return a formatted string with details of modified recipes.
     */
    public static String getModifiedRecipeDetails() {
        StringBuilder details = new StringBuilder();
        for (SerializableRecipe rec : SerializableRecipe.recipeHandlers) {
            if (rec.modified) {
                details.append("\n\t\t").append(rec.getFileName());
            }
        }
        return details.toString();
    }
}
