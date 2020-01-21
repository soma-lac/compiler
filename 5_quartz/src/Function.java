import java.util.List;

public class Function {
		private Env outer;
		private List<String> params;
		private Node body;
		
		public Function(Env outer, List<String> params, Node body){
			this.outer = outer;
			this.params = params;
			this.body = body;
		}
		
		public int arity(){return params.size();}
		
		public Object exec(List<Object> vals) throws ParseException{
			LocalEnv local = new LocalEnv();
			local.setOuter(outer);
			for(int i=0; i < params.size();i++)
				local.put(params.get(i),vals.get(i));
			return body.eval(local);
		}
		
		public String toString() { return "fun/" + arity(); }
		
}
