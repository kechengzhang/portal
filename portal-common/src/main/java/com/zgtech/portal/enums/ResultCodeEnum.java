package com.zgtech.portal.enums;

/**
 * @author zkc
 * @date  2021/05/05
 * @version  1.0
 *
 *
 */
public enum ResultCodeEnum {
    /**
     *
     * 错误产生来源分为 A/B/C，
     * A 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付超时等问题；
     * B 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题；
     * C 表示错误来源于第三方服务，比如 CDN 服务出错，消息投递超时等问题；四位数字编号从 0001 到 9999，大类之间的步长间距预留 100
     *
     */
    SUCCESS("00000", "success"),
    SAVE_SUCCESS("00000", "新增成功"),
    DELETE_SUCCESS("00000", "删除成功"),
    UPDATE_SUCCESS("00000", "修改成功"),
    QUERY_SUCCESS("00000", "查询成功"),
    FILE_UPLOAD_SUCCESS("00000", "文件上传成功"),
    USER_LOGIN_SUCCESS("00000", "登录成功"),
    ERROR("B0001", "未知异常"),
    SAVE_ERROR("B0002", "新增失败"),
    DELETE_ERROR("B0005", "删除失败，该分类已被案例使用"),
    UPDATE_ERROR("B0003", "修改失败"),
    SQL_EXCEPTION("B0004", "SqlException:SQL异常");
    private String msg;
    private String code;

        ResultCodeEnum(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public String getMessage() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public static String getValue(String index) {
        ResultCodeEnum[] values = values();
        for (ResultCodeEnum value : values) {
            if (value.getCode().equals(index)) {
                return value.msg;
            }
        }
        return null;
    }
}
