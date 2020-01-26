package policy;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Policy sub-objects can be related only to one policy object.
 * The class represent insured items (e.g. eletronic devices such as TV).
 */
public class PolicySubObject {
    private final String name;
    private final BigDecimal sumInsured;
    private final RiskType riskType;

    PolicySubObject(@NotNull String name, @NotNull BigDecimal sumInsured, @NotNull RiskType riskType){
        this.name = name;
        this.sumInsured = sumInsured.setScale(2, RoundingMode.HALF_EVEN);
        this.riskType = riskType;
    }

    String getName() {
        return name;
    }

    BigDecimal getSumInsured() {
        return sumInsured;
    }

    RiskType getRiskType() {
        return riskType;
    }

    private PolicySubObject(Builder builder) {
        name = builder.subObjectName;
        sumInsured = builder.sumInsured.setScale(2, RoundingMode.HALF_EVEN);
        riskType = builder.riskType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String subObjectName;
        private BigDecimal sumInsured;
        private RiskType riskType;

        private Builder() {
        }

        public Builder withSubObjectName(String val) {
            subObjectName = val;
            return this;
        }

        public Builder withSumInsured(BigDecimal val) {
            sumInsured = val;
            return this;
        }

        public Builder withSumInsured(String val) {
            sumInsured = new BigDecimal(val);
            return this;
        }

        public Builder withRiskType(RiskType val) {
            riskType = val;
            return this;
        }

        public PolicySubObject build() {
            return new PolicySubObject(this);
        }
    }
}
