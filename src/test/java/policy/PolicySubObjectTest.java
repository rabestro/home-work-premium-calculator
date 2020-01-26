package policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PolicySubObjectTest {
    private PolicySubObject subObject_01;
    private PolicySubObject subObject_02;
    private PolicySubObject subObject_03;
    private PolicySubObject subObject_04;

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
    void getName() {
        assertEquals(subObject_01.getName(), "TV");
        assertEquals(subObject_02.getName(), "Motorbike");
        assertEquals(subObject_03.getName(), "LCD");
        assertEquals(subObject_04.getName(), "CAR");
    }

    @Test
    void getSumInsured() {
        assertEquals(subObject_01.getSumInsured(), new BigDecimal("120.00"));
        assertEquals(subObject_02.getSumInsured(), new BigDecimal("12009.10"));
        assertEquals(subObject_03.getSumInsured(), new BigDecimal("250.00"));
        assertEquals(subObject_04.getSumInsured(), new BigDecimal("36900.00"));
    }

    @Test
    void getRiskType() {
        assertEquals(subObject_01.getRiskType(), RiskType.FIRE);
        assertEquals(subObject_02.getRiskType(), RiskType.FIRE);
        assertEquals(subObject_03.getRiskType(), RiskType.WATER);
        assertEquals(subObject_04.getRiskType(), RiskType.WATER);
    }
}