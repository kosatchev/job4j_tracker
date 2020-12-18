package ru.job4j.tracker;

public class DeleteItem extends BaseAction {

	public DeleteItem(int key, String info) {
		super(key, info);
	}

	@Override
	public void execute(Input input, Store tracker) {
		System.out.println("------------ Удаление новой заявки --------------");
		String id = input.ask("Введите ID удаляемой заявки: ");
		if (tracker.delete(id)) {
			System.out.println("------------ Заявка: " + id + " Удалена -----------");
		} else {
			System.out.println("------------ Заявки: " + id + " не существует -----------");

		}
	}

}
