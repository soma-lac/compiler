
public class WhileNode extends Node {
	private int result;
	private Node whl;
	private Node doBlock;
	
	public WhileNode(int line, Node whl, Node doBlock){
		super(line);
		this.whl = whl;
		this.doBlock = doBlock;
	}	

	@Override
	public int eval(Env env) throws ParseException {
		result = 0;
		while(whl.eval(env) != 0){
			result = doBlock.eval(env);
		}
		return result;
	}

	public String toString(){
		return "while " + whl + " do " + doBlock + " end"; 
	}
}
