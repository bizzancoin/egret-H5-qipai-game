package com.idealighter.game.service.notice;

import com.idealighter.game.dao.manage.mapper.NoticeMapper;
import com.idealighter.game.dao.manage.po.Notice;
import com.idealighter.game.dao.manage.po.NoticeExample;
import com.idealighter.game.service.notice.bo.NoticeBo;
import com.idealighter.game.service.notice.convert.NoticeBoConvert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService implements INoticeService {

  @Autowired
  private NoticeMapper noticeMapper;

  @Autowired
  private NoticeBoConvert noticeBoConvert;



  @Override
  public List<NoticeBo> findAllNotice() {

    NoticeExample select = new NoticeExample();

    select.setOrderByClause("start_time desc, end_time desc, id desc");

    List<Notice> notices = noticeMapper.selectByExample(select);

    return noticeBoConvert.toNoticeBos(notices);
  }


  @Override
  public void save(NoticeBo bo) {
    Notice notice = noticeBoConvert.toNotice(bo);
    if (notice.getId() != null) {
      noticeMapper.updateByPrimaryKey(notice);
    } else {
      noticeMapper.insert(notice);
    }
  }

  @Override
  public void delete(int id) {
    noticeMapper.deleteByPrimaryKey(id);
  }


}
