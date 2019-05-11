/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad.utils;

/**
 *
 * @author user
 */
public class User {
    private int user_id;
    private String username;
    
    public User(int user_id, String usrname) {
        this.user_id = user_id;
        this.username = usrname;
    }
    
    public int getUserID() {
        return this.user_id;
    }
    
    public String getUsername() {
        return this.username;
    }
}

