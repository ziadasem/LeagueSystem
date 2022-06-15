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
 * @author hosam
 */
public class Coach_Frame extends javax.swing.JFrame {

    //******************** Declaration of Variables, to be used for the Entire Class ********************//
    Object[][] coachesList ;

    //*****************************************************************//
    
    /**
     * Creates new form Teams_Frame
     */
    
              //******************** Teams Table Renderer ********************//
        // Necessary For alligning Rows
    DefaultTableCellRenderer tblTeamsRenderer = new DefaultTableCellRenderer();
    

    
    public Coach_Frame() {
        initComponents();
        this.setLocationRelativeTo(null);
        // Calling the rows center aligning function ...
        // Aligning Rows to the center ...
        setTableCellAlignment(SwingConstants.CENTER);
        try{
             updateCoachesTable();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            coachesList = new Object[][]{};
        }
        
        
        //******************** League Table Properties ********************//
        jTableTeams.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableTeams.setOpaque(false);
        jTableTeams.getTableHeader().setBackground(new Color(63, 16, 82));
        jTableTeams.getTableHeader().setForeground(new Color(255,255,255));
        jTableTeams.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
    }

    private void updateCoachesTable() throws Exception{
       DefaultTableModel tblSquadModel = (DefaultTableModel)jTableTeams.getModel();
        try{
           coachesList = getCoaches();
        }catch(Exception e){
          throw e ;
        }     
        // Fixed Bug (Duplicate Table Data Showing)
        for (int i = tblSquadModel.getRowCount() - 1 ;i >= 0 ; i-- ){
            tblSquadModel.removeRow(i);
        }
        
        for(int i=0; coachesList[i][0] != null; i++)
            tblSquadModel.addRow(coachesList[i]);
   }
   
