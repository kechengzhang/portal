package com.zgtech.portal.api.model;

import lombok.Data;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 13:45 ζζδΈ
 * @Version 1.0
 */
@Data
public class PortalClassifyModel {
    private Long id;
    private String name;
    private String checkedUrl;
    private String uncheckedUrl;
}
