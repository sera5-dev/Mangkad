/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad;

import java.sql.Connection;
import mangkad.utils.SQLiteJDBCDriverConnection;

/**
 *
 * @author user
 */
public class Mangkad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if(true) {
            JFrameLogin loginWindow = new JFrameLogin();
            loginWindow.setLocationRelativeTo(null);
            loginWindow.setVisible(true);
            Connection conn = new SQLiteJDBCDriverConnection("sqlite.db").getConnection();
        }
    }
    
}
