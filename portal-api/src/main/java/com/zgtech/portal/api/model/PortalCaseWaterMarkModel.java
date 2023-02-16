package com.zgtech.portal.api.model;

import lombok.Data;

/**
 * @author zkc
 * @description
 * @Date 2023/2/6 17:13 星期一
 * @Version 1.0
 */
@Data
public class PortalCaseWaterMarkModel {
    private Long id;
    private Integer status;
    private String caseWaterMarkUrl;
}
