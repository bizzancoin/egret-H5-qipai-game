package com.idealighter.game.server.service;

/**
 * IIServerService.
 * 
 * @Description 服务器服务
 * @author houdongsheng
 * @date 2018年4月14日 下午5:31:45 .
 */
public interface IServerService {
  // 获取服务编号
  public String getServerId();

  // 初始化
  public boolean initialize();

  // 启动服务
  public boolean startServer() throws Exception;

  // 关闭服务
  public boolean stopServer() throws Exception;

  // 释放
  public void release();

  // 获取状态
  public byte getState();

  // 服务是否运行
  public boolean isRunning();
}
