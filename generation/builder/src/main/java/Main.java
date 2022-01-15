import builders.CarBuilder;
import builders.CarManualBuilder;
import cars.Car;
import cars.Manual;
import director.Director;

/**
 * Строитель — это порождающий паттерн проектирования, который позволяет создавать объекты пошагово.
 * В отличие от других порождающих паттернов, Строитель позволяет производить различные продукты,
 * используя один и тот же процесс строительства.
 *
 * Применимость: Паттерн можно часто встретить в Java-коде,
 * особенно там, где требуется пошаговое создание продуктов или конфигурация сложных объектов.
 *
 * Признаки применения паттерна: Строителя можно узнать в классе,
 * который имеет один создающий метод и несколько методов настройки создаваемого продукта.
 * Обычно, методы настройки вызывают для удобства цепочкой (например, someBuilder->setValueA(1)->setValueB(2)->create()).
 *
 * Пример: Пошаговое производство автомобилей
 * В этом примере Строитель позволяет пошагово конструировать различные конфигурации автомобилей.
 * Кроме того, здесь показано как с помощью Строителя может создавать
 * другой продукт на основе тех же шагов строительства.
 * Для этого мы имеем два конкретных строителя — CarBuilder и CarManualBuilder.
 * Порядок строительства продуктов заключён в Директоре. Он знает какие шаги строителей нужно вызвать,
 * чтобы получить ту или иную конфигурацию продукта.
 * Он работает со строителями только через общий интерфейс,
 * что позволяет взаимозаменять объекты строителей для получения разных продуктов.
 */
public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        // Директор получает объект конкретного строителя от клиента
        // (приложения). Приложение само знает какой строитель использовать,
        // чтобы получить нужный продукт.
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        // Готовый продукт возвращает строитель, так как Директор чаще всего не
        // знает и не зависит от конкретных классов строителей и продуктов.
        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getType());
        director.constructCityCar(builder);
        System.out.println(builder.getResult().getType());
        director.constructSUV(builder);
        System.out.println(builder.getResult().getType());


        CarManualBuilder manualBuilder = new CarManualBuilder();

        // Директор может знать больше одного рецепта строительства.
        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }
}
