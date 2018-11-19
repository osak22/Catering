/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi_db;

/**
 *
 * @author yosyosaa
 */
public class form_makanan extends javax.swing.JFrame {
    //membuat variable model dengan untuk DefaultTableModel
    private final DefaultTableModel model;
    private final Koneksi_db kon = new Koneksi_db();
    String kd_mkn, nm_mkn, kd_kategori;
    int harga_jual;



    public form_makanan() {
        initComponents();
        //memberi penamaan pada judul kolom tblGaji;
        model = new DefaultTableModel();
        tbl_mkn.setModel(model);
        model.addColumn("Kode Kategori");
        model.addColumn("Nama Kategori");
        model.addColumn("Kode Makanan");
        model.addColumn("Nama Makanan");
        model.addColumn("Harga Satuan");
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    
    public void getData() throws SQLException{
     model.getDataVector().removeAllElements();
     model.fireTableDataChanged();
     try{
//     Statement stat = (Statement) Koneksi_db.getKoneksi();
        String SQL = "SELECT `tb_kategorimakanan`.`kd_kategori` , `tb_kategorimakanan`.`nama_kategori` , `tb_makanan`.`kd_mkn` , `tb_makanan`.`nm_mkn` , `tb_makanan`.`harga_jual`\n" +
                            "FROM `tb_kategorimakanan` , `tb_makanan`\n" +
                            "WHERE `tb_kategorimakanan`.`kd_kategori` = `tb_makanan`.`kd_kategori`;";  
//        ResultSet res = kon.executeQuery(SQL);
          kon.res = kon.stat.executeQuery(SQL);


            while(kon.res.next()) {
                Object[] obj = new Object[5];
                obj[0] = kon.res.getString("kd_kategori"); 
                obj[1] = kon.res.getString("nama_kategori");
                obj[2] = kon.res.getString("kd_mkn"); 
                obj[3] = kon.res.getString("nm_mkn");
                obj[4] = kon.res.getInt("harga_jual"); 

                model.addRow(obj);
                
            }
            } catch (SQLException ex) {
//                Logger.getLogger(form_makanan.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ada Kesalahan");
            }
    }
    
    public void loadData(){
        // memberi inisialiasai pada variabel di gui
        kd_mkn = txt_kode_mkn.getText();
        nm_mkn = txt_nama_mkn.getText();
        kd_kategori = (String) comboBox_kode.getSelectedItem();
        harga_jual = Integer.parseInt(txt_harga.getText());
    }
    
//    public void loadKodeKategori(){
//        kd_kategori = "" + comboBox_kode.getSelectedItem();
//        switch(kd_kategori){
//            case "K01":
//                
//        }
//    }
    public void saveData(){
        loadData();
        try {
            // query simpan
            String sql = "INSERT INTO tb_makanan (kd_mkn, nm_mkn, kd_kategori, harga_jual) VALUE('%s', '%s','%s',%d)";
            sql = String.format(sql, kd_mkn, nm_mkn, kd_kategori, harga_jual);

            // simpan makanan
            kon.stat.execute(sql);
            JOptionPane.showMessageDialog(null, "MAKANAN BERHASIL DITAMBAHKAN");
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Simpan = "+e);
        }
    }
    
    public void reset(){
        txt_kode_mkn.setText("");
        txt_nama_mkn.setText("");
        txt_harga.setText("");
        comboBox_kode.setSelectedIndex(0);
    }
    
    public void dataSelect(){
        int i = tbl_mkn.getSelectedRow();
        if(i == -1)
            return;
        else{
            comboBox_kode.setSelectedItem(""+model.getValueAt(i, 1));
            txt_kode_mkn.setText(""+model.getValueAt(i, 2));
            txt_nama_mkn.setText(""+model.getValueAt(i, 3));
            txt_harga.setText(""+model.getValueAt(i, 4));
        }
    }
    
    public void updateData(){
        loadData();
        try {
        // query update
        String sql = "UPDATE tb_makanan SET nm_mkn='%s', kd_kategori='%s', harga_jual=%d WHERE kd_mkn='%s'";
        sql = String.format(sql, nm_mkn, kd_kategori, harga_jual, kd_mkn);

        // update data buku
        kon.stat.execute(sql);
        JOptionPane.showMessageDialog(null, "MAKANAN BERHASIL DIRUBAH");
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Rubah Data = "+e);
        }
    }
    
    public void deleteData(){
        loadData();
        try {
            int pesan = JOptionPane.showConfirmDialog(null, "Yakin Ingin Meghapus Data "+ kd_mkn +" ?","Konfirmasi",
            JOptionPane.OK_CANCEL_OPTION);
            if(pesan == JOptionPane.OK_OPTION){
                // buat query hapus
                String sql = String.format("DELETE FROM tb_makanan WHERE kd_mkn='%s'", kd_mkn);
                // hapus data
                reset();
                kon.stat.execute(sql);
                JOptionPane.showMessageDialog(null, "MAKANAN BERHASIL DIHAPUS");
            }   
        } catch (HeadlessException | SQLException e) {
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        jLabel1 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_mkn = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_kode_mkn = new javax.swing.JTextField();
        txt_nama_mkn = new javax.swing.JTextField();
        comboBox_kode = new javax.swing.JComboBox<>();
        txt_harga = new javax.swing.JTextField();
        btn_tampil = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("MAKANAN");

        btn_tambah.setText("TAMBAH");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_edit.setText("EDIT");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        tbl_mkn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tbl_mkn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_mkn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_mknMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_mkn);

        jButton1.setText("RESET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Kode Makanan: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Nama Makanan: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Kode Kategori: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Harga Satuan: ");

        comboBox_kode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "K01", "K02", "K03" }));
        comboBox_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_kodeActionPerformed(evt);
            }
        });

        btn_tampil.setText("TAMPIL");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "K01 : Roti", "K02 : Snack", "K03 : Jajanan Pasar" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jMenu1.setText("Menu");

        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Pelanggan");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_kode_mkn)
                            .addComponent(txt_nama_mkn)
                            .addComponent(txt_harga)
                            .addComponent(comboBox_kode, 0, 207, Short.MAX_VALUE))
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tampil, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
            .addGroup(layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_kode_mkn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_nama_mkn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(comboBox_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_edit)
                    .addComponent(btn_hapus)
                    .addComponent(jButton1)
                    .addComponent(btn_tampil))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        saveData();
        try {
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(form_makanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        try {
             updateData();
             getData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Klik Data Dibawah");
        }
        
    }//GEN-LAST:event_btn_editActionPerformed

    private void tbl_mknMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_mknMouseClicked
        // TODO add your handling code here:
        dataSelect();
    }//GEN-LAST:event_tbl_mknMouseClicked

    private void comboBox_kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox_kodeActionPerformed

    private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
        try {
            // TODO add your handling code here:
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(form_makanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_tampilActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        deleteData();
        try {
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(form_makanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        form_home f_home = new form_home();
        f_home.show();
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        form_pelanggan f_plg = new form_pelanggan();
        f_plg.show();
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_makanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JComboBox<String> comboBox_kode;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JTable tbl_mkn;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_kode_mkn;
    private javax.swing.JTextField txt_nama_mkn;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel DefaultTableModel(Object object, String[] catering) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
