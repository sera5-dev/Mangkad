/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import mangkad.models.DestWisata;
import mangkad.models.KategoriDest;
import mangkad.utils.DatabaseClass;

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
        DatabaseClass dc = new DatabaseClass("mangkad.db");
        DefaultListModel<KategoriDest> kategori = dc.getKategori();
        DefaultTableModel tempatWisata = new DefaultTableModel();
        
        JLabel hint = new JLabel("Tidak ada data");
        
        tempatWisata.addColumn("Nama Tempat");
        tempatWisata.addColumn("Jumlah Kunjungan");
        tempatWisata.addColumn("Terakhir Dikunjungi");
        
        
        tempatWisata.addTableModelListener((TableModelEvent e) -> {
            DefaultTableModel model = (DefaultTableModel)e.getSource();
            hint.setVisible(model.getRowCount()==0);
        });
        
        JFrameMain jMain = new JFrameMain();
        JList listKategori  = jMain.JKategori;
        JTable listDest      = jMain.tableDestinasi;
        
        listDest.setFillsViewportHeight(true);
        listDest.setLayout(new GridBagLayout());
        listDest.add(hint);

        listKategori.addListSelectionListener((ListSelectionEvent arg0) -> {
            if (!arg0.getValueIsAdjusting()) {
                int idx = kategori.getElementAt(listKategori.getSelectedIndex()).getID();
            }
        });
        
        listKategori.setModel(kategori);
        listDest.setModel(tempatWisata);
        
        jMain.setLocationRelativeTo(null);
        jMain.setVisible(true);
    }
    
}
