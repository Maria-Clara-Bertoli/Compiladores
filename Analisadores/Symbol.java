package Analisadores;

class Symbol {
    private String name;
    private String typeFalse;
    private SymbolType type;
    private Object value;

    public Symbol(String name, String typeFalse) {
        this.name = name;
        this.typeFalse = typeFalse;
    }
    
    public Symbol(String name, SymbolType type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public SymbolType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.format("Symbol{name='%s', type=%s}", name, typeFalse);
    }

//    @Override
//    public String toString() {
//        return String.format("Symbol{name='%s', type=%s, value=%s}", name, type, value);
//    }
}
