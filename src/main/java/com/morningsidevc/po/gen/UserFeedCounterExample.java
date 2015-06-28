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

        public Criteria andFeedsumIsNull() {
            addCriterion("FeedSum is null");
            return (Criteria) this;
        }

        public Criteria andFeedsumIsNotNull() {
            addCriterion("FeedSum is not null");
            return (Criteria) this;
        }

        public Criteria andFeedsumEqualTo(Integer value) {
            addCriterion("FeedSum =", value, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumNotEqualTo(Integer value) {
            addCriterion("FeedSum <>", value, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumGreaterThan(Integer value) {
            addCriterion("FeedSum >", value, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumGreaterThanOrEqualTo(Integer value) {
            addCriterion("FeedSum >=", value, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumLessThan(Integer value) {
            addCriterion("FeedSum <", value, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumLessThanOrEqualTo(Integer value) {
            addCriterion("FeedSum <=", value, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumIn(List<Integer> values) {
            addCriterion("FeedSum in", values, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumNotIn(List<Integer> values) {
            addCriterion("FeedSum not in", values, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumBetween(Integer value1, Integer value2) {
            addCriterion("FeedSum between", value1, value2, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedsumNotBetween(Integer value1, Integer value2) {
            addCriterion("FeedSum not between", value1, value2, "feedsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumIsNull() {
            addCriterion("FeedCommentSum is null");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumIsNotNull() {
            addCriterion("FeedCommentSum is not null");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumEqualTo(Integer value) {
            addCriterion("FeedCommentSum =", value, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumNotEqualTo(Integer value) {
            addCriterion("FeedCommentSum <>", value, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumGreaterThan(Integer value) {
            addCriterion("FeedCommentSum >", value, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumGreaterThanOrEqualTo(Integer value) {
            addCriterion("FeedCommentSum >=", value, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumLessThan(Integer value) {
            addCriterion("FeedCommentSum <", value, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumLessThanOrEqualTo(Integer value) {
            addCriterion("FeedCommentSum <=", value, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumIn(List<Integer> values) {
            addCriterion("FeedCommentSum in", values, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumNotIn(List<Integer> values) {
            addCriterion("FeedCommentSum not in", values, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumBetween(Integer value1, Integer value2) {
            addCriterion("FeedCommentSum between", value1, value2, "feedcommentsum");
            return (Criteria) this;
        }

        public Criteria andFeedcommentsumNotBetween(Integer value1, Integer value2) {
            addCriterion("FeedCommentSum not between", value1, value2, "feedcommentsum");
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