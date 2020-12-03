package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Kosatchev
 * @version $Id$
 * @since 0.1
 */
public class StartUI {


    /**
     * Константа меню для добавления новой заявки.
     */
    public static final int ADD = 0;

    /**
     * Константа меню для вывода всех заявок.
     */
    public static final int SHOW = 1;

    /**
     * Константа меню для редактирования заявки.
     */
    public static final int EDIT = 2;

    /**
     * Константа меню для удаления заявки.
     */
    public static final int DELETE = 3;

    /**
     * Константа меню для поиска заявки по ID.
     */
    public static final int FINDID = 4;

    /**
     * Константа меню для поиска заявок по имени.
     */
    public static final int FINDNAME = 5;

    /**
     * Константа для выхода из цикла.
     */
    public static final int EXIT = 6;
    /**
     * Получение данных от пользователя.
     */
    public final Input input;

    /**
     * Хранилище заявок.
     */
    public final Tracker tracker;

    /**
     * Конструктор инициализирующий поля.
     *
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int[] range = new int[menu.getActionsLentgh()];
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range[i] = i;
        }
        do {
            menu.show();
            menu.select(input.ask("select:", range));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }

    /**
     * Запуск программы.
     *
     * @param args
     */
    //public static void main(String[] args) {
    //    new StartUI(new ValidateInput(), new Tracker()).init();
    //}
    public static void main(String[] args) {
    new StartUI(
            new ValidateInput(
                    new ConsoleInput()
            ),
            new Tracker()
    ).init();
}
}
