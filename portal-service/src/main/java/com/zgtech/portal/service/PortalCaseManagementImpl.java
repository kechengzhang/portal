package com.zgtech.portal.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgtech.portal.api.PortalCaseManagementService;
import com.zgtech.portal.api.model.PortalCaseModel;
import com.zgtech.portal.bean.PageData;
import com.zgtech.portal.dao.entity.PortalCaseDO;
import com.zgtech.portal.dao.entity.PortalCaseWaterMarkDO;
import com.zgtech.portal.dao.mapper.PortalCaseFileMapper;
import com.zgtech.portal.dao.mapper.PortalCaseManagementMapper;
import com.zgtech.portal.utils.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 16:49 星期三
 * @Version 1.0
 */
@Service
public class PortalCaseManagementImpl implements PortalCaseManagementService {
    private static final BeanCopier copier = BeanCopier.create(PortalCaseModel.class, PortalCaseDO.class, false);
    @Autowired
    private PortalCaseFileMapper portalCaseFileMapper;
    @Autowired
    private PortalCaseManagementMapper portalCaseManagementMapper;
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

    @Override
    public int savePortalCase(PortalCaseModel portalCaseModel) {
        PortalCaseDO portalCaseDO = new PortalCaseDO();
        copier.copy(portalCaseModel, portalCaseDO, null);
        if (cache.getIfPresent("iconPath") == null) {
            //查询水印图片地址
            PortalCaseWaterMarkDO portalCaseWaterMarkModel = portalCaseFileMapper.queryCaseWaterMarkPicture();
            cache.put("iconPath", waterMarkIconPath + portalCaseWaterMarkModel.getCaseWaterMarkUrl().split("file")[1]);
            cache.put("status", portalCaseWaterMarkModel.getStatus());
        }
        //查询当前水印是否开启
        String path = ImageConverter.markImageByIcon(cache.getIfPresent("iconPath").toString(), filePath + portalCaseModel.getPictureUrl().split("file")[1], waterMarkPath, null);
        String filePath = fileUrl + path;
        portalCaseDO.setWaterMarkUrl(filePath);
        portalCaseManagementMapper.savePortalCase(portalCaseDO);
        return 1;
    }

    @Override
    public int deletePortalCase(Long id) {
        return portalCaseManagementMapper.deletePortalCase(id);
    }

    @Override
    public int updatePortalCase(PortalCaseModel portalCaseModel) {
        if (cache.getIfPresent("iconPath") == null) {
            //查询水印图片地址
            PortalCaseWaterMarkDO portalCaseWaterMarkModel = portalCaseFileMapper.queryCaseWaterMarkPicture();
            cache.put("iconPath", waterMarkIconPath + portalCaseWaterMarkModel.getCaseWaterMarkUrl().split("file")[1]);
            cache.put("status", portalCaseWaterMarkModel.getStatus());
        }
        //查询当前水印是否开启
        PortalCaseDO portalCaseDO = new PortalCaseDO();
        copier.copy(portalCaseModel, portalCaseDO, null);
        String path = ImageConverter.markImageByIcon(cache.getIfPresent("iconPath").toString(), filePath + portalCaseModel.getPictureUrl().split("file")[1], waterMarkPath, null);
        String filePath = fileUrl + path;
        portalCaseDO.setWaterMarkUrl(filePath);
        portalCaseManagementMapper.updatePortalCase(portalCaseDO);
        return 1;
    }

    @Override
    public PageData queryPortalCase(Integer pageNumber, Integer pageSize, Integer classifyId) {
        PageHelper.startPage(pageNumber, pageSize);
        List<PortalCaseDO> list = portalCaseManagementMapper.queryPortalCase(classifyId);
        PageData pageData = new PageData(new PageInfo(list));
        return pageData;
    }

    @Override
    public Map<String, Object> queryAllCase(Integer classifyId) {
        Map<String, Object> map = new HashMap<>();
        //查询案例是否开启水印
        Integer status = portalCaseManagementMapper.queryAllCaseWaterMark();
        map.put("status", status);
        if (status == null) {
            status = 0;
            map.put("status", status);
        }
        List<PortalCaseDO> list = portalCaseManagementMapper.queryPortalCase(classifyId);
        map.put("list", list);
        return map;
    }
}
