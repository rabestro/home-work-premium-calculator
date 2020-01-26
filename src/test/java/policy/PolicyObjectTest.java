package policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolicyObjectTest {
    private PolicySubObject subObject_01;
    private PolicySubObject subObject_02;
    private PolicySubObject subObject_03;
    private PolicySubObject subObject_04;
    private PolicyObject flat;

    @BeforeEach
    void setUp() {
        subObject_01 = PolicySubObject.newBuilder()
                .withRiskType(RiskType.FIRE)
                .withSubObjectName("TV")
                .withSumInsured("120")
                .build();

        subObject_02 = PolicySubObject.newBuilder()
                .withRiskType(RiskType.FIRE)
                .withSubObjectName("Motorbike")
                .withSumInsured("12009.100")
                .build();

        subObject_03 = PolicySubObject.newBuilder()
                .withRiskType(RiskType.WATER)
                .withSubObjectName("LCD")
                .withSumInsured("249.995")
                .build();

        subObject_04 = PolicySubObject.newBuilder()
                .withRiskType(RiskType.WATER)
                .withSubObjectName("CAR")
                .withSumInsured("36900")
                .build();
    }

    @Test
    void getSumInsured() {
        flat = PolicyObject.newBuilder()
                .withObjectName("A flat")
                .addSubObject(subObject_01)
                .addSubObject(subObject_02)
                .addSubObject(subObject_03)
                .addSubObject(subObject_04)
                .build();

        assertEquals(new BigDecimal("12129.10"), flat.getSumInsured(RiskType.FIRE));
        assertEquals(new BigDecimal("37150.00"), flat.getSumInsured(RiskType.WATER));

        flat = PolicyObject.newBuilder()
                .withObjectName("A flat")
                .build();

        assertEquals(new BigDecimal("0.00"), flat.getSumInsured(RiskType.FIRE));
        assertEquals(new BigDecimal("0.00"), flat.getSumInsured(RiskType.WATER));

        flat = PolicyObject.newBuilder()
                .withObjectName("A flat")
                .addSubObject(subObject_01)
                .addSubObject(subObject_02)
                .build();

        assertEquals(new BigDecimal("12129.10"), flat.getSumInsured(RiskType.FIRE));
        assertEquals(new BigDecimal("0.00"), flat.getSumInsured(RiskType.WATER));

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

        flat = PolicyObject.newBuilder()
                .withObjectName("A flat")
                .addSubObject(subObject_03)
                .addSubObject(subObject_04)
                .build();

        assertEquals(new BigDecimal("500.00"), flat.getSumInsured(RiskType.FIRE));
        assertEquals(new BigDecimal("100.00"), flat.getSumInsured(RiskType.WATER));
    }

}