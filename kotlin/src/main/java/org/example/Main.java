package org.example;

import javax.script.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = scriptEngineManager.getEngineFactories();
        ScriptEngine kts = scriptEngineManager.getEngineByExtension("kts");

        System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String script = scanner.nextLine();
                Object r = kts.eval(script);
                System.out.format("\n-> %s%s%s\n", "\033[38;5;118m", r, "\033[0m");
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
    }
}