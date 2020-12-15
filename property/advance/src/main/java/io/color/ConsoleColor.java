package io.color;

public class ConsoleColor {
    public static void main(String[] args) {
        String str = "\u001b[39m[DEBUG]\u001b[0;39m \u001b[35m[main]\u001b[0;39m[%PARSER_ERROR[tid]]\u001b[32m[2020-12-09 06:38:33.733]\u001b[0;39m \u001b[36m[ROOT:43]\u001b[0;39m - Message 1";
        System.out.println(str);

        // 这里 八进制 的 \033 等于 16进制 的 \u001b
        // 很遗憾，java 中没有十进制表示方法和 \x1b 这种表示方法
        System.out.println("\033[31m[DEBUG]");
        System.out.println("\u001b[31m[DEBUG]");

        System.out.println("\u001b[38;2;215;84;85mHello World");
    }
}
