
package com.idealighter.game.games.jcby.handler;

import com.google.inject.Inject;
import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.dictionary.dic.JcbyBatteryDic;
import com.idealighter.game.dictionary.domain.JcbyBatteryDomain;
import com.idealighter.game.games.jcby.JcbyPlayer;
import com.idealighter.game.games.jcby.dto.BatteryInfo;
import com.idealighter.game.games.jcby.message.ReqSwitchBatteryMsg;
import com.idealighter.game.games.jcby.message.ResFireMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;
import com.idealighter.utils.code.RandCodeUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.RES_FIRE)
public class ResFireHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResFireHandler.class);
  @Inject
  private JcbyBatteryDic batteryDic;

  @Override
  public void action(Player player, ResMessage message) {
    ResFireMsg msg = (ResFireMsg) message;

    JcbyPlayer jcbyPlayer = (JcbyPlayer) player;
    BatteryInfo oldBattery = jcbyPlayer.battery;
    // int minLeftBullet = RandCodeUtil.random(1, 5);
    // // 如果筹码少于[1-5炮],切换为小炮
    // if (oldBattery != null && jcbyPlayer.chips < oldBattery.getScore() * minLeftBullet) {
    // int maxScore = 100;
    // // 100倍时才切换
    // if (oldBattery.getScore() > maxScore) {
    // List<JcbyBatteryDomain> list = new ArrayList<>();
    // for (JcbyBatteryDomain item : batteryDic.list()) {
    // if (item.getScore() < maxScore) {
    // list.add(item);
    // }
    // }
    // // 升序排列
    // list.sort((a, b) -> a.getId() - b.getId());
    // int oldBatteryId = getBatteryId(oldBattery.getScore());
    // // 新炮
    // JcbyBatteryDomain newBatteryDom = randBattery(list);
    // // 间隔
    // int gap = newBatteryDom.getId() - oldBatteryId;
    // // 暂停发炮
    // jcbyPlayer.pauseFire = true;
    // for (int i = 0; i < Math.abs(gap); i++) {
    // jcbyPlayer.schedule(() -> {
    // ReqSwitchBatteryMsg reqMsg = new ReqSwitchBatteryMsg();
    // reqMsg.setType(gap > 0 ? 1 : 0);
    // player.sendMsg(reqMsg);
    // }, 1, TimeUnit.SECONDS);
    // }
    // // 3s之后再次发炮
    // jcbyPlayer.schedule(() -> {
    // jcbyPlayer.pauseFire = false;
    // }, 3, TimeUnit.SECONDS);
    // return;
    // }
    // }
    // 1/100的概率切换炮
    if (oldBattery != null && msg.getPlayerId() == player.playerId
        && RandCodeUtil.random(100) < 1) {
      int oldBatteryId = getBatteryId(oldBattery.getScore());
      // 新炮
      JcbyBatteryDomain newBatteryDom = randBattery(batteryDic.list());
      // RandCodeUtil.randomList(batteryDic.list());
      // 间隔
      int gap = newBatteryDom.getId() - oldBatteryId;
      for (int i = 0; i < Math.abs(gap); i++) {
        jcbyPlayer.schedule(() -> {
          ReqSwitchBatteryMsg reqMsg = new ReqSwitchBatteryMsg();
          reqMsg.setType(gap > 0 ? 1 : 0);
          player.sendMsg(reqMsg);
        }, 1, TimeUnit.SECONDS);
      }
    }
  }

  private JcbyBatteryDomain randBattery(List<JcbyBatteryDomain> list) {
    int total = 0;
    for (JcbyBatteryDomain item : list) {
      total += item.getScore();
    }
    int randTotal = 0;
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    List<Integer> idList = new ArrayList<Integer>();
    for (JcbyBatteryDomain item : list) {
      map.put(item.getId(), total - item.getScore());
      randTotal += total - item.getScore();
      idList.add(item.getId());
    }
    int rand = RandCodeUtil.random(randTotal);
    Collections.shuffle(idList);
    int resultId = 0;
    int value = 0;
    for (int id : idList) {
      value += map.get(id);
      if (value >= rand) {
        resultId = id;
        break;
      }
    }
    JcbyBatteryDomain result = null;
    for (JcbyBatteryDomain item : list) {
      if (item.getId() == resultId) {
        result = item;
        break;
      }
    }
    return result;
  }


  private int getBatteryId(int score) {
    for (JcbyBatteryDomain batteryDom : batteryDic.list()) {
      if (batteryDom.getScore() == score) {
        return batteryDom.getId();
      }
    }

    return 0;
  }
}
