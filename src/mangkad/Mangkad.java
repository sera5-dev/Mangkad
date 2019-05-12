/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangkad;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import mangkad.models.DestWisata;
import mangkad.models.KategoriDest;
import mangkad.utils.DatabaseClass;
import mangkad.utils.MessageUtils;
import mangkad.utils.User;

/**
 *
 * @author user
 */
public class Mangkad {
    
    static User usr;
    static DatabaseClass dc;
    static JFrameMain jMain;
    static DefaultTableModel tempatWisata;
    static DefaultListModel<KategoriDest> kategori;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        dc = new DatabaseClass("mangkad.db");
        if(true) {
            JFrameLogin loginWindow = new JFrameLogin();
            loginWindow.setLocationRelativeTo(null);
            
            loginWindow.btnViewPasword.addItemListener((ItemEvent ev) -> {
                if(ev.getStateChange()==ItemEvent.SELECTED){
                    loginWindow.txtPassword.setEchoChar((char) 0);
                } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                    loginWindow.txtPassword.setEchoChar('*');
                }
            });
            
            loginWindow.btnBrowseOnly.addActionListener((ActionEvent ae) -> {
                int dialogResult = JOptionPane.showConfirmDialog (loginWindow, 
                        "Anda tidak dapat membuat rencana perjalanan atau meninjau tempat wisata ini.\n"
                                + "Yakin untuk melanjutkan?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION);
                
                if(dialogResult == JOptionPane.YES_OPTION){
                    loginWindow.dispose();
                    gotoMain();
                }
            });
            
            loginWindow.btnLogin.addActionListener((ActionEvent ae) -> {
                usr = dc.login(loginWindow.txtUsername.getText(), loginWindow.txtPassword.getText());
                if(usr!=null) {
                    loginWindow.dispose();
                    gotoMain();
                } else {
                    JOptionPane.showMessageDialog(loginWindow, "Tidak dapat login.\nPastikan username atau password benar.");
                }
            });
            
            loginWindow.setVisible(true);
 
        }
    }
    
    public static void gotoMain() {
        kategori = dc.getKategori();
        
        tempatWisata = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        
        JLabel hint = new JLabel("Tidak ada data");
        
        tempatWisata.addColumn("Nama Tempat");
        tempatWisata.addColumn("Jumlah Kunjungan");
        tempatWisata.addColumn("Terakhir Dikunjungi");
        
        
        tempatWisata.addTableModelListener((TableModelEvent e) -> {
            DefaultTableModel model = (DefaultTableModel)e.getSource();
            hint.setVisible(model.getRowCount()==0);
        });
        
        jMain = new JFrameMain();
        JList listKategori  = jMain.JKategori;
        JTable listDest      = jMain.tableDestinasi;
        
        listDest.setFillsViewportHeight(true);
        listDest.setLayout(new GridBagLayout());
        listDest.add(hint);

        listKategori.addListSelectionListener((ListSelectionEvent arg0) -> {
            if(arg0.getValueIsAdjusting()) {
                int idx = kategori.getElementAt(listKategori.getSelectedIndex()).getID();
                
                
                updateDestList(idx);
            }
        });
        //java.lang.ArrayIndexOutOfBoundsException
        
        listDest.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                try {
                    JTable table =(JTable) mouseEvent.getSource();
                    Point point = mouseEvent.getPoint();
                    int row = table.rowAtPoint(point);          
                    int col = table.columnAtPoint(point);
                    int idx = kategori.getElementAt(listKategori.getSelectedIndex()).getID();

                    DestWisata dw = dc.getDestWisata(idx, row);

                    if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() >= 0 && row >= 0) {
                        //JOptionPane.showMessageDialog(jMain, dw.getID());
                        jMain.setCursor(Cursor.WAIT_CURSOR);
                        gotoDetails(jMain, dw);
                        jMain.setCursor(Cursor.DEFAULT_CURSOR);
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    
                }
            }
        });
               
        listKategori.setModel(kategori);
        listDest.setModel(tempatWisata);
        
        jMain.setLocationRelativeTo(null);
        jMain.setVisible(true);
    }
    
    public static void gotoDetails(JFrameMain jMain, DestWisata item) {
        JDialogDetails jDetails = new JDialogDetails(jMain, true, item);
        JLabel lblDetails   = jDetails.lblDetails;
        JTextArea txtReview = jDetails.txtReview;
        JButton btnPlan     = jDetails.btnPlan;
        
        txtReview.setLineWrap(true);
        txtReview.setWrapStyleWord(true);
        
        lblDetails.setText(item.getName());
        txtReview.setText(!item.getDeskripsi().isEmpty() ? item.getDeskripsi() : txtReview.getText());
        
        if(usr==null) btnPlan.setVisible(false);
                
        btnPlan.addActionListener((ActionEvent e) -> {
            gotoPlan(jDetails,item);
        });
        
        jDetails.setLocationRelativeTo(jMain);
        jDetails.setVisible(true);

        
    }
    
    public static void gotoPlan(JDialog jMain, DestWisata item) {
        JDialogPlanWisata jPlan = new JDialogPlanWisata(jMain, true, item);
        JDateChooser myDate = jPlan.jDatePilih;
        JButton confirm     = jPlan.btnMakePlan;
        
        confirm.addActionListener((ActionEvent ae) -> {
            Date d = myDate.getCalendar().getTime();
            int insert = dc.insertPlan(item, usr.getUserID(), d);
            switch(insert) {
                case 0:
                    MessageUtils.showErrorDialog(jMain, "Data tidak dapat dimasukkan.");
                    break;
                case -1:
                    MessageUtils.showErrorDialog(jMain, "Data tidak dapat dimasukkan karena ada kesalahan teknis.");
                    break;
                default:
                    MessageUtils.showInfoDialog(jMain, "Data berhasil dimasukkan.");
                    updateDestList();
                    jPlan.dispose();
                    
            }
        });
        
        jPlan.setResizable(false);
        jPlan.setLocationRelativeTo(jMain);
        jPlan.setVisible(true);
    }
    
    public static void updateDestList(int idx) {
        ArrayList<DestWisata> dw = dc.getDestWisata(idx);
                
        tempatWisata.setRowCount(0);

        dw.forEach((item) -> {
            tempatWisata.addRow(new Object[]{item.getName(),item.getJmlKunjungan(),item.getKunjunganTerakhir()});
        });
    }
    
    public static void updateDestList() {
        ArrayList<DestWisata> dw = dc.getDestWisata(kategori.getElementAt(jMain.JKategori.getSelectedIndex()).getID());
                
        tempatWisata.setRowCount(0);

        dw.forEach((item) -> {
            tempatWisata.addRow(new Object[]{item.getName(),item.getJmlKunjungan(),item.getKunjunganTerakhir()});
        });
    }
}
