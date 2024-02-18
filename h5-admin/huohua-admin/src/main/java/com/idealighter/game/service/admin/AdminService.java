package com.idealighter.game.service.admin;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.common.constant.AdminStatusConstant;
import com.idealighter.game.common.constant.AdminTypeConstant;
import com.idealighter.game.configuration.security.model.DlUserDetail;
import com.idealighter.game.dao.manage.mapper.AdminMapper;
import com.idealighter.game.dao.manage.mapper.PermissionMapper;
import com.idealighter.game.dao.manage.po.Admin;
import com.idealighter.game.dao.manage.po.AdminExample;
import com.idealighter.game.dao.manage.po.Permission;
import com.idealighter.game.dao.manage.po.PermissionExample;
import com.idealighter.game.service.admin.bo.AdminBo;
import com.idealighter.game.service.admin.bo.PermissionBo;
import com.idealighter.game.service.admin.convert.AdminBoConvert;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.crypto.MD5Utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

  @Autowired
  private AdminMapper adminMapper;

  @Autowired
  private PermissionMapper permissionMapper;

  @Autowired
  private AdminBoConvert adminBoConvert;

  @Autowired
  private SessionRegistry sessionRegistry;

  @Autowired
  private PersistentTokenRepository persistentTokenRepository;

  @Override
  public ResultPage<AdminBo> findByPage(String username, Integer page, Integer pageSize) {

    AdminExample select = new AdminExample();
    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);
    select.setOrderByClause("id desc");
    AdminExample.Criteria criteria = select.createCriteria();

    if (EmptyUtil.stringIsNotEmpty(username)) {
      criteria.andUsernameEqualTo(username);
    }

    List<Admin> admins = adminMapper.selectByExample(select);

    List<AdminBo> adminBos = adminBoConvert.toAdminBos(admins);


    long total = adminMapper.countByExample(select);

    ResultPage<AdminBo> resultPage = new ResultPage<>();
    resultPage.setList(adminBos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Override
  public void resetPassword(Integer adminId, String password) {

    String salt = MD5Utils.createSalt();
    password = MD5Utils.getPassword(password, salt);

    Admin updateAdmin = new Admin();
    updateAdmin.setId(adminId);
    updateAdmin.setSalt(salt);
    updateAdmin.setPassword(password);

    int out = adminMapper.updateByPrimaryKeySelective(updateAdmin);

    IdeaAssert.isTrue(out > 0, ErrorCode.INTERNAL_SERVER_ERROR);

    invalidateSession(adminId);
  }

  @Override
  public void changeStatus(Integer adminId, Byte status) {
    Admin updateAdmin = new Admin();
    updateAdmin.setId(adminId);
    updateAdmin.setStatus(status);


    int out = adminMapper.updateByPrimaryKeySelective(updateAdmin);

    IdeaAssert.isTrue(out > 0, ErrorCode.INTERNAL_SERVER_ERROR);

    invalidateSession(adminId);
  }

  @Override
  public void add(String username, String password, String nickname) {
    Admin admin = new Admin();
    String salt = MD5Utils.createSalt();
    password = MD5Utils.getPassword(password, salt);
    admin.setNickname(nickname);
    admin.setPassword(password);
    admin.setRole(AdminTypeConstant.ADMIN);
    admin.setSalt(salt);
    admin.setStatus(AdminStatusConstant.NORMAL);
    admin.setUsername(username);
    admin.setPermission("[\"B\",\"B01\",\"B0101\"]");

    int out = adminMapper.insertSelective(admin);

    IdeaAssert.isTrue(out > 0, ErrorCode.INTERNAL_SERVER_ERROR);
  }

  @Override
  public void update(Integer adminId, String nickname) {
    Admin admin = new Admin();
    admin.setId(adminId);
    admin.setNickname(nickname);

    int out = adminMapper.updateByPrimaryKeySelective(admin);
    IdeaAssert.isTrue(out > 0, ErrorCode.INTERNAL_SERVER_ERROR);
  }

  @Override
  public List<PermissionBo> findPermission() {
    List<Permission> permissions = permissionMapper.selectByExample(new PermissionExample());
    return adminBoConvert.toPermissionBos(permissions);
  }

  @Override
  public void updatePermission(Integer adminId, String permission) {
    Admin admin = new Admin();
    admin.setId(adminId);
    admin.setPermission(permission);

    int out = adminMapper.updateByPrimaryKeySelective(admin);
    IdeaAssert.isTrue(out > 0, ErrorCode.INTERNAL_SERVER_ERROR);

    invalidateSession(adminId);
  }

  private void invalidateSession(Integer adminId) {
    List<Object> principals = sessionRegistry.getAllPrincipals();
    for (Object principal : principals) {
      if (principal instanceof DlUserDetail) {
        final DlUserDetail loggedUser = (DlUserDetail) principal;
        if (adminId.equals(loggedUser.getAccount().getId())) {
          List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
          if (null != sessionsInfo && sessionsInfo.size() > 0) {
            for (SessionInformation sessionInformation : sessionsInfo) {
              sessionInformation.expireNow();
              // sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
            }
          }
          persistentTokenRepository.removeUserTokens(loggedUser.getUsername());
        }
      }
    }
  }

}
