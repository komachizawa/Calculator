package com.company;

import java.util.Scanner;
public class ReadInput {


    public static String read() {
        // 文字入力させるための呪文。基本コピーでOK
        Scanner scanner = new Scanner (System.in);

        // 説明文表示してるだけ（System.out.println()は文字出力の呪文）
        System.out.println("計算式を入力してください(for eg: 4*3/2)");
        System.out.print("式：");



        // 文字入力の呪文 = 4*3/2
        String inputLine = scanner.nextLine();

        // 文字入力終わりの呪文
        scanner.close();

        return inputLine;
    }
}