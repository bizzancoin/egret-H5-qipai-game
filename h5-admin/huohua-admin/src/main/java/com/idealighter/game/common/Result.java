package com.idealighter.game.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Result {
  private int code;
  private String msg;
  private Map<String, Object> map;

  public Result() {
    map = new HashMap<String, Object>();
    code = ErrorCode.OK.getCode();
  }

  /**
   * 构造函数.
   * 
   * @param flag 成功还是失败
   */
  public Result(boolean flag) {
    map = new HashMap<String, Object>();
    if (flag) {
      code = ErrorCode.OK.getCode();
      msg = "执行成功";
    } else {
      code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
      msg = ErrorCode.INTERNAL_SERVER_ERROR.getMessage();
    }
  }

  /**
   * 构造函数.
   * 
   * @param flag 成功还是失败
   * @param withMessage 提示信息
   */
  public Result(boolean flag, boolean withMessage) {
    map = new HashMap<String, Object>();
    if (flag && withMessage) {
      code = ErrorCode.OK.getCode();
      msg = ErrorCode.OK.getMessage();
    } else {
      code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
      msg = ErrorCode.INTERNAL_SERVER_ERROR.getMessage();
    }
  }

  /** 
   * 错误码构造函数.
   * @param errorCode 错误码.
   */
  public Result(ErrorCode errorCode) {
    this.map = new HashMap<String, Object>();
    this.code = errorCode.getCode();
    this.msg = errorCode.getMessage();
  }

  /**
   * 错误码(int)构造函数.
   * @param returnCode 返回错误码 int.
   */
  public Result(int returnCode) {
    this.map = new HashMap<String, Object>();
    this.code = returnCode;
    this.msg = ErrorCodeMap.getMsgByCode(returnCode);
  }

  /**
   * changeReturnCode.
   *  修改返回code.
   * @author caijianbin
   * @date 2016年4月20日 下午7:57:32
   * @param returnCode 返回的错误码.
   */
  public void changeReturnCode(int returnCode) {
    this.code = returnCode;
    this.msg = ErrorCodeMap.getMsgByCode(returnCode);
  }

  /**
   * changeErrorCode.
   *  修改错误码.
   * @author caijianbin
   * @date 2016年4月20日 下午7:57:55
   * @param errorCode 错误码类型.
   */
  public void changeErrorCode(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.msg = errorCode.getMessage();
  }

  /**
   * success .
   *  成功 不含消息.
   * @author cjb
   * @date 2015年1月15日 下午4:47:53
   */
  public void success() {
    this.code = ErrorCode.OK.getCode();
    clearMg();
  }

  /**
   * clearMg .
   *  清空消息.
   * @author cjb
   * @date 2015年3月3日 上午9:55:11
   */
  private void clearMg() {
    this.msg = "";
  }

  /**
   * successMg .
   *  成功 含消息.
   * @author cjb
   * @date 2015年1月15日 下午5:11:39
   */
  public void successMg() {
    changeErrorCode(ErrorCode.OK);
  }

  /**
   * fail .
   *  失败.
   * @author cjb
   * @date 2015年3月4日 下午7:57:50
   */
  public void fail() {
    this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
    clearMg();
  }

  /**
   * failMg .
   * @Description 失败 含消息.
   * @author cjb
   * @date 2015年3月4日 下午7:57:19
   */
  public void failMg() {
    changeErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
  }

  /**
   * exception .
   *  异常不含 消息.
   * @author cjb
   * @date 2015年1月15日 下午5:12:18
   */
  public void exception() {
    this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
    clearMg();
  }

  /**
   * exceptionMg .
   *  异常含消息.
   * @author cjb
   * @date 2015年1月15日 下午5:12:35
   */
  public void exceptionMg() {
    changeErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
  }

  /**
   * noPermission  .
   *  无权限 不含消息.
   * @author cjb
   * @date 2015年1月15日 下午5:16:17
   */
  public void noPermission() {
    this.code = ErrorCode.UNAUTHORIZED.getCode();
    clearMg();
  }

  /**
   * noPermissionMg .
   *  无权限含消息.
   * @author cjb
   * @date 2015年1月15日 下午5:16:42
   */
  public void noPermissionMg() {
    changeErrorCode(ErrorCode.UNAUTHORIZED);
  }

  /**
   * illegal_ParameterMg .
   *  参数错误.
   * @author cjb
   * @date 2015年3月3日 上午9:52:07
   */
  public void illegal_ParameterMg() {
    changeErrorCode(ErrorCode.BAD_REQUEST);
  }


  /**
   * noFind .
   *  该项不存在 不含消息.
   * @author cjb
   * @date 2015年2月6日 下午9:32:29
   */
  public void noFind() {
    this.code = ErrorCode.NOT_FOUND.getCode();
    clearMg();
  }

  /**
   * noFindMg .
   *  该项不存在含消息.
   * @author cjb
   * @date 2015年2月6日 下午9:30:27
   */
  public void noFindMg() {
    changeErrorCode(ErrorCode.NOT_FOUND);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }



  public String getMsg() {
    return msg;
  }


  public void setMsg(String msg) {
    this.msg = msg;
  }


  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }
}
