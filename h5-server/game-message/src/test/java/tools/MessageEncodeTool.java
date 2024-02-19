package tools;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.core.Message;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.ResolverUtil.AnnotatedWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEncodeTool {
  private static final Logger LOG = LoggerFactory.getLogger(MessageEncodeTool.class);

  // 扫描handler的包名
  private static final String MESSAGE_PACKAGE_NAME = "com.idealighter.game";

  private static final String OUTPUT_PATH = "output";

  private static final Map<Integer, Class<? extends Message>> REQ_MESSAGES = new HashMap<>();

  private static final Map<Integer, Class<? extends Message>> RES_MESSAGES = new HashMap<>();

  static {
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
   * 生成encode代码.
   * 
   * @Title main.
   * 
   * @author houdongsheng
   * @date 2017年11月15日 下午7:44:14
   * @param args 参数
   * @throws IOException io异常.
   */
  public static void main(String[] args) throws IOException {
    generateEncode(RES_MESSAGES, ResMessage.class, "res.txt");

    generateEncode(REQ_MESSAGES, ReqMessage.class, "req.txt");
  }

  private static void generateEncode(Map<Integer, Class<? extends Message>> messages,
      Class<? extends Message> type, String file) throws IOException {
    Set<Integer> keys = messages.keySet();
    StringBuffer stringBuffer = new StringBuffer("");
    stringBuffer.append("public static void encode" + type.getSimpleName()
        + "(CodedOutputStream output, Class<? extends Message> cls, " + "Message message)" + "\n");
    stringBuffer.append("throws IOException {" + "\n");
    boolean first = true;
    for (Iterator<Integer> iterator = keys.iterator(); iterator.hasNext();) {
      Integer key = iterator.next();
      Class<? extends Message> value = messages.get(key);
      if (type.isAssignableFrom(value)) {
        if (first) {
          stringBuffer.append("if (cls.equals(" + value.getName() + ".class)) {" + "\n");
          stringBuffer.append(value.getName() + " msg = (" + value.getName() + ") message;" + "\n");
          stringBuffer.append("Codec<" + value.getName() + "> codec = ProtobufProxy.create("
              + value.getName() + ".class);" + "\n");
          stringBuffer.append("codec.writeTo(msg, output);" + "\n");
          first = false;
        } else {
          stringBuffer.append("} else if (cls.equals(" + value.getName() + ".class)) {" + "\n");
          stringBuffer.append(value.getName() + " msg = (" + value.getName() + ") message;" + "\n");
          stringBuffer.append("Codec<" + value.getName() + "> codec = ProtobufProxy.create("
              + value.getName() + ".class);" + "\n");
          stringBuffer.append("codec.writeTo(msg, output);" + "\n");
        }
        if (!iterator.hasNext()) {
          stringBuffer.append("}" + "\n");
        }
      }
    }
    stringBuffer.append("}" + "\n");

    File lua = new File(OUTPUT_PATH + File.separator + file);

    // 创建文件夹
    if (!lua.getParentFile().exists()) {
      lua.getParentFile().mkdirs();
    }

    BufferedWriter luaWriter = new BufferedWriter(new FileWriter(lua));
    luaWriter.write(stringBuffer.toString());

    luaWriter.flush();
    luaWriter.close();
  }
}
