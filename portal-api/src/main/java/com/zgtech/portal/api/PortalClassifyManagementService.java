package com.zgtech.portal.api;


import com.zgtech.portal.api.model.PortalClassifyModel;
import com.zgtech.portal.bean.PageData;
import com.zgtech.portal.dao.entity.PortalClassifyDO;

import java.util.List;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 12:58 星期三
 * @Version 1.0
 */
public interface PortalClassifyManagementService {
    /**
     * 分类保存
     *
     * @param portalClassifyModle
     * @return
     */
    int savePortalClassify(PortalClassifyModel portalClassifyModle);

    /**
     * 分类删除
     *
     * @param id
     * @return
     */
    int deleteClassify(Long id);

    /**
     * 分类查询
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageData queryClassify(Integer pageNumber, Integer pageSize);

    /**
     * 分类修改
     *
     * @param portalClassifyModel
     * @return
     */
   int updateClassify( PortalClassifyModel portalClassifyModel);

    /**
     * 分类全量数据查询
     * @return
     */
    List<PortalClassifyDO> queryAllClassify();

    /**
     * 通过id查询分类
     *
     * @param id
     * @return
     */
    Long queryClassifyId(Long id);
}
