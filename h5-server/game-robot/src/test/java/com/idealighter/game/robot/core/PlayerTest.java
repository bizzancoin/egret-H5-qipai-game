
package com.idealighter.game.robot.core;

import java.time.LocalTime;

import org.junit.Test;

/**
 * PlayerTest.
 *
 */
public class PlayerTest {

  @Test
  public void testLogin() {
    /*
     * Player player = new Player("1"); player.game = Game.getGame(2); player.roomId = 1;
     * player.login();
     * 
     * try { Thread.sleep(100000); } catch (InterruptedException e) { e.printStackTrace(); }
     */
  }

  @Test
  public void testLocalTime() {
    LocalTime time = LocalTime.now();

    System.out.println(time.getHour());
  }
}
