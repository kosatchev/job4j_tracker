package ru.job4j.tracker;

import java.util.Objects;

public class Item implements Comparable<Item> {

    private String id;
    private String name;
    private String decs;
    private long time;

    public Item(String name, String decs, long time) {
	this.name = name;
	this.decs = decs;
	this.time = time;
    }

    public Item(String id, String name, String decs, long time) {
	this.name = name;
	this.decs = decs;
	this.time = time;
	this.id = id;
    }
	
    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDesc() {
	return decs;
    }

    public void setDecs(String decs) {
	this.decs = decs;
    }

    public long getTime() {
	return time;
    }

    public void setTime(long time) {
	this.time = time;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}
	Item item = (Item) o;
	return time == item.time
		&& Objects.equals(id, item.id)
		&& Objects.equals(name, item.name)
		&& Objects.equals(decs, item.decs);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id, name, decs, time);
    }

    @Override
    public int compareTo(Item o) {
	return this.name.compareTo(o.name);
    }
    
    @Override
    public String toString() {
	return this.name;
    }
}
