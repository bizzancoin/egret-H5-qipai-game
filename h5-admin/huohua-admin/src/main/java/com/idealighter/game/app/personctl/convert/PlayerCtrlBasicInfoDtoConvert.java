package com.idealighter.game.app.personctl.convert;

import com.idealighter.game.app.personctl.dto.PlayerCtrlBasicInfoDto;
import com.idealighter.game.service.personctl.bo.PlayerCtrlBasicInfoBo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface PlayerCtrlBasicInfoDtoConvert {
  PlayerCtrlBasicInfoDto bo2dto(PlayerCtrlBasicInfoBo bo);

  List<PlayerCtrlBasicInfoDto> bo2dto(List<PlayerCtrlBasicInfoBo> bo);
}
