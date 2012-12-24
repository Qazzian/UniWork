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
 * <P>
 * This code is the property of me, Ian Wallis.  You are free to use this 
 * code in your own projects but I ask that you acknowledge my part in them  
 * and that you include my original code, or the means to get it, with your 
 * code. I accept no responsibility for any damage caused in any way by my 
 * code and I assure you that if it does cause damage then it not 
 * intentional.  I would also be gratefull for any comments or suggestions
 * that you have regarding my code.  <BR>
 * Thank you.
 */

public class ExamCreator extends JFrame
{	 
	// Variable Decleration
	private Exam theExam;
	private Question selectedQ;
	private Answer selectedA;

	// Application Header
	private JTextField examTitleText = new JTextField(15);
	private JButton newExamButton	= new JButton("NEW");
	private JButton loadExamButton  = new JButton("LOAD");
	private JButton saveExamButton  = new JButton("SAVE");
 
	// Question Manipulation
	private JTextArea newQText		= new JTextArea();  
	private JTextArea qDisplayList  = new JList();
	private JTextField selectQNum	= new JTextField(4); 
	private JButton selectQ			= new JButton("SELECT Q");
	private JButton addQ			= new JButton("ADD Q");
	private JButton delQ			= new JButton("DEL Q");
	private JButton updateQ			= new JButton("UPDATE Q");
	private int selectQIndex;
	 
	// Answer Manipulation
	private JTextArea newAText		= new JTextArea(4,15);
	private JTextArea correctAdisplay = new JTextArea(4,15);
	private JTextArea wrongADisplay = new JTextArea(9,15);
	private JTextField selectANum	= new JTextField(4);
	private JButton selectA			= new JButton("SELECT A");
	private JButton addA			= new JButton("ADD A");
	private JButton delA			= new JButton("DEL A");
	private JButton updateA			= new JButton("UPDATE A");

	private JTextField messageField = new JTextField(30);
		
	// End of variables declaration.
	 
	// create the Window
	public ExamCreator()
	{
		// set up the main display
		super("Exam Creator");
		Toolkit defaultProp = this.getToolkit();
		Dimension screenSize = defaultProp.getScreenSize();
		this.setBounds(screenSize.width/4,
						screenSize.height/4,
						screenSize.width/2,
						screenSize.height/2);

		getContentPane().setLayout(new BorderLayout(0,0));
		JPanel centrePanel = new JPanel();
		getContentPane().add(centrePanel,BorderLayout.CENTER);
		this.addWindowListener(new WindowHandler() );
		ButtonListener bl = new ButtonListener();
		
		this.windowHeader();
		this.questionPanels();
		this.AnswerPanels();

		// Add the Message Panel
		getContentPane().add(messageField,BorderLayout.SOUTH);

		// put some text in the Fields
		examTitleText.setText("< No exam Selected >");
		newQText.setText("< Cannot add a Question >");
		qDisplayList.setText("< No exam selected >");
		newAText.setText("< Cannot add an Answer >");
		correctAdisplay.setText("< No exam selected >");
		wrongADisplay.setText("< No exam selected >");
		
		this.pack();
	}
	
	private void windowHeader(){
		// Set up the Head of the Window
		JPanel titlePanel = new JPanel();
		titlePanel.add(new Label("Exam name:"));
		examTitleText.setToolTipText("The File name of the Exam.");
		titlePanel.add(examTitleText);
		
		newExamButton.setToolTipText("Create a New Exam.");
		newExamButton.addActionListener(bl);
		titlePanel.add(newExamButton);
		
		loadExamButton.setToolTipText("Load Exam from file.");
		loadExamButton.addActionListener(bl);
		titlePanel.add(loadExamButton);
		
		saveExamButton.setToolTipText("save current Exam to file.");
		saveExamButton.addActionListener(bl);
		titlePanel.add(saveExamButton);
		
		getContentPane().add(titlePanel,BorderLayout.NORTH);
	}
	
