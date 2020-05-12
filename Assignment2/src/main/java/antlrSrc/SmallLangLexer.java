// Generated from C:/Users/dell/Documents/School/Year 2/Compiler Theory/CPS2000/Assignment2/src/main/antlr4\SmallLang.g4 by ANTLR 4.8

package antlrSrc;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SmallLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LET=1, NOT=2, MINUS=3, EQUAL_SIGN=4, COLON=5, SEMI_COLON=6, BRACKET_OPEN=7, 
		BRACKET_CLOSE=8, CURLY_OPEN=9, CURLY_CLOSE=10, COMMA=11, IF=12, ELSE=13, 
		FOR=14, FF=15, PRINT=16, RETURN=17, WHILE=18, Type=19, Auto=20, BooleanLiteral=21, 
		IntegerLiteral=22, FloatLiteral=23, Identifier=24, MultiplicativeOp=25, 
		AdditiveOp=26, RelationalOp=27, WS=28, COMMENT=29, MULTI_LINE_COMMENT=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DIGIT", "LETTER", "LET", "NOT", "MINUS", "EQUAL_SIGN", "COLON", "SEMI_COLON", 
			"BRACKET_OPEN", "BRACKET_CLOSE", "CURLY_OPEN", "CURLY_CLOSE", "COMMA", 
			"IF", "ELSE", "FOR", "FF", "PRINT", "RETURN", "WHILE", "Type", "Auto", 
			"BooleanLiteral", "IntegerLiteral", "FloatLiteral", "Identifier", "MultiplicativeOp", 
			"AdditiveOp", "RelationalOp", "WS", "COMMENT", "MULTI_LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'let'", "'not'", "'-'", "'='", "':'", "';'", "'('", "')'", "'{'", 
			"'}'", "','", "'if'", "'else'", "'for'", "'ff'", "'print'", "'return'", 
			"'while'", null, "'auto'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LET", "NOT", "MINUS", "EQUAL_SIGN", "COLON", "SEMI_COLON", "BRACKET_OPEN", 
			"BRACKET_CLOSE", "CURLY_OPEN", "CURLY_CLOSE", "COMMA", "IF", "ELSE", 
			"FOR", "FF", "PRINT", "RETURN", "WHILE", "Type", "Auto", "BooleanLiteral", 
			"IntegerLiteral", "FloatLiteral", "Identifier", "MultiplicativeOp", "AdditiveOp", 
			"RelationalOp", "WS", "COMMENT", "MULTI_LINE_COMMENT"
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


	public SmallLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SmallLang.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00f5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\5\26\u0090\n\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\5\30\u00a0\n\30\3\31\6\31\u00a3\n\31\r\31\16"+
		"\31\u00a4\3\32\6\32\u00a8\n\32\r\32\16\32\u00a9\3\32\3\32\6\32\u00ae\n"+
		"\32\r\32\16\32\u00af\3\33\3\33\5\33\u00b4\n\33\3\33\3\33\3\33\7\33\u00b9"+
		"\n\33\f\33\16\33\u00bc\13\33\3\34\3\34\3\34\3\34\5\34\u00c2\n\34\3\35"+
		"\3\35\3\35\5\35\u00c7\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\5\36\u00d2\n\36\3\37\6\37\u00d5\n\37\r\37\16\37\u00d6\3\37\3\37\3 \3"+
		" \3 \3 \7 \u00df\n \f \16 \u00e2\13 \3 \3 \3 \3 \3!\3!\3!\3!\7!\u00ec"+
		"\n!\f!\16!\u00ef\13!\3!\3!\3!\3!\3!\4\u00e0\u00ed\2\"\3\2\5\2\7\3\t\4"+
		"\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22"+
		"\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A \3\2\t"+
		"\3\2\62;\4\2C\\c|\4\2,,\61\61\4\2--//\4\2>>@@\5\2\13\f\17\17\"\"\3\2\f"+
		"\f\2\u0105\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5E\3\2\2\2\7G\3\2\2\2\tK\3\2\2\2\13"+
		"O\3\2\2\2\rQ\3\2\2\2\17S\3\2\2\2\21U\3\2\2\2\23W\3\2\2\2\25Y\3\2\2\2\27"+
		"[\3\2\2\2\31]\3\2\2\2\33_\3\2\2\2\35a\3\2\2\2\37d\3\2\2\2!i\3\2\2\2#m"+
		"\3\2\2\2%p\3\2\2\2\'v\3\2\2\2)}\3\2\2\2+\u008f\3\2\2\2-\u0091\3\2\2\2"+
		"/\u009f\3\2\2\2\61\u00a2\3\2\2\2\63\u00a7\3\2\2\2\65\u00b3\3\2\2\2\67"+
		"\u00c1\3\2\2\29\u00c6\3\2\2\2;\u00d1\3\2\2\2=\u00d4\3\2\2\2?\u00da\3\2"+
		"\2\2A\u00e7\3\2\2\2CD\t\2\2\2D\4\3\2\2\2EF\t\3\2\2F\6\3\2\2\2GH\7n\2\2"+
		"HI\7g\2\2IJ\7v\2\2J\b\3\2\2\2KL\7p\2\2LM\7q\2\2MN\7v\2\2N\n\3\2\2\2OP"+
		"\7/\2\2P\f\3\2\2\2QR\7?\2\2R\16\3\2\2\2ST\7<\2\2T\20\3\2\2\2UV\7=\2\2"+
		"V\22\3\2\2\2WX\7*\2\2X\24\3\2\2\2YZ\7+\2\2Z\26\3\2\2\2[\\\7}\2\2\\\30"+
		"\3\2\2\2]^\7\177\2\2^\32\3\2\2\2_`\7.\2\2`\34\3\2\2\2ab\7k\2\2bc\7h\2"+
		"\2c\36\3\2\2\2de\7g\2\2ef\7n\2\2fg\7u\2\2gh\7g\2\2h \3\2\2\2ij\7h\2\2"+
		"jk\7q\2\2kl\7t\2\2l\"\3\2\2\2mn\7h\2\2no\7h\2\2o$\3\2\2\2pq\7r\2\2qr\7"+
		"t\2\2rs\7k\2\2st\7p\2\2tu\7v\2\2u&\3\2\2\2vw\7t\2\2wx\7g\2\2xy\7v\2\2"+
		"yz\7w\2\2z{\7t\2\2{|\7p\2\2|(\3\2\2\2}~\7y\2\2~\177\7j\2\2\177\u0080\7"+
		"k\2\2\u0080\u0081\7n\2\2\u0081\u0082\7g\2\2\u0082*\3\2\2\2\u0083\u0084"+
		"\7h\2\2\u0084\u0085\7n\2\2\u0085\u0086\7q\2\2\u0086\u0087\7c\2\2\u0087"+
		"\u0090\7v\2\2\u0088\u0089\7k\2\2\u0089\u008a\7p\2\2\u008a\u0090\7v\2\2"+
		"\u008b\u008c\7d\2\2\u008c\u008d\7q\2\2\u008d\u008e\7q\2\2\u008e\u0090"+
		"\7n\2\2\u008f\u0083\3\2\2\2\u008f\u0088\3\2\2\2\u008f\u008b\3\2\2\2\u0090"+
		",\3\2\2\2\u0091\u0092\7c\2\2\u0092\u0093\7w\2\2\u0093\u0094\7v\2\2\u0094"+
		"\u0095\7q\2\2\u0095.\3\2\2\2\u0096\u0097\7v\2\2\u0097\u0098\7t\2\2\u0098"+
		"\u0099\7w\2\2\u0099\u00a0\7g\2\2\u009a\u009b\7h\2\2\u009b\u009c\7c\2\2"+
		"\u009c\u009d\7n\2\2\u009d\u009e\7u\2\2\u009e\u00a0\7g\2\2\u009f\u0096"+
		"\3\2\2\2\u009f\u009a\3\2\2\2\u00a0\60\3\2\2\2\u00a1\u00a3\5\3\2\2\u00a2"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\62\3\2\2\2\u00a6\u00a8\5\3\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\u00ad\7\60\2\2\u00ac\u00ae\5\3\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3"+
		"\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\64\3\2\2\2\u00b1"+
		"\u00b4\7a\2\2\u00b2\u00b4\5\5\3\2\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3\2"+
		"\2\2\u00b4\u00ba\3\2\2\2\u00b5\u00b9\7a\2\2\u00b6\u00b9\5\5\3\2\u00b7"+
		"\u00b9\5\3\2\2\u00b8\u00b5\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b7\3\2"+
		"\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\66\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00c2\t\4\2\2\u00be\u00bf\7c\2\2"+
		"\u00bf\u00c0\7p\2\2\u00c0\u00c2\7f\2\2\u00c1\u00bd\3\2\2\2\u00c1\u00be"+
		"\3\2\2\2\u00c28\3\2\2\2\u00c3\u00c7\t\5\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c7"+
		"\7t\2\2\u00c6\u00c3\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7:\3\2\2\2\u00c8\u00d2"+
		"\t\6\2\2\u00c9\u00ca\7?\2\2\u00ca\u00d2\7?\2\2\u00cb\u00cc\7>\2\2\u00cc"+
		"\u00d2\7@\2\2\u00cd\u00ce\7>\2\2\u00ce\u00d2\7?\2\2\u00cf\u00d0\7@\2\2"+
		"\u00d0\u00d2\7?\2\2\u00d1\u00c8\3\2\2\2\u00d1\u00c9\3\2\2\2\u00d1\u00cb"+
		"\3\2\2\2\u00d1\u00cd\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2<\3\2\2\2\u00d3"+
		"\u00d5\t\7\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2"+
		"\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\b\37\2\2\u00d9"+
		">\3\2\2\2\u00da\u00db\7\61\2\2\u00db\u00dc\7\61\2\2\u00dc\u00e0\3\2\2"+
		"\2\u00dd\u00df\13\2\2\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2"+
		"\2\2\u00e3\u00e4\t\b\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\b \2\2\u00e6"+
		"@\3\2\2\2\u00e7\u00e8\7\61\2\2\u00e8\u00e9\7,\2\2\u00e9\u00ed\3\2\2\2"+
		"\u00ea\u00ec\13\2\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00ee"+
		"\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0"+
		"\u00f1\7,\2\2\u00f1\u00f2\7\61\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\b!"+
		"\2\2\u00f4B\3\2\2\2\21\2\u008f\u009f\u00a4\u00a9\u00af\u00b3\u00b8\u00ba"+
		"\u00c1\u00c6\u00d1\u00d6\u00e0\u00ed\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}