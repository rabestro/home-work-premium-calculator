package policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolicyObjectTest {
    private PolicySubObject subObject01;
    private PolicySubObject subObject02;
    private PolicySubObject subObject03;
    private PolicySubObject subObject04;
    private PolicyObject flat;

    @BeforeEach
    void setUp() {
        subObject01 = new PolicySubObject("TV", 120, RiskType.FIRE);
        subObject02 = new PolicySubObject("Motorbike", 12009.1, RiskType.FIRE);
        subObject03 = new PolicySubObject("LCD", 249.995, RiskType.WATER);
        subObject04 = new PolicySubObject("CAR", 36900, RiskType.WATER);
    }

    @Test
    void getSumInsured() {
        flat = new PolicyObject("A flat", List.of(subObject01, subObject02, subObject03, subObject04));

        assertEquals(new BigDecimal("12129.10"), flat.getSumInsured(RiskType.FIRE));
        assertEquals(new BigDecimal("37150.00"), flat.getSumInsured(RiskType.WATER));

        flat = new PolicyObject("A flat", List.of(subObject01, subObject02));

        assertEquals(new BigDecimal("12129.10"), flat.getSumInsured(RiskType.FIRE));
        assertEquals(new BigDecimal("0.00"), flat.getSumInsured(RiskType.WATER));

        subObject03 = new PolicySubObject("Cigar", 500, RiskType.FIRE);
        subObject04 = new PolicySubObject("Boat", 100, RiskType.WATER);

        flat = new PolicyObject("A flat", List.of(subObject03, subObject04));

        assertEquals(new BigDecimal("500.00"), flat.getSumInsured(RiskType.FIRE));
        assertEquals(new BigDecimal("100.00"), flat.getSumInsured(RiskType.WATER));
    }

    @Test
    void emptySubObjects() {
        flat = new PolicyObject("A flat", List.of());

        assertEquals(new BigDecimal("0.00"), flat.getSumInsured(RiskType.FIRE));
        assertEquals(new BigDecimal("0.00"), flat.getSumInsured(RiskType.WATER));
    }
}