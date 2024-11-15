/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lailiilhami
 */
   public class HalamanUtama extends javax.swing.JFrame {
    Connection conn;
    private DefaultTableModel modelformulir;
    private int totalPembayaran;
    private ArrayList<RiwayatPembayaran> riwayatList = new ArrayList<>();
    private javax.swing.JTextField tfPackageDate = new javax.swing.JTextField(); 
   

    public HalamanUtama() {
        if (!MainApp.isLoggedIn) { // Periksa apakah pengguna sudah login
        JOptionPane.showMessageDialog(null, "Anda harus login terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        new Login().setVisible(true); // Kembali ke halaman login
        this.dispose(); // Tutup HalamanUtama
        return; // Keluar dari konstruktor
    }
        
       conn = koneksi.getConnection();
       initComponents();
       initTableModels();
       loadData();
       initCustomComponents();  
       loadDataformulir();
    }
    private void initTableModels() {
        modelformulir = new DefaultTableModel(new String[] { "ID", "Nama", "alamat", "titik_keberangkatan","tujuan","jenis_paket","tanggal_keberangkatan","NO_Telp" }, 0);
        Jtpendaftaran.setModel(modelformulir);}
    
    
    private void loadDataformulir() {
            modelformulir.setRowCount(0);
            try {
                String sql = "SELECT t.Titik_keberangkatan, t.tujuan, t.Jenis_paket, t.ID, t.nama, t.alamat, t.tanggal_keberangkatan, t.No_Telp FROM formulir t";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(); 

                while (rs.next()) {
                    modelformulir.addRow(new Object[]{
                         rs.getInt("ID"),
                         rs.getString("nama"),
                         rs.getString("alamat"),
                         rs.getString("Titik_keberangkatan"),
                         rs.getString("tujuan"),
                         rs.getString("jenis_paket"),
                         rs.getString("tanggal_keberangkatan"),
                         rs.getString("No_Telp")
            });
                   
                }
            } catch (SQLException e) {
                System.out.println("Error Load Data Transaksi: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal memuat data transaksi: " + e.getMessage());
            }
        }
    
    
     private void initCustomComponents() {
    tfjumlah.addKeyListener(new KeyAdapter() { //untuk jumlah yang di pesan
        @Override
        public void keyReleased(KeyEvent e) {
            hitungTotalPembayaran(); 
        }
    });
    // Listener untuk memilih jenis paket
    cbjenispaket.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            hitungTotalPembayaran(); 
        }
    });
   tfjumlahuang.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                hitungKembalian(); 
            }
        });
}

    private void hitungTotalPembayaran() {
    try {
        int hargaPerPaket = getHargaPaket(cbjenispaket.getSelectedItem().toString());
        int jumlahDipesan = Integer.parseInt(tfjumlah.getText());
        totalPembayaran = hargaPerPaket * jumlahDipesan;
        tftotalbayar.setText(String.valueOf(totalPembayaran));
        System.out.println("Total Pembayaran: " + totalPembayaran);
        hitungKembalian();
    } catch (NumberFormatException e) {
        tftotalbayar.setText("0");
    }
    }
    
    private void hitungKembalian() {
    try {
        String uangDibayarText = tfjumlahuang.getText();
        if (uangDibayarText.isEmpty()) {
            tfkembalian.setText("0");
            return; // Jika kosong, tidak hitung kembalian
        }
        int uangDibayar = Integer.parseInt(uangDibayarText);
        System.out.println("Uang Dibayar: " + uangDibayar);
        int kembalian = uangDibayar - totalPembayaran;
        if (kembalian < 0) {
            tfkembalian.setText("Uang tidak cukup");
        } else {
            tfkembalian.setText(String.valueOf(kembalian));
        }
    } catch (NumberFormatException e) {
        tfkembalian.setText("0");
        System.out.println("Error: Invalid input for uang dibayar");
    }
    }

