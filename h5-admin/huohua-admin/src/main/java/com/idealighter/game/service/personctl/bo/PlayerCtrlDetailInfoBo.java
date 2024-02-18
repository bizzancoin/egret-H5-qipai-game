package com.idealighter.game.service.personctl.bo;

import java.util.List;
import lombok.Data;

@Data
public class PlayerCtrlDetailInfoBo {
  private PlayerCtrlBasicInfoBo playerInfo;
  private CtlDetailsBo games;

  @Data
  public static class CtlDetailsBo {
    private ShuiHuBo shuihu;
    private LabaBo laba;
    private FqzsBo fqzs;
    private LkbyBo lkby;
    private JcbyBo jcby;
    private SrnnBo srnn;
    private ZjhBo zjh;
    private BlackJackBo blackJack;

    @Data
    public static class ShuiHuBo {
      private Integer gameId;
      private ShuiHuContrlInfoBo control;
      private List<ShuiHuControlBo> difficultList;

      @Data
      public static class ShuiHuContrlInfoBo {
        private Long prizePools;
        private Integer difficulty;
      }

      @Data
      public static class ShuiHuControlBo {
        private int id;
        private String name;
      }
    }

    @Data
    public static class LabaBo {
      private Integer gameId;
      private LaBaContrlInfoBo control;
      private List<FruitSlotControlBo> difficultList;

      @Data
      public static class LaBaContrlInfoBo {
        private Long prizePools;
        private Integer difficulty;
      }

      @Data
      public static class FruitSlotControlBo {
        private int id;
        private String name;
      }
    }

    @Data
    public static class FqzsBo {
      private Integer gameId;
      private FqzsContrlInfoBo control;

      @Data
      public static class FqzsContrlInfoBo {
        private Long prizePools;
        // 系统赢得概率
        private Integer winRatio;
        // 系统输得概率
        private Integer loseRatio;
      }
    }

    @Data
    public static class LkbyBo {
      private Integer gameId;
      private LkbyContrlInfoBo control;
      private List<LkbyControlBo> difficultList;

      @Data
      public static class LkbyContrlInfoBo {
        private Long prizePools;
        private List<FishDifficultyBo> difficultys;

        @Data
        public static class FishDifficultyBo {
          // 鱼倍数下限
          private int multipleLower;
          // 鱼倍数上限
          private int multipleUpper;
          private int difficulty;
        }
      }

      @Data
      public static class LkbyControlBo {
        private int id;
        private String name;
      }
    }

    @Data
    public static class JcbyBo {
      private Integer gameId;
      private JcbyContrlInfoBo control;
      private List<JcbyControlBo> difficultList;

      @Data
      public static class JcbyContrlInfoBo {
        private Long prizePools;
        private List<FishDifficultyBo> difficultys;

        @Data
        public static class FishDifficultyBo {
          // 鱼倍数下限
          private int multipleLower;
          // 鱼倍数上限
          private int multipleUpper;
          private int difficulty;
        }
      }

      @Data
      public static class JcbyControlBo {
        private int id;
        private String name;
      }
    }

    @Data
    public static class SrnnBo {
      private Integer gameId;
      private SrnnContrlInfoBo control;

      @Data
      public static class SrnnContrlInfoBo {
        private Long prizePools;
        // 系统赢得概率
        private Integer winRatio;
        // 系统输得概率
        private Integer loseRatio;
      }
    }

    @Data
    public static class ZjhBo {
      private Integer gameId;
      private ZjhContrlInfoBo control;

      @Data
      public static class ZjhContrlInfoBo {
        private Long prizePools;
        // 系统赢得概率
        private Integer winRatio;
        // 系统输得概率
        private Integer loseRatio;
      }
    }

    @Data
    public static class BlackJackBo {
      private Integer gameId;
      private BlackJackContrlInfoBo control;

      @Data
      public static class BlackJackContrlInfoBo {
        private Long prizePools;
        // 系统赢得概率
        private Integer winRatio;
        // 系统输得概率
        private Integer loseRatio;
      }
    }
  }
}
