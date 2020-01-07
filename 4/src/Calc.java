/* Generated By:JavaCC: Do not edit this line. Calc.java */
import java.io.*;
import java.util.*;
public class Calc implements CalcConstants {
        private Env env = new Env();
    public static void main(String args [])
    {

        try {
            if (args.length == 0)
                new Calc(System.in).interactive();    // 対話モードへ
            else {
                Reader reader = new BufferedReader(new FileReader(new File(args[0])));
                Calc calc = new Calc(reader);
                calc.eval();
            }
        } catch (ParseException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void interactive() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            String input = "";
            while (true) {
                String line = reader.readLine();
                if (line == null) return;
                input += line;
                if (line.length() == 0 || input.charAt(input.length() - 1) != '\u005c\u005c')
                    break;
                input = input.substring(0, input.length() - 1);
            }
            try {
                ReInit(new StringReader(input));
                eval();
            } catch (ParseException e) {
                System.err.println(e);
            }
        }
    }

  final public void eval() throws ParseException {
   Node root;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IF:
      case WHILE:
      case INTEGER:
      case IDENTIFIER:
      case 20:
      case 21:
      case 25:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      root = stmt();
        System.out.println("tree: " + root);
                System.out.println("=> " + root.eval(env));
    }
    jj_consume_token(0);
  }

  final public Node stmt() throws ParseException {
  Node x;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IF:
      x = ifStmt();
      break;
    case WHILE:
      x = whileStmt();
      break;
    case INTEGER:
    case IDENTIFIER:
    case 20:
    case 21:
    case 25:
      x = simpleStmt();
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  {if (true) return x;}
    throw new Error("Missing return statement in function");
  }

  final public Node ifStmt() throws ParseException {
        Token t;
        Node cond;
        Node thn;
        Node els = null;
    t = jj_consume_token(IF);
    cond = expr();
    jj_consume_token(THEN);
    thn = block();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      els = block();
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
    jj_consume_token(END);
  {if (true) return new IfNode(t.beginLine,cond,thn,els);}
    throw new Error("Missing return statement in function");
  }

  final public Node whileStmt() throws ParseException {
  Node whl;
  Node d;
  Token t;
    t = jj_consume_token(WHILE);
    whl = expr();
    jj_consume_token(DO);
    d = block();
    jj_consume_token(END);
    {if (true) return new WhileNode(t.beginLine,whl,d);}
    throw new Error("Missing return statement in function");
  }

  final public Node block() throws ParseException {
 Node x;
 List<Node> nodes = new ArrayList< Node >();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IF:
      case WHILE:
      case INTEGER:
      case IDENTIFIER:
      case 20:
      case 21:
      case 25:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      x = stmt();
                 nodes.add(x);
    }
    {if (true) return new BlockNode(nodes);}
    throw new Error("Missing return statement in function");
  }

  final public Node simpleStmt() throws ParseException {
  Node x;
    x = expr();
    jj_consume_token(10);
                         {if (true) return x;}
    throw new Error("Missing return statement in function");
  }

  final public Node expr() throws ParseException {
  Token t;
  NameNode n;
  Node x;
    if (jj_2_1(2)) {
      n = name();
      t = jj_consume_token(11);
      x = expr();
     {if (true) return new AssignNode(t.beginLine, n, x);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER:
      case IDENTIFIER:
      case 20:
      case 21:
      case 25:
        x = orExpr();
                         {if (true) return x;}
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public Node orExpr() throws ParseException {
  Node x,y;
  Token t;
    x = andExpr();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 12:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      t = jj_consume_token(12);
      y = andExpr();
                                        x = new BinExprNode(t.beginLine,x,t.image,y);
    }
    {if (true) return x;}
    throw new Error("Missing return statement in function");
  }

  final public Node andExpr() throws ParseException {
  Node x,y;
  Token t;
    x = compExpr();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 13:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_4;
      }
      t = jj_consume_token(13);
      y = compExpr();
                 x = new BinExprNode(t.beginLine,x,t.image,y);
    }
    {if (true) return x;}
    throw new Error("Missing return statement in function");
  }

  final public Node compExpr() throws ParseException {
  Node x,y;
  Token t;
    x = numExpr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 14:
        t = jj_consume_token(14);
        break;
      case 15:
        t = jj_consume_token(15);
        break;
      case 16:
        t = jj_consume_token(16);
        break;
      case 17:
        t = jj_consume_token(17);
        break;
      case 18:
        t = jj_consume_token(18);
        break;
      case 19:
        t = jj_consume_token(19);
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      y = numExpr();
           x = new BinExprNode(t.beginLine, x, t.image, y);
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    {if (true) return x;}
    throw new Error("Missing return statement in function");
  }

  final public Node numExpr() throws ParseException {
        Token t;
        Node x,y;
    x = term();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 20:
      case 21:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 20:
        t = jj_consume_token(20);
        y = term();
        break;
      case 21:
        t = jj_consume_token(21);
        y = term();
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                  x = new BinExprNode(t.beginLine, x, t.image, y);
    }
      {if (true) return x;}
    throw new Error("Missing return statement in function");
  }

  final public Node term() throws ParseException {
  Node x, y;
  Token t;
    x = factor();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 22:
      case 23:
      case 24:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 22:
        t = jj_consume_token(22);
        y = factor();
        break;
      case 23:
        t = jj_consume_token(23);
        y = factor();
        break;
      case 24:
        t = jj_consume_token(24);
        y = factor();
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
            x = new BinExprNode(t.beginLine,x,t.image,y);
    }
    {if (true) return x;}
    throw new Error("Missing return statement in function");
  }

  final public Node factor() throws ParseException {
  Node x;
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
    case IDENTIFIER:
      x = element();
                        {if (true) return x;}
      break;
    case 25:
      t = jj_consume_token(25);
      x = expr();
      jj_consume_token(26);
                                 {if (true) return x;}
      break;
    case 21:
      t = jj_consume_token(21);
      x = factor();
                               {if (true) return new NegExprNode(t.beginLine, x, t.image);}
      break;
    case 20:
      t = jj_consume_token(20);
      x = factor();
                               {if (true) return new PosExprNode(t.beginLine, x, t.image);}
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Node element() throws ParseException {
  NumberNode x;
  NameNode n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      x = number();
                       {if (true) return x;}
      break;
    case IDENTIFIER:
      n = name();
                     {if (true) return n;}
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NumberNode number() throws ParseException {
        Token t;
    t = jj_consume_token(INTEGER);
                 {if (true) return new NumberNode(t.beginLine,Integer.parseInt(t.image));}
    throw new Error("Missing return statement in function");
  }

  final public NameNode name() throws ParseException {
        Token t;
    t = jj_consume_token(IDENTIFIER);
                  {if (true) return new NameNode(t.beginLine,t.image);}
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_3R_7() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_7()) return true;
    if (jj_scan_token(11)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public CalcTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[15];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2300324,0x2300324,0x10,0x2300324,0x2300300,0x1000,0x2000,0xfc000,0xfc000,0x300000,0x300000,0x1c00000,0x1c00000,0x2300300,0x300,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[1];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Calc(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Calc(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CalcTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Calc(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CalcTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Calc(CalcTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(CalcTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[27];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 15; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 27; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