// Fungsi untuk mendapatkan harga paket berdasarkan jenis paket yang dipilih
    private int getHargaPaket(String jenisPaket) {
    int harga = 0;
    switch (jenisPaket) {
        case "Paket A":
            harga = 500000;  // Harga untuk Paket A
            break;
        case "Paket B":
            harga = 200000;  // Harga untuk Paket B
            break;
        case "Paket C":
            harga = 300000; // Harga untuk Paket C
            break;
        case "Paket D":
            harga = 400000; // Harga untuk Paket C
            break;
        case "Paket E":
            harga = 250000; // Harga untuk Paket C
            break;
        default:
            harga = 0; // Jika tidak ada paket yang sesuai
    }
    return harga;
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLNama = new javax.swing.JLabel();
        jLAlamat = new javax.swing.JLabel();
        jLnotelp = new javax.swing.JLabel();
        tfnama = new javax.swing.JTextField();
        tfalamat = new javax.swing.JTextField();
        tfnotelp = new javax.swing.JTextField();
        cbtitikkeberangkatan = new javax.swing.JComboBox<>();
        jLtitikkeberangkatan = new javax.swing.JLabel();
        jLtujuan = new javax.swing.JLabel();
        cbtujuan = new javax.swing.JComboBox<>();
        jLjenispaket = new javax.swing.JLabel();
        cbjenispaket = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        Jtpendaftaran = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        tfpaket = new javax.swing.JTextField();
        jLid = new javax.swing.JLabel();
        tfid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bedit = new javax.swing.JButton();
        bdelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLjumlah = new javax.swing.JLabel();
        tfjumlah = new javax.swing.JTextField();
        jLtotalbayar = new javax.swing.JLabel();
        tftotalbayar = new javax.swing.JTextField();
        jLjumlahuang = new javax.swing.JLabel();
        tfjumlahuang = new javax.swing.JTextField();
        jLkembalian = new javax.swing.JLabel();
        tfkembalian = new javax.swing.JTextField();
        btnkirim = new javax.swing.JButton();
        btnriwayat = new javax.swing.JButton();
        btnlogout = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(167, 217, 217));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLNama.setText("Nama:");
        jPanel4.add(jLNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jLAlamat.setText("Alamat");
        jPanel4.add(jLAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLnotelp.setText("No.telp");
        jPanel4.add(jLnotelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));
        jPanel4.add(tfnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 171, 30));
        jPanel4.add(tfalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 171, 30));
        jPanel4.add(tfnotelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 171, 30));

        cbtitikkeberangkatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Surabaya", "Kediri", "Banyuwangi", "Lumajang", "Madura", " " }));
        jPanel4.add(cbtitikkeberangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 161, 30));

        jLtitikkeberangkatan.setText("Titik Keberangkatan:");
        jPanel4.add(jLtitikkeberangkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jLtujuan.setText("Tujuan:");
        jPanel4.add(jLtujuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 50, -1));

        cbtujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BROMO", "KAWAH IJEN", "SENTERA THE LAPONTE", "PANTAI PAPUMA", "GILILABAK-SUMENEP", " ", " " }));
        jPanel4.add(cbtujuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(601, 60, 160, 30));

        jLjenispaket.setText("Jenis Paket:");
        jPanel4.add(jLjenispaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, -1, -1));

        cbjenispaket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paket A", "Paket B", "Paket C", "Paket D", "Paket E" }));
        cbjenispaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjenispaketActionPerformed(evt);
            }
        });
        jPanel4.add(cbjenispaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(601, 100, 160, 30));

        Jtpendaftaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NAMA", "ALAMAT", "NO.TELP", "TITIK.KEB", "TUJUAN", "JENIS PAKET"
            }
        ));
        Jtpendaftaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtpendaftaranMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Jtpendaftaran);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 850, 170));

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));
        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 369, 730, -1));
        jPanel4.add(tfpaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 140, 330, 30));

        jLid.setText("ID");
        jPanel4.add(jLid, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));
        jPanel4.add(tfid, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 12, 171, 30));

        jLabel3.setText("Detail paket");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, -1, -1));

        bedit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bedit.setText("EDIT");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        jPanel4.add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 410, 30));

        bdelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bdelete.setText("DELETE");
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });
        jPanel4.add(bdelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 410, 450, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("FORM PEMESANAN", jPanel2);

        jPanel3.setBackground(new java.awt.Color(167, 217, 217));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(189, 230, 233));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLjumlah.setText("JUMLAH:");
        jPanel6.add(jLjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 31, -1, -1));

        tfjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfjumlahActionPerformed(evt);
            }
        });
        jPanel6.add(tfjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 31, 223, -1));

        jLtotalbayar.setText("TOTAL BAYAR:");
        jPanel6.add(jLtotalbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 74, 100, -1));
        jPanel6.add(tftotalbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 65, 223, -1));

        jLjumlahuang.setText("JUMLAH UANG:");
        jPanel6.add(jLjumlahuang, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 116, -1, -1));
        jPanel6.add(tfjumlahuang, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 108, 223, -1));

        jLkembalian.setText("KEMBALIAN");
        jPanel6.add(jLkembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 159, -1, -1));
        jPanel6.add(tfkembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 150, 223, -1));

        btnkirim.setText("KIRIM");
        btnkirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkirimActionPerformed(evt);
            }
        });
        jPanel6.add(btnkirim, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 205, 157, -1));

        btnriwayat.setText("RIWAYAT PEMBAYARAN");
        btnriwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnriwayatActionPerformed(evt);
            }
        });
        jPanel6.add(btnriwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 205, 166, -1));

        btnlogout.setText("LOGOUT");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });
        jPanel6.add(btnlogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 246, 127, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\lailiilhami\\OneDrive\\Pictures\\muet.png")); // NOI18N
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 394, 353));

        jTabbedPane1.addTab("PEMBAYARAN", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 850, 490));

        jPanel1.setBackground(new java.awt.Color(239, 247, 248));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tour_Travel_Business_Logo-removebg-preview.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, -10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("TRAVEL EXPLORE JATIM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdeleteActionPerformed
       try {
       String sql = "DELETE FROM formulir WHERE id = ?";
       PreparedStatement ps = conn.prepareStatement(sql);
       ps.setInt(1, Integer.parseInt(tfid.getText())); 
       ps.executeUpdate();
       JOptionPane.showMessageDialog(this, "Data formulir berhasil dihapus");
       loadDataformulir(); 
     } catch (SQLException e) {
       System.out.println("Error Delete Data: " + e.getMessage());
     }    
    }//GEN-LAST:event_bdeleteActionPerformed

    private void tfjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfjumlahActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
      try {
        String sql = "UPDATE formulir SET nama = ?, alamat = ?, No_Telp = ?, Titik_keberangkatan = ?, tujuan = ?, Jenis_paket = ?, tanggal_keberangkatan = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tfnama.getText());
        ps.setString(2, tfalamat.getText());
        ps.setString(3, tfnotelp.getText());
        ps.setString(4, cbtitikkeberangkatan.getSelectedItem().toString());
        ps.setString(5, cbtujuan.getSelectedItem().toString());
        ps.setString(6, cbjenispaket.getSelectedItem().toString());
        ps.setString(7, tfPackageDate.getText()); // Tambahkan tanggal keberangkatan dari tfPackageDate
        ps.setInt(8, Integer.parseInt(tfid.getText())); // ID sebagai acuan untuk update
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data formulir berhasil diperbarui");
        loadDataformulir(); 
    } catch (SQLException e) {
        System.out.println("Error Update Data: " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Gagal memperbarui data: " + e.getMessage());
    }
    }//GEN-LAST:event_beditActionPerformed

    private void cbjenispaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjenispaketActionPerformed
    String packageDate = "";
    switch(cbjenispaket.getSelectedIndex()) {
        case 0:
            tfpaket.setText("Paket A bromo-jeep 19-november-2024  jam 8");
            packageDate = "2024-11-19";
            break;
        case 1:
            tfpaket.setText("Paket B kawahijen-bis tgl 20-November-2024 jam 7 pagi");
            packageDate = "2024-11-20";
            break;
        case 2:
            tfpaket.setText("Paket C sentralaponte-bis tgl 20-November-2024 jam 7 pagi");
            packageDate = "2024-11-20";
            break;
        case 3:
            tfpaket.setText("Paket D pantaipapuma-bis tgl 30-November-2024 jam 8 pagi");
            packageDate = "2024-11-30";
            break;
        case 4:
            tfpaket.setText("Paket E gililabak-bis tgl 18-November-2024 jam 7 pagi");
            packageDate = "2024-11-18";
            break;
            default:
            packageDate = ""; // Jika tidak ada paket yang dipilih
    }
    tfPackageDate.setText(packageDate); // Set tanggal keberangkatan di tfPackageDate
    System.out.println("Tanggal Keberangkatan Diset: " + packageDate); // Debugging
    }//GEN-LAST:event_cbjenispaketActionPerformed

    private void btnkirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkirimActionPerformed

        try {
        System.out.println("Tanggal Keberangkatan: " + tfPackageDate.getText());
        // Simpan data ke tabel formulir
        String sql = "INSERT INTO formulir (Titik_keberangkatan, tujuan, Jenis_paket, nama, alamat, tanggal_keberangkatan, No_Telp) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cbtitikkeberangkatan.getSelectedItem().toString());
        ps.setString(2, cbtujuan.getSelectedItem().toString());
        ps.setString(3, cbjenispaket.getSelectedItem().toString());
        ps.setString(4, tfnama.getText());
        ps.setString(5, tfalamat.getText());
        ps.setString(6, tfPackageDate.getText()); // Gunakan tanggal keberangkatan dari tfPackageDate
        ps.setString(7, tfnotelp.getText());
        ps.executeUpdate(); // Eksekusi query untuk menyimpan data
        JOptionPane.showMessageDialog(this, "Data formulir berhasil ditambah");

        // Proses pembayaran
        String uangDibayarText = tfjumlahuang.getText();
        if (uangDibayarText.isEmpty() || !isNumeric(uangDibayarText)) {
            JOptionPane.showMessageDialog(this, "Jumlah uang yang dibayar tidak valid!");
            return; 
        }
        int uangDibayar = Integer.parseInt(uangDibayarText);
        // Validasi untuk kembalian
        String kembalianText = tfkembalian.getText();
        if (kembalianText.isEmpty() || !isNumeric(kembalianText)) {
            JOptionPane.showMessageDialog(this, "Kembalian tidak valid!");
            return;  
        }
        int kembalian = Integer.parseInt(kembalianText);
        String jenisPaket = cbjenispaket.getSelectedItem().toString();
        String tanggalPembayaran = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // Tambahkan ke riwayat pembayaran
        RiwayatPembayaran pembayaran = new RiwayatPembayaran(uangDibayar, kembalian, jenisPaket, tanggalPembayaran);
        riwayatList.add(pembayaran); 
        JOptionPane.showMessageDialog(this, "Pembayaran berhasil disimpan!");
        loadDataformulir(); // Memuat ulang data setelah penambahan
    } catch (SQLException e) {
        System.out.println("Error Save Data: " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Gagal menambah data: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
    }
}

