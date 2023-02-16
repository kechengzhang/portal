package com.zgtech.portal.dao.entity;

import lombok.Data;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 16:50 星期三
 * @Version 1.0
 */
@Data
public class PortalCaseDO {
    private Long id;
    /**
     * 案件名称
     */
    private String caseName;
    /**
     * 分类id
     *
     */
    private Long classifyId;
    /**
     *
     * 预览图片url
     */
    private String pictureUrl;
    /**
     *
     * 预览水印图片url
     */
    private String waterMarkUrl;

    /**
     *
     * 视频路径
     */
    private String caseVideo;

    /**
     *
     * 案例地址
     */
    private String caseUrl;
    /**
     *
     * 概要说明
     */
    private String summaryDescription;
}
