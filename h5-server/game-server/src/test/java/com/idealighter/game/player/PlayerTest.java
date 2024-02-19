package com.idealighter.game.player;

import static org.junit.Assert.assertEquals;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

  // private static final ApplicationContext applicationContext =
  // ApplicationContext.createInstance();


  @Test
  public void testAddExp() {

    Assert.assertNotNull(ApplicationContext.getBean(PlayerMgr.class));

    PlayerMgr playerMgr = ApplicationContext.getBean(PlayerMgr.class);

    PlayerBo playerDomain = playerMgr.selectPlayer(126773L);

    Assert.assertNotNull(playerDomain);

    Player player = new Player(null, null);

    player.setPlayerBo(playerDomain);
    player.setOnline(true);
    player.setTransferReward(1000000L);

    playerMgr.registerPlayer(player);

    playerMgr.addExp(player, 100);
    assertEquals(1, player.getLevel());

    player.setLevel(0);
    player.setExp(0);
    playerMgr.addExp(player, 500);

    assertEquals(2, player.getLevel());



    player.setLevel(0);
    player.setExp(0);
    playerMgr.addExp(player, 1000);

    assertEquals(3, player.getLevel());


    player.setLevel(0);
    player.setExp(0);
    playerMgr.addExp(player, 2001);

    assertEquals(4, player.getLevel());


    player.setLevel(0);
    player.setExp(0);
    playerMgr.addExp(player, 9999);

    assertEquals(4, player.getLevel());


    player.setLevel(0);
    player.setExp(122);
    playerMgr.addExp(player, 200000000);

    assertEquals(10, player.getLevel());


    player.setLevel(9);
    player.setExp(0);
    playerMgr.addExp(player, 200000000);

    assertEquals(10, player.getLevel());


    player.setLevel(10);
    player.setExp(121212);
    playerMgr.addExp(player, 200000000);

    assertEquals(10, player.getLevel());


  }
}
