package com.idealighter.game.app.games.ddz;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.games.ddz.convert.DdzDtoConvert;
import com.idealighter.game.app.games.ddz.dto.DdzRoomDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.games.ddz.IDdzService;
import com.idealighter.game.service.games.ddz.bo.DdzRoomBo;
import com.idealighter.utils.time.TimeUtil;

@RestController
@RequestMapping("/games/ddz")
@Validated
public class DdzController extends BaseController {
  @Autowired
  private IDdzService ddzService;

  @Autowired
  private DdzDtoConvert ddzDtoConvert;


  /**
   * 获取房间列表.
   * 
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 房间列表.
   */
  @PreAuthorize("hasAuthority('A0201')")
  @GetMapping("/roomlist")
  public Result roomList(@NotNull @RequestParam("page") int page,
      @NotNull @RequestParam("pageSize") int pageSize) {

    ResultPage<DdzRoomBo> resultPage = ddzService.findRoomByPage(page, pageSize);

    List<DdzRoomDto> dtos = ddzDtoConvert.toRoomDtos(resultPage.getList());

    Result result = new Result(true);
    result.getMap().put("list", dtos);

    result.getMap().put("total", resultPage.getTotal());

    return result;
  }

  /**
   * 房间类型.
   * 
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('A0201')")
  @GetMapping("/roomType")
  public Result roomType() {

    ResultPage<RoomTypeBo> resultPage = ddzService.findRoomType();

    Result result = new Result(true);
    result.getMap().put("list", resultPage.getList());
    result.getMap().put("total", resultPage.getTotal());

    return result;
  }

  /**
   * 添加房间.
   * 
   * @param dto 房间.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('A0202')")
  @PostMapping("/save")
  public Result addRoom(@NotNull @RequestBody @Valid DdzRoomDto dto) {
    DdzRoomBo bo = ddzDtoConvert.toRoomBo(dto);
    bo.setTimeCreate(TimeUtil.now());

    ddzService.saveRoom(bo);

    return new Result(true);
  }


  /**
   * 删除房间.
   * 
   * @param id 房间id.
   * @return 删除结果.
   */
  @PreAuthorize("hasAuthority('A0203')")
  @PostMapping("/delete")
  public Result deleteRoom(@NotNull @RequestParam("id") Integer id) {

    ddzService.deleteForCloseRoom(id);

    return new Result(true);
  }

  /**
   * 修改房间状态.
   * 
   * @param id 房间.
   * @param isActive 状态. 0 关闭 1 开启.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('A0204')")
  @PostMapping("/changeStatus")
  public Result changeStatus(@NotNull @RequestParam("id") int id,
      @NotNull @Min(0) @RequestParam("isActive") byte isActive) {
    ddzService.changeStatus(id, isActive);
    return new Result(true);
  }

}
