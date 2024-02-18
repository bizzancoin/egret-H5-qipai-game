package com.idealighter.game.service.games.jcby.bo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class JcbyRoomBo {

  private Integer id;

  /**
   * 房间名称.
   */
  private String name;

  /**
   * 房间类型(jcby_room_type).
   */
  private Integer type;

  /**
   * 鱼的场景.
   */
  private List<Integer> scences;



  /**
   * 李逵的fishId.
   */
  private Integer liKui;


  /**
   * 能量炮出现倍率.
   */
  private Integer powerBatteryMultiple;

  /**
   * 能量炮出现概率(万分比).
   */
  private Integer powerBatteryPro;

  /**
   * 能量炮时间(秒).
   */
  private Integer powerBatteryTime;

  /**
   * 房间最大人数.
   */
  private Integer maxNum;

  /**
   * 进入下限.
   */
  private Long lower;

  /**
   * 进入上限.
   */
  private Long upper;

  /**
   * 普通玩家进入人数百分比.
   */
  private Integer ordinarPeople;

  // 机器人占百分比
  private Integer robotRatio;

  /**
   * 金币与筹码比例（金币）.
   */
  private Integer proportionGold;

  /**
   * 金币与筹码比例（筹码）.
   */
  private Integer proportionChips;

  /**
   * 桌子数.
   */
  private Integer tableNum;

  /**
   * 每桌椅子数.
   */
  private Integer chair;

  /**
   * 台费(进桌扣一次).
   */
  private Integer afee;

  /**
   * 进入类型（0点击入座，1自动分配）.
   */
  private Integer inType;


  /**
   * 状态(0:关闭,1:开启).
   */
  private Byte isActive;

  /**
   * 创建时间.
   */
  private Date timeCreate;

  /**
   * 开启时间.
   */
  private Date timeOpen;

}
