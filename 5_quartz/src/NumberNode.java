
public class NumberNode extends Node {
	private int num;
	
	public NumberNode(int line, int num){
		super(line);
		this.num = num;
	}

	@Override
	
	public Object eval(Env env){return num;}	
	public String toString(){return String.valueOf(num);}

}
