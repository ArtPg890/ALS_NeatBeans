package analizadores;
import static analizadores.ListaDeTokens.*;
%%
%class Lexer
%type ListaDeTokens
L=[a-zA-Z_]
D=[0-9]
P=(\.)
espacio=[ \t\r]
%{
    public String lexeme;
%}
%%

/* Espacios en blanco */
{espacio}+ {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Salto de linea */
( "\n" ) {return Linea;}


/* Datos primitivos */
( int | char | float | boolean ) {lexeme=yytext(); return T_dato;}

/* Tipo de dato String */
( String ) {lexeme=yytext(); return Cadena;}

/* Palabra reservada Procedure */
( procedure ) {lexeme=yytext(); return Procedure;}

/* Palabra reservada EndProcedure */
( endProcedure ) {lexeme=yytext(); return EndProcedure;}

/* Palabra reservada Function */
( function ) {lexeme=yytext(); return Function;}

/* Palabra reservada EndFunction */
( endFunction ) {lexeme=yytext(); return EndFunction;}

/* Palabra reservada return */
( return ) {lexeme=yytext(); return Return;}

/* Palabra reservada If */
( if ) {lexeme=yytext(); return If;}

/* Palabra reservada EndIf */
( endIf ) {lexeme=yytext(); return EndIf;}

/* Palabra reservada Else */
( else ) {lexeme=yytext(); return Else;}

/* Palabra reservada EndElse */
//( endElse ) {lexeme=yytext(); return EndElse;}

/* Palabra reservada Do */
( do ) {lexeme=yytext(); return Do;}

/* Palabra reservada While */
( while ) {lexeme=yytext(); return While;}

/* Palabra resevada scanner */
( readln ) {lexeme=yytext(); return ReadLN;}

/* Palabra resevada scanner */
( println ) {lexeme=yytext(); return Println;}

/* Palabra resevada scanner */
( print ) {lexeme=yytext(); return Print;}

/* Operador Igual */
( "=" ) {lexeme=yytext(); return Igual;}

/* Operadoradores aritméticos */
( "+" | "-" | "*" | "/" ) {lexeme=yytext(); return Op_arimetico;}

/* Operadores logicos */
( "&&" | "||" | "!" ) {lexeme=yytext(); return Op_logico;}

/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" ) {lexeme = yytext(); return Op_relacional;}

/* Operadores Asignacion */
( "+=" | "-="  | "*=" | "/=" ) {lexeme = yytext(); return Op_Asignacion;}

/* Operadores Incremento y decremento */
( "++" | "--" ) {lexeme = yytext(); return Op_incremento;}

/*Operadores Booleanos*/
(true | false)      {lexeme = yytext(); return Op_booleano;}

/* Parentesis de apertura */
( "(" ) {lexeme=yytext(); return Parentesis_a;}

/* Parentesis de cierre */
( ")" ) {lexeme=yytext(); return Parentesis_c;}

/* Marcador de inicio de algoritmo */
( "program" ) {lexeme=yytext(); return Program;}

/* Marcador de final de algoritmo */
( "endProgram" ) {lexeme=yytext(); return EndProgram;}

/* Coma */
( "," ) {lexeme=yytext(); return Coma;}

/* Punto y coma */
( ";" ) {lexeme=yytext(); return P_coma;}

/* Dos puntos */
( ":" ) {lexeme=yytext(); return D_puntos;}

/* Identificador */
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}

/* Error de entero */
( 0{D}+ ) {lexeme=yytext(); return ERROR_Entero;}

/* Numero Entero */
( 0 | [1-9]{D}* ) {lexeme=yytext(); return Numero;}

/* Error de flotante */
( (0){D}*"."{D}*(0) | [1-9]{D}*"."{D}*(0) | (0){D}*"."{D}*[1-9] ) {lexeme=yytext(); return ERROR_Flotante;}

/* Número flotante */
( 0 | (0{P}{D}*[1-9]) | ([1-9]{D}*{P}{D}*[1-9]) ) {lexeme=yytext(); return Flotante;}

/* Cadena */
( \".*\" ) {lexeme=yytext(); return Litcad;}

/* Caracter */
( \'.\' ) {lexeme=yytext(); return Litcar;}

/* Error de analisis */
 . {return ERROR_Lexico;}
