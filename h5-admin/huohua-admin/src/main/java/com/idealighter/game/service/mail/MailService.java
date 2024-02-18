package com.idealighter.game.service.mail;

import com.idealighter.game.app.base.AdminSessionInfo;
import com.idealighter.game.dao.data.mapper.MailMapper;
import com.idealighter.game.dao.data.mapper.PlayerMainMapper;
import com.idealighter.game.dao.data.po.Mail;
import com.idealighter.game.dao.data.po.MailExample;
import com.idealighter.game.dao.data.po.PlayerMain;
import com.idealighter.game.service.mail.bo.MailBo;
import com.idealighter.game.service.mail.convert.MailBoConvert;
import com.idealighter.utils.time.TimeUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {

  @Autowired
  private MailMapper mailMapper;

  @Autowired
  private PlayerMainMapper playerMainMapper;

  @Autowired
  private MailBoConvert mailBoConvert;

  @Override
  public List<MailBo> findByPage(Long playerId, int page, int pageSize) {

    MailExample select = new MailExample();
    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);
    if (playerId != null) {
      select.createCriteria().andPlayerIdEqualTo(playerId);
    }

    select.setOrderByClause("create_time desc");

    List<Mail> mails = mailMapper.selectByExampleWithBLOBs(select);

    return mailBoConvert.toMailBos(mails);
  }

  @Override
  public long count(Long playerId) {
    MailExample count = new MailExample();
    if (playerId != null) {
      count.createCriteria().andPlayerIdEqualTo(playerId);
    }
    return mailMapper.countByExample(count);
  }

  @Override
  public int add(MailBo bo, AdminSessionInfo sessionInfo) {
    if (bo.getPlayerId() == 0L) {
      bo.setPlayerSuperId(0L);
      bo.setPlayerName("系统");
    } else {
      PlayerMain playerMain = playerMainMapper.selectByPrimaryKey(bo.getPlayerId());
      bo.setPlayerSuperId(playerMain.getSuperId());
      bo.setPlayerName(playerMain.getPlayerName());
    }

    bo.setFromPlayerId(0L);
    bo.setFromPlayerName("系统");
    bo.setFromPlayerSuperId(0L);
    bo.setGold(0L);
    bo.setIsRead(false);
    bo.setIsRecGold(false);
    bo.setCreateTime(TimeUtil.now());

    Mail mail = mailBoConvert.toMail(bo);

    return mailMapper.insertSelective(mail);
  }

  @Override
  public int delete(List<Integer> ids) {
    MailExample delete = new MailExample();
    delete.createCriteria().andIdIn(ids);

    return mailMapper.deleteByExample(delete);
  }

}
