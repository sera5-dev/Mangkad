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
public class KategoriDest {
    int kategoriID;
    String kategoriName;
    
    public KategoriDest(int id, String name) {
        this.kategoriID = id;
        this.kategoriName = name;
    }
    
    public void setID(int id) {
        this.kategoriID = id;
    }
    
    public void setName(String name) {
        this.kategoriName = name;
    }
    
    public int getID() {
        return this.kategoriID;
    }
    
    public String getName() {
        return this.kategoriName;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
