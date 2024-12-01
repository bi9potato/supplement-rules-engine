package com.ryan.rulesengine.model;

public class OutputData {

    private String id; // ID from input
    private boolean isEligible; // Eligibility, equal to "familyUnitInPayForDecember"
    private double baseAmount; // Base amount calculated from family composition
    private double childrenAmount; // Additional amount for children
    private double supplementAmount; // Total amount

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEligible() {
        return isEligible;
    }

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
