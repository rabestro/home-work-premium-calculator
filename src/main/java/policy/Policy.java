package policy;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Policy {
    private final String number;
    private final List<PolicyObject> objects;
    private final Status status;
    private final BigDecimal premium;

    public Policy(@NotNull final String number,
                   @NotNull final Status status,
                   @NotNull final List<PolicyObject> objects) {
        this.number = number;
        this.status = status;
        this.objects = List.copyOf(objects);
        this.premium = calculatePremium();
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
}
