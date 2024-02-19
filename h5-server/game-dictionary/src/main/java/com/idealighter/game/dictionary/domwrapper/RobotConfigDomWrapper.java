
package com.idealighter.game.dictionary.domwrapper;

import com.idealighter.game.dictionary.domain.RobotConfigDomain;

/**
 * RobotConfigDomWrapper.
 *
 */
public class RobotConfigDomWrapper extends RobotConfigDomain {

  private final RobotConfigDomain dom;

  /**
   * 构造函数.
   * @param dom .
   */
  public RobotConfigDomWrapper(RobotConfigDomain dom) {
    this.dom = dom;
  }

  /**
   * 获取该小时段的机器人数量.
   * @param hour .
   * @return .
   */
  public int getRobotNum(int hour) {
    int index = hour / 2;
    int robotNum = 0;

    switch (index) {
      case 0:
        robotNum = dom.getTime1Players();
        break;
      case 1:
        robotNum = dom.getTime2Players();
        break;
      case 2:
        robotNum = dom.getTime3Players();
        break;
      case 3:
        robotNum = dom.getTime4Players();
        break;
      case 4:
        robotNum = dom.getTime5Players();
        break;
      case 5:
        robotNum = dom.getTime6Players();
        break;
      case 6:
        robotNum = dom.getTime7Players();
        break;
      case 7:
        robotNum = dom.getTime8Players();
        break;
      case 8:
        robotNum = dom.getTime9Players();
        break;
      case 9:
        robotNum = dom.getTime10Players();
        break;
      case 10:
        robotNum = dom.getTime11Players();
        break;
      case 11:
        robotNum = dom.getTime12Players();
        break;

      default:
        break;
    }

    return robotNum;
  }

  @Override
  public int getId() {
    return dom.getId();
  }

  @Override
  public void setId(int id) {
    dom.setId(id);
  }

  @Override
  public int getGame() {
    return dom.getGame();
  }

  @Override
  public void setGame(int game) {
    dom.setGame(game);
  }

  @Override
  public int getRoom() {
    return dom.getRoom();
  }

  @Override
  public void setRoom(int room) {
    dom.setRoom(room);
  }

  @Override
  public byte getOccupyTable() {
    return dom.getOccupyTable();
  }

  @Override
  public void setOccupyTable(byte occupyTable) {
    dom.setOccupyTable(occupyTable);
  }

  @Override
  public byte getGameWithPlayer() {
    return dom.getGameWithPlayer();
  }

  @Override
  public void setGameWithPlayer(byte gameWithPlayer) {
    dom.setGameWithPlayer(gameWithPlayer);
  }

  @Override
  public long getGoldLower() {
    return dom.getGoldLower();
  }

  @Override
  public void setGoldLower(long goldLower) {
    dom.setGoldLower(goldLower);
  }

  @Override
  public long getGoldUpper() {
    return dom.getGoldUpper();
  }

  @Override
  public void setGoldUpper(long goldUpper) {
    dom.setGoldUpper(goldUpper);
  }

  @Override
  public long getMaxGold() {
    return dom.getMaxGold();
  }

  @Override
  public void setMaxGold(long maxGold) {
    dom.setMaxGold(maxGold);
  }

  @Override
  public int getTableGameLower() {
    return dom.getTableGameLower();
  }

  @Override
  public void setTableGameLower(int tableGameLower) {
    dom.setTableGameLower(tableGameLower);
  }

  @Override
  public int getTableGameUpper() {
    return dom.getTableGameUpper();
  }

  @Override
  public void setTableGameUpper(int tableGameUpper) {
    dom.setTableGameUpper(tableGameUpper);
  }

  @Override
  public int getTotalGameLower() {
    return dom.getTotalGameLower();
  }

  @Override
  public void setTotalGameLower(int totalGameLower) {
    dom.setTotalGameLower(totalGameLower);
  }

  @Override
  public int getTotalGameUpper() {
    return dom.getTotalGameUpper();
  }

  @Override
  public void setTotalGameUpper(int totalGameUpper) {
    dom.setTotalGameUpper(totalGameUpper);
  }

  @Override
  public int getTime1Players() {
    return dom.getTime1Players();
  }

  @Override
  public void setTime1Players(int time1Players) {
    dom.setTime1Players(time1Players);
  }

  @Override
  public int getTime2Players() {
    return dom.getTime2Players();
  }

  @Override
  public void setTime2Players(int time2Players) {
    dom.setTime2Players(time2Players);
  }

  @Override
  public int getTime3Players() {
    return dom.getTime3Players();
  }

  @Override
  public void setTime3Players(int time3Players) {
    dom.setTime3Players(time3Players);
  }

  @Override
  public int getTime4Players() {
    return dom.getTime4Players();
  }

  @Override
  public void setTime4Players(int time4Players) {
    dom.setTime4Players(time4Players);
  }

  @Override
  public int getTime5Players() {
    return dom.getTime5Players();
  }

  @Override
  public void setTime5Players(int time5Players) {
    dom.setTime5Players(time5Players);
  }

  @Override
  public int getTime6Players() {
    return dom.getTime6Players();
  }

  @Override
  public void setTime6Players(int time6Players) {
    dom.setTime6Players(time6Players);
  }

  @Override
  public int getTime7Players() {
    return dom.getTime7Players();
  }

  @Override
  public void setTime7Players(int time7Players) {
    dom.setTime7Players(time7Players);
  }

  @Override
  public int getTime8Players() {
    return dom.getTime8Players();
  }

  @Override
  public void setTime8Players(int time8Players) {
    dom.setTime8Players(time8Players);
  }

  @Override
  public int getTime9Players() {
    return dom.getTime9Players();
  }

  @Override
  public void setTime9Players(int time9Players) {
    dom.setTime9Players(time9Players);
  }

  @Override
  public int getTime10Players() {
    return dom.getTime10Players();
  }

  @Override
  public void setTime10Players(int time10Players) {
    dom.setTime10Players(time10Players);
  }

  @Override
  public int getTime11Players() {
    return dom.getTime11Players();
  }

  @Override
  public void setTime11Players(int time11Players) {
    dom.setTime11Players(time11Players);
  }

  @Override
  public int getTime12Players() {
    return dom.getTime12Players();
  }

  @Override
  public void setTime12Players(int time12Players) {
    dom.setTime12Players(time12Players);
  }
}
