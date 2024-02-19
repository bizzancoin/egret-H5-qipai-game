package com.idealighter.game.core.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;

/**
 * DruidDataSourceFactory.
 *
 */
public class DruidDataSourceFactory implements DataSourceFactory {
  private DataSource dataSource;

  @Override
  public DataSource getDataSource() {
    return this.dataSource;
  }

  @Override
  public void setProperties(final Properties props) {
    try {
      this.dataSource = com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource(props);
    } catch (final RuntimeException e) {
      throw e;
    } catch (final Exception e) {
      throw new RuntimeException("init datasource error", e);
    }
  }

}
