package com.idealighter.game.service.personctl.bo;

import java.util.List;
import lombok.Data;

@Data
public class PlayerCtrlPlayerListBo {
  private List<PlayerCtrlBasicInfoBo> list;
  private Long total;
}
