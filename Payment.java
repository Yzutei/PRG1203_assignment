import java.time.LocalDateTime;

public class Payment {

    private int paymentID;
    private LocalDateTime paymentDate;
    private Order order;
    private String paymentMethod;
    private double total;
    private double amountPaid;

    public Payment() {
        this.paymentDate = LocalDateTime.now();
    }

    public Payment(int paymentID, Order order, String paymentMethod, double total, double amountPaid) {
        this.paymentDate = LocalDateTime.now();
        setPaymentID(paymentID);
        setOrder(order);
        setPaymentMethod(paymentMethod);
        setTotal(total);
        setAmountPaid(amountPaid);
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        if (total < 0) {
            System.out.println("Total cannot be negative.");
            return;
        }
        this.total = total;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        if (amountPaid < 0) {
            System.out.println("Amount paid cannot be negative.");
            return;
        }
        this.amountPaid = amountPaid;
    }


    public boolean processPayment() {
        try {
            if (order == null) {
                throw new IllegalStateException("No order attached to this payment.");
            }
            if (order.isEmpty()) {
                throw new IllegalStateException("Cannot process payment for an empty order.");
            }

            setTotal(order.calculateTotal());

            if (amountPaid < total) {
                System.out.printf("Insufficient payment. RM%.2f still owed.%n", (total - amountPaid));
                return false;
            }

            System.out.println("Payment successful.");
            return true;

        }
         catch (IllegalStateException e) {
            System.out.println("Payment failed: " + e.getMessage());
            return false;
        }
    }


    public void printReceipt() {
        System.out.println("========== RECEIPT ==========");
        System.out.println("Payment ID: " + paymentID);
        System.out.println("Date: " + paymentDate);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("------------------------------");

        if (order != null) {
            order.displayOrder();
        }

        System.out.printf("Total: RM%.2f%n", total);
        System.out.printf("Amount Paid: RM%.2f%n", amountPaid);
        System.out.printf("Change: RM%.2f%n", (amountPaid - total));
        System.out.println("============================");
    }
}
