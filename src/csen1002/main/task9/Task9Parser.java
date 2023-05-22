// Generated from D:/Academic/ACL/Implementation/grammars\Task9.g4 by ANTLR 4.12.0
package csen1002.main.task9;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Task9Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3;
	public static final int
		RULE_s = 0, RULE_a = 1, RULE_b = 2, RULE_c = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"s", "a", "b", "c"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'a'", "'b'", "'c'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Task9.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		/**
		 * Compares two integer numbers
		 *
		 * @param x the first number to compare
		 * @param y the second number to compare
		 * @return 1 if x is equal to y, and 0 otherwise
		 */
		public static int equals(int x, int y) {
		    return x == y ? 1 : 0;
		}

	public Task9Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SContext extends ParserRuleContext {
		public int check;
		public AContext a;
		public CContext c;
		public BContext b;
		public AContext a() {
			return getRuleContext(AContext.class,0);
		}
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public BContext b() {
			return getRuleContext(BContext.class,0);
		}
		public SContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_s);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			((SContext)_localctx).a = a();
			setState(9);
			((SContext)_localctx).c = c(((SContext)_localctx).a.y, ((SContext)_localctx).a.x, 1, 0);
			setState(10);
			((SContext)_localctx).b = b();
			((SContext)_localctx).check =  ((SContext)_localctx).c.slf * ((SContext)_localctx).c.suf * equals(((SContext)_localctx).a.n, ((SContext)_localctx).b.n);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AContext extends ParserRuleContext {
		public int n;
		public int x;
		public int y;
		public AContext a1;
		public AContext a() {
			return getRuleContext(AContext.class,0);
		}
		public AContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterA(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitA(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitA(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AContext a() throws RecognitionException {
		AContext _localctx = new AContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_a);
		try {
			setState(18);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(13);
				match(T__0);
				setState(14);
				((AContext)_localctx).a1 = a();

				            ((AContext)_localctx).n =  ((AContext)_localctx).a1.n + 1;
				            ((AContext)_localctx).x =  ((AContext)_localctx).a1.x * 2;
				            ((AContext)_localctx).y =  ((AContext)_localctx).a1.y * 3;
				        
				}
				break;
			case EOF:
			case T__1:
			case T__2:
				enterOuterAlt(_localctx, 2);
				{

				            ((AContext)_localctx).n =  0;
				            ((AContext)_localctx).x =  1;
				            ((AContext)_localctx).y =  1;
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BContext extends ParserRuleContext {
		public int n;
		public BContext b1;
		public BContext b() {
			return getRuleContext(BContext.class,0);
		}
		public BContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitB(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitB(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BContext b() throws RecognitionException {
		BContext _localctx = new BContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_b);
		try {
			setState(25);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				match(T__1);
				setState(21);
				((BContext)_localctx).b1 = b();

				            ((BContext)_localctx).n =  ((BContext)_localctx).b1.n + 1;
				        
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{

				            ((BContext)_localctx).n =  0;
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CContext extends ParserRuleContext {
		public int u;
		public int l;
		public int iuf;
		public int ilf;
		public int slf;
		public int suf;
		public int m;
		public CContext c1;
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public CContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CContext(ParserRuleContext parent, int invokingState, int u, int l, int iuf, int ilf) {
			super(parent, invokingState);
			this.u = u;
			this.l = l;
			this.iuf = iuf;
			this.ilf = ilf;
		}
		@Override public int getRuleIndex() { return RULE_c; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterC(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitC(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitC(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CContext c(int u,int l,int iuf,int ilf) throws RecognitionException {
		CContext _localctx = new CContext(_ctx, getState(), u, l, iuf, ilf);
		enterRule(_localctx, 6, RULE_c);
		try {
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				match(T__2);
				setState(28);
				((CContext)_localctx).c1 = c(_localctx.u, _localctx.l, _localctx.iuf, _localctx.ilf);

				        ((CContext)_localctx).m =  ((CContext)_localctx).c1.m + 1;
				        ((CContext)_localctx).slf =  ((CContext)_localctx).c1.slf + equals(_localctx.l, _localctx.m);
				        ((CContext)_localctx).suf =  ((CContext)_localctx).c1.suf - equals(_localctx.u, ((CContext)_localctx).c1.m);
				    
				}
				break;
			case EOF:
			case T__1:
				enterOuterAlt(_localctx, 2);
				{

				        ((CContext)_localctx).slf =  _localctx.ilf;
				        ((CContext)_localctx).suf =  _localctx.iuf;
				        ((CContext)_localctx).m =  0;
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0003#\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u0013\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u001a\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003!\b\u0003\u0001"+
		"\u0003\u0000\u0000\u0004\u0000\u0002\u0004\u0006\u0000\u0000!\u0000\b"+
		"\u0001\u0000\u0000\u0000\u0002\u0012\u0001\u0000\u0000\u0000\u0004\u0019"+
		"\u0001\u0000\u0000\u0000\u0006 \u0001\u0000\u0000\u0000\b\t\u0003\u0002"+
		"\u0001\u0000\t\n\u0003\u0006\u0003\u0000\n\u000b\u0003\u0004\u0002\u0000"+
		"\u000b\f\u0006\u0000\uffff\uffff\u0000\f\u0001\u0001\u0000\u0000\u0000"+
		"\r\u000e\u0005\u0001\u0000\u0000\u000e\u000f\u0003\u0002\u0001\u0000\u000f"+
		"\u0010\u0006\u0001\uffff\uffff\u0000\u0010\u0013\u0001\u0000\u0000\u0000"+
		"\u0011\u0013\u0006\u0001\uffff\uffff\u0000\u0012\r\u0001\u0000\u0000\u0000"+
		"\u0012\u0011\u0001\u0000\u0000\u0000\u0013\u0003\u0001\u0000\u0000\u0000"+
		"\u0014\u0015\u0005\u0002\u0000\u0000\u0015\u0016\u0003\u0004\u0002\u0000"+
		"\u0016\u0017\u0006\u0002\uffff\uffff\u0000\u0017\u001a\u0001\u0000\u0000"+
		"\u0000\u0018\u001a\u0006\u0002\uffff\uffff\u0000\u0019\u0014\u0001\u0000"+
		"\u0000\u0000\u0019\u0018\u0001\u0000\u0000\u0000\u001a\u0005\u0001\u0000"+
		"\u0000\u0000\u001b\u001c\u0005\u0003\u0000\u0000\u001c\u001d\u0003\u0006"+
		"\u0003\u0000\u001d\u001e\u0006\u0003\uffff\uffff\u0000\u001e!\u0001\u0000"+
		"\u0000\u0000\u001f!\u0006\u0003\uffff\uffff\u0000 \u001b\u0001\u0000\u0000"+
		"\u0000 \u001f\u0001\u0000\u0000\u0000!\u0007\u0001\u0000\u0000\u0000\u0003"+
		"\u0012\u0019 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}