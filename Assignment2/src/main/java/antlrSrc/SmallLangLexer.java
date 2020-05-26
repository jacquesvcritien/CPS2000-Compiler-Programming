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
		FOR=14, FF=15, PRINT=16, RETURN=17, WHILE=18, Type=19, Auto=20, AND=21, 
		OR=22, TIMES=23, DIVIDE=24, PLUS=25, LT=26, GT=27, EQUAL=28, NOT_EQUAL=29, 
		LTE=30, GTE=31, BooleanLiteral=32, IntegerLiteral=33, FloatLiteral=34, 
		Identifier=35, WS=36, COMMENT=37, MULTI_LINE_COMMENT=38;
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
			"AND", "OR", "TIMES", "DIVIDE", "PLUS", "LT", "GT", "EQUAL", "NOT_EQUAL", 
			"LTE", "GTE", "BooleanLiteral", "IntegerLiteral", "FloatLiteral", "Identifier", 
			"WS", "COMMENT", "MULTI_LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'let'", "'not'", "'-'", "'='", "':'", "';'", "'('", "')'", "'{'", 
			"'}'", "','", "'if'", "'else'", "'for'", "'ff'", "'print'", "'return'", 
			"'while'", null, "'auto'", "'and'", "'or'", "'*'", "'/'", "'+'", "'<'", 
			"'>'", "'=='", "'<>'", "'<='", "'>='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LET", "NOT", "MINUS", "EQUAL_SIGN", "COLON", "SEMI_COLON", "BRACKET_OPEN", 
			"BRACKET_CLOSE", "CURLY_OPEN", "CURLY_CLOSE", "COMMA", "IF", "ELSE", 
			"FOR", "FF", "PRINT", "RETURN", "WHILE", "Type", "Auto", "AND", "OR", 
			"TIMES", "DIVIDE", "PLUS", "LT", "GT", "EQUAL", "NOT_EQUAL", "LTE", "GTE", 
			"BooleanLiteral", "IntegerLiteral", "FloatLiteral", "Identifier", "WS", 
			"COMMENT", "MULTI_LINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u010c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00a0"+
		"\n\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 "+
		"\3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u00cd\n#\3$\6$\u00d0"+
		"\n$\r$\16$\u00d1\3%\6%\u00d5\n%\r%\16%\u00d6\3%\3%\6%\u00db\n%\r%\16%"+
		"\u00dc\3&\3&\5&\u00e1\n&\3&\3&\3&\7&\u00e6\n&\f&\16&\u00e9\13&\3\'\6\'"+
		"\u00ec\n\'\r\'\16\'\u00ed\3\'\3\'\3(\3(\3(\3(\7(\u00f6\n(\f(\16(\u00f9"+
		"\13(\3(\3(\3(\3(\3)\3)\3)\3)\7)\u0103\n)\f)\16)\u0106\13)\3)\3)\3)\3)"+
		"\3)\4\u00f7\u0104\2*\3\2\5\2\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13"+
		"\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65"+
		"\32\67\339\34;\35=\36?\37A C!E\"G#I$K%M&O\'Q(\3\2\6\3\2\62;\4\2C\\c|\5"+
		"\2\13\f\17\17\"\"\3\2\f\f\2\u0116\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3S"+
		"\3\2\2\2\5U\3\2\2\2\7W\3\2\2\2\t[\3\2\2\2\13_\3\2\2\2\ra\3\2\2\2\17c\3"+
		"\2\2\2\21e\3\2\2\2\23g\3\2\2\2\25i\3\2\2\2\27k\3\2\2\2\31m\3\2\2\2\33"+
		"o\3\2\2\2\35q\3\2\2\2\37t\3\2\2\2!y\3\2\2\2#}\3\2\2\2%\u0080\3\2\2\2\'"+
		"\u0086\3\2\2\2)\u008d\3\2\2\2+\u009f\3\2\2\2-\u00a1\3\2\2\2/\u00a6\3\2"+
		"\2\2\61\u00aa\3\2\2\2\63\u00ad\3\2\2\2\65\u00af\3\2\2\2\67\u00b1\3\2\2"+
		"\29\u00b3\3\2\2\2;\u00b5\3\2\2\2=\u00b7\3\2\2\2?\u00ba\3\2\2\2A\u00bd"+
		"\3\2\2\2C\u00c0\3\2\2\2E\u00cc\3\2\2\2G\u00cf\3\2\2\2I\u00d4\3\2\2\2K"+
		"\u00e0\3\2\2\2M\u00eb\3\2\2\2O\u00f1\3\2\2\2Q\u00fe\3\2\2\2ST\t\2\2\2"+
		"T\4\3\2\2\2UV\t\3\2\2V\6\3\2\2\2WX\7n\2\2XY\7g\2\2YZ\7v\2\2Z\b\3\2\2\2"+
		"[\\\7p\2\2\\]\7q\2\2]^\7v\2\2^\n\3\2\2\2_`\7/\2\2`\f\3\2\2\2ab\7?\2\2"+
		"b\16\3\2\2\2cd\7<\2\2d\20\3\2\2\2ef\7=\2\2f\22\3\2\2\2gh\7*\2\2h\24\3"+
		"\2\2\2ij\7+\2\2j\26\3\2\2\2kl\7}\2\2l\30\3\2\2\2mn\7\177\2\2n\32\3\2\2"+
		"\2op\7.\2\2p\34\3\2\2\2qr\7k\2\2rs\7h\2\2s\36\3\2\2\2tu\7g\2\2uv\7n\2"+
		"\2vw\7u\2\2wx\7g\2\2x \3\2\2\2yz\7h\2\2z{\7q\2\2{|\7t\2\2|\"\3\2\2\2}"+
		"~\7h\2\2~\177\7h\2\2\177$\3\2\2\2\u0080\u0081\7r\2\2\u0081\u0082\7t\2"+
		"\2\u0082\u0083\7k\2\2\u0083\u0084\7p\2\2\u0084\u0085\7v\2\2\u0085&\3\2"+
		"\2\2\u0086\u0087\7t\2\2\u0087\u0088\7g\2\2\u0088\u0089\7v\2\2\u0089\u008a"+
		"\7w\2\2\u008a\u008b\7t\2\2\u008b\u008c\7p\2\2\u008c(\3\2\2\2\u008d\u008e"+
		"\7y\2\2\u008e\u008f\7j\2\2\u008f\u0090\7k\2\2\u0090\u0091\7n\2\2\u0091"+
		"\u0092\7g\2\2\u0092*\3\2\2\2\u0093\u0094\7h\2\2\u0094\u0095\7n\2\2\u0095"+
		"\u0096\7q\2\2\u0096\u0097\7c\2\2\u0097\u00a0\7v\2\2\u0098\u0099\7k\2\2"+
		"\u0099\u009a\7p\2\2\u009a\u00a0\7v\2\2\u009b\u009c\7d\2\2\u009c\u009d"+
		"\7q\2\2\u009d\u009e\7q\2\2\u009e\u00a0\7n\2\2\u009f\u0093\3\2\2\2\u009f"+
		"\u0098\3\2\2\2\u009f\u009b\3\2\2\2\u00a0,\3\2\2\2\u00a1\u00a2\7c\2\2\u00a2"+
		"\u00a3\7w\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7q\2\2\u00a5.\3\2\2\2\u00a6"+
		"\u00a7\7c\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7f\2\2\u00a9\60\3\2\2\2\u00aa"+
		"\u00ab\7q\2\2\u00ab\u00ac\7t\2\2\u00ac\62\3\2\2\2\u00ad\u00ae\7,\2\2\u00ae"+
		"\64\3\2\2\2\u00af\u00b0\7\61\2\2\u00b0\66\3\2\2\2\u00b1\u00b2\7-\2\2\u00b2"+
		"8\3\2\2\2\u00b3\u00b4\7>\2\2\u00b4:\3\2\2\2\u00b5\u00b6\7@\2\2\u00b6<"+
		"\3\2\2\2\u00b7\u00b8\7?\2\2\u00b8\u00b9\7?\2\2\u00b9>\3\2\2\2\u00ba\u00bb"+
		"\7>\2\2\u00bb\u00bc\7@\2\2\u00bc@\3\2\2\2\u00bd\u00be\7>\2\2\u00be\u00bf"+
		"\7?\2\2\u00bfB\3\2\2\2\u00c0\u00c1\7@\2\2\u00c1\u00c2\7?\2\2\u00c2D\3"+
		"\2\2\2\u00c3\u00c4\7v\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7w\2\2\u00c6"+
		"\u00cd\7g\2\2\u00c7\u00c8\7h\2\2\u00c8\u00c9\7c\2\2\u00c9\u00ca\7n\2\2"+
		"\u00ca\u00cb\7u\2\2\u00cb\u00cd\7g\2\2\u00cc\u00c3\3\2\2\2\u00cc\u00c7"+
		"\3\2\2\2\u00cdF\3\2\2\2\u00ce\u00d0\5\3\2\2\u00cf\u00ce\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2H\3\2\2\2"+
		"\u00d3\u00d5\5\3\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4"+
		"\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\7\60\2\2"+
		"\u00d9\u00db\5\3\2\2\u00da\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00da"+
		"\3\2\2\2\u00dc\u00dd\3\2\2\2\u00ddJ\3\2\2\2\u00de\u00e1\7a\2\2\u00df\u00e1"+
		"\5\5\3\2\u00e0\u00de\3\2\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e7\3\2\2\2\u00e2"+
		"\u00e6\7a\2\2\u00e3\u00e6\5\5\3\2\u00e4\u00e6\5\3\2\2\u00e5\u00e2\3\2"+
		"\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8L\3\2\2\2\u00e9\u00e7\3\2\2\2"+
		"\u00ea\u00ec\t\4\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\b\'\2\2\u00f0"+
		"N\3\2\2\2\u00f1\u00f2\7\61\2\2\u00f2\u00f3\7\61\2\2\u00f3\u00f7\3\2\2"+
		"\2\u00f4\u00f6\13\2\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f7\3\2"+
		"\2\2\u00fa\u00fb\t\5\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\b(\2\2\u00fd"+
		"P\3\2\2\2\u00fe\u00ff\7\61\2\2\u00ff\u0100\7,\2\2\u0100\u0104\3\2\2\2"+
		"\u0101\u0103\13\2\2\2\u0102\u0101\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0105"+
		"\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107"+
		"\u0108\7,\2\2\u0108\u0109\7\61\2\2\u0109\u010a\3\2\2\2\u010a\u010b\b)"+
		"\2\2\u010bR\3\2\2\2\16\2\u009f\u00cc\u00d1\u00d6\u00dc\u00e0\u00e5\u00e7"+
		"\u00ed\u00f7\u0104\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}