package account;

import java.util.Map;
import java.util.stream.Collectors;

public class Client {
    private final Map<String, Double> orderLines;
    private final double totalAmount;

    public Client(Map<String, Double> orderLines) {
        this.orderLines = orderLines;
        totalAmount = computeTotalAmount(orderLines);
    }

    private static double computeTotalAmount(Map<String, Double> orderLines) {
        return orderLines.values()
                .stream()
                .mapToDouble(d -> d)
                .sum();
    }

    public String toStatement() {
        return orderLines.entrySet().stream()
                .map(entry -> formatLine(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()))
                .concat(System.lineSeparator() + "Total : " + totalAmount + "€");
    }

    private String formatLine(String name, Double value) {
        return name + " for " + value + "€";
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

