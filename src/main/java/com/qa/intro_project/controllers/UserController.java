package com.qa.intro_project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.intro_project.data.entity.User;

@RestController
@RequestMapping(path = "/user") // accepts requests at localhost:8080/user
public class UserController {

	// the list of users is just pretending to be a database for now
	private static int COUNTER = 1;
	private List<User> users = new ArrayList<>(List.of(new User(COUNTER++, "Fred"), new User(COUNTER++, "Sarah")));
	
	// GET
	@GetMapping
	public List<User> getUsers() {
		return users;
	}
	
	// GET by id
	// - specify a variable in a path by surrounding it in curly braces
	@GetMapping(path = "/{id}") // localhost:8080/user/3
	public User getUser(@PathVariable(name = "id") int id) {
		for (int i = 0; i < users.size(); i++) {
			if (this.users.get(i).getId() == id) {
				return this.users.get(i);
			}
		}
		return null; // we should return a 404 not found response code
	}
	
	// POST
	@PostMapping // indicates POST requests are accepted at localhost:8080/user
	public User createUser(@Valid @RequestBody User user) {
		// The call to 
		user.setId(COUNTER++);
		users.add(user);
		return user;
	}
	
	// PUT
	@PutMapping(path = "/{id}")
	public User updateUser(@Valid @RequestBody User user, @PathVariable(name = "id") int id) {
		user.setId(id);
		
		this.users = this.users.stream().filter(u -> u.getId() == id).toList();
		
		this.users.add(user);
		
		return null;
	}
	
	// DELETE
	@DeleteMapping(path = "/{id}")
	public User deleteUser(@PathVariable(name = "id") int id) {
		// Your implementation here
		return null;
	}
}
