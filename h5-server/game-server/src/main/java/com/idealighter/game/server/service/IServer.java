package com.idealighter.game.server.service;

// 服务器统一接口
public interface IServer {
  // 服务器 编号
  public String getId();

  // 启动服务器
  public void startup() throws Exception;

  // 关闭服务器
  public void shutdown() throws Exception;
}
