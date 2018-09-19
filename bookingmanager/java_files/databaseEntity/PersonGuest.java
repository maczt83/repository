/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.databaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Anna
 */
@Entity
public class PersonGuest extends Guest {

    @NotNull
    @Size(min = 1, max = 60)
    //regex nem tudom hogy jó-e
    @Pattern(regexp = "^([A-ZÁÉÍÓÖŐÜŰÚ]([a-záéíöüóőúű]+([- ])?|'|. ))+.?$")
    private String firstName;
    @NotNull
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^([A-ZÁÉÍÓÖŐÜŰÚ]([a-záéíöüóőúű]+([- ])?|'|. ))+.?$")
    private String lastName;

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
    
    public String getName(){
        return lastName+" "+firstName;
    }

}
