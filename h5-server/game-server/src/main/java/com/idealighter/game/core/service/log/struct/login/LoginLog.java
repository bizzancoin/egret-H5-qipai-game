
package com.idealighter.game.core.service.log.struct.login;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.login.constant.LoginPlatform;
import com.idealighter.game.core.service.login.constant.LoginType;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 登录日志 .
 * 
 * @date 2015年8月31日 下午9:05:15
 *
 */
@LogTable(type = TableType.DAY)
public class LoginLog extends PlayerLog {
  @Column(type = "varchar", size = 62, remark = "登录ip")
  public final String ip;
  @Column(type = "varchar", size = 10, remark = "登录平台")
  public final String platform;
  @Column(type = "varchar", size = 10, remark = "登录方式")
  public final String loginType;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   */
  public LoginLog(Player player, int platform, int type) {
    super(player);
    this.ip = player.ip;

    String platfromName = "";
    for (LoginPlatform item : LoginPlatform.values()) {
      if (item.getId() == platform) {
        platfromName = item.getName();
        break;
      }
    }
    this.platform = platfromName;

    String loginType = "";
    for (LoginType item : LoginType.values()) {
      if (item.getType() == type) {
        loginType = item.getName();
        break;
      }
    }
    this.loginType = loginType;
  }
}
