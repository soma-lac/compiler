
public class PosExprNode extends Node {
	private String op;
	private Node opr;
	
	public PosExprNode(int line,Node opr, String op){
		super(line);
		this.opr = opr;
		this.op = op;
	}
	
	@Override
	public Object eval(Env env) throws ParseException {
		return opr.eval(env);
	}
	public String toString(){return "(" + op + opr + ")";}

}
