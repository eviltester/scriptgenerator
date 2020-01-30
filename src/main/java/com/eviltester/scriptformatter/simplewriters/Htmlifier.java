package com.eviltester.scriptformatter.simplewriters;

public class Htmlifier {
    public static String encode(final String line) {
        return line.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");

    }
}