   private Object[][] getCoaches() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from coach");  
            coachesList = new Object[1000][3];
            int index = 0 ;
            while(rs.next()) { 
                coachesList[index][2] = rs.getInt("id");
                coachesList[index][0] = rs.getString("firstname") + " " + rs.getString("lastname");
                coachesList[index][1] = rs.getInt("yearsOfExperince");
                index ++ ;
             }
            con.close(); 
            return coachesList;
        }catch(SQLException e){ 
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
        jTextField_firstName = new javax.swing.JTextField();
        jTextField_lastName = new javax.swing.JTextField();
        jTextField_Position = new javax.swing.JTextField();
        jLabel_firstName = new javax.swing.JLabel();
        jLabel_lastName = new javax.swing.JLabel();
        jLabel_Position = new javax.swing.JLabel();
        jButton_Delete = new javax.swing.JButton();
        jButton_Add = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1280, 720));
        setType(java.awt.Window.Type.POPUP);

        jPanel_Teams_Frame.setBackground(new java.awt.Color(0, 51, 51));
        jPanel_Teams_Frame.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175)));

        jPanel_TeamsClose.setBackground(new java.awt.Color(209, 181, 234));
        jPanel_TeamsClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel_TeamsCloseMouseReleased(evt);
            }
        });

        jLabel_TeamsClose.setBackground(new java.awt.Color(0, 51, 51));
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
                "name", "position"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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
        jLabel_leagueName.setText("coach");
        jLabel_leagueName.setPreferredSize(new java.awt.Dimension(130, 43));

        jTextField_firstName.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_firstName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_firstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_firstName.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jTextField_lastName.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_lastName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_lastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_lastName.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jTextField_Position.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_Position.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_Position.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Position.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jLabel_firstName.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName.setText("First Name");

        jLabel_lastName.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_lastName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_lastName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_lastName.setText("Last Name");

        jLabel_Position.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_Position.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_Position.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Position.setText("years of experience");

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
        jLabel1.setText("coach");

        javax.swing.GroupLayout jPanel_Teams_FrameLayout = new javax.swing.GroupLayout(jPanel_Teams_Frame);
        jPanel_Teams_Frame.setLayout(jPanel_Teams_FrameLayout);
        jPanel_Teams_FrameLayout.setHorizontalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386)
                .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                                .addComponent(jLabel_lastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                                .addComponent(jLabel_Position, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_Position, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                                .addComponent(jLabel_firstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_leagueName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31))))
        );
        jPanel_Teams_FrameLayout.setVerticalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_leagueName, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Position, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Position, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(123, 123, 123)))
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
            .addComponent(jPanel_Teams_Frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_TeamsCloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_TeamsCloseMouseReleased
        this.dispose();
    }//GEN-LAST:event_jPanel_TeamsCloseMouseReleased

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed

        try{
            Connection con=DriverManager.getConnection( Config.hostName,
                Config.username,Config.password);
            int row = jTableTeams.getSelectedRow();     // For Selected Row in the table
            int coachID =  Integer.parseInt(coachesList[row][2].toString()) ;
            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate("DELETE from coach where ID =" + coachID);
            con.close();
            // Updating & Showing the Teams Table Again ...
            try{
                updateCoachesTable();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
                coachesList = new Object[][]{};
            }
            // Restarting the Team_Frame JFrame ...
            this.dispose();
            try{
                Thread.sleep(250);
                new Coach_Frame().show();
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){
            System.out.println(e);
            System.out.println("Error In Delete player Function");
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        String temp_firstName = jTextField_firstName.getText();
        String temp_lastName = jTextField_lastName.getText();
        int temp_yearsOfExp = Integer.parseInt(jTextField_Position.getText());
        DataEntryChecking t1 = new DataEntryChecking();
        // Checking For Wrong Team Name Entry
        if(!(t1.isValid_Name(temp_firstName)))
        {
            JOptionPane.showMessageDialog(this,"Invalid First Name", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Checking For Wrong Year Entry
        if(!(t1.isValid_Name(temp_lastName)))
        {
            JOptionPane.showMessageDialog(this,"Invalid Last Name", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Checking For Wrong Coach First Name Entry
        if(!(t1.isValid_Name(temp_firstName)))
        {
            JOptionPane.showMessageDialog(this,"Invalid Position", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            addNewCoach(temp_firstName ,temp_lastName,temp_yearsOfExp  );
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "An Error Ocurred");
            System.out.println("is it here?");
            return ;
        }
        this.dispose();
        try{
            Thread.sleep(250);
            new Coach_Frame().show();
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
    
    
  
            
            
   private void addNewCoach(String firstName, String lastName , int yearsOfExp) throws Exception
        {
            // Removing White Spaces ...
            firstName = firstName.trim();
            lastName = lastName.trim();
            try{
                Connection con=DriverManager.getConnection( Config.hostName,
                Config.username,Config.password);
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("SELECT MAX(ID) FROM COACH");  
                int id =  0;
                while(rs.next()) {
                id = rs.getInt(1) + 1 ;
                }
                int insertingResult =stmt.executeUpdate("insert into COACH values(" +id+ ",'" +firstName+ "','" +lastName+ "'," + yearsOfExp + ")"  );  
                con.close(); 
            }catch(Exception e)
                { 
                System.out.println(e);
                System.out.println("Error In Adding Coach Function");
                throw e;
                }  
        }
            
            
          
            

        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Position;
    private javax.swing.JLabel jLabel_TeamsClose;
    private javax.swing.JLabel jLabel_firstName;
    private javax.swing.JLabel jLabel_lastName;
    private javax.swing.JLabel jLabel_leagueName;
    private javax.swing.JPanel jPanel_TeamsClose;
    private javax.swing.JPanel jPanel_Teams_Frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTeams;
    private javax.swing.JTextField jTextField_Position;
    private javax.swing.JTextField jTextField_firstName;
    private javax.swing.JTextField jTextField_lastName;
    // End of variables declaration//GEN-END:variables
}
