package com.humber.j2ee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.humber.j2ee.data.DishDB;
import com.humber.j2ee.model.Dish;

@Service
public class DishService {

	//get all dishes
	public List<Dish> getAllDishes() {
		return DishDB.dishes;
	}
	
	//get dish by id
//	optional means it might be a dish or might not be a dish - i.e. it can return null if nothing is found
	public Optional<Dish> getDishById(int id) {
		// using java streams from Java 8
		return DishDB.dishes.stream().filter(d -> d.getId() == id).findFirst();
	}
	
	//add dish
	public void addDish(Dish dish) throws Exception {
		//check if id already exits, throw an exception if it exists
		boolean isExists = DishDB.dishes.stream().anyMatch(d -> d.getId() == dish.getId());
		if(isExists) throw new Exception ("A dish with " + dish.getId() + " already exists!");
		DishDB.dishes.add(dish);
	}
	
	//update dish
	public void updateDish(Dish dish) throws Exception{
		boolean isExists = DishDB.dishes.stream().anyMatch(d -> d.getId() == dish.getId());
		if(!isExists) throw new Exception ("A dish with id " + dish.getId() + " doesn't exist!");
		DishDB.dishes.stream()
		.filter(d-> d.getId() == dish.getId())
		.findFirst()
		.ifPresent(d -> {
			d.setName(dish.getName());
			d.setPrice(dish.getPrice());
			d.setCategory(dish.getCategory());
			d.setCuisine(dish.getCuisine());
			d.setDescription(dish.getDescription());
		});
	}
	
	//delete dish by id
//	public Optional<Dish> deleteDishById(int id){
//		Optional<Dish> deletedDish =  DishDB.dishes.stream()
//				.filter(d -> d.getId() == id)
//				.findFirst();
//		
//		deletedDish.ifPresent(d -> DishDB.dishes.remove(d));
//		return deletedDish;
//	}
	
	public void deleteDishById(int id) throws Exception{
		boolean isExists = DishDB.dishes.stream().anyMatch(d -> d.getId() == id);
		if(!isExists) throw new Exception ("A dish with id " + id + " doesn't exist!");
		DishDB.dishes.stream()
				.filter(d -> d.getId() == id)
				.findFirst().
				ifPresent(d -> DishDB.dishes.remove(d));
	}
}
