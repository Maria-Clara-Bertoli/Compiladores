package Analisadores;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		String input = "para int u = 0 u me 9 u = u + 6 |int x = y * 3 + 7 / 6 + r * t|";
		Lexer lexer = new Lexer(input);
		lexer.tokenize();
		List<Token> tokens = lexer.getTokens();
		
		System.out.println("Análise léxica completada com sucesso.");
		System.out.println();
		
		for(Token token : tokens) {
			System.out.println(token);
		}
		System.out.println();

		Parser parser = new Parser(tokens);
		try {
			parser.expressionFor();
			System.out.println("Análise sintática completada com sucesso.");
		} 
		catch (ParseException e) {
			System.err.println("Análise sintática falhou: " + e.getMessage());
		}
		System.out.println();
		System.out.println("Tabela de Simbolos");
		System.out.println();
		parser.showSymbolTable();
	}
}
