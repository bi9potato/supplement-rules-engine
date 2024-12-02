package com.ryan.rulesengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "isEligible", "baseAmount", "childrenAmount", "supplementAmount"})
public class OutputData {

    @JsonProperty("id")
    private String id; // ID from input
    @JsonProperty("isEligible")
    private boolean isEligible; // Eligibility, equal to "familyUnitInPayForDecember"
    @JsonProperty("baseAmount")
    private double baseAmount; // Base amount calculated from family composition
    @JsonProperty("childrenAmount")
    private double childrenAmount; // Additional amount for children
    @JsonProperty("supplementAmount")
    private double supplementAmount; // Total amount

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("isEligible")
    public boolean isEligible() {
        return isEligible;
    }

    @JsonProperty("isEligible")
    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public double getChildrenAmount() {
        return childrenAmount;
    }

    public void setChildrenAmount(double childrenAmount) {
        this.childrenAmount = childrenAmount;
    }

    public double getSupplementAmount() {
        return supplementAmount;
    }

    public void setSupplementAmount(double supplementAmount) {
        this.supplementAmount = supplementAmount;
    }
}
