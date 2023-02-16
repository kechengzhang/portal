package com.zgtech.portal.dao.mapper;

import com.zgtech.portal.dao.entity.PortalCaseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 16:56 星期三
 * @Version 1.0
 */
@Mapper
public interface PortalCaseManagementMapper {
    /**
     * 案例新增
     * @param portalCaseDO
     * @return
     */
    int savePortalCase(@Param("portalCaseDO") PortalCaseDO portalCaseDO);
    /**
     * 案例删除
     * @param id
     * @return
     */
    int  deletePortalCase(@Param("id") Long id);
    /**
     * 案例修改
     * @param portalCaseDO
     * @return
     */
    int updatePortalCase(@Param("portalCaseDO") PortalCaseDO portalCaseDO);

    /**
     * 案例查询
     * @param classifyId
     * @return
     */
    List<PortalCaseDO> queryPortalCase(@Param("classifyId") Integer classifyId);

    /**
     * 查询案例是否开启水印
     *
     * @return
     */
    Integer queryAllCaseWaterMark();

}