	private void questionPanels(){
		// create the Question Panel
		Box mainQBox = new Box(BoxLayout.Y_AXIS);
		// Editor Panel
		mainQBox.add(new JLabel("Add/Change Question:") );
		newQText.setToolTipText("Enter new or change Questions.");
		mainQBox.add(new JScrollPane(newQText));

		// Display Panel
		mainQBox.add(new JLabel("current Question:"));
		qDisplayList.setToolTipText("Questions created so far.");
		qDisplayList.setEditable(false);
		mainQBox.add(new JScrollPane(qDisplayList));
		
		// Selection Panel
		JPanel qSelectPanel = new JPanel();
		selectQNum.setToolTipText("Number of Question to select.");
		qSelectPanel.add(selectQNum);
		
		selectQ.setToolTipText("Select Question with given number.");
		selectQ.addActionListener(bl);
		qSelectPanel.add(selectQ);
		
		mainQBox.add(qSelectPanel);
		
		// Button Panel
		JPanel qButtonPanel = new JPanel();
		addQ.setToolTipText("Add New Question to Exam.");
		addQ.addActionListener(bl);
		qButtonPanel.add(addQ);
		
		updateQ.setToolTipText("Update selected Question.");
		updateQ.addActionListener(bl);
		qButtonPanel.add(updateQ);
		
		delQ.setToolTipText("Delete selected Question.");
		delQ.addActionListener(bl);
		qButtonPanel.add(delQ);
		
		mainQBox.add(qButtonPanel);
		
		// add the Question box to the ContentPane
		centrePanel.add(mainQBox,BorderLayout.CENTER);
	}
	
	private void AnswerPanels(){
		// Create the Answer Panel
		Box mainABox = new Box(BoxLayout.Y_AXIS);
		// Editor Panel
		mainABox.add(new JLabel("Add/Change Answer:") );
		newAText.setToolTipText("Enter new or change Answer.");
		mainABox.add(new JScrollPane(newAText));
		
		// Correct Answer editor Panel
		mainABox.add(new JLabel("Correct Answer:"));
		correctAdisplay.setToolTipText
						("Correct Answer to selected Question.");
		mainABox.add(new JScrollPane(correctAdisplay));

		// Wrong Answer Display Panel
		mainABox.add(new JLabel("Wrong Answers:"));
		wrongADisplay.setToolTipText
						("Wrong Answers to selected Question.");
		wrongADisplay.setEditable(false);
		mainABox.add(new JScrollPane(wrongADisplay));
		
		// Answer Selection Panel
		JPanel aSelectPanel = new JPanel();
		selectANum.setToolTipText("Number of Answer to select.");
		aSelectPanel.add(selectANum);
		
		selectA.setToolTipText("Select Answer with given number.");
		selectA.addActionListener(bl);
		aSelectPanel.add(selectA);
		
		mainABox.add(aSelectPanel);
		
		// Answer Button Panel
		JPanel aButtonPanel = new JPanel();
		addA.setToolTipText("Add New Answer to Exam.");
		addA.addActionListener(bl);
		aButtonPanel.add(addA);
		
		updateA.setToolTipText("Update selected Answer.");
		updateA.addActionListener(bl);
		aButtonPanel.add(updateA);
		
		delA.setToolTipText("Delete selected Answer.");
		delA.addActionListener(bl);
		aButtonPanel.add(delA);
		
		mainABox.add(aButtonPanel);
		
		// add the answer to the ContentPane
		centrePanel.add(mainABox);
	}
	 
	/** Exit the Application */
	private void exitTool()
	{
		System.exit (0);
	}
	 
