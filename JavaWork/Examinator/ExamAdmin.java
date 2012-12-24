import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * The user interface for creating multiple choise Exams
 *
 * @see Exam
 *
 * @author Ian Wallis 	
 * @email: ifw9@aber.ac.uk or Qaz_Wallis@yahoo.com
 * @version last updated 23 April 2000
 * @since jdk 1.2
 *
 * This code is the property of me, Ian Wallis.  You are free to use this 
 * code in your own projects but I ask that you acknowledge my part in them  
 * and that you include my original code, or the means to get it, with your 
 * code. I accept no responsibility for any damage caused in any way by my 
 * code and I assure you that if it does cause damage then it not 
 * intentional.  I would also be gratefull for any comments or suggestions
 * that you have regarding my code.  <BR>
 * Thank you.
 */

public class ExamAdmin extends JFrame
{	
	/* Variable Decleration */
	private Exam theExam;
	private Question selectedQ;
	private Answer selectedA;

	private JTextField examTitleText = new JTextField(15);
 
	 /* Question Manipulation */
	private JTextArea newQText 		 = new JTextArea(4,20);	
	private JScrollPane qListScroll  = new JScrollPane();
	private JButton[] qSelectButtons = new JButton[30];
	private JTextArea[] qDisplays	 = new JTextArea[30];
	private JPanel[] qDispPanel		 = new JPanel[30];
	
	/* Answer Manipulation */
	private JTextArea newAText 		= new JTextArea(4,20);
	private JTextArea correctAText 	= new JTextArea(4,20);
	private Box aListButtons 		= new Box(BoxLayout.Y_AXIS);
	private Box aListText 			= new Box(BoxLayout.Y_AXIS);
	
    private JTextField messageField = new JTextField(50);

	/**
	 * Sets up the Window putting in the Text fields and buttons.
	 */
	public ExamAdmin()
	{
		// set up the basic display
		super("Exam Admin");
		Toolkit defaultProp = this.getToolkit();
		Dimension screenSize = defaultProp.getScreenSize();
		this.setBounds(screenSize.width/4,
					   screenSize.height/4,
					   screenSize.width/2,
					   screenSize.height/2);
		getContentPane().setLayout(new BorderLayout(0,0));
		// creat a Button Listener
		ButtonListener bL = new ButtonListener();
		
		/**************************
		 * The head of the Window *
		 **************************/
		JPanel titlePanel = new JPanel();
		titlePanel.add(new Label("Exam name:"));
		titlePanel.add(examTitleText);
		
		// create load, save and new buttons
		JButton loadButton = new JButton("LOAD");
		JButton saveButton = new JButton("SAVE");
		JButton newButton = new JButton("NEW");
		
		// Add the Button listener
        loadButton.addActionListener(bL);
		saveButton.addActionListener(bL);
		newButton.addActionListener(bL);
		
		// Put them in the Panel
		titlePanel.add(newButton);
		titlePanel.add(loadButton);
		titlePanel.add(saveButton);
		
		/****************************
		 * Setup the Question Panel *
		 ****************************/
		Box mainQPanel = new Box(BoxLayout.Y_AXIS);
		// Enter new Questions Here
		mainQPanel.add(new Label("Add/Change Question:") );
		JScrollPane newQScroll = new JScrollPane(newQText);
		mainQPanel.add(newQScroll);
		
		// Display the list of Questions which have been created
		mainQPanel.add(new Label("Current Questions:") );
		mainQPanel.add(qListScroll);

		// create the buttons for adding/deleting Questions	
		JPanel qButtonPanel = new JPanel();
		JButton addQButton = new JButton("Add Q");
		addQButton.addActionListener(bL);
		qButtonPanel.add(addQButton);
		JButton delQButton = new JButton("Del Q");
		delQButton.addActionListener(bL);
		qButtonPanel.add(delQButton);
		mainQPanel.add(qButtonPanel);


		/**************************
		 * Setup the Answer Panel *
		 **************************/
		JPanel aButtonPanel = new JPanel();
		// Create the main buttons for manipulating the Answers.
		JButton addAButton = new JButton("Add A");
		JButton delAButton = new JButton("Del A");
		
		// Add the Listener to them
		addAButton.addActionListener(bL);
		delAButton.addActionListener(bL);
		
		// Put them in the aButton panel
		aButtonPanel.add(addAButton);
		aButtonPanel.add(delAButton);
		
		// Put the Text fields and buttons in a new box panel
		Box mainAPanel = new Box(BoxLayout.Y_AXIS);
		mainAPanel.add(new Label("Add/Change Answer:") );
		JScrollPane newAScroll = new JScrollPane(newAText);
		mainAPanel.add(newAScroll);
		mainAPanel.add(new Label("Correct Answer:") );
		JScrollPane crrectAScroll = new JScrollPane(correctAText);
		mainAPanel.add(crrectAScroll);
		mainAPanel.add(new Label("Wrong Answers:") );
		JPanel AListPanel = new JPanel();
		AListPanel.add(aListButtons);
		AListPanel.add(aListText);
		JScrollPane wrongAScroll = new JScrollPane(AListPanel);
		mainAPanel.add(wrongAScroll);
		mainAPanel.add(aButtonPanel);
		
		// Put the Main Panels into the Content Pane
		getContentPane().add(titlePanel,BorderLayout.NORTH);
		JPanel centralPanel = new JPanel();
		centralPanel.add(mainQPanel);
		centralPanel.add(mainAPanel);
		getContentPane().add(centralPanel,BorderLayout.CENTER);

		this.addWindowListener (new WindowHandler() );		
		this.pack();
		
		// Put some text in the windows
		examTitleText.setText("< No Exam Selected >");
	}
	
	
	private void loadExam()
	{
		// Put in previusly created Questions
		int qs = theExam.getQCount();
		TextListener tL = new TextListener();
        for(int i=0; i<qs; i++)
        {
            qDispPanel[i] = new JPanel();
            qSelectButtons[i] = new JButton( "Q" + i );
            qSelectButtons[i].addActionListener(tL);
            qDispPanel[i].add(qSelectButtons[i]);
			
			qDisplays[i] = new JTextArea
							( theExam.getQuestion(i).getDisplay() );
			qDisplays[i].setEditable(false);
			qDispPanel[i].add(qDisplays[i]);
			qListScroll.add(qDispPanel[i]);
        }
		
	}
	
