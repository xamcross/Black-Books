package xam.cross.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xam.cross.entity.Role;
import xam.cross.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByName(String name) {
		List<Role> roles = roleRepository.findAll();
		for (Role role : roles){
			if (role.getName().equals(name)){
				return role;
			}
		}
		return null;
	}
}
