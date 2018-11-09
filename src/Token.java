
public class Token extends AbstractToken{
	private int type;
	private int value;
	
	public Token(int type, int value) {
		this.type = type;
		this.value = value;
	}
	
	public int getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
