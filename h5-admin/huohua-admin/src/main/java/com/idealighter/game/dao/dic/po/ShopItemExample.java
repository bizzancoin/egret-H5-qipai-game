package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class ShopItemExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public ShopItemExample() {
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

    public Criteria andGoldIsNull() {
      addCriterion("gold is null");
      return (Criteria) this;
    }

    public Criteria andGoldIsNotNull() {
      addCriterion("gold is not null");
      return (Criteria) this;
    }

    public Criteria andGoldEqualTo(Integer value) {
      addCriterion("gold =", value, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldNotEqualTo(Integer value) {
      addCriterion("gold <>", value, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldGreaterThan(Integer value) {
      addCriterion("gold >", value, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldGreaterThanOrEqualTo(Integer value) {
      addCriterion("gold >=", value, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldLessThan(Integer value) {
      addCriterion("gold <", value, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldLessThanOrEqualTo(Integer value) {
      addCriterion("gold <=", value, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldIn(List<Integer> values) {
      addCriterion("gold in", values, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldNotIn(List<Integer> values) {
      addCriterion("gold not in", values, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldBetween(Integer value1, Integer value2) {
      addCriterion("gold between", value1, value2, "gold");
      return (Criteria) this;
    }

    public Criteria andGoldNotBetween(Integer value1, Integer value2) {
      addCriterion("gold not between", value1, value2, "gold");
      return (Criteria) this;
    }

    public Criteria andPricegoldIsNull() {
      addCriterion("priceGold is null");
      return (Criteria) this;
    }

    public Criteria andPricegoldIsNotNull() {
      addCriterion("priceGold is not null");
      return (Criteria) this;
    }

    public Criteria andPricegoldEqualTo(Integer value) {
      addCriterion("priceGold =", value, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldNotEqualTo(Integer value) {
      addCriterion("priceGold <>", value, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldGreaterThan(Integer value) {
      addCriterion("priceGold >", value, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldGreaterThanOrEqualTo(Integer value) {
      addCriterion("priceGold >=", value, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldLessThan(Integer value) {
      addCriterion("priceGold <", value, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldLessThanOrEqualTo(Integer value) {
      addCriterion("priceGold <=", value, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldIn(List<Integer> values) {
      addCriterion("priceGold in", values, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldNotIn(List<Integer> values) {
      addCriterion("priceGold not in", values, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldBetween(Integer value1, Integer value2) {
      addCriterion("priceGold between", value1, value2, "pricegold");
      return (Criteria) this;
    }

    public Criteria andPricegoldNotBetween(Integer value1, Integer value2) {
      addCriterion("priceGold not between", value1, value2, "pricegold");
      return (Criteria) this;
    }

    public Criteria andIngotIsNull() {
      addCriterion("ingot is null");
      return (Criteria) this;
    }

    public Criteria andIngotIsNotNull() {
      addCriterion("ingot is not null");
      return (Criteria) this;
    }

    public Criteria andIngotEqualTo(Integer value) {
      addCriterion("ingot =", value, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotNotEqualTo(Integer value) {
      addCriterion("ingot <>", value, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotGreaterThan(Integer value) {
      addCriterion("ingot >", value, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotGreaterThanOrEqualTo(Integer value) {
      addCriterion("ingot >=", value, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotLessThan(Integer value) {
      addCriterion("ingot <", value, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotLessThanOrEqualTo(Integer value) {
      addCriterion("ingot <=", value, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotIn(List<Integer> values) {
      addCriterion("ingot in", values, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotNotIn(List<Integer> values) {
      addCriterion("ingot not in", values, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotBetween(Integer value1, Integer value2) {
      addCriterion("ingot between", value1, value2, "ingot");
      return (Criteria) this;
    }

    public Criteria andIngotNotBetween(Integer value1, Integer value2) {
      addCriterion("ingot not between", value1, value2, "ingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotIsNull() {
      addCriterion("priceIngot is null");
      return (Criteria) this;
    }

    public Criteria andPriceingotIsNotNull() {
      addCriterion("priceIngot is not null");
      return (Criteria) this;
    }

    public Criteria andPriceingotEqualTo(Integer value) {
      addCriterion("priceIngot =", value, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotNotEqualTo(Integer value) {
      addCriterion("priceIngot <>", value, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotGreaterThan(Integer value) {
      addCriterion("priceIngot >", value, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotGreaterThanOrEqualTo(Integer value) {
      addCriterion("priceIngot >=", value, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotLessThan(Integer value) {
      addCriterion("priceIngot <", value, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotLessThanOrEqualTo(Integer value) {
      addCriterion("priceIngot <=", value, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotIn(List<Integer> values) {
      addCriterion("priceIngot in", values, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotNotIn(List<Integer> values) {
      addCriterion("priceIngot not in", values, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotBetween(Integer value1, Integer value2) {
      addCriterion("priceIngot between", value1, value2, "priceingot");
      return (Criteria) this;
    }

    public Criteria andPriceingotNotBetween(Integer value1, Integer value2) {
      addCriterion("priceIngot not between", value1, value2, "priceingot");
      return (Criteria) this;
    }

    public Criteria andGiveIsNull() {
      addCriterion("give is null");
      return (Criteria) this;
    }

    public Criteria andGiveIsNotNull() {
      addCriterion("give is not null");
      return (Criteria) this;
    }

    public Criteria andGiveEqualTo(Integer value) {
      addCriterion("give =", value, "give");
      return (Criteria) this;
    }

    public Criteria andGiveNotEqualTo(Integer value) {
      addCriterion("give <>", value, "give");
      return (Criteria) this;
    }

    public Criteria andGiveGreaterThan(Integer value) {
      addCriterion("give >", value, "give");
      return (Criteria) this;
    }

    public Criteria andGiveGreaterThanOrEqualTo(Integer value) {
      addCriterion("give >=", value, "give");
      return (Criteria) this;
    }

    public Criteria andGiveLessThan(Integer value) {
      addCriterion("give <", value, "give");
      return (Criteria) this;
    }

    public Criteria andGiveLessThanOrEqualTo(Integer value) {
      addCriterion("give <=", value, "give");
      return (Criteria) this;
    }

    public Criteria andGiveIn(List<Integer> values) {
      addCriterion("give in", values, "give");
      return (Criteria) this;
    }

    public Criteria andGiveNotIn(List<Integer> values) {
      addCriterion("give not in", values, "give");
      return (Criteria) this;
    }

    public Criteria andGiveBetween(Integer value1, Integer value2) {
      addCriterion("give between", value1, value2, "give");
      return (Criteria) this;
    }

    public Criteria andGiveNotBetween(Integer value1, Integer value2) {
      addCriterion("give not between", value1, value2, "give");
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

    public Criteria andHotIsNull() {
      addCriterion("hot is null");
      return (Criteria) this;
    }

    public Criteria andHotIsNotNull() {
      addCriterion("hot is not null");
      return (Criteria) this;
    }

    public Criteria andHotEqualTo(Integer value) {
      addCriterion("hot =", value, "hot");
      return (Criteria) this;
    }

    public Criteria andHotNotEqualTo(Integer value) {
      addCriterion("hot <>", value, "hot");
      return (Criteria) this;
    }

    public Criteria andHotGreaterThan(Integer value) {
      addCriterion("hot >", value, "hot");
      return (Criteria) this;
    }

    public Criteria andHotGreaterThanOrEqualTo(Integer value) {
      addCriterion("hot >=", value, "hot");
      return (Criteria) this;
    }

    public Criteria andHotLessThan(Integer value) {
      addCriterion("hot <", value, "hot");
      return (Criteria) this;
    }

    public Criteria andHotLessThanOrEqualTo(Integer value) {
      addCriterion("hot <=", value, "hot");
      return (Criteria) this;
    }

    public Criteria andHotIn(List<Integer> values) {
      addCriterion("hot in", values, "hot");
      return (Criteria) this;
    }

    public Criteria andHotNotIn(List<Integer> values) {
      addCriterion("hot not in", values, "hot");
      return (Criteria) this;
    }

    public Criteria andHotBetween(Integer value1, Integer value2) {
      addCriterion("hot between", value1, value2, "hot");
      return (Criteria) this;
    }

    public Criteria andHotNotBetween(Integer value1, Integer value2) {
      addCriterion("hot not between", value1, value2, "hot");
      return (Criteria) this;
    }

    public Criteria andMaxcountIsNull() {
      addCriterion("maxCount is null");
      return (Criteria) this;
    }

    public Criteria andMaxcountIsNotNull() {
      addCriterion("maxCount is not null");
      return (Criteria) this;
    }

    public Criteria andMaxcountEqualTo(Integer value) {
      addCriterion("maxCount =", value, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountNotEqualTo(Integer value) {
      addCriterion("maxCount <>", value, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountGreaterThan(Integer value) {
      addCriterion("maxCount >", value, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountGreaterThanOrEqualTo(Integer value) {
      addCriterion("maxCount >=", value, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountLessThan(Integer value) {
      addCriterion("maxCount <", value, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountLessThanOrEqualTo(Integer value) {
      addCriterion("maxCount <=", value, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountIn(List<Integer> values) {
      addCriterion("maxCount in", values, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountNotIn(List<Integer> values) {
      addCriterion("maxCount not in", values, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountBetween(Integer value1, Integer value2) {
      addCriterion("maxCount between", value1, value2, "maxcount");
      return (Criteria) this;
    }

    public Criteria andMaxcountNotBetween(Integer value1, Integer value2) {
      addCriterion("maxCount not between", value1, value2, "maxcount");
      return (Criteria) this;
    }

    public Criteria andDescIsNull() {
      addCriterion("desc is null");
      return (Criteria) this;
    }

    public Criteria andDescIsNotNull() {
      addCriterion("desc is not null");
      return (Criteria) this;
    }

    public Criteria andDescEqualTo(String value) {
      addCriterion("desc =", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescNotEqualTo(String value) {
      addCriterion("desc <>", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescGreaterThan(String value) {
      addCriterion("desc >", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescGreaterThanOrEqualTo(String value) {
      addCriterion("desc >=", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescLessThan(String value) {
      addCriterion("desc <", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescLessThanOrEqualTo(String value) {
      addCriterion("desc <=", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescLike(String value) {
      addCriterion("desc like", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescNotLike(String value) {
      addCriterion("desc not like", value, "desc");
      return (Criteria) this;
    }

    public Criteria andDescIn(List<String> values) {
      addCriterion("desc in", values, "desc");
      return (Criteria) this;
    }

    public Criteria andDescNotIn(List<String> values) {
      addCriterion("desc not in", values, "desc");
      return (Criteria) this;
    }

    public Criteria andDescBetween(String value1, String value2) {
      addCriterion("desc between", value1, value2, "desc");
      return (Criteria) this;
    }

    public Criteria andDescNotBetween(String value1, String value2) {
      addCriterion("desc not between", value1, value2, "desc");
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
