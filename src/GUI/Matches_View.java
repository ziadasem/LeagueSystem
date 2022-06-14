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
public class Matches_View extends javax.swing.JFrame {

  
    Object[][] _matchsList ;
    private int currentLeagueID;
    String currentLeague_Name;
    Object[][] teamsList;
    Object[][] stadiumList;

 
    DefaultTableCellRenderer tblTeamsRenderer = new DefaultTableCellRenderer();
    

    
    public Matches_View(String currentLeague_Name, int currentLeagueID) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.currentLeagueID = currentLeagueID;
        this.currentLeague_Name = currentLeague_Name;
        
        setTableCellAlignment(SwingConstants.CENTER);
        try{
          updateTeamsTable();
          updateMatchesTable();
          buildTeamsComboBoxData();
          buildStadiumComboBoxData(-1); //inital id is -1 , to get all stadiums
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            _matchsList = new Object[][]{};
        }
        
            // Setting the label name for corresponding League Name
        jLabel_leagueName_Matches.setText(currentLeague_Name + " Matches");
        
        
        
        //******************** League Table Properties ********************//
        jTableTeams.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableTeams.setOpaque(false);
        jTableTeams.getTableHeader().setBackground(new Color(63, 16, 82));
        jTableTeams.getTableHeader().setForeground(new Color(255,255,255));
        jTableTeams.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTableTeams.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
        