	/** Start the Application */
	public static void main (String [] args)
	{
		ExamCreator adminWindow = new ExamCreator();
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
public class ButtonListener implements ActionListener{
	/**
	 * Depending on which button is pressed carry out action
	 */
	public void actionPerformed(ActionEvent event)
	{
	  String buttonString = event.getActionCommand();


	  // Exam Buttons
	  if (buttonString.equals("NEW"))
	  {
		theExam = new Exam();
		System.out.println("New Exam Created");
		examTitleText.setText("< No Name >");
		selectedQ = null;
		selectedA = null;
		newQText.setText("< Create new Questions Here >");
		qDisplayList.setText("< No Questions Created >");
		newAText.setText("< No Question Selected >");
		correctAdisplay.setText("< No Question Selected >");
		wrongADisplay.setText("< No Question Selected >");
	  }
	  if (buttonString.equals("LOAD"))
	  {
		System.out.println("Now loading");
		loadExam();
	  }
	  if (buttonString.equals("SAVE"))
	  {
		System.out.println("now saving");
		saveExam();
	  }

	  // Question Buttons
	  if (buttonString.equals("SELECT Q"))
	  {
	  try{
	  System.out.println("Selecting a Question.");
	  // get handle on the selected Question 
	  selectQIndex = Integer.parseInt(selectQNum.getText());
	  selectedQ = theExam.getQuestion(selectQIndex);
	  updateTextFields();
	  } // end of try
	  catch(Exception e)
	  {
		System.out.println(e);
		messageField.setText(e.toString());
	  }
	  }
	  
	  if (buttonString.equals("ADD Q"))
	  {
		try{
		System.out.println("adding a question");
		String tmpS = newQText.getText();
		selectedQ = new Question(tmpS);
		theExam.addQuestion(selectedQ);
		newQText.setText(selectedQ.getDisplay());
		updateTextFields();
		} // End of try
		catch(Exception e)
		{
			System.out.println(e);
			messageField.setText(e.toString());
		}
	  }
	  if (buttonString.equals("UPDATE Q"))
	  {
		System.out.println("Updating Question.");
	  }
	  if (buttonString.equals("DEL Q"))
	  {
		System.out.println("deleating a Question");
	  }
	  
	  // Answer Buttons
	  if (buttonString.equals("SELECT A"))
	  {
		System.out.println("Selecting Answer");
	  }
	  if (buttonString.equals("ADD A"))
	  {
		  try{
		  System.out.println("adding an Answer");
		  String tmpS = newAText.getText();
		  selectedA = new Answer(tmpS);
		  selectedQ.addWrongAns(selectedA);
		  updateTextFields();
		  } //End of try
		  catch(Exception e)
		  {
		  	System.out.println(e);
			messageField.setText(e.toString());
		  }
	  }
	  if (buttonString.equals("UPDATE A"))
	  {
		System.out.println("Updating Answer.");
	  }
	  if (buttonString.equals("DEL A"))
	  {
		  System.out.println("deleting an Answer");
	  }
	}
	 
	private void loadExam()
	{
	 	try{
		String fileName = examTitleText.getText() + ".XAM";
		File myFile = new File(fileName);
		if(myFile.exists())
		{
				ObjectInputStream myStream = new ObjectInputStream(
													  new BufferedInputStream(
													  new FileInputStream(myFile)));

				messageField.setText("Reading from the file now....");
				theExam = (Exam) myStream.readObject();
			messageField.setText("File read successfull.");
			selectedQ = null;
			selectedA = null;
		}
		else
		{
			messageField.setText("File not found.");
		}
		updateTextFields();

		} // end of try
		catch(Exception e)
		{
			System.out.println(e);
			messageField.setText(e.toString());
		}
	}
	 
	/**
	 * Saves the Exam to a file with the name specified in the Name field with .XAM as the Extention.
	 * NB. There is no test in here to check that there is a name in the name field.
	 */ 
	private void saveExam(){
	 	try{
		String fileName = examTitleText.getText() + ".XAM";
		File myFile = new File(fileName);
		  ObjectOutputStream myStream = new ObjectOutputStream(
						  				  new BufferedOutputStream(
						  				  new FileOutputStream(myFile)));
		  messageField.setText("Writing Exam to file....");
		myStream.writeObject(theExam);
		myStream.close();
		messageField.setText("File Written");
		} // end of try
		catch(Exception e){
			System.out.println(e);
			messageField.setText(e.toString());
		}
	}
	
	/**
	 * Call this to update all the text fields with the current data.
	 */ 
	private void updateTextFields(){
		try{
		if(theExam != null){
			qDisplayList.setText(theExam.getFullDisplay());
			// Display the selected Question
			if(selectedQ != null){
			newQText.setText(selectedQ.getDisplay());
			selectQNum.setText(Integer.toString(selectQIndex));
				if(selectedQ.getCorrectAns() != null){
					correctAdisplay.setText(selectedQ.getCorrectAns().getDisplay());
				}
				else{
					correctAdisplay.setText("< No Correct Answer set >");
				}
				// Display the wrong Answers for the selected Question
				if(selectedQ.getAnswerCount() != 0){
					int wrongAnsNum = selectedQ.getAnswerCount();
					String tmpS = "";
					for(int i=0; i<wrongAnsNum; i++){
						tmpS += i + ".   ";
						tmpS += selectedQ.getWrongAns(i).getDisplay();
						tmpS += "\n";
					}
					wrongADisplay.setText(tmpS);
				}
				else{
					wrongADisplay.setText("< No Wrong Answers set >");
				}
			}
			else{
				newQText.setText("< No Question selected >");
			}
			// Display the Selected Answer
			if(selectedA != null){
				newAText.setText(selectedA.getDisplay());
			}
			else{
				newAText.setText("< No Answer Selected >");
			}
			// Display the correct Answer for the selected Question
		}
		} // End of try
		catch(Exception e){
			System.out.println(e + "\n In update method.");
			messageField.setText(e.toString());
		}
	}
}// End of inner class
}