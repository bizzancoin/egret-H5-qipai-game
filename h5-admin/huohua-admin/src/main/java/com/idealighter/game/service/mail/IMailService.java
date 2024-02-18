package com.idealighter.game.service.mail;

import com.idealighter.game.app.base.AdminSessionInfo;
import com.idealighter.game.service.mail.bo.MailBo;

import java.util.List;

public interface IMailService {

  List<MailBo> findByPage(Long playerId, int page, int pageSize);

  long count(Long playerId);

  int add(MailBo bo, AdminSessionInfo sessionInfo);

  int delete(List<Integer> ids);
}
