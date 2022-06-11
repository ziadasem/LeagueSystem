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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

// import Functions.SharedData;
/**
 *
 * @author hosam
 * ziad assem
 */
public class Home extends javax.swing.JFrame {
    
    
   //******************** Declaration of Variables, to be used for the Entire Class ********************//
   private int currentLeagueID = 0 ;
   Object[][] _leaguesList ;
   public String currentLeague_Name;
        // Used for accessing SharedData class variables ...
   // SharedData data = new SharedData();
   //*****************************************************************//

    
          //******************** League Table Renderer ********************//
        // Necessary For alligning Rows
        DefaultTableCellRenderer tblLeagueRenderer = new DefaultTableCellRenderer();
    /**
     * Creates new form Home
     */
    public Home() {
        super.setTitle("ADMIN");
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
        //jTableLeague.setOpaque(false);
        jTableLeague.getTableHeader().setBackground(new Color(63, 16, 82));
        jTableLeague.getTableHeader().setForeground(new Color(204,204,204));
        //jTableLeague.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        jButton_addMatches = new javax.swing.JButton();
        jButton_addLeague = new javax.swing.JButton();
        jButton_addTeams = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel_TeamsClose = new javax.swing.JPanel();
        jLabel_TeamsClose = new javax.swing.JLabel();
        jButton_editSquad = new javax.swing.JButton();
        jButton_addLeague1 = new javax.swing.JButton();
        jButton_standings = new javax.swing.JButton();
        jButton_addLeague2 = new javax.swing.JButton();
        jButton_addLeague3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1440, 768));
        setName("mainframe"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1440, 768));
        setSize(new java.awt.Dimension(1440, 768));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray));
        jPanel1.setMinimumSize(new java.awt.Dimension(1421, 615));
        jPanel1.setPreferredSize(new java.awt.Dimension(1421, 768));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(1024, 720));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1024, 720));

        jTableLeague.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Year"
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
        jTableLeague.setSelectionForeground(new java.awt.Color(0, 120, 215));
        jTableLeague.setShowVerticalLines(false);
        jTableLeague.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableLeague);
        if (jTableLeague.getColumnModel().getColumnCount() > 0) {
            jTableLeague.getColumnModel().getColumn(0).setResizable(false);
            jTableLeague.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTableLeague.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_leagueName.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel_leagueName.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_leagueName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_leagueName.setText("League");
        jLabel_leagueName.setPreferredSize(new java.awt.Dimension(130, 43));

        jButton_addMatches.setBackground(new java.awt.Color(51, 85, 175));
        jButton_addMatches.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_addMatches.setForeground(new java.awt.Color(240, 240, 240));
        jButton_addMatches.setText("Add Matches");
        jButton_addMatches.setToolTipText("");
        jButton_addMatches.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_addMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addMatchesActionPerformed(evt);
            }
        });

        jButton_addLeague.setBackground(new java.awt.Color(51, 85, 175));
        jButton_addLeague.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_addLeague.setForeground(new java.awt.Color(240, 240, 240));
        jButton_addLeague.setText("Add League");
        jButton_addLeague.setToolTipText("");
        jButton_addLeague.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_addLeague.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addLeagueActionPerformed(evt);
            }
        });

        jButton_addTeams.setBackground(new java.awt.Color(51, 85, 175));
        jButton_addTeams.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_addTeams.setForeground(new java.awt.Color(240, 240, 240));
        jButton_addTeams.setText("Add Teams");
        jButton_addTeams.setToolTipText("");
        jButton_addTeams.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_addTeams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addTeamsActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(63, 16, 82));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("League");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons8_soccer_ball_100px_1.png"))); // NOI18N

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
            .addComponent(jLabel_TeamsClose, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        jPanel_TeamsCloseLayout.setVerticalGroup(
            jPanel_TeamsCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_TeamsClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton_editSquad.setBackground(new java.awt.Color(51, 85, 175));
        jButton_editSquad.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_editSquad.setForeground(new java.awt.Color(240, 240, 240));
        jButton_editSquad.setText("Edit Squad");
        jButton_editSquad.setToolTipText("");
        jButton_editSquad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_editSquad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editSquadActionPerformed(evt);
            }
        });

        jButton_addLeague1.setBackground(new java.awt.Color(51, 85, 175));
        jButton_addLeague1.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_addLeague1.setForeground(new java.awt.Color(240, 240, 240));
        jButton_addLeague1.setText("Add stadium");
        jButton_addLeague1.setToolTipText("");
        jButton_addLeague1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_addLeague1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addLeague1ActionPerformed(evt);
            }
        });

        jButton_standings.setBackground(new java.awt.Color(51, 85, 175));
        jButton_standings.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_standings.setForeground(new java.awt.Color(240, 240, 240));
        jButton_standings.setText("Standings");
        jButton_standings.setToolTipText("");
        jButton_standings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_standings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_standingsActionPerformed(evt);
            }
        });

        jButton_addLeague2.setBackground(new java.awt.Color(51, 85, 175));
        jButton_addLeague2.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_addLeague2.setForeground(new java.awt.Color(240, 240, 240));
        jButton_addLeague2.setText("Add trophy");
        jButton_addLeague2.setToolTipText("");
        jButton_addLeague2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_addLeague2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addLeague2ActionPerformed(evt);
            }
        });

        jButton_addLeague3.setBackground(new java.awt.Color(51, 85, 175));
        jButton_addLeague3.setFont(new java.awt.Font("Cambria", 1, 32)); // NOI18N
        jButton_addLeague3.setForeground(new java.awt.Color(240, 240, 240));
        jButton_addLeague3.setText("Assign trophy");
        jButton_addLeague3.setToolTipText("");
        jButton_addLeague3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_addLeague3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addLeague3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_leagueName, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(134, 134, 134)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_editSquad, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_addLeague1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_standings, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_addLeague2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_addLeague3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_addTeams, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jButton_addMatches, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jButton_addLeague, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(48, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel_leagueName, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_TeamsClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_addMatches)
                            .addComponent(jButton_addLeague)
                            .addComponent(jButton_addTeams))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton_editSquad)
                        .addGap(53, 53, 53)
                        .addComponent(jButton_addLeague1)
                        .addGap(54, 54, 54)
                        .addComponent(jButton_standings)
                        .addGap(39, 39, 39)
                        .addComponent(jButton_addLeague2)
                        .addGap(37, 37, 37)
                        .addComponent(jButton_addLeague3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(156, Short.MAX_VALUE))
        );

        // Removing inner borders inside the button
        jButton_addMatches.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_addLeague.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_addTeams.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_editSquad.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_addLeague.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_addLeague.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_addLeague.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_addLeague.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_addMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addMatchesActionPerformed
        new Matches_Frame(currentLeague_Name, currentLeagueID).show();
    }//GEN-LAST:event_jButton_addMatchesActionPerformed

    private void jButton_addLeagueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addLeagueActionPerformed
        new AddNewLeague().show();
    }//GEN-LAST:event_jButton_addLeagueActionPerformed

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

    private void jButton_addTeamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addTeamsActionPerformed
        new Teams_Frame(currentLeague_Name, currentLeagueID).show();
    }//GEN-LAST:event_jButton_addTeamsActionPerformed

    private void jPanel_TeamsCloseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_TeamsCloseMouseReleased
        this.dispose();
    }//GEN-LAST:event_jPanel_TeamsCloseMouseReleased

    private void jButton_editSquadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editSquadActionPerformed
        new Squad_Frame(currentLeague_Name, currentLeagueID).show();
    }//GEN-LAST:event_jButton_editSquadActionPerformed

    private void jButton_addLeague1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addLeague1ActionPerformed
       new Stadium_Frame(currentLeague_Name, currentLeagueID).show();
    }//GEN-LAST:event_jButton_addLeague1ActionPerformed

    private void jButton_standingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_standingsActionPerformed
      new StandingsFrame(currentLeague_Name, currentLeagueID).show();
    }//GEN-LAST:event_jButton_standingsActionPerformed

    private void jButton_addLeague2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addLeague2ActionPerformed
      new Trophies_Frame(currentLeague_Name, currentLeagueID).show();
    }//GEN-LAST:event_jButton_addLeague2ActionPerformed

    private void jButton_addLeague3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addLeague3ActionPerformed
      new Team_Trophies_Frame(currentLeague_Name, currentLeagueID).show();
    }//GEN-LAST:event_jButton_addLeague3ActionPerformed

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
    private javax.swing.JButton jButton_addLeague;
    private javax.swing.JButton jButton_addLeague1;
    private javax.swing.JButton jButton_addLeague2;
    private javax.swing.JButton jButton_addLeague3;
    private javax.swing.JButton jButton_addMatches;
    private javax.swing.JButton jButton_addTeams;
    private javax.swing.JButton jButton_editSquad;
    private javax.swing.JButton jButton_standings;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_TeamsClose;
    private javax.swing.JLabel jLabel_leagueName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_TeamsClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLeague;
    // End of variables declaration//GEN-END:variables
}
