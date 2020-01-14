import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Прототип — это порождающий паттерн,
 * который позволяет копировать объекты любой сложности без привязки к их конкретным классам.
 *
 * Все классы—Прототипы имеют общий интерфейс. Поэтому вы можете копировать объекты,
 * не обращая внимания на их конкретные типы и всегда быть уверены, что получите точную копию.
 * Клонирование совершается самим объектом-прототипом,
 * что позволяет ему скопировать значения всех полей, даже приватных.
 *
 * Применимость: Паттерн Прототип реализован в базовой библиотеке Java посредством интерфейса Cloneable.
 * Любой класс может реализовать этот интерфейс, чтобы позволить собственное клонирование.
 *
 * Признаки применения паттерна: Прототип легко определяется
 * в коде по наличию методов clone, copy и прочих.
 *
 * Пример:
 * Копирование графических фигур
 * Рассмотрим пример реализации Прототипа без использования интерфейса Cloneable.
 */
public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        List<Shape> shapesCopy = new ArrayList<>();

        Circle circle = new Circle();
        circle.x = 10;
        circle.y = 20;
        circle.radius = 15;
        circle.color = "red";
        shapes.add(circle);

        Circle anotherCircle = (Circle) circle.clone();
        shapes.add(anotherCircle);

        Rectangle rectangle = new Rectangle();
        rectangle.width = 10;
        rectangle.height = 20;
        circle.color = "blue";
        shapes.add(rectangle);

        cloneAndCompare(shapes, shapesCopy);
    }

    private static void cloneAndCompare(List<Shape> shapes, List<Shape> shapesCopy) {
        for (Shape shape : shapes) {
            shapesCopy.add(shape.clone());
        }

        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) != shapesCopy.get(i)) {
                System.out.println(i + ": Shapes are different objects (yay!)");
                if (shapes.get(i).equals(shapesCopy.get(i))) {
                    System.out.println(i + ": And they are identical (yay!)");
                } else {
                    System.out.println(i + ": But they are not identical (booo!)");
                }
            } else {
                System.out.println(i + ": Shape objects are the same (booo!)");
            }
        }
    }
}
