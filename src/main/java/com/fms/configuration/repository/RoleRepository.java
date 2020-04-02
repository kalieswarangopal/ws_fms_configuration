package com.fms.configuration.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.configuration.entity.Role;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role, Integer>{

}
