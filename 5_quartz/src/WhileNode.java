
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
	public Object eval(Env env) throws ParseException {
		result = 0;
		while((int)whl.eval(env) != 0){
			result = (int)doBlock.eval(env);
		}
		return result;
	}

	public String toString(){
		return "while " + whl + " do " + doBlock + " end"; 
	}
}
