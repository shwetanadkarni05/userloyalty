package com.loyalty.shared.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transfer {
    private Integer id;
    private Integer userId;
    private Integer amount;

    public Transfer() {
    }

    public Transfer(Integer inUserId, Integer inAmount) {
        userId = inUserId;
        amount = inAmount;
    }

    public Transfer(Integer inId, Integer inUserId, Integer inAmount) {
        id = inId;
        userId = inUserId;
        amount = inAmount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer inAmount) {
        amount = inAmount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Transfer{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
