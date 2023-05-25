package com.humber.j2ee.data;

import java.util.ArrayList;
import java.util.List;

import com.humber.j2ee.model.Dish;


// this class acts like a database for us (since we are not working with database now)
public class DishDB {
	public static List<Dish> dishes = new ArrayList<>();
	
	static {
		dishes.add(new Dish(1,"Pizza",12,"Non veg","Italian","Tasty food"));
		dishes.add(new Dish(2,"Momo",5,"Veg","Gujrati","Veg food ew"));
		dishes.add(new Dish(3,"Katsu Curry",20,"Non veg","Japanese","Yummy"));
		dishes.add(new Dish(4,"Chhat",5,"Veg","Indian","Veg food that I would accept"));
	}
}
