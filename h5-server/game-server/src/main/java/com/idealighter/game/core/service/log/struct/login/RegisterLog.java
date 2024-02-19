
package com.idealighter.game.core.service.log.struct.login;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;

/**
 * 注册日志 .
 * 
 * @date 2015年12月28日 上午10:42:30
 *
 */
public class RegisterLog extends PlayerLog {
  @Column(type = "varchar", size = 62, remark = "注册ip")
  public final String ip;
  @Column(type = "varchar", size = 62, remark = "登录平台")
  public final String platform;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   */
  public RegisterLog(Player player, int platform) {
    super(player);
    this.ip = player.ip;
    String platfromName = "";

    switch (platform) {
      case 0:
        platfromName = "ios";
        break;
      case 1:
        platfromName = "android";
        break;
      case 2:
        platfromName = "pc";
        break;
      case 3:
        platfromName = "主站";
        break;
      default:
        platfromName = "";
        break;
    }
    this.platform = platfromName;
  }
  
  /**
   * 构造函数 .
   * 
   * @param playerBo 玩家.
   */
  public RegisterLog(PlayerBo playerBo, int platform) {
    super(playerBo);
    this.ip = playerBo.getRegisterIp();
    String platfromName = "";

    switch (platform) {
      case 0:
        platfromName = "ios";
        break;
      case 1:
        platfromName = "android";
        break;
      case 2:
        platfromName = "pc";
        break;
      case 3:
        platfromName = "主站";
        break;
      default:
        platfromName = "";
        break;
    }
    this.platform = platfromName;
  }

}
