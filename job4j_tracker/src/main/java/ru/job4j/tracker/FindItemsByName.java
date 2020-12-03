package ru.job4j.tracker;

import java.util.ArrayList;

public class FindItemsByName extends BaseAction {

    public FindItemsByName(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = input.ask("Введите имя:");
//        Item[] allItems = tracker.findByName(name);
	ArrayList<Item> allItems = new ArrayList<>(tracker.findAll());
        for (int i = 0; i < allItems.size(); i++) {
            System.out.println("------------ Заявка " + allItems.get(i).getId() + " --------------");
            System.out.println("Имя " + allItems.get(i).getName());
            System.out.println("Описание " + allItems.get(i).getDesc() + "\n");
        }
    }
}
