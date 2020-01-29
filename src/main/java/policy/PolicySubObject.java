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

    public PolicySubObject(@NotNull String name, @NotNull BigDecimal sumInsured, @NotNull RiskType riskType){
        this.name = name;
        this.sumInsured = sumInsured.setScale(2, RoundingMode.HALF_EVEN);
        this.riskType = riskType;
    }

    public PolicySubObject(@NotNull String name, @NotNull double sumInsured, @NotNull RiskType riskType){
        this(name, new BigDecimal(sumInsured), riskType);
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
}
