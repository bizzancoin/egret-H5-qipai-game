package com.idealighter.game.message;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

import com.google.protobuf.CodedOutputStream;

import com.idealighter.game.common.message.ResDefaultMsg;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.core.Message;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.ResolverUtil.AnnotatedWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息工厂 .
 * 
 * 
 * @date 2015年7月25日 上午1:12:35
 *
 */
public class MessageFactory {

  private static final Logger LOG = LoggerFactory.getLogger(MessageFactory.class);

  // 扫描handler的包名
  private static final String MESSAGE_PACKAGE_NAME = "com.idealighter.game";

  private MessageFactory() {}

  private static final Map<Integer, Class<? extends Message>> REQ_MESSAGES = new HashMap<>();

  private static final Map<Integer, Class<? extends Message>> RES_MESSAGES = new HashMap<>();



  /**
   * msg init.
   */
  public static void init() {
    ResolverUtil<Message> reqHamdlerResolver =
        new ResolverUtil<Message>().find(new AnnotatedWith(ReqMsg.class), MESSAGE_PACKAGE_NAME);

    ResolverUtil<Message> resHamdlerResolver =
        new ResolverUtil<Message>().find(new AnnotatedWith(ResMsg.class), MESSAGE_PACKAGE_NAME);


    try {
      Set<Class<? extends Message>> msgClasses = reqHamdlerResolver.getClasses();
      for (Class<? extends Message> msgClass : msgClasses) {
        ReqMsg reqMsg = msgClass.getAnnotation(ReqMsg.class);

        if (REQ_MESSAGES.containsKey(reqMsg.value())) {
          LOG.error("消息[{}]的id和消息[{}]重复,请检查配置", msgClass.getName(),
              REQ_MESSAGES.get(reqMsg.value()).getName());
          System.exit(1);
        }
        REQ_MESSAGES.put(reqMsg.value(), msgClass);
      }
    } catch (Exception e) {
      LOG.error("扫描" + MESSAGE_PACKAGE_NAME + "下的Req Message异常", e);
      System.exit(1);
    }


    try {
      Set<Class<? extends Message>> msgClasses = resHamdlerResolver.getClasses();
      for (Class<? extends Message> msgClass : msgClasses) {
        ResMsg resMsg = msgClass.getAnnotation(ResMsg.class);

        if (RES_MESSAGES.containsKey(resMsg.value())) {
          LOG.error("消息[{}]的id和消息[{}]重复,请检查配置", msgClass.getName(),
              RES_MESSAGES.get(resMsg.value()).getName());
          System.exit(1);
        }
        RES_MESSAGES.put(resMsg.value(), msgClass);
      }
    } catch (Exception e) {
      LOG.error("扫描" + MESSAGE_PACKAGE_NAME + "下的Res Message异常", e);
      System.exit(1);
    }
  }

  /**
   * new一个消息实例.
   * 
   * @param id . id.
   * @return . 消息.
   * @throws IllegalAccessException 非法请求.
   * @throws InstantiationException 非法实例.
   */
  public static Message newReqMsg(int id) throws InstantiationException, IllegalAccessException {
    if (!REQ_MESSAGES.containsKey(id)) {
      LOG.error("找不到消息 {}", id);
      throw new RuntimeException("找不到消息:" + id);
    } else {
      return REQ_MESSAGES.get(id).newInstance();
    }
  }

  /**
   * new一个消息实例.
   * 
   * @param id . id.
   * @return . 消息.
   * @throws IllegalAccessException 非法请求.
   * @throws InstantiationException 非法实例.
   */
  public static Message newResMsg(int id) throws InstantiationException, IllegalAccessException {
    if (!RES_MESSAGES.containsKey(id)) {
      return new ResDefaultMsg();
    } else {
      return RES_MESSAGES.get(id).newInstance();
    }
  }

  /**
   * 消息Id 获取消息.
   * 
   * @param id . id.
   * @return . 消息.
   * @throws InstantiationException 非法实例.
   * @throws IllegalAccessException 非法请求.
   */
  public static Class<? extends Message> getReqMsgClass(int id)
      throws InstantiationException, IllegalAccessException {
    if (!REQ_MESSAGES.containsKey(id)) {
      LOG.error("找不到消息 {}", id);
      throw new RuntimeException("找不到消息:" + id);
    } else {
      return REQ_MESSAGES.get(id);
    }
  }

  /**
   * 消息Id 获取消息.
   * 
   * @param id . id.
   * @return . 消息.
   * @throws InstantiationException 非法实例.
   * @throws IllegalAccessException 非法请求.
   */
  public static Class<? extends Message> getResMsgClass(int id)
      throws InstantiationException, IllegalAccessException {
    if (!RES_MESSAGES.containsKey(id)) {
      return ResDefaultMsg.class;
    } else {
      return RES_MESSAGES.get(id);
    }
  }

  /**
   * 对ResMessage对象转Byte[].
   * 
   * @param output output.
   * @param message 消息.
   * @throws IOException io异常.
   */
  @SuppressWarnings("unchecked")
  public static <T extends ResMessage> void encodeResMessage(CodedOutputStream output, T message)
      throws IOException {
    Codec<T> code = (Codec<T>) ProtobufProxy.create(message.getClass());
    // Codec<T> code = (Codec<T>) resCodecMap.get(message.getClass());
    code.writeTo(message, output);
  }

  /**
   * 对ReqMessage对象转Byte[].
   * 
   * @date 2018年6月14日 下午5:14:39
   * @param output output
   * @param message 消息.
   * @throws IOException io异常
   */
  @SuppressWarnings("unchecked")
  public static <T extends ReqMessage> void encodeReqMessage(CodedOutputStream output, T message)
      throws IOException {
    Codec<T> code = (Codec<T>) ProtobufProxy.create(message.getClass());
    // Codec<T> code = (Codec<T>) reqCodecMap.get(message.getClass());
    code.writeTo(message, output);
  }

}