// Fungsi untuk memeriksa apakah input angka valid untuk jumlah uang dan kembalian
private boolean isNumeric(String str) {
    try {
        Integer.parseInt(str);  
        return true;  
    } catch (NumberFormatException e) {
        return false;  
    }
    }//GEN-LAST:event_btnkirimActionPerformed

public class RiwayatPembayaran {
    private int jumlahDibayar;
    private int kembalian;
    private String jenisPaket;
    private String tanggalPembayaran;
    @Override
    public String toString() {
        return "Jenis Paket: " + jenisPaket +
               "\nTanggal Pembayaran: " + tanggalPembayaran +
               "\nJumlah Dibayar: " + jumlahDibayar +
               "\nKembalian: " + kembalian;
    }

    // Konstruktor
    public RiwayatPembayaran(int jumlahDibayar, int kembalian, String jenisPaket, String tanggalPembayaran) {
        this.jumlahDibayar = jumlahDibayar;
        this.kembalian = kembalian;
        this.jenisPaket = jenisPaket;
        this.tanggalPembayaran = tanggalPembayaran;
    }
}

    private void btnriwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnriwayatActionPerformed
     if (riwayatList.isEmpty()) {
         JOptionPane.showMessageDialog(this, "Tidak ada riwayat pembayaran.");
     } else {
         // Ambil riwayat pembayaran terbaru (pembayaran terakhir)
         RiwayatPembayaran riwayat = riwayatList.get(riwayatList.size() - 1);
         JOptionPane.showMessageDialog(this, riwayat.toString(), "Riwayat Pembayaran", JOptionPane.INFORMATION_MESSAGE);
     }  
    }//GEN-LAST:event_btnriwayatActionPerformed

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
       Login LoginFrame = new Login();
       LoginFrame.setVisible(true);
       LoginFrame.pack();
       LoginFrame.setLocationRelativeTo(null); 
       this.dispose();
    }//GEN-LAST:event_btnlogoutActionPerformed
 
    private void JtpendaftaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtpendaftaranMouseClicked
      tfid.setText(Jtpendaftaran.getValueAt(Jtpendaftaran.getSelectedRow(), 0).toString());
      tfnama.setText(Jtpendaftaran.getValueAt(Jtpendaftaran.getSelectedRow(), 1).toString());
      tfalamat.setText(Jtpendaftaran.getValueAt(Jtpendaftaran.getSelectedRow(), 2).toString());
      tfnotelp.setText(Jtpendaftaran.getValueAt(Jtpendaftaran.getSelectedRow(), 2).toString());
      bedit.setEnabled(true);
      bdelete.setEnabled(true);
    }//GEN-LAST:event_JtpendaftaranMouseClicked

    
   


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
            java.util.logging.Logger.getLogger(HalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HalamanUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Jtpendaftaran;
    private javax.swing.JButton bdelete;
    private javax.swing.JButton bedit;
    private javax.swing.JButton btnkirim;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnriwayat;
    private javax.swing.JComboBox<String> cbjenispaket;
    private javax.swing.JComboBox<String> cbtitikkeberangkatan;
    private javax.swing.JComboBox<String> cbtujuan;
    private javax.swing.JLabel jLAlamat;
    private javax.swing.JLabel jLNama;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLid;
    private javax.swing.JLabel jLjenispaket;
    private javax.swing.JLabel jLjumlah;
    private javax.swing.JLabel jLjumlahuang;
    private javax.swing.JLabel jLkembalian;
    private javax.swing.JLabel jLnotelp;
    private javax.swing.JLabel jLtitikkeberangkatan;
    private javax.swing.JLabel jLtotalbayar;
    private javax.swing.JLabel jLtujuan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField tfalamat;
    private javax.swing.JTextField tfid;
    private javax.swing.JTextField tfjumlah;
    private javax.swing.JTextField tfjumlahuang;
    private javax.swing.JTextField tfkembalian;
    private javax.swing.JTextField tfnama;
    private javax.swing.JTextField tfnotelp;
    private javax.swing.JTextField tfpaket;
    private javax.swing.JTextField tftotalbayar;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

//    private void initTableModels() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    private void loadData() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

//    private void loadDataformulir() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
}