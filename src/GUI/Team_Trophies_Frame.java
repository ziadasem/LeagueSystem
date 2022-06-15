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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// import Functions.SharedData;

/**
 *
 * @author hosam
 */
public class Team_Trophies_Frame extends javax.swing.JFrame {

    //******************** Declaration of Variables, to be used for the Entire Class ********************//
    Object[][] trophiesList ;
    Object[][] totalTrophiesList ;
    Object[][] teamsList;
    private int ThiscurrentLeagueID;
    private int currentTeamID = -1;
    private int currentTrophyID = -1 ;
  
    DefaultTableCellRenderer tblTeamsRenderer = new DefaultTableCellRenderer();
    

    
    public Team_Trophies_Frame(String currentLeague_Name, int currentLeagueID) {
        initComponents();
        this.setLocationRelativeTo(null);
        ThiscurrentLeagueID = currentLeagueID;
        // Calling the rows center aligning function ...
        // Aligning Rows to the center ...
        setTableCellAlignment(SwingConstants.CENTER);
        try{
             updateTrophiesTable();
             buildTeamsComboBoxData();
             buildTrophiesComboBoxData();
           

        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane,"An Error Ocurred");
            trophiesList = new Object[][]{};
        }
        
            // Setting the label name for corresponding League Name
      
        
        
