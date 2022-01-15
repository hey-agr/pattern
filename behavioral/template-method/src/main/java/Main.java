import networks.Facebook;
import networks.Network;
import networks.Twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Шаблонный метод — это поведенческий паттерн,
 * задающий скелет алгоритма в суперклассе и заставляющий подклассы
 * реализовать конкретные шаги этого алгоритма.
 *
 * Применимость:
 * Шаблонные методы можно встретить во многих библиотечных классах Java.
 * Разработчики создают их, чтобы позволить клиентам легко
 * и быстро расширять стандартный код при помощи наследования.
 *
 * Признаки применения паттерна:
 * Класс заставляет своих потомков реализовать методы-шаги,
 * но самостоятельно реализует структуру алгоритма.
 *
 * Пример: Переопределение шагов алгоритма
 * Социальные сети предоставляют собственные методы API для авторизации,
 * постинга и выхода, но общий процесс для всех сетей совпадает.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        System.out.print("Input user name: ");
        String userName = reader.readLine();
        System.out.print("Input password: ");
        String password = reader.readLine();

        // Вводим сообщение.
        System.out.print("Input message: ");
        String message = reader.readLine();

        System.out.println("\nChoose social network for posting message.\n" +
                "1 - Facebook\n" +
                "2 - Twitter");
        int choice = Integer.parseInt(reader.readLine());

        // Создаем сетевые объекты и публикуем пост.
        if (choice == 1) {
            network = new Facebook(userName, password);
        } else if (choice == 2) {
            network = new Twitter(userName, password);
        }
        network.post(message);
    }
}
