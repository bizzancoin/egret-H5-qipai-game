package com.idealighter.game.server.context;

import com.google.inject.Key;
import com.google.inject.name.Names;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.annotation.DataDb;
import com.idealighter.game.core.annotation.DicDb;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * ApplicationContext测试.
 */
public class ApplicationContextTest {

  // private static final ApplicationContext applicationContext =
  // ApplicationContext.createInstance();

  @Test
  public void testDictionarySession() {
    SqlSession session = ApplicationContext.getBean(Key.get(SqlSession.class, DicDb.class));
    Assert.assertNotNull(session);
  }

  @Test
  public void testDataDbSession() {
    SqlSession session = ApplicationContext.getBean(Key.get(SqlSession.class, DataDb.class));
    Assert.assertNotNull(session);
  }

  @Test
  public void testMapper() {

    // Assert.assertNotNull(ApplicationContext.getBean(ItemMapper.class));
    // Assert.assertNotNull(ApplicationContext.getBean(PlayerMainDomainMapper.class));
    //
    // System.out.println(ApplicationContext.getBean(PlayerMainDomainMapper.class)
    // .selectByPrimaryKey(1L).getPlayerName());
  }

  @Test
  public void testNames() {
    System.out.println(
        ApplicationContext.getBean(Key.get(Boolean.class, Names.named("backend.authorization"))));
  }
}
