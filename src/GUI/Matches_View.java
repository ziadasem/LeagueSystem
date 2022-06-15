/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Functions.Config;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import Functions.DataEntryChecking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Matches_View extends javax.swing.JFrame {

  
    Object[][] _matchsList ;
    private int currentLeagueID;
    String currentLeague_Name;
    Object[][] teamsList;
    Object[][] stadiumList;    
    int currentWeek = -1 ;


 
    DefaultTableCellRenderer tblTeamsRenderer = new DefaultTableCellRenderer();
    

    
    public Matches_View(String currentLeague_Name, int currentLeagueID, int currentWeekId) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.currentLeagueID = currentLeagueID;
        this.currentLeague_Name = currentLeague_Name;
        this.currentWeek = currentWeekId ;
        setTableCellAlignment(SwingConstants.CENTER);
        try{
          updateMatchesTable();
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            _matchsList = new Object[][]{};
        }
        
            // Setting the label name for corresponding League Name
        jLabel_leagueName_Matches.setText(currentLeague_Name + " Matches");
        
       
        
        //******************** League Matches Properties ********************//
        jTableMatches.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableMatches.setOpaque(false);
        // Setting Colmuns Width
        jTableMatches.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableMatches.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableMatches.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        jTableMatches.getTableHeader().setBackground(new Color(51,85,175));
        jTableMatches.getTableHeader().setForeground(new Color(255,255,255));
        jTableMatches.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Matches_Frame = new javax.swing.JPanel();
        jPanel_MatchesClose = new javax.swing.JPanel();
        jLabel_MatchesClose = new javax.swing.JLabel();
        jLabel_leagueName_Matches = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMatches = new javax.swing.JTable(){

            public Component prepareRenderer(TableCellRenderer r, int rw, int col)
            {
                Component c = super.prepareRenderer(r, rw, col);
                c.setBackground(Color.WHITE);
                c.setFont(new Font("League", Font.BOLD,18));
                // Setting Alternating Colors
                if(rw %2 == 0)
                c.setBackground(new Color(225, 225, 225));
                return c;
            }
        };
        jLabel_Week = new javax.swing.JLabel();
        jTextField_Week = new javax.swing.JTextField();
        jButton_Filter = new javax.swing.JButton();
        jButton_Clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1286, 720));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1280, 720));
        setType(java.awt.Window.Type.POPUP);

        jPanel_Matches_Frame.setBackground(new java.awt.Color(0, 51, 51));
        jPanel_Matches_Frame.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175)));
        jPanel_Matches_Frame.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel_Matches_Frame.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel_MatchesClose.setBackground(new java.awt.Color(0, 51, 51));
        jPanel_MatchesClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel_MatchesCloseMouseReleased(evt);
            }
        });

        jLabel_MatchesClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_MatchesClose.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_MatchesClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_MatchesClose.setText("X");
        jLabel_MatchesClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel_MatchesCloseLayout = new javax.swing.GroupLayout(jPanel_MatchesClose);
        jPanel_MatchesClose.setLayout(jPanel_MatchesCloseLayout);
        jPanel_MatchesCloseLayout.setHorizontalGroup(
            jPanel_MatchesCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_MatchesClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        jPanel_MatchesCloseLayout.setVerticalGroup(
            jPanel_MatchesCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_MatchesClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel_leagueName_Matches.setFont(new java.awt.Font("Cambria", 1, 38)); // NOI18N
        jLabel_leagueName_Matches.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_leagueName_Matches.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_leagueName_Matches.setText("League");
        jLabel_leagueName_Matches.setPreferredSize(new java.awt.Dimension(130, 43));

        jScrollPane2.setBackground(new java.awt.Color(63, 16, 82));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175)));
        jScrollPane2.setToolTipText("");
        jScrollPane2.setMinimumSize(new java.awt.Dimension(1024, 720));
        jScrollPane2.setOpaque(false);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1024, 720));

        jTableMatches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Team 1", "Team 2", "Stadium"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMatches.setFocusable(false);
        jTableMatches.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableMatches.setMinimumSize(new java.awt.Dimension(1024, 720));
        jTableMatches.setPreferredSize(new java.awt.Dimension(1024, 720));
        jTableMatches.setRowHeight(30);
        jTableMatches.setSelectionForeground(new java.awt.Color(0, 120, 215));
        jTableMatches.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableMatches.setShowVerticalLines(false);
        jTableMatches.getTableHeader().setResizingAllowed(false);
        jTableMatches.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableMatches);

        jLabel_Week.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_Week.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_Week.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Week.setText("Week");

        jTextField_Week.setEditable(false);
        jTextField_Week.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_Week.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jTextField_Week.setForeground(new java.awt.Color(51, 51, 51));
        jTextField_Week.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Week.setText("1");
        jTextField_Week.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        jTextField_Week.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_WeekMouseClicked(evt);
            }
        });

        jButton_Filter.setBackground(new java.awt.Color(51, 85, 175));
        jButton_Filter.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_Filter.setForeground(new java.awt.Color(240, 240, 240));
        jButton_Filter.setText("Filter");
        jButton_Filter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_Filter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FilterActionPerformed(evt);
            }
        });

        jButton_Clear.setBackground(new java.awt.Color(51, 85, 175));
        jButton_Clear.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_Clear.setForeground(new java.awt.Color(240, 240, 240));
        jButton_Clear.setText("Clear Filters");
        jButton_Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_Clear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Matches_FrameLayout = new javax.swing.GroupLayout(jPanel_Matches_Frame);
        jPanel_Matches_Frame.setLayout(jPanel_Matches_FrameLayout);
        jPanel_Matches_FrameLayout.setHorizontalGroup(
            jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                .addContainerGap(1241, Short.MAX_VALUE)
                .addComponent(jPanel_MatchesClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel_Week, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Week, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButton_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1192, Short.MAX_VALUE)
                    .addComponent(jLabel_leagueName_Matches, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Matches_FrameLayout.setVerticalGroup(
            jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_MatchesClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel_leagueName_Matches, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Week, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Week, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        // Removing inner borders inside the button
        jButton_Filter.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_Clear.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Matches_Frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Matches_Frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_MatchesCloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_MatchesCloseMouseReleased
        this.dispose();
    }//GEN-LAST:event_jPanel_MatchesCloseMouseReleased

    private void jTextField_WeekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_WeekMouseClicked
        jTextField_Week.setEditable(true);
        jTextField_Week.setText("");
    }//GEN-LAST:event_jTextField_WeekMouseClicked

    private void jButton_FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FilterActionPerformed
        String temp_Week = jTextField_Week.getText();      
        DataEntryChecking t1 = new DataEntryChecking();
        if(!(t1.isValid_Week(temp_Week)))
        {
            JOptionPane.showMessageDialog(this,"Invalid Week ... Please Choose a Valid Week", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
           
            jTextField_Week.setForeground(Color.red);
            jTextField_Week.setText("0 - 100!");
            return;
        }
        try{
            currentWeek  = Integer.parseInt(temp_Week);
            this.dispose();
            try{
                Thread.sleep(250);
                new Matches_View(this.currentLeague_Name, currentLeagueID , currentWeek).show();
             }catch(InterruptedException e){
                System.out.println(e.getMessage());
              }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
            return ;
        }
      
    }//GEN-LAST:event_jButton_FilterActionPerformed

    private void jButton_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearActionPerformed
      
            this.dispose();
            try{
                Thread.sleep(250);
                new Matches_View(this.currentLeague_Name, currentLeagueID , -1).show();
             }catch(InterruptedException e){
                System.out.println(e.getMessage());
              }

    }//GEN-LAST:event_jButton_ClearActionPerformed

    
  
    public void updateMatchesTable() throws Exception{
      DefaultTableModel tblMatchesModel = (DefaultTableModel)jTableMatches.getModel();
        try{
           _matchsList = getMatches();
        }catch(Exception e){
          throw e ;
        }     
        for (int i = 0 ;i < tblMatchesModel.getRowCount() ; i++ ){
            tblMatchesModel.removeRow(i);
        }
        
        for(int i=0; _matchsList[i][0] != null; i++)
            tblMatchesModel.addRow(_matchsList[i]);
   }
            
    
        private void setTableCellAlignment(int alignment) {
            tblTeamsRenderer.setHorizontalAlignment(alignment);
            for (int i=0; i<jTableMatches.getColumnCount();i++){
               jTableMatches.setDefaultRenderer(jTableMatches.getColumnClass(i),tblTeamsRenderer);
               }
             // repaint to show table cell changes
            jTableMatches.updateUI();
        }
    
            
