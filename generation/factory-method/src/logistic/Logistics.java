package logistic;

import transport.Transport;

public abstract class Logistics {

    public void deliveryCargo() {
        Transport transport = createTransport();
        transport.delivery();
    }

    public abstract Transport createTransport();
}
