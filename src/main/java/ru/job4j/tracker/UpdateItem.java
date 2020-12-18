package ru.job4j.tracker;

public class UpdateItem extends BaseAction {

	public UpdateItem(int key, String info) {
		super(key, info);
	}

	@Override
	public void execute(Input input, Store tracker) {
		System.out.println("------------ Редактирование заявки --------------");
		String id = input.ask("Введите ID:");
		Item item = tracker.findById(id);

		String name = input.ask("Введите новое имя заявки :");

		String desc = input.ask("Введите новое описание заявки :");

		Item newItem = new Item(name, desc, System.currentTimeMillis());

		if (tracker.replace(id, newItem)) {
			System.out.println("Заявка ID " + id + " Обновлена");
		} else {
			System.out.println("Заявки с ID " + id + " Не существует");
		}
	}

}
