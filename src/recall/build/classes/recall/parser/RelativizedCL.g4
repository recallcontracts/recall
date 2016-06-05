grammar RelativizedCL;
@header {
package recall.parser;

import recall.model.actions.*;
import java.util.*;
import recall.model.contracts.*;
}

@members{
   ArrayList<Conflict> globalCNF = new  ArrayList<Conflict>();
   ArrayList<Conflict> relCNF = new  ArrayList<Conflict>();
}

//GRAMMAR----------------------------------------------------------------------
contract    returns [Contract c]
            : conflict c1=clause END {$c = new Contract($c1.c); $c.setConflicts(globalCNF, relCNF); }
            | conflict c1=clause {$c = new Contract($c1.c); $c.setConflicts(globalCNF, relCNF); } (END c2=clause {$c.addClause($c2.c);})* END
            ;

conflict    : CONFLICT '{' cfGlobal cfRel '}' END
            | CONFLICT '{' cfRel cfGlobal '}' END
            | /*no conflicts*/
            ;

cfGlobal    : GLOBAL      '{' p1=cfPair { globalCNF.add(new Conflict($p1.a, $p1.b, ConflictType.GLOBAL));}
                         (',' p2=cfPair { globalCNF.add(new Conflict($p2.a, $p2.b, ConflictType.GLOBAL));} )* '}' END
            | ;
cfRel       : RELATIVIZED'{'  p1=cfPair {relCNF.add(new Conflict($p1.a, $p1.b, ConflictType.RELATIVIZED));}
                         (',' p2=cfPair {relCNF.add(new Conflict($p2.a, $p2.b, ConflictType.RELATIVIZED));} )* '}' END
            | ;
cfPair      returns [BasicAction a, BasicAction b]
            : '(' id1=ID ',' id2=ID ')' {
                $a = new BasicAction(SymbolTable.getInstance().addSymbol($id1.text, SymbolType.action));
                $b = new BasicAction(SymbolTable.getInstance().addSymbol($id2.text, SymbolType.action));
            };

clause      returns [Clause c]
            : c1=clause AND c2=clause {
              $c2.c.setComposition(new ClauseComposition(ClauseCompositionType.AND, $c1.c));
              $c = $c2.c;
             }
            | co {$c = $co.c;}
            | cp {$c = $cp.c;}
            | cf {$c = $cf.c;}
            | cd {$c = $cd.c;}
            | T {$c = BooleanClause.TRUE;}
            | F {$c = BooleanClause.FALSE;}
            | '(' c1=clause ')' {$c = $c1.c;}
            ;

penalty     returns [Clause c]
            : OPEN_PTY clause CLOSE_PTY {$c = $clause.c;}
            | {$c = BooleanClause.FALSE;}
            ;

cd          returns [DynamicClause c]
            : rel OPEN_DYN beta CLOSE_DYN OPEN_EXP clause CLOSE_EXP {
              $c = new DynamicClause($rel.sender, $rel.receiver, $beta.a, $clause.c);
            }
            | rel OPEN_DYN beta CLOSE_DYN {
              $c = new DynamicClause($rel.sender, $rel.receiver, $beta.a,  BooleanClause.TRUE);
            }
            ;

co          returns [DeonticClause c]
            : rel DEO_O OPEN_EXP alpha CLOSE_EXP penalty {
              $c = new DeonticClause($rel.sender, $rel.receiver, $alpha.a, DeonticClauseType.OBLIGATION ,$penalty.c);
            }
            | c1=co XOR c2=co{
                 $c2.c.setComposition(new ClauseComposition(ClauseCompositionType.XOR, $c1.c));
                 $c = $c2.c;
            }
            ;

cp          returns [DeonticClause c]
            : rel DEO_P OPEN_EXP alpha CLOSE_EXP {
              $c = new DeonticClause($rel.sender, $rel.receiver, $alpha.a, DeonticClauseType.PERMISSION ,null);
            }
            | c1=cp XOR c2=cp{
                 $c2.c.setComposition(new ClauseComposition(ClauseCompositionType.XOR, $c1.c));
                 $c = $c2.c;
            }
            ;

cf          returns [DeonticClause c]
            : rel DEO_F OPEN_EXP alpha CLOSE_EXP penalty {
              $c = new DeonticClause($rel.sender, $rel.receiver, $alpha.a, DeonticClauseType.PROHIBITION ,$penalty.c);
            }
            | c1=cd OR c2=cf{
                 $c2.c.setComposition(new ClauseComposition(ClauseCompositionType.OR, $c1.c));
                 $c = $c2.c;
            }
            | c2=cf OR c1=cd{
                 $c1.c.setComposition(new ClauseComposition(ClauseCompositionType.OR, $c2.c));
                 $c = $c2.c;
            }
            ;

