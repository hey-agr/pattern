package logistic;

import transport.Airplane;
import transport.Transport;

public class AirLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Airplane();
    }
}
