package de.praxisit.tips.profiles.config;

/**
 * All spring profiles.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
public class Profiles {

    // Stage

    public static final String TEST = "test";
    public static final String QA = "qa";
    public static final String PROD = "prod";
    public static final String NOT_TEST = "!" + TEST;

    // Database

    public static final String H2_MEM = "h2-mem";
    public static final String H2_EMBEDDED = "h2-embedded";
    public static final String H2_SERVER = "h2-server";

}
