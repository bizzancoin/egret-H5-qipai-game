package com.idealighter.game.service.games.ddz.convert;

import java.util.List;
import org.mapstruct.Mapper;
import com.idealighter.game.dao.dic.po.DdzRoom;
import com.idealighter.game.service.games.ddz.bo.DdzRoomBo;
import com.idealighter.utils.json.JsonUtil;

@Mapper(imports = JsonUtil.class)
public interface DdzBoConvert {
  DdzRoomBo toRoomBo(DdzRoom pojo);

  List<DdzRoomBo> toRoomBos(List<DdzRoom> pojos);

  DdzRoom toRoom(DdzRoomBo bo);
}

