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
// import Functions.SharedData;

/**
 *
 * @author hosam
 */
public class Stadium_Frame extends javax.swing.JFrame {

    //******************** Declaration of Variables, to be used for the Entire Class ********************//
    Object[][] stadiumList ;
    Object[][] teamsList;
    private int ThiscurrentLeagueID;
    private int currentTeamID = 0;
    //*****************************************************************//
    
    /**
     * Creates new form Teams_Frame
     */
    
              //******************** Teams Table Renderer ********************//
        // Necessary For alligning Rows
    DefaultTableCellRenderer tblTeamsRenderer = new DefaultTableCellRenderer();
    

    
    public Stadium_Frame(String currentLeague_Name, int currentLeagueID) {
        initComponents();
        this.setLocationRelativeTo(null);
        ThiscurrentLeagueID = currentLeagueID;
        // Calling the rows center aligning function ...
        // Aligning Rows to the center ...
        setTableCellAlignment(SwingConstants.CENTER);
        try{
             updateٍStadiumTable();
             buildTeamsComboBoxData();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            stadiumList = new Object[][]{};
        }
        
        jLabel3.setText(currentLeague_Name);
        setTableCellAlignment(SwingConstants.CENTER);
        //******************** League Table Model ********************//
        //******************** Teams Table Properties ********************//
        jTableTeams.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableTeams.setOpaque(false);
        jTableTeams.getTableHeader().setBackground(new Color(51,85,175));
        jTableTeams.getTableHeader().setForeground(new Color(255,255,255));
        jTableTeams.setBackground(new Color(244, 244, 244));
        // Setting Colmuns Width
        jTableTeams.getColumnModel().getColumn(0).setPreferredWidth(85);
        jTableTeams.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTableTeams.getColumnModel().getColumn(2).setPreferredWidth(10);
        //*****************************************************************//
    }

    private void updateٍStadiumTable() throws Exception{
       DefaultTableModel tblSquadModel = (DefaultTableModel)jTableTeams.getModel();
        try{
           stadiumList = getStadium();
        }catch(Exception e){
          throw e ;
        }     
        // Fixed Bug (Duplicate Table Data Showing)
        for (int i = tblSquadModel.getRowCount() - 1 ;i >= 0 ; i-- ){
            tblSquadModel.removeRow(i);
        }
        
        for(int i=0; stadiumList[i][0] != null; i++)
            tblSquadModel.addRow(stadiumList[i]);
   }
   
   private Object[][] getStadium() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            // Selecting Only Stadiums That Belong to teams in this particular chosen League (Based on LeagueID) ...
            ResultSet rs=stmt.executeQuery("SELECT * FROM stadium WHERE TEAMID IN (SELECT ID FROM TEAM WHERE LEAGUEID = "+ ThiscurrentLeagueID +")");  
            stadiumList = new Object[1000][5];
            int index = 0 ;
            while(rs.next()) { 
                stadiumList[index][4] = rs.getInt("id");
                stadiumList[index][0] = rs.getString("name");
                stadiumList[index][1] = rs.getString("city");
                stadiumList[index][2] = rs.getInt("capacity");
                Statement stmt2=con.createStatement();  
                ResultSet rs2=stmt2.executeQuery("select name from team where id =" +rs.getInt("teamid")); 
                while(rs2.next()){
                    stadiumList[index][3] = rs2.getString("name");
                }
                index ++ ;
             }
            
