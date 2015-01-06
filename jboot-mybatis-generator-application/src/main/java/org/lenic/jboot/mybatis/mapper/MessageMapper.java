package org.lenic.jboot.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.lenic.jboot.mybatis.model.Message;
import org.lenic.jboot.mybatis.model.MessageExample;

public interface MessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @SelectProvider(type=MessageSqlProvider.class, method="countByExample")
    int countByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @DeleteProvider(type=MessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @Delete({
        "delete from MESSAGE",
        "where ID = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @Insert({
        "insert into MESSAGE (ID, AUTHOR, ",
        "EMAIL, CONTENT, ",
        "CREATED)",
        "values (#{id,jdbcType=CHAR}, #{author,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{created,jdbcType=TIMESTAMP})"
    })
    int insert(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @InsertProvider(type=MessageSqlProvider.class, method="insertSelective")
    int insertSelective(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @SelectProvider(type=MessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="AUTHOR", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATED", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Message> selectByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, AUTHOR, EMAIL, CONTENT, CREATED",
        "from MESSAGE",
        "where ID = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="AUTHOR", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATED", property="created", jdbcType=JdbcType.TIMESTAMP)
    })
    Message selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MESSAGE
     *
     * @mbggenerated
     */
    @Update({
        "update MESSAGE",
        "set AUTHOR = #{author,jdbcType=VARCHAR},",
          "EMAIL = #{email,jdbcType=VARCHAR},",
          "CONTENT = #{content,jdbcType=VARCHAR},",
          "CREATED = #{created,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(Message record);
}