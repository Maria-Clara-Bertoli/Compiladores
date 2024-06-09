package Analisadores;

public enum TokenType {
	
	IDENTIFIER, // letras
    NUMBER,     // números
    PLUS,       // +
    MINUS,      // -
    MULTIPLY,   // *
    DIVIDE,     // /
    LPAREN,     // (
    RPAREN,     // )
    TYPES,    // tipos
    DELIMITER,  // símbolo que marca o início e o final do código
    GREATER,    // >
    LESS,       // <
    GREATEREQUAL,  // >=
    GREATERLESS,  // <=
    EQUAL,        // ==
    IF,        // se
    ELSEIF,  // se_nao
    ELSE,     // senao
    WHILE,    // enquanto
    FOR,      // para
    AND,      // e
    OR,       // ou
    ASSIGMENT,  // atribuição
    INESPECTED,   // inesperado
    EOF       // final do arquivo/entrada

}
