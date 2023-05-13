/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

grammar Task8;

/**
 * This rule is to check your grammar using "ANTLR Preview"
 */
test: (IF | ELSE | COMP | ID | NUM | LIT | LP | RP | WS
)+  EOF; //Replace the non-fragment lexer rules here

// Write all the necessary lexer rules and fragment lexer rules here
IF: [iI][fF];
ELSE: [eE][lL][sS][eE];
COMP: '>' | '<' | '>=' | '<=' | '==' | '!=';
ID: [a-zA-Z_][a-zA-Z0-9_]*;

NUM: DIGIT+ DECIMAL? EXPONENT?;
fragment DIGIT: [0-9];
fragment DECIMAL: '.' DIGIT+;
fragment EXPONENT: [eE] [+-]? DIGIT+ ;

LIT : DQ ( BS BS | BS DQ | ~[\r\n\\"] )* DQ;

fragment DQ: '"';
fragment BS: '\\';

LP: '(';
RP: ')';
WS: [ \t\r\n]+ -> skip;
