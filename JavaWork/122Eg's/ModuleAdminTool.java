import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * ModuleAdminTool provides the front end for the module administrator
 *
 * @author Mark Ratcliffe
 * @version 04 December 1999
 *
 */
 public class ModuleAdminTool extends JFrame
{
    // Variables declaration -
    ModuleList theModules = new ModuleList();
    // Variables declaration - do not modify
    private JTabbedPane mainPane      = new javax.swing.JTabbedPane ();

    // Definitions for the module panel
    private JTextField moduleTitle     = new JTextField("< enter data >");
    private JTextField moduleCode      = new JTextField("< enter data >");
    private JTextField moduleCoord     = new JTextField("< enter data >");
    private JTextField moduleLectures  = new JTextField("< enter data >");
    private JButton findModuleButton   = new JButton("Find Module");
    private JButton createModuleButton = new JButton("Create Module");
    private JButton updateModuleButton = new JButton("Update Module");

     // Definitions for the student panel
    private JTextField studentTitle    = new JTextField("< enter data >");
    private JTextField studentForename = new JTextField("< enter data >");
    private JTextField studentSurname  = new JTextField("< enter data >");
    private JTextField studentAddress  = new JTextField("< enter data >");
    private JTextField studentPhoneNum = new JTextField("< enter data >");
    private JTextField studentEmail    = new JTextField("< enter data >");
    private JButton findStudentButton    = new JButton("Find Student");
    private JButton createStudentButton  = new JButton("Create Student");
    private JButton updateStudentButton  = new JButton("Update Student");

    // Definitions for the admin panel
    private JTextField locateModuleField = new JTextField("< enter data >");
    private JButton locateModuleButton   = new JButton("Locate Module");
    private JTextField adminForenameField   = new JTextField("< enter data >");
    private JTextField adminSurnameField    = new JTextField("< enter data >");
    private JButton addStudToModButton   = new JButton("Add Student");
    private JList studentOnModuleList;


    // End of variables declaration
    /** Creates new form ModuleAdminAWT */
    public ModuleAdminTool()
    {
        initComponents ();
        pack ();
        setVisible(true);
    }


