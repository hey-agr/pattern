import shapes.*;
import visitor.XMLExportVisitor;

/**
 * Посетитель — это поведенческий паттерн,
 * который позволяет добавить новую операцию для целой иерархии классов, не изменяя код этих классов.
 *
 * Применимость:
 * Посетитель нечасто встречается в Java-коде из-за своей сложности и нюансов реализазации.
 *
 * Пример: Сериализация объектов в XML
 * В нашем примере классы геометрических фигур не могут сами экспортировать своё состояние в XML.
 * Представьте, что у вас нет доступа к их коду.
 * Однако с помощью Посетителя мы можем прикрутить любое поведение
 * к этой иерархии (с оговоркой, что в ней будет реализован метод accept).
 */
public class Main {
    public static void main(String[] args) {
        Dot dot = new Dot(1, 10, 55);
        Circle circle = new Circle(2, 23, 15, 10);
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

        CompoundShape compoundShape = new CompoundShape(4);
        compoundShape.add(dot);
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        CompoundShape c = new CompoundShape(5);
        c.add(dot);
        compoundShape.add(c);

        export(circle, compoundShape);
    }

    private static void export(Shape... shapes) {
        XMLExportVisitor exportVisitor = new XMLExportVisitor();
        System.out.println(exportVisitor.export(shapes));
    }
}
