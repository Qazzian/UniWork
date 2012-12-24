/*
 * TicTacToeF.java
 *
 * Created on 24 February 2001, 13:13
 */

package TicTacToe;

/**
 *
 * @author  Ian Wallis
 * @version 
 */
public class TicTacToeF extends javax.swing.JFrame {

    /** Creates new form TicTacToeF */
    public TicTacToeF() {
        initComponents ();
        pack ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jMenuBar1 = new javax.swing.JMenuBar();
        jmGame = new javax.swing.JMenu();
        JmiNewGame = new javax.swing.JMenuItem();
        jmiOptions = new javax.swing.JMenuItem();
        jsGame1 = new javax.swing.JSeparator();
        jmiExit = new javax.swing.JMenuItem();
        lmHelp = new javax.swing.JMenu();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        
        jmGame.setJLabel("Game");
          jmGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
          jmGame.setText("Menu");
          
          JmiNewGame.setText("Item");
            jmGame.add(JmiNewGame);
            
          jmiOptions.setText("Item");
            jmGame.add(jmiOptions);
            
          jmGame.add(jsGame1);
            
          jmiExit.setText("Item");
            jmGame.add(jmiExit);
            jMenuBar1.add(jmGame);
          
        lmHelp.setLabel("Help");
          lmHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
          lmHelp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
          lmHelp.setText("Menu");
          lmHelp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          jMenuBar1.add(lmHelp);
          getContentPane().setLayout(new java.awt.GridLayout(3, 3, 3, 3));
        setTitle("TicTacToe");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        }
        );
        
        jButton1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton1.setActionCommand("TopLeft");
        jButton1.setDefaultCapable(false);
        
        getContentPane().add(jButton1);
        
        
        jButton4.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton4.setActionCommand("TopLeft");
        jButton4.setDefaultCapable(false);
        
        getContentPane().add(jButton4);
        
        
        jButton5.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton5.setActionCommand("TopLeft");
        jButton5.setDefaultCapable(false);
        
        getContentPane().add(jButton5);
        
        
        jButton6.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton6.setActionCommand("TopLeft");
        jButton6.setDefaultCapable(false);
        
        getContentPane().add(jButton6);
        
        
        jButton7.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton7.setActionCommand("TopLeft");
        jButton7.setDefaultCapable(false);
        
        getContentPane().add(jButton7);
        
        
        jButton8.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton8.setActionCommand("TopLeft");
        jButton8.setDefaultCapable(false);
        
        getContentPane().add(jButton8);
        
        
        jButton9.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton9.setActionCommand("TopLeft");
        jButton9.setDefaultCapable(false);
        
        getContentPane().add(jButton9);
        
        
        jButton10.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton10.setActionCommand("TopLeft");
        jButton10.setDefaultCapable(false);
        
        getContentPane().add(jButton10);
        
        
        jButton11.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jButton11.setActionCommand("TopLeft");
        jButton11.setDefaultCapable(false);
        
        getContentPane().add(jButton11);
        
        setJMenuBar(jMenuBar1);
        
    }//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        new TicTacToeF ().show ();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jmGame;
    private javax.swing.JMenuItem JmiNewGame;
    private javax.swing.JMenuItem jmiOptions;
    private javax.swing.JSeparator jsGame1;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenu lmHelp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    // End of variables declaration//GEN-END:variables

}