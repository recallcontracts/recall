// Generated from RelativizedCL.g4 by ANTLR 4.5

package recall.parser;

import recall.model.actions.*;
import java.util.*;
import recall.model.contracts.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RelativizedCLParser extends Parser {
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
	public static final int
		RULE_contract = 0, RULE_conflict = 1, RULE_cfGlobal = 2, RULE_cfRel = 3, 
		RULE_cfPair = 4, RULE_clause = 5, RULE_penalty = 6, RULE_cd = 7, RULE_co = 8, 
		RULE_cp = 9, RULE_cf = 10, RULE_rel = 11, RULE_alpha = 12, RULE_beta = 13, 
		RULE_op = 14;
	public static final String[] ruleNames = {
		"contract", "conflict", "cfGlobal", "cfRel", "cfPair", "clause", "penalty", 
		"cd", "co", "cp", "cf", "rel", "alpha", "beta", "op"
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

	@Override
	public String getGrammarFileName() { return "RelativizedCL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	   ArrayList<Conflict> globalCNF = new  ArrayList<Conflict>();
	   ArrayList<Conflict> relCNF = new  ArrayList<Conflict>();

	public RelativizedCLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ContractContext extends ParserRuleContext {
		public Contract c;
		public ClauseContext c1;
		public ClauseContext c2;
		public ConflictContext conflict() {
			return getRuleContext(ConflictContext.class,0);
		}
		public List<TerminalNode> END() { return getTokens(RelativizedCLParser.END); }
		public TerminalNode END(int i) {
			return getToken(RelativizedCLParser.END, i);
		}
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public ContractContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contract; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterContract(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitContract(this);
		}
	}

	public final ContractContext contract() throws RecognitionException {
		ContractContext _localctx = new ContractContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_contract);
		try {
			int _alt;
			setState(49);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				conflict();
				setState(31);
				((ContractContext)_localctx).c1 = clause(0);
				setState(32);
				match(END);
				((ContractContext)_localctx).c =  new Contract(((ContractContext)_localctx).c1.c); _localctx.c.setConflicts(globalCNF, relCNF); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				conflict();
				setState(36);
				((ContractContext)_localctx).c1 = clause(0);
				((ContractContext)_localctx).c =  new Contract(((ContractContext)_localctx).c1.c); _localctx.c.setConflicts(globalCNF, relCNF); 
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(38);
						match(END);
						setState(39);
						((ContractContext)_localctx).c2 = clause(0);
						_localctx.c.addClause(((ContractContext)_localctx).c2.c);
						}
						} 
					}
					setState(46);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(47);
				match(END);
				}
				break;
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

	public static class ConflictContext extends ParserRuleContext {
		public TerminalNode CONFLICT() { return getToken(RelativizedCLParser.CONFLICT, 0); }
		public CfGlobalContext cfGlobal() {
			return getRuleContext(CfGlobalContext.class,0);
		}
		public CfRelContext cfRel() {
			return getRuleContext(CfRelContext.class,0);
		}
		public TerminalNode END() { return getToken(RelativizedCLParser.END, 0); }
		public ConflictContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conflict; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterConflict(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitConflict(this);
		}
	}

	public final ConflictContext conflict() throws RecognitionException {
		ConflictContext _localctx = new ConflictContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_conflict);
		try {
			setState(66);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(CONFLICT);
				setState(52);
				match(OPEN_REL);
				setState(53);
				cfGlobal();
				setState(54);
				cfRel();
				setState(55);
				match(CLOSE_REL);
				setState(56);
				match(END);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(CONFLICT);
				setState(59);
				match(OPEN_REL);
				setState(60);
				cfRel();
				setState(61);
				cfGlobal();
				setState(62);
				match(CLOSE_REL);
				setState(63);
				match(END);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
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

	public static class CfGlobalContext extends ParserRuleContext {
		public CfPairContext p1;
		public CfPairContext p2;
		public TerminalNode GLOBAL() { return getToken(RelativizedCLParser.GLOBAL, 0); }
		public TerminalNode END() { return getToken(RelativizedCLParser.END, 0); }
		public List<CfPairContext> cfPair() {
			return getRuleContexts(CfPairContext.class);
		}
		public CfPairContext cfPair(int i) {
			return getRuleContext(CfPairContext.class,i);
		}
		public CfGlobalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cfGlobal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterCfGlobal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitCfGlobal(this);
		}
	}

	public final CfGlobalContext cfGlobal() throws RecognitionException {
		CfGlobalContext _localctx = new CfGlobalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_cfGlobal);
		int _la;
		try {
			setState(85);
			switch (_input.LA(1)) {
			case GLOBAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				match(GLOBAL);
				setState(69);
				match(OPEN_REL);
				setState(70);
				((CfGlobalContext)_localctx).p1 = cfPair();
				 globalCNF.add(new Conflict(((CfGlobalContext)_localctx).p1.a, ((CfGlobalContext)_localctx).p1.b, ConflictType.GLOBAL));
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP_REL) {
					{
					{
					setState(72);
					match(SEP_REL);
					setState(73);
					((CfGlobalContext)_localctx).p2 = cfPair();
					 globalCNF.add(new Conflict(((CfGlobalContext)_localctx).p2.a, ((CfGlobalContext)_localctx).p2.b, ConflictType.GLOBAL));
					}
					}
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(81);
				match(CLOSE_REL);
				setState(82);
				match(END);
				}
				break;
			case RELATIVIZED:
			case CLOSE_REL:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class CfRelContext extends ParserRuleContext {
		public CfPairContext p1;
		public CfPairContext p2;
		public TerminalNode RELATIVIZED() { return getToken(RelativizedCLParser.RELATIVIZED, 0); }
		public TerminalNode END() { return getToken(RelativizedCLParser.END, 0); }
		public List<CfPairContext> cfPair() {
			return getRuleContexts(CfPairContext.class);
		}
		public CfPairContext cfPair(int i) {
			return getRuleContext(CfPairContext.class,i);
		}
		public CfRelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cfRel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterCfRel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitCfRel(this);
		}
	}

	public final CfRelContext cfRel() throws RecognitionException {
		CfRelContext _localctx = new CfRelContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cfRel);
		int _la;
		try {
			setState(104);
			switch (_input.LA(1)) {
			case RELATIVIZED:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				match(RELATIVIZED);
				setState(88);
				match(OPEN_REL);
				setState(89);
				((CfRelContext)_localctx).p1 = cfPair();
				relCNF.add(new Conflict(((CfRelContext)_localctx).p1.a, ((CfRelContext)_localctx).p1.b, ConflictType.RELATIVIZED));
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP_REL) {
					{
					{
					setState(91);
					match(SEP_REL);
					setState(92);
					((CfRelContext)_localctx).p2 = cfPair();
					relCNF.add(new Conflict(((CfRelContext)_localctx).p2.a, ((CfRelContext)_localctx).p2.b, ConflictType.RELATIVIZED));
					}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(100);
				match(CLOSE_REL);
				setState(101);
				match(END);
				}
				break;
			case GLOBAL:
			case CLOSE_REL:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class CfPairContext extends ParserRuleContext {
		public BasicAction a;
		public BasicAction b;
		public Token id1;
		public Token id2;
		public List<TerminalNode> ID() { return getTokens(RelativizedCLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RelativizedCLParser.ID, i);
		}
		public CfPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cfPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterCfPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitCfPair(this);
		}
	}

	public final CfPairContext cfPair() throws RecognitionException {
		CfPairContext _localctx = new CfPairContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cfPair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(OPEN_EXP);
			setState(107);
			((CfPairContext)_localctx).id1 = match(ID);
			setState(108);
			match(SEP_REL);
			setState(109);
			((CfPairContext)_localctx).id2 = match(ID);
			setState(110);
			match(CLOSE_EXP);

			                ((CfPairContext)_localctx).a =  new BasicAction(SymbolTable.getInstance().addSymbol((((CfPairContext)_localctx).id1!=null?((CfPairContext)_localctx).id1.getText():null), SymbolType.action));
			                ((CfPairContext)_localctx).b =  new BasicAction(SymbolTable.getInstance().addSymbol((((CfPairContext)_localctx).id2!=null?((CfPairContext)_localctx).id2.getText():null), SymbolType.action));
			            
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

	public static class ClauseContext extends ParserRuleContext {
		public Clause c;
		public ClauseContext c1;
		public CoContext co;
		public CpContext cp;
		public CfContext cf;
		public CdContext cd;
		public ClauseContext c2;
		public CoContext co() {
			return getRuleContext(CoContext.class,0);
		}
		public CpContext cp() {
			return getRuleContext(CpContext.class,0);
		}
		public CfContext cf() {
			return getRuleContext(CfContext.class,0);
		}
		public CdContext cd() {
			return getRuleContext(CdContext.class,0);
		}
		public TerminalNode T() { return getToken(RelativizedCLParser.T, 0); }
		public TerminalNode F() { return getToken(RelativizedCLParser.F, 0); }
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public TerminalNode AND() { return getToken(RelativizedCLParser.AND, 0); }
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitClause(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		return clause(0);
	}

	private ClauseContext clause(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ClauseContext _localctx = new ClauseContext(_ctx, _parentState);
		ClauseContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_clause, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(114);
				((ClauseContext)_localctx).co = co(0);
				((ClauseContext)_localctx).c =  ((ClauseContext)_localctx).co.c;
				}
				break;
			case 2:
				{
				setState(117);
				((ClauseContext)_localctx).cp = cp(0);
				((ClauseContext)_localctx).c =  ((ClauseContext)_localctx).cp.c;
				}
				break;
			case 3:
				{
				setState(120);
				((ClauseContext)_localctx).cf = cf(0);
				((ClauseContext)_localctx).c =  ((ClauseContext)_localctx).cf.c;
				}
				break;
			case 4:
				{
				setState(123);
				((ClauseContext)_localctx).cd = cd();
				((ClauseContext)_localctx).c =  ((ClauseContext)_localctx).cd.c;
				}
				break;
			case 5:
				{
				setState(126);
				match(T);
				((ClauseContext)_localctx).c =  BooleanClause.TRUE;
				}
				break;
			case 6:
				{
				setState(128);
				match(F);
				((ClauseContext)_localctx).c =  BooleanClause.FALSE;
				}
				break;
			case 7:
				{
				setState(130);
				match(OPEN_EXP);
				setState(131);
				((ClauseContext)_localctx).c1 = clause(0);
				setState(132);
				match(CLOSE_EXP);
				((ClauseContext)_localctx).c =  ((ClauseContext)_localctx).c1.c;
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(144);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ClauseContext(_parentctx, _parentState);
					_localctx.c1 = _prevctx;
					_localctx.c1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_clause);
					setState(137);
					if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
					setState(138);
					match(AND);
					setState(139);
					((ClauseContext)_localctx).c2 = clause(9);

					                        ((ClauseContext)_localctx).c2.c.setComposition(new ClauseComposition(ClauseCompositionType.AND, ((ClauseContext)_localctx).c1.c));
					                        ((ClauseContext)_localctx).c =  ((ClauseContext)_localctx).c2.c;
					                       
					}
					} 
				}
				setState(146);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PenaltyContext extends ParserRuleContext {
		public Clause c;
		public ClauseContext clause;
		public TerminalNode OPEN_PTY() { return getToken(RelativizedCLParser.OPEN_PTY, 0); }
		public ClauseContext clause() {
			return getRuleContext(ClauseContext.class,0);
		}
		public TerminalNode CLOSE_PTY() { return getToken(RelativizedCLParser.CLOSE_PTY, 0); }
		public PenaltyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_penalty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterPenalty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitPenalty(this);
		}
	}

	public final PenaltyContext penalty() throws RecognitionException {
		PenaltyContext _localctx = new PenaltyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_penalty);
		try {
			setState(153);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				match(OPEN_PTY);
				setState(148);
				((PenaltyContext)_localctx).clause = clause(0);
				setState(149);
				match(CLOSE_PTY);
				((PenaltyContext)_localctx).c =  ((PenaltyContext)_localctx).clause.c;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				((PenaltyContext)_localctx).c =  BooleanClause.FALSE;
				}
				break;
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

	public static class CdContext extends ParserRuleContext {
		public DynamicClause c;
		public RelContext rel;
		public BetaContext beta;
		public ClauseContext clause;
		public RelContext rel() {
			return getRuleContext(RelContext.class,0);
		}
		public TerminalNode OPEN_DYN() { return getToken(RelativizedCLParser.OPEN_DYN, 0); }
		public BetaContext beta() {
			return getRuleContext(BetaContext.class,0);
		}
		public TerminalNode CLOSE_DYN() { return getToken(RelativizedCLParser.CLOSE_DYN, 0); }
		public TerminalNode OPEN_EXP() { return getToken(RelativizedCLParser.OPEN_EXP, 0); }
		public ClauseContext clause() {
			return getRuleContext(ClauseContext.class,0);
		}
		public TerminalNode CLOSE_EXP() { return getToken(RelativizedCLParser.CLOSE_EXP, 0); }
		public CdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterCd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitCd(this);
		}
	}

	public final CdContext cd() throws RecognitionException {
		CdContext _localctx = new CdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cd);
		try {
			setState(170);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				((CdContext)_localctx).rel = rel();
				setState(156);
				match(OPEN_DYN);
				setState(157);
				((CdContext)_localctx).beta = beta(0);
				setState(158);
				match(CLOSE_DYN);
				setState(159);
				match(OPEN_EXP);
				setState(160);
				((CdContext)_localctx).clause = clause(0);
				setState(161);
				match(CLOSE_EXP);

				              ((CdContext)_localctx).c =  new DynamicClause(((CdContext)_localctx).rel.sender, ((CdContext)_localctx).rel.receiver, ((CdContext)_localctx).beta.a, ((CdContext)_localctx).clause.c);
				            
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				((CdContext)_localctx).rel = rel();
				setState(165);
				match(OPEN_DYN);
				setState(166);
				((CdContext)_localctx).beta = beta(0);
				setState(167);
				match(CLOSE_DYN);

				              ((CdContext)_localctx).c =  new DynamicClause(((CdContext)_localctx).rel.sender, ((CdContext)_localctx).rel.receiver, ((CdContext)_localctx).beta.a,  BooleanClause.TRUE);
				            
				}
				break;
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

	public static class CoContext extends ParserRuleContext {
		public DeonticClause c;
		public CoContext c1;
		public RelContext rel;
		public AlphaContext alpha;
		public PenaltyContext penalty;
		public CoContext c2;
		public RelContext rel() {
			return getRuleContext(RelContext.class,0);
		}
		public TerminalNode DEO_O() { return getToken(RelativizedCLParser.DEO_O, 0); }
		public TerminalNode OPEN_EXP() { return getToken(RelativizedCLParser.OPEN_EXP, 0); }
		public AlphaContext alpha() {
			return getRuleContext(AlphaContext.class,0);
		}
		public TerminalNode CLOSE_EXP() { return getToken(RelativizedCLParser.CLOSE_EXP, 0); }
		public PenaltyContext penalty() {
			return getRuleContext(PenaltyContext.class,0);
		}
		public TerminalNode XOR() { return getToken(RelativizedCLParser.XOR, 0); }
		public List<CoContext> co() {
			return getRuleContexts(CoContext.class);
		}
		public CoContext co(int i) {
			return getRuleContext(CoContext.class,i);
		}
		public CoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_co; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterCo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitCo(this);
		}
	}

	public final CoContext co() throws RecognitionException {
		return co(0);
	}

	private CoContext co(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CoContext _localctx = new CoContext(_ctx, _parentState);
		CoContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_co, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(173);
			((CoContext)_localctx).rel = rel();
			setState(174);
			match(DEO_O);
			setState(175);
			match(OPEN_EXP);
			setState(176);
			((CoContext)_localctx).alpha = alpha(0);
			setState(177);
			match(CLOSE_EXP);
			setState(178);
			((CoContext)_localctx).penalty = penalty();

			              ((CoContext)_localctx).c =  new DeonticClause(((CoContext)_localctx).rel.sender, ((CoContext)_localctx).rel.receiver, ((CoContext)_localctx).alpha.a, DeonticClauseType.OBLIGATION ,((CoContext)_localctx).penalty.c);
			            
			}
			_ctx.stop = _input.LT(-1);
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CoContext(_parentctx, _parentState);
					_localctx.c1 = _prevctx;
					_localctx.c1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_co);
					setState(181);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(182);
					match(XOR);
					setState(183);
					((CoContext)_localctx).c2 = co(2);

					                           ((CoContext)_localctx).c2.c.setComposition(new ClauseComposition(ClauseCompositionType.XOR, ((CoContext)_localctx).c1.c));
					                           ((CoContext)_localctx).c =  ((CoContext)_localctx).c2.c;
					                      
					}
					} 
				}
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CpContext extends ParserRuleContext {
		public DeonticClause c;
		public CpContext c1;
		public RelContext rel;
		public AlphaContext alpha;
		public CpContext c2;
		public RelContext rel() {
			return getRuleContext(RelContext.class,0);
		}
		public TerminalNode DEO_P() { return getToken(RelativizedCLParser.DEO_P, 0); }
		public TerminalNode OPEN_EXP() { return getToken(RelativizedCLParser.OPEN_EXP, 0); }
		public AlphaContext alpha() {
			return getRuleContext(AlphaContext.class,0);
		}
		public TerminalNode CLOSE_EXP() { return getToken(RelativizedCLParser.CLOSE_EXP, 0); }
		public TerminalNode XOR() { return getToken(RelativizedCLParser.XOR, 0); }
		public List<CpContext> cp() {
			return getRuleContexts(CpContext.class);
		}
		public CpContext cp(int i) {
			return getRuleContext(CpContext.class,i);
		}
		public CpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterCp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitCp(this);
		}
	}

	public final CpContext cp() throws RecognitionException {
		return cp(0);
	}

	private CpContext cp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CpContext _localctx = new CpContext(_ctx, _parentState);
		CpContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_cp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(192);
			((CpContext)_localctx).rel = rel();
			setState(193);
			match(DEO_P);
			setState(194);
			match(OPEN_EXP);
			setState(195);
			((CpContext)_localctx).alpha = alpha(0);
			setState(196);
			match(CLOSE_EXP);

			              ((CpContext)_localctx).c =  new DeonticClause(((CpContext)_localctx).rel.sender, ((CpContext)_localctx).rel.receiver, ((CpContext)_localctx).alpha.a, DeonticClauseType.PERMISSION ,null);
			            
			}
			_ctx.stop = _input.LT(-1);
			setState(206);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CpContext(_parentctx, _parentState);
					_localctx.c1 = _prevctx;
					_localctx.c1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_cp);
					setState(199);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(200);
					match(XOR);
					setState(201);
					((CpContext)_localctx).c2 = cp(2);

					                           ((CpContext)_localctx).c2.c.setComposition(new ClauseComposition(ClauseCompositionType.XOR, ((CpContext)_localctx).c1.c));
					                           ((CpContext)_localctx).c =  ((CpContext)_localctx).c2.c;
					                      
					}
					} 
				}
				setState(208);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CfContext extends ParserRuleContext {
		public DeonticClause c;
		public CfContext c2;
		public CdContext c1;
		public RelContext rel;
		public AlphaContext alpha;
		public PenaltyContext penalty;
		public TerminalNode OR() { return getToken(RelativizedCLParser.OR, 0); }
		public CdContext cd() {
			return getRuleContext(CdContext.class,0);
		}
		public CfContext cf() {
			return getRuleContext(CfContext.class,0);
		}
		public RelContext rel() {
			return getRuleContext(RelContext.class,0);
		}
		public TerminalNode DEO_F() { return getToken(RelativizedCLParser.DEO_F, 0); }
		public TerminalNode OPEN_EXP() { return getToken(RelativizedCLParser.OPEN_EXP, 0); }
		public AlphaContext alpha() {
			return getRuleContext(AlphaContext.class,0);
		}
		public TerminalNode CLOSE_EXP() { return getToken(RelativizedCLParser.CLOSE_EXP, 0); }
		public PenaltyContext penalty() {
			return getRuleContext(PenaltyContext.class,0);
		}
		public CfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterCf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitCf(this);
		}
	}

	public final CfContext cf() throws RecognitionException {
		return cf(0);
	}

	private CfContext cf(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CfContext _localctx = new CfContext(_ctx, _parentState);
		CfContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_cf, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(210);
				((CfContext)_localctx).c1 = cd();
				setState(211);
				match(OR);
				setState(212);
				((CfContext)_localctx).c2 = cf(2);

				                 ((CfContext)_localctx).c2.c.setComposition(new ClauseComposition(ClauseCompositionType.OR, ((CfContext)_localctx).c1.c));
				                 ((CfContext)_localctx).c =  ((CfContext)_localctx).c2.c;
				            
				}
				break;
			case 2:
				{
				setState(215);
				((CfContext)_localctx).rel = rel();
				setState(216);
				match(DEO_F);
				setState(217);
				match(OPEN_EXP);
				setState(218);
				((CfContext)_localctx).alpha = alpha(0);
				setState(219);
				match(CLOSE_EXP);
				setState(220);
				((CfContext)_localctx).penalty = penalty();

				              ((CfContext)_localctx).c =  new DeonticClause(((CfContext)_localctx).rel.sender, ((CfContext)_localctx).rel.receiver, ((CfContext)_localctx).alpha.a, DeonticClauseType.PROHIBITION ,((CfContext)_localctx).penalty.c);
				            
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(232);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CfContext(_parentctx, _parentState);
					_localctx.c2 = _prevctx;
					_localctx.c2 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_cf);
					setState(225);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(226);
					match(OR);
					setState(227);
					((CfContext)_localctx).c1 = cd();

					                           ((CfContext)_localctx).c1.c.setComposition(new ClauseComposition(ClauseCompositionType.OR, ((CfContext)_localctx).c2.c));
					                           ((CfContext)_localctx).c =  ((CfContext)_localctx).c2.c;
					                      
					}
					} 
				}
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RelContext extends ParserRuleContext {
		public RelativizationType type;
		public int sender;
		public int receiver;
		public Token i;
		public Token j;
		public TerminalNode OPEN_REL() { return getToken(RelativizedCLParser.OPEN_REL, 0); }
		public TerminalNode CLOSE_REL() { return getToken(RelativizedCLParser.CLOSE_REL, 0); }
		public List<TerminalNode> ID() { return getTokens(RelativizedCLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RelativizedCLParser.ID, i);
		}
		public TerminalNode SEP_REL() { return getToken(RelativizedCLParser.SEP_REL, 0); }
		public RelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterRel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitRel(this);
		}
	}

	public final RelContext rel() throws RecognitionException {
		RelContext _localctx = new RelContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_rel);
		try {
			setState(246);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				match(OPEN_REL);
				setState(236);
				((RelContext)_localctx).i = match(ID);
				setState(237);
				match(CLOSE_REL);

				                 ((RelContext)_localctx).type =  RelativizationType.RELATIVIZED;
				                 ((RelContext)_localctx).sender =  SymbolTable.getInstance().addSymbol((((RelContext)_localctx).i!=null?((RelContext)_localctx).i.getText():null), SymbolType.individual);
				                 ((RelContext)_localctx).receiver =  -1;
				            
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(OPEN_REL);
				setState(240);
				((RelContext)_localctx).i = match(ID);
				setState(241);
				match(SEP_REL);
				setState(242);
				((RelContext)_localctx).j = match(ID);
				setState(243);
				match(CLOSE_REL);

				                 ((RelContext)_localctx).type =  RelativizationType.DIRECTED;
				                 ((RelContext)_localctx).sender =  SymbolTable.getInstance().addSymbol((((RelContext)_localctx).i!=null?((RelContext)_localctx).i.getText():null), SymbolType.individual);
				                 ((RelContext)_localctx).receiver =   SymbolTable.getInstance().addSymbol((((RelContext)_localctx).j!=null?((RelContext)_localctx).j.getText():null), SymbolType.individual);
				            
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{

				               ((RelContext)_localctx).type =  RelativizationType.GLOBAL;
				               ((RelContext)_localctx).sender =  -1;
				               ((RelContext)_localctx).receiver =  -1;
				            
				}
				break;
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

	public static class AlphaContext extends ParserRuleContext {
		public Action a;
		public AlphaContext a1;
		public Token ID;
		public OpContext op;
		public AlphaContext a2;
		public TerminalNode ID() { return getToken(RelativizedCLParser.ID, 0); }
		public List<AlphaContext> alpha() {
			return getRuleContexts(AlphaContext.class);
		}
		public AlphaContext alpha(int i) {
			return getRuleContext(AlphaContext.class,i);
		}
		public TerminalNode SKIP() { return getToken(RelativizedCLParser.SKIP, 0); }
		public TerminalNode VIOLATION() { return getToken(RelativizedCLParser.VIOLATION, 0); }
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public AlphaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alpha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterAlpha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitAlpha(this);
		}
	}

	public final AlphaContext alpha() throws RecognitionException {
		return alpha(0);
	}

	private AlphaContext alpha(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AlphaContext _localctx = new AlphaContext(_ctx, _parentState);
		AlphaContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_alpha, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(249);
				((AlphaContext)_localctx).ID = match(ID);
				((AlphaContext)_localctx).a =  new BasicAction(SymbolTable.getInstance().addSymbol((((AlphaContext)_localctx).ID!=null?((AlphaContext)_localctx).ID.getText():null), SymbolType.action));
				}
				break;
			case OPEN_EXP:
				{
				setState(251);
				match(OPEN_EXP);
				setState(252);
				((AlphaContext)_localctx).a1 = alpha(0);
				setState(253);
				match(CLOSE_EXP);
				((AlphaContext)_localctx).a =  ((AlphaContext)_localctx).a1.a;
				}
				break;
			case SKIP:
				{
				setState(256);
				match(SKIP);
				((AlphaContext)_localctx).a =  BasicAction.SKIP;
				}
				break;
			case VIOLATION:
				{
				setState(258);
				match(VIOLATION);
				((AlphaContext)_localctx).a =  BasicAction.VIOLATION;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(269);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AlphaContext(_parentctx, _parentState);
					_localctx.a1 = _prevctx;
					_localctx.a1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_alpha);
					setState(262);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(263);
					((AlphaContext)_localctx).op = op();
					setState(264);
					((AlphaContext)_localctx).a2 = alpha(5);

					                           ((AlphaContext)_localctx).a =  new ComposedAction(((AlphaContext)_localctx).a1.a, ((AlphaContext)_localctx).a2.a, ((AlphaContext)_localctx).op.o);
					                      
					}
					} 
				}
				setState(271);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BetaContext extends ParserRuleContext {
		public Action a;
		public BetaContext a1;
		public Token ID;
		public BetaContext a2;
		public BetaContext b1;
		public OpContext op;
		public TerminalNode ID() { return getToken(RelativizedCLParser.ID, 0); }
		public List<BetaContext> beta() {
			return getRuleContexts(BetaContext.class);
		}
		public BetaContext beta(int i) {
			return getRuleContext(BetaContext.class,i);
		}
		public TerminalNode SKIP() { return getToken(RelativizedCLParser.SKIP, 0); }
		public TerminalNode VIOLATION() { return getToken(RelativizedCLParser.VIOLATION, 0); }
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public BetaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterBeta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitBeta(this);
		}
	}

	public final BetaContext beta() throws RecognitionException {
		return beta(0);
	}

	private BetaContext beta(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BetaContext _localctx = new BetaContext(_ctx, _parentState);
		BetaContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_beta, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(273);
				((BetaContext)_localctx).ID = match(ID);
				setState(274);
				match(OP_SEQ);
				setState(275);
				((BetaContext)_localctx).a2 = beta(11);

						         BasicAction a1 = new BasicAction(SymbolTable.getInstance().addSymbol((((BetaContext)_localctx).ID!=null?((BetaContext)_localctx).ID.getText():null), SymbolType.action));
				               ((BetaContext)_localctx).a =  new ComposedAction(a1, ((BetaContext)_localctx).a2.a, ActionOperator.SEQUENCE);
				            
				}
				break;
			case 2:
				{
				setState(278);
				((BetaContext)_localctx).ID = match(ID);
				((BetaContext)_localctx).a =  new BasicAction(SymbolTable.getInstance().addSymbol((((BetaContext)_localctx).ID!=null?((BetaContext)_localctx).ID.getText():null), SymbolType.action));
				}
				break;
			case 3:
				{
				setState(280);
				((BetaContext)_localctx).ID = match(ID);
				setState(281);
				match(UN_OP_IT);

				                ((BetaContext)_localctx).a =  new ComposedAction(new BasicAction(SymbolTable.getInstance().addSymbol((((BetaContext)_localctx).ID!=null?((BetaContext)_localctx).ID.getText():null), SymbolType.action)), null,ActionOperator.STAR);
				            
				}
				break;
			case 4:
				{
				setState(283);
				match(SKIP);
				setState(284);
				match(UN_OP_IT);

				                ((BetaContext)_localctx).a =  new ComposedAction(BasicAction.SKIP, null,ActionOperator.STAR);
				            
				}
				break;
			case 5:
				{
				setState(286);
				match(VIOLATION);
				setState(287);
				match(UN_OP_IT);

				                ((BetaContext)_localctx).a =  new ComposedAction(BasicAction.VIOLATION, null,ActionOperator.STAR);
				            
				}
				break;
			case 6:
				{
				setState(289);
				match(OPEN_EXP);
				setState(290);
				((BetaContext)_localctx).a1 = beta(0);
				setState(291);
				match(CLOSE_EXP);
				setState(292);
				match(UN_OP_IT);

				                ((BetaContext)_localctx).a =  new ComposedAction(((BetaContext)_localctx).a1.a, null,ActionOperator.STAR);
				            
				}
				break;
			case 7:
				{
				setState(295);
				match(OPEN_EXP);
				setState(296);
				((BetaContext)_localctx).b1 = beta(0);
				setState(297);
				match(CLOSE_EXP);
				((BetaContext)_localctx).a =  ((BetaContext)_localctx).b1.a;
				}
				break;
			case 8:
				{
				setState(300);
				match(UN_OP_NEG);
				setState(301);
				((BetaContext)_localctx).ID = match(ID);

				                 BasicAction a1 = new BasicAction(SymbolTable.getInstance().addSymbol((((BetaContext)_localctx).ID!=null?((BetaContext)_localctx).ID.getText():null), SymbolType.action));
				                 ((BetaContext)_localctx).a =  new ComposedAction(a1, null,ActionOperator.NEGATION);
				            
				}
				break;
			case 9:
				{
				setState(303);
				match(UN_OP_NEG);
				setState(304);
				match(OPEN_EXP);
				setState(305);
				((BetaContext)_localctx).a1 = beta(0);
				setState(306);
				match(CLOSE_EXP);

				                 ((BetaContext)_localctx).a =  new ComposedAction(((BetaContext)_localctx).a1.a, null,ActionOperator.NEGATION);
				            
				}
				break;
			case 10:
				{
				setState(309);
				match(SKIP);
				((BetaContext)_localctx).a =  BasicAction.SKIP;
				}
				break;
			case 11:
				{
				setState(311);
				match(VIOLATION);
				((BetaContext)_localctx).a =  BasicAction.VIOLATION;
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(322);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BetaContext(_parentctx, _parentState);
					_localctx.a1 = _prevctx;
					_localctx.a1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_beta);
					setState(315);
					if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
					setState(316);
					((BetaContext)_localctx).op = op();
					setState(317);
					((BetaContext)_localctx).a2 = beta(11);

					                                ((BetaContext)_localctx).a =  new ComposedAction(((BetaContext)_localctx).a1.a, ((BetaContext)_localctx).a2.a, ((BetaContext)_localctx).op.o);
					                      
					}
					} 
				}
				setState(324);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OpContext extends ParserRuleContext {
		public ActionOperator o;
		public TerminalNode OP_CHOICE() { return getToken(RelativizedCLParser.OP_CHOICE, 0); }
		public TerminalNode OP_SEQ() { return getToken(RelativizedCLParser.OP_SEQ, 0); }
		public TerminalNode OP_CONC() { return getToken(RelativizedCLParser.OP_CONC, 0); }
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelativizedCLListener ) ((RelativizedCLListener)listener).exitOp(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_op);
		try {
			setState(331);
			switch (_input.LA(1)) {
			case OP_CHOICE:
				enterOuterAlt(_localctx, 1);
				{
				setState(325);
				match(OP_CHOICE);
				((OpContext)_localctx).o =  ActionOperator.CHOICE;
				}
				break;
			case OP_SEQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(327);
				match(OP_SEQ);
				((OpContext)_localctx).o =  ActionOperator.SEQUENCE;
				}
				break;
			case OP_CONC:
				enterOuterAlt(_localctx, 3);
				{
				setState(329);
				match(OP_CONC);
				((OpContext)_localctx).o =  ActionOperator.CONCURRENCY;
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return clause_sempred((ClauseContext)_localctx, predIndex);
		case 8:
			return co_sempred((CoContext)_localctx, predIndex);
		case 9:
			return cp_sempred((CpContext)_localctx, predIndex);
		case 10:
			return cf_sempred((CfContext)_localctx, predIndex);
		case 12:
			return alpha_sempred((AlphaContext)_localctx, predIndex);
		case 13:
			return beta_sempred((BetaContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean clause_sempred(ClauseContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		}
		return true;
	}
	private boolean co_sempred(CoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean cp_sempred(CpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean cf_sempred(CfContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean alpha_sempred(AlphaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean beta_sempred(BetaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u0150\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\3\2\3\2\5\2\64"+
		"\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3E"+
		"\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4O\n\4\f\4\16\4R\13\4\3\4\3\4\3"+
		"\4\3\4\5\4X\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5b\n\5\f\5\16\5e\13"+
		"\5\3\5\3\5\3\5\3\5\5\5k\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7\u008a\n\7\3\7\3\7\3\7\3\7\3\7\7\7\u0091\n\7\f\7\16\7\u0094\13"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u009c\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ad\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00bd\n\n\f\n\16\n\u00c0\13\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00cf"+
		"\n\13\f\13\16\13\u00d2\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00e2\n\f\3\f\3\f\3\f\3\f\3\f\7\f\u00e9\n\f\f\f\16"+
		"\f\u00ec\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00f9\n"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0107"+
		"\n\16\3\16\3\16\3\16\3\16\3\16\7\16\u010e\n\16\f\16\16\16\u0111\13\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u013c\n\17\3\17\3\17\3\17\3\17\3\17\7\17\u0143\n\17\f\17\16\17\u0146"+
		"\13\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u014e\n\20\3\20\2\b\f\22\24"+
		"\26\32\34\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\u0168\2\63\3\2"+
		"\2\2\4D\3\2\2\2\6W\3\2\2\2\bj\3\2\2\2\nl\3\2\2\2\f\u0089\3\2\2\2\16\u009b"+
		"\3\2\2\2\20\u00ac\3\2\2\2\22\u00ae\3\2\2\2\24\u00c1\3\2\2\2\26\u00e1\3"+
		"\2\2\2\30\u00f8\3\2\2\2\32\u0106\3\2\2\2\34\u013b\3\2\2\2\36\u014d\3\2"+
		"\2\2 !\5\4\3\2!\"\5\f\7\2\"#\7\37\2\2#$\b\2\1\2$\64\3\2\2\2%&\5\4\3\2"+
		"&\'\5\f\7\2\'.\b\2\1\2()\7\37\2\2)*\5\f\7\2*+\b\2\1\2+-\3\2\2\2,(\3\2"+
		"\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\62\7\37"+
		"\2\2\62\64\3\2\2\2\63 \3\2\2\2\63%\3\2\2\2\64\3\3\2\2\2\65\66\7\3\2\2"+
		"\66\67\7\b\2\2\678\5\6\4\289\5\b\5\29:\7\n\2\2:;\7\37\2\2;E\3\2\2\2<="+
		"\7\3\2\2=>\7\b\2\2>?\5\b\5\2?@\5\6\4\2@A\7\n\2\2AB\7\37\2\2BE\3\2\2\2"+
		"CE\3\2\2\2D\65\3\2\2\2D<\3\2\2\2DC\3\2\2\2E\5\3\2\2\2FG\7\4\2\2GH\7\b"+
		"\2\2HI\5\n\6\2IP\b\4\1\2JK\7\t\2\2KL\5\n\6\2LM\b\4\1\2MO\3\2\2\2NJ\3\2"+
		"\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7\n\2\2TU\7\37"+
		"\2\2UX\3\2\2\2VX\3\2\2\2WF\3\2\2\2WV\3\2\2\2X\7\3\2\2\2YZ\7\5\2\2Z[\7"+
		"\b\2\2[\\\5\n\6\2\\c\b\5\1\2]^\7\t\2\2^_\5\n\6\2_`\b\5\1\2`b\3\2\2\2a"+
		"]\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7\n\2\2"+
		"gh\7\37\2\2hk\3\2\2\2ik\3\2\2\2jY\3\2\2\2ji\3\2\2\2k\t\3\2\2\2lm\7\13"+
		"\2\2mn\7\34\2\2no\7\t\2\2op\7\34\2\2pq\7\f\2\2qr\b\6\1\2r\13\3\2\2\2s"+
		"t\b\7\1\2tu\5\22\n\2uv\b\7\1\2v\u008a\3\2\2\2wx\5\24\13\2xy\b\7\1\2y\u008a"+
		"\3\2\2\2z{\5\26\f\2{|\b\7\1\2|\u008a\3\2\2\2}~\5\20\t\2~\177\b\7\1\2\177"+
		"\u008a\3\2\2\2\u0080\u0081\7\32\2\2\u0081\u008a\b\7\1\2\u0082\u0083\7"+
		"\33\2\2\u0083\u008a\b\7\1\2\u0084\u0085\7\13\2\2\u0085\u0086\5\f\7\2\u0086"+
		"\u0087\7\f\2\2\u0087\u0088\b\7\1\2\u0088\u008a\3\2\2\2\u0089s\3\2\2\2"+
		"\u0089w\3\2\2\2\u0089z\3\2\2\2\u0089}\3\2\2\2\u0089\u0080\3\2\2\2\u0089"+
		"\u0082\3\2\2\2\u0089\u0084\3\2\2\2\u008a\u0092\3\2\2\2\u008b\u008c\f\n"+
		"\2\2\u008c\u008d\7\27\2\2\u008d\u008e\5\f\7\13\u008e\u008f\b\7\1\2\u008f"+
		"\u0091\3\2\2\2\u0090\u008b\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\r\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0096"+
		"\7\6\2\2\u0096\u0097\5\f\7\2\u0097\u0098\7\7\2\2\u0098\u0099\b\b\1\2\u0099"+
		"\u009c\3\2\2\2\u009a\u009c\b\b\1\2\u009b\u0095\3\2\2\2\u009b\u009a\3\2"+
		"\2\2\u009c\17\3\2\2\2\u009d\u009e\5\30\r\2\u009e\u009f\7\r\2\2\u009f\u00a0"+
		"\5\34\17\2\u00a0\u00a1\7\16\2\2\u00a1\u00a2\7\13\2\2\u00a2\u00a3\5\f\7"+
		"\2\u00a3\u00a4\7\f\2\2\u00a4\u00a5\b\t\1\2\u00a5\u00ad\3\2\2\2\u00a6\u00a7"+
		"\5\30\r\2\u00a7\u00a8\7\r\2\2\u00a8\u00a9\5\34\17\2\u00a9\u00aa\7\16\2"+
		"\2\u00aa\u00ab\b\t\1\2\u00ab\u00ad\3\2\2\2\u00ac\u009d\3\2\2\2\u00ac\u00a6"+
		"\3\2\2\2\u00ad\21\3\2\2\2\u00ae\u00af\b\n\1\2\u00af\u00b0\5\30\r\2\u00b0"+
		"\u00b1\7\17\2\2\u00b1\u00b2\7\13\2\2\u00b2\u00b3\5\32\16\2\u00b3\u00b4"+
		"\7\f\2\2\u00b4\u00b5\5\16\b\2\u00b5\u00b6\b\n\1\2\u00b6\u00be\3\2\2\2"+
		"\u00b7\u00b8\f\3\2\2\u00b8\u00b9\7\31\2\2\u00b9\u00ba\5\22\n\4\u00ba\u00bb"+
		"\b\n\1\2\u00bb\u00bd\3\2\2\2\u00bc\u00b7\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\23\3\2\2\2\u00c0\u00be\3\2\2"+
		"\2\u00c1\u00c2\b\13\1\2\u00c2\u00c3\5\30\r\2\u00c3\u00c4\7\20\2\2\u00c4"+
		"\u00c5\7\13\2\2\u00c5\u00c6\5\32\16\2\u00c6\u00c7\7\f\2\2\u00c7\u00c8"+
		"\b\13\1\2\u00c8\u00d0\3\2\2\2\u00c9\u00ca\f\3\2\2\u00ca\u00cb\7\31\2\2"+
		"\u00cb\u00cc\5\24\13\4\u00cc\u00cd\b\13\1\2\u00cd\u00cf\3\2\2\2\u00ce"+
		"\u00c9\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\25\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d4\b\f\1\2\u00d4\u00d5"+
		"\5\20\t\2\u00d5\u00d6\7\30\2\2\u00d6\u00d7\5\26\f\4\u00d7\u00d8\b\f\1"+
		"\2\u00d8\u00e2\3\2\2\2\u00d9\u00da\5\30\r\2\u00da\u00db\7\21\2\2\u00db"+
		"\u00dc\7\13\2\2\u00dc\u00dd\5\32\16\2\u00dd\u00de\7\f\2\2\u00de\u00df"+
		"\5\16\b\2\u00df\u00e0\b\f\1\2\u00e0\u00e2\3\2\2\2\u00e1\u00d3\3\2\2\2"+
		"\u00e1\u00d9\3\2\2\2\u00e2\u00ea\3\2\2\2\u00e3\u00e4\f\3\2\2\u00e4\u00e5"+
		"\7\30\2\2\u00e5\u00e6\5\20\t\2\u00e6\u00e7\b\f\1\2\u00e7\u00e9\3\2\2\2"+
		"\u00e8\u00e3\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb"+
		"\3\2\2\2\u00eb\27\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\7\b\2\2\u00ee"+
		"\u00ef\7\34\2\2\u00ef\u00f0\7\n\2\2\u00f0\u00f9\b\r\1\2\u00f1\u00f2\7"+
		"\b\2\2\u00f2\u00f3\7\34\2\2\u00f3\u00f4\7\t\2\2\u00f4\u00f5\7\34\2\2\u00f5"+
		"\u00f6\7\n\2\2\u00f6\u00f9\b\r\1\2\u00f7\u00f9\b\r\1\2\u00f8\u00ed\3\2"+
		"\2\2\u00f8\u00f1\3\2\2\2\u00f8\u00f7\3\2\2\2\u00f9\31\3\2\2\2\u00fa\u00fb"+
		"\b\16\1\2\u00fb\u00fc\7\34\2\2\u00fc\u0107\b\16\1\2\u00fd\u00fe\7\13\2"+
		"\2\u00fe\u00ff\5\32\16\2\u00ff\u0100\7\f\2\2\u0100\u0101\b\16\1\2\u0101"+
		"\u0107\3\2\2\2\u0102\u0103\7\35\2\2\u0103\u0107\b\16\1\2\u0104\u0105\7"+
		"\36\2\2\u0105\u0107\b\16\1\2\u0106\u00fa\3\2\2\2\u0106\u00fd\3\2\2\2\u0106"+
		"\u0102\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u010f\3\2\2\2\u0108\u0109\f\6"+
		"\2\2\u0109\u010a\5\36\20\2\u010a\u010b\5\32\16\7\u010b\u010c\b\16\1\2"+
		"\u010c\u010e\3\2\2\2\u010d\u0108\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d"+
		"\3\2\2\2\u010f\u0110\3\2\2\2\u0110\33\3\2\2\2\u0111\u010f\3\2\2\2\u0112"+
		"\u0113\b\17\1\2\u0113\u0114\7\34\2\2\u0114\u0115\7\23\2\2\u0115\u0116"+
		"\5\34\17\r\u0116\u0117\b\17\1\2\u0117\u013c\3\2\2\2\u0118\u0119\7\34\2"+
		"\2\u0119\u013c\b\17\1\2\u011a\u011b\7\34\2\2\u011b\u011c\7\25\2\2\u011c"+
		"\u013c\b\17\1\2\u011d\u011e\7\35\2\2\u011e\u011f\7\25\2\2\u011f\u013c"+
		"\b\17\1\2\u0120\u0121\7\36\2\2\u0121\u0122\7\25\2\2\u0122\u013c\b\17\1"+
		"\2\u0123\u0124\7\13\2\2\u0124\u0125\5\34\17\2\u0125\u0126\7\f\2\2\u0126"+
		"\u0127\7\25\2\2\u0127\u0128\b\17\1\2\u0128\u013c\3\2\2\2\u0129\u012a\7"+
		"\13\2\2\u012a\u012b\5\34\17\2\u012b\u012c\7\f\2\2\u012c\u012d\b\17\1\2"+
		"\u012d\u013c\3\2\2\2\u012e\u012f\7\26\2\2\u012f\u0130\7\34\2\2\u0130\u013c"+
		"\b\17\1\2\u0131\u0132\7\26\2\2\u0132\u0133\7\13\2\2\u0133\u0134\5\34\17"+
		"\2\u0134\u0135\7\f\2\2\u0135\u0136\b\17\1\2\u0136\u013c\3\2\2\2\u0137"+
		"\u0138\7\35\2\2\u0138\u013c\b\17\1\2\u0139\u013a\7\36\2\2\u013a\u013c"+
		"\b\17\1\2\u013b\u0112\3\2\2\2\u013b\u0118\3\2\2\2\u013b\u011a\3\2\2\2"+
		"\u013b\u011d\3\2\2\2\u013b\u0120\3\2\2\2\u013b\u0123\3\2\2\2\u013b\u0129"+
		"\3\2\2\2\u013b\u012e\3\2\2\2\u013b\u0131\3\2\2\2\u013b\u0137\3\2\2\2\u013b"+
		"\u0139\3\2\2\2\u013c\u0144\3\2\2\2\u013d\u013e\f\f\2\2\u013e\u013f\5\36"+
		"\20\2\u013f\u0140\5\34\17\r\u0140\u0141\b\17\1\2\u0141\u0143\3\2\2\2\u0142"+
		"\u013d\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2"+
		"\2\2\u0145\35\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0148\7\22\2\2\u0148\u014e"+
		"\b\20\1\2\u0149\u014a\7\23\2\2\u014a\u014e\b\20\1\2\u014b\u014c\7\24\2"+
		"\2\u014c\u014e\b\20\1\2\u014d\u0147\3\2\2\2\u014d\u0149\3\2\2\2\u014d"+
		"\u014b\3\2\2\2\u014e\37\3\2\2\2\27.\63DPWcj\u0089\u0092\u009b\u00ac\u00be"+
		"\u00d0\u00e1\u00ea\u00f8\u0106\u010f\u013b\u0144\u014d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}