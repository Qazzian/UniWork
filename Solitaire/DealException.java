/**
 * DealExcption is thrown when a Problem is encounterd while dealing a card.
 */
public class DealException extends Exception{

	DealException(){
		super("There was a problem Dealing the card!");
	}
	
	DealException(String s){
		super(s);
	}
}