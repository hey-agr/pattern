import app.Application;
import factories.GUIFactory;
import factories.MacOSFactory;
import factories.WindowsFactory;

/**
 * Абстрактная фабрика — это порождающий паттерн проектирования,
 * который позволяет создавать семейства связанных объектов,
 * не привязываясь к конкретным классам создаваемых объектов.
 *
 * Абстрактная фабрика задаёт интерфейс создания всех доступных типов продуктов,
 * а каждая конкретная реализация фабрики порождает продукты одной из вариаций.
 * Клиентский код вызывает методы фабрики для получения продуктов,
 * вместо самостоятельного создания с помощью оператора new.
 * При этом фабрика сама следит за тем, чтобы создать продукт нужной вариации.
 *
 * Признаки применения паттерна: Паттерн можно определить по методам,
 * возвращающим фабрику, которая, в свою очередь,
 * используется для создания конкретных продуктов, возвращая их через абстрактные типы или интерфейсы.
 */
public class Main {

    private static Application configureApplication() {
        GUIFactory factory;

        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }

        return new Application(factory);
    }

    public static void main(String[] args) {
        Application application = configureApplication();
        application.paint();
    }
}
