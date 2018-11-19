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
public class form_pelanggan extends javax.swing.JFrame {
        private DefaultTableModel model;
        private Koneksi_db kon = new Koneksi_db();
        String kd_plg, kd_mkn, nm_plg, nm_mkn, telp, alamat, tanggal_kirim;
        int banyak;

        
    public form_pelanggan() {
        initComponents();
        model = new DefaultTableModel();
        tbl_plg.setModel(model);
        model.addColumn("NAMA PELANGGAN");
        model.addColumn("NAMA MAKANAN");
        model.addColumn("BANYAK MAKANAN");
        model.addColumn("HARGA MAKANAN");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void getData(){
        try {
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
//        Statement stat = (Statement) Koneksi_db.getKoneksi();
        String SQL = "SELECT `tb_pelanggan`.`nm_plg` , `tb_makanan`.`nm_mkn` , `tb_pembelian_detil`.`banyak` ,(`tb_makanan`.`harga_jual` * `tb_pembelian_detil`.`banyak`) AS harga\n" +
                            "FROM `tb_pelanggan`, `tb_makanan`, `tb_pembelian_detil`\n" +
                            "WHERE `tb_pelanggan`.`kd_plg` = `tb_pembelian_detil`.`kd_plg` AND `tb_makanan`.`kd_mkn` = `tb_pembelian_detil`.`kd_mkn`";
//        ResultSet res = kon.executeQuery(SQL);
        kon.res = kon.stat.executeQuery(SQL);
        model = new DefaultTableModel();
        tbl_plg.setModel(model);
        model.addColumn("Nama Pelanggan");
        model.addColumn("Nama Makanan");
        model.addColumn("Banyak Makanan");
        model.addColumn("Harga Makanan");
        
        while(kon.res.next()) {
            Object[] obj = new Object[4];
            obj[0] = kon.res.getString("nm_plg"); 
            obj[1] = kon.res.getString("nm_mkn");
            obj[2] = kon.res.getInt("banyak"); 
            obj[3] = kon.res.getInt("harga"); 
            model.addRow(obj);

        }
        } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, "Ada Kesalahan");
        }
    }
    
    public void loadData(){
        kd_plg = txt_kode_plg.getText();
        nm_plg = txt_nama_plg.getText();
        kd_mkn = txt_kode_mkn.getText();
        telp = txt_tlf.getText();
        alamat = txt_alamat.getText();
        kd_mkn = txt_kode_mkn.getText();
        banyak = Integer.parseInt(txt_jumlah.getText());
        tanggal_kirim = txt_tanggal.getText();
    }
    
    public void saveDataPlg(){
        loadData();
        try {
            // query simpan tambel plg
            String sql = "INSERT INTO tb_pelanggan (kd_plg, nm_plg, telp, alamat) VALUE('%s','%s','%s','%s')";
            sql = String.format(sql, kd_plg, nm_plg, telp, alamat);
            
            // plg detil                       
            String sql2 = "INSERT INTO tb_pembelian_detil (kd_plg, kd_mkn, banyak) VALUE('%s','%s',%d)";
            sql2 = String.format(sql, kd_plg, kd_mkn, banyak);
            

            // simpan makanan
            kon.stat.execute(sql);
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Simpan = "+e);
        }
    }
    public void saveDataDetilPlg(){
        loadData();
        try {          
            // plg detil                       
            String sql = "INSERT INTO tb_pembelian_detil (kd_plg, kd_mkn, banyak, tanggal_kirim) VALUE('%s','%s',%d,'%s')";
            sql = String.format(sql, kd_plg, kd_mkn, banyak, tanggal_kirim);
            

            // simpan makanan
            kon.stat.execute(sql);
            JOptionPane.showMessageDialog(null, "PESANAN BERHASIL DITAMBAHKAN");
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Simpan = "+e);
        }
    }
    
    public void reset(){
        txt_kode_mkn.setText("");
        txt_nama_plg.setText("");
        txt_alamat.setText("");
        txt_jumlah.setText("");
        txt_kode_plg.setText("");
        txt_tanggal.setText("0000/00/00");
        txt_tlf.setText("");       
    }
    
    public void deleteData(){
        loadData();
        try {
            int pesan = JOptionPane.showConfirmDialog(null, "Yakin Ingin Meghapus Data "+ kd_plg +" : ( "+nm_plg+" )?","Konfirmasi",
            JOptionPane.OK_CANCEL_OPTION);
            if(pesan == JOptionPane.OK_OPTION){
                // buat query hapus
                String sql = String.format("DELETE FROM tb_Pelanggan WHERE kd_plg='%s'", kd_plg);
                // hapus data
                reset();
                kon.stat.execute(sql);
                JOptionPane.showMessageDialog(null, "PELANGGAN BERHASIL DIHAPUS");
            }   
        } catch (HeadlessException | SQLException e) {
        }
    }
    
    public void getDataMkn() throws SQLException{
     model.getDataVector().removeAllElements();
     model.fireTableDataChanged();
//     Statement stat = (Statement) Koneksi_db.getKoneksi();
        String SQL = "SELECT `tb_kategorimakanan`.`kd_kategori` , `tb_kategorimakanan`.`nama_kategori` , `tb_makanan`.`kd_mkn` , `tb_makanan`.`nm_mkn` , `tb_makanan`.`harga_jual`\n" +
                            "FROM `tb_kategorimakanan` , `tb_makanan`\n" +
                            "WHERE `tb_kategorimakanan`.`kd_kategori` = `tb_makanan`.`kd_kategori`;";  
//        ResultSet res = kon.executeQuery(SQL);
          kon.res = kon.stat.executeQuery(SQL);
          
        model = new DefaultTableModel();
        tbl_plg.setModel(model);
        model.addColumn("Kode Kategori");
        model.addColumn("Nama Kategori");
        model.addColumn("Kode Makanan");
        model.addColumn("Nama Makanan");
        model.addColumn("Harga Satuan");
        
        try {
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_kode_plg = new javax.swing.JTextField();
        txt_nama_plg = new javax.swing.JTextField();
        txt_tlf = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        btn_tampil_mkn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_kode_mkn = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        btn_reset = new javax.swing.JButton();
        btn_tampil_plg = new javax.swing.JButton();
        btn_tambah_plg = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_plg = new javax.swing.JTable();
        btn_hapus = new javax.swing.JButton();
        btn_detil_plg = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("PELANGGAN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Kode Pelanggan  : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Nama Pelanggan : ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Telfon                :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Alamat              : ");

        txt_kode_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kode_plgActionPerformed(evt);
            }
        });

        txt_tlf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tlfActionPerformed(evt);
            }
        });

        btn_tampil_mkn.setText("TAMPIL MAKANAN");
        btn_tampil_mkn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampil_mknActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Kode Makanan   :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Jumlah               :");

        txt_kode_mkn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kode_mknActionPerformed(evt);
            }
        });

        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });

        btn_reset.setText("RESET");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        btn_tampil_plg.setText("TAMPIL PELANGGAN");
        btn_tampil_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampil_plgActionPerformed(evt);
            }
        });

        btn_tambah_plg.setText("TAMBAH PELANGGAN");
        btn_tambah_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_plgActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Tanggal Kirim     :");

        txt_tanggal.setText("0000/00/00");
        txt_tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tanggalActionPerformed(evt);
            }
        });

        tbl_plg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_plg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_plgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_plg);

        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_detil_plg.setText("DETIL PELANGGAN");
        btn_detil_plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detil_plgActionPerformed(evt);
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

        jMenuItem2.setText("Makanan");
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
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_tlf)
                                    .addComponent(txt_alamat)
                                    .addComponent(txt_nama_plg, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txt_kode_plg)))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_kode_mkn, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_tambah_plg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_tampil_plg, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_tampil_mkn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_detil_plg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_kode_plg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_nama_plg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_kode_mkn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah_plg)
                    .addComponent(btn_reset)
                    .addComponent(btn_tampil_mkn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tampil_plg)
                    .addComponent(btn_hapus)
                    .addComponent(btn_detil_plg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void btn_tampil_mknActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampil_mknActionPerformed
            try {
                // TODO add your handling code here:
                getDataMkn();
            } catch (SQLException ex) {
                Logger.getLogger(form_pelanggan.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btn_tampil_mknActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_tambah_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_plgActionPerformed
        // TODO add your handling code here:
        try {
            saveDataPlg();
            saveDataDetilPlg();
            getData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Inputkan Terlebih Dahulu");
        }
    }//GEN-LAST:event_btn_tambah_plgActionPerformed

    private void tbl_plgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_plgMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_plgMouseClicked

    private void txt_kode_mknActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kode_mknActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kode_mknActionPerformed

    private void txt_tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tanggalActionPerformed

    private void btn_tampil_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampil_plgActionPerformed
        // TODO add your handling code here:
        getData();
    }//GEN-LAST:event_btn_tampil_plgActionPerformed

    private void txt_kode_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kode_plgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kode_plgActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        deleteData();
        getData();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_detil_plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detil_plgActionPerformed
        // TODO add your handling code here:
        form_detil_plg f_detil = new form_detil_plg();
        f_detil.show();
    }//GEN-LAST:event_btn_detil_plgActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        form_home f_home = new form_home();
        f_home.show();
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        form_makanan f_mkn = new form_makanan();
        f_mkn.show();
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txt_tlfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tlfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tlfActionPerformed

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
            java.util.logging.Logger.getLogger(form_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_detil_plg;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_tambah_plg;
    private javax.swing.JButton btn_tampil_mkn;
    private javax.swing.JButton btn_tampil_plg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_plg;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kode_mkn;
    private javax.swing.JTextField txt_kode_plg;
    private javax.swing.JTextField txt_nama_plg;
    private javax.swing.JTextField txt_tanggal;
    private javax.swing.JTextField txt_tlf;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel DefaultTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