rel         returns [RelativizationType type, int sender, int receiver]
            : OPEN_REL i=ID CLOSE_REL {
                 $type = RelativizationType.RELATIVIZED;
                 $sender = SymbolTable.getInstance().addSymbol($i.text, SymbolType.individual);
                 $receiver = -1;
            }
            | OPEN_REL i=ID SEP_REL j=ID CLOSE_REL {
                 $type = RelativizationType.DIRECTED;
                 $sender = SymbolTable.getInstance().addSymbol($i.text, SymbolType.individual);
                 $receiver =  SymbolTable.getInstance().addSymbol($j.text, SymbolType.individual);
            }
            | {
               $type = RelativizationType.GLOBAL;
               $sender = -1;
               $receiver = -1;
            };

alpha       returns [Action a]
            : ID {$a = new BasicAction(SymbolTable.getInstance().addSymbol($ID.text, SymbolType.action));}
            | a1=alpha op a2=alpha {
                 $a = new ComposedAction($a1.a, $a2.a, $op.o);
            }
            | '(' a1=alpha ')' {$a = $a1.a;}
            | SKIP {$a = BasicAction.SKIP;}
            | VIOLATION {$a = BasicAction.VIOLATION;}
            ;

beta        returns [Action a]
            : ID {$a = new BasicAction(SymbolTable.getInstance().addSymbol($ID.text, SymbolType.action));}
            | ID '.' a2=beta {
		         BasicAction a1 = new BasicAction(SymbolTable.getInstance().addSymbol($ID.text, SymbolType.action));
               $a = new ComposedAction(a1, $a2.a, ActionOperator.SEQUENCE);
            }
            | a1=beta op a2=beta {
                      $a = new ComposedAction($a1.a, $a2.a, $op.o);
            }
            | ID '*'{
                $a = new ComposedAction(new BasicAction(SymbolTable.getInstance().addSymbol($ID.text, SymbolType.action)), null,ActionOperator.STAR);
            }
            | SKIP '*'{
                $a = new ComposedAction(BasicAction.SKIP, null,ActionOperator.STAR);
            }
            | VIOLATION '*'{
                $a = new ComposedAction(BasicAction.VIOLATION, null,ActionOperator.STAR);
            }
            | '('a1=beta')' '*'{
                $a = new ComposedAction($a1.a, null,ActionOperator.STAR);
            }
            | '(' b1=beta ')' {$a = $b1.a;}
            | '!' ID {
                 BasicAction a1 = new BasicAction(SymbolTable.getInstance().addSymbol($ID.text, SymbolType.action));
                 $a = new ComposedAction(a1, null,ActionOperator.NEGATION);
            }
            | '!' '('a1=beta')' {
                 $a = new ComposedAction($a1.a, null,ActionOperator.NEGATION);
            }
            | SKIP {$a = BasicAction.SKIP;}
            | VIOLATION {$a = BasicAction.VIOLATION;}
            ;

op          returns [ActionOperator o]
            : OP_CHOICE {$o = ActionOperator.CHOICE;}
            | OP_SEQ    {$o = ActionOperator.SEQUENCE;}
            | OP_CONC   {$o = ActionOperator.CONCURRENCY;}
            ;


//TOKENS---------------------------------------------------------------------
CONFLICT    : 'conflict';
GLOBAL      : 'global';
RELATIVIZED : 'relativized';

OPEN_PTY    : '_/';
CLOSE_PTY   : '/_';
OPEN_REL    : '{';
SEP_REL     : ',';
CLOSE_REL   : '}';
OPEN_EXP    : '(';
CLOSE_EXP   : ')';
OPEN_DYN    : '[';
CLOSE_DYN   : ']';

DEO_O       : 'O'|'_Obliged';
DEO_P       : 'P'|'_Permitted';
DEO_F       : 'F'|'_Prohibited';

OP_CHOICE   : '+';
OP_SEQ      : '.';
OP_CONC     : '&';
UN_OP_IT    : '*';
UN_OP_NEG   : '!';

AND         : '^'|'AND';
OR          : '|'|'OR';
XOR         : '-'|'XOR';

T           : 'true';
F           : 'false';

ID          : [a-zA-Z][a-zA-Z0-9_]*;

SKIP        : '1';
VIOLATION   : '0';
END         : ';';

COMMENT     : '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
WS          : [ \t\r\n]+ -> skip;
