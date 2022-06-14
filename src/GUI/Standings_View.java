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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ramy Mohamed
 */
public class Standings_View extends javax.swing.JFrame {
    private int ThiscurrentLeagueID;
    private int id=0;
    DefaultTableCellRenderer tblLeagueRenderer = new DefaultTableCellRenderer();
    /**
     * Creates new form LeagueStandings
     */
    public Standings_View(String currentLeague_Name, int currentLeagueID) throws SQLException {
        ThiscurrentLeagueID = currentLeagueID ;
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        try {
            updateStandingsTable();
            /* Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select id from league where leaguename = '"+leagueName+"'");
            System.out.println("iam heres");
            id = 0 ;
            // id = rs.getInt("ID");
            try {
            updateTeamsTable();
            } catch (Exception ex) {
            Logger.getLogger(Standings_View.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            }*/
        } catch (Exception ex) {
            Logger.getLogger(Standings_View.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        jLabel_leagueName.setText(currentLeague_Name);
        
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
    public void updateStandingsTable() throws Exception{
       DefaultTableModel tblLeagueModel = (DefaultTableModel)jTableLeague.getModel();
        Object[][] _testData ;
        try{
           _testData = getStandings();
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
   
   private Object[][] getStandings() throws Exception{
       try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            Statement stmt2=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from standings where leagueid =" +ThiscurrentLeagueID + " order by points desc");  
            
            Object[][] teamsList = new Object[1000][1000];
            int index = 0 ;
            while(rs.next()) { 
                //getting team
                ResultSet rs2=stmt2.executeQuery("select name from team where id =" + rs.getInt("teamid"));  
                String _teamName  = "N/A";
                while(rs2.next()){
                    _teamName = rs2.getString("name");
                }
                
                teamsList[index][0] = index+1;
                teamsList[index][1] = _teamName;
                teamsList[index][2] = rs.getInt("plays");
                teamsList[index][3] = rs.getInt("wins");
                teamsList[index][4] = rs.getInt("draws");
                teamsList[index][5] = rs.getInt("losses");
                teamsList[index][6] = rs.getInt("points");
                index ++ ;
             }
            con.close(); 
            return teamsList;
        }catch(SQLException e){ 
                System.out.println(e.getMessage());
                throw e;
        }  
   }
   
      private void setTableCellAlignment(int alignment) {
             tblLeagueRenderer.setHorizontalAlignment(alignment);
             for (int i=0; i<jTableLeague.getColumnCount();i++){
                jTableLeague.setDefaultRenderer(jTableLeague.getColumnClass(i),tblLeagueRenderer);
                }
            // repaint to show table cell changes
            jTableLeague.updateUI();
        }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_leagueName = new javax.swing.JLabel();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jLabel_leagueName.setFont(new java.awt.Font("Cambria", 1, 42)); // NOI18N
        jLabel_leagueName.setForeground(new java.awt.Color(108, 147, 59));
        jLabel_leagueName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_leagueName.setText("League");
        getContentPane().add(jLabel_leagueName);
        jLabel_leagueName.setBounds(10, 20, 1220, 50);

        jLabel_TeamsClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_TeamsClose.setForeground(new java.awt.Color(153, 153, 153));
        jLabel_TeamsClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TeamsClose.setText("X");
        jLabel_TeamsClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_TeamsClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_TeamsCloseMouseClicked(evt);
            }
        });
        getContentPane().add(jLabel_TeamsClose);
        jLabel_TeamsClose.setBounds(1240, 10, 25, 29);

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

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(110, 100, 1100, 590);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Soccer Stadum.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1450, 768);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 6));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1408, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1420, 770);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_TeamsCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TeamsCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel_TeamsCloseMouseClicked

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Standings_View("Premiere League").setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Standings_View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_TeamsClose;
    private javax.swing.JLabel jLabel_leagueName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLeague;
    // End of variables declaration//GEN-END:variables
}
