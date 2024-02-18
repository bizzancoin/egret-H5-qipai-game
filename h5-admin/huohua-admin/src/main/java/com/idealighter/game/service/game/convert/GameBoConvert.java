package com.idealighter.game.service.game.convert;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import com.idealighter.game.app.game.dto.IdName;
import com.idealighter.game.dao.dic.po.BaccaratRoomType;
import com.idealighter.game.dao.dic.po.BairenniuniuRoomType;
import com.idealighter.game.dao.dic.po.DdzRoomType;
import com.idealighter.game.dao.dic.po.Games;
import com.idealighter.game.dao.dic.po.JcbyRoomType;
import com.idealighter.game.service.game.bo.GameBo;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.games.baccarat.bo.BaccaratRoomBo;
import com.idealighter.game.service.games.brnn.bo.BaiRenNiuniuRoomBo;
import com.idealighter.game.service.games.ddz.bo.DdzRoomBo;
import com.idealighter.game.service.games.jcby.bo.JcbyRoomBo;
import com.idealighter.game.service.robotcfg.bo.RoomConfigBo;

@Mapper
public interface GameBoConvert {
  RoomTypeBo toRoomTypeBo(JcbyRoomType jcbyRoomType);

  RoomTypeBo toRoomTypeBo(BaccaratRoomType baccaratRoomType);
  
  RoomTypeBo toRoomTypeBo(BairenniuniuRoomType roomType);
  
  RoomTypeBo toRoomTypeBo(DdzRoomType roomType);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RoomTypeBo> toRoomTypeJcbyBos(List<JcbyRoomType> jcbyRoomTypes);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RoomTypeBo> toRoomTypeBaiRenNiuniuBos(List<BairenniuniuRoomType> niuniuRoomTypes);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RoomTypeBo> toRoomTypeDdzBos(List<DdzRoomType> roomTypes);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RoomTypeBo> toRoomTypeBaccaratBos(List<BaccaratRoomType> baccaratRoomTypes);

  GameBo toGameBo(Games games);

  List<GameBo> toGameBos(List<Games> gamesList);


  IdName toIdNameBaiRenNiuniu(BaiRenNiuniuRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<IdName> toIdNameBaiRenNiunius(List<BaiRenNiuniuRoomBo> bos);


  IdName toIdNameJcby(JcbyRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<IdName> toIdNameJcbys(List<JcbyRoomBo> bos);

  IdName toIdNameBaccarat(BaccaratRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<IdName> toIdNameBaccarats(List<BaccaratRoomBo> bos);
  
  IdName toIdNameDdz(DdzRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<IdName> toIdNameDdz(List<DdzRoomBo> bos);

  @Mapping(target = "hasRobot", constant = "false")
  RoomConfigBo toRoomConfigBoBaiRenNiuniu(BaiRenNiuniuRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RoomConfigBo> toRoomConfigBoBaiRenNiunius(List<BaiRenNiuniuRoomBo> bos);

  @Mapping(target = "hasRobot", constant = "false")
  RoomConfigBo toRoomConfigBoJcby(JcbyRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RoomConfigBo> toRoomConfigBoJcbys(List<JcbyRoomBo> bos);



  @Mapping(target = "hasRobot", constant = "false")
  RoomConfigBo toRoomConfigBoBaccarat(BaccaratRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RoomConfigBo> toRoomConfigBoBaccarats(List<BaccaratRoomBo> bos);

  @Mapping(target = "hasRobot", constant = "false")
  RoomConfigBo toRoomConfigBoDdz(DdzRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RoomConfigBo> toRoomConfigBoDdz(List<DdzRoomBo> bos);


}
