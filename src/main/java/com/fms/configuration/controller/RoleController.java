package com.fms.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fms.configuration.entity.Role;
import com.fms.configuration.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping
	public ResponseEntity<Mono<Role>> createRole(@RequestBody Role role) {
		
		Mono<Role> response = roleService.createRole(role);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public Flux<Role> getRoles(){
		
		return roleService.getRoles();
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteRole(@PathVariable Integer id){
		
		return roleService.deleteRole(id);
	}

}
