
import com.idealighter.game.robot.context.ApplicationContext;
import com.idealighter.game.robot.scheduler.PlayerScheduler;
import com.idealighter.game.web.core.WebServer;

/**
 * 机器人启动器.
 *
 */
public class Launcher {

  /**
   * 机器人启动器.
   * 
   * @param args 参数.
   * @throws InterruptedException 中断异常.
   */
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext instance = ApplicationContext.createInstance();
    ApplicationContext.getBean(PlayerScheduler.class).startUp();
    // instance.getBean(RobotHttpServer.class).startUp();

    final WebServer webServer = ApplicationContext.getBean(WebServer.class);
    Integer port = ApplicationContext.getProperty(Integer.class, "robot.httpPort");
    webServer.init("robotWebServer", port, instance.allMembers()); // 获取所有托管类，提供给resteasy扫描
    webServer.start();

    // Thread testExist = new Thread(new Runnable() {
    //
    // @Override
    // public void run() {
    // System.out.println("==============eclipse debug==================");
    // System.out.println("press ENTER to call System.exit(0) ");
    // System.out.println("==============eclipse debug==================");
    // try {
    // System.out.println(System.in.read());
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // System.exit(0);
    //
    // }
    // });
    //
    // testExist.start();

  }
}
