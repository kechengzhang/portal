package com.zgtech.portal.dao.entity;

import lombok.Data;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 12:58 星期三
 * @Version 1.0
 */
@Data
public class PortalClassifyDO {
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 选中图片
     *
     */
    private String checkedUrl;
    /**
     * 未选中图片
     *
     */
    private String uncheckedUrl;
}
