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
public class Squad_Frame extends javax.swing.JFrame {

    //******************** Declaration of Variables, to be used for the Entire Class ********************//
    Object[][] squadList ;
    Object[][] _leaguesList ;
    Object[][] teamsList;
    private int ThiscurrentLeagueID;
    private int currentTeamID = 0;          
    DefaultTableCellRenderer tblTeamsRenderer = new DefaultTableCellRenderer();
    

    
    public Squad_Frame(String currentLeague_Name, int currentLeagueID) {
        initComponents();
        this.setLocationRelativeTo(null);
        ThiscurrentLeagueID = currentLeagueID;
        // Calling the rows center aligning function ...
        // Aligning Rows to the center ...
        setTableCellAlignment(SwingConstants.CENTER);
        try{
             updateٍSquadTable();
             buildTeamsComboBoxData();

        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            squadList = new Object[][]{};
        }
        
            // Setting the label name for corresponding League Name
        jLabel3.setText(currentLeague_Name);
        
        
        //******************** League Table Properties ********************//
        jTableSquad.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableSquad.setOpaque(false);
        jTableSquad.getTableHeader().setBackground(new Color(51,85,175));
        jTableSquad.getTableHeader().setForeground(new Color(255,255,255));
        jTableSquad.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
    }

    private void updateٍSquadTable() throws Exception{
       DefaultTableModel tblSquadModel = (DefaultTableModel)jTableSquad.getModel();
        try{
           squadList = getSquad();
        }catch(Exception e){
          throw e ;
        }     
        // Fixed Bug (Duplicate Table Data Showing)
        for (int i = tblSquadModel.getRowCount() - 1 ;i >= 0 ; i-- ){
            tblSquadModel.removeRow(i);
        }
        
        for(int i=0; squadList[i][0] != null; i++)
            tblSquadModel.addRow(squadList[i]);
   }
   
