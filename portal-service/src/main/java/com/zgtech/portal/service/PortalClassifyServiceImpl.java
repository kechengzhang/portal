package com.zgtech.portal.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgtech.portal.api.PortalClassifyManagementService;
import com.zgtech.portal.api.model.PortalClassifyModel;
import com.zgtech.portal.bean.PageData;
import com.zgtech.portal.dao.entity.PortalClassifyDO;
import com.zgtech.portal.dao.mapper.PortalClassifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 13:02 星期三
 * @Version 1.0
 */
@Service
public class PortalClassifyServiceImpl implements PortalClassifyManagementService {
    private static final BeanCopier copier = BeanCopier.create(PortalClassifyModel.class, PortalClassifyDO.class, false);
    private static final BeanCopier copier1 = BeanCopier.create(PortalClassifyDO.class, PortalClassifyModel.class, false);
    @Autowired
    private PortalClassifyMapper portalClassifyMapper;
    @Override
    public int savePortalClassify(PortalClassifyModel portalClassifyModle) {
        PortalClassifyDO portalClassifyDO =new PortalClassifyDO();
        copier.copy(portalClassifyModle,portalClassifyDO,null);
        portalClassifyMapper.savePortalClassify(portalClassifyDO);
        return 1;
    }

    @Override
    public int deleteClassify(Long id) {
        portalClassifyMapper.deleteClassify(id);
        return 1;
    }

    @Override
    public PageData queryClassify(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<PortalClassifyDO> list = portalClassifyMapper.queryClassify();
//        List<PortalClassifyModel> list1 = new ArrayList<>();
//        list.stream().forEach(portalClassifyDO->{
//            PortalClassifyModel portalClassifyModel =new PortalClassifyModel();
//            copier1.copy(portalClassifyDO,portalClassifyModel,null);
//            list1.add(portalClassifyModel);
//        });
        PageData pageData = new PageData(new PageInfo(list));

        return pageData;
    }

    @Override
    public int updateClassify(PortalClassifyModel portalClassifyModel) {
        PortalClassifyDO portalClassifyDO =new PortalClassifyDO();
        copier.copy(portalClassifyModel,portalClassifyDO,null);
        portalClassifyMapper.updateClassify(portalClassifyDO);
        return 1;
    }

    @Override
    public List<PortalClassifyDO> queryAllClassify() {
        return portalClassifyMapper.queryAllClassify();
    }

    @Override
    public Long queryClassifyId(Long id) {
        return  portalClassifyMapper.queryClassifyId(id);
    }
}
