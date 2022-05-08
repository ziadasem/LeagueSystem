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
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author hosam
 * ziad assem
 */
public class Home extends javax.swing.JFrame {
    
   private int currentLeagueID = 0 ;
   Object[][] _leaguesList ;
    
          //******************** League Table Renderer ********************//
        // Necessary For alligning Rows
        DefaultTableCellRenderer tblLeagueRenderer = new DefaultTableCellRenderer();
    /**
     * Creates new form Home
     */
    public Home() {
        super.setTitle("Home");
        
        
        initComponents();
        this.setLocationRelativeTo(null);
        // Calling the rows center aligning function ...
        // Aligning Rows to the center ...
        setTableCellAlignment(SwingConstants.CENTER);
        //******************** League Table Model ********************//
        // Necessary For Adding Rows
        // Method 1 For Adding
     

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
       /* Object testData[] = {1, "Liverpool", "5", "5", "0", "0", "15", "W - W - W"};
        tblLeagueModel.addRow(testData);
        Object testData2[] = {2, "Manchester City", "5", "4", "1", "0", "13", "W - W - W"};
        tblLeagueModel.addRow(testData2);
        // Method 2 For Adding
        Object[][] _testData = new Object[][]
        {
            {"3", "Chelsea", "5", "3", "2", "0", "11", "W - D - W" },
            {"4", "Manchester United", "5", "3", "1", "1", "10", "W - W - D" },
            {"5", "Tottenham", "5", "2", "3", "0", "9", "D - D - W" },
            {"6", "Arsenal", "5", "3", "0", "2", "9", "L - W - W" },
            {"7", "West Ham United", "5", "2", "0", "3", "6", "W - W - L" },
            {"8", "Wolves", "5", "2", "0", "3", "6", "L - L - W" },
            {"9", "Leicester City", "5", "1", "1", "3", "4", "L - D - L" },
            {"10", "Everton", "5", "1", "0", "4", "3", "L - L - L" },
            {"11", "Crystal Palace", "5", "0", "1", "4", "1", "D - L - L" },
            {"12", "Al Zamalek", "5", "0", "0", "5", "0", "L - L - L" },
        };*/
      
        //*****************************************************************//
        
        //******************** League Table Properties ********************//
        jTableLeague.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableLeague.setOpaque(false);
        jTableLeague.getTableHeader().setBackground(new Color(63, 16, 82));
        jTableLeague.getTableHeader().setForeground(new Color(255,255,255));
        jTableLeague.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLeague = new javax.swing.JTable(){

            public Component prepareRenderer(TableCellRenderer r, int rw, int col)
            {
                Component c = super.prepareRenderer(r, rw, col);
                c.setBackground(Color.WHITE);
                c.setFont(new Font("League", Font.BOLD,18));
                // Setting Alternating Colors
                if(rw %2 == 0)
                c.setBackground(new Color(225, 225, 225));
                // Setting Colors Of First 3 Champions Qualified Teams
                if((rw == 0 && col == 0) || (rw == 1 && col == 0) || (rw == 2 && col == 0))
                c.setBackground(new Color(66, 133, 244));

                // Setting Colors Of Second 2 2-Champions Qualified Teams
                if((rw == 3 && col == 0) || (rw == 4 && col == 0))
                c.setBackground(new Color(251, 150, 68));

                // Setting color of last 3 (Descending Teams) ...
                if((rw == jTableLeague.getRowCount() - 1 && col == 0) || (rw == jTableLeague.getRowCount() - 2 && col == 0) || (rw == jTableLeague.getRowCount() - 3 && col == 0))
                c.setBackground(new Color(243, 64, 54));
                return c;
            }
        };
        jLabel_leagueName = new javax.swing.JLabel();
        jButton_latestNews = new javax.swing.JButton();
        jButton_latestNews1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1440, 996));
        setName("mainframe"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(1024, 720));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1024, 720));

        jTableLeague.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "name", "year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLeague.setFocusable(false);
        jTableLeague.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableLeague.setMinimumSize(new java.awt.Dimension(1024, 720));
        jTableLeague.setPreferredSize(new java.awt.Dimension(1024, 720));
        jTableLeague.setRowHeight(30);
        jTableLeague.setShowVerticalLines(false);
        jTableLeague.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableLeague);
        if (jTableLeague.getColumnModel().getColumnCount() > 0) {
            jTableLeague.getColumnModel().getColumn(0).setResizable(false);
            jTableLeague.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTableLeague.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_leagueName.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jLabel_leagueName.setForeground(new java.awt.Color(63, 16, 82));
        jLabel_leagueName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_leagueName.setText("My League");

        jButton_latestNews.setBackground(new java.awt.Color(109, 28, 145));
        jButton_latestNews.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_latestNews.setForeground(new java.awt.Color(240, 240, 240));
        jButton_latestNews.setText("Latest News");
        jButton_latestNews.setToolTipText("");
        jButton_latestNews.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_latestNews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_latestNewsActionPerformed(evt);
            }
        });

        jButton_latestNews1.setBackground(new java.awt.Color(109, 28, 145));
        jButton_latestNews1.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_latestNews1.setForeground(new java.awt.Color(240, 240, 240));
        jButton_latestNews1.setText("add league");
        jButton_latestNews1.setToolTipText("");
        jButton_latestNews1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_latestNews1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_latestNews1ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("current league");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jLabel_leagueName, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_latestNews, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_latestNews1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_leagueName)
                    .addComponent(jButton_latestNews)
                    .addComponent(jButton_latestNews1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(204, Short.MAX_VALUE))
        );

        // Removing inner borders inside the button
        jButton_latestNews.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_latestNews.setFocusPainted(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_latestNewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_latestNewsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_latestNewsActionPerformed

    private void jButton_latestNews1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_latestNews1ActionPerformed
        new AddNewLeague().show();
    }//GEN-LAST:event_jButton_latestNews1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int idIndex = jComboBox1.getSelectedIndex();
        currentLeagueID = Integer.parseInt(_leaguesList[idIndex][0].toString()) ;
        System.out.println(currentLeagueID);
        try{
            updateTeamsTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);  
            }
        });
    }*/
    
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

  private Object[][] getLeagues() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from league");  
            Object[][] leagueList = new Object[1000][1000];
            int index = 0 ;
            while(rs.next()) { 
                leagueList[index][0] = rs.getInt("id");
                leagueList[index][1] = rs.getString("leaguename");
                index ++ ;
             }
            con.close(); 
            return leagueList;
        }catch(Exception e){ 
                System.out.println(e);
                throw e;
        }  
   }
        
   private void updateTeamsTable() throws Exception{
       DefaultTableModel tblLeagueModel = (DefaultTableModel)jTableLeague.getModel();
        Object[][] _testData ;
        try{
           _testData = getTeams();
        }catch(Exception e){
          throw e ;
        }     
        for (int i = 0 ;i < tblLeagueModel.getRowCount() ; i++ ){
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
        }catch(Exception e){ 
                System.out.println(e);
                throw e;
        }  
   }


         
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_latestNews;
    private javax.swing.JButton jButton_latestNews1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_leagueName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLeague;
    // End of variables declaration//GEN-END:variables
}
