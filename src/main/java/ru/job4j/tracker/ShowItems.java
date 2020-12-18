package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class ShowItems extends BaseAction {

	public ShowItems(int key, String info) {
		super(key, info);
	}

	@Override
	public void execute(Input input, Store tracker) {
		System.out.println("------------ Список всех заявок --------------");
		List<Item> allItems = new ArrayList<>();
		allItems = tracker.findAll();
		for (int i = 0; i < allItems.size(); i++) {
			System.out.println("------------ Заявка " + allItems.get(i).getId() + " --------------");
			System.out.println("Имя: " + allItems.get(i).getName());
			System.out.println("Описание: " + allItems.get(i).getDesc() + "\n");
		}
	}

}
