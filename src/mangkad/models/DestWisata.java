/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad.models;


/**
 *
 * @author user
 */
public class DestWisata {
    
    private String name;
    private long latitude;
    private long longitude;
    private int jumlah_kunjungan;
    private String kunjungan_terakhir;
    private final int id;
    private final String deskripsi;
    
    public DestWisata(int id, String name, long latitude, long longitude, int jml_kunjungan, String kunjungan_last, String deskripsi) {
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
    
    public String getKunjunganTerakhir() {
        return this.kunjungan_terakhir;
    }
    
    public void setJmlKunjungan(int i) {
        this.jumlah_kunjungan += i;
    }
}
