package com.loyalty.shared.domain
        ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;


/**
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer points;

    private List<Transaction> transactions;

    public User() {
    }

    public User(String inEmail, String inFirstName, String inLastName, Integer inPoints) {
        id = null;
        email = inEmail;
        firstName = inFirstName;
        lastName = inLastName;
        points = inPoints;
        transactions = new LinkedList<Transaction>();
    }

    public User(Integer inId, String inEmail, String inFirstName, String inLastName, Integer inPoints, List<Transaction> inTransactions) {
        id = inId;
        email = inEmail;
        firstName = inFirstName;
        lastName = inLastName;
        points = inPoints;
        transactions = inTransactions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer inId) {
        id = inId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String inEmail) {
        email = inEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String inFirstName) {
        firstName = inFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String inLastName) {
        lastName = inLastName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer inPoints) {
        points = inPoints;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> inTransactions) {
        transactions = inTransactions;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", points=").append(points);
        sb.append(", transactions=").append(transactions);
        sb.append('}');
        return sb.toString();
    }
}
