/*
 * SixStackWin.java
 *
 * Created on 18 December 2000, 21:34
 */

package Solitaire;

/**
 *
 * @author  Ian Wallis
 * @version 
 */
public class SixStackWin extends javax.swing.JApplet {

    /** Creates new form SixStackWin */
    public SixStackWin() {
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        SsMenuBar = new javax.swing.JMenuBar();
        SsGameMenu = new javax.swing.JMenu();
        SsGmNew = new javax.swing.JMenuItem();
        SsGmOptions = new javax.swing.JMenuItem();
        SsGmExit = new javax.swing.JMenuItem();
        SsHelpMenu = new javax.swing.JMenu();
        SsHmAbout = new javax.swing.JMenuItem();
        MainPanel = new javax.swing.JPanel();
        SouthPanel = new javax.swing.JPanel();
        SsMenuBar.setName("SSMenu");
        
        SsGameMenu.setName("ssMenuGame");
          SsGameMenu.setText("Game");
          
          SsGmNew.setName("ssMGameNew");
            SsGmNew.setText("New");
            SsGameMenu.add(SsGmNew);
            
          SsGmOptions.setName("ssMGameOptions");
            SsGmOptions.setText("Options");
            SsGameMenu.add(SsGmOptions);
            
          SsGmExit.setName("ssMGameExit");
            SsGmExit.setText("Exit");
            SsGmExit.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    SsGmExitActionPerformed(evt);
                }
            }
            );
            SsGmExit.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    SsGmExitMouseClicked(evt);
                }
            }
            );
            SsGameMenu.add(SsGmExit);
            SsMenuBar.add(SsGameMenu);
          
        SsHelpMenu.setName("ssMenuHelp");
          SsHelpMenu.setText("Help");
          
          SsHmAbout.setName("ssMHelpAbout");
            SsHmAbout.setText("About");
            SsHelpMenu.add(SsHmAbout);
            SsMenuBar.add(SsHelpMenu);
          
        MainPanel.setLayout(new javax.swing.BoxLayout(MainPanel, 1));
        
        getContentPane().add(MainPanel, java.awt.BorderLayout.CENTER);
        
        
        
        getContentPane().add(SouthPanel, java.awt.BorderLayout.SOUTH);
        
        setJMenuBar(SsMenuBar);
        
    }//GEN-END:initComponents

  private void SsGmExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SsGmExitMouseClicked
    System.exit();
  }//GEN-LAST:event_SsGmExitMouseClicked

  private void SsGmExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SsGmExitActionPerformed
// Add your handling code here:
  }//GEN-LAST:event_SsGmExitActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuBar SsMenuBar;
  private javax.swing.JMenu SsGameMenu;
  private javax.swing.JMenuItem SsGmNew;
  private javax.swing.JMenuItem SsGmOptions;
  private javax.swing.JMenuItem SsGmExit;
  private javax.swing.JMenu SsHelpMenu;
  private javax.swing.JMenuItem SsHmAbout;
  private javax.swing.JPanel MainPanel;
  private javax.swing.JPanel SouthPanel;
  // End of variables declaration//GEN-END:variables

}