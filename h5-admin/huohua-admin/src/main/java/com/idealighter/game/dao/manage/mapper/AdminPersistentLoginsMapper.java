package com.idealighter.game.dao.manage.mapper;

import com.idealighter.game.dao.manage.po.AdminPersistentLogins;
import com.idealighter.game.dao.manage.po.AdminPersistentLoginsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminPersistentLoginsMapper {
    long countByExample(AdminPersistentLoginsExample example);

    int deleteByExample(AdminPersistentLoginsExample example);

    int deleteByPrimaryKey(String series);

    int insert(AdminPersistentLogins record);

    int insertSelective(AdminPersistentLogins record);

    List<AdminPersistentLogins> selectByExample(AdminPersistentLoginsExample example);

    AdminPersistentLogins selectByPrimaryKey(String series);

    int updateByExampleSelective(@Param("record") AdminPersistentLogins record, @Param("example") AdminPersistentLoginsExample example);

    int updateByExample(@Param("record") AdminPersistentLogins record, @Param("example") AdminPersistentLoginsExample example);

    int updateByPrimaryKeySelective(AdminPersistentLogins record);

    int updateByPrimaryKey(AdminPersistentLogins record);
}