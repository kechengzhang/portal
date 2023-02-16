package com.zgtech.portal.api;

import com.zgtech.portal.api.model.PortalCaseFileModel;
import com.zgtech.portal.api.model.PortalCaseWaterMarkModel;
import com.zgtech.portal.dao.entity.PortalCaseWaterMarkDO;

/**
 * @author zkc
 * @description
 * @Date 2023/2/6 12:58 星期一
 * @Version 1.0
 */
public interface PortalCaseFileService {
    /**
     * 新增案例图片
     *
     * @param portalCaseWaterMarkModel
     * @return
     */
    int savePortalCasePicture(PortalCaseWaterMarkModel portalCaseWaterMarkModel);

    /**
     * 修改是否展示水印
     * @param portalCaseWaterMarkModel
     * @return
     */
    int updatePortalCaseFileStatus(PortalCaseWaterMarkModel portalCaseWaterMarkModel);

    /**
     * 修改案例水印图片url
     *
     * @param portalCaseWaterMarkModel
     * @return
     */
    int updateCaseWaterMark(PortalCaseWaterMarkModel portalCaseWaterMarkModel);

    /**
     * 水印图查询
     *
     * @return
     */
    PortalCaseWaterMarkDO queryCaseWaterMarkPicture();

    /**
     * 水印图删除
     *
     * @param id
     * @return
     *
     */
    int deleteCaseWaterMarkPicture( Long id);

    /**
     * 水印图片修改
     *
     * @param waterMarkUrl
     * @return
     */
    int updateWaterMarkPicture(String waterMarkUrl);

    /**
     * 水印图片上传
     * @param portalCasePictureModel
     * @return
     */
    void watermarkImageUploading(PortalCaseFileModel portalCasePictureModel);
}
