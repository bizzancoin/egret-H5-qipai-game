import com.idealighter.game.ApplicationContext;
import com.idealighter.game.server.GameServer;
import com.idealighter.game.web.core.WebServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * gameserver启动器.
 *
 */
public class Launcher {

  private static final Logger LOG = LoggerFactory.getLogger(Launcher.class);

  private static volatile boolean running = true;

  /**
   * 启动器.
   * 
   * @param args 参数 .
   */
  public static void main(String[] args) {
    try {
      ApplicationContext app = ApplicationContext.createInstance();
      final GameServer gameServer = ApplicationContext.getBean(GameServer.class);
      gameServer.start();

      final WebServer webServer = new WebServer();
      Integer port = ApplicationContext.getProperty(Integer.class, "web.httpPort");
      // 获取所有托管类，提供给resteasy扫描
      webServer.init("webServer", port, app.allAdminWebMembers());
      webServer.start();


      // final WebServer thirdServer = new WebServer();
      // port = ApplicationContext.getProperty(Integer.class, "server.thirdPort");
      // // 获取所有托管类，提供给resteasy扫描
      // thirdServer.init("thirdServer", port, app.allThirdWebMembers());
      // thirdServer.start();



      // 注册关闭Hook，所有的关闭操作都注册到onShutDown中
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        LOG.info("服务器关闭中...");
        webServer.shutdown();
        // thirdServer.shutdown();
        gameServer.shutdown();
        LOG.info("全部 服务器关闭成功");
        synchronized (Launcher.class) {
          LOG.info("ShutdownHook 退出");
          running = false;
          Launcher.class.notify();
        }

      }));

    } catch (Exception e) {
      LOG.error("服务器启动异常", e);
      System.exit(1);
    }

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

    // testExist.start();

    synchronized (Launcher.class) {
      while (running) {
        try {
          LOG.info("主进程 等待");
          Launcher.class.wait();
        } catch (Throwable e) {
          LOG.error("服务器的等待异常", e);
        }
      }
    }
    LOG.info("主进程 退出");
  }
}
