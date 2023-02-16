package com.zgtech.portal.api.model;

import lombok.Data;

import java.util.List;

/**
 * @author zkc
 * @description
 * @Date 2023/2/6 13:47 星期一
 * @Version 1.0
 */
@Data
public class PortalCaseFileModel {
    /**
     * 水印图片id
     *
     */
    private Long id;
    /**
     * 是否开启
     *
     */
    private Integer status;
    /**
     *
     *
     */
    List<PortalCaseWaterMarkModel> portalCaseModels;

}
