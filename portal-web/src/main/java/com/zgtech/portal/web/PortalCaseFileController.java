package com.zgtech.portal.web;

import com.github.benmanes.caffeine.cache.Cache;
import com.zgtech.portal.api.PortalCaseFileService;
import com.zgtech.portal.api.model.PortalCaseFileModel;
import com.zgtech.portal.api.model.PortalCaseWaterMarkModel;
import com.zgtech.portal.bean.Result;
import com.zgtech.portal.dao.entity.PortalCaseWaterMarkDO;
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

/**
 * @author zkc
 * @description
 * @Date 2023/2/6 13:45 星期一
 * @Version 1.0
 */
@RestController
@Api(tags = "案例文件管理")
public class PortalCaseFileController {
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
    private PortalCaseFileService portalCaseFileService;

    @ApiOperation(value = "水印开启关闭")
    @PostMapping("updateWaterMark")
    public Result updateWaterMark(@RequestBody PortalCaseFileModel portalCasePictureModel) {
        if (portalCasePictureModel.getStatus() == 1) {
            portalCaseFileService.watermarkImageUploading(portalCasePictureModel);
        }
        PortalCaseWaterMarkModel portalCaseWaterMarkModel =new PortalCaseWaterMarkModel();
        portalCaseWaterMarkModel.setId(portalCasePictureModel.getId());
        portalCaseWaterMarkModel.setStatus(portalCasePictureModel.getStatus());
        //修改水印开启状态
        portalCaseFileService.updatePortalCaseFileStatus(portalCaseWaterMarkModel);
        return Result.success(ResultCodeEnum.FILE_UPLOAD_SUCCESS);
    }
    @ApiOperation(value = "水印图片上传")
    @PostMapping("uploadCasePicture")
    public Result uploadCasePicture(@RequestParam("multipartFile") MultipartFile multipartFile) {
        String url = PictureUtils.upload(multipartFile, waterMarkIconPath, fileUrl);
        PortalCaseWaterMarkModel portalCaseWaterMarkModel = new PortalCaseWaterMarkModel();
        portalCaseWaterMarkModel.setCaseWaterMarkUrl(url);
        cache.put("iconPath",waterMarkIconPath+url.split("file")[1]);
        portalCaseFileService.updateCaseWaterMark(portalCaseWaterMarkModel);
        return Result.success(ResultCodeEnum.FILE_UPLOAD_SUCCESS, url);
    }

    @ApiOperation(value = "水印图片查询")
    @GetMapping("queryCaseWaterMarkPicture")
    public Result queryCaseWaterMarkPicture() {
        PortalCaseWaterMarkDO portalCaseWaterMarkModel = portalCaseFileService.queryCaseWaterMarkPicture();
        return Result.success(ResultCodeEnum.QUERY_SUCCESS, portalCaseWaterMarkModel);
    }

    @ApiOperation(value = "水印图片删除")
    @Deprecated
    @DeleteMapping("deleteCaseWaterMarkPicture")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "水印图片id", required = false, paramType = "query", dataType = "Integer")
    })
    public Result deleteCaseWaterMarkPicture(@RequestParam("id") Long id) {
        portalCaseFileService.deleteCaseWaterMarkPicture(id);
        return Result.success(ResultCodeEnum.DELETE_SUCCESS);
    }

    @ApiOperation(value = "水印图片修改")
    @PutMapping("updateWaterMarkPicture")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "waterMarkUrl", value = "水印图片url", required = false, paramType = "query", dataType = "String")
    })
    public Result updateWaterMarkPicture(@RequestParam("waterMarkUrl") String waterMarkUrl) {
        portalCaseFileService.updateWaterMarkPicture(waterMarkUrl);
        return Result.success(ResultCodeEnum.UPDATE_SUCCESS);
    }

    public static void main(String[] args) {
        Thread thread =new Thread();
        thread.start();
    }
}
