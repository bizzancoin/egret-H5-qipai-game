package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class ItemattributeExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public ItemattributeExample() {
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

    public Criteria andNameIsNull() {
      addCriterion("name is null");
      return (Criteria) this;
    }

    public Criteria andNameIsNotNull() {
      addCriterion("name is not null");
      return (Criteria) this;
    }

    public Criteria andNameEqualTo(String value) {
      addCriterion("name =", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotEqualTo(String value) {
      addCriterion("name <>", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThan(String value) {
      addCriterion("name >", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThanOrEqualTo(String value) {
      addCriterion("name >=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThan(String value) {
      addCriterion("name <", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThanOrEqualTo(String value) {
      addCriterion("name <=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLike(String value) {
      addCriterion("name like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotLike(String value) {
      addCriterion("name not like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameIn(List<String> values) {
      addCriterion("name in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotIn(List<String> values) {
      addCriterion("name not in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameBetween(String value1, String value2) {
      addCriterion("name between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotBetween(String value1, String value2) {
      addCriterion("name not between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andTypeIsNull() {
      addCriterion("type is null");
      return (Criteria) this;
    }

    public Criteria andTypeIsNotNull() {
      addCriterion("type is not null");
      return (Criteria) this;
    }

    public Criteria andTypeEqualTo(Integer value) {
      addCriterion("type =", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotEqualTo(Integer value) {
      addCriterion("type <>", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeGreaterThan(Integer value) {
      addCriterion("type >", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
      addCriterion("type >=", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeLessThan(Integer value) {
      addCriterion("type <", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeLessThanOrEqualTo(Integer value) {
      addCriterion("type <=", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeIn(List<Integer> values) {
      addCriterion("type in", values, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotIn(List<Integer> values) {
      addCriterion("type not in", values, "type");
      return (Criteria) this;
    }

    public Criteria andTypeBetween(Integer value1, Integer value2) {
      addCriterion("type between", value1, value2, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotBetween(Integer value1, Integer value2) {
      addCriterion("type not between", value1, value2, "type");
      return (Criteria) this;
    }

    public Criteria andGetIsNull() {
      addCriterion("get is null");
      return (Criteria) this;
    }

    public Criteria andGetIsNotNull() {
      addCriterion("get is not null");
      return (Criteria) this;
    }

    public Criteria andGetEqualTo(Integer value) {
      addCriterion("get =", value, "get");
      return (Criteria) this;
    }

    public Criteria andGetNotEqualTo(Integer value) {
      addCriterion("get <>", value, "get");
      return (Criteria) this;
    }

    public Criteria andGetGreaterThan(Integer value) {
      addCriterion("get >", value, "get");
      return (Criteria) this;
    }

    public Criteria andGetGreaterThanOrEqualTo(Integer value) {
      addCriterion("get >=", value, "get");
      return (Criteria) this;
    }

    public Criteria andGetLessThan(Integer value) {
      addCriterion("get <", value, "get");
      return (Criteria) this;
    }

    public Criteria andGetLessThanOrEqualTo(Integer value) {
      addCriterion("get <=", value, "get");
      return (Criteria) this;
    }

    public Criteria andGetIn(List<Integer> values) {
      addCriterion("get in", values, "get");
      return (Criteria) this;
    }

    public Criteria andGetNotIn(List<Integer> values) {
      addCriterion("get not in", values, "get");
      return (Criteria) this;
    }

    public Criteria andGetBetween(Integer value1, Integer value2) {
      addCriterion("get between", value1, value2, "get");
      return (Criteria) this;
    }

    public Criteria andGetNotBetween(Integer value1, Integer value2) {
      addCriterion("get not between", value1, value2, "get");
      return (Criteria) this;
    }

    public Criteria andGetnumIsNull() {
      addCriterion("getnum is null");
      return (Criteria) this;
    }

    public Criteria andGetnumIsNotNull() {
      addCriterion("getnum is not null");
      return (Criteria) this;
    }

    public Criteria andGetnumEqualTo(Integer value) {
      addCriterion("getnum =", value, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumNotEqualTo(Integer value) {
      addCriterion("getnum <>", value, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumGreaterThan(Integer value) {
      addCriterion("getnum >", value, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumGreaterThanOrEqualTo(Integer value) {
      addCriterion("getnum >=", value, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumLessThan(Integer value) {
      addCriterion("getnum <", value, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumLessThanOrEqualTo(Integer value) {
      addCriterion("getnum <=", value, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumIn(List<Integer> values) {
      addCriterion("getnum in", values, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumNotIn(List<Integer> values) {
      addCriterion("getnum not in", values, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumBetween(Integer value1, Integer value2) {
      addCriterion("getnum between", value1, value2, "getnum");
      return (Criteria) this;
    }

    public Criteria andGetnumNotBetween(Integer value1, Integer value2) {
      addCriterion("getnum not between", value1, value2, "getnum");
      return (Criteria) this;
    }

    public Criteria andTimeIsNull() {
      addCriterion("time is null");
      return (Criteria) this;
    }

    public Criteria andTimeIsNotNull() {
      addCriterion("time is not null");
      return (Criteria) this;
    }

    public Criteria andTimeEqualTo(Integer value) {
      addCriterion("time =", value, "time");
      return (Criteria) this;
    }

    public Criteria andTimeNotEqualTo(Integer value) {
      addCriterion("time <>", value, "time");
      return (Criteria) this;
    }

    public Criteria andTimeGreaterThan(Integer value) {
      addCriterion("time >", value, "time");
      return (Criteria) this;
    }

    public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
      addCriterion("time >=", value, "time");
      return (Criteria) this;
    }

    public Criteria andTimeLessThan(Integer value) {
      addCriterion("time <", value, "time");
      return (Criteria) this;
    }

    public Criteria andTimeLessThanOrEqualTo(Integer value) {
      addCriterion("time <=", value, "time");
      return (Criteria) this;
    }

    public Criteria andTimeIn(List<Integer> values) {
      addCriterion("time in", values, "time");
      return (Criteria) this;
    }

    public Criteria andTimeNotIn(List<Integer> values) {
      addCriterion("time not in", values, "time");
      return (Criteria) this;
    }

    public Criteria andTimeBetween(Integer value1, Integer value2) {
      addCriterion("time between", value1, value2, "time");
      return (Criteria) this;
    }

    public Criteria andTimeNotBetween(Integer value1, Integer value2) {
      addCriterion("time not between", value1, value2, "time");
      return (Criteria) this;
    }

    public Criteria andObjectIsNull() {
      addCriterion("object is null");
      return (Criteria) this;
    }

    public Criteria andObjectIsNotNull() {
      addCriterion("object is not null");
      return (Criteria) this;
    }

    public Criteria andObjectEqualTo(Integer value) {
      addCriterion("object =", value, "object");
      return (Criteria) this;
    }

    public Criteria andObjectNotEqualTo(Integer value) {
      addCriterion("object <>", value, "object");
      return (Criteria) this;
    }

    public Criteria andObjectGreaterThan(Integer value) {
      addCriterion("object >", value, "object");
      return (Criteria) this;
    }

    public Criteria andObjectGreaterThanOrEqualTo(Integer value) {
      addCriterion("object >=", value, "object");
      return (Criteria) this;
    }

    public Criteria andObjectLessThan(Integer value) {
      addCriterion("object <", value, "object");
      return (Criteria) this;
    }

    public Criteria andObjectLessThanOrEqualTo(Integer value) {
      addCriterion("object <=", value, "object");
      return (Criteria) this;
    }

    public Criteria andObjectIn(List<Integer> values) {
      addCriterion("object in", values, "object");
      return (Criteria) this;
    }

    public Criteria andObjectNotIn(List<Integer> values) {
      addCriterion("object not in", values, "object");
      return (Criteria) this;
    }

    public Criteria andObjectBetween(Integer value1, Integer value2) {
      addCriterion("object between", value1, value2, "object");
      return (Criteria) this;
    }

    public Criteria andObjectNotBetween(Integer value1, Integer value2) {
      addCriterion("object not between", value1, value2, "object");
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
