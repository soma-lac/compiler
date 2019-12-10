
public class BinExprNode extends Node {
	private Node left;
	private String op;
	private Node right;
	
	public BinExprNode(int line, Node left, String op, Node right){
		super(line);
		this.left = left;
		this.op = op;
		this.right = right;
	}
	
	public int eval(Env env) throws ParseException { 
		switch(op){
		case "+":
			return left.eval(env) + right.eval(env);
		case "-":
			return left.eval(env) - right.eval(env);
		case "*":
			return left.eval(env) * right.eval(env);
		case "/":
			return left.eval(env) / right.eval(env);
		case "<":
			return left.eval(env) < right.eval(env) ? 1 : 0;
		case ">":
			return left.eval(env) > right.eval(env) ? 1 : 0;
		case "<=":
			return left.eval(env) <= right.eval(env) ? 1 : 0;
		case ">=":
			return left.eval(env) <= right.eval(env) ? 1 : 0;
		case "==":
			return left.eval(env) == right.eval(env) ? 1 : 0;
		case "!=":
			return left.eval(env) != right.eval(env) ? 1 : 0;
		case "&&":
			return left.eval(env)!= 0 && right.eval(env)!= 0 ? 1 : 0;
		case "||":
			return left.eval(env)!= 0 || right.eval(env)!= 0 ? 1 : 0;
		default:
			throw new ParseException(op + "は未定義のオペレータです" + where());
		}
	}
		
		public String toString(){
			return "(" + left + " " + op + " " + right + ")";
	}

}
