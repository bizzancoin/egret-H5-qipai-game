package com.idealighter.game.robot.datadb;

import com.google.inject.PrivateModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

import com.idealighter.game.core.annotation.DataDb;
import com.idealighter.game.core.datasource.DruidDataSourceProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.session.SqlSessionManagerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 玩家数据数据库模块.
 *
 */
public class DataDdModule extends PrivateModule {

  private static final Logger LOG = LoggerFactory.getLogger(DataDdModule.class);

  /** 游戏数据字典数据库jdbc配置文件路径. */
  private static final String DICDB_JDBC_PATH = "config/jdbc/datadb.properties";
  /** 游戏数据字典数据库jdbc Properties. */
  private static final Properties DICDB_PROPS = new Properties();
  /** 字段数据库mapper路径. */
  private static final String MAPPER_PATH = "com.idealighter.game.robot.datadb.mapper";

  static {
    try {
      InputStream is = ClassLoader.getSystemResourceAsStream(DICDB_JDBC_PATH);
      DICDB_PROPS.load(is);
      is.close();
    } catch (IOException e) {
      LOG.error("读取游戏数据字典数据库jdbc配置文件[" + DICDB_JDBC_PATH + "]异常", e);
      System.exit(1);
    }
  }

  private Set<Class<?>> mapperClasses;

  @Override
  protected void configure() {
    install(new MyBatisModule() {

      @Override
      protected void initialize() {
        bindDataSourceProviderType(DruidDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);

        mapperClasses =
            new ResolverUtil<Object>().find((type) -> type.isInterface(), MAPPER_PATH).getClasses();

        addMapperClasses(mapperClasses);// 将包下类都作为mapper导入

        Names.bindProperties(this.binder(), DICDB_PROPS);

      }
    });

    // expose所有mapper
    mapperClasses.forEach((type) -> expose(type));

    /*
     * expose SqlSessionFactory，SqlSession，SqlSessionManager
     */
    bind(SqlSessionFactory.class).annotatedWith(DataDb.class).to(SqlSessionFactory.class);
    expose(SqlSessionFactory.class).annotatedWith(DataDb.class);

    bind(SqlSession.class).annotatedWith(DataDb.class).toProvider(SqlSessionManagerProvider.class)
        .in(Scopes.SINGLETON);
    expose(SqlSession.class).annotatedWith(DataDb.class);

    bind(SqlSessionManager.class).annotatedWith(DataDb.class)
        .toProvider(SqlSessionManagerProvider.class).in(Scopes.SINGLETON);
    expose(SqlSessionManager.class).annotatedWith(DataDb.class);
  }
}
