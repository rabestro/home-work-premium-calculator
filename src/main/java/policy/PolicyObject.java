package policy;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Policy objects can have multiple sub-objects and can be related only to one policy
 */
public class PolicyObject {

    private final String name;
    private final List<PolicySubObject> subObjects;

    PolicyObject(@NotNull final String name, @NotNull final List<PolicySubObject> subObject) {
        this.name = name;
        this.subObjects = subObject;
    }

    public String getName() {
        return name;
    }
    public BigDecimal getSumInsured(@NotNull final RiskType riskType) {
        assert !Objects.isNull(riskType);
        assert !Objects.isNull(subObjects);

        return subObjects.stream()
                .filter(obj -> obj.getRiskType() == riskType)
                .map(PolicySubObject::getSumInsured)
                .reduce((a, b) -> a = a.add(b))
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private PolicyObject(@NotNull final Builder builder) {
        this.name = builder.objectName;
        this.subObjects = List.copyOf(builder.subObjects);
    }

    public static final class Builder {
        private String objectName;
        private final List<PolicySubObject> subObjects = new ArrayList<>();

        private Builder() {
        }

        public Builder withObjectName(@NotNull final String val) {
            objectName = val;
            return this;
        }
        public Builder addSubObject(@NotNull final PolicySubObject val) {
            subObjects.add(val);
            return this;
        }

        public PolicyObject build() {
            return new PolicyObject(this);
        }
    }
}
