package Analisadores;

import java.util.List;

public class Parser {
	private List<Token> tokens;
	private int currentTokenIndex;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		this.currentTokenIndex = 0;
	}

	private Token getCurrentToken() {
		return tokens.get(currentTokenIndex);
	}

	private Token getNextToken() {
		return tokens.get(++currentTokenIndex);
	}
	
	private Token getNextTokenSpecial() {
		int nextTokenIndex = currentTokenIndex;
		return tokens.get(++nextTokenIndex);
	}
	
	SymbolTable symbolTable = new SymbolTable();

	public void expressionVariable() throws ParseException {
		int state = 0;
		String type = "";
		String variable = "";
		while (getCurrentToken().getType() != TokenType.EOF && getCurrentToken().getType() != TokenType.DELIMITER) {
			Token currentToken = getCurrentToken();
			if (currentToken.getType() == TokenType.TYPES) {
				type = currentToken.getValue();
				state = 1;
				getNextToken();
			} else if (state == 1 && currentToken.getType() == TokenType.IDENTIFIER) {
				variable = currentToken.getValue();
				state = 2;
				getNextToken();
			} else if (state == 2 && currentToken.getType() == TokenType.ASSIGMENT) {
				state = 3;
				getNextToken();
			} else if (state == 3) {
				expressionFunction();
			} else {
				throw new ParseException(
						"Token inesperado ou construção incompreendida (expressionVariable): " + currentToken);
			}
		}
		symbolTable.addSymbolFalse(variable, type);
	}

	public void expressionFunction() throws ParseException {
		int state = 0;
		while (getCurrentToken().getType() != TokenType.EOF && getCurrentToken().getType() != TokenType.DELIMITER) {
			Token currentToken = getCurrentToken();
			if (state == 0 && (currentToken.getType() == TokenType.NUMBER || currentToken.getType() == TokenType.IDENTIFIER)) {
				state = 1;
				getNextToken();
			} else if(state == 1 && (currentToken.getType() == TokenType.MINUS || currentToken.getType() == TokenType.PLUS || currentToken.getType() == TokenType.DIVIDE || currentToken.getType() == TokenType.MULTIPLY)){
				state = 2;
				if(state != 1 && getNextTokenSpecial().getType() == TokenType.EOF){
					throw new ParseException(
							"Expressão incoerente. Expressão deve terminar em um 'IDENTIFIER' ou 'NUMBER' (expressionFunction): " + currentToken);
				} else {
					getNextToken();
				}
			} else if(state == 2 && (currentToken.getType() == TokenType.NUMBER || currentToken.getType() == TokenType.IDENTIFIER)) {
				state = 1;
				getNextToken();
			} else {
				throw new ParseException(
						"Token inesperado ou construção incompreendida (expressionFunction): " + currentToken);
			}
		}
	}

	public void expressionIf() throws ParseException {
		int state = 0;
		while (getCurrentToken().getType() != TokenType.EOF) {
			Token currentToken = getCurrentToken();
			if (currentToken.getType() == TokenType.IF) {
				state = 1;
				getNextToken();
			} else if (state == 1
					&& (currentToken.getType() == TokenType.NUMBER || currentToken.getType() == TokenType.IDENTIFIER)) {
				state = 2;
				getNextToken();
			} else if (state == 2 && (currentToken.getType() == TokenType.AND || currentToken.getType() == TokenType.OR
					|| currentToken.getType() == TokenType.GREATER || currentToken.getType() == TokenType.LESS
					|| currentToken.getType() == TokenType.GREATERLESS
					|| currentToken.getType() == TokenType.GREATEREQUAL || currentToken.getType() == TokenType.EQUAL)) {
				state = 1;
				getNextToken();
			} else if ((state == 2 || state == 1) && currentToken.getType() == TokenType.DELIMITER) {
				state = 3;
				getNextToken();
			} else if (state == 3) {
				expressionVariable();
				state = 4;
			} else if (state == 4 && currentToken.getType() == TokenType.DELIMITER) {
				getNextToken();
			} else {
				throw new ParseException(
						"Token inesperado ou construção incompreendida (expressionIf): " + currentToken);
			}
		}
	}

	public void expressionElseIf() throws ParseException {
		int state = 0;
		while (getCurrentToken().getType() != TokenType.EOF) {
			Token currentToken = getCurrentToken();
			if (currentToken.getType() == TokenType.ELSEIF) {
				state = 1;
				getNextToken();
			} else if (state == 1
					&& (currentToken.getType() == TokenType.NUMBER || currentToken.getType() == TokenType.IDENTIFIER)) {
				state = 2;
				getNextToken();
			} else if (state == 2 && (currentToken.getType() == TokenType.AND || currentToken.getType() == TokenType.OR
					|| currentToken.getType() == TokenType.GREATER || currentToken.getType() == TokenType.LESS
					|| currentToken.getType() == TokenType.GREATERLESS
					|| currentToken.getType() == TokenType.GREATEREQUAL || currentToken.getType() == TokenType.EQUAL)) {
				state = 1;
				getNextToken();
			} else if ((state == 2 || state == 1) && currentToken.getType() == TokenType.DELIMITER) {
				state = 3;
				getNextToken();
			} else if (state == 3) {
				expressionVariable();
				state = 4;
			} else if (state == 4 && currentToken.getType() == TokenType.DELIMITER) {
				getNextToken();
			} else {
				throw new ParseException(
						"Token inesperado ou construção incompreendida (expressionIf): " + currentToken);
			}
		}
	}

	public void expressionElse() throws ParseException {
		int state = 0;
		while (getCurrentToken().getType() != TokenType.EOF) {
			Token currentToken = getCurrentToken();
			if (currentToken.getType() == TokenType.ELSE) {
				state = 1;
				getNextToken();
			} else if (state == 1 && currentToken.getType() == TokenType.DELIMITER) {
				state = 2;
				getNextToken();
			} else if (state == 2) {
				expressionVariable();
				state = 3;
			} else if (state == 3 && currentToken.getType() == TokenType.DELIMITER) {
				getNextToken();
			} else {
				throw new ParseException(
						"Token inesperado ou construção incompreendida (expressionElseIf): " + currentToken);
			}
		}
	}

	public void expressionWhile() throws ParseException {
		int state = 0;
		while (getCurrentToken().getType() != TokenType.EOF) {
			Token currentToken = getCurrentToken();
			if (currentToken.getType() == TokenType.WHILE) {
				state = 1;
				getNextToken();
			} else if (state == 1
					&& (currentToken.getType() == TokenType.NUMBER || currentToken.getType() == TokenType.IDENTIFIER)) {
				state = 2;
				getNextToken();
			} else if (state == 2 && (currentToken.getType() == TokenType.AND || currentToken.getType() == TokenType.OR
					|| currentToken.getType() == TokenType.GREATER || currentToken.getType() == TokenType.LESS
					|| currentToken.getType() == TokenType.GREATERLESS
					|| currentToken.getType() == TokenType.GREATEREQUAL || currentToken.getType() == TokenType.EQUAL)) {
				state = 1;
				getNextToken();
			} else if ((state == 2 || state == 1) && currentToken.getType() == TokenType.DELIMITER) {
				state = 3;
				getNextToken();
			} else if (state == 3) {
				expressionVariable();
				state = 4;
			} else if (state == 4 && currentToken.getType() == TokenType.DELIMITER) {
				getNextToken();
			} else {
				throw new ParseException(
						"Token inesperado ou construção incompreendida (expressionWhile): " + currentToken);
			}
		}
	}

	public void expressionFor() throws ParseException {
		int state = 0;
		while (getCurrentToken().getType() != TokenType.EOF) {
			Token currentToken = getCurrentToken();
			if (currentToken.getType() == TokenType.FOR) {
				state = 1;
				getNextToken();
			} else if (state == 1 && currentToken.getType() == TokenType.TYPES
					&& currentToken.getValue().contentEquals("int")) {
				state = 2;
				getNextToken();
			} else if (state == 2 && currentToken.getType() == TokenType.IDENTIFIER) {
				state = 3;
				getNextToken();
			} else if (state == 3 && currentToken.getType() == TokenType.ASSIGMENT) {
				state = 4;
				getNextToken();
			} else if (state == 4 && currentToken.getType() == TokenType.NUMBER) {
				state = 5;
				getNextToken();
			} else if (state == 5 && currentToken.getType() == TokenType.IDENTIFIER) {
				state = 6;
				getNextToken();
			} else if (state == 6
					&& (currentToken.getType() == TokenType.LESS || currentToken.getType() == TokenType.GREATERLESS)) {
				state = 7;
				getNextToken();
			} else if (state == 7 && currentToken.getType() == TokenType.NUMBER) {
				state = 8;
				getNextToken();
			} else if (state == 8 && currentToken.getType() == TokenType.IDENTIFIER) {
				state = 9;
				getNextToken();
			} else if (state == 9 && currentToken.getType() == TokenType.ASSIGMENT) {
				state = 10;
				getNextToken();
			} else if (state == 10 && currentToken.getType() == TokenType.IDENTIFIER) {
				state = 11;
				getNextToken();
			} else if (state == 11 && currentToken.getType() == TokenType.PLUS) {
				state = 12;
				getNextToken();
			} else if (state == 12 && currentToken.getType() == TokenType.NUMBER) {
				state = 13;
				getNextToken();
			} else if (state == 13 && currentToken.getType() == TokenType.DELIMITER) {
				state = 14;
				getNextToken();
			} else if (state == 14) {
				expressionVariable();
				state = 15;
			} else if (state == 15 && currentToken.getType() == TokenType.DELIMITER) {
				getNextToken();
			} else {
				throw new ParseException(
						"Token inesperado ou construção incompreendida (expressionFor): " + currentToken);
			}
		}
	}
	
	public void showSymbolTable() {
		System.out.println(symbolTable);
	}
}
