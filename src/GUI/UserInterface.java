/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Functions.Config;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author hosam
 */
public class UserInterface extends javax.swing.JFrame {
    
    //******************** Declaration of Variables, to be used for the Entire Class ********************//
    private int currentLeagueID = 0 ;
    Object[][] _leaguesList ;
    public String currentLeague_Name;
    //*****************************************************************//
   
            //******************** League Table Renderer ********************//
        // Necessary For alligning Rows
        DefaultTableCellRenderer tblLeagueRenderer = new DefaultTableCellRenderer();
   
    /**
     * Creates new form NewJFrame
     */
    public UserInterface() {
        super.setTitle("Home");
        initComponents();
        this.setLocationRelativeTo(null);
        // Calling the rows center aligning function ...
        // Aligning Rows to the center ...
        setTableCellAlignment(SwingConstants.CENTER);
        
        playersTable.setVisible(false);
        //****************init*****************
        jScrollPane_tableLeague.setBackground(new java.awt.Color(18,30,49));
        playersTable.setBackground(new java.awt.Color(18,30,49)); 
        //************************************
        try{
          _leaguesList= getLeagues();
          updateTeamsTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            _leaguesList = new Object[][]{};
        }
        for (int i = 0 ;_leaguesList[i][1] != null ; i++){
               jComboBox1.addItem(_leaguesList[i][1].toString());
        }
        
        //******************** League Table Properties ********************//
        jTableLeague.getTableHeader().setFont(new Font("League", Font.BOLD,24));
        //jTableLeague.setOpaque(false);
        
        // Setting Colmuns Width
        jTableLeague.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTableLeague.getColumnModel().getColumn(1).setPreferredWidth(10);
        jTableLeague.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTableLeague.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        jTableLeague.getTableHeader().setBackground(new Color(63, 16, 82));
        jTableLeague.getTableHeader().setForeground(new Color(108,147,59));
        // Changing the League Table Color
        jScrollPane_tableLeague.getViewport().setBackground(new Color(108, 147, 59));
        //*****************************************************************//
        
    }
    private Object[][] getLeagues() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from league");  
            // ResultSet rs=stmt.executeUpdate("insert into league ...");  
            Object[][] leagueList = new Object[1000][1000];
            int index = 0 ;
            while(rs.next()) { 
                leagueList[index][0] = rs.getInt("ID");
                leagueList[index][1] = rs.getString("LEAGUENAME");
                index ++ ;
             }
            con.close(); 
            return leagueList;
        }catch(Exception e){ 
                System.out.println(e);
                throw e;
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

        jScrollPane_tableLeague = new javax.swing.JScrollPane();
        jTableLeague = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer r, int rw, int col)
            {
                Component c = super.prepareRenderer(r, rw, col);
                c.setBackground(new Color(87, 125, 41));
                c.setFont(new Font("League", Font.BOLD,18));
                // Setting Alternating Colors
                if(rw %2 == 0)
                c.setBackground(new Color(150, 150, 150));
                return c;
            }

        }

        ;
        jComboBox1 = new javax.swing.JComboBox<>();
        playersTable = new javax.swing.JScrollPane();
        playerTable = new javax.swing.JTable();
        showPlayers = new javax.swing.JButton();
        jButton_SignIn = new javax.swing.JButton();
        jLabel_TeamsClose = new javax.swing.JLabel();
        jLabel_leagueName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1440, 768));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1440, 768));
        getContentPane().setLayout(null);

        jScrollPane_tableLeague.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(108, 147, 59), new java.awt.Color(108, 147, 59), new java.awt.Color(108, 147, 59), new java.awt.Color(108, 147, 59)));

        jTableLeague.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Team", "Coach", "City", "Stadium"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLeague.setFocusable(false);
        jTableLeague.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableLeague.setRowHeight(30);
        jTableLeague.setRowSelectionAllowed(true);
        jTableLeague.setSelectionForeground(new java.awt.Color(0, 120, 215));
        jTableLeague.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableLeague.setShowVerticalLines(false);
        jTableLeague.getTableHeader().setReorderingAllowed(false);
        jScrollPane_tableLeague.setViewportView(jTableLeague);

        getContentPane().add(jScrollPane_tableLeague);
        jScrollPane_tableLeague.setBounds(30, 100, 810, 480);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(930, 160, 210, 30);

        playerTable.setModel(new javax.swing.table.DefaultTableModel(
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
        playersTable.setViewportView(playerTable);

        getContentPane().add(playersTable);
        playersTable.setBounds(890, 240, 410, 260);

        showPlayers.setBackground(new java.awt.Color(51, 85, 175));
        showPlayers.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        showPlayers.setForeground(new java.awt.Color(204, 204, 204));
        showPlayers.setText("View Team");
        showPlayers.setBorder(null);
        showPlayers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showPlayers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPlayersActionPerformed(evt);
            }
        });
        getContentPane().add(showPlayers);
        showPlayers.setBounds(1010, 600, 210, 50);
        jButton_SignIn.setFocusPainted(false);

        jButton_SignIn.setBackground(new java.awt.Color(51, 85, 175));
        jButton_SignIn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton_SignIn.setForeground(new java.awt.Color(204, 204, 204));
        jButton_SignIn.setText("Latest News");
        jButton_SignIn.setBorder(null);
        jButton_SignIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_SignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SignInActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_SignIn);
        jButton_SignIn.setBounds(1000, 30, 210, 50);
        jButton_SignIn.setFocusPainted(false);

        jLabel_TeamsClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_TeamsClose.setForeground(new java.awt.Color(153, 153, 153));
        jLabel_TeamsClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TeamsClose.setText("X");
        jLabel_TeamsClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_TeamsClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_TeamsCloseMouseClicked(evt);
            }
        });
        getContentPane().add(jLabel_TeamsClose);
        jLabel_TeamsClose.setBounds(1390, 10, 25, 29);

        jLabel_leagueName.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel_leagueName.setForeground(new java.awt.Color(108, 147, 59));
        jLabel_leagueName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_leagueName.setText("League");
        getContentPane().add(jLabel_leagueName);
        jLabel_leagueName.setBounds(30, 30, 730, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Soccer Stadum.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1450, 768);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1420, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1420, 770);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_TeamsCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TeamsCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel_TeamsCloseMouseClicked

    private void jButton_SignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SignInActionPerformed
          try{
              String myUrl = "https://www.besoccer.com/news";
              java.awt.Desktop.getDesktop().browse(java.net.URI.create(myUrl));
          }
          catch(Exception e){
              e.printStackTrace();
          }
    }//GEN-LAST:event_jButton_SignInActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int idIndex = jComboBox1.getSelectedIndex();
        currentLeagueID = Integer.parseInt(_leaguesList[idIndex][0].toString()) ;
        
        try{
            // Changing the league label name corresponding to the selected league
            // Updating the League Table ...
            currentLeague_Name = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());
            jLabel_leagueName.setText(currentLeague_Name);
            updateTeamsTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void showPlayersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPlayersActionPerformed
        playersTable.setVisible(true);
    }//GEN-LAST:event_showPlayersActionPerformed

    /** ******************** League Table Renderer Function ********************
     * This function is for configuring the Table League Renderer which is
     * Responsible for pairing each column to all its cells and then
     * aligning each cell of each row to the center of its corresponding column
     * Which will result that rows of the table is aligned in the center ...
     * https://coderanch.com/t/680374/java/Center-Alignment-JTABLE-Records
     */
    
         private void setTableCellAlignment(int alignment) {
             tblLeagueRenderer.setHorizontalAlignment(alignment);
             for (int i=0; i<jTableLeague.getColumnCount();i++){
                jTableLeague.setDefaultRenderer(jTableLeague.getColumnClass(i),tblLeagueRenderer);
                }
            // repaint to show table cell changes
            jTableLeague.updateUI();
        }
    
    
    
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
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }
        
        
    public void updateTeamsTable() throws Exception{
       DefaultTableModel tblLeagueModel = (DefaultTableModel)jTableLeague.getModel();
        Object[][] _testData ;
        try{
           _testData = getTeams();
        }catch(Exception e){
          throw e ;
        }     
        // Fixed Bug (Duplicate Table Data Showing)
        for (int i = tblLeagueModel.getRowCount() - 1 ;i >= 0 ; i-- ){
            tblLeagueModel.removeRow(i);
        }
        
        for(int i=0; _testData[i][0] != null; i++)
            tblLeagueModel.addRow(_testData[i]);
   }
   
   private Object[][] getTeams() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from team where leagueid =" +currentLeagueID);  
            Object[][] teamsList = new Object[1000][1000];
            int index = 0 ;
            while(rs.next()) { 
                teamsList[index][0] = rs.getString("name");
                teamsList[index][1] = rs.getInt("foundedyear");
                index ++ ;
             }
            con.close(); 
            return teamsList;
        }catch(SQLException e){ 
                System.out.println(e);
                throw e;
        }  
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_SignIn;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_TeamsClose;
    private javax.swing.JLabel jLabel_leagueName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane_tableLeague;
    private javax.swing.JTable jTableLeague;
    private javax.swing.JTable playerTable;
    private javax.swing.JScrollPane playersTable;
    private javax.swing.JButton showPlayers;
    // End of variables declaration//GEN-END:variables
}
