package com.hplasma.imagecode;

import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ImageCode ic = new ImageCode();
        while (true) {
            String code = ic.createImageCode("ImageCode.jpg");
            System.out.println("===已生成图片验证码(" + ic.getCodeLength() + "位)===");
            System.out.print("请输入验证码（不区分大小写，输入数字0刷新）：");
            String userInput = sc.next();
            if ("0".equals(userInput)) continue;
            if (userInput.equalsIgnoreCase(code)) {
                System.out.println("验证码正确！！！！！");
                break;
            }
            System.out.println("验证码输入错误，请重新输入！");
        }

    }
}
