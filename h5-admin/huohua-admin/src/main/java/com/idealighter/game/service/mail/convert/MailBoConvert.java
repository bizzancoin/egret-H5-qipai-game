package com.idealighter.game.service.mail.convert;

import com.idealighter.game.dao.data.po.Mail;
import com.idealighter.game.service.mail.bo.MailBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface MailBoConvert {

  Mail toMail(MailBo mailBo);

  MailBo toMailBo(Mail mail);

  List<MailBo> toMailBos(List<Mail> mails);
}
