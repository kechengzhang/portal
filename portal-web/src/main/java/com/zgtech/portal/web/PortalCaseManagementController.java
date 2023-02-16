package com.zgtech.portal.web;


import com.github.benmanes.caffeine.cache.Cache;
import com.zgtech.portal.api.PortalCaseManagementService;
import com.zgtech.portal.api.model.PortalCaseModel;
import com.zgtech.portal.bean.PageData;
import com.zgtech.portal.bean.Result;
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

import java.util.Map;


/**
 * @author zkc
 * @description
 * @Date 2023/2/1 16:48 星期三
 * @Version 1.0
 */
@RestController
@Api(tags = "案例管理")
public class PortalCaseManagementController {
    @Autowired
    private Cache<String, Object> cache;
    @Value("${path.url}")
    private String fileUrl;
    @Value("${path.filepath}")
    private String filePath;
    @Autowired
    private PortalCaseManagementService portalCaseManagementService;
    @ApiOperation(value = "文件上传")
    @PostMapping("uploadFile")
    public Result uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile) {
//        String  url = PictureUtils.upload(multipartFile,"/home/user/portal/file/","/portal/file/");
        String  url = PictureUtils.upload(multipartFile,filePath,fileUrl);
            return Result.success(ResultCodeEnum.FILE_UPLOAD_SUCCESS,url);
    }
    @ApiOperation(value = "新增案例")
    @PostMapping("saveCase")
    public Result saveCase(@RequestBody PortalCaseModel portalCaseModel) {
        portalCaseManagementService.savePortalCase(portalCaseModel);
        return Result.success(ResultCodeEnum.SAVE_SUCCESS);
    }

    @ApiOperation(value = "删除案例")
    @DeleteMapping("deleteCase")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="案例id",required = true,paramType="query",dataType="Long"),
    })
    public Result deletePortalCase(Long id) {
        portalCaseManagementService.deletePortalCase(id);
        return Result.success(ResultCodeEnum.DELETE_SUCCESS);
    }
    @ApiOperation(value = "查询案例列表")
    @GetMapping("queryCase")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNumber",value="当前页数",required = false,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required = false,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name="classifyId",value="分类id",required = false,paramType = "query",dataType = "Integer")
    })
    public Result<PageData> queryCase(@RequestParam("pageNumber")Integer pageNumber, @RequestParam("pageSize")Integer pageSize,Integer classifyId) {
        PageData pageData = portalCaseManagementService.queryPortalCase(pageNumber,pageSize,classifyId);
        return Result.success(ResultCodeEnum.QUERY_SUCCESS, pageData);
    }

    @ApiOperation(value = "查询全部案例")
    @GetMapping("queryAllCase")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classifyId",value="分类id",required = false,paramType = "query",dataType = "Integer")
    })
    public Result<Map<String,Object>> queryAllCase(Integer classifyId) {
        Map<String,Object> map = portalCaseManagementService.queryAllCase(classifyId);
        return Result.success(ResultCodeEnum.QUERY_SUCCESS, map);
    }
    @ApiOperation(value = "修改案例")
    @PutMapping("updatePortalCase")
    public Result updatePortalCase(@RequestBody PortalCaseModel portalCaseModel) {
        portalCaseManagementService.updatePortalCase(portalCaseModel);
        return Result.success(ResultCodeEnum.UPDATE_SUCCESS);
    }
}
