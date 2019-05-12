/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad.models;

import java.util.Comparator;
import java.util.Date;


/**
 *
 * @author user
 */
public class DestWisata  {
    
    private String name;
    private long latitude;
    private long longitude;
    private int jumlah_kunjungan = 0;
    private Date kunjungan_terakhir = new Date(0);
    private final int id;
    private final String deskripsi;
    
    public DestWisata(int id, String name, long latitude, long longitude, int jml_kunjungan, Date kunjungan_last, String deskripsi) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jumlah_kunjungan = jml_kunjungan;
        this.kunjungan_terakhir = kunjungan_last;
        this.id = id;
        this.deskripsi = deskripsi;
    }
    
    public String getDeskripsi() {
        return this.deskripsi;
    }
    
    public int getID() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getLatitude() {
        return this.latitude;
    }
    
    public long getLongitude() {
        return this.longitude;
    }
    
    public int getJmlKunjungan() {
        return this.jumlah_kunjungan;
    }
    
    public Date getKunjunganTerakhir() {
        return this.kunjungan_terakhir;
    }
    
    public void setJmlKunjungan(int i) {
        this.jumlah_kunjungan += i;
    }
    
    public static class KunjunganComparator implements Comparator<DestWisata> {

        @Override
        public int compare(DestWisata t, DestWisata t1) {
            return t1.getJmlKunjungan() - t.getJmlKunjungan();
        }
        
    }
    
    public static class LastVisitComparator implements Comparator<DestWisata> {

        @Override
        public int compare(DestWisata t, DestWisata t1) {
            if(t.getKunjunganTerakhir()!=null&& t1.getKunjunganTerakhir()!=null) return t1.getKunjunganTerakhir().compareTo(t.kunjungan_terakhir);
            else return new Date(0).compareTo(new Date(0));
        }
        
    }
}
