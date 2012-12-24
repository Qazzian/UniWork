import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;        //required for saving to file
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
    private GenericList theModules;
    private GenericList theStudents;
    // Variables declaration - do not modify
    private JTabbedPane mainPane      = new javax.swing.JTabbedPane ();

    // Definitions for the module panel
    private JTextField moduleCode      = new JTextField(7);
    private JTextField moduleTitle     = new JTextField(30);
    private JTextField moduleCoord     = new JTextField(20);
    private JTextField moduleLectures  = new JTextField(3);
    private JButton findModuleButton   = new JButton("Find Module");
    private JButton createModuleButton = new JButton("Create Module");
    private JButton updateModuleButton = new JButton("Update Module");
    private JButton clearModuleButton  = new JButton("Clear Entry");

     // Definitions for the student panel
    private JTextField studentTitle    = new JTextField(4);
    private JTextField studentForename = new JTextField(15);
    private JTextField studentSurname  = new JTextField(20);
    private JTextField studentAddress  = new JTextField(20);
    private JTextField studentPhoneNum = new JTextField(20);
    private JTextField studentEmail    = new JTextField(20);
    private JTextField studentUCAS     = new JTextField(10);
    private JButton findStudentButton    = new JButton("Find Student");
    private JButton createStudentButton  = new JButton("Create Student");
    private JButton updateStudentButton  = new JButton("Update Student");
    private JButton clearStudentButton  = new JButton("Clear Fields");

    // Definitions for the admin panel
    private JTextField locateModuleCode = new JTextField(7);
    private JButton locateModuleButton   = new JButton("Locate Module");
    private JTextField locateModuleTitle = new JTextField(30);
    private JTextField adminUCASField = new JTextField(10);
    private JButton locateStudForModButton = new JButton("Locate Student");
    private JTextField adminForenameField   = new JTextField(15);
    private JTextField adminSurnameField    = new JTextField(20);
    private JButton addStudToModButton = new JButton("Add Student");
    private JTextArea studentsOnModuleList = new JTextArea(6,60);
    private JScrollPane studentsOnModulePane 
            = new JScrollPane(studentsOnModuleList);

    // Definitions for the message panel
    private JTextField messageField = new JTextField(50);

    private final String DATAFILE = "StudentDataFile";

    // End of variables declaration
    /** Creates new form ModuleAdminAWT */
    public ModuleAdminTool()
    {
        initFromFile ();
        initComponents ();
        pack ();
        setVisible(true);
    }

    private void initComponents ()
    {
        getContentPane().setBounds(50,50,200,150);
        getContentPane().setBackground(Color.blue);
        this.addWindowListener (new WindowHandler());
        JLabel mainTitle = new JLabel("University of Wales, Aberystwyth   "
                                      + "   Module Administrator",
                                      SwingConstants.CENTER);
        mainTitle.setForeground(Color.orange);
        mainTitle.setBackground(Color.orange);
        getContentPane().add (mainTitle, BorderLayout.NORTH);
        mainPane.setToolTipText ("Choose your option here");

        initModulePanel();
        initStudentPanel();
        initAdminPanel();
    }

    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    //     Module Panel allows user to add students to a module
    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    private void initModulePanel()
    {
        JPanel modulePanel = new JPanel();
        modulePanel.setBackground(Color.orange);
        modulePanel.setLayout (new BorderLayout());
        modulePanel.setToolTipText ("Use this panel to "
                                   + "register new modules");

        JPanel moduleEntry = new JPanel(new GridLayout(4,2));
        // Title <enter code here>
        JLabel codeLabel = new JLabel("Code: ",
                                      SwingConstants.RIGHT);
        moduleEntry.add(codeLabel);
        moduleEntry.add(moduleCode);

        // Title <enter title here>
        JLabel moduleTitleLabel = new JLabel("Title: ",
                                             SwingConstants.RIGHT);
        moduleEntry.add(moduleTitleLabel);
        moduleEntry.add(moduleTitle);

        // Forename <enter title here>
        JLabel coordLabel = new JLabel("Coordinator: ",
                                       SwingConstants.RIGHT);
        moduleEntry.add(coordLabel);
        moduleEntry.add(moduleCoord);

        // Address <enter title here>
        JLabel lectureLabel = new JLabel("Num lectures: ",
                                       SwingConstants.RIGHT);
        moduleEntry.add(lectureLabel);
        moduleEntry.add(moduleLectures);

        modulePanel.add(moduleEntry, BorderLayout.CENTER);

        // Now add a panel for providing command buttons
        JPanel moduleCommand = new JPanel();
        moduleCommand.setLayout (new FlowLayout());

        // [Find Module] [Create Module] [Update Module] [Clear Module]
        findModuleButton.setToolTipText ("Search on module code");
        findModuleButton.addActionListener(new ButtonListener());
        moduleCommand.add(findModuleButton);

        createModuleButton.setToolTipText ("Add module to list -" +
                                            " if code is unique");
        createModuleButton.addActionListener(new ButtonListener());
        moduleCommand.add(createModuleButton);

        updateModuleButton.setToolTipText ("Update details in list");
        updateModuleButton.addActionListener(new ButtonListener());
        moduleCommand.add(updateModuleButton);

        clearModuleButton.setToolTipText ("Clear all entries in fields");
        clearModuleButton.addActionListener(new ButtonListener());
        moduleCommand.add(clearModuleButton);

        // Now add the module command panel to the module panel
        modulePanel.add(moduleCommand, BorderLayout.SOUTH);

        // Add the module panel to the main pane
        mainPane.addTab ("Modules", modulePanel);
    }

    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    //     Student Panel create students and register details
    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    private void initStudentPanel()
    {
        JPanel studentPanel = new JPanel ();
        studentPanel.setBackground(Color.orange);
        studentPanel.setLayout (new BorderLayout ());
        studentPanel.setToolTipText ("Use this panel to " +
                                     "register students");

        JPanel studentEntry = new JPanel(new GridLayout(7,2));

        // Title <enter title here>
        JLabel titleLabel = new JLabel("Title: ",
                                       SwingConstants.RIGHT);
        studentEntry.add(titleLabel);
        studentEntry.add(studentTitle);

        // Forename <enter title here>
        JLabel studentForenameLabel = new JLabel("Forename: ",
                                       SwingConstants.RIGHT);
        studentEntry.add(studentForenameLabel);
        studentEntry.add(studentForename);

        // Surname <enter title here>
        JLabel studentSurnameLabel = new JLabel("Surname: ",
                                       SwingConstants.RIGHT);
        studentEntry.add(studentSurnameLabel);
        studentEntry.add(studentSurname);

        // Address <enter title here>
        JLabel addressLabel = new JLabel("Address: ",
                                       SwingConstants.RIGHT);
        studentEntry.add(addressLabel);
        studentEntry.add(studentAddress);

        // Phone Num <enter title here>
        JLabel phoneNumLabel = new JLabel("Phone Num: ",
                                       SwingConstants.RIGHT);
        studentEntry.add(phoneNumLabel);
        studentEntry.add(studentPhoneNum);

        // E-mail <enter title here>
        JLabel eMailLabel = new JLabel("E-mail: ",
                                       SwingConstants.RIGHT);
        studentEntry.add(eMailLabel);
        studentEntry.add(studentEmail);

        // UCAS code  <enter code here>
        JLabel ucasLabel = new JLabel("UCAS code: ",
                                       SwingConstants.RIGHT);
        studentEntry.add(ucasLabel);
        studentEntry.add(studentUCAS);
        studentUCAS.setToolTipText ("UCAS code is unique for each student");
        studentPanel.add(studentEntry, BorderLayout.CENTER);

        // Now add a panel for providing command buttons
        JPanel studentCommand = new JPanel();
        studentCommand.setLayout (new FlowLayout());

        // [Find Student] [Create Student] [Update Student] [Clear Fields]
        findStudentButton.setToolTipText ("Search on UCAS code");
        findStudentButton.addActionListener(new ButtonListener());
        studentCommand.add(findStudentButton);

        createStudentButton.addActionListener(new ButtonListener());
        createStudentButton.setToolTipText ("Add student to list -" +
                                            " if code is unique");
        studentCommand.add(createStudentButton);

        updateStudentButton.setToolTipText ("Update details in list");
        updateStudentButton.addActionListener(new ButtonListener());
        studentCommand.add(updateStudentButton);

        clearStudentButton.setToolTipText ("Clear all entries in fields");
        clearStudentButton.addActionListener(new ButtonListener());
        studentCommand.add(clearStudentButton);

        // Now add the student command panel to student panel
        studentPanel.add(studentCommand, BorderLayout.SOUTH);

        // Add student panel to main pane
        mainPane.addTab ("Students", studentPanel);
    }

    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    // Admin Panel allows user to add students to a module
    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    private void initAdminPanel()
    {
        JPanel adminPanel = new JPanel();
        adminPanel.setBackground(Color.orange);
        adminPanel.setLayout (new java.awt.BorderLayout());
        adminPanel.setToolTipText ("Use this panel to add "
                                   + "students to a module");

        JPanel locateModulePanel = new JPanel(new GridLayout(2,1));
        /*
         * First provide facilities to locate module
         */

        // 1st row: Module Code: <Code entered here> [Locate Module]
        JPanel upperPanel = new JPanel(new FlowLayout());
        JLabel locateModuleLabel = new JLabel("Module Code:",
                                        SwingConstants.CENTER);
        upperPanel.add(locateModuleLabel);
        locateModuleButton.setToolTipText("Locate module to add students");
        upperPanel.add(locateModuleCode);
        upperPanel.add(new JLabel("           ")); // spacer
        locateModuleButton.addActionListener(new ButtonListener());
        upperPanel.add(locateModuleButton);
        locateModulePanel.add(upperPanel);

        // 2nd row Module Title: <Module Title displayed here>
        JPanel lowerPanel = new JPanel(new FlowLayout());
        JLabel locateModuleTitleLabel = new JLabel("Title:",
                                            SwingConstants.CENTER);
        lowerPanel.add(locateModuleTitleLabel);
        lowerPanel.add(locateModuleTitle);
        locateModuleTitle.setToolTipText("Non editable - found by code");
        locateModuleTitle.setEditable(false); // display only
        locateModulePanel.add(lowerPanel);
        adminPanel.add(locateModulePanel, BorderLayout.NORTH);

        /*
         * Now provide main display area for students on module
         */

        // first populate list with the students
        // studentsOnModuleList = new JList();
        // studentsOnModulePane = new JScrollPane(studentsOnModuleList);
        studentsOnModulePane.createHorizontalScrollBar();
        studentsOnModulePane.createVerticalScrollBar();
        updateStudentDisplayPane(theStudents);


        adminPanel.add (studentsOnModulePane, BorderLayout.CENTER);

        JPanel addStudentPanel = new JPanel(new GridLayout(3,1));
        /*
         * Now provide facilities to locate student
         */
        JPanel topPanel = new JPanel(new FlowLayout());

        // 1st row: UCAS code: <UCAS code displayed here> [Locate Student]
        JLabel adminUCASLabel = new JLabel("UCAS code:",
                                       SwingConstants.CENTER);
        topPanel.add(adminUCASLabel);
        adminUCASLabel.setToolTipText("Locate student by code");
        topPanel.add(adminUCASField);
        locateStudForModButton.addActionListener(new ButtonListener());
        topPanel.add(locateStudForModButton);
        addStudentPanel.add(topPanel);

        // 2nd row: Forename: <forename shown>  Surname: < surname shown>
        JPanel midPanel = new JPanel(new FlowLayout());
        JLabel adminForenameLabel = new JLabel("Forename:",
                                       SwingConstants.CENTER);
        midPanel.add(adminForenameLabel);
        adminForenameLabel.setToolTipText("Forename displayed here if found");
        midPanel.add(adminForenameField);
        adminForenameField.setEditable(false); // do not allow editing
        adminForenameField.setToolTipText("Non editable - found by code");

        JLabel adminSurnameLabel = new JLabel("Surname:",
                                       SwingConstants.CENTER);
        midPanel.add(adminSurnameLabel);
        adminSurnameLabel.setToolTipText("Surname displayed here if found");
        midPanel.add(adminSurnameField);
        adminSurnameField.setEditable(false); // do not allow editing
        adminSurnameField.setToolTipText("Non editable - found by code");
        addStudentPanel.add(midPanel);

        // 3rd row [Add Student]
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(new JLabel("                "));
        addStudToModButton.addActionListener(new ButtonListener());
        bottomPanel.add(addStudToModButton);
        bottomPanel.add(new JLabel("                "));
        addStudentPanel.add(bottomPanel);

        // Now add the addStudentPanel to the administration panel
        adminPanel.add (addStudentPanel, BorderLayout.SOUTH);

        // And add the adminPanel to the main pane
        mainPane.addTab ("Admin", adminPanel);

        getContentPane ().add (mainPane, BorderLayout.CENTER);

        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
        //     Message Panel provides an area for displaying text
        // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
        JPanel messagePanel = new JPanel ();
        messagePanel.setBackground(Color.orange);
        messagePanel.setToolTipText ("Information for user displayed here");
        messageField.setEditable(false);
        messagePanel.add(messageField);
        getContentPane().add (messagePanel, BorderLayout.SOUTH);
    }

    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    // initFromFile loads previous version from file
    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    private void initFromFile()
    {
        try
        {
            ObjectInputStream myStream =
                new ObjectInputStream(
                new BufferedInputStream(
                new FileInputStream(DATAFILE)));

            displayMessage("Reading from the file now....");

            // Read the object from file - it needs to be cast

            theModules = (GenericList) myStream.readObject();
            theStudents = (GenericList) myStream.readObject();
        }
        catch(Exception e)
        {
            displayMessage("Error in loading from file: "
                                 + "Creating database");
            theModules = new GenericList();
            theStudents = new GenericList();
        }
    }

    private void displayMessage(String message)
    {
        messageField.setText(message);
 /**       try
        {
            wait (100L);
        }
        catch (InterruptedException e)
        {
        }
 **/
        for (int i=0; i< 1000000; i++);

        messageField.setText(message);
    }

    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    // update JList with details of theStudents on a module
    // *** *** *** *** *** *** *** *** *** *** *** *** *** ***

    private void updateStudentDisplayPane(GenericList studentsOnModule)
    {

        int totalStudents = studentsOnModule.getListLength();
        String toInsert = "";

        // First clear the list
        studentsOnModuleList.setText("");

        try
        {
            for (int i=0; i < totalStudents; i++)
            {
                Student temp = (Student)theStudents.getItem(i);
                toInsert = temp.getTitle()
                         + "\t"
                         + temp.getForename()
                         + "\t"
                         + temp.getSurname()
                         + "\t"
                         + temp.getUcasCode()
                         +"\n";

                studentsOnModuleList.insert(toInsert,i);

            }
        }
        catch (ListException e)
        {
            displayMessage("Error is listing students on module");
            return;
        }
    }


    /** Exit the Application */
    private void exitTool()
    {
        try
        {
            // Lets get a handle on the file to write to
            File myFile = new File (DATAFILE);

            ObjectOutputStream myStream =
                    new ObjectOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(myFile)));

            System.out.println("Writing data to the file now....");

            myStream.writeObject(theModules);         // Write object to file
            myStream.writeObject(theStudents);        // Write object to file
            myStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Error in saving to file");
        }
        finally
        {
            System.exit(0);
        }
    } // end of exitTool


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
        // utility code to find a student given ucasCode
        private Student findStudent(String ucasCode)
        {
             Student found;
             Student toFind = new Student();
             toFind.setUcasCode(ucasCode);

             try
             {
                 found = (Student)theStudents.getItem(toFind);
             }
             catch (ListException e)
             {
                 displayMessage("Could not locate student");
                 return null;
             }
             return found;
        }

        // utility code to find a module given module code
        private Module findModule(String moduleCode)
        {
             Module found;
             Module toFind = new Module();
             toFind.setCode(moduleCode);

             try
             {
                 found = (Module) theModules.getItem(toFind);
             }
             catch (ListException e)
             {
                 displayMessage("Could not locate module");
                 return null;
             }
             return found;
        }

        /**
         * When button is pressed carry out action
         */
        public void actionPerformed(ActionEvent event)
        {
            String buttonString = event.getActionCommand();

            if (buttonString.equals("Find Student"))
            {
                Student found = findStudent(studentUCAS.getText());

                if (found == null)
                {
                    displayMessage("Student not found");
                }
                else
                {
                    studentTitle.setText(found.getTitle());
                    studentSurname.setText(found.getSurname());
                    studentForename.setText(found.getForename());
                    studentAddress.setText(found.getAddress());
                    studentPhoneNum.setText(found.getPhoneNum());
                    studentEmail.setText(found.getEmail());
                    studentUCAS.setText(found.getUcasCode());
                }
            }

            if (buttonString.equals("Create Student"))
            {
                Student found = findStudent(studentUCAS.getText());

                if (found != null)
                {
                    displayMessage("Duplicate Student not entered");
                    return;
                }

                Student temp = new Student(studentTitle.getText(),
                                           studentForename.getText(),
                                           studentSurname.getText(),
                                           studentAddress.getText(),
                                           studentPhoneNum.getText(),
                                           studentEmail.getText(),
                                           studentUCAS.getText());
                try
                {
                    theStudents.addItem(temp);
                    displayMessage("Student created");
                }
                catch (ListFullException e)
                {
                    displayMessage("Student numbers exceeded");
                }
            }

            if (buttonString.equals("Update Student"))
            {
                Student found = findStudent(studentUCAS.getText());

                if (found == null)
                {
                    displayMessage("Student not found");
                }
                else
                {
                    found.setTitle(studentTitle.getText());
                    found.setForename(studentForename.getText());
                    found.setSurname(studentSurname.getText());
                    found.setAddress(studentAddress.getText());
                    found.setPhoneNum(studentPhoneNum.getText());
                    found.setEmail(studentEmail.getText());
                    displayMessage("Updating student");
                }
            }

            if (buttonString.equals("Clear Fields"))
            {
                studentTitle.setText("");
                studentForename.setText("");
                studentSurname.setText("");
                studentAddress.setText("");
                studentPhoneNum.setText("");
                studentEmail.setText("");
                studentUCAS.setText("");
                displayMessage("All entries cleared");
            }

            if (buttonString.equals("Find Module"))
            {
                Module found = findModule(moduleCode.getText());

                if (found == null)
                {
                    displayMessage("Module not found");
                }
                else
                {
                    moduleTitle.setText(found.getTitle());
                    moduleCoord.setText(found.getCoordinator());
                    moduleLectures.setText(Integer.toString(
                                               found.getNumOfLectures()));
                }
            }

            if (buttonString.equals("Create Module"))
            {
                Module found = findModule(moduleCode.getText());

                // First check that Module does not exist
                if (found != null)
                {
                    displayMessage("Error - Module already exists");
                    return;
                }

                // OK, it doesn't exist so add to list
                Module temp = new Module(moduleCode.getText(),
                                         moduleTitle.getText(),
                                         moduleCoord.getText());

                temp.setNumOfLectures(Integer.parseInt(
                                          moduleLectures.getText()));

                // Now add module to the list
                try
                {
                    theModules.addItem(temp);
                }
                catch (ListFullException e)
                {
                    displayMessage("List of modules too long");
                }
                displayMessage("Module created");
            }

            if (buttonString.equals("Update Module"))
            {
                Module found = findModule(moduleCode.getText());
                if (found == null)
                {
                    displayMessage("Module not found");
                }
                else
                {
                    found.setTitle(moduleTitle.getText());
                    found.setCode(moduleCode.getText());
                    found.setCoordinator(moduleCoord.getText());
                    found.setNumOfLectures(Integer.parseInt(
                                          moduleLectures.getText()));
                    displayMessage("Updating module");
                }

            }

            if (buttonString.equals("Clear Entry"))
            {
                moduleTitle.setText("");
                moduleCode.setText("");
                moduleCoord.setText("");
                moduleLectures.setText("0");
                displayMessage("All entries cleared");
            }

            if (buttonString.equals("Locate Module"))
            {
                String tempModCode = locateModuleCode.getText();
                Module found = findModule(tempModCode);
                displayMessage("Locating " +
                               tempModCode +
                               " to find students");

                if (found == null)
                {
                    displayMessage("Module not found");
                    return;
                }
                locateModuleTitle.setText(found.getTitle());

                // now update display of students on module
                updateStudentDisplayPane(found.getStudents());

            }

            if (buttonString.equals("Locate Student"))
            {
                Student found = findStudent(adminUCASField.getText());

                displayMessage("Searching for student in main list");

                if (found == null)
                {
                    displayMessage("Student not found");
                    return;
                }
                adminSurnameField.setText(found.getSurname());
                adminForenameField.setText(found.getForename());
                // turn on Add Student
                addStudToModButton.setVisible(true);
            }

            if (buttonString.equals("Add Student"))
            {
              // Check that codes are still valid
                Module theMod = findModule(locateModuleCode.getText());

                if (theMod == null)
                {
                    displayMessage("Ooops couldn't find module");
                    return;
                }

                GenericList studsOnModule = theMod.getStudents();

                Student studToFind = new Student();
                Student theStud;
                studToFind.setUcasCode(adminUCASField.getText());

                try
                {
                    theStud = (Student) studsOnModule.getItem(studToFind);
                }
                catch (ListException e)
                {
                    // Student not already on list so add
                    try
                    {
                         studsOnModule.addItem(studToFind);
                         displayMessage("Added student to module");
                         // Update view on screen
                         updateStudentDisplayPane(studsOnModule);
                         return;
                    }
                    catch (ListException ee)
                    {
                         displayMessage("Ooops couldn't add student");
                    }
                }
                // if we get here then student was already present
                displayMessage("Student already on module");
            }
        }
    }


}