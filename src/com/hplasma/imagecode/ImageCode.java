package com.hplasma.imagecode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 图片验证码生成器
 */

public class ImageCode {
    private final int codeLength = 4;   // 验证码长度

    public int getCodeLength() {
        return codeLength;
    }

    // 基本字符
    static String[] strings = {
//            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
//            "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
//            "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N", "P", "Q", "R",
//            "S", "T", "U", "V", "W", "X", "Y", "Z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
//            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
//            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
    };

    /**
     * 生成验证码图片
     */
    public String createImageCode(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        /* 1. 生成画板类 */
        int width = 150;        // 宽度
        int height = 50;        // 高度
        int imageType = BufferedImage.TYPE_INT_RGB;      // 图片类型（=1）
        BufferedImage image = new BufferedImage(width, height, imageType);

        /* 2. 获取画笔对象 */
        Graphics g = image.getGraphics();
        Random rand = new Random();     // 随机数生成器
        // 设定画笔颜色，画填充矩形
        g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        g.fillRect(0, 0, width, height);
        // 修改画笔颜色与字体
        g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        g.setFont(new Font("黑体", Font.PLAIN, rand.nextInt(15) + 25));

        /* 3. 准备数据：从数组中随机取若干个 */
        int x = width / 15, y = height / 4 * 3;     // 字符坐标
        for (int i = 0; i < codeLength; i++) {
            int num = rand.nextInt(strings.length);
            String str = strings[num];
            sb.append(str);
            g.drawString(str, x, y);
            x += rand.nextInt(12) + width / 9;
            y += rand.nextInt(10) - 5;
            g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        }

        /* 4. 画干扰线 */
        g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        int lineNum = rand.nextInt(5) + 3;
        for (int i = 0; i < lineNum; i++) {
            int x1 = rand.nextInt(30);
            int y1 = rand.nextInt(50);
            int x2 = rand.nextInt(30) + 120;
            int y2 = rand.nextInt(50);
            g.drawLine(x1, y1, x2, y2);
            g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        }

        /* 5. 生成image */
        String filePath = "/Users/hyperplasma/workspace/codes/Java/ProtoImageCode/" + fileName;
        ImageIO.write(image, "jpg", new File(filePath));
        return sb.toString();
    }
}
