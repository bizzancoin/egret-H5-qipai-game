
package com.idealighter.game.core.service.log.struct.recharge;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 玩家充值日志 .
 * 
 * @date 2015年9月2日 上午10:16:29
 *
 */
@LogTable(type = TableType.SINGLE)
public class RechargeLog extends PlayerLog {

  @Column(type = "varchar", size = 256, remark = "订单号")
  public final String orderid;
  @Column(type = "varchar", size = 128, remark = "支付方式")
  public final String payway;
  @Column(type = "varchar", size = 128, remark = "支付商名称")
  public final String paywayname;
  @Column(type = "bigint", size = 20, remark = "支付金额")
  public final long payamount;
  @Column(type = "bigint", size = 20, remark = "支付/赠送金币")
  public final long paygold;
  @Column(type = "varchar", size = 256, remark = "第三方订单号/充值卡/新手卡")
  public final String providerorderid;


  /**
   * 构造函数 .
   * 
   * @param domain .
   * @param orderid .
   * @param payway .
   * @param paywayname .
   * @param payamount .
   * @param paygold .
   * @param providerorderid .
   */
  public RechargeLog(PlayerBo domain, String orderid, String payway, String paywayname,
      long payamount, long paygold, String providerorderid) {
    super(domain);
    this.orderid = orderid;
    this.payway = payway;
    this.paywayname = paywayname;
    this.payamount = payamount;
    this.paygold = paygold;
    this.providerorderid = providerorderid;
  }

}
