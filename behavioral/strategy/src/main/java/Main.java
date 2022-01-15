import order.Order;
import strategies.PayByCreditCard;
import strategies.PayByPayPal;
import strategies.PayStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Стратегия — это поведенческий паттерн,
 * выносит набор алгоритмов в собственные классы и делает их взаимозаменимыми.
 * Другие объекты содержат ссылку на объект-стратегию и делегируют ей работу.
 * Программа может подменить этот объект другим, если требуется иной способ решения задачи.
 *
 * Применимость:
 * Стратегия часто используется в Java-коде, особенно там,
 * где нужно подменять алгоритм во время выполнения программы.
 * Начиная с Java 8, многие примеры стратегии можно заменить простыми lambda-выражениями.
 *
 * Признаки применения паттерна:
 * Класс делегирует выполнение вложенному объекту абстрактного типа или интерфейса.
 *
 * Пример: Методы оплаты в интернет магазине
 * В этом примере Стратегия реализует выбор платёжного метода в интернет магазине.
 * Когда пользователь сформировал заказ, он получает выбор из нескольких платёжных стредств:
 * электронного кошелька или кредитной карты.
 * В данном случае конкретные стратегии платёжных методов не только проводят саму оплату,
 * но и собирают необходимые данные на форме заказа.
 */
public class Main {
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;

    static {
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        while (!order.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("Please, select a product:" + "\n" +
                        "1 - Mother board" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memory" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("Count: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy == null) {
                System.out.println("Please, select a payment method:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Credit Card");
                String paymentMethod = reader.readLine();

                // Клиент создаёт различные стратегии на основании
                // пользовательских данных, конфигурации и прочих параметров.
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }

                // Объект заказа делегирует сбор платёжных данны стратегии, т.к.
                // только стратегии знают какие данные им нужны для приёма
                // оплаты.
                order.processOrder(strategy);

                System.out.print("Pay " + order.getTotalCost() + " units or Continue shopping? P/C: ");
                String proceed = reader.readLine();
                if (proceed.equalsIgnoreCase("P")) {
                    // И наконец, стратегия запускает приём платежа.
                    if (strategy.pay(order.getTotalCost())) {
                        System.out.println("Payment has been successful.");
                    } else {
                        System.out.println("FAIL! Please, check your data.");
                    }
                    order.setClosed();
                }
            }
        }
    }
}