        //******************** League Matches Properties ********************//
        jTableMatches.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableMatches.setOpaque(false);
        // Setting Colmuns Width
        jTableMatches.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTableMatches.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableMatches.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        jTableMatches.getTableHeader().setBackground(new Color(63, 16, 82));
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
        jLabel_leagueName_Matches = new javax.swing.JLabel();
        jLabel_homeTeam = new javax.swing.JLabel();
        jLabel_awayTeam = new javax.swing.JLabel();
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
        jLabel_awayTeam1 = new javax.swing.JLabel();
        stadium_combo_box = new javax.swing.JComboBox<>();
        away_team_combo_box = new javax.swing.JComboBox<>();
        home_team_combo_box = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1286, 720));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));
        setType(java.awt.Window.Type.POPUP);

        jPanel_Matches_Frame.setBackground(new java.awt.Color(0, 51, 51));
        jPanel_Matches_Frame.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(109, 28, 145), new java.awt.Color(109, 28, 145)));
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
                "Name", "Coach Name"
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
        jTableTeams.getTableHeader().setResizingAllowed(false);
        jTableTeams.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableTeams);

        jLabel_leagueName_Matches.setFont(new java.awt.Font("Cambria", 1, 38)); // NOI18N
        jLabel_leagueName_Matches.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_leagueName_Matches.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_leagueName_Matches.setText("League");
        jLabel_leagueName_Matches.setPreferredSize(new java.awt.Dimension(130, 43));

        jLabel_homeTeam.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_homeTeam.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_homeTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_homeTeam.setText("Team 1");

        jLabel_awayTeam.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_awayTeam.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_awayTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_awayTeam.setText("Team 2");

        jScrollPane2.setBackground(new java.awt.Color(63, 16, 82));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(109, 28, 145), new java.awt.Color(109, 28, 145), new java.awt.Color(109, 28, 145), new java.awt.Color(109, 28, 145)));
        jScrollPane2.setToolTipText("");
        jScrollPane2.setMinimumSize(new java.awt.Dimension(1024, 720));
        jScrollPane2.setOpaque(false);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1024, 720));

        jTableMatches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Team 1", "Team 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        jLabel_awayTeam1.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_awayTeam1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_awayTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_awayTeam1.setText("stadium");

        stadium_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stadium_combo_boxActionPerformed(evt);
            }
        });

        away_team_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                away_team_combo_boxActionPerformed(evt);
            }
        });

        home_team_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_team_combo_boxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Matches_FrameLayout = new javax.swing.GroupLayout(jPanel_Matches_Frame);
        jPanel_Matches_Frame.setLayout(jPanel_Matches_FrameLayout);
        jPanel_Matches_FrameLayout.setHorizontalGroup(
            jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_homeTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_awayTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(away_team_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(home_team_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addComponent(jLabel_awayTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(stadium_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_leagueName_Matches, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGap(0, 661, Short.MAX_VALUE)
                        .addComponent(jPanel_MatchesClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
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
                .addGap(27, 99, Short.MAX_VALUE))
            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(away_team_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_homeTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(home_team_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_awayTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stadium_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_awayTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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

    private void home_team_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_team_combo_boxActionPerformed
        try{
            int homeTeamID = (int) teamsList[home_team_combo_box.getSelectedIndex()][2];
            buildStadiumComboBoxData(homeTeamID);
            System.out.println("called");
        }catch(Exception e){
            System.out.println("can't get stadium + \n" + e.toString());
            JOptionPane.showMessageDialog(rootPane, "can't get stadium + \n" + e.toString());
        }

    }//GEN-LAST:event_home_team_combo_boxActionPerformed

    private void away_team_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_away_team_combo_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_away_team_combo_boxActionPerformed

    private void stadium_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stadium_combo_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stadium_combo_boxActionPerformed

    /**
     * @param args the command line arguments
     */
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
    
     
    private void addNewGame(String week, String Time, int homeTeamID, int awayTeamID, int stadiumID) throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            Statement insertionToGameStatment=con.createStatement();  
            Statement insertionToPlayStatment1=con.createStatement();  
            Statement insertionToPlayStatment2=con.createStatement();  

            ResultSet rs=stmt.executeQuery("SELECT MAX(ID) FROM GAME");  
            int id =  0;
            while(rs.next()) {
               id = rs.getInt(1) + 1 ;
            }
            
            insertionToGameStatment.executeUpdate("INSERT INTO game (id,  \"Date\", leagueid, stadiumid) VALUES"
                    + " ("+id+",'"+ Time+ " week# "+week+"',"+ currentLeagueID+","+ stadiumID+")");
            
            insertionToPlayStatment1.executeUpdate("INSERT INTO play  (Teamid, Matchid)  VALUES"
                    + " ("+homeTeamID+","+ id+")");
            
            insertionToPlayStatment2.executeUpdate("INSERT INTO play  (Teamid, Matchid)  VALUES"
                    + " ("+awayTeamID+","+ id+")");
            
            con.close(); 
        }catch(Exception e){ 
             System.out.println(e);
             System.out.println("HERE");
             throw e;
        }  
    }
            
            
       
            
            
            
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
            
            
    private Object[][] getMatches() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
             Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            Statement stmt2=con.createStatement();  
            Statement stmt3=con.createStatement();  
       

            ResultSet gamesStatement=stmt.executeQuery("select * from game where Leagueid =" +currentLeagueID);  

            Object[][] gamesList = new Object[1000][4];
            int index = 0 ;
            while(gamesStatement.next()) { 
                gamesList[index][0] = gamesStatement.getString("date");
                int matchID = gamesStatement.getInt("id");
                ResultSet teamsStamtment=stmt3.executeQuery("select name from team where id in (select teamid from play where Matchid =" +matchID +" )");
                int teamsIndex = 1 ;
                while(teamsStamtment.next()){
                     gamesList[index][teamsIndex] = teamsStamtment.getString("name");
                     teamsIndex++ ;
                }
               gamesList[index][3] = gamesStatement.getInt("id");
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
    
    
    public void buildTeamsComboBoxData() throws Exception{
        Object[][] _testData ;
        try{
           _testData = getTeams();
        }catch(Exception e){
          throw e ;
        } 
      
        // jComboBox1.removeAllItems();
        for (int i = 0 ;_testData[i][0] != null  ; i++ ){
            home_team_combo_box.addItem(_testData[i][0].toString());
            away_team_combo_box.addItem(_testData[i][0].toString());
        } 
   }
   public void updateTeamsTable() throws Exception{
       DefaultTableModel tblLeagueModel = (DefaultTableModel)jTableTeams.getModel();
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
            Statement stmt2=con.createStatement();  

            ResultSet rs=stmt.executeQuery("select * from team WHERE LEAGUEID ="+ currentLeagueID);  

            teamsList = new Object[1000][5];
            int index = 0 ;
            while(rs.next()) { 
                 teamsList[index][0] = rs.getString("name");
                 ResultSet rs2=stmt2.executeQuery("select * from coach WHERE id ="+ rs.getInt("coachid"));  
                 while(rs2.next()){
                     teamsList[index][1] = rs2.getString("firstname") + " " + rs2.getString("lastname");
                 } 
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
     
     public void buildStadiumComboBoxData(int homeID) throws Exception{
        try{
           stadiumList = getStadium(homeID);
           stadium_combo_box.removeAllItems();
           for (int i = 0 ;stadiumList[i][0] != null  ; i++ ){
            stadium_combo_box.addItem(stadiumList[i][0].toString());
        } 
        }catch(Exception e){
          throw e ;
          
        } 
  
   }
     
    
     private Object[][] getStadium(int homeTeamStadium) throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
               Config.username,Config.password);  
           
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from stadium where teamid ="+ homeTeamStadium);  
            stadiumList = new Object[1000][2];
            int index = 0 ;
            while(rs.next()) { 
                 stadiumList[index][0] = rs.getString("name");
                 stadiumList[index][1] = rs.getInt("id");
                index ++ ;
             }
            System.out.println("home stad " + homeTeamStadium + " index "+ index);
            if (index == 0){ //team has no stadium, team can play in any stadium
                Statement stmt2=con.createStatement();  
                ResultSet rs2=stmt2.executeQuery("select * from stadium"); 
                while(rs2.next()) { 
                    stadiumList[index][0] = rs2.getString("name");
                    stadiumList[index][1] = rs2.getInt("id");
                    index ++ ;
             }
            }
            con.close(); 
            return stadiumList;
        }catch(SQLException e){ 
                System.out.println(e);
                System.out.println("Error In getting Teams Function");
                throw e;
        } 
     }
        
        private void deleteMatch(){
            try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);
            int row = jTableMatches.getSelectedRow();     // For Selected Row in the table
            int gameID =(int) _matchsList[row][3];  
          
            Statement stmt=con.createStatement();  
            int rs=stmt.executeUpdate("DELETE from play where matchid =" + gameID);
            Statement stmt2=con.createStatement();  
            int rs2=stmt2.executeUpdate("DELETE from game where ID =" + gameID);
            con.close();
            // Updating & Showing the Teams Table Again ...
        try{
            updateMatchesTable();
            }
            catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error in connection to DB");
            _matchsList = new Object[][]{};
            }
        // Restarting the Team_Frame JFrame ...
            this.dispose();
            try{
            Thread.sleep(250);
            new Matches_View(jLabel_leagueName_Matches.getText(), currentLeagueID).show();
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){ 
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e.getMessage());
        }
  
   }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> away_team_combo_box;
    private javax.swing.JComboBox<String> home_team_combo_box;
    private javax.swing.JLabel jLabel_MatchesClose;
    private javax.swing.JLabel jLabel_awayTeam;
    private javax.swing.JLabel jLabel_awayTeam1;
    private javax.swing.JLabel jLabel_homeTeam;
    private javax.swing.JLabel jLabel_leagueName_Matches;
    private javax.swing.JPanel jPanel_MatchesClose;
    private javax.swing.JPanel jPanel_Matches_Frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableMatches;
    private javax.swing.JTable jTableTeams;
    private javax.swing.JComboBox<String> stadium_combo_box;
    // End of variables declaration//GEN-END:variables
}
