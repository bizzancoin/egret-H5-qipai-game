package com.idealighter.game.service.account;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.common.constant.AdminStatusConstant;
import com.idealighter.game.dao.manage.mapper.AdminMapper;
import com.idealighter.game.dao.manage.po.Admin;
import com.idealighter.game.dao.manage.po.AdminExample;
import com.idealighter.game.service.admin.bo.AdminBo;
import com.idealighter.game.service.admin.convert.AdminBoConvert;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.crypto.MD5Utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

  @Autowired
  private AdminMapper adminMapper;

  @Autowired
  private AdminBoConvert adminBoConvert;



  private Admin selectByUserName(String username) {
    AdminExample example = new AdminExample();
    example.createCriteria().andUsernameEqualTo(username);
    example.setLimit(1);

    Admin admin = null;
    List<Admin> admins = adminMapper.selectByExample(example);
    if (EmptyUtil.listIsNotEmpty(admins)) {
      admin = admins.get(0);
    }
    return admin;
  }
  
  @Override
  public AdminBo findByUserName(String username) {
    Admin admin = selectByUserName(username);
    return adminBoConvert.toAdminBo(admin);
  }

  @Override
  public AdminBo loginByUserPwd(String username, String password) {
    Admin admin = selectByUserName(username);

    IdeaAssert.notNull(admin, ErrorCode.UNAUTHORIZED);

    IdeaAssert.isTrue(admin.getStatus().byteValue() == AdminStatusConstant.NORMAL,
        ErrorCode.ACCOUNT_LOCK);

    IdeaAssert.isTrue(MD5Utils.getPassword(password, admin.getSalt()).equals(admin.getPassword()),
        ErrorCode.ACCOUNT_FAIL);


    return adminBoConvert.toAdminBo(admin);
  }

  @Override
  public AdminBo findById(Integer adminId) {
    Admin admin = adminMapper.selectByPrimaryKey(adminId);
    IdeaAssert.notNull(admin, ErrorCode.UNAUTHORIZED);

    IdeaAssert.isTrue(admin.getStatus().byteValue() == AdminStatusConstant.NORMAL,
        ErrorCode.ACCOUNT_LOCK);

    return adminBoConvert.toAdminBo(admin);
  }

  @Override
  public void changePwd(Integer adminId, String newPassword, String oldPassword) {
    Admin admin = adminMapper.selectByPrimaryKey(adminId);
    IdeaAssert.isTrue(
        admin != null
            && MD5Utils.getPassword(oldPassword, admin.getSalt()).equals(admin.getPassword()),
        ErrorCode.ACCOUNT_OLD_PWD_FAIL);
    String salt = MD5Utils.createSalt();
    newPassword = MD5Utils.getPassword(newPassword, salt);

    Admin updateAdmin = new Admin();
    updateAdmin.setId(adminId);
    updateAdmin.setSalt(salt);
    updateAdmin.setPassword(newPassword);

    int out = adminMapper.updateByPrimaryKeySelective(updateAdmin);

    IdeaAssert.isTrue(out > 0, ErrorCode.INTERNAL_SERVER_ERROR);

  }

}