        //******************** League Table Properties ********************//
        jTableSquad.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableSquad.setOpaque(false);
        jTableSquad.getTableHeader().setBackground(new Color(51,85,175));
        jTableSquad.getTableHeader().setForeground(new Color(255,255,255));
        jTableSquad.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
    }

  private void updateTrophiesTable() throws Exception{
       DefaultTableModel tblSquadModel = (DefaultTableModel)jTableSquad.getModel();
        try{
           trophiesList = getTrophies();
        }catch(Exception e){
          throw e ;
        }     
        for (int i = tblSquadModel.getRowCount() - 1 ;i >= 0 ; i-- ){
            tblSquadModel.removeRow(i);
        }
        
        for(int i=0; trophiesList[i][2] != null; i++){
            tblSquadModel.addRow(trophiesList[i]);
        }
   }
   private Object[][] getTrophies() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT * FROM has_trophies, trophy WHERE trophyID = id and teamID = "+ currentTeamID ); 
            trophiesList = new Object[1000][5];
            int index = 0 ;
            while(rs.next()) {
                trophiesList[index][0] = rs.getString("name");
                trophiesList[index][1] = rs.getInt("count");
                trophiesList[index][2] = rs.getString("leagueid");
                trophiesList[index][3] = rs.getInt("teamID");
                trophiesList[index][4] = rs.getInt("trophyID");

                index ++ ;
             }
            
            con.close(); 
            return trophiesList;
        }catch(SQLException e){ 
                System.out.println(e);
                throw e;
        }  
   }
  
   
   public void assignNewTrophy(int count) throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            System.out.println("insert into has_trophies values"
                    + "(" +currentTeamID+ "," +currentTrophyID + "," +count+ ")");
            int insertingResult =stmt.executeUpdate("insert into has_trophies values"
                    + "(" +currentTeamID+ "," +currentTrophyID + "," +count+ ")" );  
            con.close(); 
        }catch(Exception e){ 
             System.out.println(e);
             throw e;
        }  
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Teams_Frame = new javax.swing.JPanel();
        jPanel_TeamsClose = new javax.swing.JPanel();
        jLabel_TeamsClose = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSquad = new javax.swing.JTable(){

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
        jLabel_squadName1 = new javax.swing.JLabel();
        jButton_Delete = new javax.swing.JButton();
        jButton_Add = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTrophy = new javax.swing.JComboBox<>();
        jLabel_firstName = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1280, 720));
        setType(java.awt.Window.Type.POPUP);

        jPanel_Teams_Frame.setBackground(new java.awt.Color(0, 51, 51));
        jPanel_Teams_Frame.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175)));
        jPanel_Teams_Frame.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel_TeamsClose.setBackground(new java.awt.Color(0, 51, 51));
        jPanel_TeamsClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel_TeamsCloseMouseReleased(evt);
            }
        });

        jLabel_TeamsClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_TeamsClose.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_TeamsClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TeamsClose.setText("X");
        jLabel_TeamsClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel_TeamsCloseLayout = new javax.swing.GroupLayout(jPanel_TeamsClose);
        jPanel_TeamsClose.setLayout(jPanel_TeamsCloseLayout);
        jPanel_TeamsCloseLayout.setHorizontalGroup(
            jPanel_TeamsCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_TeamsClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        jPanel_TeamsCloseLayout.setVerticalGroup(
            jPanel_TeamsCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_TeamsClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane1.setBackground(new java.awt.Color(63, 16, 82));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175)));
        jScrollPane1.setToolTipText("");
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1024, 720));
        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1024, 720));

        jTableSquad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSquad.setFocusable(false);
        jTableSquad.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableSquad.setMinimumSize(new java.awt.Dimension(1024, 720));
        jTableSquad.setPreferredSize(new java.awt.Dimension(1024, 720));
        jTableSquad.setRowHeight(30);
        jTableSquad.setSelectionForeground(new java.awt.Color(0, 120, 215));
        jTableSquad.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableSquad.setShowVerticalLines(false);
        jTableSquad.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableSquad);

        jLabel_squadName1.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel_squadName1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_squadName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_squadName1.setText("Trophies");
        jLabel_squadName1.setPreferredSize(new java.awt.Dimension(130, 43));

        jButton_Delete.setBackground(new java.awt.Color(51, 85, 175));
        jButton_Delete.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_Delete.setForeground(new java.awt.Color(240, 240, 240));
        jButton_Delete.setText("Delete");
        jButton_Delete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_Delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        jButton_Add.setBackground(new java.awt.Color(51, 85, 175));
        jButton_Add.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_Add.setForeground(new java.awt.Color(240, 240, 240));
        jButton_Add.setText("Add");
        jButton_Add.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_Add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Choose Team");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 204, 204));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Team Trophies");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 26)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Choose Trophy");

        jTrophy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTrophyActionPerformed(evt);
            }
        });

        jLabel_firstName.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName.setText("Count");

        jTextField_name.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_name.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        jTextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Teams_FrameLayout = new javax.swing.GroupLayout(jPanel_Teams_Frame);
        jPanel_Teams_Frame.setLayout(jPanel_Teams_FrameLayout);
        jPanel_Teams_FrameLayout.setHorizontalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTrophy, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel_firstName, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_squadName1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );
        jPanel_Teams_FrameLayout.setVerticalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addComponent(jLabel_squadName1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTrophy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(158, 158, 158)))
                .addGap(39, 39, 39)
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Delete)
                    .addComponent(jButton_Add))
                .addGap(50, 50, 50))
        );

        // Removing inner borders inside the button
        jButton_Delete.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_Add.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Teams_Frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Teams_Frame, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_TeamsCloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_TeamsCloseMouseReleased
        this.dispose();
    }//GEN-LAST:event_jPanel_TeamsCloseMouseReleased

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
    int reply = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
    if(reply == JOptionPane.NO_OPTION)
        return;
    else if(reply == JOptionPane.YES_OPTION)  {
        deleteTrophy();
    }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        try{
            int count = Integer.parseInt(jTextField_name.getText()) ;
            if(count <=0 || count >= 120)
            {
                JOptionPane.showMessageDialog(this,"Data Entry Error ... Invalid Trophies Number", "Invalid Trophies",JOptionPane.ERROR_MESSAGE);
                return;
            }
            assignNewTrophy(count);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,"Data Entry Error ... Trophies Must Be Integers", "Invalid Trophies",JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this,"You Cannot Insert New Trophies For The Same Team !!!", "Invalid Team",JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "An Error Ocurred");
            System.out.println("is it here?");
            return ;
        }
        this.dispose();
        try{
        Thread.sleep(250);
        new Team_Trophies_Frame(jLabel33.getText(), ThiscurrentLeagueID).show();
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton_AddActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int idIndex = jComboBox1.getSelectedIndex();
        currentTeamID = Integer.parseInt(  teamsList[idIndex][2].toString()) ;
        try{
           updateTrophiesTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "An Error Ocurred");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTrophyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTrophyActionPerformed
        int idIndex = jTrophy.getSelectedIndex();
        currentTrophyID = Integer.parseInt(totalTrophiesList[idIndex][1].toString()) ;
        
    }//GEN-LAST:event_jTrophyActionPerformed

    private void jTextField_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nameActionPerformed

   
    public static void main(String args[]) {
       
    }
    
    
    
        private void setTableCellAlignment(int alignment) {
            tblTeamsRenderer.setHorizontalAlignment(alignment);
            for (int i=0; i<jTableSquad.getColumnCount();i++){
               jTableSquad.setDefaultRenderer(jTableSquad.getColumnClass(i),tblTeamsRenderer);
               }
             // repaint to show table cell changes
            jTableSquad.updateUI();
        }
    
              
  
      
    public void buildTeamsComboBoxData() throws Exception{
        Object[][] _testData ;
        try{
           _testData = getTeams();
        }catch(Exception e){
          throw e ;
        } 
      
        
        for (int i = 0 ;_testData[i][0] != null  ; i++ ){
            jComboBox1.addItem(_testData[i][0].toString());
        }
        
   } 
    
    private void deleteTrophy(){
        try{  
            
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);
            int row = jTableSquad.getSelectedRow();     // For Selected Row in the table
            Statement stmt=con.createStatement();  
            int rs=stmt.executeUpdate("DELETE from has_trophies where teamid =" +trophiesList[row][3] + " and trophyid = " +trophiesList[row][4]   );
            con.close();
            // Updating & Showing the Teams Table Again ...
        try{
            updateTrophiesTable();
            }
            catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            trophiesList = new Object[][]{};
            }
        // Restarting the Team_Frame JFrame ...
            this.dispose();
            try{
            Thread.sleep(250);
            new Team_Trophies_Frame(jLabel33.getText(), ThiscurrentLeagueID).show();
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){ 
                System.out.println(e);
                System.out.println("Error In Delete player Function");
        }
    }
    
    private Object[][] getTeams() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
               Config.username,Config.password);  
           
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from team WHERE LEAGUEID ="+ ThiscurrentLeagueID);  
            teamsList = new Object[1000][5];
            int index = 0 ;
            while(rs.next()) { 
                 teamsList[index][0] = rs.getString("name");
                 teamsList[index][1] = rs.getInt("foundedyear");
                 teamsList[index][2] = rs.getInt("id");
                index ++ ;
             }
            con.close(); 
            return teamsList;
        }catch(SQLException e){ 
                System.out.println(e);
                System.out.println("Error In getting Teams Function");
                throw e;
        }  
   }
        
   public void buildTrophiesComboBoxData() throws Exception{
        Object[][] _testData ;
        try{
           totalTrophiesList = getTotalTrophies();
        }catch(Exception e){
          throw e ;
        } 
      
        // jComboBox1.removeAllItems();
        for (int i = 0 ;totalTrophiesList[i][0] != null  ; i++ ){
            jTrophy.addItem(totalTrophiesList[i][0].toString());
        }
        
   } 
    
    private Object[][] getTotalTrophies() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
               Config.username,Config.password);  
           
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from trophy WHERE LEAGUEID ="+ ThiscurrentLeagueID);  
            totalTrophiesList = new Object[1000][3];
            int index = 0 ;
            while(rs.next()) { 
                 totalTrophiesList[index][0] = rs.getString("name");
                 totalTrophiesList[index][1] = rs.getInt("id");
                index ++ ;
             }
            con.close(); 
            return totalTrophiesList;
        }catch(SQLException e){ 
                System.out.println(e);
                System.out.println("Error In getting Teams Function");
                throw e;
        }  
   }
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_TeamsClose;
    private javax.swing.JLabel jLabel_firstName;
    private javax.swing.JLabel jLabel_squadName1;
    private javax.swing.JPanel jPanel_TeamsClose;
    private javax.swing.JPanel jPanel_Teams_Frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSquad;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JComboBox<String> jTrophy;
    // End of variables declaration//GEN-END:variables
}
