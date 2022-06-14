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
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// import Functions.SharedData;

/**
 *
 * @author Ziad ben Assem
 */
public class Trophies_Frame extends javax.swing.JFrame {

    private int ThiscurrentLeagueID;
    private int currentTeamID = 0;
    Object[][] trophiesList ;

    DefaultTableCellRenderer tblTeamsRenderer = new DefaultTableCellRenderer();
    
    public Trophies_Frame(String currentLeague_Name, int currentLeagueID) {
        initComponents();
        this.setLocationRelativeTo(null);
        ThiscurrentLeagueID = currentLeagueID;
        setTableCellAlignment(SwingConstants.CENTER);
        try{
             updateTrophiesTable();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            trophiesList = new Object[][]{};
        }
        
        jLabel3.setText(currentLeague_Name);
        
        //******************** League Table Properties ********************//
        jTableTeams.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableTeams.setOpaque(false);
        jTableTeams.getTableHeader().setBackground(new Color(63, 16, 82));
        jTableTeams.getTableHeader().setForeground(new Color(255,255,255));
        jTableTeams.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
    }

 
  private void updateTrophiesTable() throws Exception{
       DefaultTableModel tblSquadModel = (DefaultTableModel)jTableTeams.getModel();
        try{
           trophiesList = getTrophies();
        }catch(Exception e){
          throw e ;
        }     
        // Fixed Bug (Duplicate Table Data Showing)
        for (int i = tblSquadModel.getRowCount() - 1 ;i >= 0 ; i-- ){
            tblSquadModel.removeRow(i);
        }
        
        for(int i=0; trophiesList[i][0] != null; i++)
            tblSquadModel.addRow(trophiesList[i]);
   }
   private Object[][] getTrophies() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            // Selecting Only Stadiums That Belong to teams in this particular chosen League (Based on LeagueID) ...
            ResultSet rs=stmt.executeQuery("SELECT * FROM trophy WHERE leagueid = "+ ThiscurrentLeagueID );  
            trophiesList = new Object[1000][3];
            int index = 0 ;
            while(rs.next()) {
                trophiesList[index][0] = rs.getString("name");
                trophiesList[index][1] = rs.getInt("id");
                trophiesList[index][2] = rs.getString("leagueid");
                index ++ ;
             }
            
