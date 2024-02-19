package com.idealighter.game.core.service.games.jcby.struct;

public enum JcbyDifficulty {
  /**
   * HARDEST 最难，用户收益(-99%,-90%).
   */
  HARDEST(-2, "最难", 100000, 1000000),
  /**
   * DIFFICULT 困难，用户收益(-90%,-60%).
   */
  DIFFICULT(-1, "困难", 25000, 100000),
  /**
   * 普通，用户收益(-40%,-20%).
   */
  NORMAL(0, "普通", 12500, 16666),
  /**
   * 简单，用户收益(20%,60%).
   */
  EASY(1, "简单", 6250, 8333),
  /**
   * 轻松，用户收益(60%,90%).
   */
  EASIEST(2, "轻松", 5263, 6250);

  // 难度
  private final int difficulty;
  // 名称
  private final String name;
  private final int lower;
  private final int upper;

  public int getDifficulty() {
    return difficulty;
  }

  public String getName() {
    return name;
  }

  public int getLower() {
    return lower;
  }

  public int getUpper() {
    return upper;
  }

  /**
   * 构造函数.
   * 
   * @param difficulty 难度.
   * @param name 名字.
   * @param lower 最低.
   * @param upper 最高.
   */
  private JcbyDifficulty(int difficulty, String name, int lower, int upper) {
    this.difficulty = difficulty;
    this.name = name;
    this.lower = lower;
    this.upper = upper;
  }

  /**
   * 获取难度.
   * 
   * @param difficulty 难度值.
   * @return 难度》
   */
  public static JcbyDifficulty get(int difficulty) {
    for (JcbyDifficulty d : values()) {
      if (d.difficulty == difficulty) {
        return d;
      }
    }

    return null;
  }

}
