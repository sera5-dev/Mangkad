/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import mangkad.utils.DatabaseClass;
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
            
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS USER("
                + "USER_ID NUMBER(5) NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "PASSWORD VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
                + ");";
        
        createTableSQL += "CREATE TABLE IF NOT EXISTS KATEGORI("
                + "KATEGORI_ID NUMBER(5) NOT NULL, "
                + "NAMA_KATEGORI VARCHAR(20) NOT NULL, "
                + "DESKRIPSI_KATEGORI TEXT, "
                + "TGL_KAT_BUAT DATE NOT NULL, " + "PRIMARY KEY (KATEGORI_ID) "
                + ");";
        
        createTableSQL += "CREATE TABLE IF NOT EXISTS DEST_WISATA("
                + "DEST_ID NUMBER(5) NOT NULL, "
                + "KATEGORI_ID NUMBER(5) NOT NULL, "
                + "NAMA_DEST VARCHAR(20) NOT NULL, "
                + "DESKRIPSI_KATEGORI TEXT, "
                + "TGL_DEST_BUAT DATE NOT NULL, " 
                + "PRIMARY KEY (DEST_ID), "
                + "FOREIGN KEY (KATEGORI_ID) REFERENCES KATEGORI(KATEGORI_ID)"
                + ");";
        
        createTableSQL += "CREATE TABLE IF NOT EXISTS REVIEW_DEST_WISATA("
                + "REVIEW_ID NUMBER(5) NOT NULL, "
                + "DEST_ID NUMBER(5) NOT NULL, "
                + "TGL_REVIEW_BUAT DATE NOT NULL, " 
                + "PRIMARY KEY (REVIEW_ID), "
                + "FOREIGN KEY (DEST_ID) REFERENCES DEST_WISATA(DEST_ID)"
                + ");";
        
        createTableSQL += "CREATE TABLE IF NOT EXISTS PLAN_WISATA("
                + "PLAN_ID NUMBER(5) NOT NULL, "
                + "DEST_ID NUMBER(5) NOT NULL, "
                + "USER_ID NUMBER(5) NOT NULL, " 
                + "TGL_KUNJUNGAN DATE NOT NULL, " 
                + "TGL_RENCANA_BUAT DATE NOT NULL, " 
                + "PRIMARY KEY (PLAN_ID), "
                + "FOREIGN KEY (DEST_ID) REFERENCES DEST_WISATA(DEST_ID),"
                + "FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)"
                + ");";
        
        System.out.println(createTableSQL);
        }
    }
    
    public static void gotoMain() {
        DatabaseClass dc = new DatabaseClass("mangkad.db");
        dc.firstInit();
        JFrameMain jMain = new JFrameMain();
        jMain.setLocationRelativeTo(null);
        jMain.setVisible(true);
    }
    
}
