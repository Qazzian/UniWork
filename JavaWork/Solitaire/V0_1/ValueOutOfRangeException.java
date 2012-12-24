public class ValueOutOfRangeException extends Exception{

	public ValueOutOfRangeException(){
		super("That value is out of range!");
	}
	
	public ValueOutOfRangeException(String s){
		super(s);
	}
}