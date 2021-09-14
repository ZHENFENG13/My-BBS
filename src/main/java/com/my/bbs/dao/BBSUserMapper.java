package com.my.bbs.dao;

import com.my.bbs.entity.BBSUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(BBSUser record);

    int insertSelective(BBSUser record);

    BBSUser selectByPrimaryKey(Long userId);

    List<BBSUser> selectByPrimaryKeys(@Param("userIds") List<Long> userIds);

    BBSUser selectByLoginName(String loginName);

    BBSUser selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    int updateByPrimaryKeySelective(BBSUser record);

    int updateByPrimaryKey(BBSUser record);
}