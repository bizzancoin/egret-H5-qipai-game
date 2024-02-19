
package com.idealighter.game.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * md5测试.
 *
 */
public class MD5UtilTest {

  /**
   * 测试md5 .
   */
  @Test
  public void testMd5() {
    Assert.assertEquals("9d488bd40cfd640b87c205cdaa4c1844", PwdEncode.encodeWithTail("888888"));
  }
}
