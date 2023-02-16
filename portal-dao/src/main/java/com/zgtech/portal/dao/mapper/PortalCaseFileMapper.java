package com.zgtech.portal.dao.mapper;

import com.zgtech.portal.dao.entity.PortalCasePictureDO;
import com.zgtech.portal.dao.entity.PortalCaseWaterMarkDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zkc
 * @description
 * @Date 2023/2/6 16:56 星期一
 * @Version 1.0
 */
@Mapper
public interface PortalCaseFileMapper {
    /**
     * 新增案例图片
     *
     * @param portalCasePictureDO
     * @return
     */
    int savePortalCaseFile(@Param("portalCasePictureDO") PortalCasePictureDO portalCasePictureDO);

    /**
     * 修改水印状态
     * @param portalCasePictureModel
     * @return
     */
    int updatePortalCaseFileStatus(PortalCasePictureDO portalCasePictureModel);

    /**
     * 修改水印文件
     *
     * @param portalCaseWaterMarkDO
     * @return
     */
    int updateCaseWaterMark(PortalCaseWaterMarkDO portalCaseWaterMarkDO);

    /**
     *水印图查询
     *
     * @return
     */
    PortalCaseWaterMarkDO queryCaseWaterMarkPicture();

    /**
     * 删除水印图
     *
     * @param id
     * @return
     */
    int deleteCaseWaterMarkPicture(@Param("id") Long id );

    /**
     * 水印图片修改
     * @param waterMarkUrl
     * @return
     */
    int updateWaterMarkPicture(@Param("waterMarkUrl") String waterMarkUrl);
}
