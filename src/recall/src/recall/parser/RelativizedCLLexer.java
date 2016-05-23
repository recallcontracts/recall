// Generated from RelativizedCL.g4 by ANTLR 4.5

  package recall.parser;
  import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import recall.model.contracts.Conflict;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RelativizedCLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONFLICT=1, GLOBAL=2, RELATIVIZED=3, OPEN_PTY=4, CLOSE_PTY=5, OPEN_REL=6, 
		SEP_REL=7, CLOSE_REL=8, OPEN_EXP=9, CLOSE_EXP=10, OPEN_DYN=11, CLOSE_DYN=12, 
		DEO_O=13, DEO_P=14, DEO_F=15, OP_CHOICE=16, OP_SEQ=17, OP_CONC=18, UN_OP_IT=19, 
		UN_OP_NEG=20, AND=21, OR=22, XOR=23, T=24, F=25, ID=26, SKIP=27, VIOLATION=28, 
		END=29, COMMENT=30, LINE_COMMENT=31, WS=32;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"CONFLICT", "GLOBAL", "RELATIVIZED", "OPEN_PTY", "CLOSE_PTY", "OPEN_REL", 
		"SEP_REL", "CLOSE_REL", "OPEN_EXP", "CLOSE_EXP", "OPEN_DYN", "CLOSE_DYN", 
		"DEO_O", "DEO_P", "DEO_F", "OP_CHOICE", "OP_SEQ", "OP_CONC", "UN_OP_IT", 
		"UN_OP_NEG", "AND", "OR", "XOR", "T", "F", "ID", "SKIP", "VIOLATION", 
		"END", "COMMENT", "LINE_COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'conflict'", "'global'", "'relativized'", "'_/'", "'/_'", "'{'", 
		"','", "'}'", "'('", "')'", "'['", "']'", null, null, null, "'+'", "'.'", 
		"'&'", "'*'", "'!'", null, null, null, "'true'", "'false'", null, "'1'", 
		"'0'", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "CONFLICT", "GLOBAL", "RELATIVIZED", "OPEN_PTY", "CLOSE_PTY", "OPEN_REL", 
		"SEP_REL", "CLOSE_REL", "OPEN_EXP", "CLOSE_EXP", "OPEN_DYN", "CLOSE_DYN", 
		"DEO_O", "DEO_P", "DEO_F", "OP_CHOICE", "OP_SEQ", "OP_CONC", "UN_OP_IT", 
		"UN_OP_NEG", "AND", "OR", "XOR", "T", "F", "ID", "SKIP", "VIOLATION", 
		"END", "COMMENT", "LINE_COMMENT", "WS"
	};
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


	   ArrayList<Conflict> globalCNF = new  ArrayList<Conflict>();
	   ArrayList<Conflict> relCNF = new  ArrayList<Conflict>();


	public RelativizedCLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RelativizedCL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\"\u00ec\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16}\n\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u008a\n\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0098\n\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\5\26\u00a8\n\26\3\27"+
		"\3\27\3\27\5\27\u00ad\n\27\3\30\3\30\3\30\3\30\5\30\u00b3\n\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\7\33\u00c2"+
		"\n\33\f\33\16\33\u00c5\13\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3"+
		"\37\3\37\7\37\u00d1\n\37\f\37\16\37\u00d4\13\37\3\37\3\37\3\37\3\37\3"+
		"\37\3 \3 \3 \3 \7 \u00df\n \f \16 \u00e2\13 \3 \3 \3!\6!\u00e7\n!\r!\16"+
		"!\u00e8\3!\3!\3\u00d2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"\3\2\6\4\2C\\c|\6\2\62;C\\aac|\4\2\f"+
		"\f\17\17\5\2\13\f\17\17\"\"\u00f5\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\3C\3\2\2\2\5L\3\2\2\2\7S\3\2\2\2\t_\3\2\2\2\13b\3\2\2\2\re\3\2\2\2"+
		"\17g\3\2\2\2\21i\3\2\2\2\23k\3\2\2\2\25m\3\2\2\2\27o\3\2\2\2\31q\3\2\2"+
		"\2\33|\3\2\2\2\35\u0089\3\2\2\2\37\u0097\3\2\2\2!\u0099\3\2\2\2#\u009b"+
		"\3\2\2\2%\u009d\3\2\2\2\'\u009f\3\2\2\2)\u00a1\3\2\2\2+\u00a7\3\2\2\2"+
		"-\u00ac\3\2\2\2/\u00b2\3\2\2\2\61\u00b4\3\2\2\2\63\u00b9\3\2\2\2\65\u00bf"+
		"\3\2\2\2\67\u00c6\3\2\2\29\u00c8\3\2\2\2;\u00ca\3\2\2\2=\u00cc\3\2\2\2"+
		"?\u00da\3\2\2\2A\u00e6\3\2\2\2CD\7e\2\2DE\7q\2\2EF\7p\2\2FG\7h\2\2GH\7"+
		"n\2\2HI\7k\2\2IJ\7e\2\2JK\7v\2\2K\4\3\2\2\2LM\7i\2\2MN\7n\2\2NO\7q\2\2"+
		"OP\7d\2\2PQ\7c\2\2QR\7n\2\2R\6\3\2\2\2ST\7t\2\2TU\7g\2\2UV\7n\2\2VW\7"+
		"c\2\2WX\7v\2\2XY\7k\2\2YZ\7x\2\2Z[\7k\2\2[\\\7|\2\2\\]\7g\2\2]^\7f\2\2"+
		"^\b\3\2\2\2_`\7a\2\2`a\7\61\2\2a\n\3\2\2\2bc\7\61\2\2cd\7a\2\2d\f\3\2"+
		"\2\2ef\7}\2\2f\16\3\2\2\2gh\7.\2\2h\20\3\2\2\2ij\7\177\2\2j\22\3\2\2\2"+
		"kl\7*\2\2l\24\3\2\2\2mn\7+\2\2n\26\3\2\2\2op\7]\2\2p\30\3\2\2\2qr\7_\2"+
		"\2r\32\3\2\2\2s}\7Q\2\2tu\7a\2\2uv\7Q\2\2vw\7d\2\2wx\7n\2\2xy\7k\2\2y"+
		"z\7i\2\2z{\7g\2\2{}\7f\2\2|s\3\2\2\2|t\3\2\2\2}\34\3\2\2\2~\u008a\7R\2"+
		"\2\177\u0080\7a\2\2\u0080\u0081\7R\2\2\u0081\u0082\7g\2\2\u0082\u0083"+
		"\7t\2\2\u0083\u0084\7o\2\2\u0084\u0085\7k\2\2\u0085\u0086\7v\2\2\u0086"+
		"\u0087\7v\2\2\u0087\u0088\7g\2\2\u0088\u008a\7f\2\2\u0089~\3\2\2\2\u0089"+
		"\177\3\2\2\2\u008a\36\3\2\2\2\u008b\u0098\7H\2\2\u008c\u008d\7a\2\2\u008d"+
		"\u008e\7R\2\2\u008e\u008f\7t\2\2\u008f\u0090\7q\2\2\u0090\u0091\7j\2\2"+
		"\u0091\u0092\7k\2\2\u0092\u0093\7d\2\2\u0093\u0094\7k\2\2\u0094\u0095"+
		"\7v\2\2\u0095\u0096\7g\2\2\u0096\u0098\7f\2\2\u0097\u008b\3\2\2\2\u0097"+
		"\u008c\3\2\2\2\u0098 \3\2\2\2\u0099\u009a\7-\2\2\u009a\"\3\2\2\2\u009b"+
		"\u009c\7\60\2\2\u009c$\3\2\2\2\u009d\u009e\7(\2\2\u009e&\3\2\2\2\u009f"+
		"\u00a0\7,\2\2\u00a0(\3\2\2\2\u00a1\u00a2\7#\2\2\u00a2*\3\2\2\2\u00a3\u00a8"+
		"\7`\2\2\u00a4\u00a5\7C\2\2\u00a5\u00a6\7P\2\2\u00a6\u00a8\7F\2\2\u00a7"+
		"\u00a3\3\2\2\2\u00a7\u00a4\3\2\2\2\u00a8,\3\2\2\2\u00a9\u00ad\7~\2\2\u00aa"+
		"\u00ab\7Q\2\2\u00ab\u00ad\7T\2\2\u00ac\u00a9\3\2\2\2\u00ac\u00aa\3\2\2"+
		"\2\u00ad.\3\2\2\2\u00ae\u00b3\7/\2\2\u00af\u00b0\7Z\2\2\u00b0\u00b1\7"+
		"Q\2\2\u00b1\u00b3\7T\2\2\u00b2\u00ae\3\2\2\2\u00b2\u00af\3\2\2\2\u00b3"+
		"\60\3\2\2\2\u00b4\u00b5\7v\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7\7w\2\2\u00b7"+
		"\u00b8\7g\2\2\u00b8\62\3\2\2\2\u00b9\u00ba\7h\2\2\u00ba\u00bb\7c\2\2\u00bb"+
		"\u00bc\7n\2\2\u00bc\u00bd\7u\2\2\u00bd\u00be\7g\2\2\u00be\64\3\2\2\2\u00bf"+
		"\u00c3\t\2\2\2\u00c0\u00c2\t\3\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2"+
		"\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\66\3\2\2\2\u00c5\u00c3"+
		"\3\2\2\2\u00c6\u00c7\7\63\2\2\u00c78\3\2\2\2\u00c8\u00c9\7\62\2\2\u00c9"+
		":\3\2\2\2\u00ca\u00cb\7=\2\2\u00cb<\3\2\2\2\u00cc\u00cd\7\61\2\2\u00cd"+
		"\u00ce\7,\2\2\u00ce\u00d2\3\2\2\2\u00cf\u00d1\13\2\2\2\u00d0\u00cf\3\2"+
		"\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3"+
		"\u00d5\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00d6\7,\2\2\u00d6\u00d7\7\61"+
		"\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\b\37\2\2\u00d9>\3\2\2\2\u00da\u00db"+
		"\7\61\2\2\u00db\u00dc\7\61\2\2\u00dc\u00e0\3\2\2\2\u00dd\u00df\n\4\2\2"+
		"\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1"+
		"\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\b \2\2\u00e4"+
		"@\3\2\2\2\u00e5\u00e7\t\5\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2"+
		"\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb"+
		"\b!\2\2\u00ebB\3\2\2\2\r\2|\u0089\u0097\u00a7\u00ac\u00b2\u00c3\u00d2"+
		"\u00e0\u00e8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}