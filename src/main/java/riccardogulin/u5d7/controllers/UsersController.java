package riccardogulin.u5d7.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import riccardogulin.u5d7.entities.User;
import riccardogulin.u5d7.exceptions.NotFoundException;
import riccardogulin.u5d7.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersService usersService;

	// 1. - GET http://localhost:3001/users (+opzionalmente query params) <-- READ
	@GetMapping("")
	public List<User> getUsers(){
		return usersService.getUsers();
	}

	// 2. - POST http://localhost:3001/users (+ req.body) <-- CREATE
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED) // <-- 201 CREATED
	public User saveUser(@RequestBody User body) {
		User createdUser = usersService.saveNewUser(body);
		return createdUser;
	}

	// 3. - GET http://localhost:3001/users/:userId <-- READ
	@GetMapping("/{userId}")
	public User findById(@PathVariable int userId) {
		return usersService.findUserById(userId).orElseThrow(() -> new NotFoundException(userId));
	}

	// 4. - PUT http://localhost:3001/users/:userId (+ req.body) <-- UPDATE
	@PutMapping("/{userId}")
	public User findByIdAndUpdate(@PathVariable int userId, @RequestBody User body) {
		return usersService.findUserByIdAndUpdate(userId, body).orElseThrow(() -> new NotFoundException(userId));
	}

	// 5. - DELETE http://localhost:3001/users/:userId <-- DELETE
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
	public void findByIdAndDelete(@PathVariable int userId) {
		usersService.findUserByIdAndDelete(userId).orElseThrow(() -> new NotFoundException(userId));
	}

}
