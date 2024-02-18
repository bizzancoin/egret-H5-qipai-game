package com.idealighter.game.dao.data.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlackListExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public BlackListExample() {
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
      addCriterion("id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andLimittypeIsNull() {
      addCriterion("limitType is null");
      return (Criteria) this;
    }

    public Criteria andLimittypeIsNotNull() {
      addCriterion("limitType is not null");
      return (Criteria) this;
    }

    public Criteria andLimittypeEqualTo(String value) {
      addCriterion("limitType =", value, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeNotEqualTo(String value) {
      addCriterion("limitType <>", value, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeGreaterThan(String value) {
      addCriterion("limitType >", value, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeGreaterThanOrEqualTo(String value) {
      addCriterion("limitType >=", value, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeLessThan(String value) {
      addCriterion("limitType <", value, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeLessThanOrEqualTo(String value) {
      addCriterion("limitType <=", value, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeLike(String value) {
      addCriterion("limitType like", value, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeNotLike(String value) {
      addCriterion("limitType not like", value, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeIn(List<String> values) {
      addCriterion("limitType in", values, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeNotIn(List<String> values) {
      addCriterion("limitType not in", values, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeBetween(String value1, String value2) {
      addCriterion("limitType between", value1, value2, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimittypeNotBetween(String value1, String value2) {
      addCriterion("limitType not between", value1, value2, "limittype");
      return (Criteria) this;
    }

    public Criteria andLimitactionIsNull() {
      addCriterion("limitAction is null");
      return (Criteria) this;
    }

    public Criteria andLimitactionIsNotNull() {
      addCriterion("limitAction is not null");
      return (Criteria) this;
    }

    public Criteria andLimitactionEqualTo(String value) {
      addCriterion("limitAction =", value, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionNotEqualTo(String value) {
      addCriterion("limitAction <>", value, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionGreaterThan(String value) {
      addCriterion("limitAction >", value, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionGreaterThanOrEqualTo(String value) {
      addCriterion("limitAction >=", value, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionLessThan(String value) {
      addCriterion("limitAction <", value, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionLessThanOrEqualTo(String value) {
      addCriterion("limitAction <=", value, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionLike(String value) {
      addCriterion("limitAction like", value, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionNotLike(String value) {
      addCriterion("limitAction not like", value, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionIn(List<String> values) {
      addCriterion("limitAction in", values, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionNotIn(List<String> values) {
      addCriterion("limitAction not in", values, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionBetween(String value1, String value2) {
      addCriterion("limitAction between", value1, value2, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitactionNotBetween(String value1, String value2) {
      addCriterion("limitAction not between", value1, value2, "limitaction");
      return (Criteria) this;
    }

    public Criteria andLimitvalueIsNull() {
      addCriterion("limitValue is null");
      return (Criteria) this;
    }

    public Criteria andLimitvalueIsNotNull() {
      addCriterion("limitValue is not null");
      return (Criteria) this;
    }

    public Criteria andLimitvalueEqualTo(String value) {
      addCriterion("limitValue =", value, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueNotEqualTo(String value) {
      addCriterion("limitValue <>", value, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueGreaterThan(String value) {
      addCriterion("limitValue >", value, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueGreaterThanOrEqualTo(String value) {
      addCriterion("limitValue >=", value, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueLessThan(String value) {
      addCriterion("limitValue <", value, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueLessThanOrEqualTo(String value) {
      addCriterion("limitValue <=", value, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueLike(String value) {
      addCriterion("limitValue like", value, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueNotLike(String value) {
      addCriterion("limitValue not like", value, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueIn(List<String> values) {
      addCriterion("limitValue in", values, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueNotIn(List<String> values) {
      addCriterion("limitValue not in", values, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueBetween(String value1, String value2) {
      addCriterion("limitValue between", value1, value2, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andLimitvalueNotBetween(String value1, String value2) {
      addCriterion("limitValue not between", value1, value2, "limitvalue");
      return (Criteria) this;
    }

    public Criteria andEndtimeIsNull() {
      addCriterion("endTime is null");
      return (Criteria) this;
    }

    public Criteria andEndtimeIsNotNull() {
      addCriterion("endTime is not null");
      return (Criteria) this;
    }

    public Criteria andEndtimeEqualTo(Date value) {
      addCriterion("endTime =", value, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeNotEqualTo(Date value) {
      addCriterion("endTime <>", value, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeGreaterThan(Date value) {
      addCriterion("endTime >", value, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
      addCriterion("endTime >=", value, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeLessThan(Date value) {
      addCriterion("endTime <", value, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeLessThanOrEqualTo(Date value) {
      addCriterion("endTime <=", value, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeIn(List<Date> values) {
      addCriterion("endTime in", values, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeNotIn(List<Date> values) {
      addCriterion("endTime not in", values, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeBetween(Date value1, Date value2) {
      addCriterion("endTime between", value1, value2, "endtime");
      return (Criteria) this;
    }

    public Criteria andEndtimeNotBetween(Date value1, Date value2) {
      addCriterion("endTime not between", value1, value2, "endtime");
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
