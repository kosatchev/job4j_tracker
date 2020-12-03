package ru.job4j.tracker;

public class FindItemById extends BaseAction {

    public FindItemById(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по ID --------------");
        String id = input.ask("Введите ID:");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("------------ Заявка " + item.getId() + " --------------");
            System.out.println("Имя " + item.getName());
            System.out.println("Описание " + item.getDesc() + "\n");
        } else {
            System.out.println("Заявки с ID " + id + " Не существует");
        }
    }
}
