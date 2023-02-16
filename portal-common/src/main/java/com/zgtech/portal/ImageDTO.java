package com.zgtech.portal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * @author zkc
 * @description
 * @Date 2023/2/3 17:38 星期五
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class ImageDTO {
    /**
     * 文字内容
     */
    private String text;

    /**
     * 字体颜色和透明度
     */
    private Color color;

    /**
     * 文字内容
     */
    private Font font;

    /**
     * 横坐标
     */
    private Integer x;

    /**
     * 纵坐标
     */
    private Integer y;
}
