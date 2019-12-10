
public class NegExprNode extends Node {
	private int num;
	private String op;
	
	public NegExprNode(int line, int num, String op){
		super(line);
		this.num = num;
		this.op = op;
	}
	
	@Override
	public int eval(Env env) throws ParseException {
		if(env.containsKey(op)){
			return env.get(op);
		}
		throw new ParseException(op + "は未定義のオペランドです" + where());
	}
	public int num(){return -num;}
	public String toString(){return "(" + op + num + ")";}

}
