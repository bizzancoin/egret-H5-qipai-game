package com.idealighter.game.dao.dic.po;

import java.util.ArrayList;
import java.util.List;

public class ThirdChannelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ThirdChannelExample() {
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

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("channel_id like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("channel_id not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNull() {
            addCriterion("channel_name is null");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNotNull() {
            addCriterion("channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNameEqualTo(String value) {
            addCriterion("channel_name =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotEqualTo(String value) {
            addCriterion("channel_name <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThan(String value) {
            addCriterion("channel_name >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_name >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThan(String value) {
            addCriterion("channel_name <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThanOrEqualTo(String value) {
            addCriterion("channel_name <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLike(String value) {
            addCriterion("channel_name like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotLike(String value) {
            addCriterion("channel_name not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIn(List<String> values) {
            addCriterion("channel_name in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotIn(List<String> values) {
            addCriterion("channel_name not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameBetween(String value1, String value2) {
            addCriterion("channel_name between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotBetween(String value1, String value2) {
            addCriterion("channel_name not between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkIsNull() {
            addCriterion("channel_remark is null");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkIsNotNull() {
            addCriterion("channel_remark is not null");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkEqualTo(String value) {
            addCriterion("channel_remark =", value, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkNotEqualTo(String value) {
            addCriterion("channel_remark <>", value, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkGreaterThan(String value) {
            addCriterion("channel_remark >", value, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("channel_remark >=", value, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkLessThan(String value) {
            addCriterion("channel_remark <", value, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkLessThanOrEqualTo(String value) {
            addCriterion("channel_remark <=", value, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkLike(String value) {
            addCriterion("channel_remark like", value, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkNotLike(String value) {
            addCriterion("channel_remark not like", value, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkIn(List<String> values) {
            addCriterion("channel_remark in", values, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkNotIn(List<String> values) {
            addCriterion("channel_remark not in", values, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkBetween(String value1, String value2) {
            addCriterion("channel_remark between", value1, value2, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelRemarkNotBetween(String value1, String value2) {
            addCriterion("channel_remark not between", value1, value2, "channelRemark");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyIsNull() {
            addCriterion("channel_des_key is null");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyIsNotNull() {
            addCriterion("channel_des_key is not null");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyEqualTo(String value) {
            addCriterion("channel_des_key =", value, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyNotEqualTo(String value) {
            addCriterion("channel_des_key <>", value, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyGreaterThan(String value) {
            addCriterion("channel_des_key >", value, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyGreaterThanOrEqualTo(String value) {
            addCriterion("channel_des_key >=", value, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyLessThan(String value) {
            addCriterion("channel_des_key <", value, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyLessThanOrEqualTo(String value) {
            addCriterion("channel_des_key <=", value, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyLike(String value) {
            addCriterion("channel_des_key like", value, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyNotLike(String value) {
            addCriterion("channel_des_key not like", value, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyIn(List<String> values) {
            addCriterion("channel_des_key in", values, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyNotIn(List<String> values) {
            addCriterion("channel_des_key not in", values, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyBetween(String value1, String value2) {
            addCriterion("channel_des_key between", value1, value2, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelDesKeyNotBetween(String value1, String value2) {
            addCriterion("channel_des_key not between", value1, value2, "channelDesKey");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyIsNull() {
            addCriterion("channel_md5_key is null");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyIsNotNull() {
            addCriterion("channel_md5_key is not null");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyEqualTo(String value) {
            addCriterion("channel_md5_key =", value, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyNotEqualTo(String value) {
            addCriterion("channel_md5_key <>", value, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyGreaterThan(String value) {
            addCriterion("channel_md5_key >", value, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyGreaterThanOrEqualTo(String value) {
            addCriterion("channel_md5_key >=", value, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyLessThan(String value) {
            addCriterion("channel_md5_key <", value, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyLessThanOrEqualTo(String value) {
            addCriterion("channel_md5_key <=", value, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyLike(String value) {
            addCriterion("channel_md5_key like", value, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyNotLike(String value) {
            addCriterion("channel_md5_key not like", value, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyIn(List<String> values) {
            addCriterion("channel_md5_key in", values, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyNotIn(List<String> values) {
            addCriterion("channel_md5_key not in", values, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyBetween(String value1, String value2) {
            addCriterion("channel_md5_key between", value1, value2, "channelMd5Key");
            return (Criteria) this;
        }

        public Criteria andChannelMd5KeyNotBetween(String value1, String value2) {
            addCriterion("channel_md5_key not between", value1, value2, "channelMd5Key");
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