package ru.job4j.tracker;

import java.util.*;

/**
 * @author Dmitry Kosatchev
 * @version $Id$
 * @since 0.1
 */
public class MemTracker {

	/**
	 * Массив для хранение заявок.
	 */
//    private final Item[] items = new Item[100];Item
	private final List<Item> items = new ArrayList<>();

	/**
	 * Указатель ячейки для новой заявки.
	 */
	private int position = 0;

	private static final Random RN = new Random();

	/**
	 * Добавление заявки в хранилище
	 *
	 * @param item новая заявка
	 * @return
	 */
	public Item add(Item item) {
		item.setId(this.generateId());
//          this.items[this.position++] = item;
		this.items.add(item);
		return item;
	}

	/**
	 * Редактирование заявки.
	 *
	 * @param id идентификатор заявки.
	 * @param item редактируемая заявка.
	 * @return
	 */
	public boolean replace(String id, Item item) {
		boolean result = false;
		int index = 0;
		for (Item it : items) {
			if (it.getId().equals(id)) {
				item.setId(id);
				items.set(index, item);
				result = true;
				break;
			}
			index++;
		}
		return result;
	}

	/**
	 * Удаление заявки.
	 *
	 * @param id идентификатор заявки.
	 * @return
	 */
	public boolean delete(String id) {
		boolean result = false;
		int index = 0;
		for (Item it : items) {
			if (it.getId().equals(id)) {
				items.remove(index);
				result = true;
				break;
			}
			index++;
		}
		return result;
	}

	/**
	 * Список всех заявок
	 *
	 * @return
	 */
	public ArrayList<Item> findAll() {
		ArrayList<Item> findItems = new ArrayList<>();
		findItems.addAll(items);
		return findItems;
	}

	/**
	 * Получение списка по имени из хранилища.
	 *
	 * @param key - ...
	 * @return
	 */
	public ArrayList<Item> findByName(String key) {
		ArrayList<Item> find = new ArrayList<>();
		Iterator<Item> it = items.iterator();
		while (it.hasNext()) {
			Item i = it.next();
			if (i.getName().equals(key)) {
				find.add(i);
			}
		}
		return find;
	}

	/**
	 * Получение заявки по id из хранилища.
	 *
	 * @param id идентификатор заявки.
	 * @return
	 */
	public Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (item.getId().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}

	/**
	 * Метод генерирует уникальный ключ для заявки. Так как у заявки нет
	 * уникальности полей, имени и описание. Для идентификации нам нужен
	 * уникальный ключ.
	 *
	 * @return Уникальный ключ.
	 */
	private String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
}
