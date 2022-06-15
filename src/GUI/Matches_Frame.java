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
public class Matches_Frame extends javax.swing.JFrame {

  
    Object[][] _matchsList ;
    private int currentLeagueID;
    String currentLeague_Name;
    Object[][] teamsList;
    Object[][] stadiumList;
    private int weekID ;
    
 
    DefaultTableCellRenderer tblTeamsRenderer = new DefaultTableCellRenderer();
    

    
    public Matches_Frame(String currentLeague_Name, int currentLeagueID ) {
        initComponents();
        jRadioButton1.setForeground(new Color(222,222,222));
        jRadioButton2.setForeground(new Color(222,222,222));
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
            JOptionPane.showMessageDialog(rootPane, "An Error Ocurred");
            _matchsList = new Object[][]{};
        }
        
            // Setting the label name for corresponding League Name
        jLabel_leagueName_Matches.setText(currentLeague_Name + " Matches");
        
        
        
        //******************** Team/Coach Table Properties ********************//
        jTableTeams.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableTeams.setOpaque(false);
        jTableTeams.getTableHeader().setBackground(new Color(51,85,175));
        jTableTeams.getTableHeader().setForeground(new Color(255,255,255));
        jTableTeams.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTableTeams.setBackground(new Color(244, 244, 244));
        jTableTeams.setCellSelectionEnabled(false);
        //*****************************************************************//
        
        //******************** League Matches Properties ********************//
        jTableMatches.getTableHeader().setFont(new Font("League", Font.BOLD,22));
        jTableMatches.setOpaque(false);
        // Setting Colmuns Width
        jTableMatches.getColumnModel().getColumn(0).setPreferredWidth(75);
        jTableMatches.getColumnModel().getColumn(1).setPreferredWidth(75);
        jTableMatches.getColumnModel().getColumn(2).setPreferredWidth(75);
        
