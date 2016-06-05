// Generated from RelativizedCL.g4 by ANTLR 4.5

package recall.parser;

import recall.model.actions.*;
import java.util.*;
import recall.model.contracts.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RelativizedCLParser}.
 */
public interface RelativizedCLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#contract}.
	 * @param ctx the parse tree
	 */
	void enterContract(RelativizedCLParser.ContractContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#contract}.
	 * @param ctx the parse tree
	 */
	void exitContract(RelativizedCLParser.ContractContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#conflict}.
	 * @param ctx the parse tree
	 */
	void enterConflict(RelativizedCLParser.ConflictContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#conflict}.
	 * @param ctx the parse tree
	 */
	void exitConflict(RelativizedCLParser.ConflictContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#cfGlobal}.
	 * @param ctx the parse tree
	 */
	void enterCfGlobal(RelativizedCLParser.CfGlobalContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#cfGlobal}.
	 * @param ctx the parse tree
	 */
	void exitCfGlobal(RelativizedCLParser.CfGlobalContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#cfRel}.
	 * @param ctx the parse tree
	 */
	void enterCfRel(RelativizedCLParser.CfRelContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#cfRel}.
	 * @param ctx the parse tree
	 */
	void exitCfRel(RelativizedCLParser.CfRelContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#cfPair}.
	 * @param ctx the parse tree
	 */
	void enterCfPair(RelativizedCLParser.CfPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#cfPair}.
	 * @param ctx the parse tree
	 */
	void exitCfPair(RelativizedCLParser.CfPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(RelativizedCLParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(RelativizedCLParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#penalty}.
	 * @param ctx the parse tree
	 */
	void enterPenalty(RelativizedCLParser.PenaltyContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#penalty}.
	 * @param ctx the parse tree
	 */
	void exitPenalty(RelativizedCLParser.PenaltyContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#cd}.
	 * @param ctx the parse tree
	 */
	void enterCd(RelativizedCLParser.CdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#cd}.
	 * @param ctx the parse tree
	 */
	void exitCd(RelativizedCLParser.CdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#co}.
	 * @param ctx the parse tree
	 */
	void enterCo(RelativizedCLParser.CoContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#co}.
	 * @param ctx the parse tree
	 */
	void exitCo(RelativizedCLParser.CoContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#cp}.
	 * @param ctx the parse tree
	 */
	void enterCp(RelativizedCLParser.CpContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#cp}.
	 * @param ctx the parse tree
	 */
	void exitCp(RelativizedCLParser.CpContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#cf}.
	 * @param ctx the parse tree
	 */
	void enterCf(RelativizedCLParser.CfContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#cf}.
	 * @param ctx the parse tree
	 */
	void exitCf(RelativizedCLParser.CfContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#rel}.
	 * @param ctx the parse tree
	 */
	void enterRel(RelativizedCLParser.RelContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#rel}.
	 * @param ctx the parse tree
	 */
	void exitRel(RelativizedCLParser.RelContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#alpha}.
	 * @param ctx the parse tree
	 */
	void enterAlpha(RelativizedCLParser.AlphaContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#alpha}.
	 * @param ctx the parse tree
	 */
	void exitAlpha(RelativizedCLParser.AlphaContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#beta}.
	 * @param ctx the parse tree
	 */
	void enterBeta(RelativizedCLParser.BetaContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#beta}.
	 * @param ctx the parse tree
	 */
	void exitBeta(RelativizedCLParser.BetaContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelativizedCLParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(RelativizedCLParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelativizedCLParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(RelativizedCLParser.OpContext ctx);
}