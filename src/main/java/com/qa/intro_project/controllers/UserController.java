package com.qa.intro_project.controllers;

import java.util.ArrayList;
import java.util.List;

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
		// we need to get the id out of the path, this can be done with the @PathVariable annotation
		// - the 'name' attribute takes the name of the path variable
		// - this then gets the path variable from the path and inserts it into the parameter 'int id'
		
		
		User user = users.stream()
						  .filter(u -> u.getId() == id)
						  .findFirst()
						  .orElse(null);
		return user;
	}
	
	// POST
	@PostMapping // indicates POST requests are accepted at localhost:8080/user
	public User createUser(@RequestBody User user) {
		// @RequestBody tells Spring to retrieve the data in the requests body and then create an instance
		// of User (using the default empty constructor) and then calls its mutators (setters) to set its values
		user.setId(COUNTER++);
		users.add(user);
		return user;
	}
	
	// PUT
	@PutMapping(path = "/{id}")
	public User updateUser(@RequestBody User user, @PathVariable(name = "id") int id) {
		user.setId(id);
		
		users = users.stream().filter(u -> u.getId() != id).toList();
		
		users.add(user);
		
		return user;
	}
	
	// DELETE
	@DeleteMapping(path = "/{id}")
	public User deleteUser(@PathVariable(name = "id") int id) {
		users = users.stream().filter(u -> u.getId() != id).toList();
		return null;
	}
	
}
