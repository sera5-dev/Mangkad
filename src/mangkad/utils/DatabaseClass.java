/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import mangkad.models.DestWisata;
import mangkad.models.KategoriDest;


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
        ArrayList<String> createTable = new ArrayList<>();
        
        createTable.add("CREATE TABLE IF NOT EXISTS USER("
                + "USER_ID INTEGER PRIMARY KEY, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "PASSWORD VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL "
                + ");");
        
        createTable.add("CREATE TABLE IF NOT EXISTS KATEGORI("
                + "KATEGORI_ID INTEGER PRIMARY KEY, "
                + "NAMA_KATEGORI VARCHAR(20) NOT NULL, "
                + "DESKRIPSI_KATEGORI TEXT, "
                + "TGL_KAT_BUAT DATE NOT NULL "
                + ");");
        
        createTable.add("CREATE TABLE IF NOT EXISTS DEST_WISATA("
                + "DEST_ID INTEGER PRIMARY KEY, "
                + "KATEGORI_ID INTEGER NOT NULL, "
                + "NAMA_DEST VARCHAR(20) NOT NULL, "
                + "DESKRIPSI_KATEGORI TEXT, "
                + "LATITUDE REAL NOT NULL, "
                + "LONGITUDE REAL NOT NULL, "
                + "TGL_DEST_BUAT DATE NOT NULL, " 
                + "FOREIGN KEY (KATEGORI_ID) REFERENCES KATEGORI(KATEGORI_ID)"
                + ");");
        
        createTable.add("CREATE TABLE IF NOT EXISTS REVIEW_DEST_WISATA("
                + "REVIEW_ID INTEGER PRIMARY KEY, "
                + "DEST_ID INTEGER NOT NULL, "
                + "TGL_REVIEW_BUAT DATE NOT NULL, " 
                + "FOREIGN KEY (DEST_ID) REFERENCES DEST_WISATA(DEST_ID)"
                + ");");
        
        createTable.add("CREATE TABLE IF NOT EXISTS PLAN_WISATA("
                + "PLAN_ID INTEGER PRIMARY KEY, "
                + "DEST_ID INTEGER NOT NULL, "
                + "USER_ID INTEGER NOT NULL, " 
                + "TGL_KUNJUNGAN DATE NOT NULL, " 
                + "TGL_RENCANA_BUAT DATE NOT NULL, " 
                + "FOREIGN KEY (DEST_ID) REFERENCES DEST_WISATA(DEST_ID),"
                + "FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)"
                + ");");
        
        try {
            for(String a: createTable) {
                con1.prepareStatement(a).execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           
        }
    }
    
    public DefaultListModel<KategoriDest> getKategori(){
        DefaultListModel<KategoriDest> result = new DefaultListModel<>();
        ResultSet rs = null;
        
         try {
            rs = con1.createStatement().executeQuery("SELECT * FROM KATEGORI");
            while(rs.next()) {
                result.addElement(new KategoriDest(rs.getInt("KATEGORI_ID"),rs.getString("NAMA_KATEGORI")));
            }
         } catch(SQLException e) {
             System.out.println(e.getSQLState());
         }
         
         return result;
    }
    
    public ArrayList<DestWisata> getDestWisata(int idx) {
        ArrayList<DestWisata> result = new ArrayList<>();
        ResultSet rs = null;
        
         try {
            String selectSQL = "SELECT * FROM DEST_WISATA WHERE DEST_ID = ?";
            PreparedStatement preparedStatement = con1.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idx);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(new DestWisata(
                        rs.getInt("DEST_ID"),
                        rs.getString("NAMA_DEST"),
                        rs.getLong("LATITUDE"),
                        rs.getLong("Longitude"),
                        getJumlahKunjungan(rs.getInt("DEST_ID")),
                        ""
                ));
            }
         } catch(SQLException e) {
             System.out.println("Error: "+e.toString());
         }
         
         return result;
    }
    
    public DestWisata getDestWisata(int kategori, int row) {
        ArrayList<DestWisata> src = getDestWisata(kategori);
        
        return src.get(row);
    }
    
    public int getJumlahKunjungan(int idx) {
        int result = 0;
        
        return result;
    }
}
