package food;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Supplier;

public record Food(LocalDate expirationDate,
                   Boolean approvedForConsumption,
                   UUID inspectorId) {
    public boolean isEdible(Supplier<LocalDate> now) {
        return isStillFresh(now) &&
                hasBeenApproved() &&
                hasBeenInspected();
    }

    private boolean hasBeenInspected() {
        return this.inspectorId != null;
    }

    private Boolean hasBeenApproved() {
        return this.approvedForConsumption;
    }

    private boolean isStillFresh(Supplier<LocalDate> now) {
        return this.expirationDate.isAfter(now.get());
    }
}