package Rest;

import Client.Client;

import java.io.FileWriter;
import java.io.IOException;

public class CreaterFile {

    public CreaterFile() {
    }

    public void createFile(Invoice invoice, int clientId, int orderId) throws IOException {
        FileWriter mywrite = new FileWriter("faktura"+clientId+orderId+".txt");
        mywrite.write(invoice.createInvoice());
        mywrite.close();
    }
}
