package com.ryan.rulesengine.handler;

import com.ryan.rulesengine.model.InputData;
import com.ryan.rulesengine.model.OutputData;

public class SupplementHandler {

    public SupplementHandler() {
    }

//     Validate all input parameters
    private void validateInput(InputData inputData) {
        if (inputData == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }

//         Validate ID
        if (inputData.getId() == null || inputData.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }

//         Validate number of children
        if (inputData.getNumberOfChildren() < 0) {
            throw new IllegalArgumentException("Number of children cannot be negative");
        }

//         Validate family composition
        String familyComposition = inputData.getFamilyComposition();
        if (familyComposition == null ||
                (!familyComposition.equalsIgnoreCase("single") &&
                        !familyComposition.equalsIgnoreCase("couple"))) {
            throw new IllegalArgumentException("Invalid family composition. Must be 'single' or 'couple'");
        }
    }

//    do computation of supplement amounts
    public OutputData start(InputData inputData) {
        try {
//            validate input date first
            validateInput(inputData);

            OutputData outputData = new OutputData();

//        get input data
            String id = inputData.getId();
            int numberOfChildren = inputData.getNumberOfChildren();
            String familyComposition = inputData.getFamilyComposition();
            boolean familyUnitInPayForDecember = inputData.isFamilyUnitInPayForDecember();

//          initialize amounts
            double childrenAmount = 0.0;
            double baseAmount = 0.0;
            double supplementAmount = 0.0;

//            if eligible
            if (familyUnitInPayForDecember) {
                if ("single".equalsIgnoreCase(familyComposition) && numberOfChildren == 0) {
                    baseAmount = 60.0;
                } else {
                    baseAmount = 120.0;
                }

                childrenAmount = 20.0 * numberOfChildren;

                supplementAmount = baseAmount + childrenAmount;
            }

//            Set output data
            outputData.setBaseAmount(baseAmount);
            outputData.setChildrenAmount(childrenAmount);
            outputData.setSupplementAmount(supplementAmount);
            outputData.setId(id);
            outputData.setEligible(familyUnitInPayForDecember);

            return outputData;

        } catch (Exception e) {

//            if input is invalid, return all zero and false.
            System.out.println(e.getMessage());

            if (inputData == null) return null;

            OutputData outputData = new OutputData();

            outputData.setBaseAmount(0.0);
            outputData.setChildrenAmount(0.0);
            outputData.setSupplementAmount(0.0);
            outputData.setId(inputData.getId());
            outputData.setEligible(false);

            return outputData;
        }


    }

}
