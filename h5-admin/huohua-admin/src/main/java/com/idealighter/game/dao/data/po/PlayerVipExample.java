package com.idealighter.game.dao.data.po;

import java.util.ArrayList;
import java.util.List;

public class PlayerVipExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public PlayerVipExample() {
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

        public Criteria andPlayerIdIsNull() {
            addCriterion("player_id is null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNotNull() {
            addCriterion("player_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdEqualTo(Long value) {
            addCriterion("player_id =", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotEqualTo(Long value) {
            addCriterion("player_id <>", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThan(Long value) {
            addCriterion("player_id >", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("player_id >=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThan(Long value) {
            addCriterion("player_id <", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThanOrEqualTo(Long value) {
            addCriterion("player_id <=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIn(List<Long> values) {
            addCriterion("player_id in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotIn(List<Long> values) {
            addCriterion("player_id not in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdBetween(Long value1, Long value2) {
            addCriterion("player_id between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotBetween(Long value1, Long value2) {
            addCriterion("player_id not between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectIsNull() {
            addCriterion("play_item_effect is null");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectIsNotNull() {
            addCriterion("play_item_effect is not null");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectEqualTo(Boolean value) {
            addCriterion("play_item_effect =", value, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectNotEqualTo(Boolean value) {
            addCriterion("play_item_effect <>", value, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectGreaterThan(Boolean value) {
            addCriterion("play_item_effect >", value, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectGreaterThanOrEqualTo(Boolean value) {
            addCriterion("play_item_effect >=", value, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectLessThan(Boolean value) {
            addCriterion("play_item_effect <", value, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectLessThanOrEqualTo(Boolean value) {
            addCriterion("play_item_effect <=", value, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectIn(List<Boolean> values) {
            addCriterion("play_item_effect in", values, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectNotIn(List<Boolean> values) {
            addCriterion("play_item_effect not in", values, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectBetween(Boolean value1, Boolean value2) {
            addCriterion("play_item_effect between", value1, value2, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andPlayItemEffectNotBetween(Boolean value1, Boolean value2) {
            addCriterion("play_item_effect not between", value1, value2, "playItemEffect");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldIsNull() {
            addCriterion("table_limit_gold is null");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldIsNotNull() {
            addCriterion("table_limit_gold is not null");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldEqualTo(Long value) {
            addCriterion("table_limit_gold =", value, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldNotEqualTo(Long value) {
            addCriterion("table_limit_gold <>", value, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldGreaterThan(Long value) {
            addCriterion("table_limit_gold >", value, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldGreaterThanOrEqualTo(Long value) {
            addCriterion("table_limit_gold >=", value, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldLessThan(Long value) {
            addCriterion("table_limit_gold <", value, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldLessThanOrEqualTo(Long value) {
            addCriterion("table_limit_gold <=", value, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldIn(List<Long> values) {
            addCriterion("table_limit_gold in", values, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldNotIn(List<Long> values) {
            addCriterion("table_limit_gold not in", values, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldBetween(Long value1, Long value2) {
            addCriterion("table_limit_gold between", value1, value2, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldNotBetween(Long value1, Long value2) {
            addCriterion("table_limit_gold not between", value1, value2, "tableLimitGold");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledIsNull() {
            addCriterion("table_limit_gold_abled is null");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledIsNotNull() {
            addCriterion("table_limit_gold_abled is not null");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledEqualTo(Boolean value) {
            addCriterion("table_limit_gold_abled =", value, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledNotEqualTo(Boolean value) {
            addCriterion("table_limit_gold_abled <>", value, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledGreaterThan(Boolean value) {
            addCriterion("table_limit_gold_abled >", value, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("table_limit_gold_abled >=", value, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledLessThan(Boolean value) {
            addCriterion("table_limit_gold_abled <", value, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledLessThanOrEqualTo(Boolean value) {
            addCriterion("table_limit_gold_abled <=", value, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledIn(List<Boolean> values) {
            addCriterion("table_limit_gold_abled in", values, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledNotIn(List<Boolean> values) {
            addCriterion("table_limit_gold_abled not in", values, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledBetween(Boolean value1, Boolean value2) {
            addCriterion("table_limit_gold_abled between", value1, value2, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitGoldAbledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("table_limit_gold_abled not between", value1, value2, "tableLimitGoldAbled");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpIsNull() {
            addCriterion("table_limit_ip is null");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpIsNotNull() {
            addCriterion("table_limit_ip is not null");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpEqualTo(Boolean value) {
            addCriterion("table_limit_ip =", value, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpNotEqualTo(Boolean value) {
            addCriterion("table_limit_ip <>", value, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpGreaterThan(Boolean value) {
            addCriterion("table_limit_ip >", value, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpGreaterThanOrEqualTo(Boolean value) {
            addCriterion("table_limit_ip >=", value, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpLessThan(Boolean value) {
            addCriterion("table_limit_ip <", value, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpLessThanOrEqualTo(Boolean value) {
            addCriterion("table_limit_ip <=", value, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpIn(List<Boolean> values) {
            addCriterion("table_limit_ip in", values, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpNotIn(List<Boolean> values) {
            addCriterion("table_limit_ip not in", values, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpBetween(Boolean value1, Boolean value2) {
            addCriterion("table_limit_ip between", value1, value2, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTableLimitIpNotBetween(Boolean value1, Boolean value2) {
            addCriterion("table_limit_ip not between", value1, value2, "tableLimitIp");
            return (Criteria) this;
        }

        public Criteria andTablePwdIsNull() {
            addCriterion("table_pwd is null");
            return (Criteria) this;
        }

        public Criteria andTablePwdIsNotNull() {
            addCriterion("table_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andTablePwdEqualTo(String value) {
            addCriterion("table_pwd =", value, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdNotEqualTo(String value) {
            addCriterion("table_pwd <>", value, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdGreaterThan(String value) {
            addCriterion("table_pwd >", value, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdGreaterThanOrEqualTo(String value) {
            addCriterion("table_pwd >=", value, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdLessThan(String value) {
            addCriterion("table_pwd <", value, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdLessThanOrEqualTo(String value) {
            addCriterion("table_pwd <=", value, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdLike(String value) {
            addCriterion("table_pwd like", value, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdNotLike(String value) {
            addCriterion("table_pwd not like", value, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdIn(List<String> values) {
            addCriterion("table_pwd in", values, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdNotIn(List<String> values) {
            addCriterion("table_pwd not in", values, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdBetween(String value1, String value2) {
            addCriterion("table_pwd between", value1, value2, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdNotBetween(String value1, String value2) {
            addCriterion("table_pwd not between", value1, value2, "tablePwd");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledIsNull() {
            addCriterion("table_pwd_abled is null");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledIsNotNull() {
            addCriterion("table_pwd_abled is not null");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledEqualTo(Boolean value) {
            addCriterion("table_pwd_abled =", value, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledNotEqualTo(Boolean value) {
            addCriterion("table_pwd_abled <>", value, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledGreaterThan(Boolean value) {
            addCriterion("table_pwd_abled >", value, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("table_pwd_abled >=", value, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledLessThan(Boolean value) {
            addCriterion("table_pwd_abled <", value, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledLessThanOrEqualTo(Boolean value) {
            addCriterion("table_pwd_abled <=", value, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledIn(List<Boolean> values) {
            addCriterion("table_pwd_abled in", values, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledNotIn(List<Boolean> values) {
            addCriterion("table_pwd_abled not in", values, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledBetween(Boolean value1, Boolean value2) {
            addCriterion("table_pwd_abled between", value1, value2, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andTablePwdAbledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("table_pwd_abled not between", value1, value2, "tablePwdAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledIsNull() {
            addCriterion("room_setting_abled is null");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledIsNotNull() {
            addCriterion("room_setting_abled is not null");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledEqualTo(Boolean value) {
            addCriterion("room_setting_abled =", value, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledNotEqualTo(Boolean value) {
            addCriterion("room_setting_abled <>", value, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledGreaterThan(Boolean value) {
            addCriterion("room_setting_abled >", value, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("room_setting_abled >=", value, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledLessThan(Boolean value) {
            addCriterion("room_setting_abled <", value, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledLessThanOrEqualTo(Boolean value) {
            addCriterion("room_setting_abled <=", value, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledIn(List<Boolean> values) {
            addCriterion("room_setting_abled in", values, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledNotIn(List<Boolean> values) {
            addCriterion("room_setting_abled not in", values, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledBetween(Boolean value1, Boolean value2) {
            addCriterion("room_setting_abled between", value1, value2, "roomSettingAbled");
            return (Criteria) this;
        }

        public Criteria andRoomSettingAbledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("room_setting_abled not between", value1, value2, "roomSettingAbled");
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