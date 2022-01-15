import adapters.SquarePegAdapter;
import round.RoundHole;
import round.RoundPeg;
import square.SquarePeg;

/**
 * Адаптер — это структурный паттерн, который позволяет подружить несовместимые объекты.
 * Адаптер выступает прослойкой между двумя объектами, превращая вызовы одного в вызовы понятные другому.
 *
 * Применимость: Паттерн можно часто встретить в Java-коде, особенно там,
 * где требуется конвертация разных типов данных или совместная работа классов с разными интерфейсами.
 *
 * Признаки применения паттерна: Адаптер получает конвертируемый объект в конструкторе
 * или через параметры своих методов. Методы Адаптера обычно совместимы с интерфейсом одного объекта.
 * Они делегируют вызовы вложенному объекту, превратив перед этим параметры вызова в формат,
 * поддерживаемый вложенным объектом.
 *
 * Пример: Помещение квадратных колышков в круглые отверстия
 * Этот простой пример показывает как с помощью паттерна Адаптер можно совмещать несовместимые вещи.
 */
public class Main {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("Round peg r5 fits round hole r5");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        // hole.fits(smallSqPeg); // Не скомпилируется.

        // Адаптер решит проблему.
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }
}
