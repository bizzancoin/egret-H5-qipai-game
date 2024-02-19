package com.idealighter.game.core;

import com.google.inject.PrivateModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import com.idealighter.game.core.annotation.DataDb;
import com.idealighter.game.core.datasource.DruidDataSourceProvider;
import com.idealighter.game.core.service.player.manager.IPlayerMgr;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.IPlayerService;
import com.idealighter.game.core.service.player.service.PlayerService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.configuration.settings.ConfigurationSetting;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import org.mybatis.guice.session.SqlSessionManagerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 游戏数据db模块 .
 * 
 * @date 2015年7月29日 上午11:17:17
 *
 */
public class CoreModule extends PrivateModule {

  private static final Logger LOG = LoggerFactory.getLogger(CoreModule.class);

  /** 游戏数据数据库jdbc配置文件路径. */
  private static final String DATADB_JDBC_PATH = "config/jdbc/datadb.properties";
  private static final String ENVIRONMENT_ID = "datadb_product";

  /** 字段数据库mapper路径 . */
  private static final String MAPPER_PATH = "com.idealighter.game.core.dao.mapper";

  private static final String PACKAGE_PATH = "com.idealighter.game.core";

  private Set<Class<?>> mapperClasses;

  /** 游戏数据数据库jdbc配置文件配置信息. */
  private static final Properties DATADB_PROPS = new Properties();

  static {
    try {
      InputStream is = ClassLoader.getSystemResourceAsStream(DATADB_JDBC_PATH);
      DATADB_PROPS.load(is);
      is.close();
    } catch (IOException e) {
      LOG.error("读取游戏数据数据库jdbc配置文件[" + DATADB_JDBC_PATH + "]异常", e);
      System.exit(1);
    }
  }

  @Override
  protected void configure() {

    install(new MyBatisModule() {
      @Override
      protected void initialize() {

        install(JdbcHelper.MySQL);
        bindDataSourceProviderType(DruidDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);
        environmentId(ENVIRONMENT_ID);
        mapperClasses =
            new ResolverUtil<Object>().find((type) -> type.isInterface(), MAPPER_PATH).getClasses();

        addMapperClasses(mapperClasses);// 将包下类都作为mapper导入



        bindConfigurationSetting(new ConfigurationSetting() {

          @Override
          public void applyConfigurationSetting(Configuration configuration) {
            configuration.setCacheEnabled(false);
            configuration.setUseActualParamName(true);
            configuration.setDefaultExecutorType(ExecutorType.REUSE);

          }
        });

        Names.bindProperties(this.binder(), DATADB_PROPS);
      }
    });

    // expose所有mapper
    mapperClasses.forEach((type) -> expose(type));

    /*
     * expose . SqlSessionFactory，SqlSession，SqlSessionManager
     */
    bind(SqlSessionFactory.class).annotatedWith(DataDb.class).to(SqlSessionFactory.class);
    expose(SqlSessionFactory.class).annotatedWith(DataDb.class);

    bind(SqlSession.class).annotatedWith(DataDb.class).toProvider(SqlSessionManagerProvider.class)
        .in(Scopes.SINGLETON);
    expose(SqlSession.class).annotatedWith(DataDb.class);

    bind(SqlSessionManager.class).annotatedWith(DataDb.class)
        .toProvider(SqlSessionManagerProvider.class).in(Scopes.SINGLETON);
    expose(SqlSessionManager.class).annotatedWith(DataDb.class);



    // bind(PlayerService.class).in(Scopes.SINGLETON);
    bind(IPlayerService.class).to(PlayerService.class);
    expose(IPlayerService.class);

    bind(IPlayerMgr.class).to(PlayerMgr.class);
    expose(IPlayerMgr.class);

    Set<Class<? extends Object>> singletonTypes = new ResolverUtil<Object>()
        .find(new ResolverUtil.AnnotatedWith(Singleton.class), PACKAGE_PATH).getClasses();
    for (Class<? extends Object> singletonType : singletonTypes) {
      bind(singletonType);
      expose(singletonType);
    }
  }
}
