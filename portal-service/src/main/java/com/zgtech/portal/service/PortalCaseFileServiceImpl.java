package com.zgtech.portal.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.zgtech.portal.api.PortalCaseFileService;
import com.zgtech.portal.api.model.PortalCaseFileModel;
import com.zgtech.portal.api.model.PortalCaseWaterMarkModel;
import com.zgtech.portal.dao.entity.PortalCasePictureDO;
import com.zgtech.portal.dao.entity.PortalCaseWaterMarkDO;
import com.zgtech.portal.dao.mapper.PortalCaseFileMapper;
import com.zgtech.portal.utils.ImageConverter;
import com.zgtech.portal.utils.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * @author zkc
 * @description
 * @Date 2023/2/6 12:58 星期一
 * @Version 1.0
 */
@Service
public class PortalCaseFileServiceImpl implements PortalCaseFileService {
    @Autowired
    private Cache<String, Object> cache;
    @Value("${path.url}")
    private String fileUrl;
    @Value("${path.filepath}")
    private String filePath;
    @Value("${path.watermarkpath}")
    private String waterMarkPath;
    @Value("${path.watermarkiconpath}")
    private String waterMarkIconPath;
    @Autowired
    private PortalCaseFileMapper portalCaseFileMapper;
    private static final BeanCopier copier = BeanCopier.create(PortalCaseWaterMarkModel.class, PortalCasePictureDO.class, false);
    private static final BeanCopier copier1 = BeanCopier.create(PortalCaseWaterMarkModel.class, PortalCaseWaterMarkDO.class, false);

    @Override
    public int savePortalCasePicture(PortalCaseWaterMarkModel portalCasePictureModel) {
        PortalCasePictureDO portalCasePictureDO = new PortalCasePictureDO();
        copier.copy(portalCasePictureModel, portalCasePictureDO, null);
        return portalCaseFileMapper.savePortalCaseFile(portalCasePictureDO);
    }

    @Override
    public int updatePortalCaseFileStatus(PortalCaseWaterMarkModel portalCaseWaterMarkModel) {
        PortalCasePictureDO portalCasePictureDO = new PortalCasePictureDO();
        copier.copy(portalCaseWaterMarkModel, portalCasePictureDO, null);
        return portalCaseFileMapper.updatePortalCaseFileStatus(portalCasePictureDO);
    }

    @Override
    public int updateCaseWaterMark(PortalCaseWaterMarkModel portalCaseWaterMarkModel) {
        PortalCaseWaterMarkDO portalCaseWaterMarkDO = new PortalCaseWaterMarkDO();
        copier1.copy(portalCaseWaterMarkModel, portalCaseWaterMarkDO, null);
        return portalCaseFileMapper.updateCaseWaterMark(portalCaseWaterMarkDO);
    }

    @Override
    public PortalCaseWaterMarkDO queryCaseWaterMarkPicture() {
        PortalCaseWaterMarkDO portalCaseWaterMarkDO = portalCaseFileMapper.queryCaseWaterMarkPicture();
        if (portalCaseWaterMarkDO == null) {
            portalCaseWaterMarkDO = new PortalCaseWaterMarkDO();
            portalCaseWaterMarkDO.setId(0L);
            portalCaseWaterMarkDO.setStatus(0);
        }
        return portalCaseWaterMarkDO;
    }

    @Override
    public int deleteCaseWaterMarkPicture(Long id) {
        return portalCaseFileMapper.deleteCaseWaterMarkPicture(id);
    }

    @Override
    public int updateWaterMarkPicture(String waterMarkUrl) {
        return portalCaseFileMapper.updateWaterMarkPicture(waterMarkUrl);
    }

    @Override
    public void watermarkImageUploading(PortalCaseFileModel portalCasePictureModel) {
        //删除文件夹下所有文件
        PictureUtils.deleteFile(waterMarkPath);
        if (cache.getIfPresent("iconPath") == null) {
            //查询水印图片地址
            PortalCaseWaterMarkDO portalCaseWaterMarkModel = portalCaseFileMapper.queryCaseWaterMarkPicture();
            cache.put("iconPath", waterMarkIconPath + portalCaseWaterMarkModel.getCaseWaterMarkUrl().split("file")[1]);
            cache.put("status", portalCaseWaterMarkModel.getStatus());
        }
        String iconPath = String.valueOf(cache.getIfPresent("iconPath"));
        portalCasePictureModel.getPortalCaseModels().stream().forEach(portalCaseModel -> {
            String[] caseWaterMarkUrlArray = portalCaseModel.getCaseWaterMarkUrl().split("file");
//                String path = ImageConverter.markImageByIcon("d:/a/icon.jpg", filePath + caseWaterMarkUrlArray[1], null);
            String path = ImageConverter.markImageByIcon(iconPath, filePath + caseWaterMarkUrlArray[1], waterMarkPath, null);
            String filePath = fileUrl + path;
            portalCaseModel.setCaseWaterMarkUrl(filePath);
            updateCaseWaterMark(portalCaseModel);
        });
    }


}
