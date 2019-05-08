/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author user
 */
public class DatabaseClass {
    Connection con1;
    
    public DatabaseClass(String filename) {
        con1 = new SQLiteJDBCDriverConnection(filename).getConnection();
    }
    
    public void firstInit() {
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
        
        createTableSQL += "CREATE TABLE IF NOT EXISTS RENCANA_WISATA("
                + "PLAIN_ID NUMBER(5) NOT NULL, "
                + "DEST_ID NUMBER(5) NOT NULL, "
                + "USER_ID NUMBER(5) NOT NULL, " 
                + "TGL_KUNJUNGAN DATE NOT NULL, " 
                + "TGL_RENCANA_BUAT DATE NOT NULL, " 
                + "PRIMARY KEY (PLAN_ID), "
                + "FOREIGN KEY (DEST_ID) REFERENCES DEST_WISATA(DEST_ID),"
                + "FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)"
                + ");";
        
        try {
            con1.prepareStatement(createTableSQL).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }

    }
}
