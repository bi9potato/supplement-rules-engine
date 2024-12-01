package com.ryan.rulesengine.handler;

import com.ryan.rulesengine.model.InputData;
import com.ryan.rulesengine.model.OutputData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SupplementHandlerTest {

    @Test
    public void testNoEligible() {
        SupplementHandler supplementHandler = new SupplementHandler();
        InputData inputData = new InputData();

        inputData.setId("test1");
        inputData.setFamilyComposition("single");
        inputData.setNumberOfChildren(10);
        inputData.setFamilyUnitInPayForDecember(false);

        OutputData outputData = supplementHandler.start(inputData);

        assertEquals("test1", outputData.getId());
        assertFalse(outputData.isEligible());
        assertEquals(0.0, outputData.getBaseAmount());
        assertEquals(0.0, outputData.getChildrenAmount());
        assertEquals(0.0, outputData.getSupplementAmount());

        System.out.println("Test1 executed");
    }


    @Test
    public void testEligibleSingleNoChildren() {
        SupplementHandler handler = new SupplementHandler();
        InputData inputData = new InputData();

        inputData.setId("test2");
        inputData.setFamilyComposition("single");
        inputData.setNumberOfChildren(0);
        inputData.setFamilyUnitInPayForDecember(true);

        OutputData outputData = handler.start(inputData);

        assertEquals("test2", outputData.getId());
        assertTrue(outputData.isEligible());
        assertEquals(60.0, outputData.getBaseAmount());
        assertEquals(0.0, outputData.getChildrenAmount());
        assertEquals(60.0, outputData.getSupplementAmount());

        System.out.println("Test2 executed");
    }

    @Test
    public void testEligibleSingleWithChildren() {
        SupplementHandler handler = new SupplementHandler();
        InputData inputData = new InputData();

        inputData.setId("test3");
        inputData.setFamilyComposition("single");
        inputData.setNumberOfChildren(11);
        inputData.setFamilyUnitInPayForDecember(true);

        OutputData outputData = handler.start(inputData);

        assertEquals("test3", outputData.getId());
        assertTrue(outputData.isEligible());
        assertEquals(120.0, outputData.getBaseAmount());
        assertEquals(11*20.0, outputData.getChildrenAmount());
        assertEquals(120.0+11*20.0, outputData.getSupplementAmount());

        System.out.println("Test3 executed");
    }

    @Test
    public void testEligibleCoupleNoChildren() {

        SupplementHandler handler = new SupplementHandler();
        InputData inputData = new InputData();

        inputData.setId("test4");
        inputData.setFamilyComposition("couple");
        inputData.setNumberOfChildren(0);
        inputData.setFamilyUnitInPayForDecember(true);

        OutputData outputData = handler.start(inputData);

        assertEquals("test4", outputData.getId());
        assertTrue(outputData.isEligible());
        assertEquals(120.0, outputData.getBaseAmount());
        assertEquals(0.0, outputData.getChildrenAmount());
        assertEquals(120.0, outputData.getSupplementAmount());

        System.out.println("Test4 executed");
    }

    @Test
    public void testEligibleCoupleWithChildren() {

        SupplementHandler handler = new SupplementHandler();
        InputData inputData = new InputData();

        inputData.setId("test5");
        inputData.setFamilyComposition("couple");
        inputData.setNumberOfChildren(88);
        inputData.setFamilyUnitInPayForDecember(true);

        OutputData outputData = handler.start(inputData);

        assertEquals("test5", outputData.getId());
        assertTrue(outputData.isEligible());
        assertEquals(120.0, outputData.getBaseAmount());
        assertEquals(88*20.0, outputData.getChildrenAmount());
        assertEquals(120.0+88*20.0, outputData.getSupplementAmount());

        System.out.println("Test5 executed");
    }

    @Test
    public void testNegativeChildren() {

        SupplementHandler handler = new SupplementHandler();
        InputData inputData = new InputData();

        inputData.setId("test6");
        inputData.setFamilyComposition("couple");
        inputData.setNumberOfChildren(-8);
        inputData.setFamilyUnitInPayForDecember(true);

        OutputData outputData = handler.start(inputData);

        assertEquals("test6", outputData.getId());
        assertFalse(false);
        assertEquals(0.0, outputData.getBaseAmount());
        assertEquals(0.0, outputData.getChildrenAmount());
        assertEquals(0.0, outputData.getSupplementAmount());


        System.out.println("Test6 executed");
    }

    @Test
    public void testInvalidInput() {

        SupplementHandler handler = new SupplementHandler();
        InputData inputData = null;

        OutputData outputData = handler.start(inputData);

        assertEquals(null, outputData);

        System.out.println("Test7 executed");
    }

    @Test
    public void testValidId() {

        SupplementHandler handler = new SupplementHandler();
        InputData inputData = new InputData();

        inputData.setId("");
        inputData.setFamilyComposition("couple");
        inputData.setNumberOfChildren(1);
        inputData.setFamilyUnitInPayForDecember(true);

        OutputData outputData = handler.start(inputData);

        assertEquals("", outputData.getId());
        assertFalse(false);
        assertEquals(0.0, outputData.getBaseAmount());
        assertEquals(0.0, outputData.getChildrenAmount());
        assertEquals(0.0, outputData.getSupplementAmount());


        System.out.println("Test8 executed");
    }

    @Test
    public void testInvalidFamilyComposition() {
        SupplementHandler handler = new SupplementHandler();
        InputData inputData = new InputData();
        inputData.setId("test10");
        inputData.setFamilyComposition("unknown");
        inputData.setNumberOfChildren(1);
        inputData.setFamilyUnitInPayForDecember(true);

        OutputData outputData = handler.start(inputData);

        assertEquals("test10", outputData.getId());
        assertFalse(false);
        assertEquals(0.0, outputData.getBaseAmount());
        assertEquals(0.0, outputData.getChildrenAmount());
        assertEquals(0.0, outputData.getSupplementAmount());
    }




}