            con.close(); 
            return stadiumList;
        }catch(SQLException e){ 
                System.out.println(e);
                throw e;
        }  
   }
   

     private void addNewStadium(String name, String city, int capacity) throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT MAX(ID) FROM stadium");  
            int id =  0;
            while(rs.next()) {
               id = rs.getInt(1) + 1 ;
            }
           
            int insertingResult =stmt.executeUpdate("insert into stadium values("+currentTeamID+" ," +id+ ",'" +name+ "', '" +city+"',"+capacity+
                     ")"  );  
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
        jTableTeams = new javax.swing.JTable(){

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
        jLabel_leagueName = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jTextField_capacity = new javax.swing.JTextField();
        jTextField_city = new javax.swing.JTextField();
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

        jTableTeams.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "City", "Capacity", "Team"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jLabel_leagueName.setText("Stadiums");
        jLabel_leagueName.setPreferredSize(new java.awt.Dimension(130, 43));

        jTextField_name.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_name.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jTextField_capacity.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_capacity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_capacity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_capacity.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        jTextField_capacity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_capacityActionPerformed(evt);
            }
        });

        jTextField_city.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_city.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_city.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_city.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jLabel_firstName.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName.setText("Name");

        jLabel_lastName.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_lastName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_lastName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_lastName.setText("Capacity");

        jLabel_Position.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_Position.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_Position.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Position.setText("City");

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
        jLabel1.setText("Stadium");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Choose Team");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("League Stadiums");

        javax.swing.GroupLayout jPanel_Teams_FrameLayout = new javax.swing.GroupLayout(jPanel_Teams_Frame);
        jPanel_Teams_Frame.setLayout(jPanel_Teams_FrameLayout);
        jPanel_Teams_FrameLayout.setHorizontalGroup(
            jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(478, 478, 478)
                .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                                        .addComponent(jLabel_lastName, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                                        .addComponent(jLabel_Position, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField_city, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                                        .addComponent(jLabel_firstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(169, 169, 169)))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(153, 153, 153))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)))))
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_leagueName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235))))
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
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_leagueName, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Delete))
                    .addGroup(jPanel_Teams_FrameLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel_Teams_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_city, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Position, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addComponent(jButton_Add)))
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
    
    int reply = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
    if(reply == JOptionPane.NO_OPTION)
        return;
    else if(reply == JOptionPane.YES_OPTION)  
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                Config.username,Config.password);
            Statement stmt=con.createStatement(); 
            Statement stmt2=con.createStatement(); 
            Statement stmt3=con.createStatement(); 

            int stadiumID = (int) stadiumList[jTableTeams.getSelectedRow()][4];
            int rs1=stmt.executeUpdate("DELETE from play where matchid = (select id from game where stadiumid =" + stadiumID + ")");
            System.out.println(rs1);
            int rs2=stmt2.executeUpdate("DELETE from game where stadiumid =" + stadiumID );
            int rs3=stmt3.executeUpdate("DELETE from stadium where  id =" + stadiumID );

            con.close();
        try{
            updateٍStadiumTable();
            }
            catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            stadiumList = new Object[][]{};
            }
        // Restarting the Team_Frame JFrame ...
            this.dispose();
            try{
            Thread.sleep(250);
            new Stadium_Frame(jLabel3.getText(), ThiscurrentLeagueID).show();
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){ 
                JOptionPane.showMessageDialog(rootPane, "An Error Ocurred");
                System.out.println("Error In Delete Stadium Function");
        }         
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        String temp_firstName;
        int temp_capacity ;
        String temp_city  ;
        DataEntryChecking t1 = new DataEntryChecking();
        
        try{
            temp_firstName = jTextField_name.getText();
            temp_capacity = Integer.parseInt(jTextField_capacity.getText());
            temp_city = jTextField_city.getText();
       
         } catch(Exception e){
             JOptionPane.showMessageDialog(this, "insert valid numbers");
             return ;
        }
        
        // Checking For Wrong Team Name Entry
        if(!(t1.isValid_Name(temp_firstName) ||  t1.isValid_Name(temp_city)))
        {
            JOptionPane.showMessageDialog(this,"Invalid Name", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!(t1.isValid_number(temp_capacity)))
        {
            JOptionPane.showMessageDialog(this,"Invalid capacity", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
          int x=  Integer.parseInt(jTextField_name.getText());
          JOptionPane.showMessageDialog(this,"Stadium name is letters", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
          return;
        }catch(Exception e){
            
        }
       
        try{
          int x=  Integer.parseInt(jTextField_city.getText());
          JOptionPane.showMessageDialog(this,"city name is letters", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
          return;
        }catch(Exception e){
            
        }
        
        try{
            addNewStadium(temp_firstName,temp_city,temp_capacity  );
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "An Error Ocurred");
            System.out.println("is it here?");
            return ;
        }
        this.dispose();
        try{
        Thread.sleep(250);
        new Stadium_Frame(jLabel3.getText(), ThiscurrentLeagueID).show();
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
            updateٍStadiumTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "An Error Ocurred");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField_capacityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_capacityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_capacityActionPerformed

   
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
            ResultSet rs=stmt.executeQuery("select * from team where leagueid=" + ThiscurrentLeagueID);  
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
    private javax.swing.JLabel jLabel_leagueName;
    private javax.swing.JPanel jPanel_TeamsClose;
    private javax.swing.JPanel jPanel_Teams_Frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTeams;
    private javax.swing.JTextField jTextField_capacity;
    private javax.swing.JTextField jTextField_city;
    private javax.swing.JTextField jTextField_name;
    // End of variables declaration//GEN-END:variables
}
