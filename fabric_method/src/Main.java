import logistic.AirLogistics;
import logistic.Logistics;
import logistic.RoadLogistics;

/**
 * Фабричный метод — это порождающий паттерн проектирования,
 * который определяет общий интерфейс для создания объектов в суперклассе,
 * позволяя подклассам изменять тип создаваемых объектов.
 *
 * Признаки применения паттерна: Фабричный метод можно определить по создающим методам,
 * которые возвращают объекты продуктов через абстрактные типы или интерфейсы.
 * Это позволяет переопределять типы создаваемых продуктов в подклассах.
 */
public class Main {
    public static void main(String[] args) {
        Logistics logistics = new RoadLogistics();
        logistics.deliveryCargo();

        logistics = new AirLogistics();
        logistics.deliveryCargo();

    }
}
