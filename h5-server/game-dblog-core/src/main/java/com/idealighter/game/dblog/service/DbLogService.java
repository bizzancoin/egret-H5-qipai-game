package com.idealighter.game.dblog.service;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.task.InsertDbLogTask;
import com.idealighter.game.dblog.task.UpdateDbLogTask;
import com.idealighter.game.dblog.util.DbLogScanner;
import com.idealighter.game.dblog.util.DbLogValidator;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库日志服务.
 *
 */
public class DbLogService {

  private static final Logger LOG = LoggerFactory.getLogger(DbLogService.class);
  // 数据库日志监控日志
  private static final Logger DBLOG_MOINTORLOG = LoggerFactory.getLogger("DbLogMointorLog");

  // jdbc配置文件
  private static final String JDBC_PATH = "config/jdbc/logdb.properties";
  // 数据库日志class定义包
  private static final String DBLOG_PACKAGE = "com.idealighter.game";

  // DbLogService单例
  private static DbLogService instance;

  static {
    instance = new DbLogService();
  }

  // 数据源
  private DataSource ds;
  // 数据库日志服务队列
  public final BlockingQueue<Runnable> dbqueue;
  // 数据库日志服务线程执行器
  private final ThreadPoolExecutor dbexecutor;
  // 监控数据库日志队列长度timer
  private final ScheduledExecutorService mointorTimer;

  /**
   * 构造.
   */
  public DbLogService() {
    dbqueue = new LinkedBlockingQueue<>();
    dbexecutor = new ThreadPoolExecutor(4, 8, 0L, TimeUnit.MILLISECONDS, this.dbqueue,
        r -> new Thread(r, "dbLogExecutor"));
    // dbexecutor关闭后依旧要将新提交的日志记录到数据库
    dbexecutor.setRejectedExecutionHandler((r, executor) -> r.run());
    mointorTimer =
        Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "dbLogMointorTimer"));
    try {
      this.ds = createDataSource();
    } catch (Exception e) {
      LOG.error("初始化日志服务失败", e);
      System.exit(1);
    }

    // 定时监控数据库日志队列的长度
    this.mointorTimer.scheduleWithFixedDelay(
        () -> DBLOG_MOINTORLOG.info("dblog queue length[{}]", dbqueue.size()), 1, 1,
        TimeUnit.MINUTES);
    LOG.info("初始化日志数据库服务成功");
  }

  /**
   * 初始化数据源 .
   * @return .
   * @throws Exception .
   */
  private DataSource createDataSource() throws Exception {
    Properties props = new Properties();
    InputStream is = ClassLoader.getSystemResourceAsStream(JDBC_PATH);
    props.load(is);
    is.close();
    return DruidDataSourceFactory.createDataSource(props);
  }

  /**
   * 校验正在写的日志表的列定义是否正确，在开发阶段偶尔会调整字段类型,长度等 .
   */
  public static void checkTables() {
    Set<Class<? extends DbLog>> dbLogClasses = DbLogScanner.scanLogClass(DBLOG_PACKAGE);
    for (Class<? extends DbLog> dbLogClass : dbLogClasses) {
      DbLogValidator.validateTbl(dbLogClass);
    }
  }

  /**
   * 纪录数据库日志(异步的) .
   * @param dbLog . .
   */
  public static void log(DbLog dbLog) {
    if (dbLog != null) {
      instance.dbexecutor.execute(new InsertDbLogTask(instance.ds, dbLog));
    }
  }

  /**
   * 更新数据库日志(异步的)，LogTable种必须指定id列 .
   * @param dbLog . .
   */
  public static void updateLog(DbLog dbLog) {
    if (dbLog != null) {
      instance.dbexecutor.execute(new UpdateDbLogTask(instance.ds, dbLog));
    }
  }

  /**
   * 关闭数据库日志服务 .
   */
  public static void shutdown() {
    LOG.info("关闭日志系统中...");
    /*
     * List<Runnable> tasks = instance.dbexecutor.shutdownNow(); if (tasks.size() > 0) { for (int i
     * = 0; i < tasks.size(); i++) { Runnable task = tasks.get(i); task.run(); } }
     */
    instance.dbexecutor.shutdown();
    LOG.info("日志系统关闭成功");
  }

  /**
   * 获取数据库连接 .
   * @return .
   * @throws SQLException .
   */
  public static Connection getConnection() throws SQLException {
    return instance.ds.getConnection();
  }

  /**
   * 获取数据库日志队列 .
   * @return .
   */
  public static BlockingQueue<Runnable> getDbQueue() {

    return instance.dbqueue;
  }

}
