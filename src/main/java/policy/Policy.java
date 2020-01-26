package policy;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Policy {
    private final String number;
    private final List<PolicyObject> objects;
    private final Status status;
    private final BigDecimal premium;

    private Policy(@NotNull final String number,
                   @NotNull final Status status,
                   @NotNull final List<PolicyObject> objects) {
        this.number = number;
        this.status = status;
        this.objects = List.copyOf(objects);
        this.premium = calculatePremium();
    }

    private Policy(@NotNull final Builder builder) {
        number = builder.policyNumber;
        status = builder.policyStatus;
        objects = List.copyOf(builder.policyObjects);
        premium = calculatePremium();
    }

    Policy changeStatus(@NotNull final Status newStatus) {
        return new Policy(this.number, newStatus, this.objects);
    }

    public String getNumber() {
        return number;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Get calculated premium of the policy.
     *
     * @return Premium for all types of risk for all objects in Policy.
     * The returned value is BigDecimal with two digits scale.
     */
    public BigDecimal getPremium() {
        return premium;
    }

    private BigDecimal calculatePremium() {
        return Arrays.stream(RiskType.values())
                .map(this::getPremium)
                .reduce((a, b) -> a = a.add(b))
                .orElse(BigDecimal.ZERO);
    }

    /**
     * The function calculate premium for selected risk type
     *
     * @param riskType
     * @return Premium for selected riskType as BigDecimal
     */
    public BigDecimal getPremium(final RiskType riskType) {
        final BigDecimal sum = objects.stream()
                .map(obj -> obj.getSumInsured(riskType))
                .reduce((a, b) -> a = a.add(b))
                .orElse(BigDecimal.ZERO);

        return riskType.getPremium(sum);
    }
    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String policyNumber;
        private Status policyStatus;
        private final List<PolicyObject> policyObjects = new ArrayList<>();

        private Builder() {
        }

        public Builder withNumber(@NotNull final String val) {
            policyNumber = val;
            return this;
        }

        public Builder withStatus(@NotNull final Status val) {
            policyStatus = val;
            return this;
        }

        public Builder addObject(@NotNull final PolicyObject val) {
            policyObjects.add(val);
            return this;
        }

        public Policy build() {
            return new Policy(this);
        }
    }
}
