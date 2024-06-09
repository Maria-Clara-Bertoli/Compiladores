package Analisadores;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

	private static final String TOKEN_PATTERN = "(?<NUMBER>\\d+)" + "|(?<IDENTIFIER>(?!se\\b|casonao\\b|entao\\b|ma\\b|me\\b|mai\\b|mei\\b|i\\b|para\\b|enquanto\\b)[a-zA-Z_][a-zA-Z_0-9]*)" + "|(?<PLUS>\\+)" + "|(?<MINUS>-)" + "|(?<MULTIPLY>\\*)" + "|(?<DIVIDE>\\/)" + "|(?<LPAREN>\\()" + "|(?<RPAREN>\\))"  + "|(?<DELIMITER>\\|)" + "|(?<GREATER>ma)" + "|(?<LESS>me)" + "|(?<GREATEREQUAL>mai)" + "|(?<GREATERLESS>mei)" + "|(?<EQUAL>i)" + "|(?<IF>se)" + "|(?<ELSEIF>casonao)" + "|(?<ELSE>entao)" + "|(?<WHILE>enquanto)" + "|(?<FOR>para)" + "|(?<AND>e)" + "|(?<OR>ou)" + "|(?<ASSIGMENT>\\=)" + "|(?<INESPECTED>\\;)"; 
	
	private static final Pattern PATTERN = Pattern.compile(TOKEN_PATTERN);
	private static final String[] TYPES = {"int", "flu", "tex", "log", "lis"};

	private String input;
	private List<Token> tokens;

	public Lexer(String input) {
		this.input = input;
		this.tokens = new ArrayList<>();
	}

	public void tokenize() {
		Matcher matcher = PATTERN.matcher(input);
		while (matcher.find()) {
			if (matcher.group("NUMBER") != null) {
				tokens.add(new Token(TokenType.NUMBER, matcher.group("NUMBER")));
				continue;
			}
			else if (matcher.group("IDENTIFIER") != null) {
				String value = matcher.group("IDENTIFIER");
				TokenType type = isKeyword(value) ? TokenType.TYPES : TokenType.IDENTIFIER;
				tokens.add(new Token(type, value));
				continue;
			}
			else if (matcher.group("PLUS") != null) {
				tokens.add(new Token(TokenType.PLUS, matcher.group("PLUS")));
				continue;
			}
			else if (matcher.group("MINUS") != null) {
				tokens.add(new Token(TokenType.MINUS, matcher.group("MINUS")));
				continue;
			}
			else if (matcher.group("MULTIPLY") != null) {
				tokens.add(new Token(TokenType.MULTIPLY, matcher.group("MULTIPLY")));
				continue;
			}
			else if (matcher.group("DIVIDE") != null) {
				tokens.add(new Token(TokenType.DIVIDE, matcher.group("DIVIDE")));
				continue;
			}
			else if (matcher.group("LPAREN") != null) {
				tokens.add(new Token(TokenType.LPAREN, matcher.group("LPAREN")));
				continue;
			}
			else if (matcher.group("RPAREN") != null) {
				tokens.add(new Token(TokenType.RPAREN, matcher.group("RPAREN")));
				continue;
			}
			else if (matcher.group("DELIMITER") != null) {
				tokens.add(new Token(TokenType.DELIMITER, matcher.group("DELIMITER")));
				continue;
			}
			else if (matcher.group("GREATER") != null) {
				tokens.add(new Token(TokenType.GREATER, matcher.group("GREATER")));
				continue;
			}
			else if (matcher.group("LESS") != null) {
				tokens.add(new Token(TokenType.LESS, matcher.group("LESS")));
				continue;
			}
			else if (matcher.group("GREATEREQUAL") != null) {
				tokens.add(new Token(TokenType.GREATEREQUAL, matcher.group("GREATEREQUAL")));
				continue;
			}
			else if (matcher.group("GREATERLESS") != null) {
				tokens.add(new Token(TokenType.GREATERLESS, matcher.group("GREATERLESS")));
				continue;
			}
			else if (matcher.group("EQUAL") != null) {
				tokens.add(new Token(TokenType.EQUAL, matcher.group("EQUAL")));
				continue;
			}
			else if (matcher.group("IF") != null) {
				tokens.add(new Token(TokenType.IF, matcher.group("IF")));
				continue;
			}
			else if (matcher.group("ELSEIF") != null) {
				tokens.add(new Token(TokenType.ELSEIF, matcher.group("ELSEIF")));
				continue;
			}
			else if (matcher.group("ELSE") != null) {
				tokens.add(new Token(TokenType.ELSE, matcher.group("ELSE")));
				continue;
			}
			else if (matcher.group("WHILE") != null) {
				tokens.add(new Token(TokenType.WHILE, matcher.group("WHILE")));
				continue;
			}
			else if (matcher.group("FOR") != null) {
				tokens.add(new Token(TokenType.FOR, matcher.group("FOR")));
				continue;
			}
			else if (matcher.group("AND") != null) {
				tokens.add(new Token(TokenType.AND, matcher.group("AND")));
				continue;
			}
			else if (matcher.group("OR") != null) {
				tokens.add(new Token(TokenType.OR, matcher.group("OR")));
				continue;
			}
			else if (matcher.group("ASSIGMENT") != null) {
				tokens.add(new Token(TokenType.ASSIGMENT, matcher.group("ASSIGMENT")));
				continue;
			}
			else {
				tokens.add(new Token(TokenType.INESPECTED, matcher.group("INESPECTED")));
			}
		}
		tokens.add(new Token(TokenType.EOF, ""));
	}

	private boolean isKeyword(String value) {
		for (String keyword : TYPES) {
			if (keyword.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public List<Token> getTokens() {
		return tokens;
	}

}
