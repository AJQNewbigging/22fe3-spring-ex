package com.qa.intro_project.data.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Spring has a 'spring-boot-starter-validation' module which transitively includes
// the 'hibernate-validator' dependency. This can be used to apply validation annotations
// to data transfer or domain objects.
// 
// The preferred method is to annotate your fields with validation attributes, but you
// can annotate methods. Do not do both to avoid unnecessarily validating twice.
//
// Validation must be triggered via the @Valid annotation, see `UserController -> createUser()`.
//
// Validation annotations include:
// - @Email            Applies strict email validation according to the Bean validation provider
// - @NotBlank         The field must not be null and contain at least 1 non-whitespace character
// - @Min @Max         Used to specify the minimum and maximum ranges of a numeric value respectively (inclusive)
// - @Past @Future     Used to specify that a date should be in the past or the future respectively
// - @PastOrPresent    Specifies that the date should be in the past or now
// - @FutureOrPresent  Specifies that the date should be in the future or now
// - @Pattern          Use to provide a regex pattern

public class User {

	private int id;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 16, message = "Username must have at least 2 characters, but no more than 16")
	private String username;
	
	@Email
	@NotBlank
	@NotNull
	private String email;
	
	@Min(16)
	@Max(120)
	private int age;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 16, message = "Last name must have at least 2 characters, but no more than 16")
	private String lastName;
	
	// TODO: Add 3 new fields to the User class, with appropriate validation annotations applied to each
	
	public User() {
		
	}

	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}
	
}
