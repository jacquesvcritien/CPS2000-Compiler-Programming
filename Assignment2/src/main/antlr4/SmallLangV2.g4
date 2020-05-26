grammar SmallLangV2;


//used to add the package for the generated files
@header {
package antlrSrc;
}

//literal
literal : BooleanLiteral | IntegerLiteral | FloatLiteral | CharLiteral;

//operators
multiplicativeOp : TIMES | DIVIDE | AND;
additiveOp : PLUS | MINUS | OR;
relationalOp : LT | GT | EQUAL | NOT_EQUAL | LTE | GTE;

//EBNF expression
actualParams :  expression (COMMA expression)*;
functionCall : Identifier BRACKET_OPEN actualParams? BRACKET_CLOSE;
subExpression : BRACKET_OPEN expression BRACKET_CLOSE;
unary : (MINUS | NOT) expression;
factor : literal | abstractIdentifier | functionCall | subExpression | unary; //CHANGED
term : factor (multiplicativeOp factor)*;
simpleExpression : term (additiveOp term)*;
expression : simpleExpression (relationalOp simpleExpression)*;
assignment : abstractIdentifier EQUAL_SIGN expression; //CHANGED
arrayIndex : SQUARE_OPEN expression SQUARE_CLOSE; //NEW
arrayIdentifier : Identifier arrayIndex; //NEW
abstractIdentifier: Identifier | arrayIdentifier; //NEW

//EBNF statements
variableDecl : Identifier COLON (Type | Auto) EQUAL_SIGN expression; // CHANGED
arrayDecl : Identifier SQUARE_OPEN expression? SQUARE_CLOSE COLON Type EQUAL_SIGN arrayValue; //NEW
arrayValue : CURLY_OPEN (expression (COMMA expression)*)? CURLY_CLOSE; //NEW
declaration : 'let' (variableDecl | arrayDecl); //NEW
printStatement : PRINT expression;
rtrnStatement : RETURN expression;
ifStatement : IF BRACKET_OPEN expression BRACKET_CLOSE block (ELSE block)?;
forStatement : FOR BRACKET_OPEN variableDecl? SEMI_COLON expression SEMI_COLON assignment? BRACKET_CLOSE block;
whileStatement : WHILE BRACKET_OPEN expression BRACKET_CLOSE block;
formalParam : Identifier (SQUARE_OPEN SQUARE_CLOSE)? COLON Type; // CHANGED
formalParams : formalParam (COMMA formalParam)*;
functionDecl : FF Identifier BRACKET_OPEN formalParams? BRACKET_CLOSE COLON (Type | Auto) block;
statement : declaration SEMI_COLON // CHANGED
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

//tokens for literals
BooleanLiteral : 'true' | 'false';
IntegerLiteral : DIGIT+;
FloatLiteral : DIGIT+ '.' DIGIT+;
CharLiteral : '\'' LETTER '\''; //NEW

//token for identifier
Identifier : ('_' | LETTER) ('_' | LETTER | DIGIT)*;

//used to skip whitespaces and comments
WS : [ \r\t\n]+ -> skip ;
COMMENT : '//' .*? [\n] -> skip;
MULTI_LINE_COMMENT : '/*' .*? '*/' -> skip ; // .*?
