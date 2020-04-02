package com.fms.configuration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	
	@Id
	@Column("ID")
	private Integer id;
	
	@Column("EmployeeID")
	private Integer employeeID;
	
	@Column("FirstName")
	private String firstName;
	
	@Column("LastName")
	private String lastName;
	
	@Column("email")
	private String email;
	
	@Column("role")
	private String role;

}
