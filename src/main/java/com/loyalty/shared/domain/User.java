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

    private List<Transfer> transfers;

    public User() {
    }

    public User(String inEmail, String inFirstName, String inLastName, Integer inPoints) {
        id = null;
        email = inEmail;
        firstName = inFirstName;
        lastName = inLastName;
        points = inPoints;
        transfers = new LinkedList<Transfer>();
    }

    public User(Integer inId, String inEmail, String inFirstName, String inLastName, Integer inPoints, List<Transfer> inTransfers) {
        id = inId;
        email = inEmail;
        firstName = inFirstName;
        lastName = inLastName;
        points = inPoints;
        transfers = inTransfers;
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

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> inTransfers) {
        transfers = inTransfers;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", points=").append(points);
        sb.append(", transfers=").append(transfers);
        sb.append('}');
        return sb.toString();
    }
}
