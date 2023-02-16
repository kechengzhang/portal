package com.zgtech.portal.web;


import com.zgtech.portal.api.PortalClassifyManagementService;
import com.zgtech.portal.api.model.PortalClassifyModel;
import com.zgtech.portal.bean.PageData;
import com.zgtech.portal.bean.Result;
import com.zgtech.portal.dao.entity.PortalClassifyDO;
import com.zgtech.portal.enums.ResultCodeEnum;
import com.zgtech.portal.utils.PictureUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zkc
 * @description
 * @Date 2023/2/1 13:04 星期三
 * @Version 1.0
 */
@RestController
@Api(tags = "分类管理")
public class PortalClassifyManagementController {
    @Value("${path.url}")
    private String fileUrl;
    @Value("${path.filepath}")
    private String filePath;
    @Autowired
    private PortalClassifyManagementService portalClassifyService;
    @ApiOperation(value = "新增分类")
    @PostMapping("saveClassify")
    public Result saveClassify( @RequestBody PortalClassifyModel portalClassifyModel) {
        portalClassifyService.savePortalClassify(portalClassifyModel);
        return Result.success(ResultCodeEnum.SAVE_SUCCESS);
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping("deleteClassify")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="分类id",required = true,paramType="query",dataType="Long"),
    })
    public Result deleteClassify(Long id) {
        //查询该分类是否有使用
        Long classId = portalClassifyService.queryClassifyId(id);
        if(classId != null){
            return Result.success(ResultCodeEnum.DELETE_ERROR);
        }
        portalClassifyService.deleteClassify(id);
        return Result.success(ResultCodeEnum.DELETE_SUCCESS);
    }
    @ApiOperation(value = "查询分类列表")
    @GetMapping("queryClassify")
        @ApiImplicitParams({
            @ApiImplicitParam(name="pageNumber",value="当前页数",required = false,paramType = "query",dataType = "Integer"),
                @ApiImplicitParam(name="pageSize",value="每页条数",required = false,paramType = "query",dataType = "Integer")
    })
    public Result<PageData> queryClassify(@RequestParam("pageNumber")Integer pageNumber, @RequestParam("pageSize")Integer pageSize) {
        PageData pageData = portalClassifyService.queryClassify(pageNumber,pageSize);
        return Result.success(ResultCodeEnum.QUERY_SUCCESS, pageData);
    }
    @ApiOperation(value = "修改分类")
    @PutMapping("updateClassify")
    public Result updateClassify(@RequestBody PortalClassifyModel portalClassifyModel) {
        portalClassifyService.updateClassify(portalClassifyModel);
        return Result.success(ResultCodeEnum.UPDATE_SUCCESS);
    }

    @ApiOperation(value = "查询全部分类")
    @GetMapping("queryAllClassify")
    public Result queryAllClassify() {
        List<PortalClassifyDO> list = portalClassifyService.queryAllClassify();
        return Result.success(ResultCodeEnum.QUERY_SUCCESS,list);
    }
    @ApiOperation(value = "分类图片上传")
    @PostMapping("uploadClassifyPicture")
    public Result uploadClassifyPicture(@RequestParam("multipartFile")MultipartFile multipartFile) {
        String  url = PictureUtils.upload(multipartFile,filePath,fileUrl);
        String [] pathArray =url.split(",");
        return Result.success(ResultCodeEnum.FILE_UPLOAD_SUCCESS,pathArray[0]);
    }
}
