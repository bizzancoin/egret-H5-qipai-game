
package com.idealighter.game.robot.context;

import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import com.idealighter.game.dictionary.DictionaryModule;
import com.idealighter.game.robot.datadb.DataDdModule;

import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 机器人应用程序上下文信息.
 *
 */
public class ApplicationContext {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationContext.class);

  private static final String PACKAGE_PATH = "com.idealighter";
  private static final String ROBOT_FILE = "config/robot.properties";
  private static final String SERVER_FILE = "config/server.properties";

  /**
   * 单例，最好为volatile或AtomicReference,但是ApplicationContext调用很频繁，不加性能会更好，
   * 由于ApplicationContext在main中初始化一次后不再改变，instance可视化问题几乎不存在 .
   */
  private static ApplicationContext instance;

  /** guice的injector . */
  private final Injector injector;


  private ApplicationContext() {
    this.injector = Guice.createInjector(Stage.PRODUCTION, new DictionaryModule(),
        new DataDdModule(), new AbstractModule() {

          @Override
          protected void configure() {
            Properties props = new Properties();
            try {
              InputStream is = ClassLoader.getSystemResourceAsStream(ROBOT_FILE);
              props.load(is);
              is.close();
            } catch (Exception e) {
              LOG.error("加载文件[{}]失败，请检查后再启动", ROBOT_FILE);
              System.exit(1);
            }
            try {
              InputStream is = ClassLoader.getSystemResourceAsStream(SERVER_FILE);
              props.load(is);
              is.close();
            } catch (Exception e) {
              LOG.error("加载文件[{}]失败，请检查后再启动", SERVER_FILE);
              System.exit(1);
            }
            Names.bindProperties(binder(), props);

            Set<Class<? extends Object>> singletonTypes = new ResolverUtil<Object>()
                .find(new ResolverUtil.AnnotatedWith(Singleton.class), PACKAGE_PATH).getClasses();
            for (Class<? extends Object> singletonType : singletonTypes) {
              binder().bind(singletonType);
            }
          }

        });
  }

  /**
   * 创建实例,同步方法创建.
   * 
   * @return .
   */
  public static synchronized ApplicationContext createInstance() {

    if (instance == null) {
      LOG.info("创建ApplicationContext...");
      instance = new ApplicationContext();
      LOG.info("创建ApplicationContext成功");
    } else {
      LOG.warn("ApplicationContext实例已经创建，请勿重复创建");
    }

    return instance;
  }

  /**
   * 获取实例 .
   * 
   * @return . .
   */
  public static ApplicationContext getInstance() {

    if (instance == null) {
      createInstance();
    }

    return instance;
  }

  /**
   * 获取bean实例.
   * 
   * @param type . 类型.
   * @return . 类型实例.
   */
  public <T> T getBeanFromInjector(Class<T> type) {
    return injector.getInstance(type);
  }

  /**
   * 获取bean实例.
   * 
   * @param key . key.
   * @return . 类型实例.
   */
  public <T> T getBeanFromInjector(Key<T> key) {

    return injector.getInstance(key);
  }

  public static <T> T getBean(Class<T> type) {
    return ApplicationContext.getInstance().getBeanFromInjector(type);
  }

  public static <T> T getBean(Key<T> type) {
    return ApplicationContext.getInstance().getBeanFromInjector(type);
  }

  public static <T> T getProperty(Class<T> clz, String propertyName) {
    return ApplicationContext.getInstance().injector
        .getInstance(Key.get(clz, Names.named(propertyName)));
  }

  /**
   * 手动注入 .
   * 
   * @param instance . .
   */
  public void injectMembers(Object instance) {
    injector.injectMembers(instance);
  }

  /**
   * 所有对象.
   * 
   * @return 对象集合.
   */
  public Collection<Binding<?>> allMembers() {
    if (injector != null) {
      return injector.getBindings().values();
    }
    return null;
  }


  public Collection<Binding<Provider>> allProviders() {
    if (injector != null) {
      return injector.findBindingsByType(TypeLiteral.get(Provider.class));
    }
    return null;
  }

  public Collection<Binding<Path>> allPaths() {
    if (injector != null) {
      return injector.findBindingsByType(TypeLiteral.get(Path.class));
    }
    return null;
  }

}
