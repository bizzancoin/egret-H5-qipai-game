package com.idealighter.game.core.dao.generate.mapper;

import com.idealighter.game.core.dao.generate.domain.AppleInAppPayReceiptDataDomain;
import com.idealighter.game.core.dao.generate.domain.AppleInAppPayReceiptDataDomainExample;
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

public interface AppleInAppPayReceiptDataDomainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @SelectProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="countByExample")
    long countByExample(AppleInAppPayReceiptDataDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @DeleteProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="deleteByExample")
    int deleteByExample(AppleInAppPayReceiptDataDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Delete({
        "delete from apple_in_app_pay_receipt_data",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Insert({
        "insert into apple_in_app_pay_receipt_data (md5_check, create_time, ",
        "receipt_data)",
        "values (#{md5Check,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{receiptData,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AppleInAppPayReceiptDataDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @InsertProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(AppleInAppPayReceiptDataDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @SelectProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="md5_check", property="md5Check", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="receipt_data", property="receiptData", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<AppleInAppPayReceiptDataDomain> selectByExampleWithBLOBs(AppleInAppPayReceiptDataDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @SelectProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="md5_check", property="md5Check", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AppleInAppPayReceiptDataDomain> selectByExample(AppleInAppPayReceiptDataDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Select({
        "select",
        "id, md5_check, create_time, receipt_data",
        "from apple_in_app_pay_receipt_data",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="md5_check", property="md5Check", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="receipt_data", property="receiptData", jdbcType=JdbcType.LONGVARCHAR)
    })
    AppleInAppPayReceiptDataDomain selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @UpdateProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AppleInAppPayReceiptDataDomain record, @Param("example") AppleInAppPayReceiptDataDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @UpdateProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") AppleInAppPayReceiptDataDomain record, @Param("example") AppleInAppPayReceiptDataDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @UpdateProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AppleInAppPayReceiptDataDomain record, @Param("example") AppleInAppPayReceiptDataDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @UpdateProvider(type=AppleInAppPayReceiptDataDomainSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AppleInAppPayReceiptDataDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Update({
        "update apple_in_app_pay_receipt_data",
        "set md5_check = #{md5Check,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "receipt_data = #{receiptData,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(AppleInAppPayReceiptDataDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table apple_in_app_pay_receipt_data
     *
     * @mbg.generated Tue Aug 14 14:13:21 CST 2018
     */
    @Update({
        "update apple_in_app_pay_receipt_data",
        "set md5_check = #{md5Check,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AppleInAppPayReceiptDataDomain record);
}