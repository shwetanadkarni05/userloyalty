package com.loyalty.shared.domain;

/**
 */

public class Transaction {
    private Integer id;
    private Integer userId;
    private Integer points;

    public Transaction() {
    }

    public Transaction(Integer inUserId, Integer inPoints) {
        userId = inUserId;
        points = inPoints;
    }

    public Transaction(Integer inId, Integer inUserId, Integer inPoints) {
        id = inId;
        userId = inUserId;
        points = inPoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer inId) {
        id = inId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer inUserId) {
        userId = inUserId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer inPoints) {
        points = inPoints;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Transaction{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", points=").append(points);
        sb.append('}');
        return sb.toString();
    }
}
