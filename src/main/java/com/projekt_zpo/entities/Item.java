package com.projekt_zpo.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer userId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Integer price;
    @Column
    private Boolean isNegotiable;
    @Column
    private Integer noOfVisits;
    @Column
    private String photoUrl;
    @Column
    private Integer categoryId;
    @Column
    private String addedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getNegotiable() {
        return isNegotiable;
    }

    public void setNegotiable(Boolean negotiable) {
        isNegotiable = negotiable;
    }

    public Integer getNoOfVisits() {
        return noOfVisits;
    }

    public void setNoOfVisits(Integer noOfVisits) {
        this.noOfVisits = noOfVisits;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Item() {
    }

    public Item(Integer userId, String name, String description, Integer price, Boolean isNegotiable, String photoUrl, Integer categoryId) {

        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isNegotiable = isNegotiable;
        this.noOfVisits = 0;
        this.photoUrl = photoUrl;
        this.categoryId = categoryId;
        //this.addedOn= LocalDateTime.now();
    }
}

