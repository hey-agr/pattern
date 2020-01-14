package transport;

public class Car implements Transport {
    @Override
    public void delivery() {
        System.out.println("Vroom vrooooomm!");
    }
}
