/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            
            loginWindow.btnLogin.addActionListener((ActionEvent ae) -> {
               loginWindow.dispose();
               gotoMain();
            });
            loginWindow.setVisible(true);
        }
    }
    
    public static void gotoMain() {
        JFrameMain jMain = new JFrameMain();
        jMain.setLocationRelativeTo(null);
        jMain.setVisible(true);
    }
    
}
