package com.book.mapper;

import com.book.pojo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户Mapper
 */
@Mapper
@Repository
public interface UserMapper extends MyMapper<User> {
    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    @Select("select rid,account,password,name,sex,time,condi,salt from `user` where account=#{account}")
    User selectWholeByAccount(@Param("account") String account);

    /**
     * 根据主键,修改用户权限
     * @param rid
     */
    @Update("update `user` set condi=#{condi} where rid=#{rid}")
    void update(@Param("rid") int rid,@Param("condi") int condi);

    /**
     * 查找用户集数据
     * @param account
     * @return
     */
    @Select("select * from `user` where account like concat('%',concat(#{account},'%'))")
    List<User> selectByAccount(@Param("account") String account);

    @Select("select * from `user` where rid=#{rid}")
    User selectById(@Param("rid") int rid);
}
