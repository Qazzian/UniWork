import java.applet.Applet;
import java.math.*;
import java.io.*;

public class MyApp extends Applet {
    public static final double MYMINDELTA = 0.00001; // accuracy to this level
	boolean giveReport = false;

    public static void main(String args[]) {
      	MyApp f = new MyApp();
    }

    public MyApp(){  //This routine sets up problem values and calls wallHidesPeople
        boolean result;
	    DPoint wall1, wall2, person1, person2;
	    double [] pointsInProb1 = {  -1, -2, -2, -1,  4, 6, -2, -3,
	                                -1, -1, -5, -2, -1, 3, 2, 1 };
	    double [] pointsInProb2 = {  0, 0, -1, 5, 2, 1, 3, 3,
	                                 5.5, 0, 0.5, 4, 4, 2, 1, 1 };
	    int index;

        // Show a case in full details
        wall1 = new DPoint( 1, 2 );
        wall2 = new DPoint( 4, 7 );
        person1 = new DPoint( 3, 6 );
        person2 = new DPoint( 5, 2 );

        giveReport = true; // show people what the full output looks like
        result = wallHidesPeople( wall1, wall2, person1, person2 );
        System.out.print( "With wall from " + wall1 + " to "  + wall2 + ", people at " + person1 + " and " + person2 + " can" );
        if (result) {  System.out.print( "'t" ); }
        System.out.println( " see each other." );
        System.out.println( " " );
        System.out.println( " " );

        // Now try out the test cases in problem 14 on handout 2
        giveReport = false;

        // First set of tests with wall from (0,0) to (2,3)
		System.out.println( " PART A ANSWERS:" );
        for (index=0;index<pointsInProb1.length;index=index+4) {
            wall1 = new DPoint( 0, 0);
            wall2 = new DPoint( 2,3);
            person1 = new DPoint( pointsInProb1[index], pointsInProb1[index+1] );
            person2 = new DPoint( pointsInProb1[index+2], pointsInProb1[index+3] );
            result = wallHidesPeople( wall1, wall2, person1, person2 );
            System.out.print( " With wall from " +wall1 + " to " +wall2 + ", people at " + person1 + " and " + person2 + " can" );
            if (result) { System.out.print( "'t" ); }
            System.out.println( " see each other." );
        }

        // Second set of tests with wall from (4,1) to (1,3)
		System.out.println( " PART B ANSWERS:" );
        for (index=0;index<pointsInProb2.length;index=index+4) {
            wall1 = new DPoint( 4, 1 );
            wall2 = new DPoint( 1, 3 );
            person1 = new DPoint( pointsInProb2[index], pointsInProb2[index+1] );
            person2 = new DPoint( pointsInProb2[index+2], pointsInProb2[index+3] );
            result = wallHidesPeople( wall1, wall2, person1, person2 );
            System.out.print( " With wall from " +wall1 + " to " +wall2 + ", people at " + person1 + " and " + person2 + " can" );
            if (result) {  System.out.print( "'t" ); }
            System.out.println( " see each other." );
        }
    };


