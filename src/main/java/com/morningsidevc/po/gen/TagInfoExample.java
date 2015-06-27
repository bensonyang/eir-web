package com.morningsidevc.po.gen;

import java.util.ArrayList;
import java.util.List;

public class TagInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TagInfoExample() {
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

        public Criteria andTagidIsNull() {
            addCriterion("TagId is null");
            return (Criteria) this;
        }

        public Criteria andTagidIsNotNull() {
            addCriterion("TagId is not null");
            return (Criteria) this;
        }

        public Criteria andTagidEqualTo(Integer value) {
            addCriterion("TagId =", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidNotEqualTo(Integer value) {
            addCriterion("TagId <>", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidGreaterThan(Integer value) {
            addCriterion("TagId >", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidGreaterThanOrEqualTo(Integer value) {
            addCriterion("TagId >=", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidLessThan(Integer value) {
            addCriterion("TagId <", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidLessThanOrEqualTo(Integer value) {
            addCriterion("TagId <=", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidIn(List<Integer> values) {
            addCriterion("TagId in", values, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidNotIn(List<Integer> values) {
            addCriterion("TagId not in", values, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidBetween(Integer value1, Integer value2) {
            addCriterion("TagId between", value1, value2, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidNotBetween(Integer value1, Integer value2) {
            addCriterion("TagId not between", value1, value2, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagnameIsNull() {
            addCriterion("TagName is null");
            return (Criteria) this;
        }

        public Criteria andTagnameIsNotNull() {
            addCriterion("TagName is not null");
            return (Criteria) this;
        }

        public Criteria andTagnameEqualTo(String value) {
            addCriterion("TagName =", value, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameNotEqualTo(String value) {
            addCriterion("TagName <>", value, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameGreaterThan(String value) {
            addCriterion("TagName >", value, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameGreaterThanOrEqualTo(String value) {
            addCriterion("TagName >=", value, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameLessThan(String value) {
            addCriterion("TagName <", value, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameLessThanOrEqualTo(String value) {
            addCriterion("TagName <=", value, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameLike(String value) {
            addCriterion("TagName like", value, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameNotLike(String value) {
            addCriterion("TagName not like", value, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameIn(List<String> values) {
            addCriterion("TagName in", values, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameNotIn(List<String> values) {
            addCriterion("TagName not in", values, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameBetween(String value1, String value2) {
            addCriterion("TagName between", value1, value2, "tagname");
            return (Criteria) this;
        }

        public Criteria andTagnameNotBetween(String value1, String value2) {
            addCriterion("TagName not between", value1, value2, "tagname");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("Status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOrderIsNull() {
            addCriterion("Order is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsNotNull() {
            addCriterion("Order is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEqualTo(Integer value) {
            addCriterion("Order =", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotEqualTo(Integer value) {
            addCriterion("Order <>", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThan(Integer value) {
            addCriterion("Order >", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("Order >=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThan(Integer value) {
            addCriterion("Order <", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThanOrEqualTo(Integer value) {
            addCriterion("Order <=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderIn(List<Integer> values) {
            addCriterion("Order in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotIn(List<Integer> values) {
            addCriterion("Order not in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderBetween(Integer value1, Integer value2) {
            addCriterion("Order between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("Order not between", value1, value2, "order");
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