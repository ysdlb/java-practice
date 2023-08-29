package org.ysdlb.io;

import java.io.PrintStream;

public class ColorOut {

    private static final PrintStream OUT = System.out;

    // ************* 16 color **************
    private static final String RED = "\033[31m";

    // ************** erase color ************
    private static final String ERASE = "\033[0m";

    public static void rPrintln(String s) {
        OUT.println(RED + s + ERASE);
    }

    public static void info(String s) {
        rPrintln(s);
    }

}