private Object[][] getMatches() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
             Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            Statement stmt2=con.createStatement();  
            Statement stmt3=con.createStatement();  
            Statement stmt4=con.createStatement();  
      
            String condition = "";
            if (currentWeek == -1){
                condition = " and week = " + currentWeek + " ";
            }
            ResultSet gamesStatement=stmt.executeQuery("select distinct id, time,leagueid,stadiumid,week from game, play where Leagueid =" +currentLeagueID + "and play.matchid = game.id "+condition+" order by week");  

            Object[][] gamesList = new Object[1000][5];
            int index = 0 ;
            while(gamesStatement.next()) { 
                int matchID = gamesStatement.getInt("id");        
                gamesList[index][0] = "week #"+gamesStatement.getInt("week")+ " " + gamesStatement.getString("time");

                ResultSet teamsStamtment=stmt3.executeQuery("select name from team where id in (select teamid from play where Matchid =" +matchID +" )");
                ResultSet stadiumStatment =stmt4.executeQuery("select name from stadium where id = (select stadiumid from game where id  =" +matchID +" )");

                int teamsIndex = 1 ;
                while(teamsStamtment.next()){
                     gamesList[index][teamsIndex] = teamsStamtment.getString("name");
                     teamsIndex++ ;
                }
                while(stadiumStatment.next()){
                     gamesList[index][3] = stadiumStatment.getString("name");
                }
                
               gamesList[index][4] = gamesStatement.getInt("id");
               index ++ ;
             }
            con.close(); 
            return gamesList;
        }catch(SQLException e){ 
                System.out.println(e);
                System.out.println("Error In Update Teams Function");
                throw e;
        }  
   }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Clear;
    private javax.swing.JButton jButton_Filter;
    private javax.swing.JLabel jLabel_MatchesClose;
    private javax.swing.JLabel jLabel_Week;
    private javax.swing.JLabel jLabel_leagueName_Matches;
    private javax.swing.JPanel jPanel_MatchesClose;
    private javax.swing.JPanel jPanel_Matches_Frame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableMatches;
    private javax.swing.JTextField jTextField_Week;
    // End of variables declaration//GEN-END:variables
}
