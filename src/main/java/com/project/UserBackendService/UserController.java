package com.project.UserBackendService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private UserRepo uRepo; 
	
	
	@GetMapping("/api/user")
	public List<UserModel> getAllUsers() {
		return uRepo.findAll();
	}
	
	@GetMapping("/api/user/{id}")
	public UserModel getUserById(@PathVariable("id") Integer id) {
		return uRepo.findById(id).get();
	}

	@PostMapping("/api/user")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public UserModel addUser(@RequestBody UserModel um) {
		if(um.getId() == 0) {
			int c = (int)uRepo.count() + 1;
			um.setId(c);
		} 
		uRepo.save(um);
		return um;
	}
	
	@DeleteMapping("/api/user/{id}")
	public List<UserModel> deleteUsersById(@PathVariable("id") Integer id){
		uRepo.deleteById(id);
		return uRepo.findAll();
	}
	
	@DeleteMapping("/api/user")
	public List<UserModel> deleteAllUsers() {
		uRepo.deleteAll();
		return uRepo.findAll();
	}
	
	@GetMapping("/api/user/email/{email}")
	public UserModel getPassword(@PathVariable("email")String email) {
		UserModel u = uRepo.findByEmail(email);
		return u;
	}
	@GetMapping("/api/user/email/{email}/{password}")
	public Boolean getPassword1(@PathVariable("email")String email, @PathVariable("password")String pass) {
		UserModel u = uRepo.findByEmail(email);
		if(u.getPassword().equals(pass)) {
			return true;
		}
		return false;
	}
	@PostMapping("/api/user/email")
	@Consumes({MediaType.APPLICATION_JSON})
	public Boolean getPassword2(@RequestBody UserModel um) {
		UserModel um2 = uRepo.findByEmail(um.getEmail());
		if(um.getPassword().equals(um2.getPassword())) {
			return true;
		}
		return false;
	}
}
