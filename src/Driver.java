import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		ControllerCommandsParser parser = new ControllerCommandsParser();
		
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter command: ");
		String input = scn.nextLine();
		ArrayList<Token> words = new ArrayList<Token>();

		if(parser.parse(input, words)) {
			
		}
		else System.out.println("Lexical Error");
		
		
		for (Token token : words)
			System.out.println(token.getValue());
		
		
	}
	
}
