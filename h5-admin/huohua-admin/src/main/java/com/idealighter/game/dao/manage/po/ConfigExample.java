package com.idealighter.game.dao.manage.po;

import java.util.ArrayList;
import java.util.List;

public class ConfigExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public ConfigExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getOffset() {
    return offset;
  }

  protected abstract static class GeneratedCriteria {
    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andIdIsNull() {
      addCriterion("Id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("Id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("Id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("Id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("Id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("Id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("Id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("Id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("Id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("Id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("Id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("Id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andShortnameIsNull() {
      addCriterion("ShortName is null");
      return (Criteria) this;
    }

    public Criteria andShortnameIsNotNull() {
      addCriterion("ShortName is not null");
      return (Criteria) this;
    }

    public Criteria andShortnameEqualTo(String value) {
      addCriterion("ShortName =", value, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameNotEqualTo(String value) {
      addCriterion("ShortName <>", value, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameGreaterThan(String value) {
      addCriterion("ShortName >", value, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameGreaterThanOrEqualTo(String value) {
      addCriterion("ShortName >=", value, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameLessThan(String value) {
      addCriterion("ShortName <", value, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameLessThanOrEqualTo(String value) {
      addCriterion("ShortName <=", value, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameLike(String value) {
      addCriterion("ShortName like", value, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameNotLike(String value) {
      addCriterion("ShortName not like", value, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameIn(List<String> values) {
      addCriterion("ShortName in", values, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameNotIn(List<String> values) {
      addCriterion("ShortName not in", values, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameBetween(String value1, String value2) {
      addCriterion("ShortName between", value1, value2, "shortname");
      return (Criteria) this;
    }

    public Criteria andShortnameNotBetween(String value1, String value2) {
      addCriterion("ShortName not between", value1, value2, "shortname");
      return (Criteria) this;
    }

    public Criteria andCompanynameIsNull() {
      addCriterion("CompanyName is null");
      return (Criteria) this;
    }

    public Criteria andCompanynameIsNotNull() {
      addCriterion("CompanyName is not null");
      return (Criteria) this;
    }

    public Criteria andCompanynameEqualTo(String value) {
      addCriterion("CompanyName =", value, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameNotEqualTo(String value) {
      addCriterion("CompanyName <>", value, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameGreaterThan(String value) {
      addCriterion("CompanyName >", value, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameGreaterThanOrEqualTo(String value) {
      addCriterion("CompanyName >=", value, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameLessThan(String value) {
      addCriterion("CompanyName <", value, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameLessThanOrEqualTo(String value) {
      addCriterion("CompanyName <=", value, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameLike(String value) {
      addCriterion("CompanyName like", value, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameNotLike(String value) {
      addCriterion("CompanyName not like", value, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameIn(List<String> values) {
      addCriterion("CompanyName in", values, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameNotIn(List<String> values) {
      addCriterion("CompanyName not in", values, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameBetween(String value1, String value2) {
      addCriterion("CompanyName between", value1, value2, "companyname");
      return (Criteria) this;
    }

    public Criteria andCompanynameNotBetween(String value1, String value2) {
      addCriterion("CompanyName not between", value1, value2, "companyname");
      return (Criteria) this;
    }

    public Criteria andTel1IsNull() {
      addCriterion("Tel1 is null");
      return (Criteria) this;
    }

    public Criteria andTel1IsNotNull() {
      addCriterion("Tel1 is not null");
      return (Criteria) this;
    }

    public Criteria andTel1EqualTo(String value) {
      addCriterion("Tel1 =", value, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1NotEqualTo(String value) {
      addCriterion("Tel1 <>", value, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1GreaterThan(String value) {
      addCriterion("Tel1 >", value, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1GreaterThanOrEqualTo(String value) {
      addCriterion("Tel1 >=", value, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1LessThan(String value) {
      addCriterion("Tel1 <", value, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1LessThanOrEqualTo(String value) {
      addCriterion("Tel1 <=", value, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1Like(String value) {
      addCriterion("Tel1 like", value, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1NotLike(String value) {
      addCriterion("Tel1 not like", value, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1In(List<String> values) {
      addCriterion("Tel1 in", values, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1NotIn(List<String> values) {
      addCriterion("Tel1 not in", values, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1Between(String value1, String value2) {
      addCriterion("Tel1 between", value1, value2, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel1NotBetween(String value1, String value2) {
      addCriterion("Tel1 not between", value1, value2, "tel1");
      return (Criteria) this;
    }

    public Criteria andTel2IsNull() {
      addCriterion("Tel2 is null");
      return (Criteria) this;
    }

    public Criteria andTel2IsNotNull() {
      addCriterion("Tel2 is not null");
      return (Criteria) this;
    }

    public Criteria andTel2EqualTo(String value) {
      addCriterion("Tel2 =", value, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2NotEqualTo(String value) {
      addCriterion("Tel2 <>", value, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2GreaterThan(String value) {
      addCriterion("Tel2 >", value, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2GreaterThanOrEqualTo(String value) {
      addCriterion("Tel2 >=", value, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2LessThan(String value) {
      addCriterion("Tel2 <", value, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2LessThanOrEqualTo(String value) {
      addCriterion("Tel2 <=", value, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2Like(String value) {
      addCriterion("Tel2 like", value, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2NotLike(String value) {
      addCriterion("Tel2 not like", value, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2In(List<String> values) {
      addCriterion("Tel2 in", values, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2NotIn(List<String> values) {
      addCriterion("Tel2 not in", values, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2Between(String value1, String value2) {
      addCriterion("Tel2 between", value1, value2, "tel2");
      return (Criteria) this;
    }

    public Criteria andTel2NotBetween(String value1, String value2) {
      addCriterion("Tel2 not between", value1, value2, "tel2");
      return (Criteria) this;
    }

    public Criteria andQq1IsNull() {
      addCriterion("QQ1 is null");
      return (Criteria) this;
    }

    public Criteria andQq1IsNotNull() {
      addCriterion("QQ1 is not null");
      return (Criteria) this;
    }

    public Criteria andQq1EqualTo(String value) {
      addCriterion("QQ1 =", value, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1NotEqualTo(String value) {
      addCriterion("QQ1 <>", value, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1GreaterThan(String value) {
      addCriterion("QQ1 >", value, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1GreaterThanOrEqualTo(String value) {
      addCriterion("QQ1 >=", value, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1LessThan(String value) {
      addCriterion("QQ1 <", value, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1LessThanOrEqualTo(String value) {
      addCriterion("QQ1 <=", value, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1Like(String value) {
      addCriterion("QQ1 like", value, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1NotLike(String value) {
      addCriterion("QQ1 not like", value, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1In(List<String> values) {
      addCriterion("QQ1 in", values, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1NotIn(List<String> values) {
      addCriterion("QQ1 not in", values, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1Between(String value1, String value2) {
      addCriterion("QQ1 between", value1, value2, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq1NotBetween(String value1, String value2) {
      addCriterion("QQ1 not between", value1, value2, "qq1");
      return (Criteria) this;
    }

    public Criteria andQq2IsNull() {
      addCriterion("QQ2 is null");
      return (Criteria) this;
    }

    public Criteria andQq2IsNotNull() {
      addCriterion("QQ2 is not null");
      return (Criteria) this;
    }

    public Criteria andQq2EqualTo(String value) {
      addCriterion("QQ2 =", value, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2NotEqualTo(String value) {
      addCriterion("QQ2 <>", value, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2GreaterThan(String value) {
      addCriterion("QQ2 >", value, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2GreaterThanOrEqualTo(String value) {
      addCriterion("QQ2 >=", value, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2LessThan(String value) {
      addCriterion("QQ2 <", value, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2LessThanOrEqualTo(String value) {
      addCriterion("QQ2 <=", value, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2Like(String value) {
      addCriterion("QQ2 like", value, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2NotLike(String value) {
      addCriterion("QQ2 not like", value, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2In(List<String> values) {
      addCriterion("QQ2 in", values, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2NotIn(List<String> values) {
      addCriterion("QQ2 not in", values, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2Between(String value1, String value2) {
      addCriterion("QQ2 between", value1, value2, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq2NotBetween(String value1, String value2) {
      addCriterion("QQ2 not between", value1, value2, "qq2");
      return (Criteria) this;
    }

    public Criteria andQq3IsNull() {
      addCriterion("QQ3 is null");
      return (Criteria) this;
    }

    public Criteria andQq3IsNotNull() {
      addCriterion("QQ3 is not null");
      return (Criteria) this;
    }

    public Criteria andQq3EqualTo(String value) {
      addCriterion("QQ3 =", value, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3NotEqualTo(String value) {
      addCriterion("QQ3 <>", value, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3GreaterThan(String value) {
      addCriterion("QQ3 >", value, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3GreaterThanOrEqualTo(String value) {
      addCriterion("QQ3 >=", value, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3LessThan(String value) {
      addCriterion("QQ3 <", value, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3LessThanOrEqualTo(String value) {
      addCriterion("QQ3 <=", value, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3Like(String value) {
      addCriterion("QQ3 like", value, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3NotLike(String value) {
      addCriterion("QQ3 not like", value, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3In(List<String> values) {
      addCriterion("QQ3 in", values, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3NotIn(List<String> values) {
      addCriterion("QQ3 not in", values, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3Between(String value1, String value2) {
      addCriterion("QQ3 between", value1, value2, "qq3");
      return (Criteria) this;
    }

    public Criteria andQq3NotBetween(String value1, String value2) {
      addCriterion("QQ3 not between", value1, value2, "qq3");
      return (Criteria) this;
    }

    public Criteria andEmailIsNull() {
      addCriterion("Email is null");
      return (Criteria) this;
    }

    public Criteria andEmailIsNotNull() {
      addCriterion("Email is not null");
      return (Criteria) this;
    }

    public Criteria andEmailEqualTo(String value) {
      addCriterion("Email =", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailNotEqualTo(String value) {
      addCriterion("Email <>", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailGreaterThan(String value) {
      addCriterion("Email >", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailGreaterThanOrEqualTo(String value) {
      addCriterion("Email >=", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailLessThan(String value) {
      addCriterion("Email <", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailLessThanOrEqualTo(String value) {
      addCriterion("Email <=", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailLike(String value) {
      addCriterion("Email like", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailNotLike(String value) {
      addCriterion("Email not like", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailIn(List<String> values) {
      addCriterion("Email in", values, "email");
      return (Criteria) this;
    }

    public Criteria andEmailNotIn(List<String> values) {
      addCriterion("Email not in", values, "email");
      return (Criteria) this;
    }

    public Criteria andEmailBetween(String value1, String value2) {
      addCriterion("Email between", value1, value2, "email");
      return (Criteria) this;
    }

    public Criteria andEmailNotBetween(String value1, String value2) {
      addCriterion("Email not between", value1, value2, "email");
      return (Criteria) this;
    }

    public Criteria andIcpIsNull() {
      addCriterion("ICP is null");
      return (Criteria) this;
    }

    public Criteria andIcpIsNotNull() {
      addCriterion("ICP is not null");
      return (Criteria) this;
    }

    public Criteria andIcpEqualTo(String value) {
      addCriterion("ICP =", value, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpNotEqualTo(String value) {
      addCriterion("ICP <>", value, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpGreaterThan(String value) {
      addCriterion("ICP >", value, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpGreaterThanOrEqualTo(String value) {
      addCriterion("ICP >=", value, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpLessThan(String value) {
      addCriterion("ICP <", value, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpLessThanOrEqualTo(String value) {
      addCriterion("ICP <=", value, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpLike(String value) {
      addCriterion("ICP like", value, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpNotLike(String value) {
      addCriterion("ICP not like", value, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpIn(List<String> values) {
      addCriterion("ICP in", values, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpNotIn(List<String> values) {
      addCriterion("ICP not in", values, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpBetween(String value1, String value2) {
      addCriterion("ICP between", value1, value2, "icp");
      return (Criteria) this;
    }

    public Criteria andIcpNotBetween(String value1, String value2) {
      addCriterion("ICP not between", value1, value2, "icp");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeIsNull() {
      addCriterion("StatisticalCode is null");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeIsNotNull() {
      addCriterion("StatisticalCode is not null");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeEqualTo(String value) {
      addCriterion("StatisticalCode =", value, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeNotEqualTo(String value) {
      addCriterion("StatisticalCode <>", value, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeGreaterThan(String value) {
      addCriterion("StatisticalCode >", value, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeGreaterThanOrEqualTo(String value) {
      addCriterion("StatisticalCode >=", value, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeLessThan(String value) {
      addCriterion("StatisticalCode <", value, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeLessThanOrEqualTo(String value) {
      addCriterion("StatisticalCode <=", value, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeLike(String value) {
      addCriterion("StatisticalCode like", value, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeNotLike(String value) {
      addCriterion("StatisticalCode not like", value, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeIn(List<String> values) {
      addCriterion("StatisticalCode in", values, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeNotIn(List<String> values) {
      addCriterion("StatisticalCode not in", values, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeBetween(String value1, String value2) {
      addCriterion("StatisticalCode between", value1, value2, "statisticalcode");
      return (Criteria) this;
    }

    public Criteria andStatisticalcodeNotBetween(String value1, String value2) {
      addCriterion("StatisticalCode not between", value1, value2, "statisticalcode");
      return (Criteria) this;
    }
  }

  /**
   */
  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  public static class Criterion {
    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}
