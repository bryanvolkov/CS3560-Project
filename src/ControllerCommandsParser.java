import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerCommandsParser extends AbstractParser{
	
	public int MOVE = 0;
	public int SHOOT = 1;
	public int NORTH = 2;
	public int SOUTH = 3;
	public int WEST = 4;
	public int EAST = 5;
	public int GO = 6;
	public int PICKUP = 7;
	public int EAT = 8;
	public int EXAMINE = 9;
	
	public int COMMAND = 0;
	public int NUMBER = 1;
	
	Map<String, Integer> keywords;
	
	public ControllerCommandsParser() {
		super();
		words = new ArrayList<>();
		keywords = new HashMap<String, Integer>();
		
		keywords.put("move", MOVE);
		keywords.put("shoot", SHOOT);
		keywords.put("north", NORTH);
		keywords.put("south", SOUTH);
		keywords.put("west", WEST);
		keywords.put("east", EAST);
		keywords.put("go", GO);
		keywords.put("pickup", PICKUP);
		keywords.put("eat", EAT);
		keywords.put("examine", EXAMINE);
	}
	
	// This interface is used to make an array of functions
	private interface checkState{
		void check(char c);
	}
	
	// Array of functions
	// Contains the functions that represent the states 
	private checkState[] states = new checkState[] {
		new checkState() {public void check(char c) {state0(c);}},
		new checkState() {public void check(char c) {state1(c);}},
		new checkState() {public void check(char c) {state2(c);}}
	};
	
	Map<String, Integer> commands;
	
	private static final char SPACE = ' ';
	private int STATE_0 = 0;
	private int STATE_1 = 1;
	private int STATE_2 = 2;
	private ArrayList<Token> words;
	private int state;
	private boolean error;
	private int start;
	private int i;
	private String str;
	
	public boolean parse(String input, ArrayList<Token> commands){
		state = STATE_0;
		start = 0;
		error = false;
		str = input;
		for(i = 0; i < str.length(); i++) {
			states[state].check(input.charAt(i));
			if(error) return !error;}

		states[state].check(SPACE);
		commands.addAll(words);
		
		return !error;
	}
	
	private void state0(char c) {
		if(c == SPACE) start = i+1;
		else if (isLetter(c)) state = STATE_1;
		else if (isNumber(c)) state = STATE_2;
		else error = true;
	}
	
	private void state1(char c) {
		if(c == SPACE) {
			//words.add();
			String k = str.substring(start, i);
			if(keywords.containsKey(k)) {
				words.add(new Token(COMMAND, keywords.get(k)));
			}
			
			start = i+1;
			state = STATE_0;
		}
		else if(!isLetter(c)) error = true;
	}
	
	private void state2(char c) {
		if(c == SPACE) {
			words.add(new Token(NUMBER, Integer.parseInt(str.substring(start, i))));
			start = i+1;
			state = STATE_0;
		}
		else if(!isNumber(c)) error = true;
	}
	
	private boolean isLetter(char c) {
		return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
	}
	
	private boolean isNumber(char c) {
		return '0' <= c && c <= '9';
	}
}
