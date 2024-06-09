package Analisadores;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Symbol> symbols;

    public SymbolTable() {
        this.symbols = new HashMap<>();
    }

    public void addSymbolFalse(String name, String typeFalse) {
        Symbol symbol = new Symbol(name, typeFalse);
        symbols.put(name, symbol);
    }
    
    public void addSymbolReal(String name, SymbolType type, Object value) {
        Symbol symbol = new Symbol(name, type, value);
        symbols.put(name, symbol);
    }

    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    public boolean containsSymbol(String name) {
        return symbols.containsKey(name);
    }
    
  @Override
  public String toString() {
      StringBuilder builder = new StringBuilder();
      for (Symbol symbol : symbols.values()) {
          builder.append(symbol).append("\n");
      }
      return builder.toString();
  }

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Symbol Table:\n");
//        for (Symbol symbol : symbols.values()) {
//            builder.append(symbol).append("\n");
//        }
//        return builder.toString();
//    }
}
