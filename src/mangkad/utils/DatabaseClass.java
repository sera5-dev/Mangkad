/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad.utils;

import java.sql.Connection;


/**
 *
 * @author user
 */
public class DatabaseClass {
    Connection con1;
    
    public DatabaseClass(String filename) {
        con1 = new SQLiteJDBCDriverConnection("mangkad.db").getConnection();
    }
}
