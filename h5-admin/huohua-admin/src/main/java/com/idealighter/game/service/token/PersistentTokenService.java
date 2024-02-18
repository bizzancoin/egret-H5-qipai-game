package com.idealighter.game.service.token;

import com.idealighter.game.dao.manage.mapper.AdminPersistentLoginsMapper;
import com.idealighter.game.dao.manage.po.AdminPersistentLogins;
import com.idealighter.game.dao.manage.po.AdminPersistentLoginsExample;
import com.idealighter.game.service.token.bo.AdminPersistentLoginsBo;
import com.idealighter.game.service.token.convert.AdminPersistentTokenBoConvert;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistentTokenService implements IPersistentTokenService {
  @Autowired
  private AdminPersistentLoginsMapper adminPersistentLoginsMapper;

  @Autowired
  private AdminPersistentTokenBoConvert adminPersistentTokenBoConvert;

  @Override
  public AdminPersistentLoginsBo getBySeries(String series) {
    AdminPersistentLogins pojo = adminPersistentLoginsMapper.selectByPrimaryKey(series);
    return adminPersistentTokenBoConvert.toAdminPersistentLoginsBo(pojo);
  }

  @Override
  public void insert(AdminPersistentLoginsBo bo) {
    AdminPersistentLogins po = adminPersistentTokenBoConvert.toAdminPersistentLogins(bo);
    adminPersistentLoginsMapper.insertSelective(po);
  }

  @Override
  public boolean updateBySeriesAndPlatform(String token, Date lastUsed, String series,
      String platform) {
    AdminPersistentLoginsExample example = new AdminPersistentLoginsExample();
    example.createCriteria().andSeriesEqualTo(series).andPlatformEqualTo(platform);
    AdminPersistentLogins record = new AdminPersistentLogins();
    record.setToken(token);
    record.setLastUsed(lastUsed);
    return adminPersistentLoginsMapper.updateByExampleSelective(record, example) > 0;
  }

  @Override
  public boolean deledeByUserNameAndPlatform(String username, String platform) {
    AdminPersistentLoginsExample example = new AdminPersistentLoginsExample();
    example.createCriteria().andUsernameEqualTo(username).andPlatformEqualTo(platform);
    return adminPersistentLoginsMapper.deleteByExample(example) > 0;
  }
}
