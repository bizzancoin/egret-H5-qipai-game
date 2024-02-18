package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TexaporkerRoomExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  public TexaporkerRoomExample() {
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

    public Criteria andMaxnumIsNull() {
      addCriterion("maxNum is null");
      return (Criteria) this;
    }

    public Criteria andMaxnumIsNotNull() {
      addCriterion("maxNum is not null");
      return (Criteria) this;
    }

    public Criteria andMaxnumEqualTo(Integer value) {
      addCriterion("maxNum =", value, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumNotEqualTo(Integer value) {
      addCriterion("maxNum <>", value, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumGreaterThan(Integer value) {
      addCriterion("maxNum >", value, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumGreaterThanOrEqualTo(Integer value) {
      addCriterion("maxNum >=", value, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumLessThan(Integer value) {
      addCriterion("maxNum <", value, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumLessThanOrEqualTo(Integer value) {
      addCriterion("maxNum <=", value, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumIn(List<Integer> values) {
      addCriterion("maxNum in", values, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumNotIn(List<Integer> values) {
      addCriterion("maxNum not in", values, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumBetween(Integer value1, Integer value2) {
      addCriterion("maxNum between", value1, value2, "maxnum");
      return (Criteria) this;
    }

    public Criteria andMaxnumNotBetween(Integer value1, Integer value2) {
      addCriterion("maxNum not between", value1, value2, "maxnum");
      return (Criteria) this;
    }

    public Criteria andFreeIsNull() {
      addCriterion("free is null");
      return (Criteria) this;
    }

    public Criteria andFreeIsNotNull() {
      addCriterion("free is not null");
      return (Criteria) this;
    }

    public Criteria andFreeEqualTo(Integer value) {
      addCriterion("free =", value, "free");
      return (Criteria) this;
    }

    public Criteria andFreeNotEqualTo(Integer value) {
      addCriterion("free <>", value, "free");
      return (Criteria) this;
    }

    public Criteria andFreeGreaterThan(Integer value) {
      addCriterion("free >", value, "free");
      return (Criteria) this;
    }

    public Criteria andFreeGreaterThanOrEqualTo(Integer value) {
      addCriterion("free >=", value, "free");
      return (Criteria) this;
    }

    public Criteria andFreeLessThan(Integer value) {
      addCriterion("free <", value, "free");
      return (Criteria) this;
    }

    public Criteria andFreeLessThanOrEqualTo(Integer value) {
      addCriterion("free <=", value, "free");
      return (Criteria) this;
    }

    public Criteria andFreeIn(List<Integer> values) {
      addCriterion("free in", values, "free");
      return (Criteria) this;
    }

    public Criteria andFreeNotIn(List<Integer> values) {
      addCriterion("free not in", values, "free");
      return (Criteria) this;
    }

    public Criteria andFreeBetween(Integer value1, Integer value2) {
      addCriterion("free between", value1, value2, "free");
      return (Criteria) this;
    }

    public Criteria andFreeNotBetween(Integer value1, Integer value2) {
      addCriterion("free not between", value1, value2, "free");
      return (Criteria) this;
    }

    public Criteria andGeneralIsNull() {
      addCriterion("general is null");
      return (Criteria) this;
    }

    public Criteria andGeneralIsNotNull() {
      addCriterion("general is not null");
      return (Criteria) this;
    }

    public Criteria andGeneralEqualTo(Integer value) {
      addCriterion("general =", value, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralNotEqualTo(Integer value) {
      addCriterion("general <>", value, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralGreaterThan(Integer value) {
      addCriterion("general >", value, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralGreaterThanOrEqualTo(Integer value) {
      addCriterion("general >=", value, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralLessThan(Integer value) {
      addCriterion("general <", value, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralLessThanOrEqualTo(Integer value) {
      addCriterion("general <=", value, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralIn(List<Integer> values) {
      addCriterion("general in", values, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralNotIn(List<Integer> values) {
      addCriterion("general not in", values, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralBetween(Integer value1, Integer value2) {
      addCriterion("general between", value1, value2, "general");
      return (Criteria) this;
    }

    public Criteria andGeneralNotBetween(Integer value1, Integer value2) {
      addCriterion("general not between", value1, value2, "general");
      return (Criteria) this;
    }

    public Criteria andCrowdedIsNull() {
      addCriterion("crowded is null");
      return (Criteria) this;
    }

    public Criteria andCrowdedIsNotNull() {
      addCriterion("crowded is not null");
      return (Criteria) this;
    }

    public Criteria andCrowdedEqualTo(Integer value) {
      addCriterion("crowded =", value, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedNotEqualTo(Integer value) {
      addCriterion("crowded <>", value, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedGreaterThan(Integer value) {
      addCriterion("crowded >", value, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedGreaterThanOrEqualTo(Integer value) {
      addCriterion("crowded >=", value, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedLessThan(Integer value) {
      addCriterion("crowded <", value, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedLessThanOrEqualTo(Integer value) {
      addCriterion("crowded <=", value, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedIn(List<Integer> values) {
      addCriterion("crowded in", values, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedNotIn(List<Integer> values) {
      addCriterion("crowded not in", values, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedBetween(Integer value1, Integer value2) {
      addCriterion("crowded between", value1, value2, "crowded");
      return (Criteria) this;
    }

    public Criteria andCrowdedNotBetween(Integer value1, Integer value2) {
      addCriterion("crowded not between", value1, value2, "crowded");
      return (Criteria) this;
    }

    public Criteria andLowerIsNull() {
      addCriterion("lower is null");
      return (Criteria) this;
    }

    public Criteria andLowerIsNotNull() {
      addCriterion("lower is not null");
      return (Criteria) this;
    }

    public Criteria andLowerEqualTo(Integer value) {
      addCriterion("lower =", value, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerNotEqualTo(Integer value) {
      addCriterion("lower <>", value, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerGreaterThan(Integer value) {
      addCriterion("lower >", value, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerGreaterThanOrEqualTo(Integer value) {
      addCriterion("lower >=", value, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerLessThan(Integer value) {
      addCriterion("lower <", value, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerLessThanOrEqualTo(Integer value) {
      addCriterion("lower <=", value, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerIn(List<Integer> values) {
      addCriterion("lower in", values, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerNotIn(List<Integer> values) {
      addCriterion("lower not in", values, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerBetween(Integer value1, Integer value2) {
      addCriterion("lower between", value1, value2, "lower");
      return (Criteria) this;
    }

    public Criteria andLowerNotBetween(Integer value1, Integer value2) {
      addCriterion("lower not between", value1, value2, "lower");
      return (Criteria) this;
    }

    public Criteria andUpperIsNull() {
      addCriterion("upper is null");
      return (Criteria) this;
    }

    public Criteria andUpperIsNotNull() {
      addCriterion("upper is not null");
      return (Criteria) this;
    }

    public Criteria andUpperEqualTo(Integer value) {
      addCriterion("upper =", value, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperNotEqualTo(Integer value) {
      addCriterion("upper <>", value, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperGreaterThan(Integer value) {
      addCriterion("upper >", value, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperGreaterThanOrEqualTo(Integer value) {
      addCriterion("upper >=", value, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperLessThan(Integer value) {
      addCriterion("upper <", value, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperLessThanOrEqualTo(Integer value) {
      addCriterion("upper <=", value, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperIn(List<Integer> values) {
      addCriterion("upper in", values, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperNotIn(List<Integer> values) {
      addCriterion("upper not in", values, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperBetween(Integer value1, Integer value2) {
      addCriterion("upper between", value1, value2, "upper");
      return (Criteria) this;
    }

    public Criteria andUpperNotBetween(Integer value1, Integer value2) {
      addCriterion("upper not between", value1, value2, "upper");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleIsNull() {
      addCriterion("ordinarPeople is null");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleIsNotNull() {
      addCriterion("ordinarPeople is not null");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleEqualTo(Integer value) {
      addCriterion("ordinarPeople =", value, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleNotEqualTo(Integer value) {
      addCriterion("ordinarPeople <>", value, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleGreaterThan(Integer value) {
      addCriterion("ordinarPeople >", value, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleGreaterThanOrEqualTo(Integer value) {
      addCriterion("ordinarPeople >=", value, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleLessThan(Integer value) {
      addCriterion("ordinarPeople <", value, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleLessThanOrEqualTo(Integer value) {
      addCriterion("ordinarPeople <=", value, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleIn(List<Integer> values) {
      addCriterion("ordinarPeople in", values, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleNotIn(List<Integer> values) {
      addCriterion("ordinarPeople not in", values, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleBetween(Integer value1, Integer value2) {
      addCriterion("ordinarPeople between", value1, value2, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andOrdinarpeopleNotBetween(Integer value1, Integer value2) {
      addCriterion("ordinarPeople not between", value1, value2, "ordinarpeople");
      return (Criteria) this;
    }

    public Criteria andProportiongoldIsNull() {
      addCriterion("proportionGold is null");
      return (Criteria) this;
    }

    public Criteria andProportiongoldIsNotNull() {
      addCriterion("proportionGold is not null");
      return (Criteria) this;
    }

    public Criteria andProportiongoldEqualTo(Integer value) {
      addCriterion("proportionGold =", value, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldNotEqualTo(Integer value) {
      addCriterion("proportionGold <>", value, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldGreaterThan(Integer value) {
      addCriterion("proportionGold >", value, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldGreaterThanOrEqualTo(Integer value) {
      addCriterion("proportionGold >=", value, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldLessThan(Integer value) {
      addCriterion("proportionGold <", value, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldLessThanOrEqualTo(Integer value) {
      addCriterion("proportionGold <=", value, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldIn(List<Integer> values) {
      addCriterion("proportionGold in", values, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldNotIn(List<Integer> values) {
      addCriterion("proportionGold not in", values, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldBetween(Integer value1, Integer value2) {
      addCriterion("proportionGold between", value1, value2, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportiongoldNotBetween(Integer value1, Integer value2) {
      addCriterion("proportionGold not between", value1, value2, "proportiongold");
      return (Criteria) this;
    }

    public Criteria andProportionchipsIsNull() {
      addCriterion("proportionChips is null");
      return (Criteria) this;
    }

    public Criteria andProportionchipsIsNotNull() {
      addCriterion("proportionChips is not null");
      return (Criteria) this;
    }

    public Criteria andProportionchipsEqualTo(Integer value) {
      addCriterion("proportionChips =", value, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsNotEqualTo(Integer value) {
      addCriterion("proportionChips <>", value, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsGreaterThan(Integer value) {
      addCriterion("proportionChips >", value, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsGreaterThanOrEqualTo(Integer value) {
      addCriterion("proportionChips >=", value, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsLessThan(Integer value) {
      addCriterion("proportionChips <", value, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsLessThanOrEqualTo(Integer value) {
      addCriterion("proportionChips <=", value, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsIn(List<Integer> values) {
      addCriterion("proportionChips in", values, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsNotIn(List<Integer> values) {
      addCriterion("proportionChips not in", values, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsBetween(Integer value1, Integer value2) {
      addCriterion("proportionChips between", value1, value2, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andProportionchipsNotBetween(Integer value1, Integer value2) {
      addCriterion("proportionChips not between", value1, value2, "proportionchips");
      return (Criteria) this;
    }

    public Criteria andTableIsNull() {
      addCriterion("table is null");
      return (Criteria) this;
    }

    public Criteria andTableIsNotNull() {
      addCriterion("table is not null");
      return (Criteria) this;
    }

    public Criteria andTableEqualTo(Integer value) {
      addCriterion("table =", value, "table");
      return (Criteria) this;
    }

    public Criteria andTableNotEqualTo(Integer value) {
      addCriterion("table <>", value, "table");
      return (Criteria) this;
    }

    public Criteria andTableGreaterThan(Integer value) {
      addCriterion("table >", value, "table");
      return (Criteria) this;
    }

    public Criteria andTableGreaterThanOrEqualTo(Integer value) {
      addCriterion("table >=", value, "table");
      return (Criteria) this;
    }

    public Criteria andTableLessThan(Integer value) {
      addCriterion("table <", value, "table");
      return (Criteria) this;
    }

    public Criteria andTableLessThanOrEqualTo(Integer value) {
      addCriterion("table <=", value, "table");
      return (Criteria) this;
    }

    public Criteria andTableIn(List<Integer> values) {
      addCriterion("table in", values, "table");
      return (Criteria) this;
    }

    public Criteria andTableNotIn(List<Integer> values) {
      addCriterion("table not in", values, "table");
      return (Criteria) this;
    }

    public Criteria andTableBetween(Integer value1, Integer value2) {
      addCriterion("table between", value1, value2, "table");
      return (Criteria) this;
    }

    public Criteria andTableNotBetween(Integer value1, Integer value2) {
      addCriterion("table not between", value1, value2, "table");
      return (Criteria) this;
    }

    public Criteria andChairIsNull() {
      addCriterion("chair is null");
      return (Criteria) this;
    }

    public Criteria andChairIsNotNull() {
      addCriterion("chair is not null");
      return (Criteria) this;
    }

    public Criteria andChairEqualTo(Integer value) {
      addCriterion("chair =", value, "chair");
      return (Criteria) this;
    }

    public Criteria andChairNotEqualTo(Integer value) {
      addCriterion("chair <>", value, "chair");
      return (Criteria) this;
    }

    public Criteria andChairGreaterThan(Integer value) {
      addCriterion("chair >", value, "chair");
      return (Criteria) this;
    }

    public Criteria andChairGreaterThanOrEqualTo(Integer value) {
      addCriterion("chair >=", value, "chair");
      return (Criteria) this;
    }

    public Criteria andChairLessThan(Integer value) {
      addCriterion("chair <", value, "chair");
      return (Criteria) this;
    }

    public Criteria andChairLessThanOrEqualTo(Integer value) {
      addCriterion("chair <=", value, "chair");
      return (Criteria) this;
    }

    public Criteria andChairIn(List<Integer> values) {
      addCriterion("chair in", values, "chair");
      return (Criteria) this;
    }

    public Criteria andChairNotIn(List<Integer> values) {
      addCriterion("chair not in", values, "chair");
      return (Criteria) this;
    }

    public Criteria andChairBetween(Integer value1, Integer value2) {
      addCriterion("chair between", value1, value2, "chair");
      return (Criteria) this;
    }

    public Criteria andChairNotBetween(Integer value1, Integer value2) {
      addCriterion("chair not between", value1, value2, "chair");
      return (Criteria) this;
    }

    public Criteria andTopIsNull() {
      addCriterion("top is null");
      return (Criteria) this;
    }

    public Criteria andTopIsNotNull() {
      addCriterion("top is not null");
      return (Criteria) this;
    }

    public Criteria andTopEqualTo(Integer value) {
      addCriterion("top =", value, "top");
      return (Criteria) this;
    }

    public Criteria andTopNotEqualTo(Integer value) {
      addCriterion("top <>", value, "top");
      return (Criteria) this;
    }

    public Criteria andTopGreaterThan(Integer value) {
      addCriterion("top >", value, "top");
      return (Criteria) this;
    }

    public Criteria andTopGreaterThanOrEqualTo(Integer value) {
      addCriterion("top >=", value, "top");
      return (Criteria) this;
    }

    public Criteria andTopLessThan(Integer value) {
      addCriterion("top <", value, "top");
      return (Criteria) this;
    }

    public Criteria andTopLessThanOrEqualTo(Integer value) {
      addCriterion("top <=", value, "top");
      return (Criteria) this;
    }

    public Criteria andTopIn(List<Integer> values) {
      addCriterion("top in", values, "top");
      return (Criteria) this;
    }

    public Criteria andTopNotIn(List<Integer> values) {
      addCriterion("top not in", values, "top");
      return (Criteria) this;
    }

    public Criteria andTopBetween(Integer value1, Integer value2) {
      addCriterion("top between", value1, value2, "top");
      return (Criteria) this;
    }

    public Criteria andTopNotBetween(Integer value1, Integer value2) {
      addCriterion("top not between", value1, value2, "top");
      return (Criteria) this;
    }

    public Criteria andMinoneIsNull() {
      addCriterion("minOne is null");
      return (Criteria) this;
    }

    public Criteria andMinoneIsNotNull() {
      addCriterion("minOne is not null");
      return (Criteria) this;
    }

    public Criteria andMinoneEqualTo(Integer value) {
      addCriterion("minOne =", value, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneNotEqualTo(Integer value) {
      addCriterion("minOne <>", value, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneGreaterThan(Integer value) {
      addCriterion("minOne >", value, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneGreaterThanOrEqualTo(Integer value) {
      addCriterion("minOne >=", value, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneLessThan(Integer value) {
      addCriterion("minOne <", value, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneLessThanOrEqualTo(Integer value) {
      addCriterion("minOne <=", value, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneIn(List<Integer> values) {
      addCriterion("minOne in", values, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneNotIn(List<Integer> values) {
      addCriterion("minOne not in", values, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneBetween(Integer value1, Integer value2) {
      addCriterion("minOne between", value1, value2, "minone");
      return (Criteria) this;
    }

    public Criteria andMinoneNotBetween(Integer value1, Integer value2) {
      addCriterion("minOne not between", value1, value2, "minone");
      return (Criteria) this;
    }

    public Criteria andMaxoneIsNull() {
      addCriterion("maxOne is null");
      return (Criteria) this;
    }

    public Criteria andMaxoneIsNotNull() {
      addCriterion("maxOne is not null");
      return (Criteria) this;
    }

    public Criteria andMaxoneEqualTo(Integer value) {
      addCriterion("maxOne =", value, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneNotEqualTo(Integer value) {
      addCriterion("maxOne <>", value, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneGreaterThan(Integer value) {
      addCriterion("maxOne >", value, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneGreaterThanOrEqualTo(Integer value) {
      addCriterion("maxOne >=", value, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneLessThan(Integer value) {
      addCriterion("maxOne <", value, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneLessThanOrEqualTo(Integer value) {
      addCriterion("maxOne <=", value, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneIn(List<Integer> values) {
      addCriterion("maxOne in", values, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneNotIn(List<Integer> values) {
      addCriterion("maxOne not in", values, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneBetween(Integer value1, Integer value2) {
      addCriterion("maxOne between", value1, value2, "maxone");
      return (Criteria) this;
    }

    public Criteria andMaxoneNotBetween(Integer value1, Integer value2) {
      addCriterion("maxOne not between", value1, value2, "maxone");
      return (Criteria) this;
    }

    public Criteria andAfeeIsNull() {
      addCriterion("afee is null");
      return (Criteria) this;
    }

    public Criteria andAfeeIsNotNull() {
      addCriterion("afee is not null");
      return (Criteria) this;
    }

    public Criteria andAfeeEqualTo(Integer value) {
      addCriterion("afee =", value, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeNotEqualTo(Integer value) {
      addCriterion("afee <>", value, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeGreaterThan(Integer value) {
      addCriterion("afee >", value, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeGreaterThanOrEqualTo(Integer value) {
      addCriterion("afee >=", value, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeLessThan(Integer value) {
      addCriterion("afee <", value, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeLessThanOrEqualTo(Integer value) {
      addCriterion("afee <=", value, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeIn(List<Integer> values) {
      addCriterion("afee in", values, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeNotIn(List<Integer> values) {
      addCriterion("afee not in", values, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeBetween(Integer value1, Integer value2) {
      addCriterion("afee between", value1, value2, "afee");
      return (Criteria) this;
    }

    public Criteria andAfeeNotBetween(Integer value1, Integer value2) {
      addCriterion("afee not between", value1, value2, "afee");
      return (Criteria) this;
    }

    public Criteria andIntypeIsNull() {
      addCriterion("inType is null");
      return (Criteria) this;
    }

    public Criteria andIntypeIsNotNull() {
      addCriterion("inType is not null");
      return (Criteria) this;
    }

    public Criteria andIntypeEqualTo(Integer value) {
      addCriterion("inType =", value, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeNotEqualTo(Integer value) {
      addCriterion("inType <>", value, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeGreaterThan(Integer value) {
      addCriterion("inType >", value, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeGreaterThanOrEqualTo(Integer value) {
      addCriterion("inType >=", value, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeLessThan(Integer value) {
      addCriterion("inType <", value, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeLessThanOrEqualTo(Integer value) {
      addCriterion("inType <=", value, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeIn(List<Integer> values) {
      addCriterion("inType in", values, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeNotIn(List<Integer> values) {
      addCriterion("inType not in", values, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeBetween(Integer value1, Integer value2) {
      addCriterion("inType between", value1, value2, "intype");
      return (Criteria) this;
    }

    public Criteria andIntypeNotBetween(Integer value1, Integer value2) {
      addCriterion("inType not between", value1, value2, "intype");
      return (Criteria) this;
    }

    public Criteria andDisplaysIsNull() {
      addCriterion("displays is null");
      return (Criteria) this;
    }

    public Criteria andDisplaysIsNotNull() {
      addCriterion("displays is not null");
      return (Criteria) this;
    }

    public Criteria andDisplaysEqualTo(String value) {
      addCriterion("displays =", value, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysNotEqualTo(String value) {
      addCriterion("displays <>", value, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysGreaterThan(String value) {
      addCriterion("displays >", value, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysGreaterThanOrEqualTo(String value) {
      addCriterion("displays >=", value, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysLessThan(String value) {
      addCriterion("displays <", value, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysLessThanOrEqualTo(String value) {
      addCriterion("displays <=", value, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysLike(String value) {
      addCriterion("displays like", value, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysNotLike(String value) {
      addCriterion("displays not like", value, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysIn(List<String> values) {
      addCriterion("displays in", values, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysNotIn(List<String> values) {
      addCriterion("displays not in", values, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysBetween(String value1, String value2) {
      addCriterion("displays between", value1, value2, "displays");
      return (Criteria) this;
    }

    public Criteria andDisplaysNotBetween(String value1, String value2) {
      addCriterion("displays not between", value1, value2, "displays");
      return (Criteria) this;
    }

    public Criteria andPlaceholderIsNull() {
      addCriterion("placeHolder is null");
      return (Criteria) this;
    }

    public Criteria andPlaceholderIsNotNull() {
      addCriterion("placeHolder is not null");
      return (Criteria) this;
    }

    public Criteria andPlaceholderEqualTo(String value) {
      addCriterion("placeHolder =", value, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderNotEqualTo(String value) {
      addCriterion("placeHolder <>", value, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderGreaterThan(String value) {
      addCriterion("placeHolder >", value, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderGreaterThanOrEqualTo(String value) {
      addCriterion("placeHolder >=", value, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderLessThan(String value) {
      addCriterion("placeHolder <", value, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderLessThanOrEqualTo(String value) {
      addCriterion("placeHolder <=", value, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderLike(String value) {
      addCriterion("placeHolder like", value, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderNotLike(String value) {
      addCriterion("placeHolder not like", value, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderIn(List<String> values) {
      addCriterion("placeHolder in", values, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderNotIn(List<String> values) {
      addCriterion("placeHolder not in", values, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderBetween(String value1, String value2) {
      addCriterion("placeHolder between", value1, value2, "placeholder");
      return (Criteria) this;
    }

    public Criteria andPlaceholderNotBetween(String value1, String value2) {
      addCriterion("placeHolder not between", value1, value2, "placeholder");
      return (Criteria) this;
    }

    public Criteria andIsactiveIsNull() {
      addCriterion("isActive is null");
      return (Criteria) this;
    }

    public Criteria andIsactiveIsNotNull() {
      addCriterion("isActive is not null");
      return (Criteria) this;
    }

    public Criteria andIsactiveEqualTo(Byte value) {
      addCriterion("isActive =", value, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveNotEqualTo(Byte value) {
      addCriterion("isActive <>", value, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveGreaterThan(Byte value) {
      addCriterion("isActive >", value, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveGreaterThanOrEqualTo(Byte value) {
      addCriterion("isActive >=", value, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveLessThan(Byte value) {
      addCriterion("isActive <", value, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveLessThanOrEqualTo(Byte value) {
      addCriterion("isActive <=", value, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveIn(List<Byte> values) {
      addCriterion("isActive in", values, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveNotIn(List<Byte> values) {
      addCriterion("isActive not in", values, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveBetween(Byte value1, Byte value2) {
      addCriterion("isActive between", value1, value2, "isactive");
      return (Criteria) this;
    }

    public Criteria andIsactiveNotBetween(Byte value1, Byte value2) {
      addCriterion("isActive not between", value1, value2, "isactive");
      return (Criteria) this;
    }

    public Criteria andTimecreateIsNull() {
      addCriterion("timeCreate is null");
      return (Criteria) this;
    }

    public Criteria andTimecreateIsNotNull() {
      addCriterion("timeCreate is not null");
      return (Criteria) this;
    }

    public Criteria andTimecreateEqualTo(Date value) {
      addCriterion("timeCreate =", value, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateNotEqualTo(Date value) {
      addCriterion("timeCreate <>", value, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateGreaterThan(Date value) {
      addCriterion("timeCreate >", value, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateGreaterThanOrEqualTo(Date value) {
      addCriterion("timeCreate >=", value, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateLessThan(Date value) {
      addCriterion("timeCreate <", value, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateLessThanOrEqualTo(Date value) {
      addCriterion("timeCreate <=", value, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateIn(List<Date> values) {
      addCriterion("timeCreate in", values, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateNotIn(List<Date> values) {
      addCriterion("timeCreate not in", values, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateBetween(Date value1, Date value2) {
      addCriterion("timeCreate between", value1, value2, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimecreateNotBetween(Date value1, Date value2) {
      addCriterion("timeCreate not between", value1, value2, "timecreate");
      return (Criteria) this;
    }

    public Criteria andTimeopenIsNull() {
      addCriterion("timeOpen is null");
      return (Criteria) this;
    }

    public Criteria andTimeopenIsNotNull() {
      addCriterion("timeOpen is not null");
      return (Criteria) this;
    }

    public Criteria andTimeopenEqualTo(Date value) {
      addCriterion("timeOpen =", value, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenNotEqualTo(Date value) {
      addCriterion("timeOpen <>", value, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenGreaterThan(Date value) {
      addCriterion("timeOpen >", value, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenGreaterThanOrEqualTo(Date value) {
      addCriterion("timeOpen >=", value, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenLessThan(Date value) {
      addCriterion("timeOpen <", value, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenLessThanOrEqualTo(Date value) {
      addCriterion("timeOpen <=", value, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenIn(List<Date> values) {
      addCriterion("timeOpen in", values, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenNotIn(List<Date> values) {
      addCriterion("timeOpen not in", values, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenBetween(Date value1, Date value2) {
      addCriterion("timeOpen between", value1, value2, "timeopen");
      return (Criteria) this;
    }

    public Criteria andTimeopenNotBetween(Date value1, Date value2) {
      addCriterion("timeOpen not between", value1, value2, "timeopen");
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
