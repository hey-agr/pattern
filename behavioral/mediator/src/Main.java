import components.*;
import mediator.Editor;
import mediator.Mediator;

import javax.swing.*;

/**
 * Посредник — это поведенческий паттерн,
 * который упрощает коммуникацию между компонентами системы.
 *
 * Посредник убирает прямые связи между отдельными компонентами,
 * заставляя их общаться друг с другом через себя.
 *
 * Применимость: Пожалуй, самое популярное применение Посредника
 * в Java-коде — это связь нескольких компонентов GUI одной программы.
 *
 * Пример: Редактор заметок
 * Этот пример показывает как организовать множество элементов интерфейса при помощи посредника так,
 * чтобы они не знали и не зависели друг от друга.
 */
public class Main {
    public static void main(String[] args) {
        Mediator mediator = new Editor();

        mediator.registerComponent(new Title());
        mediator.registerComponent(new TextBox());
        mediator.registerComponent(new AddButton());
        mediator.registerComponent(new DeleteButton());
        mediator.registerComponent(new SaveButton());
        mediator.registerComponent(new List(new DefaultListModel()));
        mediator.registerComponent(new Filter());

        mediator.createGUI();
    }
}
