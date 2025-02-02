grammar SmallLang;

//used to add the package for the generated files
@header {
package antlrSrc;
}

//literal
literal : BooleanLiteral | IntegerLiteral | FloatLiteral;

//operators
multiplicativeOp : TIMES | DIVIDE | AND;
additiveOp : PLUS | MINUS | OR;
relationalOp : LT | GT | EQUAL | NOT_EQUAL | LTE | GTE;

//EBNF expression
actualParams :  expression (COMMA expression)*;
functionCall : Identifier BRACKET_OPEN actualParams? BRACKET_CLOSE;
subExpression : BRACKET_OPEN expression BRACKET_CLOSE;
unary : (MINUS | NOT) expression;
factor : literal | Identifier | functionCall | subExpression | unary;
term : factor (multiplicativeOp factor)*;
simpleExpression : term (additiveOp term)*;
expression : simpleExpression (relationalOp simpleExpression)*;
assignment : Identifier EQUAL_SIGN expression;

//EBNF statements
variableDecl : LET Identifier COLON (Type | Auto) EQUAL_SIGN expression;
printStatement : PRINT expression;
rtrnStatement : RETURN expression;
ifStatement : IF BRACKET_OPEN expression BRACKET_CLOSE block (ELSE block)?;
forStatement : FOR BRACKET_OPEN variableDecl? SEMI_COLON expression SEMI_COLON assignment? BRACKET_CLOSE block;
whileStatement : WHILE BRACKET_OPEN expression BRACKET_CLOSE block;
formalParam : Identifier COLON Type;
formalParams : formalParam (COMMA formalParam)*;
functionDecl : FF Identifier BRACKET_OPEN formalParams? BRACKET_CLOSE COLON (Type | Auto) block;
statement : variableDecl SEMI_COLON
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

//fragments to make up literals and identifiers
fragment DIGIT : [0-9];
fragment LETTER : [A-Za-z];

//different types of tokens
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
COMMA : ',';
IF: 'if';
ELSE: 'else';
FOR : 'for';
FF : 'ff';
PRINT : 'print';
RETURN : 'return';
WHILE : 'while';
Type : 'float' | 'int' | 'bool';
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

//tokens for literals
BooleanLiteral : 'true' | 'false';
IntegerLiteral : DIGIT+;
FloatLiteral : IntegerLiteral '.' IntegerLiteral;

//token for identifier
Identifier : ('_' | LETTER) ('_' | LETTER | DIGIT)*;

//used to skip whitespaces and comments
WS : [ \r\t\n]+ -> skip ;
COMMENT : '//' .*? [\n] -> skip;
MULTI_LINE_COMMENT : '/*' .*? '*/' -> skip ; // .*?
