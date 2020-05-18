grammar SmallLangV2;

/*
Done to add package on top of generated files
*/
@header {
package antlrSrc;
}

literal : BooleanLiteral | IntegerLiteral | FloatLiteral | CharLiteral;
multiplicativeOp : TIMES | DIVIDE | AND;
additiveOp : PLUS | MINUS | OR;
relationalOp : LT | GT | EQUAL | NOT_EQUAL | LTE | GTE;
actualParams :  expression (COMMA expression)*;
functionCall : Identifier BRACKET_OPEN actualParams? BRACKET_CLOSE;
subExpression : BRACKET_OPEN expression BRACKET_CLOSE;
unary : (MINUS | NOT) expression;
factor : literal | abstractIdentifier | functionCall | subExpression | unary;
term : factor (multiplicativeOp factor)*;
simpleExpression : term (additiveOp term)*;
expression : simpleExpression (relationalOp simpleExpression)*;
assignment : abstractIdentifier EQUAL_SIGN expression;
arrayIndex : SQUARE_OPEN expression SQUARE_CLOSE;
variableDecl : Identifier COLON (Type | Auto) EQUAL_SIGN expression;
arrayDecl : Identifier SQUARE_OPEN expression? SQUARE_CLOSE COLON Type EQUAL_SIGN arrayValue;
arrayIdentifier : Identifier arrayIndex;
abstractIdentifier: Identifier | arrayIdentifier;
arrayValue : CURLY_OPEN (expression (COMMA expression)*)? CURLY_CLOSE;
declaration : 'let' (variableDecl | arrayDecl);
printStatement : PRINT expression;
rtrnStatement : RETURN expression;
ifStatement : IF BRACKET_OPEN expression BRACKET_CLOSE block (ELSE block)?;
forStatement : FOR BRACKET_OPEN variableDecl? SEMI_COLON expression SEMI_COLON assignment? BRACKET_CLOSE block;
whileStatement : WHILE BRACKET_OPEN expression BRACKET_CLOSE block;
formalParam : Identifier (SQUARE_OPEN SQUARE_CLOSE)? COLON Type;
formalParams : formalParam (COMMA formalParam)*;
functionDecl : FF Identifier BRACKET_OPEN formalParams? BRACKET_CLOSE COLON (Type | Auto) block;
statement : declaration SEMI_COLON
          | assignment SEMI_COLON
          | printStatement SEMI_COLON
          | ifStatement
          | forStatement
          | whileStatement
          | rtrnStatement SEMI_COLON
          | functionDecl
          | block;
block : CURLY_OPEN statement* CURLY_CLOSE;
program : statement*;

fragment DIGIT : [0-9];
fragment LETTER : [A-Za-z];
LET : 'let';
NOT : 'not';
MINUS : '-';
EQUAL_SIGN : '=';
COLON : ':';
SEMI_COLON : ';';
BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';
CURLY_OPEN : '{';
CURLY_CLOSE : '}';
SQUARE_OPEN : '[';
SQUARE_CLOSE : ']';
COMMA : ',';
IF: 'if';
ELSE: 'else';
FOR : 'for';
FF : 'ff';
PRINT : 'print';
RETURN : 'return';
WHILE : 'while';
Type : 'float' | 'int' | 'bool' | 'char';
Auto : 'auto';
AND : 'and';
OR : 'or';
TIMES : '*';
DIVIDE : '/';
PLUS : '+';
LT : '<';
GT : '>';
EQUAL : '==';
NOT_EQUAL : '<>';
LTE : '<=';
GTE: '>=';
BooleanLiteral : 'true' | 'false';
IntegerLiteral : DIGIT+;
FloatLiteral : DIGIT+ '.' DIGIT+;
CharLiteral : '\'' LETTER '\'';
Identifier : ('_' | LETTER) ('_' | LETTER | DIGIT)*;
WS : [ \r\t\n]+ -> skip ;
COMMENT : '//' .*? [\n] -> skip;
MULTI_LINE_COMMENT : '/*' .*? '*/' -> skip ; // .*?
