/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Functions.Config;
import Functions.DataEntryChecking;
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
 * @author Ramy Mohamed
 */
public class StandingsFrame extends javax.swing.JFrame {

    
    Object[][] _leaguesList ;
    Object[][] standingList ;
    Object[][] squadList ;
    Object[][] teamsList;
    private int currentTeamID = 0;
    private int ThiscurrentLeagueID;
   private String currentLeague_Name;
    DefaultTableCellRenderer tblLeagueRenderer = new DefaultTableCellRenderer();
    
    public StandingsFrame(String currentLeague_Name, int currentLeagueID) {
        initComponents();
        ThiscurrentLeagueID = currentLeagueID;
         this.setLocationRelativeTo(null);
        this.currentLeague_Name = currentLeague_Name;
        try{
             updateٍStandingTable();
             buildTeamsComboBoxData();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB "  +e.getMessage()); 
        }
        
        jLabel3.setText(currentLeague_Name);
        setTableCellAlignment(SwingConstants.CENTER);
        //******************** League Table Model ********************//
        //******************** Teams Table Properties ********************//
        jTableLeague.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableLeague.setOpaque(false);
        jTableLeague.getTableHeader().setBackground(new Color(51,85,175));
        jTableLeague.getTableHeader().setForeground(new Color(255,255,255));
        jTableLeague.setBackground(new Color(244, 244, 244));
        // Setting Colmuns Width
        jTableLeague.getColumnModel().getColumn(0).setPreferredWidth(3);
        jTableLeague.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTableLeague.getColumnModel().getColumn(2).setPreferredWidth(10);
        jTableLeague.getColumnModel().getColumn(3).setPreferredWidth(3);
        jTableLeague.getColumnModel().getColumn(4).setPreferredWidth(3);
        jTableLeague.getColumnModel().getColumn(5).setPreferredWidth(3);
        jTableLeague.getColumnModel().getColumn(2).setPreferredWidth(10);
        //*****************************************************************//


    }
     private Object[][] getStanding() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            Statement stmt2=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from standings where leagueid =" +ThiscurrentLeagueID + " order by points desc, plays asc");  
            
