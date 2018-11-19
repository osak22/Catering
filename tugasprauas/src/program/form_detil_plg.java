package program;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi_db;

/**
 *
 * @author yosyosaa
 */
public final class form_detil_plg extends javax.swing.JFrame {
    private final Koneksi_db kon = new Koneksi_db();
    private final DefaultTableModel model;

    /**
     * Creates new form form_detil_plg
     */
    public form_detil_plg() {
        initComponents();
        tampil_combo();
            model = new DefaultTableModel();
            tbl_pem_detil.setModel(model);
            model.addColumn("Kode Makanan");
            model.addColumn("Nama Makanan");
            model.addColumn("Banyak");
            model.addColumn("Harga");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public void tampil_combo(){
        try {
        String sql = "SELECT kd_plg FROM tb_pelanggan ORDER BY kd_plg ASC";      // disini saya menampilkan NIM, anda dapat menampilkan
        kon.res = kon.stat.executeQuery(sql); // yang anda ingin kan
        
        while(kon.res.next()){
//            Object[] obj = new Object[1];
//            obj[0] = kon.res.getString(1);
            cbb_kode_plg.addItem(kon.res.getString("kd_plg"));                   // fungsi ini bertugas menampung isi dari database
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void tampil()
    {
        try {
        String sql = "SELECT `nm_plg`,`telp`,`alamat` FROM `tb_pelanggan` WHERE `kd_plg` = '"+cbb_kode_plg.getSelectedItem()+"'";  
        kon.res = kon.stat.executeQuery(sql); // yang anda ingin kan

        while(kon.res.next()){
            Object[] obj = new Object[3];
            obj[0]=  kon.res.getString(1);
            obj[1]= kon.res.getString(2);
            obj[2]= kon.res.getString(3);
            
            lbl_nama.setText((String) obj[0]);
            lbl_telp.setText((String) obj[1]);
            lbl_alamat.setText((String) obj[2]);
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }              
    }
    
    public void getData() throws SQLException{
     try{
//     Statement stat = (Statement) Koneksi_db.getKoneksi();
        String SQL = "SELECT `tb_pembelian_detil`.`kd_mkn`, `tb_makanan`.`nm_mkn`,`tb_pembelian_detil`.`banyak`,\n" +
                        "(`tb_makanan`.`harga_jual` * `tb_pembelian_detil`.`banyak`) AS harga\n" +
                        "FROM `tb_makanan` ,`tb_pembelian_detil`\n" +
                        "WHERE `tb_makanan`.`kd_mkn` = `tb_pembelian_detil`.`kd_mkn` \n" +
                        "AND `tb_pembelian_detil`.`kd_plg` = '"+cbb_kode_plg.getSelectedItem()+"'";  
//        ResultSet res = kon.executeQuery(SQL);
          kon.res = kon.stat.executeQuery(SQL);
         
            while(kon.res.next()) {
                Object[] obj = new Object[4];
                obj[0] = kon.res.getString("kd_mkn"); 
                obj[1] = kon.res.getString("nm_mkn");
                obj[2] = kon.res.getInt("banyak"); 
                obj[3] = kon.res.getInt("harga"); 

                model.addRow(obj);
            }
            } catch (SQLException ex) {
//                Logger.getLogger(form_makanan.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ada Kesalahan"+ex);
            }
    }   
    
    public void reset(){
        lbl_nama.setText("");
        lbl_telp.setText("");
        lbl_alamat.setText("");
        tbl_pem_detil.removeColumnSelectionInterval(0, 3);
        cbb_kode_plg.setSelectedIndex(-1);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_pem_detil = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_telp = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_alamat = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbb_kode_plg = new javax.swing.JComboBox<>();
        btn_reset = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_pem_detil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_pem_detil);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("PEMBELIAN DETAIL");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Nama                :");

        lbl_nama.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_nama.setText("-");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Telphone            :");

        lbl_telp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_telp.setText("-");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Alamat               :");

        lbl_alamat.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_alamat.setText("-");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Kode Pelanggan   :");

        cbb_kode_plg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilihan-" }));
        cbb_kode_plg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbb_kode_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_kode_plgActionPerformed(evt);
            }
        });

        btn_reset.setText("RESET");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        jMenu1.setText("Menu");

        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(35, 35, 35)
                            .addComponent(cbb_kode_plg, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                .addComponent(lbl_telp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_alamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_reset)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbb_kode_plg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_nama))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_telp))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbl_alamat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_reset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_kode_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_kode_plgActionPerformed
        try {
            // TODO add your handling code here:
            model.setRowCount(0);
            tampil_combo();
            tampil();
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(form_detil_plg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbb_kode_plgActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbb_kode_plg.getModel();
        model.removeAllElements();
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        form_home f_home = new form_home();
        f_home.show();
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(form_detil_plg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_detil_plg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_detil_plg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_detil_plg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_detil_plg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reset;
    private javax.swing.JComboBox<String> cbb_kode_plg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_alamat;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_telp;
    private javax.swing.JTable tbl_pem_detil;
    // End of variables declaration//GEN-END:variables
}