    boolean wallHidesPeople(DPoint wall1, DPoint wall2, DPoint person1, DPoint person2){
    // This routine returns true if the line from wall1 to wall2 obscures
    // the view from person1 to person2
    	DPoint newAxis = wall1;
    	DPoint rotatedPoint;
    	double gradient;
    	double yIntersect;
    	double xIntersect;

    	// if printout wanted, show the parameters
    	if (giveReport) {
            System.out.println( "The wall is from " + wall1 + " to " + wall2 );
            System.out.println( "The people are at " + person1 + " and " + person2 );
    	}

    	// First, translate axes so that wall1 is at the origin
    	wall1 = new DPoint( 0, 0 ); // easy!!
    	wall2 = axisTranslate( wall2, newAxis );
    	person1 = axisTranslate( person1, newAxis );
    	person2 = axisTranslate( person2, newAxis );

    	if (giveReport) {
            System.out.println( " " );
            System.out.println( "Shift origin to " + wall1 );
            System.out.println( "The wall is now from " + wall1 + " to " + wall2 );            
            System.out.println( "The people are at " + person1 + " and " + person2 );
    	}

    	// Now rotate the axes about the origin to put wall2 on the + x-axis
    	rotatedPoint = wall2;
    	wall2 = axisRotateOrigin( wall2, rotatedPoint );
    	person1 = axisRotateOrigin( person1, rotatedPoint );
    	person2 = axisRotateOrigin( person2, rotatedPoint );
    	if (giveReport) {
            System.out.println( " " );
            System.out.println( "Rotate axis so that wall2 is on the x-axis" );
            System.out.println( "The wall is now from " + wall1 + " to " + wall2 );
            System.out.println( "The people are at " + person1 + " and " + person2 );
    	}

    	// Check for a subtle problem. Because of number representation errors,
    	// if the people are on the x-axis, then their x values might not quite
    	// be zero, and so checks for zero would not work. If they are very
    	// close to zero, make them zero. Another solution would be to have an
    	// equality test that returned true comparing zero and nearly zero.
    	person1 = zeroFixed( person1);
    	person2 = zeroFixed( person2);

    	// First test whether both people are on the same side of the x-axis
    	// or both to left or right of wall.
    	// If they are, then they must be able to see each other.
    	if ( ((person1.y > 0.0) && (person2.y > 0.0))
    	     || ((person1.y < 0.0) && (person2.y < 0.0))
    	     || (( person1.x < 0.0) && (person2.x < 0.0 ))
    	     || ((person1.x > wall2.x) && (person2.x > wall2.x))
    	   ) {
    	    if (giveReport) {
                System.out.println( " " );
                System.out.println("Found special case - both people well away from wall");
      	    }
    	    return( false );
    	}

    	// Now test for nasty case of both having same x value, giving
    	// infinite gradient. Must intersect if so, as not left or right of wall.
    	if (approxEqual( person1.x, person2.x )) {
    	    if (giveReport) {
                System.out.println( " " );
                System.out.print( "Found special case - " );
                System.out.println( "both people have same x val within wall" );
      	    }
    	    return( true );
    	}

    	// Third special case - both on x axis, and on opposite sides of wall
    	if ((person1.y == 0.0) && (person2.y == 0.0)) {
    	    if (giveReport) {
                System.out.println( " " );
                System.out.println( "Found special case - people either end of wall" );
      	    }
    	    return( true ); // must be on opposite sides as already tested
    	}

    	//Finally, work out the equation of the line from person1 to person2
    	// and substitute y = 0 to find out where it intersects the x-axis
    	// if it is between zero and wall2.x, then the wall is in the way
    	// Uses c = y - mx, and then x = (y - c) / m
    	gradient = (person1.y - person2.y) / (person1.x - person2.x);
    	yIntersect = person1.y - gradient * person1.x;

        xIntersect = -yIntersect / gradient;
    	if (giveReport) {
            System.out.println( " " );
            System.out.println( "Gradient is " + gradient
                                + "; y intersect is " + yIntersect
                                + "; x intersect is " + xIntersect );

        }
        return((xIntersect >= 0) && (xIntersect <= wall2.x));
    }

    DPoint axisTranslate( DPoint oldPoint, DPoint newOrigin ) {
    // Return the position of oldPoint, when origin is moved to newOrigin
        DPoint newPoint = new DPoint( 0, 0 );
    	newPoint.x = oldPoint.x - newOrigin.x;
    	newPoint.y = oldPoint.y - newOrigin.y;
    	return( newPoint );
    }


    DPoint axisRotateOrigin( DPoint oldPoint, DPoint rotateDPoint ) {
        // Return the position of oldPoint, when rotateDPoint is
        // rotated around the origin to lie on the positve x axis
       double angleInRadians;
       DPoint newPoint = new DPoint(0,0);
   
        angleInRadians = Math.atan2( rotateDPoint.y, rotateDPoint.x );
    	newPoint.x = oldPoint.x * Math.cos(angleInRadians)
    	               + oldPoint.y * Math.sin( angleInRadians );
    	newPoint.y = oldPoint.y * Math.cos(angleInRadians)
    	               - oldPoint.x * Math.sin( angleInRadians );
    	return( newPoint );
    }

    boolean approxEqual( double value1, double value2 ) {
        // if the difference between the values is less than delta, return true
        return( (Math.abs( value1 - value2 ) < MYMINDELTA) );
    }

    DPoint zeroFixed( DPoint thePoint ){
       // if either the x or y value is near zero, make it zero
       DPoint outPoint = new DPoint( thePoint.x, thePoint.y );

       if (approxEqual( outPoint.x, 0.0 )) { outPoint.x = 0.0; }
       if (approxEqual( outPoint.x, 0.0 )) { outPoint.y = 0.0; }

       return( outPoint );
    }

}

class DPoint extends Object{
// Defines a Point with real x and y (Java inbuilt Point only has ints)
   double x;
   double y;

   public DPoint( double newX, double newY ) {
      x = newX;
      y = newY;
   }

   public String toString(){
   // Returns a String value for the DPoint
      return( "(" + x + ", " + y + ")");
   }

}

