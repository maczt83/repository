/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.Role;

/**
 *
 * @author Stankye
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findById(long id);
    Role findByRoleName(String roleName);
}