    private void initComponents ()
    {
        getContentPane().setBounds(50,50,200,200);
        getContentPane().setBackground(Color.blue);
        this.addWindowListener (new WindowHandler());
        JLabel mainTitle = new JLabel("University of Wales, Aberystwyth   "
                                      + "   Module Administrator",
                                      SwingConstants.CENTER);
        mainTitle.setForeground(Color.orange);
        mainTitle.setBackground(Color.orange);
        getContentPane().add (mainTitle, BorderLayout.NORTH);
        mainPane.setToolTipText ("Choose your option here");


        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
        //     Module Panel allows user to add students to a module
        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***

        JPanel modulePanel = new JPanel();
        modulePanel.setBackground(Color.orange);
        modulePanel.setLayout (new BorderLayout());
        modulePanel.setToolTipText ("Use this panel to "
                                   + "register new modules");

        JPanel moduleEntry = new JPanel(new GridLayout(4,2));
        // Title <enter code here>
        JLabel codeLabel = new JLabel("Code:",
                                      SwingConstants.CENTER);
        moduleEntry.add(codeLabel);
        moduleEntry.add(moduleTitle);

        // Title <enter title here>
        JLabel moduleTitleLabel = new JLabel("Title:",
                                             SwingConstants.CENTER);
        moduleEntry.add(moduleTitleLabel);
        moduleEntry.add(moduleCode);

        // Forename <enter title here>
        JLabel CoordLabel = new JLabel("Coordinator:",
                                       SwingConstants.CENTER);
        moduleEntry.add(CoordLabel);
        moduleEntry.add(moduleCoord);

        // Address <enter title here>
        JLabel lectureLabel = new JLabel("Num lectures:",
                                       SwingConstants.CENTER);
        moduleEntry.add(lectureLabel);
        moduleEntry.add(moduleLectures);

        modulePanel.add(moduleEntry, BorderLayout.CENTER);

        // Now add a panel for providing command buttons
        JPanel moduleCommand = new JPanel();
        moduleCommand.setLayout (new FlowLayout());

        // [Find Student] [Create Student]
        findModuleButton.setToolTipText ("Search on module code");
        findModuleButton.addActionListener(new ButtonListener());
        moduleCommand.add(findModuleButton);

        createModuleButton.setToolTipText ("Add module to list");
        createModuleButton.addActionListener(new ButtonListener());
        moduleCommand.add(createModuleButton);

        updateModuleButton.setToolTipText ("Update details in list");
        updateModuleButton.addActionListener(new ButtonListener());
        moduleCommand.add(updateModuleButton);

        // Now add the module command panel to the module panel
        modulePanel.add(moduleCommand, BorderLayout.SOUTH);

        // Add the module panel to the main pane
        mainPane.addTab ("Modules", modulePanel);

        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
        //     Student Panel allows user to add students to a module
        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***

        JPanel studentPanel = new JPanel ();
        studentPanel.setBackground(Color.orange);
        studentPanel.setLayout (new BorderLayout ());
        studentPanel.setToolTipText ("Use this panel to "
                                   + "register students");

        JPanel studentEntry = new JPanel(new GridLayout(6,2));

        // Title <enter title here>
        JLabel titleLabel = new JLabel("Title:",
                                       SwingConstants.CENTER);
        studentEntry.add(titleLabel);
        studentEntry.add(studentTitle);

        // Forename <enter title here>
        JLabel studentForenameLabel = new JLabel("Forename:",
                                       SwingConstants.CENTER);
        studentEntry.add(studentForenameLabel);
        studentEntry.add(studentForename);

        // Surname <enter title here>
        JLabel studentSurnameLabel = new JLabel("Surname:",
                                       SwingConstants.CENTER);
        studentEntry.add(studentSurnameLabel);
        studentEntry.add(studentSurname);

        // Address <enter title here>
        JLabel addressLabel = new JLabel("Address:",
                                       SwingConstants.CENTER);
        studentEntry.add(addressLabel);
        studentEntry.add(studentAddress);

        // Phone Num <enter title here>
        JLabel phoneNumLabel = new JLabel("Phone Num:",
                                       SwingConstants.CENTER);
        studentEntry.add(phoneNumLabel);
        studentEntry.add(studentPhoneNum);

        // E-mail <enter title here>
        JLabel eMailLabel = new JLabel("E-mail:",
                                       SwingConstants.CENTER);
        studentEntry.add(eMailLabel);
        studentEntry.add(studentEmail);

        studentPanel.add(studentEntry, BorderLayout.CENTER);

        // Now add a panel for providing command buttons
        JPanel studentCommand = new JPanel();
        studentCommand.setLayout (new FlowLayout());

        // [Find Student] [Create Student] [Update Student]
        findStudentButton.setToolTipText ("Search on Forename & Surname");
        findStudentButton.addActionListener(new ButtonListener());
        studentCommand.add(findStudentButton);

        createStudentButton.addActionListener(new ButtonListener());
        createStudentButton.setToolTipText ("Add student to list ");
        studentCommand.add(createStudentButton);

        updateStudentButton.setToolTipText ("Update details in list");
        updateStudentButton.addActionListener(new ButtonListener());
        studentCommand.add(updateStudentButton);

        // Now add the student command panel to student panel
        studentPanel.add(studentCommand, BorderLayout.SOUTH);

        // Add student panel to main pane
        mainPane.addTab ("Students", studentPanel);
        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
        //     Admin Panel allows user to add students to a module
        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***

        JPanel adminPanel = new JPanel();
        adminPanel.setBackground(Color.orange);
        adminPanel.setLayout (new java.awt.BorderLayout());
        adminPanel.setToolTipText ("Use this panel to add "
                                   + "students to a module");

        JPanel locateModulePanel = new JPanel(new FlowLayout());
        JLabel locateModuleLabel = new JLabel("Module Code:",
                                        SwingConstants.CENTER);
        locateModulePanel.add(locateModuleLabel);
        locateModuleButton.setToolTipText("Locate module to add students");
        locateModulePanel.add(locateModuleField);
        locateModulePanel.add(locateModuleButton);

        adminPanel.add(locateModulePanel, BorderLayout.NORTH);

        studentOnModuleList = new JList();
        adminPanel.add (studentOnModuleList, BorderLayout.CENTER);

        JPanel addStudentPanel = new JPanel();
        // Forename: <enter forename here>
        JLabel adminForenameLabel = new JLabel("Forename:",
                                       SwingConstants.CENTER);
        addStudentPanel.add(adminForenameLabel);
        adminForenameLabel.setToolTipText("Enter forename");
        addStudentPanel.add(adminForenameField);

        // Surname: <enter surname here>
        JLabel adminSurnameLabel = new JLabel("Surname:",
                                       SwingConstants.CENTER);
        addStudentPanel.add(adminSurnameLabel);
        adminSurnameLabel.setToolTipText("Enter forename");
        addStudentPanel.add(adminSurnameField);

        // [Add Student]
        addStudentPanel.add(addStudToModButton);

        // Now add the addPanel to the administration panel
        adminPanel.add (addStudentPanel, BorderLayout.SOUTH);

        // And add the adminPanel to the main pane
        mainPane.addTab ("Admin", adminPanel);


        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***

        getContentPane ().add (mainPane, BorderLayout.CENTER);

    }


    /** Exit the Application */
    private void exitTool()
    {
        System.exit (0);
    } // end of event_exitTool


    /**
     * Simple test harness
     */
    public static void main (String [] args)
    {
        ModuleAdminTool testTool = new ModuleAdminTool();
    }

    /*
     *                  Inner Class Definition - WindowHandler
     */

    /**
     * WindowHandler - listens out for user operations on the frame
     */
    public class WindowHandler extends WindowAdapter    
    {
        public void windowClosing(WindowEvent event)
        {
            exitTool();
        }
    }

    /*
     *                  Inner Class Definition - ButtonListener
     */

    /**
     * Listen for button pressed
     */
    public class ButtonListener implements ActionListener
    {
        /**
         * When button is pressed carry out action
         */
        public void actionPerformed(ActionEvent event)
        {
            String button = event.getActionCommand();

            if (button.equals("Find Student"))
            {
               System.out.println("Finding student");
            }
            if (button.equals("Create Student"))
            {
               System.out.println("Creating student");
            }
            if (button.equals("Update Student"))
            {
               System.out.println("Updating student");
            }
            if (button.equals("Find Module"))
            {
               System.out.println("Finding module");
            }
            if (button.equals("Create Module"))
            {
               System.out.println("Creating module");
            }
            if (button.equals("Update Module"))
            {
               System.out.println("Updating module");
            }
            if (button.equals("Locate Module"))
            {
               System.out.println("Locating module to find students");
            }
            if (button.equals("Add Student"))
            {
               System.out.println("Adding a student to a module");
            }
        }
    }

}