           standingList = new Object[1000][9];
            int index = 0 ;
            while(rs.next()) { 
                //getting team
                ResultSet rs2=stmt2.executeQuery("select name from team where id =" + rs.getInt("teamid"));  
                String _teamName  = "N/A";
                while(rs2.next()){
                    _teamName = rs2.getString("name");
                }
                
                standingList[index][0] = index+1;
                standingList[index][1] = _teamName;
                standingList[index][2] = rs.getInt("plays");
                standingList[index][3] = rs.getInt("wins");
                standingList[index][4] = rs.getInt("draws");
                standingList[index][5] = rs.getInt("losses");
                standingList[index][6] = rs.getInt("points");
                
                standingList[index][7] = rs.getInt("teamid");
                standingList[index][8] = ThiscurrentLeagueID ;

                index ++ ;
             }
            con.close(); 
            return standingList;
        }catch(SQLException e){ 
                System.out.println(e.getMessage());
                throw e;
        }  
        }  
   
     private void updateٍStandingTable() throws Exception{
       DefaultTableModel tblSquadModel = (DefaultTableModel)jTableLeague.getModel();
        try{
           standingList = getStanding();
        }catch(Exception e){
          throw e ;
        }     
        // Fixed Bug (Duplicate Table Data Showing)
        for (int i = tblSquadModel.getRowCount() - 1 ;i >= 0 ; i-- ){
            tblSquadModel.removeRow(i);
        }
        
        for(int i=0; standingList[i][0] != null; i++)
            tblSquadModel.addRow(standingList[i]);
   }
    private void addNewStanding(int MP, int wins, int draws, int losses, boolean isAdd) throws Exception{
        int pts = wins*3 + draws;
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            if (isAdd){
             int insertingResult =stmt.executeUpdate("insert into STANDINGS values("+ThiscurrentLeagueID+" ,"+currentTeamID+" ,"+MP+" ," +wins+ "," +draws+ ", " +losses+","+pts+")"  );  
            }else{
              int insertingResult =stmt.executeUpdate( "update standings set leagueid = " + ThiscurrentLeagueID + ", teamid = "+currentTeamID + ", plays = " + MP
                      +", wins = "+wins + ",draws=" +draws+ ", losses = " + losses + ",points =" + pts + "where leagueid=" +ThiscurrentLeagueID+"and teamid = " + currentTeamID );                 
            }
            con.close(); 
        }catch(Exception e){ 
             System.out.println(e);
             throw e;
        }  
    }
    public void deleteStanding(){
     try{
            Connection con=DriverManager.getConnection( Config.hostName,
                Config.username,Config.password);
            int row = jTableLeague.getSelectedRow();     // For Selected Row in the table
            int teamID = (int)  standingList[row][7] ;
            int leagueID =  (int)  standingList[row][8] ;

            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate("DELETE from standings where leagueid =" + leagueID + "and teamID = "+ teamID);
            con.close();
            // Updating & Showing the Teams Table Again ...
            try{
                updateٍStandingTable();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
                standingList = new Object[][]{};
            }
            // Restarting the Team_Frame JFrame ...
            this.dispose();
            try{
                Thread.sleep(250);
                new StandingsFrame(currentLeague_Name, currentTeamID).show();
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

   public String isValidMatches(int mp, int w, int d , int l){
       if (mp == (w + d + l)){
           return "done";
       }
       
       if (mp < (w + d + l) ){
           return "matchs is less than wins,draws and losses";
       }
      
       if (mp > (w + d + l) ){
           return "matchs is more than wins,draws and losses";
       }
       
       return "done";
   }
   
   public void addOrUpdateMatch(boolean isAdd){
       int matches_played;
        int wins ;
        int draws ;
        int losses  ;
        try{
            matches_played = Integer.parseInt(jTextField_MP.getText());
            wins = Integer.parseInt(jTextField_wins.getText()); 
            draws = Integer.parseInt(jTextField_draws.getText());
            losses = Integer.parseInt(jTextField_losses.getText());
            //int pts = Integer.parseInt(jTextField_pts.getText());
         }catch(Exception e){
             JOptionPane.showMessageDialog(this, "enter valid numbers");
             return ;
         }
        DataEntryChecking t1 = new DataEntryChecking();
        // Checking For Wrong Team Name Entry
        if(!(t1.isValid_number(matches_played)))
        {
            JOptionPane.showMessageDialog(this,"Matches can't be negative", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!(t1.isValid_number(wins)))
        {
            JOptionPane.showMessageDialog(this,"wins can't be negative", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!(t1.isValid_number(draws)))
        {
            JOptionPane.showMessageDialog(this,"draws can't be negative", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!(t1.isValid_number(losses)))
        {
            JOptionPane.showMessageDialog(this,"loses can't be negative", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        String errorMsg = isValidMatches(matches_played, wins , draws , losses) ;
        
        if( !errorMsg.equals("done"))
        {
            JOptionPane.showMessageDialog(this,errorMsg, "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
           addNewStanding(matches_played,wins,draws,losses, isAdd);                    
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
            return ;
        }
        this.dispose();
        try{
        Thread.sleep(250);
        new StandingsFrame(jLabel3.getText(), ThiscurrentLeagueID).show();
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_TeamsClose = new javax.swing.JLabel();
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
        jLabel3 = new javax.swing.JLabel();
        jButton_Add = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jLabel_firstName = new javax.swing.JLabel();
        jLabel_firstName1 = new javax.swing.JLabel();
        jLabel_firstName2 = new javax.swing.JLabel();
        jLabel_firstName3 = new javax.swing.JLabel();
        jLabel_firstName5 = new javax.swing.JLabel();
        jTextField_MP = new javax.swing.JTextField();
        jTextField_wins = new javax.swing.JTextField();
        jTextField_draws = new javax.swing.JTextField();
        jTextField_losses = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton_modify = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175), new java.awt.Color(51, 85, 175)));

        jLabel_TeamsClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_TeamsClose.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_TeamsClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TeamsClose.setText("X");
        jLabel_TeamsClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_TeamsClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_TeamsCloseMouseReleased(evt);
            }
        });

        jScrollPane1.setMinimumSize(new java.awt.Dimension(1024, 720));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1024, 720));

        jTableLeague.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pos", "Name", "MP", "W", "D", "L", "Pts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jTableLeague.setSelectionForeground(new java.awt.Color(0, 120, 215));
        jTableLeague.setShowVerticalLines(false);
        jTableLeague.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableLeague);

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("League Standings");

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

        jButton_delete.setBackground(new java.awt.Color(51, 85, 175));
        jButton_delete.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_delete.setForeground(new java.awt.Color(240, 240, 240));
        jButton_delete.setText("delete");
        jButton_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jLabel_firstName.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName.setText("Losses");

        jLabel_firstName1.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName1.setText("Wins");

        jLabel_firstName2.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName2.setText("Draws");

        jLabel_firstName3.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName3.setText("Matches Played");

        jLabel_firstName5.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_firstName5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_firstName5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_firstName5.setText("Name");

        jTextField_MP.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_MP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_MP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_MP.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jTextField_wins.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_wins.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_wins.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_wins.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jTextField_draws.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_draws.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_draws.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_draws.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jTextField_losses.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_losses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_losses.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_losses.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton_modify.setBackground(new java.awt.Color(51, 85, 175));
        jButton_modify.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_modify.setForeground(new java.awt.Color(240, 240, 240));
        jButton_modify.setText("Modify");
        jButton_modify.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_modify.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_firstName5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_firstName3)
                            .addComponent(jLabel_firstName1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_firstName2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_draws, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(jTextField_wins, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(jTextField_losses, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_MP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(617, 617, 617)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(jLabel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton_modify, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_firstName5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_firstName3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_MP, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_wins, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_firstName1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_draws, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_firstName2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_losses, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Add)
                    .addComponent(jButton_modify))
                .addGap(56, 56, 56))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_TeamsClose)))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_delete)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        // Removing inner borders inside the button
        jButton_Add.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_Add.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_Add.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        addOrUpdateMatch(true);
        
    }//GEN-LAST:event_jButton_AddActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
       deleteStanding();
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int idIndex = jComboBox1.getSelectedIndex();
        currentTeamID = Integer.parseInt(teamsList[idIndex][2].toString()) ;
        try{
            //jLabel_squadName.setText(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()) + " Squad");
            //updateٍSquadTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modifyActionPerformed
                addOrUpdateMatch(false);
    }//GEN-LAST:event_jButton_modifyActionPerformed

    private void jLabel_TeamsCloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TeamsCloseMouseReleased
        this.dispose();
    }//GEN-LAST:event_jLabel_TeamsCloseMouseReleased

   private void setTableCellAlignment(int alignment) {
             tblLeagueRenderer.setHorizontalAlignment(alignment);
             for (int i=0; i<jTableLeague.getColumnCount();i++){
                jTableLeague.setDefaultRenderer(jTableLeague.getColumnClass(i),tblLeagueRenderer);
                }
            // repaint to show table cell changes
            jTableLeague.updateUI();
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_modify;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_TeamsClose;
    private javax.swing.JLabel jLabel_firstName;
    private javax.swing.JLabel jLabel_firstName1;
    private javax.swing.JLabel jLabel_firstName2;
    private javax.swing.JLabel jLabel_firstName3;
    private javax.swing.JLabel jLabel_firstName5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLeague;
    private javax.swing.JTextField jTextField_MP;
    private javax.swing.JTextField jTextField_draws;
    private javax.swing.JTextField jTextField_losses;
    private javax.swing.JTextField jTextField_wins;
    // End of variables declaration//GEN-END:variables
}
