package policy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RiskTypeTest {

    @org.junit.jupiter.api.Test
    void getPremium() {
        BigDecimal result = RiskType.FIRE.getPremium(new BigDecimal("0"));
        assertEquals(result, new BigDecimal("0.00"));

        result = RiskType.FIRE.getPremium(new BigDecimal("99.99"));
        assertEquals(result, new BigDecimal("1.30"));

        result = RiskType.FIRE.getPremium(new BigDecimal("100"));
        assertEquals(result, new BigDecimal("1.30"));

        result = RiskType.FIRE.getPremium(new BigDecimal("100.01"));
        assertEquals(result, new BigDecimal("2.30"));

        result = RiskType.FIRE.getPremium(new BigDecimal("100.45"));
        assertEquals(result, new BigDecimal("2.31"));

        // 22,999.99977 has to be rounded to 23000.00
        result = RiskType.FIRE.getPremium(new BigDecimal("999999.99"));
        assertEquals(result, new BigDecimal("23000.00"));
    }
}