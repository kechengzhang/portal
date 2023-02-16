package com.zgtech.portal.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * @author zkc
 * <p>
 * 图片工具类
 */
@Slf4j
public class PictureUtils {
    /**
     * 水印字体
     */
    public static final Font FONT = new Font("微软雅黑", Font.PLAIN, 16);
    /**
     * 透明度
     */
    private static final AlphaComposite COMPOSITE = AlphaComposite
            .getInstance(AlphaComposite.SRC_OVER, 0.6f);
    /**
     * 水印之间的间隔
     */
    private static final int X_MOVE = 80;

    /**
     * 水印之间的间隔
     */
    private static final int Y_MOVE = 130;

    public static String upload(MultipartFile multipartFile, String filePath, String url) {
        try {
            //获取文件名称
            String fileName = multipartFile.getOriginalFilename();
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //获取uuid
            String uuid = UUID.randomUUID() + suffixName;
            File file = new File(filePath);
            if (!file.exists()) {
                //判断当前文件夹是否存在
                file.mkdirs();
            }
            //设置文件路径
            filePath = filePath + "//" + uuid;
            File file1 = new File(filePath);
            //写入文件
            multipartFile.transferTo(file1);
            //图片url
            String pictureUrl = url + uuid;
            return pictureUrl;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 将图片转为file
     *
     * @param url 图片url
     * @return File
     * @author dyc
     * date:   2020/9/4 14:54
     */
    private static File getFile(String url) throws Exception {
        //对本地文件命名
        String fileName = url.substring(url.lastIndexOf("."), url.length());
        File file = null;
        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("net_url", fileName);
            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    /**
     * 删除文件夹下所有文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        //判断是否为文件，是，则删除
        if (file.isFile()) {
            file.delete();
        }
        //不为文件，则为文件夹
        else {
            //获取文件夹下所有文件相对路径
            String[] childFilePath = file.list();
            for (String path : childFilePath) {
                //递归，对每个都进行判断
                deleteFile(file.getAbsoluteFile() + "/" + path);
            }
            // 如果不保留文件夹本身 则执行此行代码
            //file.delete();
        }
    }

    /**
     * 打水印(文字)
     *
     * @param srcImgPath       源文件地址
     * @param font             字体
     * @param markContentColor 水印颜色
     * @param waterMarkContent 水印内容
     * @param outImgPath       输出文件的地址
     */
    public static void markWithContent(String srcImgPath, Font font, Color markContentColor,
                                       String waterMarkContent,
                                       String outImgPath) {
        FileOutputStream fos = null;
        try {
            // 读取原图片信息
            File srcFile = new File(srcImgPath);
            File outFile = new File(outImgPath);
            BufferedImage srcImg = ImageIO.read(srcFile);
            // 图片宽、高
            int imgWidth = srcImg.getWidth();
            int imgHeight = srcImg.getHeight();

            // 图片缓存
            BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            // 创建绘图工具
            Graphics2D graphics = bufImg.createGraphics();
            // 画入原始图像
            graphics.drawImage(srcImg, 0, 0, imgWidth, imgHeight, null);
            // 设置水印颜色
            graphics.setColor(markContentColor);
            // 设置水印透明度
            graphics.setComposite(COMPOSITE);
            // 设置倾斜角度
            graphics.rotate(Math.toRadians(-35), (double) bufImg.getWidth() / 2,
                    (double) bufImg.getHeight() / 2);
            // 设置水印字体
            graphics.setFont(font);
            // 消除java.awt.Font字体的锯齿
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int xCoordinate = -imgWidth / 2, yCoordinate;
            // 字体长度
            int markWidth = FONT.getSize() * getTextLength(waterMarkContent);
            // 字体高度
            int markHeight = FONT.getSize();
            // 循环添加水印
            while (xCoordinate < imgWidth * 1.5) {
                yCoordinate = -imgHeight / 2;
                while (yCoordinate < imgHeight * 1.5) {
                    graphics.drawString(waterMarkContent, xCoordinate, yCoordinate);
                    yCoordinate += markHeight + Y_MOVE;
                }
                xCoordinate += markWidth + X_MOVE;
            }
            // 释放画图工具
            graphics.dispose();
            // 输出图片
            fos = new FileOutputStream(outFile);
            ImageIO.write(bufImg, "jpg", fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 计算水印文本长度
     * 1、中文长度即文本长度 2、英文长度为文本长度二分之一
     *
     * @param text
     * @return
     */
    public static int getTextLength(String text) {
        //水印文字长度
        int length = text.length();
        for (int i = 0; i < text.length(); i++) {
            String s = String.valueOf(text.charAt(i));
            if (s.getBytes().length > 1) {
                length++;
            }
        }
        length = length % 2 == 0 ? length / 2 : length / 2 + 1;
        return length;
    }

    public static void main(String[] args) {
        PictureUtils.markWithContent("d:/a/3.jpg", FONT, Color.darkGray, "中冠科技",
                "d:/a/4.jpg");
    }

}