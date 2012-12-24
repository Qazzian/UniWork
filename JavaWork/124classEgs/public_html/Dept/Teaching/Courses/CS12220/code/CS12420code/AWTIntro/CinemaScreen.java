import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CinemaScreen extends Frame
{
    TaggedButton [][] theButtons;  // The screen holds an array of Buttons

    Screen theScreen;  // the main screen on display

    int currentRow;    // row, seat used to hold an active seat location
    int currentSeat;   // these refer to booking's seat & row - not buttons

    private TextArea nameField =
           new TextArea("",1,20, TextArea.SCROLLBARS_HORIZONTAL_ONLY);

    private TextArea addressField =
           new TextArea("",1,20, TextArea.SCROLLBARS_HORIZONTAL_ONLY);

    private TextArea telNoField =
           new TextArea("",1,20, TextArea.SCROLLBARS_HORIZONTAL_ONLY);

    private TextField videoDateField = new TextField(8);

    /**
     * Constructor to create a new screen AWT
     * @param screenTitle - a String giving the title of this film
     * @param screenCert - a string giving certificate for film
     * @param rows - an integer specifying the number of rows in auditorium
     * @param seats - an integer specifying the number of seats in each row
     */
    public CinemaScreen(String screenTitle,
                        String screenCert,
                        int rows,
                        int seats)
    {
        super("Cindy's Cinema");
        setBounds(50,50,500,400);

        theButtons = new TaggedButton[rows+1][seats+1];

        theScreen = new Screen(screenTitle, screenCert, rows, seats);

        this.setLayout(new GridLayout(3,1));
        this.setBackground(Color.gray);
        this.addWindowListener(new FrameListener());

        /*
         * Let's start by defining a master panel
         */
        Panel masterPanel = new Panel(new GridLayout(2,1));
        Label tempLabel = new Label("            ");
        masterPanel.add(tempLabel);
        tempLabel = new Label("*** Now Showing ***       "
                              + screenTitle
                              + "       *** Now Showing ***", Label.CENTER);

        masterPanel.add(tempLabel);
        this.add(masterPanel);

        /*
         * Now let's define the panel for the seats
         */
        Panel seatPanel = new Panel(new GridLayout(rows+1,seats+1));
        for (int theRow = 0; theRow <= rows; theRow++)
        {
            for (int theSeat =0; theSeat <= seats; theSeat++)
            {
                theButtons[theRow][theSeat] = new TaggedButton(theRow,theSeat);
                seatPanel.add(theButtons[theRow][theSeat]);
            }
        }
        this.add(seatPanel);

        /*
         * Now let's define the window for making the booking
         */
        Panel bookingPanel = new Panel(new GridLayout(4,3));

        // There are three separate rows to this panel
        // Name: <enter name> [new booking button]
        // new boking button clears all lighted seats
        tempLabel = new Label("Name:", Label.CENTER);
        bookingPanel.add(tempLabel);
        bookingPanel.add(nameField);
        Button newBookingButton = new Button("New Booking");
        newBookingButton.addActionListener(new BookingButtonListener());
        bookingPanel.add(newBookingButton);

        // Address: <enter address> [new booking button]
        tempLabel = new Label("Address:", Label.CENTER);
        bookingPanel.add(tempLabel);
        bookingPanel.add(addressField);
        Button confirmBookingButton = new Button("Confirm Booking");
        confirmBookingButton.addActionListener(new BookingButtonListener());
        bookingPanel.add(confirmBookingButton);

        // Phone : <enter tel no> [show booking button]
        // show booking button highlights all buttons that are booked
        tempLabel = new Label("TelNo:", Label.CENTER);
        bookingPanel.add(tempLabel);
        bookingPanel.add(telNoField);
        Button showBookingButton = new Button("Show Bookings");
        showBookingButton.addActionListener(new BookingButtonListener());
        bookingPanel.add(showBookingButton);

        tempLabel = new Label(" ", Label.CENTER);
        bookingPanel.add(tempLabel);
        Button closeScreenButton = new Button("Close Screen");
        closeScreenButton.addActionListener(new BookingButtonListener());
        bookingPanel.add(closeScreenButton);
        this.add(bookingPanel);
    }

    /**
     * highlightBookings colors in all booked seats
     */
    public void highlightBookings()
    {
        for (int theRow = 1; theRow < theButtons.length; theRow++)
        {
            for (int theSeat = 1; theSeat < theButtons[1].length; theSeat++)
            {
                if (!theScreen.isEmpty(theRow-1, theSeat-1))
                {
                    theButtons[theRow][theSeat].setBackground(Color.orange);
                }
            }
        }
    }

    /**
     * unHighlightBookings colors in all booked seats
     */
    public void unHighlightBookings()
    {
        for (int theRow = 1; theRow < theButtons.length; theRow++)
        {
            for (int theSeat =1; theSeat < theButtons[1].length; theSeat++)
            {
                theButtons[theRow][theSeat].setBackground(Color.white);
            }
        }
    }

    private void closeWindow()
    {
        this.setVisible(false);
    }

    private void openWindow()
    {
        this.setVisible(true);
    }

    private void exit()
    {
        dispose();  // removes the screen
        System.exit(0);
    }


    public static void main (String [] args)
    {
        CinemaScreen screen1 = new CinemaScreen("Titanic","12",5,10);
        CinemaScreen screen2 = new CinemaScreen("Jaws","12",5,10);
        screen1.setVisible(true);
        screen2.setVisible(true);
    }

    /*
     *                  Inner Class Definition - TaggedButton
     */

    /**
     * TaggedButton provides a simple Button that records its corresponding
     * location in booking array BUT see getRow and getSeat below.
     */
    public class TaggedButton extends Button
    {
        private int row;
        private int seat;

        public TaggedButton(int rowPosition, int seatPosition)
        {
            row = rowPosition;
            seat = seatPosition;

            // Set up ordinary seats
            this.setLabel("  ");
            this.setBackground(Color.white);

            // Set up headers
            if ((row==0) & (seat !=0))
            {
                this.setLabel("Seat " + (char)(seat-1 + (int)'A'));
                this.setBackground(Color.green);
                return;
            }

            if ((seat==0) & (row !=0))
            {
                this.setLabel("Row " + row);
                this.setBackground(Color.red);
                return;
            }

            if ((seat != 0) & (row != 0))
            {
                // What is left must be an actual seat
                this.setLabel("  ");
                this.setBackground(Color.white);

                // Make seats selectable
                this.addActionListener(new SeatButtonListener());
            }
        }

        /**
         * getRow passes back the current row BUT as the heading buttons
         * don't count - we actually have to deduct 1.
         * Row will then corrcetly correspond with bookings array.
         * @return integer corresponding with appropriate array position
         */
        public int getRow()
        {
            return row-1;
        }

        /**
         * getSeat passes back the current seat BUT as the heading buttons
         * don't count - we actually have to deduct 1.
         * Seat will then correctly correspond with bookings array.
         * @return integer corresponding with appropriate array position
         */
        public int getSeat()
        {
            return seat-1;
        }
    }
    /*
     *                  Inner Class Definition - FrameListener
     */

    /**
     * FrameListener - listens out for user operations on the frame
     */
    public class FrameListener extends WindowAdapter
                               implements WindowListener
    {
        public void windowClosing(WindowEvent event)
        {
            exit();
        }
    }
    
    /*
     *                  Inner Class Definition - SeatButtonListener
     */

    /**
     * SeatButtonListener - listens out for user operations on the button
     */
    public class SeatButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            TaggedButton clickedButton = (TaggedButton)event.getSource();
            clickedButton.setLabel("X");
            clickedButton.setBackground(Color.blue);
            // Call Cinema with clickedButton.getRow(),clickedButton.getSeat()
            currentRow = clickedButton.getRow();
            currentSeat = clickedButton.getSeat();

            Booking temp = theScreen.getBooking(currentRow, currentSeat);

            if (temp != null)
            {
                nameField.setText(temp.getName());
                addressField.setText(temp.getAddress());
                telNoField.setText(temp.getTelNo());
            }
        }
    }

    /*
     *                  Inner Class Definition - BookingButtonListener
     */
    /**
     * Listen for button pressed
     */
    public class BookingButtonListener implements ActionListener
    {
		/**
         * Depending on which button is pressed carry out action
         */
        public void actionPerformed(ActionEvent event)
        {
            String button = event.getActionCommand();

            String enteredName = nameField.getText();
            String enteredAddress = addressField.getText();
            String enteredTelNo = telNoField.getText();

            if (button.equals("New Booking"))
            {
                System.out.println("Turning off all lighted seats");
                unHighlightBookings();
                // Clear entries in text fields
                nameField.setText(" ");
                addressField.setText(" ");
                telNoField.setText(" ");
            }

            if (button.equals("Confirm Booking"))
            {
                enteredName = nameField.getText();
                enteredAddress = addressField.getText();
                enteredTelNo = telNoField.getText();

                Booking temp = new Booking(enteredName,
                                           enteredAddress,
                                           enteredTelNo);

                theScreen.reserveSeat(temp, currentRow, currentSeat);

                System.out.println("Making a booking  \t currentRow +1 "
                                   + currentRow + "\t currentSeat "
                                   + (char)(currentSeat + (int)'A'));
            }

            if (button.equals("Show Bookings"))
            {
                System.out.println("Highlighting all booked seats");
                highlightBookings();
            }

            if (button.equals("Close Screen"))
            {
                 closeWindow();
            }
        }
    }

}