package com.zgtech.portal.api;

import com.zgtech.portal.api.model.PortalCaseModel;
import com.zgtech.portal.bean.PageData;

import java.util.Map;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 12:58 星期三
 * @Version 1.0
 */
public interface PortalCaseManagementService {
    /**
     * 案例新增
     * @param portalCaseModle
     * @return
     */
    int savePortalCase(PortalCaseModel portalCaseModle);

    /**
     * 案例删除
     *
     * @param id
     * @return
     */
    int  deletePortalCase(Long id);

    /**
     * 案例修改
     * @param portalCaseModle
     * @return
     */
    int updatePortalCase(PortalCaseModel portalCaseModle);

    /**
     * 案例查询
     * @param pageSize
     * @param pageNumber
     * @param classifyId
     * @return
     */
    PageData queryPortalCase(Integer pageNumber, Integer pageSize,Integer classifyId);

    /**
     * 查询全量案例
     *
     * @return
     */
    Map<String,Object> queryAllCase(Integer classifyId);
}
