package com.humber.j2ee.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humber.j2ee.model.Dish;
import com.humber.j2ee.service.DishService;

@RestController
@RequestMapping("/api")
public class HomeController {

	@Autowired
	private DishService dishService;

	@GetMapping("/dish")
	public List<Dish> getAllDishes() {
		return dishService.getAllDishes();
	}

	// path variable = {id}
	@GetMapping("/dish/{id}")
	public Optional<Dish> getDishById(@PathVariable int id) {
		return dishService.getDishById(id);
	}

	@PostMapping("/dish")
//	ResponseEntity will return status code (whether a post was successful or not)
	public ResponseEntity<String> addDish(@RequestBody Dish dish) {
		try {
			dishService.addDish(dish);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/dish")
	public ResponseEntity<String> updateDish(@RequestBody Dish dish) {
		try {
			dishService.updateDish(dish);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Dish with id " + dish.getId() + " has been updated!");
	}
	
//	@DeleteMapping("/dish/{id}")
//	public Optional<Dish> deleteDishById(@PathVariable int id){
//		return dishService.deleteDishById(id);
//	}
	@DeleteMapping("/dish/{id}")
	public ResponseEntity<String> deleteDishById(@PathVariable int id){
		try {
			dishService.deleteDishById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Dish with id " + id + " has been deleted!");
	}
}
