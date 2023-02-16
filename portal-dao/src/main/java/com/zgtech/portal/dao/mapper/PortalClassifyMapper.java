package com.zgtech.portal.dao.mapper;

import com.zgtech.portal.dao.entity.PortalClassifyDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author zkc
 *
 *
 */
@Mapper
public interface PortalClassifyMapper {
    /**
     * 分类保存
     *
     * @param portalClassifyDO
     * @return
     */
    int savePortalClassify(PortalClassifyDO portalClassifyDO);
    /**
     * 分类删除
     * @param id
     * @return
     */
    int deleteClassify(Long id);

    /**
     * 分类查询
     *
     * @return
     */
    List<PortalClassifyDO> queryClassify();

    /**
     * 修改分类
     *
     * @param portalClassifyDO
     * @return
     */
    int updateClassify(PortalClassifyDO portalClassifyDO);
    /**
     * 分类全量数据查询
     * @return
     */
    List<PortalClassifyDO>queryAllClassify();

    /**
     * 通过id查询分类
     *
     * @param id
     * @return
     */
    Long queryClassifyId(Long id);
}
