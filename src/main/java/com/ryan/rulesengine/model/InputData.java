package com.ryan.rulesengine.model;

public class InputData {

    private String id; // Unique ID, should be included in the results
    private int numberOfChildren;
    private String familyComposition; // Choices are ["single", "couple"]
    private boolean familyUnitInPayForDecember; // Used for eligibility determination

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getFamilyComposition() {
        return familyComposition;
    }

    public void setFamilyComposition(String familyComposition) {
        this.familyComposition = familyComposition;
    }

    public boolean isFamilyUnitInPayForDecember() {
        return familyUnitInPayForDecember;
    }

    public void setFamilyUnitInPayForDecember(boolean familyUnitInPayForDecember) {
        this.familyUnitInPayForDecember = familyUnitInPayForDecember;
    }
}
