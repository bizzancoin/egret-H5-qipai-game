package com.idealighter.game.core.datasource;

import com.alibaba.druid.pool.DruidDataSource;

import com.google.inject.Provider;

import java.sql.SQLException;
import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DruidDataSource数据源Provider.
 *
 */
public class DruidDataSourceProvider implements Provider<DataSource> {

  private static final Logger LOG = LoggerFactory.getLogger(DruidDataSourceProvider.class);

  private final DruidDataSource dataSource;

  public DruidDataSourceProvider() {
    super();
    dataSource = new DruidDataSource();
  }

  @Inject
  public void setDriverClassName(@Named("jdbc.driver") final String driverClass) {
    dataSource.setDriverClassName(driverClass);
  }

  @Inject
  public void setUrl(@Named("jdbc.url") final String jdbcUrl) {
    dataSource.setUrl(jdbcUrl);
  }

  @Inject
  public void setUsername(@Named("jdbc.user") final String username) {
    dataSource.setUsername(username);
  }

  @Inject
  public void setPassword(@Named("jdbc.pw") final String password) {
    dataSource.setPassword(password);
  }

  @Inject
  public void setInitialSize(@Named("jdbc.initialSize") final int initialSize) {
    dataSource.setInitialSize(initialSize);
  }

  @Inject
  public void setMinIdle(@Named("jdbc.minIdle") final int minIdle) {
    dataSource.setMinIdle(minIdle);
  }

  @Inject
  public void setMaxActive(@Named("jdbc.maxActive") final int maxActive) {
    dataSource.setMaxActive(maxActive);
  }

  @Inject
  public void setMaxWait(@Named("jdbc.maxWait") final long maxWaitMillis) {
    dataSource.setMaxWait(maxWaitMillis);
  }

  @Inject
  public void setTimeBetweenEvictionRunsMillis(
      @Named("jdbc.timeBetweenEvictionRunsMillis") final long timeBetweenEvictionRunsMillis) {
    dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
  }

  @Inject
  public void setMinEvictableIdleTimeMillis(
      @Named("jdbc.minEvictableIdleTimeMillis") final long minEvictableIdleTimeMillis) {
    dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
  }

  @Inject
  public void setValidationQuery(@Named("jdbc.validationQuery") final String validationQuery) {
    dataSource.setValidationQuery(validationQuery);
  }

  @Inject
  public void setTestWhileIdle(@Named("jdbc.testWhileIdle") final boolean testWhileIdle) {
    dataSource.setTestWhileIdle(testWhileIdle);
  }

  @Inject
  public void setTestOnBorrow(@Named("jdbc.testOnBorrow") final boolean testOnBorrow) {
    dataSource.setTestOnBorrow(testOnBorrow);
  }

  @Inject
  public void setTestOnReturn(@Named("jdbc.testOnReturn") final boolean testOnReturn) {
    dataSource.setTestOnReturn(testOnReturn);
  }

  @Inject
  public void setConnectionInitSqls(@Named("jdbc.connectionInitSqls") final String sqls) {

    dataSource.setConnectionInitSqls(Arrays.asList(sqls.split(";")));
  }

  /**
   * 设置拦截器.
   * 
   * @param filters 拦截器.
   */
  @Inject
  public void setFilters(@Named("jdbc.filters") final String filters) {
    try {
      dataSource.setFilters(filters);
    } catch (SQLException e) {
      LOG.error("setFilters error", e);
    }
  }

  @Override
  public DataSource get() {
    return dataSource;
  }

}
