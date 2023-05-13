// Generated from D:/Academic/ACL/Implementation/grammars\Task8.g4 by ANTLR 4.12.0
package csen1002.main.task8;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Task8Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, COMP=3, ID=4, NUM=5, LIT=6, LP=7, RP=8, WS=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "COMP", "ID", "NUM", "DIGIT", "DECIMAL", "EXPONENT", "LIT", 
			"DQ", "BS", "LP", "RP", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "COMP", "ID", "NUM", "LIT", "LP", "RP", "WS"
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


	public Task8Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Task8.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\tq\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002/\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0005\u00033\b\u0003\n\u0003\f\u00036\t\u0003\u0001\u0004\u0004"+
		"\u00049\b\u0004\u000b\u0004\f\u0004:\u0001\u0004\u0003\u0004>\b\u0004"+
		"\u0001\u0004\u0003\u0004A\b\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0004\u0006G\b\u0006\u000b\u0006\f\u0006H\u0001\u0007\u0001"+
		"\u0007\u0003\u0007M\b\u0007\u0001\u0007\u0004\u0007P\b\u0007\u000b\u0007"+
		"\f\u0007Q\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\b\\\b\b\n\b\f\b_\t\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0004\rl\b\r"+
		"\u000b\r\f\rm\u0001\r\u0001\r\u0000\u0000\u000e\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0000\r\u0000\u000f\u0000\u0011"+
		"\u0006\u0013\u0000\u0015\u0000\u0017\u0007\u0019\b\u001b\t\u0001\u0000"+
		"\f\u0002\u0000IIii\u0002\u0000FFff\u0002\u0000EEee\u0002\u0000LLll\u0002"+
		"\u0000SSss\u0002\u0000<<>>\u0003\u0000AZ__az\u0004\u000009AZ__az\u0001"+
		"\u000009\u0002\u0000++--\u0004\u0000\n\n\r\r\"\"\\\\\u0003\u0000\t\n\r"+
		"\r  z\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000"+
		"\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0001\u001d\u0001\u0000\u0000\u0000"+
		"\u0003 \u0001\u0000\u0000\u0000\u0005.\u0001\u0000\u0000\u0000\u00070"+
		"\u0001\u0000\u0000\u0000\t8\u0001\u0000\u0000\u0000\u000bB\u0001\u0000"+
		"\u0000\u0000\rD\u0001\u0000\u0000\u0000\u000fJ\u0001\u0000\u0000\u0000"+
		"\u0011S\u0001\u0000\u0000\u0000\u0013b\u0001\u0000\u0000\u0000\u0015d"+
		"\u0001\u0000\u0000\u0000\u0017f\u0001\u0000\u0000\u0000\u0019h\u0001\u0000"+
		"\u0000\u0000\u001bk\u0001\u0000\u0000\u0000\u001d\u001e\u0007\u0000\u0000"+
		"\u0000\u001e\u001f\u0007\u0001\u0000\u0000\u001f\u0002\u0001\u0000\u0000"+
		"\u0000 !\u0007\u0002\u0000\u0000!\"\u0007\u0003\u0000\u0000\"#\u0007\u0004"+
		"\u0000\u0000#$\u0007\u0002\u0000\u0000$\u0004\u0001\u0000\u0000\u0000"+
		"%/\u0007\u0005\u0000\u0000&\'\u0005>\u0000\u0000\'/\u0005=\u0000\u0000"+
		"()\u0005<\u0000\u0000)/\u0005=\u0000\u0000*+\u0005=\u0000\u0000+/\u0005"+
		"=\u0000\u0000,-\u0005!\u0000\u0000-/\u0005=\u0000\u0000.%\u0001\u0000"+
		"\u0000\u0000.&\u0001\u0000\u0000\u0000.(\u0001\u0000\u0000\u0000.*\u0001"+
		"\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000/\u0006\u0001\u0000\u0000"+
		"\u000004\u0007\u0006\u0000\u000013\u0007\u0007\u0000\u000021\u0001\u0000"+
		"\u0000\u000036\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000045\u0001"+
		"\u0000\u0000\u00005\b\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u0000"+
		"79\u0003\u000b\u0005\u000087\u0001\u0000\u0000\u00009:\u0001\u0000\u0000"+
		"\u0000:8\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;=\u0001\u0000"+
		"\u0000\u0000<>\u0003\r\u0006\u0000=<\u0001\u0000\u0000\u0000=>\u0001\u0000"+
		"\u0000\u0000>@\u0001\u0000\u0000\u0000?A\u0003\u000f\u0007\u0000@?\u0001"+
		"\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A\n\u0001\u0000\u0000\u0000"+
		"BC\u0007\b\u0000\u0000C\f\u0001\u0000\u0000\u0000DF\u0005.\u0000\u0000"+
		"EG\u0003\u000b\u0005\u0000FE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000"+
		"\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000I\u000e\u0001"+
		"\u0000\u0000\u0000JL\u0007\u0002\u0000\u0000KM\u0007\t\u0000\u0000LK\u0001"+
		"\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MO\u0001\u0000\u0000\u0000"+
		"NP\u0003\u000b\u0005\u0000ON\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000"+
		"\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000R\u0010\u0001"+
		"\u0000\u0000\u0000S]\u0003\u0013\t\u0000TU\u0003\u0015\n\u0000UV\u0003"+
		"\u0015\n\u0000V\\\u0001\u0000\u0000\u0000WX\u0003\u0015\n\u0000XY\u0003"+
		"\u0013\t\u0000Y\\\u0001\u0000\u0000\u0000Z\\\b\n\u0000\u0000[T\u0001\u0000"+
		"\u0000\u0000[W\u0001\u0000\u0000\u0000[Z\u0001\u0000\u0000\u0000\\_\u0001"+
		"\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000"+
		"^`\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000`a\u0003\u0013\t\u0000"+
		"a\u0012\u0001\u0000\u0000\u0000bc\u0005\"\u0000\u0000c\u0014\u0001\u0000"+
		"\u0000\u0000de\u0005\\\u0000\u0000e\u0016\u0001\u0000\u0000\u0000fg\u0005"+
		"(\u0000\u0000g\u0018\u0001\u0000\u0000\u0000hi\u0005)\u0000\u0000i\u001a"+
		"\u0001\u0000\u0000\u0000jl\u0007\u000b\u0000\u0000kj\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000"+
		"\u0000\u0000no\u0001\u0000\u0000\u0000op\u0006\r\u0000\u0000p\u001c\u0001"+
		"\u0000\u0000\u0000\f\u0000.4:=@HLQ[]m\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}