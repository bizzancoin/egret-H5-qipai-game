package com.idealighter.game.core.result;

import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.error.ErrorCodeMap;
import com.idealighter.utils.check.EmptyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
  private int code;
  private String msg;
  private Map<String, Object> map;

  public Result() {
    map = new HashMap<String, Object>();
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
   * 构造函数.
   * 
   * @param errorCode 错误码.
   */
  public Result(ErrorCode errorCode) {
    this.map = new HashMap<String, Object>();
    this.code = errorCode.getCode();
    this.msg = errorCode.getMessage();
  }

  /**
   * 构造函数.
   * 
   * @param returnCode 返回错误码.
   */
  public Result(int returnCode) {
    this.map = new HashMap<String, Object>();
    this.code = returnCode;
    this.msg = ErrorCodeMap.getMsgByCode(returnCode);
  }

  /**
   * 修改返回code.
   * 
   * @Title: changeReturnCode.
   * @author caijianbin
   * @date 2016年4月20日 下午7:57:32
   * @param returnCode 返回的错误码.
   */
  public void changeReturnCode(int returnCode) {
    this.code = returnCode;
    this.msg = ErrorCodeMap.getMsgByCode(returnCode);
  }

  /**
   * 修改错误码.
   * 
   * @Title: changeErrorCode.
   * @author caijianbin
   * @date 2016年4月20日 下午7:57:55
   * @param errorCode 错误码类型.
   */
  public void changeErrorCode(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.msg = errorCode.getMessage();
  }

  /**
   * 改变错误码.
   * 
   * @Title changeErrorCode.
   * @author houdongsheng
   * @date 2018年2月1日 下午3:12:28
   * @param errorCode 错误码
   * @param args 参数列表
   */
  public void changeErrorCode(ErrorCode errorCode, List<String> args) {
    this.code = errorCode.getCode();
    if (EmptyUtil.listIsNotEmpty(args)) {
      String tmpMsg = errorCode.getMessage();
      String format = tmpMsg.replaceAll("\\{[0-9]*\\}", "%s");
      this.msg = String.format(format, args);
    } else {
      this.msg = errorCode.getMessage();
    }
  }

  /**
   * 成功 不含消息.
   * 
   * @Title: OK
   * @author cjb
   * @date 2015年1月15日 下午4:47:53
   */
  public void ok() {
    this.code = ErrorCode.OK.getCode();
    clearMg();
  }

  /**
   * clearMg 清空消息.
   * 
   * @Title:
   * @author cjb
   * @date 2015年3月3日 上午9:55:11
   */
  private void clearMg() {
    this.msg = "";
  }

  /**
   * 成功 含消息.
   * 
   * @Title: successMg .
   * @author cjb
   * @date 2015年1月15日 下午5:11:39
   */
  public void successMg() {
    changeErrorCode(ErrorCode.OK);
  }

  /**
   * 失败.
   * 
   * @Title: INTERNAL_SERVER_ERROR .
   * @author cjb
   * @date 2015年3月4日 下午7:57:50
   */
  public void internalServerError() {
    this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
    clearMg();
  }

  /**
   * 失败 含消息.
   * 
   * @Title: failMg
   * @author cjb
   * @date 2015年3月4日 下午7:57:19
   */
  public void failMg() {
    changeErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
  }

  /**
   * 无权限 不含消息.
   * 
   * @Title: noPermission .
   * @author cjb
   * @date 2015年1月15日 下午5:16:17
   */
  public void noPermission() {
    this.code = ErrorCode.UNAUTHORIZED.getCode();
    clearMg();
  }

  /**
   * 无权限含消息.
   * 
   * @Title: noPermissionMg .
   * @author cjb
   * @date 2015年1月15日 下午5:16:42
   */
  public void noPermissionMg() {
    changeErrorCode(ErrorCode.UNAUTHORIZED);
  }

  /**
   * 参数错误.
   * 
   * @Title: illegal_ParameterMg .
   * @author cjb
   * @date 2015年3月3日 上午9:52:07
   */
  public void illegal_ParameterMg() {
    changeErrorCode(ErrorCode.BAD_REQUEST);
  }


  /**
   * 该项不存在 不含消息.
   * 
   * @Title: noFind .
   * @author cjb
   * @date 2015年2月6日 下午9:32:29
   */
  public void noFind() {
    this.code = ErrorCode.NOT_FOUND.getCode();
    clearMg();
  }

  /**
   * 该项不存在含消息.
   * 
   * @Title: noFindMg .
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
