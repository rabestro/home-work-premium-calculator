package policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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

        flat = PolicyObject.newBuilder()
                .withObjectName("A flat")
                .addSubObject(subObject01)
                .addSubObject(subObject02)
                .build();

        villa = PolicyObject.newBuilder()
                .withObjectName("A villa")
                .addSubObject(subObject03)
                .addSubObject(subObject04)
                .build();
    }

    @Test
    void getPremium() {
        Policy ExampleOne = Policy.newBuilder()
                .withNumber("LV19-07-100000-1")
                .withStatus(Status.REGISTERED)
                .addObject(flat)
                .build();

        assertEquals(new BigDecimal("2.10"), ExampleOne.getPremium());

        Policy ExampleTwo = Policy.newBuilder()
                .withNumber("LV19-07-100000-2")
                .withStatus(Status.APPROVED)
                .addObject(villa)
                .build();

        assertEquals(new BigDecimal("16.50"), ExampleTwo.getPremium());
    }

    @Test
    void testGetPremium() {
        Policy ExampleTwo = Policy.newBuilder()
                .withNumber("LV19-07-100000-2")
                .withStatus(Status.APPROVED)
                .addObject(villa)
                .build();

        assertEquals(new BigDecimal("11.50"), ExampleTwo.getPremium(RiskType.FIRE));
        assertEquals(new BigDecimal("5.00"), ExampleTwo.getPremium(RiskType.WATER));
    }
}