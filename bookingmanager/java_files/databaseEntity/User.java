/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "user")


public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(unique = true)
    @NotNull
    @Size(min = 1, max = 45)
    private String username;
    @NotNull
    @Size(min = 1, max = 60)
    private String password;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @NotNull
    @Size(min = 1, max = 45)
    private String email;
    private Short active;
    @NotNull
    @Size(min = 1, max = 45)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 45)
    private String lastName;
    @JoinColumn(name = "role_id")
    @ManyToOne
    private Role role;

    public User() {
        
    }

   

    public User(String username, String password, String email, String firstName, String lastName, Role role) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "progmatic.databaseEntity.User[ id=" + id + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(createAuthority(role.getRoleName()));      
        return authorities;
    }
    
    private GrantedAuthority createAuthority(String roleName){
        return new SimpleGrantedAuthority("ROLE_" + roleName.toUpperCase() );
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//      //TODO
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//      //TODO
        return true;
    }

    @Override
    public boolean isEnabled() {
        
        return active==1?true:false;
    }
    
}
