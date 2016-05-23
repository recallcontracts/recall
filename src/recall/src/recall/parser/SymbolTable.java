/*
 * 
 */
package recall.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author wellington
 */
public final class SymbolTable {

    private int id_base;
    private List<Symbol> dictionary;

    private static SymbolTable instance;

    /**
     * singleton design pattern
     *
     * @return the singleton instance of SymbolTable
     */
    public static SymbolTable getInstance() {
        if (instance == null) {
            instance = new SymbolTable();
        }
        return instance;
    }

    protected SymbolTable() {
        clear();
    }

    public List<Symbol> getDictionary() {
        return dictionary;
    }

    public int addSymbol(String value, SymbolType type) {
        Stream<Symbol> filter = dictionary.stream().filter((Symbol t) -> (t.getType() == type && t.getValue().equals(value)));
        Optional<Symbol> f = filter.findFirst();
        if (!f.isPresent()) {
            Symbol s = new Symbol(id_base++, value, type);
            dictionary.add(s);
            return s.getId();
        }
        return f.get().getId();
    }

    public Symbol getSymbolById(int id) {
        Stream<Symbol> filter = dictionary.stream().filter(s -> s.getId() == id);
        Optional<Symbol> findFirst = filter.findFirst();
        if (findFirst.isPresent()) {
            return findFirst.get();
        } else {
            return null;
        }
    }

    public void clear() {
        id_base = 1;
        dictionary = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table of Symbols\n");
        dictionary.stream().forEach((s) -> {
            sb.append(String.format("(%d)  %10s  %s \n", s.getId(), s.getType(), s.getValue()));
        });
        return sb.toString();
    }
    
}