	private void saveExam()
	{
	
	}
	
	/** Exit the Application */
	private void exitTool()
	{
		System.exit (0);
	}
	
	/** Start the Application */
	public static void main (String [] args)
	{
		ExamAdmin adminWindow = new ExamAdmin();
		adminWindow.setVisible(true);
	}
	
	/*
	 * Define inner Classes *
	 */
	
	/**
	 * Handles events within the window.
	 */
	public class WindowHandler extends WindowAdapter
	{
		public void windowClosing(WindowEvent event)
		{
			exitTool();
		}
	}

	/**
	 * Listens for button activation and responds acordingly.
	 */
	public class ButtonListener implements ActionListener
	{
		/**
		 * Depending on which button is pressed carry out action
		 */
		public void actionPerformed(ActionEvent event)
		{
			String buttonString = event.getActionCommand();


			if (buttonString.equals("NEW"))
			{
				theExam = new Exam();
				System.out.println("New Exam Created");
				examTitleText.setText("< No Name >");
                newQText.setText("");
                newAText.setText("");
                correctAText.setText("");
			}
			if (buttonString.equals("LOAD"))
			{
				System.out.println("Now loading");
			}
			if (buttonString.equals("SAVE"))
			{
				System.out.println("now saving");
			}
			if (buttonString.equals("Add Q"))
			{
				try
				{
				int qs = theExam.getQCount();
				// create a new Question & Add it to theExam
				System.out.println("adding a question");
				String tmpS = newQText.getText();
				newQText.setText("");
				Question tmpQ = new Question(tmpS);
				theExam.addQuestion(tmpQ);
				// now put the Question in the Display
				qDispPanel[qs] = new JPanel();
				qSelectButtons[qs] = new JButton("Q" + qs);
				qDispPanel[qs].add(qSelectButtons[qs]);
				qDisplays[qs] = new JTextArea(tmpS);
				qDispPanel[qs].add(qDisplays[qs]);
				qListScroll.add(qDispPanel[qs]);
				}
				catch(QuestionBankFullException e)
				{
					System.out.println(e);
				}
			}
			if (buttonString.equals("Del Q"))
			{
				System.out.println("deleating a Question");
			}
			if (buttonString.equals("Add A"))
			{
				System.out.println("adding an Answer");
			}
			if (buttonString.equals("Del A"))
			{
				System.out.println("deleting an Answer");
			}
		}
		
	}
	
	public class TextListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			/*
			String nameString = event.getActionCommand();
			char QorA = nameString.charAt(0);
			String numSt = String.valueOf( nameString.charAt(1) );
			int num = Integer.parseInt(numSt) - 1;
			if(QorA == 'Q')
			{
				newQText.setText( qListText[num].getText() );
			}
			if(QorA == 'A')
			{
			
			}
			updateApp();
			*/
		} 
	}
 	

}