package com.idealighter.game.admin.personctl.controller.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.idealighter.game.admin.common.controller.dto.JcbyControlDto;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.jcby.struct.JcbyDifficulty;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.prizepool.struct.control.FishDifficulty;
import com.idealighter.game.core.service.prizepool.struct.control.JcbyControl;
import com.idealighter.game.core.service.prizepool.struct.player.PlayerPrizePool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PlayerCtrlDetailInfoDto {
  private PlayerCtrlBasicInfoDto playerInfo;
  private CtlDetailsDto games;

  public PlayerCtrlDetailInfoDto(PlayerBo domain) {
    this.playerInfo = new PlayerCtrlBasicInfoDto(domain);
    this.games = new CtlDetailsDto(domain.prizePools());
  }

  public PlayerCtrlDetailInfoDto(Player player) {
    this.playerInfo = new PlayerCtrlBasicInfoDto(player);
    this.games = new CtlDetailsDto(player.prizePools());
  }

  @Data
  public static class CtlDetailsDto {
    private JcbyDto jcby;


    /**
     * 构造函数 .
     * 
     * @param prizePool 所有奖池.
     */
    public CtlDetailsDto(Map<Integer, PlayerPrizePool> prizePool) {
      if (prizePool != null) {
        this.jcby = new JcbyDto(prizePool.get(Game.JCBY.getType()));

      }
    }



    @Data
    public static class JcbyDto {
      private Integer gameId;
      private JcbyContrlInfoDto control;
      private List<JcbyControlDto> difficultList;

      /**
       * 构造函数 .
       * 
       * @param prizePool 奖池.
       */
      public JcbyDto(PlayerPrizePool prizePool) {
        this.gameId = Game.JCBY.getType();
        if (prizePool != null) {
          this.control = new JcbyContrlInfoDto(prizePool);
        }
        difficultList = new ArrayList<JcbyControlDto>();
        for (JcbyDifficulty item : JcbyDifficulty.values()) {
          difficultList.add(new JcbyControlDto(item.getDifficulty(), item.getName()));
        }
      }

      @Data
      public static class JcbyContrlInfoDto {
        private Long prizePools;
        private List<FishDifficulty> difficultys;

        /**
         * 构造函数 .
         * 
         * @param prizePool 奖池.
         */
        public JcbyContrlInfoDto(PlayerPrizePool prizePool) {
          this.prizePools = prizePool.getPrize().get();
          JcbyControl jcbyControl =
              JSON.parseObject(prizePool.getControl(), new TypeReference<JcbyControl>() {});
          this.difficultys = jcbyControl.getDifficultys();
        }
      }
    }
  }
}
