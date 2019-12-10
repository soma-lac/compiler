
public class NumberNode extends Node {
	private int num;
	
	public NumberNode(int line, int num){
		super(line);
		this.num = num;
	}

	@Override
	public int eval(Env env) throws ParseException {
		if(env.containsKey(num)){
			return env.get(num);
		}
		throw new ParseException(num +"は未定義だよ" + where());
	}
	public int num(){return num;}	
	public String toString(){return num + "";}

}
