package com.idealighter.game.core.dao.generate.mapper;

import com.idealighter.game.core.dao.generate.domain.RechargeRecordDomain;
import com.idealighter.game.core.dao.generate.domain.RechargeRecordDomainExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RechargeRecordDomainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @SelectProvider(type=RechargeRecordDomainSqlProvider.class, method="countByExample")
    long countByExample(RechargeRecordDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @DeleteProvider(type=RechargeRecordDomainSqlProvider.class, method="deleteByExample")
    int deleteByExample(RechargeRecordDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Delete({
        "delete from recharge_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Insert({
        "insert into recharge_record (trade_no, outside_no, ",
        "pay_type, player_id, ",
        "recharge_item_id, gold, ",
        "send_gold, price, ",
        "create_time, pay_time, ",
        "state)",
        "values (#{tradeNo,jdbcType=VARCHAR}, #{outsideNo,jdbcType=VARCHAR}, ",
        "#{payType,jdbcType=TINYINT}, #{playerId,jdbcType=BIGINT}, ",
        "#{rechargeItemId,jdbcType=INTEGER}, #{gold,jdbcType=BIGINT}, ",
        "#{sendGold,jdbcType=BIGINT}, #{price,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{payTime,jdbcType=TIMESTAMP}, ",
        "#{state,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(RechargeRecordDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @InsertProvider(type=RechargeRecordDomainSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(RechargeRecordDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @SelectProvider(type=RechargeRecordDomainSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="trade_no", property="tradeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="outside_no", property="outsideNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.TINYINT),
        @Result(column="player_id", property="playerId", jdbcType=JdbcType.BIGINT),
        @Result(column="recharge_item_id", property="rechargeItemId", jdbcType=JdbcType.INTEGER),
        @Result(column="gold", property="gold", jdbcType=JdbcType.BIGINT),
        @Result(column="send_gold", property="sendGold", jdbcType=JdbcType.BIGINT),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.TINYINT)
    })
    List<RechargeRecordDomain> selectByExample(RechargeRecordDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Select({
        "select",
        "id, trade_no, outside_no, pay_type, player_id, recharge_item_id, gold, send_gold, ",
        "price, create_time, pay_time, state",
        "from recharge_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="trade_no", property="tradeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="outside_no", property="outsideNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.TINYINT),
        @Result(column="player_id", property="playerId", jdbcType=JdbcType.BIGINT),
        @Result(column="recharge_item_id", property="rechargeItemId", jdbcType=JdbcType.INTEGER),
        @Result(column="gold", property="gold", jdbcType=JdbcType.BIGINT),
        @Result(column="send_gold", property="sendGold", jdbcType=JdbcType.BIGINT),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.TINYINT)
    })
    RechargeRecordDomain selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @UpdateProvider(type=RechargeRecordDomainSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RechargeRecordDomain record, @Param("example") RechargeRecordDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @UpdateProvider(type=RechargeRecordDomainSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RechargeRecordDomain record, @Param("example") RechargeRecordDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @UpdateProvider(type=RechargeRecordDomainSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RechargeRecordDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recharge_record
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Update({
        "update recharge_record",
        "set trade_no = #{tradeNo,jdbcType=VARCHAR},",
          "outside_no = #{outsideNo,jdbcType=VARCHAR},",
          "pay_type = #{payType,jdbcType=TINYINT},",
          "player_id = #{playerId,jdbcType=BIGINT},",
          "recharge_item_id = #{rechargeItemId,jdbcType=INTEGER},",
          "gold = #{gold,jdbcType=BIGINT},",
          "send_gold = #{sendGold,jdbcType=BIGINT},",
          "price = #{price,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "pay_time = #{payTime,jdbcType=TIMESTAMP},",
          "state = #{state,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RechargeRecordDomain record);
}