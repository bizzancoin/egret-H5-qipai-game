package com.idealighter.game.app.admin.convert;

import com.idealighter.game.app.admin.dto.AdminDto;
import com.idealighter.game.service.admin.bo.AdminBo;
import com.idealighter.utils.json.JsonUtil;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = JsonUtil.class)
public interface AdminDtoConvert {
  @Mapping(target = "permission",
      expression = "java(JsonUtil.fromJsonToList(bo.getPermission(), String.class))")
  AdminDto toAdminDto(AdminBo bo);

  List<AdminDto> toAdminDtos(List<AdminBo> bos);

  @Mapping(target = "permission", ignore = true)
  AdminBo toAdminBo(AdminDto dto);
}
