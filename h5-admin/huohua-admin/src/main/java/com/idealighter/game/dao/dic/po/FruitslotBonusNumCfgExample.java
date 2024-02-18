package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class FruitslotBonusNumCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public FruitslotBonusNumCfgExample() {
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

        public Criteria andOrangenumIsNull() {
            addCriterion("orangeNum is null");
            return (Criteria) this;
        }

        public Criteria andOrangenumIsNotNull() {
            addCriterion("orangeNum is not null");
            return (Criteria) this;
        }

        public Criteria andOrangenumEqualTo(Byte value) {
            addCriterion("orangeNum =", value, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumNotEqualTo(Byte value) {
            addCriterion("orangeNum <>", value, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumGreaterThan(Byte value) {
            addCriterion("orangeNum >", value, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumGreaterThanOrEqualTo(Byte value) {
            addCriterion("orangeNum >=", value, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumLessThan(Byte value) {
            addCriterion("orangeNum <", value, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumLessThanOrEqualTo(Byte value) {
            addCriterion("orangeNum <=", value, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumIn(List<Byte> values) {
            addCriterion("orangeNum in", values, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumNotIn(List<Byte> values) {
            addCriterion("orangeNum not in", values, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumBetween(Byte value1, Byte value2) {
            addCriterion("orangeNum between", value1, value2, "orangenum");
            return (Criteria) this;
        }

        public Criteria andOrangenumNotBetween(Byte value1, Byte value2) {
            addCriterion("orangeNum not between", value1, value2, "orangenum");
            return (Criteria) this;
        }

        public Criteria andBonusnumIsNull() {
            addCriterion("bonusNum is null");
            return (Criteria) this;
        }

        public Criteria andBonusnumIsNotNull() {
            addCriterion("bonusNum is not null");
            return (Criteria) this;
        }

        public Criteria andBonusnumEqualTo(Byte value) {
            addCriterion("bonusNum =", value, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumNotEqualTo(Byte value) {
            addCriterion("bonusNum <>", value, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumGreaterThan(Byte value) {
            addCriterion("bonusNum >", value, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumGreaterThanOrEqualTo(Byte value) {
            addCriterion("bonusNum >=", value, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumLessThan(Byte value) {
            addCriterion("bonusNum <", value, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumLessThanOrEqualTo(Byte value) {
            addCriterion("bonusNum <=", value, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumIn(List<Byte> values) {
            addCriterion("bonusNum in", values, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumNotIn(List<Byte> values) {
            addCriterion("bonusNum not in", values, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumBetween(Byte value1, Byte value2) {
            addCriterion("bonusNum between", value1, value2, "bonusnum");
            return (Criteria) this;
        }

        public Criteria andBonusnumNotBetween(Byte value1, Byte value2) {
            addCriterion("bonusNum not between", value1, value2, "bonusnum");
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