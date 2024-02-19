package tools;

import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.core.Message;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.ResolverUtil.AnnotatedWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoGeneratorTool {
  private static final Logger LOG = LoggerFactory.getLogger(MessageEncodeTool.class);

  // 扫描handler的包名
  private static final String MESSAGE_PACKAGE_NAME = "com.idealighter.game";

  private static final String REPLACE_PREFIX = "com.idealighter.game";

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


  private static final String OUTPUT_PATH = "output";
  private static final String PROTO_PATH = OUTPUT_PATH + File.separator + "proto";
  private static final String H5_PATH = OUTPUT_PATH + File.separator + "h5";
  private static final String PB_PATH = OUTPUT_PATH + File.separator + "pb";

  private static final String JSON_PATH = OUTPUT_PATH + File.separator + "json";

  /**
   * 生成encode代码.
   * 
   * @Title main.
   * @author houdongsheng
   * @date 2017年11月15日 下午7:44:14
   * @param args 参数
   * @throws IOException io异常.
   * @throws IllegalAccessException .
   * @throws InstantiationException .
   */
  public static void main(String[] args)
      throws IOException, InstantiationException, IllegalAccessException {

    File protoFolder = new File(PROTO_PATH);

    if (!protoFolder.exists()) {
      protoFolder.mkdirs();
    }

    File pbFolder = new File(PB_PATH);

    if (!pbFolder.exists()) {
      pbFolder.mkdirs();
    }

    File jsonMapFile = new File(JSON_PATH);

    if (!jsonMapFile.exists()) {
      jsonMapFile.mkdirs();
    }

    generateEncode(RES_MESSAGES, ResMessage.class, "resMsg");

    generateEncode(REQ_MESSAGES, ReqMessage.class, "reqMsg");
  }

  private static void execCmd(String[] cmd) {
    Process p;

    try {
      // 执行命令
      p = Runtime.getRuntime().exec(cmd);

      // 取得命令结果的输出流
      InputStream fis = p.getErrorStream();
      // 用一个读输出流类去读
      InputStreamReader isr = new InputStreamReader(fis);
      // 用缓冲器读行
      BufferedReader br = new BufferedReader(isr);
      String line = null;
      // 直到读完为止
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void generateEncode(Map<Integer, Class<? extends Message>> messages,
      Class<? extends Message> type, String luaFile)
      throws IOException, InstantiationException, IllegalAccessException {

    Set<Integer> keys = messages.keySet();

    File lua = new File(OUTPUT_PATH + File.separator + luaFile + ".lua");
    File json = new File(OUTPUT_PATH + File.separator + luaFile + ".json");
    File jsonMapFile = new File(JSON_PATH + File.separator + luaFile + ".json");
    BufferedWriter luaWriter = new BufferedWriter(new FileWriter(lua));
    BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(json));
    BufferedWriter jsonMapWriter = new BufferedWriter(new FileWriter(jsonMapFile));
    luaWriter.write("local " + luaFile + " = { \n");
    jsonWriter.write("local " + luaFile + " = { \n");
    jsonMapWriter.write("{ \n");
    for (Iterator<Integer> iterator = keys.iterator(); iterator.hasNext();) {
      Integer key = iterator.next();
      Class<? extends Message> value = messages.get(key);
      if (type.isAssignableFrom(value)) {

        Message message = value.newInstance();

        if (message == null || message.getId() == 0) {
          continue;
        }

        String code = ProtobufIDLGenerator.getIDL(value);

        code = "syntax = \"proto2\"; \n" + code;

        code = code.replace("$$ByJProtobuf", "");

        String packageName = value.getPackage().getName();
        String fullPath = "";
        if (packageName != null && !packageName.isEmpty()) {
          packageName = packageName.replaceFirst(REPLACE_PREFIX + ".", "");
          fullPath += packageName.replace('.', '_');
          packageName = packageName.replace('.', File.separatorChar);
          File subFolder = new File(PROTO_PATH + File.separator + packageName);
          if (!subFolder.exists()) {
            subFolder.mkdirs();
          }

          File subFolder2 = new File(PB_PATH + File.separator + packageName);
          if (!subFolder2.exists()) {
            subFolder2.mkdirs();
          }

          File subFolder3 = new File(H5_PATH + File.separator + packageName);
          if (!subFolder3.exists()) {
            subFolder3.mkdirs();
          }
        }

        String fileName = PROTO_PATH + File.separator + packageName + File.separator
            + value.getSimpleName() + ".proto";
        System.out.println(fileName);
        File protoFile = new File(fileName);
        BufferedWriter out = new BufferedWriter(new FileWriter(protoFile));
        out.write(code);
        out.flush();
        out.close();

        String fullNameJsonfileName = H5_PATH + File.separator + packageName + File.separator
            + fullPath + "_" + value.getSimpleName() + ".proto";
        System.out.println(fullNameJsonfileName);
        File fullNameProtoFile = new File(fullNameJsonfileName);
        BufferedWriter fullNameOut = new BufferedWriter(new FileWriter(fullNameProtoFile));
        fullNameOut.write(code);
        fullNameOut.flush();
        fullNameOut.close();

        String pbFilename =
            PB_PATH + File.separator + packageName + File.separator + value.getSimpleName() + ".pb";

        String[] cmd = { "protoc.exe", "--descriptor_set_out", pbFilename, fileName };
        execCmd(cmd);

        pbFilename = pbFilename.replace(OUTPUT_PATH, "");

        pbFilename = pbFilename.replace('\\', '/');

        System.out.println(pbFilename);

        luaWriter.write("\t[" + message.getId() + "] = {  \n");
        luaWriter.write("\t\t file = \"" + pbFilename + "\",\n");
        luaWriter.write("\t\t name = \"" + value.getName() + "\"\n");
        luaWriter.write("\t},\n");

        jsonWriter.write("\t\"" + message.getId() + "\":{  \n");
        jsonWriter.write("\t\t \"file\":\"" + fileName.replace(PROTO_PATH, "")
            .replaceFirst("\\\\", "").replace('\\', '_').replace('.', '_') + "\",\n");
        jsonWriter.write("\t\t \"name\":\"" + value.getName() + "\"\n");
        jsonWriter.write("\t},\n");

        jsonMapWriter.write("\t\"" + message.getId() + "\":{  \n");
        jsonMapWriter.write("\t\t \"file\":\""
            + code.replace("\n", "").replace("\\", "").replace('\"', '\'') + "\",\n");
        jsonMapWriter.write("\t\t \"name\":\"" + value.getName() + "\"\n");
        jsonMapWriter.write("\t},\n");
      }
    }

    luaWriter.write("} \n");
    luaWriter.write("return " + luaFile);
    luaWriter.flush();
    luaWriter.close();

    jsonWriter.write("} \n");
    jsonWriter.write("return " + luaFile);
    jsonWriter.flush();
    jsonWriter.close();

    jsonMapWriter.write("} \n");
    jsonMapWriter.flush();
    jsonMapWriter.close();
  }
}