   private Object[][] getSquad() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from player where TEAMID =" +currentTeamID);  
            squadList = new Object[50][3];
            int index = 0 ;
            while(rs.next()) { 
                squadList[index][0] = rs.getString("firstname") + " " + rs.getString("lastname");
                squadList[index][1] = rs.getString("position");
                index ++ ;
             }
            con.close(); 
            return squadList;
        }catch(SQLException e){ 
                System.out.println(e);
                throw e;
        }  
   }
   
   private void addNewPlayer(String firstname , String lastname, String position) throws Exception{
       try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT MAX(ID) FROM player");  
            int id =  0;
            while(rs.next()) {
               id = rs.getInt(1) + 1 ;
            }
            int insertingResult =stmt.executeUpdate("insert into player values"
                    + "(" +id+ ",'" +firstname+ "','" +lastname+ "','" +position+ "'," +currentTeamID+ ")" );  
            con.close(); 
        }catch(Exception e){ 
             System.out.println(e);
             throw e;
        }  
   }
   
     private void updateCaptain(int newPlayerID , int clubID) throws Exception{
       try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
           Statement updateTeamCaptainStatment=con.createStatement(); 
           Statement updateCaptainStatment=con.createStatement(); 

            //remove old captain
            ResultSet rs=stmt.executeQuery("SELECT CAPTINID FROM team where id=" + clubID);  
            int oldPlayerID =  -1;
            while(rs.next()) {
               oldPlayerID = rs.getInt("CAPTINID")  ;
            }
            if (oldPlayerID != -1){
                 Statement stmt2=con.createStatement(); 
                 stmt2.executeUpdate("update  player set isCaptain = false where id=" + oldPlayerID);  
            }    
            
            updateTeamCaptainStatment.executeUpdate("update  team set CAPTINID = "+newPlayerID+" where id=" + clubID);  
            updateCaptainStatment.executeUpdate("update  player set ISCAPTAIN = true where id = "+newPlayerID);  
            
            con.close(); 
        }catch(Exception e){ 
             System.out.println(e);
             System.out.println("error in updataing capatin");
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
        jLabel_squadName = new javax.swing.JLabel();
        jTextField_firstName = new javax.swing.JTextField();
        jTextField_lastName = new javax.swing.JTextField();
        jTextField_Position = new javax.swing.JTextField();
        jLabel_firstName = new javax.swing.JLabel();
        jLabel_lastName = new javax.swing.JLabel();
        jLabel_Position = new javax.swing.JLabel();
        jButton_Delete = new javax.swing.JButton();
        jButton_Add = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));
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
        jLabel_TeamsClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_TeamsCloseMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_TeamsCloseMouseReleased(evt);
            }
        });

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
                "Name", "Position"
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

        jLabel_squadName.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel_squadName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_squadName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_squadName.setText("Squad");
        jLabel_squadName.setPreferredSize(new java.awt.Dimension(130, 43));

        jTextField_firstName.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_firstName.setFont(new java.awt.Font("Cambria", 0, 22)); // NOI18N
        jTextField_firstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_firstName.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        jTextField_firstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_firstNameFocusGained(evt);
            }
        });

        jTextField_lastName.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_lastName.setFont(new java.awt.Font("Cambria", 0, 22)); // NOI18N
        jTextField_lastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_lastName.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        jTextField_lastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_lastNameFocusGained(evt);
            }
        });

        jTextField_Position.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_Position.setFont(new java.awt.Font("Cambria", 0, 22)); // NOI18N
        jTextField_Position.setForeground(new java.awt.Color(51, 51, 51));
        jTextField_Position.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Position.setText("GK - CB - CM - ST - ...");
        jTextField_Position.setToolTipText("GK - CB - LB - RB - DM - CM - AM - LW - RW - ST - CF");
        jTextField_Position.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        jTextField_Position.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_PositionFocusGained(evt);
            }
        });

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
        jLabel_Position.setText("Position");

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
        jLabel1.setText("Add Player");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Choose Team");

        jComboBox1.setFocusable(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("League Squads");

        javax.swing.GroupLayout jPanel_Teams_FrameLayout = new javax.swing.GroupLayout(jPanel_Teams_Frame);
        jPanel_Teams_Frame.setLayout(jPanel_Teams_FrameLayout);
        jPanel_Teams_FrameLayout.setHorizontalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 69, Short.MAX_VALUE))))
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(jTextField_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)))
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_squadName, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGap(152, 849, Short.MAX_VALUE)
                .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );
        jPanel_Teams_FrameLayout.setVerticalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_squadName, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Add)))
                .addGap(39, 39, 39)
                .addComponent(jButton_Delete)
                .addGap(50, 50, 50))
        );

        jTextField_Position.getAccessibleContext().setAccessibleDescription("");
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
    else if(reply == JOptionPane.YES_OPTION)  
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);
            int row = jTableSquad.getSelectedRow();     // For Selected Row in the table
            // Variable containing Player ID to be deleted ...
            int playerID = 0;
            // Getting the name of the Player to be deleted from the DataBase & Table ...
            String playerName = jTableSquad.getValueAt(row, 0).toString();
            // Getting the corresponding First name of the player which exists inside the table ...
            String player_firstName = playerName.substring(0, playerName.indexOf(" "));
            // SQL Statement & Query for selecting the coach ID which to be DELETED from COACH Table.
            Statement stmt=con.createStatement();  
            int rs=stmt.executeUpdate("DELETE from player where FIRSTNAME =" + "'"+player_firstName+"'");
            con.close();
            // Updating & Showing the Teams Table Again ...
        try{
            updateٍSquadTable();
            }
            catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            _leaguesList = new Object[][]{};
            }
        // Restarting the Team_Frame JFrame ...
            this.dispose();
            try{
            Thread.sleep(250);
            new Squad_Frame(jLabel3.getText(), ThiscurrentLeagueID).show();
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
        String temp_Position = jTextField_Position.getText();
        DataEntryChecking t1 = new DataEntryChecking();
        if(t1.containsNumbers(temp_firstName)||t1.containsNumbers(temp_lastName)){
            JOptionPane.showMessageDialog(this,"Player name cannot contain numbers");
            return;
        }
        // Checking For Wrong Player First Name Entry
        if(!(t1.isValid_firstName(temp_firstName)))
        {
            JOptionPane.showMessageDialog(this,"Invalid First Name", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            jTextField_lastName.setForeground(new Color(51,51,51));
            jTextField_Position.setForeground(new Color(51,51,51));
            // Wrong Entry Here
            jTextField_firstName.setForeground(Color.red);
            jTextField_firstName.setText("NAME!");
            return;
        }
        // Checking For Wrong Player Last Name Entry
        if(!(t1.isValid_Name(temp_lastName)))
        {
            JOptionPane.showMessageDialog(this,"Invalid Last Name", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            jTextField_firstName.setForeground(new Color(51,51,51));
            jTextField_Position.setForeground(new Color(51,51,51));
            // Wrong Entry Here
            jTextField_lastName.setForeground(Color.red);
            jTextField_lastName.setText("NAME!");
            return;
        }
        // Checking For Wrong Player Position Entry
        if(!(t1.isValid_Position(temp_Position)))
        {
            JOptionPane.showMessageDialog(this,"Invalid Position ... Please Choose a valid Position (Check Tooltip)", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            jTextField_firstName.setForeground(new Color(51,51,51));
            jTextField_lastName.setForeground(new Color(51,51,51));
            // Wrong Entry Here
            jTextField_Position.setForeground(Color.red);
            jTextField_Position.setText("CHECK TOOLTIP");
            return;
        }
        try{
            addNewPlayer(temp_firstName ,temp_lastName,temp_Position  );
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
            System.out.println("is it here?");
            return ;
        }
        this.dispose();
        try{
        Thread.sleep(250);
        new Squad_Frame(jLabel3.getText(), ThiscurrentLeagueID).show();
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton_AddActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int idIndex = jComboBox1.getSelectedIndex();
        currentTeamID = Integer.parseInt(teamsList[idIndex][2].toString()) ;
        try{
            jLabel_squadName.setText(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()) + " Squad");
            updateٍSquadTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField_firstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_firstNameFocusGained
        jTextField_firstName.setText("");
    }//GEN-LAST:event_jTextField_firstNameFocusGained

    private void jTextField_lastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_lastNameFocusGained
        jTextField_lastName.setText("");
    }//GEN-LAST:event_jTextField_lastNameFocusGained

    private void jTextField_PositionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_PositionFocusGained
        jTextField_Position.setText("");
    }//GEN-LAST:event_jTextField_PositionFocusGained

    private void jLabel_TeamsCloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TeamsCloseMouseReleased
        this.dispose();
    }//GEN-LAST:event_jLabel_TeamsCloseMouseReleased

    private void jLabel_TeamsCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TeamsCloseMousePressed
      this.dispose();
    }//GEN-LAST:event_jLabel_TeamsCloseMousePressed

   
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
      
        // jComboBox1.removeAllItems();
        for (int i = 0 ;_testData[i][0] != null  ; i++ ){
            jComboBox1.addItem(_testData[i][0].toString());
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
    
    
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_Position;
    private javax.swing.JLabel jLabel_TeamsClose;
    private javax.swing.JLabel jLabel_firstName;
    private javax.swing.JLabel jLabel_lastName;
    private javax.swing.JLabel jLabel_squadName;
    private javax.swing.JPanel jPanel_TeamsClose;
    private javax.swing.JPanel jPanel_Teams_Frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSquad;
    private javax.swing.JTextField jTextField_Position;
    private javax.swing.JTextField jTextField_firstName;
    private javax.swing.JTextField jTextField_lastName;
    // End of variables declaration//GEN-END:variables
}
