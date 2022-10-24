package analizadores;

/**
 *
 * Clase donde se enumeran los Tokens que retornará la clase "Lexer.java"
 *
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 * @version 1.0
 */
public enum ListaDeTokens {
    Linea,
    T_dato,
    Cadena,
    If,
    EndIf,
    Else,
    Procedure,
    EndProcedure,
    Function,
    EndFunction,
    Return,
    Do,
    While,
    ReadLN,
    Print,
    Println,
    Igual,
    Op_arimetico,
    Op_logico,
    Op_relacional,
    Op_Asignacion,
    Op_incremento,
    Op_booleano,
    Parentesis_a,
    Parentesis_c,
    Program,
    EndProgram,
    Coma,
    P_coma,
    D_puntos,
    Identificador,
    Numero,
    Flotante,
    Litcad,
    Litcar,
    ERROR_Entero,
    ERROR_Flotante,
    ERROR_Lexico
}
