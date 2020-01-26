package policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PolicyTest {
    private PolicySubObject subObject_01;
    private PolicySubObject subObject_02;
    private PolicySubObject subObject_03;
    private PolicySubObject subObject_04;
    private PolicyObject flat;
    private PolicyObject villa;

    @BeforeEach
    void setUp() {
        subObject_01 = PolicySubObject.newBuilder()
                .withRiskType(RiskType.FIRE)
                .withSubObjectName("TV")
                .withSumInsured("100")
                .build();

        subObject_02 = PolicySubObject.newBuilder()
                .withRiskType(RiskType.WATER)
                .withSubObjectName("Papers")
                .withSumInsured("8")
                .build();

        flat = PolicyObject.newBuilder()
                .withObjectName("A flat")
                .addSubObject(subObject_01)
                .addSubObject(subObject_02)
                .build();

        subObject_03 = PolicySubObject.newBuilder()
                .withRiskType(RiskType.FIRE)
                .withSubObjectName("Cigar")
                .withSumInsured("500")
                .build();

        subObject_04 = PolicySubObject.newBuilder()
                .withRiskType(RiskType.WATER)
                .withSubObjectName("Boat")
                .withSumInsured("100")
                .build();

        villa = PolicyObject.newBuilder()
                .withObjectName("A villa")
                .addSubObject(subObject_03)
                .addSubObject(subObject_04)
                .build();
    }

    @Test
    void changeStatus() {
    }

    @Test
    void getNumber() {
    }

    @Test
    void getStatus() {
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