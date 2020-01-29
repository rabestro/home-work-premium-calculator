package policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolicyTest {
    private PolicySubObject subObject01;
    private PolicySubObject subObject02;
    private PolicySubObject subObject03;
    private PolicySubObject subObject04;
    private PolicyObject flat;
    private PolicyObject villa;

    @BeforeEach
    void setUp() {
        subObject01 = new PolicySubObject("TV", 100, RiskType.FIRE);
        subObject02 = new PolicySubObject("Motorbike", 8, RiskType.WATER);
        subObject03 = new PolicySubObject("LCD", 500, RiskType.FIRE);
        subObject04 = new PolicySubObject("CAR", 100, RiskType.WATER);

        flat = new PolicyObject("A flat", List.of(subObject01, subObject02));
        villa = new PolicyObject("A flat", List.of(subObject03, subObject04));
    }

    @Test
    void exampleOne() {
        Policy ExampleOne = new Policy("LV19-07-100000-1",Status.REGISTERED, List.of(flat));

        assertEquals(new BigDecimal("2.10"), ExampleOne.getPremium());
    }

    @Test
    void exampleTwo() {
        Policy ExampleTwo = new Policy("LV19-07-100000-2", Status.APPROVED, List.of(villa));

        assertEquals(new BigDecimal("16.50"), ExampleTwo.getPremium());
    }

    @Test
    void testGetPremiumRiskType() {
        Policy ExampleTwo = new Policy("LV19-07-100000-2", Status.APPROVED, List.of(villa));

        assertEquals(new BigDecimal("11.50"), ExampleTwo.getPremium(RiskType.FIRE));
        assertEquals(new BigDecimal("5.00"), ExampleTwo.getPremium(RiskType.WATER));
    }
}