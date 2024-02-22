package com.book.mapper;

import com.book.pojo.po.Subalbum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubalbumMapper extends MyMapper<Subalbum> {
    @Select("select * from t_subalbum where aid=#{aid}")
    List<Subalbum> selectByAid(@Param("aid") int aid);

    @Select("select * from t_subalbum where sid=#{sid}")
    Subalbum selectById(@Param("sid") int sid);

    @Select("select * from t_subalbum where number=#{number}")
    Subalbum selectByNumber(@Param("number") String number);
}
