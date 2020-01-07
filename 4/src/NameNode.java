
public class NameNode extends Node {
	private String name;
	public NameNode(int line, String name){
		super(line);
		this.name = name;
	}
	
	public int eval(Env env) throws ParseException {
		if(env.containsKey(name)){
			return env.get(name);
		}
		throw new ParseException(name + "は未定義の変数です" + where());
	}
	public String name(){return name;}
	public String toString(){return name;}
}
