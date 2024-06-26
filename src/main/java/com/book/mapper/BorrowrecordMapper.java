package com.book.mapper;

import com.book.pojo.po.Borrowrecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BorrowrecordMapper extends MyMapper<Borrowrecord> {
    /**
     *根据借阅者账号模糊查询借阅记录
     *@params:
     *@return:
     *@date: 22:12 2018/1/10
     **/
    @Select("<script> "+
            "select * from borrowrecord"+
            " <where> "+
            " raccount like concat('%',concat(#{raccount},'%'))"+
            " </where> "+
            " </script> "
    )
    @Results({
            @Result(id=true,column="bid",property = "bid"),
            @Result(column = "rid",property = "rid"),
            @Result(column = "raccount",property = "raccount"),
            @Result(column = "aid",property = "aid"),
            @Result(column = "sid",property = "sid"),
            @Result(column = "time",property = "time"),
            @Result(column = "inttime",property = "inttime"),
            @Result(column="rid",property = "user",
                    one=@One(select="com.book.mapper.UserMapper.selectById")
            ),
            @Result(column="aid",property = "album",
                    one=@One(select="com.book.mapper.AlbumMapper.selectById")
            ),
            @Result(column="sid",property = "subalbum",
                    one=@One(select="com.book.mapper.SubalbumMapper.selectById")
            )
    })
    List<Borrowrecord> selectAllInfoByRaccount(@Param("raccount") String raccount);

    @Select("<script> "+
            "select * from borrowrecord"+
            " <where> "+
            " rid=#{rid}"+
            " </where> "+
            " </script> "
    )
    @Results({
            @Result(id=true,column="bid",property = "bid"),
            @Result(column = "rid",property = "rid"),
            @Result(column = "raccount",property = "raccount"),
            @Result(column = "aid",property = "aid"),
            @Result(column = "sid",property = "sid"),
            @Result(column = "time",property = "time"),
            @Result(column = "inttime",property = "inttime"),
            @Result(column="aid",property = "album",
                    one=@One(select="com.book.mapper.AlbumMapper.selectById")
            ),
            @Result(column="sid",property = "subalbum",
                    one=@One(select="com.book.mapper.SubalbumMapper.selectById")
            )
    })
    List<Borrowrecord> selectAllInfoByrid(@Param("rid") int rid);
}