            con.close(); 
            return trophiesList;
        }catch(SQLException e){ 
                System.out.println(e);
                throw e;
        }  
   }
   

     private void addNewTrophy(String name) throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT MAX(ID) FROM trophy");  
            int id =  0;
            while(rs.next()) {
               id = rs.getInt(1) + 1 ;
            }
           
            int insertingResult =stmt.executeUpdate("insert into trophy values(" + id+ " ,'" +name+ "', "+ ThiscurrentLeagueID + ")");
            con.close(); 
        }catch(Exception e){ 
             System.out.println(e);
             throw e;
        }  
    }
     
    private void deleteTrophy(){
         int reply = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
    if(reply == JOptionPane.NO_OPTION)
        return;
    else if(reply == JOptionPane.YES_OPTION)  
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                Config.username,Config.password);
            int row = jTableTeams.getSelectedRow();     // For Selected Row in the table
            int trophyID =  (int) trophiesList[row][1];
            Statement stmt=con.createStatement();  
            Statement stmt2=con.createStatement();  
            int rs=stmt.executeUpdate("DELETE from HAS_TROPHIES where teamid =" + trophyID);
            int rs2=stmt2.executeUpdate("DELETE from trophy where id =" + trophyID);
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
            new Trophies_Frame(jLabel3.getText(), ThiscurrentLeagueID).show();
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){ 
                System.out.println(e);
                System.out.println("Error In Delete Stadium Function");
        }     
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Teams_Frame = new javax.swing.JPanel();
        jPanel_TeamsClose = new javax.swing.JPanel();
        jLabel_TeamsClose = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTeams = new javax.swing.JTable(){

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
                if((rw == jTableTeams.getRowCount() - 1 && col == 0) || (rw == jTableTeams.getRowCount() - 2 && col == 0) || (rw == jTableTeams.getRowCount() - 3 && col == 0))
                c.setBackground(new Color(243, 64, 54));
                return c;
            }
        };
        jLabel_leagueName = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jLabel_firstName = new javax.swing.JLabel();
        jButton_Delete = new javax.swing.JButton();
        jButton_Add = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1280, 720));
        setType(java.awt.Window.Type.POPUP);

        jPanel_Teams_Frame.setBackground(new java.awt.Color(0, 51, 51));
        jPanel_Teams_Frame.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(109, 28, 145), new java.awt.Color(109, 28, 145)));
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
        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(109, 28, 145), new java.awt.Color(109, 28, 145), new java.awt.Color(109, 28, 145), new java.awt.Color(109, 28, 145)));
        jScrollPane1.setToolTipText("");
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1024, 720));
        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1024, 720));

        jTableTeams.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTeams.setFocusable(false);
        jTableTeams.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableTeams.setMinimumSize(new java.awt.Dimension(1024, 720));
        jTableTeams.setPreferredSize(new java.awt.Dimension(1024, 720));
        jTableTeams.setRowHeight(30);
        jTableTeams.setSelectionForeground(new java.awt.Color(0, 120, 215));
        jTableTeams.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableTeams.setShowVerticalLines(false);
        jTableTeams.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableTeams);

        jLabel_leagueName.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel_leagueName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_leagueName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_leagueName.setText("Trophies");
        jLabel_leagueName.setPreferredSize(new java.awt.Dimension(130, 43));

        jTextField_name.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_name.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jLabel_firstName.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName.setText("name");

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

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trophy");

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("League Trophies");

        javax.swing.GroupLayout jPanel_Teams_FrameLayout = new javax.swing.GroupLayout(jPanel_Teams_Frame);
        jPanel_Teams_Frame.setLayout(jPanel_Teams_FrameLayout);
        jPanel_Teams_FrameLayout.setHorizontalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386)
                .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addComponent(jLabel_firstName, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_leagueName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(478, 478, 478)
                .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_Teams_FrameLayout.setVerticalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel_leagueName, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(225, 225, 225)))
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Delete)
                    .addComponent(jButton_Add))
                .addGap(54, 54, 54))
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
            .addComponent(jPanel_Teams_Frame, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_TeamsCloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_TeamsCloseMouseReleased
        this.dispose();
    }//GEN-LAST:event_jPanel_TeamsCloseMouseReleased

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed

       deleteTrophy();
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        String temp_firstName = jTextField_name.getText();
        DataEntryChecking t1 = new DataEntryChecking();
        if(t1.containsNumbers(temp_firstName)){
            JOptionPane.showMessageDialog(this, "Trophy name cannot contain numbers");
            return;
        }
        // Checking For Wrong Team Name Entry
        if(!(t1.isValid_Name(temp_firstName)))
        {
            JOptionPane.showMessageDialog(this,"Invalid First Name", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            addNewTrophy(temp_firstName );
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
            System.out.println("is it here?");
            return ;
        }
        this.dispose();
        try{
        Thread.sleep(250);
        new Trophies_Frame(jLabel3.getText(), ThiscurrentLeagueID).show();
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton_AddActionPerformed

   
    public static void main(String args[]) {
      
    }
    
    
    
        private void setTableCellAlignment(int alignment) {
            tblTeamsRenderer.setHorizontalAlignment(alignment);
            for (int i=0; i<jTableTeams.getColumnCount();i++){
               jTableTeams.setDefaultRenderer(jTableTeams.getColumnClass(i),tblTeamsRenderer);
               }
             // repaint to show table cell changes
            jTableTeams.updateUI();
        }
    
            
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_TeamsClose;
    private javax.swing.JLabel jLabel_firstName;
    private javax.swing.JLabel jLabel_leagueName;
    private javax.swing.JPanel jPanel_TeamsClose;
    private javax.swing.JPanel jPanel_Teams_Frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTeams;
    private javax.swing.JTextField jTextField_name;
    // End of variables declaration//GEN-END:variables
}
