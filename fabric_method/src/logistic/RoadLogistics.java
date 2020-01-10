package logistic;

import transport.Car;
import transport.Transport;

public class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Car();
    }
}
