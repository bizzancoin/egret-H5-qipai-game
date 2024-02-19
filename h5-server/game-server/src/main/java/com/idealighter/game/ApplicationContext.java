package com.idealighter.game;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Stage;
import com.google.inject.name.Names;

import com.idealighter.game.admin.AdminModule;
import com.idealighter.game.core.CoreModule;
import com.idealighter.game.dictionary.DictionaryModule;
import com.idealighter.game.web.core.WebModule;

import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用程序上下文信息 .
 * 
 * @date 2015年7月27日 下午5:23:30
 *
 */
public class ApplicationContext {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationContext.class);

  private static final String SERVER_FILE = "config/server.properties";

  /**
   * 单例，最好为volatile或AtomicReference,但是ApplicationContext调用很频繁，不加性能会更好， .
   * 由于ApplicationContext在main中初始化一次后不再改变，instance可视化问题几乎不存在.
   */
  private static ApplicationContext instance;

  /** guice的injector. */
  private final Injector injector;

  private final Injector adminInjector;

  private ApplicationContext() {
    this.injector = Guice.createInjector(Stage.PRODUCTION, new DictionaryModule(), new CoreModule(),
        new AbstractModule() {

          @Override
          protected void configure() {
            Properties props = new Properties();
            try {
              InputStream is = ClassLoader.getSystemResourceAsStream(SERVER_FILE);
              props.load(is);
              is.close();
            } catch (Exception e) {
              LOG.error("加载文件[{}]失败，请检查后再启动", SERVER_FILE);
              System.exit(1);
            }
            Binder binder = binder();
            Names.bindProperties(binder, props);
          }

        });

    this.adminInjector = injector.createChildInjector(new AdminModule(), new WebModule());

  }

  /**
   * 创建实例,同步方法创建.
   * 
   * @return app上下文.
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
   * 获取实例.
   * 
   * @return 实例.
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
   * @return 类型实例.
   */
  public <T> T getBeanFromInjector(Class<T> type) {
    return injector.getInstance(type);
  }

  /**
   * 获取bean实例.
   * 
   * @param key . key.
   * @return 类型实例.
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
   * 手动注入.
   * 
   * @param instance . 实例.
   */
  public void injectMembers(Object instance) {
    injector.injectMembers(instance);
  }

  /**
   * 所有对象.
   * 
   * @return bangding 列表.
   */
  public Collection<Binding<?>> allMembers() {
    if (injector != null) {
      return injector.getBindings().values();
    }
    return null;
  }

  /**
   * 所有管理 bindings .
   *
   * @author abin
   * @date 2018年6月14日 下午7:25:02
   * @return bindings .
   */
  public Collection<Binding<?>> allAdminWebMembers() {
    if (adminInjector != null) {
      return adminInjector.getBindings().values();
    }
    return null;
  }




}
