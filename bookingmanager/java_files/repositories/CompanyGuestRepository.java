/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.repositories;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.CompanyGuest;

/**
 *
 * @author Anna
 */
public interface CompanyGuestRepository extends JpaRepository<CompanyGuest, Long>{
    List<CompanyGuest> findByCompanyName(String companyName);    
    List<CompanyGuest> findAll();
    List<CompanyGuest> findAllByOrderByCompanyNameAsc();
}
