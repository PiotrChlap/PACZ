package Rest;

import java.time.LocalDate;
import java.util.Date;

public class Invoice {
    private final int InvoiceId;
    private LocalDate dateOfPayment;
    private LocalDate dateOfissue;
    private boolean paid;
    private Order order;

    public Invoice(int invoiceId, LocalDate dateOfPayment, LocalDate dateOfissue, boolean paid, Order order) {
        InvoiceId = invoiceId;
        this.dateOfPayment = dateOfPayment;
        this.dateOfissue = dateOfissue;
        this.paid = paid;
        this.order = order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getInvoiceId() {
        return InvoiceId;
    }

    public LocalDate getDateOfPayment() {
        setPaid();
        return dateOfPayment;
    }

    public LocalDate getDateOfissue() {
        return dateOfissue;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public void setDateOfissue(LocalDate dateOfissue) {
        this.dateOfissue = dateOfissue;
    }

    private void setPaid() {
        this.paid = true;
    }

    public boolean toCompile(){
        return true;
    }

    public String createInvoice(){
        StringBuilder tmp = new StringBuilder();
        tmp.append("Klient: ");
        tmp.append(order.getClient().getBasedInfo());
        tmp.append("\n");
        tmp.append("Suma:");
        tmp.append("\n");
        tmp.append(order.getTotalCost());
        tmp.append("\n");
        return tmp +order.getInfoOrder();

    }

}
