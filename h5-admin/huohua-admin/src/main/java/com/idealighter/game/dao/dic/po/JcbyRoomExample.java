package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JcbyRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public JcbyRoomExample() {
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

        public Criteria andMaxNumIsNull() {
            addCriterion("maxNum is null");
            return (Criteria) this;
        }

        public Criteria andMaxNumIsNotNull() {
            addCriterion("maxNum is not null");
            return (Criteria) this;
        }

        public Criteria andMaxNumEqualTo(Integer value) {
            addCriterion("maxNum =", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotEqualTo(Integer value) {
            addCriterion("maxNum <>", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumGreaterThan(Integer value) {
            addCriterion("maxNum >", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxNum >=", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumLessThan(Integer value) {
            addCriterion("maxNum <", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumLessThanOrEqualTo(Integer value) {
            addCriterion("maxNum <=", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumIn(List<Integer> values) {
            addCriterion("maxNum in", values, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotIn(List<Integer> values) {
            addCriterion("maxNum not in", values, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumBetween(Integer value1, Integer value2) {
            addCriterion("maxNum between", value1, value2, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotBetween(Integer value1, Integer value2) {
            addCriterion("maxNum not between", value1, value2, "maxNum");
            return (Criteria) this;
        }

        public Criteria andScencesIsNull() {
            addCriterion("scences is null");
            return (Criteria) this;
        }

        public Criteria andScencesIsNotNull() {
            addCriterion("scences is not null");
            return (Criteria) this;
        }

        public Criteria andScencesEqualTo(String value) {
            addCriterion("scences =", value, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesNotEqualTo(String value) {
            addCriterion("scences <>", value, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesGreaterThan(String value) {
            addCriterion("scences >", value, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesGreaterThanOrEqualTo(String value) {
            addCriterion("scences >=", value, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesLessThan(String value) {
            addCriterion("scences <", value, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesLessThanOrEqualTo(String value) {
            addCriterion("scences <=", value, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesLike(String value) {
            addCriterion("scences like", value, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesNotLike(String value) {
            addCriterion("scences not like", value, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesIn(List<String> values) {
            addCriterion("scences in", values, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesNotIn(List<String> values) {
            addCriterion("scences not in", values, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesBetween(String value1, String value2) {
            addCriterion("scences between", value1, value2, "scences");
            return (Criteria) this;
        }

        public Criteria andScencesNotBetween(String value1, String value2) {
            addCriterion("scences not between", value1, value2, "scences");
            return (Criteria) this;
        }

        public Criteria andLiKuiIsNull() {
            addCriterion("liKui is null");
            return (Criteria) this;
        }

        public Criteria andLiKuiIsNotNull() {
            addCriterion("liKui is not null");
            return (Criteria) this;
        }

        public Criteria andLiKuiEqualTo(Integer value) {
            addCriterion("liKui =", value, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiNotEqualTo(Integer value) {
            addCriterion("liKui <>", value, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiGreaterThan(Integer value) {
            addCriterion("liKui >", value, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiGreaterThanOrEqualTo(Integer value) {
            addCriterion("liKui >=", value, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiLessThan(Integer value) {
            addCriterion("liKui <", value, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiLessThanOrEqualTo(Integer value) {
            addCriterion("liKui <=", value, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiIn(List<Integer> values) {
            addCriterion("liKui in", values, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiNotIn(List<Integer> values) {
            addCriterion("liKui not in", values, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiBetween(Integer value1, Integer value2) {
            addCriterion("liKui between", value1, value2, "liKui");
            return (Criteria) this;
        }

        public Criteria andLiKuiNotBetween(Integer value1, Integer value2) {
            addCriterion("liKui not between", value1, value2, "liKui");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleIsNull() {
            addCriterion("powerBatteryMultiple is null");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleIsNotNull() {
            addCriterion("powerBatteryMultiple is not null");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleEqualTo(Integer value) {
            addCriterion("powerBatteryMultiple =", value, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleNotEqualTo(Integer value) {
            addCriterion("powerBatteryMultiple <>", value, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleGreaterThan(Integer value) {
            addCriterion("powerBatteryMultiple >", value, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleGreaterThanOrEqualTo(Integer value) {
            addCriterion("powerBatteryMultiple >=", value, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleLessThan(Integer value) {
            addCriterion("powerBatteryMultiple <", value, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleLessThanOrEqualTo(Integer value) {
            addCriterion("powerBatteryMultiple <=", value, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleIn(List<Integer> values) {
            addCriterion("powerBatteryMultiple in", values, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleNotIn(List<Integer> values) {
            addCriterion("powerBatteryMultiple not in", values, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleBetween(Integer value1, Integer value2) {
            addCriterion("powerBatteryMultiple between", value1, value2, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryMultipleNotBetween(Integer value1, Integer value2) {
            addCriterion("powerBatteryMultiple not between", value1, value2, "powerBatteryMultiple");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProIsNull() {
            addCriterion("powerBatteryPro is null");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProIsNotNull() {
            addCriterion("powerBatteryPro is not null");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProEqualTo(Integer value) {
            addCriterion("powerBatteryPro =", value, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProNotEqualTo(Integer value) {
            addCriterion("powerBatteryPro <>", value, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProGreaterThan(Integer value) {
            addCriterion("powerBatteryPro >", value, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProGreaterThanOrEqualTo(Integer value) {
            addCriterion("powerBatteryPro >=", value, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProLessThan(Integer value) {
            addCriterion("powerBatteryPro <", value, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProLessThanOrEqualTo(Integer value) {
            addCriterion("powerBatteryPro <=", value, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProIn(List<Integer> values) {
            addCriterion("powerBatteryPro in", values, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProNotIn(List<Integer> values) {
            addCriterion("powerBatteryPro not in", values, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProBetween(Integer value1, Integer value2) {
            addCriterion("powerBatteryPro between", value1, value2, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryProNotBetween(Integer value1, Integer value2) {
            addCriterion("powerBatteryPro not between", value1, value2, "powerBatteryPro");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeIsNull() {
            addCriterion("powerBatteryTime is null");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeIsNotNull() {
            addCriterion("powerBatteryTime is not null");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeEqualTo(Integer value) {
            addCriterion("powerBatteryTime =", value, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeNotEqualTo(Integer value) {
            addCriterion("powerBatteryTime <>", value, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeGreaterThan(Integer value) {
            addCriterion("powerBatteryTime >", value, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("powerBatteryTime >=", value, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeLessThan(Integer value) {
            addCriterion("powerBatteryTime <", value, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeLessThanOrEqualTo(Integer value) {
            addCriterion("powerBatteryTime <=", value, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeIn(List<Integer> values) {
            addCriterion("powerBatteryTime in", values, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeNotIn(List<Integer> values) {
            addCriterion("powerBatteryTime not in", values, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeBetween(Integer value1, Integer value2) {
            addCriterion("powerBatteryTime between", value1, value2, "powerBatteryTime");
            return (Criteria) this;
        }

        public Criteria andPowerBatteryTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("powerBatteryTime not between", value1, value2, "powerBatteryTime");
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

        public Criteria andLowerEqualTo(Long value) {
            addCriterion("lower =", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerNotEqualTo(Long value) {
            addCriterion("lower <>", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerGreaterThan(Long value) {
            addCriterion("lower >", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerGreaterThanOrEqualTo(Long value) {
            addCriterion("lower >=", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerLessThan(Long value) {
            addCriterion("lower <", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerLessThanOrEqualTo(Long value) {
            addCriterion("lower <=", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerIn(List<Long> values) {
            addCriterion("lower in", values, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerNotIn(List<Long> values) {
            addCriterion("lower not in", values, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerBetween(Long value1, Long value2) {
            addCriterion("lower between", value1, value2, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerNotBetween(Long value1, Long value2) {
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

        public Criteria andUpperEqualTo(Long value) {
            addCriterion("upper =", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperNotEqualTo(Long value) {
            addCriterion("upper <>", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperGreaterThan(Long value) {
            addCriterion("upper >", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperGreaterThanOrEqualTo(Long value) {
            addCriterion("upper >=", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperLessThan(Long value) {
            addCriterion("upper <", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperLessThanOrEqualTo(Long value) {
            addCriterion("upper <=", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperIn(List<Long> values) {
            addCriterion("upper in", values, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperNotIn(List<Long> values) {
            addCriterion("upper not in", values, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperBetween(Long value1, Long value2) {
            addCriterion("upper between", value1, value2, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperNotBetween(Long value1, Long value2) {
            addCriterion("upper not between", value1, value2, "upper");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleIsNull() {
            addCriterion("ordinarPeople is null");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleIsNotNull() {
            addCriterion("ordinarPeople is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleEqualTo(Integer value) {
            addCriterion("ordinarPeople =", value, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleNotEqualTo(Integer value) {
            addCriterion("ordinarPeople <>", value, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleGreaterThan(Integer value) {
            addCriterion("ordinarPeople >", value, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("ordinarPeople >=", value, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleLessThan(Integer value) {
            addCriterion("ordinarPeople <", value, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleLessThanOrEqualTo(Integer value) {
            addCriterion("ordinarPeople <=", value, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleIn(List<Integer> values) {
            addCriterion("ordinarPeople in", values, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleNotIn(List<Integer> values) {
            addCriterion("ordinarPeople not in", values, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleBetween(Integer value1, Integer value2) {
            addCriterion("ordinarPeople between", value1, value2, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andOrdinarPeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("ordinarPeople not between", value1, value2, "ordinarPeople");
            return (Criteria) this;
        }

        public Criteria andRobotRatioIsNull() {
            addCriterion("robotRatio is null");
            return (Criteria) this;
        }

        public Criteria andRobotRatioIsNotNull() {
            addCriterion("robotRatio is not null");
            return (Criteria) this;
        }

        public Criteria andRobotRatioEqualTo(Integer value) {
            addCriterion("robotRatio =", value, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioNotEqualTo(Integer value) {
            addCriterion("robotRatio <>", value, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioGreaterThan(Integer value) {
            addCriterion("robotRatio >", value, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioGreaterThanOrEqualTo(Integer value) {
            addCriterion("robotRatio >=", value, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioLessThan(Integer value) {
            addCriterion("robotRatio <", value, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioLessThanOrEqualTo(Integer value) {
            addCriterion("robotRatio <=", value, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioIn(List<Integer> values) {
            addCriterion("robotRatio in", values, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioNotIn(List<Integer> values) {
            addCriterion("robotRatio not in", values, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioBetween(Integer value1, Integer value2) {
            addCriterion("robotRatio between", value1, value2, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andRobotRatioNotBetween(Integer value1, Integer value2) {
            addCriterion("robotRatio not between", value1, value2, "robotRatio");
            return (Criteria) this;
        }

        public Criteria andProportionGoldIsNull() {
            addCriterion("proportionGold is null");
            return (Criteria) this;
        }

        public Criteria andProportionGoldIsNotNull() {
            addCriterion("proportionGold is not null");
            return (Criteria) this;
        }

        public Criteria andProportionGoldEqualTo(Integer value) {
            addCriterion("proportionGold =", value, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldNotEqualTo(Integer value) {
            addCriterion("proportionGold <>", value, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldGreaterThan(Integer value) {
            addCriterion("proportionGold >", value, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("proportionGold >=", value, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldLessThan(Integer value) {
            addCriterion("proportionGold <", value, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldLessThanOrEqualTo(Integer value) {
            addCriterion("proportionGold <=", value, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldIn(List<Integer> values) {
            addCriterion("proportionGold in", values, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldNotIn(List<Integer> values) {
            addCriterion("proportionGold not in", values, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldBetween(Integer value1, Integer value2) {
            addCriterion("proportionGold between", value1, value2, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("proportionGold not between", value1, value2, "proportionGold");
            return (Criteria) this;
        }

        public Criteria andProportionChipsIsNull() {
            addCriterion("proportionChips is null");
            return (Criteria) this;
        }

        public Criteria andProportionChipsIsNotNull() {
            addCriterion("proportionChips is not null");
            return (Criteria) this;
        }

        public Criteria andProportionChipsEqualTo(Integer value) {
            addCriterion("proportionChips =", value, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsNotEqualTo(Integer value) {
            addCriterion("proportionChips <>", value, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsGreaterThan(Integer value) {
            addCriterion("proportionChips >", value, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsGreaterThanOrEqualTo(Integer value) {
            addCriterion("proportionChips >=", value, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsLessThan(Integer value) {
            addCriterion("proportionChips <", value, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsLessThanOrEqualTo(Integer value) {
            addCriterion("proportionChips <=", value, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsIn(List<Integer> values) {
            addCriterion("proportionChips in", values, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsNotIn(List<Integer> values) {
            addCriterion("proportionChips not in", values, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsBetween(Integer value1, Integer value2) {
            addCriterion("proportionChips between", value1, value2, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andProportionChipsNotBetween(Integer value1, Integer value2) {
            addCriterion("proportionChips not between", value1, value2, "proportionChips");
            return (Criteria) this;
        }

        public Criteria andTableNumIsNull() {
            addCriterion("tableNum is null");
            return (Criteria) this;
        }

        public Criteria andTableNumIsNotNull() {
            addCriterion("tableNum is not null");
            return (Criteria) this;
        }

        public Criteria andTableNumEqualTo(Integer value) {
            addCriterion("tableNum =", value, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumNotEqualTo(Integer value) {
            addCriterion("tableNum <>", value, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumGreaterThan(Integer value) {
            addCriterion("tableNum >", value, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("tableNum >=", value, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumLessThan(Integer value) {
            addCriterion("tableNum <", value, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumLessThanOrEqualTo(Integer value) {
            addCriterion("tableNum <=", value, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumIn(List<Integer> values) {
            addCriterion("tableNum in", values, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumNotIn(List<Integer> values) {
            addCriterion("tableNum not in", values, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumBetween(Integer value1, Integer value2) {
            addCriterion("tableNum between", value1, value2, "tableNum");
            return (Criteria) this;
        }

        public Criteria andTableNumNotBetween(Integer value1, Integer value2) {
            addCriterion("tableNum not between", value1, value2, "tableNum");
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

        public Criteria andInTypeIsNull() {
            addCriterion("inType is null");
            return (Criteria) this;
        }

        public Criteria andInTypeIsNotNull() {
            addCriterion("inType is not null");
            return (Criteria) this;
        }

        public Criteria andInTypeEqualTo(Integer value) {
            addCriterion("inType =", value, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeNotEqualTo(Integer value) {
            addCriterion("inType <>", value, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeGreaterThan(Integer value) {
            addCriterion("inType >", value, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("inType >=", value, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeLessThan(Integer value) {
            addCriterion("inType <", value, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeLessThanOrEqualTo(Integer value) {
            addCriterion("inType <=", value, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeIn(List<Integer> values) {
            addCriterion("inType in", values, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeNotIn(List<Integer> values) {
            addCriterion("inType not in", values, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeBetween(Integer value1, Integer value2) {
            addCriterion("inType between", value1, value2, "inType");
            return (Criteria) this;
        }

        public Criteria andInTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("inType not between", value1, value2, "inType");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNull() {
            addCriterion("isActive is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNotNull() {
            addCriterion("isActive is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveEqualTo(Byte value) {
            addCriterion("isActive =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Byte value) {
            addCriterion("isActive <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Byte value) {
            addCriterion("isActive >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Byte value) {
            addCriterion("isActive >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Byte value) {
            addCriterion("isActive <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Byte value) {
            addCriterion("isActive <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Byte> values) {
            addCriterion("isActive in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Byte> values) {
            addCriterion("isActive not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Byte value1, Byte value2) {
            addCriterion("isActive between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Byte value1, Byte value2) {
            addCriterion("isActive not between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andTimeCreateIsNull() {
            addCriterion("timeCreate is null");
            return (Criteria) this;
        }

        public Criteria andTimeCreateIsNotNull() {
            addCriterion("timeCreate is not null");
            return (Criteria) this;
        }

        public Criteria andTimeCreateEqualTo(Date value) {
            addCriterion("timeCreate =", value, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateNotEqualTo(Date value) {
            addCriterion("timeCreate <>", value, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateGreaterThan(Date value) {
            addCriterion("timeCreate >", value, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("timeCreate >=", value, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateLessThan(Date value) {
            addCriterion("timeCreate <", value, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateLessThanOrEqualTo(Date value) {
            addCriterion("timeCreate <=", value, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateIn(List<Date> values) {
            addCriterion("timeCreate in", values, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateNotIn(List<Date> values) {
            addCriterion("timeCreate not in", values, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateBetween(Date value1, Date value2) {
            addCriterion("timeCreate between", value1, value2, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeCreateNotBetween(Date value1, Date value2) {
            addCriterion("timeCreate not between", value1, value2, "timeCreate");
            return (Criteria) this;
        }

        public Criteria andTimeOpenIsNull() {
            addCriterion("timeOpen is null");
            return (Criteria) this;
        }

        public Criteria andTimeOpenIsNotNull() {
            addCriterion("timeOpen is not null");
            return (Criteria) this;
        }

        public Criteria andTimeOpenEqualTo(Date value) {
            addCriterion("timeOpen =", value, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenNotEqualTo(Date value) {
            addCriterion("timeOpen <>", value, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenGreaterThan(Date value) {
            addCriterion("timeOpen >", value, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenGreaterThanOrEqualTo(Date value) {
            addCriterion("timeOpen >=", value, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenLessThan(Date value) {
            addCriterion("timeOpen <", value, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenLessThanOrEqualTo(Date value) {
            addCriterion("timeOpen <=", value, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenIn(List<Date> values) {
            addCriterion("timeOpen in", values, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenNotIn(List<Date> values) {
            addCriterion("timeOpen not in", values, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenBetween(Date value1, Date value2) {
            addCriterion("timeOpen between", value1, value2, "timeOpen");
            return (Criteria) this;
        }

        public Criteria andTimeOpenNotBetween(Date value1, Date value2) {
            addCriterion("timeOpen not between", value1, value2, "timeOpen");
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