        jTableMatches.getTableHeader().setBackground(new Color(51,85,175));
        jTableMatches.getTableHeader().setForeground(new Color(255,255,255));
        jTableMatches.setBackground(new Color(244, 244, 244));
        //*****************************************************************//
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
                return c;
            }
        };
        jLabel_leagueName_Matches = new javax.swing.JLabel();
        jTextField_Week = new javax.swing.JTextField();
        jTextField_Time = new javax.swing.JTextField();
        jLabel_Week = new javax.swing.JLabel();
        jLabel_Time = new javax.swing.JLabel();
        jLabel_homeTeam = new javax.swing.JLabel();
        jButton_Delete = new javax.swing.JButton();
        jButton_Add = new javax.swing.JButton();
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
                return c;
            }
        };
        jLabel_awayTeam1 = new javax.swing.JLabel();
        stadium_combo_box = new javax.swing.JComboBox<>();
        away_team_combo_box = new javax.swing.JComboBox<>();
        home_team_combo_box = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1286, 720));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));
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

        jTextField_Time.setBackground(new java.awt.Color(209, 204, 192));
        jTextField_Time.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        jTextField_Time.setForeground(new java.awt.Color(51, 51, 51));
        jTextField_Time.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Time.setText("00:00!");
        jTextField_Time.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        jTextField_Time.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_TimeFocusGained(evt);
            }
        });

        jLabel_Week.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_Week.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_Week.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Week.setText("Week");

        jLabel_Time.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_Time.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Time.setText("Time");

        jLabel_homeTeam.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_homeTeam.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_homeTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_homeTeam.setText("Team 1");

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

        jLabel_awayTeam.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_awayTeam.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_awayTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_awayTeam.setText("Team 2");

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
                "Time", "Team 1", "Team 2", "stadium"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        if (jTableMatches.getColumnModel().getColumnCount() > 0) {
            jTableMatches.getColumnModel().getColumn(0).setResizable(false);
            jTableMatches.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableMatches.getColumnModel().getColumn(1).setResizable(false);
            jTableMatches.getColumnModel().getColumn(2).setResizable(false);
            jTableMatches.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel_awayTeam1.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel_awayTeam1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_awayTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_awayTeam1.setText("Stadium");

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

        jRadioButton1.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("PM");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("AM");

        javax.swing.GroupLayout jPanel_Matches_FrameLayout = new javax.swing.GroupLayout(jPanel_Matches_Frame);
        jPanel_Matches_Frame.setLayout(jPanel_Matches_FrameLayout);
        jPanel_Matches_FrameLayout.setHorizontalGroup(
            jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addComponent(jTextField_Week, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addComponent(jLabel_Week, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)))
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jTextField_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addGap(180, 180, 180)
                                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel_leagueName_Matches, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(251, 251, 251))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addComponent(jPanel_MatchesClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))))
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
                .addGap(18, 18, 18)
                .addComponent(jButton_Delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Week, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Matches_FrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Week, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Matches_FrameLayout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(8, 8, 8)
                        .addComponent(jRadioButton2)))
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
                .addGap(27, 27, 27)
                .addComponent(jButton_Add)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );

        // Removing inner borders inside the button
        jButton_Delete.setFocusPainted(false);
        // Removing inner borders inside the button
        jButton_Add.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Matches_Frame, javax.swing.GroupLayout.DEFAULT_SIZE, 1286, Short.MAX_VALUE)
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

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
       deleteMatch();
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        String temp_Week = jTextField_Week.getText();
        String temp_Time = jTextField_Time.getText();
      
        DataEntryChecking t1 = new DataEntryChecking();
        // Checking For Wrong Team Name Entry
        if(!(t1.isValid_Week(temp_Week)))
        {
            JOptionPane.showMessageDialog(this,"Invalid Week ... Please Choose a Valid Week", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            jTextField_Time.setForeground(new Color(51,51,51));
            //jTextField_homeTeam.setForeground(new Color(51,51,51));
            //jTextField_awayTeam.setForeground(new Color(51,51,51));
            // Wrong Entry Here
            jTextField_Week.setForeground(Color.red);
            jTextField_Week.setText("0 - 100!");
            return;
        }
        
        // Checking For Wrong Year Entry
        if(!(t1.isValid_Time(temp_Time)))
        {
            JOptionPane.showMessageDialog(this,"Invalid Time ... Please Choose a Valid Time", "Data Entry Error",JOptionPane.ERROR_MESSAGE);
            jTextField_Week.setForeground(new Color(51,51,51));
            //jTextField_homeTeam.setForeground(new Color(51,51,51));
            //jTextField_awayTeam.setForeground(new Color(51,51,51));
            // Wrong Entry Here
            jTextField_Time.setForeground(Color.red);
            jTextField_Time.setText("00:00!");
            return;
        }
        
        try{
            int homeTeamID = (int) teamsList[home_team_combo_box.getSelectedIndex()][2];
            int awayTeamID = (int) teamsList[away_team_combo_box.getSelectedIndex()][2];
            int stadiumID =  (int) stadiumList[stadium_combo_box.getSelectedIndex()][1];
            int week  = Integer.parseInt(temp_Week);
            if (homeTeamID == awayTeamID){
                JOptionPane.showMessageDialog(this,"The Two Teams Cannot Be The Same !!!", "Invalid Trophies",JOptionPane.ERROR_MESSAGE);
                return ;
            }
            addNewGame(week, jTextField_Time.getText(),
                     homeTeamID, awayTeamID, stadiumID);
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "An Error Ocurred");
            return ;
        }
        this.dispose();
        try{
        Thread.sleep(250);
        new Matches_Frame(this.currentLeague_Name, currentLeagueID).show();
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton_AddActionPerformed

    private void jTextField_TimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_TimeFocusGained
        jTextField_Time.setText("");
    }//GEN-LAST:event_jTextField_TimeFocusGained

    private void jTextField_WeekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_WeekMouseClicked
        jTextField_Week.setEditable(true);
        jTextField_Week.setText("");
    }//GEN-LAST:event_jTextField_WeekMouseClicked

    private void stadium_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stadium_combo_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stadium_combo_boxActionPerformed

    private void away_team_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_away_team_combo_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_away_team_combo_boxActionPerformed

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

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            for (int i=0; i<jTableMatches.getColumnCount();i++){
               jTableMatches.setDefaultRenderer(jTableMatches.getColumnClass(i),tblTeamsRenderer);
               }
             // repaint to show table cell changes
            jTableTeams.updateUI();
            jTableMatches.updateUI();
        }
    
     
    private void addNewGame(int week , String Time, int homeTeamID, int awayTeamID, int stadiumID) throws Exception{
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
            Time = jRadioButton1.isSelected()? (Time + " pm") : (Time + " am");
            insertionToGameStatment.executeUpdate("INSERT INTO game (id, time, leagueid, stadiumid) VALUES"
                    + " ("+id+",'"+ Time+ "', "+ currentLeagueID+","+ stadiumID+")");
            
            insertionToPlayStatment1.executeUpdate("INSERT INTO play  (Teamid, Matchid,week)  VALUES"
                    + " ("+homeTeamID+","+ id+","+week+")");
            
            insertionToPlayStatment2.executeUpdate("INSERT INTO play  (Teamid, Matchid, week)  VALUES"
                    + " ("+awayTeamID+","+ id+","+ week+ ")");
            
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
            Statement stmt4=con.createStatement();  
      

            ResultSet gamesStatement=stmt.executeQuery("select distinct id, time,leagueid,stadiumid,week from game, play where Leagueid =" +currentLeagueID + "and play.matchid = game.id order by week");  

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
            int gameID =(int) _matchsList[row][4];  
          
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
            new Matches_Frame(jLabel_leagueName_Matches.getText(), currentLeagueID).show();
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){ 
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "An Error Ocurred");
        }
  
   }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> away_team_combo_box;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> home_team_combo_box;
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JLabel jLabel_MatchesClose;
    private javax.swing.JLabel jLabel_Time;
    private javax.swing.JLabel jLabel_Week;
    private javax.swing.JLabel jLabel_awayTeam;
    private javax.swing.JLabel jLabel_awayTeam1;
    private javax.swing.JLabel jLabel_homeTeam;
    private javax.swing.JLabel jLabel_leagueName_Matches;
    private javax.swing.JPanel jPanel_MatchesClose;
    private javax.swing.JPanel jPanel_Matches_Frame;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableMatches;
    private javax.swing.JTable jTableTeams;
    private javax.swing.JTextField jTextField_Time;
    private javax.swing.JTextField jTextField_Week;
    private javax.swing.JComboBox<String> stadium_combo_box;
    // End of variables declaration//GEN-END:variables
}
