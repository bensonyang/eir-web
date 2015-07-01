package com.morningsidevc.po.gen;

import java.util.ArrayList;
import java.util.List;

public class UserFeedCounterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UserFeedCounterExample() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
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

        public Criteria andCounteridIsNull() {
            addCriterion("CounterId is null");
            return (Criteria) this;
        }

        public Criteria andCounteridIsNotNull() {
            addCriterion("CounterId is not null");
            return (Criteria) this;
        }

        public Criteria andCounteridEqualTo(Integer value) {
            addCriterion("CounterId =", value, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridNotEqualTo(Integer value) {
            addCriterion("CounterId <>", value, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridGreaterThan(Integer value) {
            addCriterion("CounterId >", value, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridGreaterThanOrEqualTo(Integer value) {
            addCriterion("CounterId >=", value, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridLessThan(Integer value) {
            addCriterion("CounterId <", value, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridLessThanOrEqualTo(Integer value) {
            addCriterion("CounterId <=", value, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridIn(List<Integer> values) {
            addCriterion("CounterId in", values, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridNotIn(List<Integer> values) {
            addCriterion("CounterId not in", values, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridBetween(Integer value1, Integer value2) {
            addCriterion("CounterId between", value1, value2, "counterid");
            return (Criteria) this;
        }

        public Criteria andCounteridNotBetween(Integer value1, Integer value2) {
            addCriterion("CounterId not between", value1, value2, "counterid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("UserId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("UserId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("UserId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("UserId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("UserId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("UserId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("UserId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("UserId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("UserId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("UserId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("UserId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andSumIsNull() {
            addCriterion("Sum is null");
            return (Criteria) this;
        }

        public Criteria andSumIsNotNull() {
            addCriterion("Sum is not null");
            return (Criteria) this;
        }

        public Criteria andSumEqualTo(Integer value) {
            addCriterion("Sum =", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumNotEqualTo(Integer value) {
            addCriterion("Sum <>", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumGreaterThan(Integer value) {
            addCriterion("Sum >", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sum >=", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumLessThan(Integer value) {
            addCriterion("Sum <", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumLessThanOrEqualTo(Integer value) {
            addCriterion("Sum <=", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumIn(List<Integer> values) {
            addCriterion("Sum in", values, "sum");
            return (Criteria) this;
        }

        public Criteria andSumNotIn(List<Integer> values) {
            addCriterion("Sum not in", values, "sum");
            return (Criteria) this;
        }

        public Criteria andSumBetween(Integer value1, Integer value2) {
            addCriterion("Sum between", value1, value2, "sum");
            return (Criteria) this;
        }

        public Criteria andSumNotBetween(Integer value1, Integer value2) {
            addCriterion("Sum not between", value1, value2, "sum");
            return (Criteria) this;
        }

        public Criteria andCountertypeIsNull() {
            addCriterion("CounterType is null");
            return (Criteria) this;
        }

        public Criteria andCountertypeIsNotNull() {
            addCriterion("CounterType is not null");
            return (Criteria) this;
        }

        public Criteria andCountertypeEqualTo(Byte value) {
            addCriterion("CounterType =", value, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeNotEqualTo(Byte value) {
            addCriterion("CounterType <>", value, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeGreaterThan(Byte value) {
            addCriterion("CounterType >", value, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("CounterType >=", value, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeLessThan(Byte value) {
            addCriterion("CounterType <", value, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeLessThanOrEqualTo(Byte value) {
            addCriterion("CounterType <=", value, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeIn(List<Byte> values) {
            addCriterion("CounterType in", values, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeNotIn(List<Byte> values) {
            addCriterion("CounterType not in", values, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeBetween(Byte value1, Byte value2) {
            addCriterion("CounterType between", value1, value2, "countertype");
            return (Criteria) this;
        }

        public Criteria andCountertypeNotBetween(Byte value1, Byte value2) {
            addCriterion("CounterType not between", value1, value2, "countertype");
            return (Criteria) this;
        }
    }

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