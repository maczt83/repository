/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.exceptions;

/**
 *
 * @author Attila
 */
public class PasswordResetTokenAlreadyExist extends Exception{

    public PasswordResetTokenAlreadyExist(String message) {
        super(message);
    }
    
}
