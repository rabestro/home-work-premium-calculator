package policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PolicySubObjectTest {
    private PolicySubObject subObject01;
    private PolicySubObject subObject02;
    private PolicySubObject subObject03;
    private PolicySubObject subObject04;

    @BeforeEach
    void setUp() {
        subObject01 = new PolicySubObject("TV", 120, RiskType.FIRE);
        subObject02 = new PolicySubObject("Motorbike", 12009.1, RiskType.FIRE);
        subObject03 = new PolicySubObject("LCD", 249.995, RiskType.WATER);
        subObject04 = new PolicySubObject("CAR", 36900, RiskType.WATER);
    }

    @Test
    void getName() {
        assertEquals(subObject01.getName(), "TV");
        assertEquals(subObject02.getName(), "Motorbike");
        assertEquals(subObject03.getName(), "LCD");
        assertEquals(subObject04.getName(), "CAR");
    }

    @Test
    void getSumInsured() {
        assertEquals(subObject01.getSumInsured(), new BigDecimal("120.00"));
        assertEquals(subObject02.getSumInsured(), new BigDecimal("12009.10"));
        assertEquals(subObject03.getSumInsured(), new BigDecimal("250.00"));
        assertEquals(subObject04.getSumInsured(), new BigDecimal("36900.00"));
    }

    @Test
    void getRiskType() {
        assertEquals(subObject01.getRiskType(), RiskType.FIRE);
        assertEquals(subObject02.getRiskType(), RiskType.FIRE);
        assertEquals(subObject03.getRiskType(), RiskType.WATER);
        assertEquals(subObject04.getRiskType(), RiskType.WATER);
    }
}