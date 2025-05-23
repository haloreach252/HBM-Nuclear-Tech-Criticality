package com.miniverse.hbm.util;

import com.miniverse.hbm.HBMNuclearTechCriticality;

public class LoggingUtil {

    /*
     * Only works with ANSI-compatible outputs. http://www.mihai-nita.net/eclipse works really well for dev envs.
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Logs an error message with ANSI red highlight.
     *
     * @param error The error message to log.
     */
    public static void errorWithHighlight(String error) {
        HBMNuclearTechCriticality.LOGGER.error(ANSI_RED + error + ANSI_RESET);
    }
}
