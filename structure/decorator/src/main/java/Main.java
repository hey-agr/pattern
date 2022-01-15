/**
 * Декоратор — это структурный паттерн, который позволяет добавлять объектам новые поведения на лету,
 * помещая их в объекты-обёртки.
 *
 * Декоратор позволяет оборачивать объекты бесчисленное количество раз благодаря тому,
 * что и обёртки, и реальные оборачиваемые объекты имеют общий интерфейс.
 *
 * Применимость: Паттерн можно часто встретить в Java-коде,
 * особенно в коде, работающем с потоками данных.
 *
 * Признаки применения паттерна: Декоратор можно распознать по создающим методам, которые принимают
 * в параметрах объекты того же абстрактного типа или интерфейса, что и текущий класс.
 *
 * Пример: Шифрование и сжатие данных
 * Пример показывает, как можно добавить новую функциональность объекту, не меняя его класса.
 * Сначала класс бизнес-логики мог считывать и записывать только чистые данные напрямую из/в файлы.
 * Применив паттерн Декоратор, мы создали небольшие классы-обёртки,
 * которые добавляют новые поведения до или после основной работы вложенного объекта бизнес-логики.
 */
public class Main {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("out/OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("out/OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
