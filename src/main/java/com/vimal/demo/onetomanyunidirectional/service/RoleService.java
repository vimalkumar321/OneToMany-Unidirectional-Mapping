package com.vimal.demo.onetomanyunidirectional.service;

import com.vimal.demo.onetomanyunidirectional.model.Role;
import com.vimal.demo.onetomanyunidirectional.model.User;
import com.vimal.demo.onetomanyunidirectional.repository.RoleRepository;
import com.vimal.demo.onetomanyunidirectional.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    /**
    * Create a new role along with users
     */
    @Transactional
    public ResponseEntity<Object> addRole(Role role) {

        Role newRole = new Role();
        newRole.setName(role.getName());
        newRole.setDescription(role.getDescription());

        /*for(int i=0; i< role.getUsers().size(); i++){
            User savedUser = userRepository.save(role.getUsers().get(i));
            if(!userRepository.findById(savedUser.getId()).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed creating user and roles");
        }*/

        newRole.setUsers(role.getUsers());
        Role savedRole = roleRepository.save(newRole);
        if (roleRepository.findById(savedRole.getId()).isPresent()) {
            return ResponseEntity.accepted().body("Successfully Created Role and Users");
        }
        else
            return ResponseEntity.unprocessableEntity().body("Failed to Create specified Role");

    }

    /**
     * Delete a specified role given the id
     */
    @Transactional
    public ResponseEntity<Object> deleteRole(Long id) {
        if (roleRepository.findById(id).isPresent()) {
            roleRepository.deleteById(id);
            if (roleRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
        return ResponseEntity.unprocessableEntity().body("No Records Found");
    }

}
