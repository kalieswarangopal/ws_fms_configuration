package com.fms.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fms.configuration.entity.Role;
import com.fms.configuration.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public Mono<Role> createRole(Role role) {

		return roleRepository.save(role);
	}
	
	public Flux<Role> getRoles(){
		
		return roleRepository.findAll();
	}
	
	public Mono<Void> deleteRole(Integer id){
		
		return roleRepository.deleteById(id);
	}

}
