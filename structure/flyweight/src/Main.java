import forest.Forest;

import java.awt.*;

/**
 * Легковес — это структурный паттерн, который экономит память,
 * благодаря разделению общего состояния, вынесенного в один объект, между множеством объектов.
 * Легковес позволяет экономить память, кешируя одинаковые данные, используемые в разных объектах.
 *
 * Применимость: Весь смысл использования Легковеса — в экономии памяти.
 * Поэтому, если в приложении нет такой проблемы,
 * то вы вряд ли найдёте там примеры Легковеса.
 *
 * Признаки применения паттерна: Легковес можно определить по создающим методам класса,
 * которые возвращают закешированные объекты, вместо создания новых.
 *
 * Пример:
 * Отрисовка леса
 * В этом примере мы создадим и нарисуем лес (1.000.000 деревьев)!
 * Каждому дереву соответствует свой объект, имеющий некоторое состояние (координаты, текстура и прочее).
 * Такая программа хоть и работает, но ест слишком много памяти.
 *
 * Много деревьев имеют одинаковые свойства (название, текстуру, цвет).
 * Потому мы можем применить паттерн Легковес и закешировать эти свойства в отдельных объектах TreeType.
 * Теперь вместо хранения этих данных в миллионах объектов деревьев Tree,
 * мы будем ссылаться на один из нескольких объектов-легковесов.
 * Клиенту даже необязательно знать обо всём этом.
 * Фабрика легковесов TreeType сама позаботится о создании нового типа дерева, если будет запрошено дерево с какими-то уникальными параметрами.
 */
public class Main {
    static int CANVAS_SIZE = 500;
    static int TREES_TO_DRAW = 1000000;
    static int TREE_TYPES = 2;

    public static void main(String[] args) {
        Forest forest = new Forest();
        for (int i = 0; i < Math.floor(TREES_TO_DRAW / TREE_TYPES); i++) {
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Summer Oak", Color.GREEN, "Oak texture stub");
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Autumn Oak", Color.ORANGE, "Autumn Oak texture stub");
        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setVisible(true);

        System.out.println(TREES_TO_DRAW + " trees drawn");
        System.out.println("---------------------");
        System.out.println("Memory usage:");
        System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
        System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
        System.out.println("---------------------");
        System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
