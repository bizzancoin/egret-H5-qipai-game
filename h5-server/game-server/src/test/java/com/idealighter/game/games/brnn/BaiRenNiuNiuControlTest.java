package com.idealighter.game.games.brnn;

import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuControlScript;
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuControlScript.CardsMapWithSysWinChips;
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuMgr;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCard;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsType;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsTypeGetter;
import com.idealighter.utils.code.RandCodeUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BaiRenNiuNiuControlTest {
  private static final String OUTPUT_PATH = "output";
  private static final String TEST_PATH = OUTPUT_PATH + File.separator + "test";
  private static long totalBetChips = 0L; // 总下注
  private static long totalBetCounts = 0; // 总下注把数
  private static long totalSysWinChips = 0L; // 系统总收益筹码
  private static double minSysInputRate = Double.MAX_VALUE; // 最低系统收益率
  private static double maxSysInputRate = Double.MIN_VALUE; // 最高系统收益率

  public static void main(String[] args) {
    // 100局
    pxy(0, 100, 100, 100);
    pxy(10, 90, 100, 100);
    pxy(20, 80, 100, 100);
    pxy(30, 70, 100, 100);
    pxy(40, 60, 100, 100);
    pxy(50, 50, 100, 100);
    pxy(60, 40, 100, 100);
    pxy(70, 30, 100, 100);
    pxy(80, 20, 100, 100);
    pxy(90, 10, 100, 100);
    pxy(100, 0, 100, 100);
    // 500局
    pxy(0, 100, 500, 20);
    pxy(10, 90, 500, 20);
    pxy(20, 80, 500, 20);
    pxy(30, 70, 500, 20);
    pxy(40, 60, 500, 20);
    pxy(50, 50, 500, 20);
    pxy(60, 40, 500, 20);
    pxy(70, 30, 500, 20);
    pxy(80, 20, 500, 20);
    pxy(90, 10, 500, 20);
    pxy(100, 0, 500, 20);
    // 500局
    pxy(0, 100, 800, 10);
    pxy(10, 90, 800, 10);
    pxy(20, 80, 800, 10);
    pxy(30, 70, 800, 10);
    pxy(40, 60, 800, 10);
    pxy(50, 50, 800, 10);
    pxy(60, 40, 800, 10);
    pxy(70, 30, 800, 10);
    pxy(80, 20, 800, 10);
    pxy(90, 10, 800, 10);
    pxy(100, 0, 800, 10);
    // 1000局
    pxy(0, 100, 1000, 10);
    pxy(10, 90, 1000, 10);
    pxy(20, 80, 1000, 10);
    pxy(30, 70, 1000, 10);
    pxy(40, 60, 1000, 10);
    pxy(50, 50, 1000, 10);
    pxy(60, 40, 1000, 10);
    pxy(70, 30, 1000, 10);
    pxy(80, 20, 1000, 10);
    pxy(90, 10, 1000, 10);
    pxy(100, 0, 1000, 10);
  }

  private static void pxy(int win, int lose, long totalRoundPerGame, int games) {
    totalBetChips = 0L;
    totalBetCounts = 0;
    totalSysWinChips = 0L;
    minSysInputRate = Double.MAX_VALUE;
    maxSysInputRate = Double.MIN_VALUE;

    String fileName = "result-玩家输赢(" + win * 1.0 / (win + lose) * 100 + "%)-每次统计("
        + totalRoundPerGame + ")局.txt";
    String content = "玩家胜率:" + win * 1.0 / (win + lose) * 100 + "%, 每次统计: " + totalRoundPerGame
        + "局, 测试: " + games + "次\n";
    writeResult(fileName, false, content);
    long start = System.currentTimeMillis();
    for (int i = 0; i < games; i++) {
      writeResult(fileName, true, "第" + (i + 1) + "次: ");
      p(fileName, win, lose, totalRoundPerGame, 100, 500);
    }
    content = "最终统计: 总下注次数->" + totalBetCounts + //
        "次, 总下注->" + totalBetChips + //
        ", 系统总赢分->" + totalSysWinChips + //
        ", 最低系统收益率->" + minSysInputRate + //
        ", 最高系统收益率->" + maxSysInputRate + //
        ", 评论系统收益率->" + ((totalSysWinChips * 1.0) / totalBetChips * 100) + "%";

    writeResult(fileName, true, content);
    long end = System.currentTimeMillis();
    System.out.println("耗时: " + (end - start) / 1000 * 1.0 + "s");
  }

  private static void p(String fileName, int playerWin, int playerLose, long totalRound,
      long minBets, long maxBets) {
    long sysWinChips = 0;
    long roundCount = 0;
    long bets = 0;
    for (int i = 0; i < totalRound; i++) {
      roundCount = i + 1;
      System.out.println("第:" + roundCount + "局进行中");

      // 所有分发的牌
      LinkedList<List<NiuNiuCard>> cardsList = new LinkedList<>();
      LinkedList<NiuNiuCardsType> cardsTypeList = new LinkedList<>();
      int cardIndex = 0;
      List<NiuNiuCard> list = Arrays.asList(NiuNiuCard.values());
      // 洗牌
      Collections.shuffle(list);
      for (int m = 0; m < 10; m++) {
        List<NiuNiuCard> subList =
            list.subList(cardIndex, cardIndex += BaiRenNiuNiuMgr.PLAYER_CARDS);
        NiuNiuCardsType seatCardType =
            NiuNiuCardsTypeGetter.getCardsType(subList, new ArrayList<>());

        cardsList.add(subList);
        cardsTypeList.add(seatCardType);
      }
      boolean sysWin = !(RandCodeUtil.random(playerWin + playerLose) >= playerLose);
      boolean sysBanker = RandCodeUtil.randomBoolean();
      long seat1PlayerBets = 0;
      long seat1RobotBets = 0;
      long seat2PlayerBets = 0;
      long seat2RobotBets = 0;
      long seat3PlayerBets = 0;
      long seat3RobotBets = 0;
      long seat4PlayerBets = 0;
      long seat4RobotBets = 0;

      long seat1Bets = RandCodeUtil.random(minBets, maxBets);
      long seat2Bets = RandCodeUtil.random(minBets, maxBets);
      long seat3Bets = RandCodeUtil.random(minBets, maxBets);
      long seat4Bets = RandCodeUtil.random(minBets, maxBets);

      bets += seat1Bets;
      bets += seat2Bets;
      bets += seat3Bets;
      bets += seat4Bets;

      if (seat1Bets > 0 || seat2Bets > 0 || seat3Bets > 0 || seat4Bets > 0) {
        if (sysBanker) {
          seat1PlayerBets = seat1Bets;
          seat2PlayerBets = seat2Bets;
          seat3PlayerBets = seat3Bets;
          seat4PlayerBets = seat4Bets;
        } else {
          seat1RobotBets = seat1Bets;
          seat2RobotBets = seat2Bets;
          seat3RobotBets = seat3Bets;
          seat4RobotBets = seat4Bets;
        }

        CardsMapWithSysWinChips result =
            BaiRenNiuNiuControlScript.choseCards(cardsList, cardsTypeList, sysWin, sysBanker,
                playerLose, playerWin, seat1PlayerBets, seat1RobotBets, seat2PlayerBets,
                seat2RobotBets, seat3PlayerBets, seat3RobotBets, seat4PlayerBets, seat4RobotBets);
        sysWinChips += result.getSysWinChips();
      }
    }
    totalBetChips += bets;
    totalBetCounts += roundCount;
    totalSysWinChips += sysWinChips;

    // 最低收益
    Double sysInputRate = sysWinChips * 1.0 / bets * 100;
    if (sysInputRate.compareTo(minSysInputRate) < 0) {
      minSysInputRate = sysInputRate;
    }
    // 最高收益
    if (sysInputRate.compareTo(maxSysInputRate) > 0) {
      maxSysInputRate = sysInputRate;
    }
    String content =
        "总下分: " + bets + ", 总赢分: " + sysWinChips + ", 系统收益率(赢分/总下注): " + sysInputRate + "%\n";
    writeResult(fileName, true, content);
  }

  private static void writeResult(String fileName, boolean append, String content) {
    File protoFolder = new File(TEST_PATH);

    if (!protoFolder.exists()) {
      protoFolder.mkdirs();
    }

    File file = new File(TEST_PATH + File.separator + fileName);
    BufferedWriter fileWriter = null;

    try {
      fileWriter = new BufferedWriter(new FileWriter(file, true));
      fileWriter.write(content);
    } catch (IOException e) {
      System.out.println("写入失败");
      e.printStackTrace();
    } finally {
      if (fileWriter != null) {
        try {
          fileWriter.flush();
          fileWriter.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
}
