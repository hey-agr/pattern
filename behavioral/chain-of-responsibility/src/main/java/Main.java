import middleware.Middleware;
import middleware.RoleCheckMiddleware;
import middleware.ThrottlingMiddleware;
import middleware.UserExistsMiddleware;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Цепочка обязанностей — это поведенческий паттерн,
 * позволяющий передавать запрос по цепочке потенциальных обработчиков,
 * пока один из них не обработает запрос.
 *
 * Избавляет от жёсткой привязки отправителя запроса к его получателю,
 * позволяя выстраивать цепь из различных обработчиков динамически.
 *
 * Применимость: Паттерн встречается в Java не так уж часто,
 * так как для его применения нужна цепь объектов, например, связанный список.
 * Область применения цепочки обязанностей — всевозможные обработчики событий,
 * последовательные проверки доступа и прочее.
 *
 * Признаки применения паттерна: Цепочку обязанностей можно определить по спискам обработчиков или проверок,
 * через которые пропускаются запросы. Особенно если порядок следования обработчиков важен.
 *
 * Пример: Слои авторизации и аутентификации пользователей
 * Этот пример показывает как пользовательские данные проходят последовательную аутентификацию в множестве обработчиков,
 * связанных в одну цепь.
 *
 * Этот пример отличается от канонической версии тем, что проверка обрывается,
 * если очередной обработчик не может обработать запрос. В классическом варианте,
 * следование по цепочке заканчивается как только нашёлся элемент цепи, который может обработать запрос.
 * Просто знайте, что Концептуальный пример от этого не меняется, а код отличается только условием выхода из цепи.
 *
 */
public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // Проверки связаны в одну цепь. Клиент может строить различные цепи,
        // используя одни и те же компоненты.
        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistsMiddleware(server))
                .linkWith(new RoleCheckMiddleware());

        // Сервер получает цепочку от клиентского кода.
        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}
