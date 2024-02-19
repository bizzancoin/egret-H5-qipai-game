
package com.idealighter.game.games.jcby;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.gamehall.dto.SeatInfo;
import com.idealighter.game.gamehall.dto.TableInfo;
import com.idealighter.game.games.jcby.dto.BatteryInfo;
import com.idealighter.game.games.jcby.message.ReqEnterGameHallMsg;
import com.idealighter.game.games.jcby.message.ReqEnterRoomMsg;
import com.idealighter.game.games.jcby.message.ReqFastEnterTableMsg;
import com.idealighter.game.games.jcby.message.ReqFireMsg;
import com.idealighter.game.robot.context.ApplicationContext;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.scheduler.PlayerScheduler;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 金蝉捕鱼机器人 .
 * 
 * @date 2016年3月23日 下午8:05:45
 *
 */
public class JcbyPlayer extends Player {

  // 默认发炮延迟毫秒
  private static final int DEFAULT_FIRE_DELAY = 400;

  // 筹码
  public long chips;
  // 开炮角度
  private int angle;
  // 炮台信息
  public BatteryInfo battery;
  // 是否暂停发炮
  public boolean pauseFire = false;
  private Future<?> fireFuture;

  public JcbyRoomDomain roomDomain;

  /**
   * 构造函数.
   * 
   * @param playerId .
   * @param userName .
   * @param roomId .
   * @param initGold .
   * @param totalGameNum .
   * @param tableGameNum .
   */
  public JcbyPlayer(long playerId, String userName, int roomId, long initGold, int totalGameNum,
      int tableGameNum, RobotConfigDomain configDomain, JcbyRoomDomain roomDomain) {
    super(playerId, userName, roomId, initGold, totalGameNum, tableGameNum, configDomain);
    this.roomDomain = roomDomain;
  }

  @Override
  public boolean goldCheck() {
    long gold = playerInfo.getSafeGold();
    if (gold < roomDomain.getLower()) {
      LOG.info("[金蝉捕鱼]机器人[{}][{}]金币小于房间下限退出游戏", userName, playerId);
      delayLogout();
      return false;
    }

    long upper = roomDomain.getUpper();
    if (upper > 0L && gold > upper) {
      LOG.info("[金蝉捕鱼]机器人[{}][{}]金币高于房间下限退出游戏", userName, playerId);
      delayLogout();
      return false;
    }

    if (gold < roomDomain.getAfee()) {
      LOG.info("[金蝉捕鱼]机器人[{}][{}]金币小于台费退出游戏", userName, playerId);
      delayLogout();
      return false;
    }

    return true;

  }


  @Override
  public void enterGameHall() {
    sendMsg(new ReqEnterGameHallMsg());
  }

  @Override
  public void enterRoom() {
    if (rooms.contains(roomId)) {
      if (!goldCheck()) {
        return;
      }
      ReqEnterRoomMsg msg = new ReqEnterRoomMsg();
      msg.setRoomId(roomId);
      sendMsg(msg);
    }
  }

  @Override
  public void enterTable() {
    if (!goldCheck()) {
      return;
    }
    ReqFastEnterTableMsg msg = new ReqFastEnterTableMsg();
    msg.setRoomId(this.roomId);

    schedule(() -> sendMsg(msg), RandCodeUtil.random(3, 5), TimeUnit.SECONDS);
  }

  /**
   * 延迟开炮 .
   */
  public void scheduleFire() {
    stopFire();
    this.fireFuture =
        scheduleWithFixedDelay(this::fireRandTime, 0, DEFAULT_FIRE_DELAY, TimeUnit.MILLISECONDS);
  }

  /**
   * 取消开炮 .
   */
  public void stopFire() {
    if (fireFuture != null) {
      fireFuture.cancel(false);
    }
  }

  /**
   * 开炮 .
   */
  private void doFire() {
    if (pauseFire) {
      return;
    }

    int minLeftBullet = RandCodeUtil.random(1, 5);
    if (battery != null && chips < battery.getScore() * minLeftBullet) {
      LOG.info("[金蝉捕鱼]机器人[{}][{}]鱼币不足[{}]炮退出游戏", userName, playerId, minLeftBullet);
      delayLogout();
      return;
    }

    ReqFireMsg fireMsg = new ReqFireMsg();
    // 是否切换角度(20分之1的概率)
    boolean switchAngle = RandCodeUtil.random(20) < 1;

    if (switchAngle || angle == 0) {
      angle = RandCodeUtil.random(5, 175);
    }

    fireMsg.setAngle(angle);
    sendMsg(fireMsg);
  }



  @Override
  public void delayLogout() {
    stopFire();
    super.delayLogout();
  }

  private void fireRandTime() {
    this.schedule(() -> {
      this.doFire();
    }, RandCodeUtil.random(0, 1000), TimeUnit.MILLISECONDS);
  }

  /*
   * 复写seatInfoUpdate方法，座位变更时捕鱼机器人是否停止开炮
   */
  @Override
  public void seatInfoUpdate(SeatInfo seat) {
    int tableId = seat.getTableId();
    super.seatInfoUpdate(seat);

    PlayerScheduler playerScheduler = ApplicationContext.getBean(PlayerScheduler.class);
    TableInfo table = tables.get(tableId);
    boolean hasPlayer = false;

    List<SeatInfo> seats = table.getSeats();
    for (SeatInfo s : seats) {
      long playerId = s.getPlayerId();
      if (playerId > 0 && !playerScheduler.isRobot(playerId)) {
        hasPlayer = true;
        break;
      }
    }

    for (SeatInfo s : seats) {
      Player player = playerScheduler.getPlayer(s.getPlayerId());
      if (player instanceof JcbyPlayer) {
        JcbyPlayer jcbyPlayer = (JcbyPlayer) player;
        jcbyPlayer.pauseFire = !hasPlayer;
      }
    }
  }

  @Override
  public Game game() {
    return Game.JCBY;
  }

  @Override
  public void clear() {
    super.clear();
    stopFire();
  }

  @Override
  public boolean enterPwdTableAble() {
    return true;
  }

}
