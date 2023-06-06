package riccardogulin.u5d7.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import riccardogulin.u5d7.entities.User;

@RestController
@RequestMapping("/examples") // prefisso per tutti gli endpoint di questo controller
public class ExamplesController {

	@GetMapping("/index")
	public String index() {
		return "<h1>Pagina principale</h1>";
	}

	@GetMapping("/about")
	public String about() {
		return "<h1>Pagina about</h1>";
	}

	@GetMapping("/queryParamsExample")
	public String queryParams(@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname) {
		return "Hello " + name + " " + surname;
	}

	@GetMapping("/pathParamsExample/{parameter}")
	public String pathParams(@PathVariable String parameter) {
		return "Ciao sei sull'endpoint /pathParamsExample/" + parameter;
	}

	@GetMapping("/pathParamsExample")
	public String noPathParams() {
		return "Ciao sei sull'endpoint /pathParamsExample/";
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1, "Aldo", "Baglio"));
		users.add(new User(2, "Giovanni", "Storti"));
		users.add(new User(3, "Giacomo", "Poretti"));
		return users; // users verrà convertito automaticamente in Json
	}

	@GetMapping("/users/{id}")
	public Optional<User> getSingleUser(@PathVariable String id) {
		List<User> users = new ArrayList<User>();
		users.add(new User(1, "Aldo", "Baglio"));
		users.add(new User(2, "Giovanni", "Storti"));
		users.add(new User(3, "Giacomo", "Poretti"));

		User u = null;

		for (User user : users) {
			if (Integer.toString(user.getId()).equals(id)) {
				u = user;
			}
		}

		return Optional.ofNullable(u); // user/null verrà convertito automaticamente in Json

	}

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		user.setId(4);
		System.out.println("User: " + user + " è stato aggiunto al db!");
		return user;
	}

	@GetMapping("/additionalHeadersExample")
	public ResponseEntity<User> additionalHeadersExample() {

		// Creo degli headers custom
		HttpHeaders headers = new HttpHeaders();
		headers.add("customHeader", "customValue");

		User body = new User(5, "Claudio", "Bisio");

		return new ResponseEntity<User>(body, headers, HttpStatus.OK); // costruttore con 3 parametri: body, headers,
																		// statuscode

	}

	@GetMapping("/kaboom")
	public void kaboom() throws Exception {
		throw new Exception("KABOOOOOOOOOOOOOOOOOOOM");
	}
}
