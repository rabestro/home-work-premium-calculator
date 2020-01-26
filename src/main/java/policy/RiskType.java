package policy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The enum class encapsulate the formulas and coefficients required
 * to calculate premium for different risk types.
 */
public enum RiskType {
    FIRE("0.013") {
        private final BigDecimal overSum = new BigDecimal("100");
        private final BigDecimal overCoefficient = new BigDecimal("0.023");

        @Override
        BigDecimal getPremium(BigDecimal sumInsured) {
            final BigDecimal premium;

            if (sumInsured.compareTo(overSum) > 0) {
                premium = sumInsured.multiply(overCoefficient);
            } else {
                premium = sumInsured.multiply(defaultCoefficient);
            }
            return premium.setScale(scale, rounding);
        }
    },
    WATER("0.1") {
        private final BigDecimal equalOrGreaterSum = new BigDecimal("10");
        private final BigDecimal equalOrGreaterCoefficient = new BigDecimal("0.05");

        @Override
        BigDecimal getPremium(BigDecimal sumInsured) {
            final BigDecimal premium;

            if (sumInsured.compareTo(equalOrGreaterSum) >= 0) {
                premium = sumInsured.multiply(equalOrGreaterCoefficient);
            } else {
                premium = sumInsured.multiply(defaultCoefficient);
            }
            return premium.setScale(scale, rounding);
        }
    };

    RiskType(String defaultCoefficient) {
        this.defaultCoefficient = new BigDecimal(defaultCoefficient);
    }

    private final static int scale = 2;
    private final static RoundingMode rounding = RoundingMode.HALF_EVEN;

    protected final BigDecimal defaultCoefficient;

    /**
     *
     * @param  sumInsured - insured sum for the risk type
     * @return Calculated premium for selected risk type and insured sum.
     */
    BigDecimal getPremium(BigDecimal sumInsured) {
        final BigDecimal premium = sumInsured.multiply(defaultCoefficient);
        return premium.setScale(scale, rounding);
    };
}
