grammar SmallLangV2;

@header {
package antlrSrc;
}

literal : BooleanLiteral | IntegerLiteral | FloatLiteral | CharLiteral;
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
Type : 'float' | 'int' | 'bool';
Auto : 'auto';
BooleanLiteral : 'true' | 'false';
IntegerLiteral : DIGIT+;
FloatLiteral : DIGIT+ '.' DIGIT+;
CharLiteral : '\'' LETTER '\'';
Identifier : ('_' | LETTER) ('_' | LETTER | DIGIT)*;
MultiplicativeOp : '*' | '/' | 'and';
AdditiveOp : '+' | '-' | 'or';
RelationalOp : '<' | '>' | '==' | '<>' | '<=' | '>=';
actualParams :  expression (COMMA expression)*;
functionCall : Identifier BRACKET_OPEN actualParams? BRACKET_CLOSE;
subExpression : BRACKET_OPEN expression BRACKET_CLOSE;
unary : (MINUS | NOT) expression;
factor : literal | Identifier | functionCall | subExpression | unary;
term : factor (MultiplicativeOp factor)*;
simpleExpression : term (AdditiveOp term)*;
expression : simpleExpression (RelationalOp simpleExpression)*;
assignment : Identifier EQUAL_SIGN expression;
arrayIndex : SQUARE_OPEN expression SQUARE_CLOSE;
variableDecl : LET Identifier COLON (Type | Auto) EQUAL_SIGN expression;
arrayDecl : LET arrayIdentifier COLON TYPE EQUAL_SIGN arrayValue;
arrayIdentifier : Identifier arrayIndex;
arrayValue : CURLY_OPEN expression (COMMA expression)* CURLY_CLOSE;
decleration : variableDecl | arrayDecl;
printStatement : PRINT expression;
rtrnStatement : RETURN expression;
ifStatement : IF BRACKET_OPEN expression BRACKET_CLOSE block (ELSE block)?;
forStatement : FOR BRACKET_OPEN variableDecl? SEMI_COLON expression SEMI_COLON assignment? BRACKET_CLOSE block;
whileStatement : WHILE BRACKET_OPEN expression BRACKET_CLOSE block;
formalParam : (Identifier | arrayIdentifier) COLON Type;
formalParams : formalParam (COMMA formalParam)*;
functionDecl : FF Identifier BRACKET_OPEN formalParams? BRACKET_CLOSE COLON (Type | Auto) block;
statement : decleration SEMI_COLON
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
WS : [ \r\t\n]+ -> skip ;
COMMENT : '//' .*? [\n] -> skip;
MULTI_LINE_COMMENT : '/*' .*? '*/' -> skip ; // .*?
