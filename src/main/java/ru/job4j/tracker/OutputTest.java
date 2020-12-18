package ru.job4j.tracker;


import java.util.ArrayList;
import java.util.Collections;
import ru.job4j.tracker.Item;

public class OutputTest {
 
    public static void main(String[] args) {


	Item item1 = new Item("test1", "testDescription", System.currentTimeMillis());
	Item item2 = new Item("test2", "atestDescription2", System.currentTimeMillis());
	Item item3 = new Item("test3", "testDescription3", System.currentTimeMillis());

	ArrayList<Item> unsorted = new ArrayList<Item>();
	unsorted.add(item2);
	unsorted.add(item3);
	unsorted.add(item1);

	ArrayList<Item> sorted = new ArrayList<Item>();
	sorted.add(item1);
	sorted.add(item2);
	sorted.add(item3);

	System.out.println("unsorted: " + unsorted);
	System.out.println("sorted: " + sorted);
	Collections.sort(unsorted);
	System.out.println("sort(unsorted): " + unsorted);
	
	ItemReverseNameComparator reverseName = new ItemReverseNameComparator();
	Collections.sort(unsorted, reverseName);
	System.out.println("reverseName sorted: " + unsorted);
	
    }
}