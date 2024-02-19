package tools;

import com.idealighter.game.core.error.ErrorCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorCodeGeneratorTool {
  private static final String OUTPUT_PATH = "output";

  /**
   * main.
   * 
   * @param args 参数.
   * @throws IOException io异常.
   */
  public static void main(String[] args) throws IOException {

    StringBuilder builder = new StringBuilder();
    builder.append("MessageConfig = {\n");

    for (ErrorCode errorCode : ErrorCode.values()) {
      builder.append("\t");
      builder.append("[" + errorCode.getCode() + "] = { id = " + errorCode.getCode() + ", name = \""
          + errorCode.getMessage() + "\"},\n");
    }

    builder.append("}");

    File lua = new File(OUTPUT_PATH + File.separator + "errorcode.lua");

    BufferedWriter out = new BufferedWriter(new FileWriter(lua));
    out.write(builder.toString());
    out.flush();
    out.close();
  }
}
