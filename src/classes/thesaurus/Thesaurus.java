package classes.thesaurus;

import classes.entries.LexicalConstruct;
import classes.entries.MultipleWord;
import classes.entries.SingleWord;
import classes.thesaurus.EntryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *  Main container for all Lexical Constructs
 */
public class Thesaurus {
    // uses hashmap
    // hashmaps are NOT injective (i.e. one to one: multiple keys can map to the same hash value)

    /**
     *
     */

    private Map<String, LexicalConstruct> entries = new HashMap<String, LexicalConstruct>(); // >>> Uniqueness is ensured via HashSet
    private HashSet<String> entriesAsStrings = new HashSet<>(); // >>> Ensuring uniqueness

    private ArrayList<ArrayList<LexicalConstruct>> connections = new ArrayList<>(); // TODO test

    /**
     * TODO
     * @param name
     * @param def
     * @param type
     * @return
     */

    public boolean addEntry(String name, String def, EntryType type) {
        String nameFormatted = LexicalConstruct.formatter(name);
        String defFormatted = LexicalConstruct.formatter(def);
        if (entriesAsStrings.contains(nameFormatted)) {
            return false;
        }
        LexicalConstruct entry;
        switch (type) {
            case SINGLE:
                addSingle(nameFormatted, defFormatted, null);
                return true;
                // break;
            case MULTIPLE:
                addMultiple(nameFormatted, defFormatted, null);
                return true;
                // break;
            default: throw new RuntimeException(); // TODO
        }
    }

    /**
     * REQUIRES: inheritFrom must be pre-present TODO
     * @param name
     * @param def
     * @param type
     * @param inheritFrom
     * @return
     */
    public boolean addEntry(String name, String def, EntryType type, String inheritFrom) {
        String nameFormatted = LexicalConstruct.formatter(name);
        String defFormatted = LexicalConstruct.formatter(def);
        String inheritFromFormatted = LexicalConstruct.formatter(inheritFrom);
        if (entriesAsStrings.contains(nameFormatted)) {
            return false;
        }
        LexicalConstruct entry;
        switch (type) {
            case SINGLE:
                addSingle(nameFormatted, defFormatted, inheritFromFormatted);
                return true;
            // break;
            case MULTIPLE:
                addMultiple(nameFormatted, defFormatted, inheritFromFormatted);
                return true;
            // break;
            default: throw new RuntimeException(); // TODO
        }
    }

    // >>> Both addSingle and addMultiple could be abstracted creating a single method with a parameter for the Word to create

    /**
     * REQUIRES: name and def are formatted
     *          formats inheritFrom
     * TODO
     * @param name
     * @param def
     * @param inheritFrom
     */
    private void addSingle(String name, String def, String inheritFrom) {
        SingleWord entry;
        String inheritFromFormatted = inheritFrom;
        if (inheritFrom != null) {
            inheritFromFormatted = LexicalConstruct.formatter(inheritFrom);
        }
        if (inheritFrom == null) {
            entry = new SingleWord(name, def);
            connections.add(entry.getDirectRelated());
        } else {
            entry = new SingleWord(name, def, entries.get(inheritFromFormatted));
        }
        entriesAsStrings.add(name);
        entries.put(name, entry);
    }
    // >>> alternatively could use keySet on HashMap Directly and not keep a separate container.

    // TODO might be a misnomer...
    /**
     * REQUIRES: name and def are formatted
     *          formats inheritFrom
     * TODO
     * @param name
     * @param def
     * @param inheritFrom
     */
    private void addMultiple(String name, String def, String inheritFrom) {
        MultipleWord entry;
        String inheritFromFormatted = inheritFrom;
        if (inheritFrom != null) {
            inheritFromFormatted = LexicalConstruct.formatter(inheritFrom);
        }
        if (inheritFrom == null) {
            entry = new MultipleWord(name, def);
            connections.add(entry.getDirectRelated());
        } else {
            entry = new MultipleWord(name, def, entries.get(inheritFromFormatted));
        }
        entriesAsStrings.add(name);
        entries.put(name, entry);
    }
    // >>> alternatively could use keySet on HashMap Directly and not keep a separate container.

    /**
     *  TODO
     * @param toDelete
     */
    public void delete(String toDelete) {
        String toDeleteFormatted = LexicalConstruct.formatter(toDelete);
        if (!entriesAsStrings.contains(toDeleteFormatted)) {
            return;
        } else {
            entriesAsStrings.remove(toDeleteFormatted);
            LexicalConstruct temp = entries.get(toDeleteFormatted);
            temp.delete();
            entries.remove(toDeleteFormatted);
        }
    }

    /**
     * TODO
     * @param toOverwrite
     * @param def
     * @param et
     * @param inheritFrom
     */
    public void overwrite(String toOverwrite, String def, EntryType et, String inheritFrom) {
        String toOverwriteFormatted = LexicalConstruct.formatter(toOverwrite);
        String defFormatted = LexicalConstruct.formatter(def);
        String inheritFromFormatted = inheritFrom;     // TODO: redundancy at present since addSingle and Multiple format inheritFrom
        if (inheritFrom != null) {
            inheritFromFormatted = LexicalConstruct.formatter(inheritFrom);
        }
        delete(toOverwrite);
        if (et == EntryType.SINGLE) {
            addSingle(toOverwriteFormatted, defFormatted, inheritFromFormatted);
        } else {
            addMultiple(toOverwriteFormatted, defFormatted, inheritFromFormatted);
        }
    }
    // TODO: make a separate method for without the inheritance

    /**
     * TODO
     * @param check
     * @return
     */

    // >>> Method(s) below are for testing
    public boolean contains(String check) {
        String checkF = LexicalConstruct.formatter(check);
        return  entries.containsKey(checkF) && entriesAsStrings.contains(checkF);
    }

    public LexicalConstruct getEntry(String toGet) {
        String toGetFormatted = LexicalConstruct.formatter(toGet);
        if (!entriesAsStrings.contains(toGetFormatted)) {
            throw new RuntimeException(); // TODO
        }
        return entries.get(toGetFormatted);
    }

    public HashMap<String, LexicalConstruct> getEntries() {
        return (HashMap<String, LexicalConstruct>) entries;
    }

    public ArrayList<ArrayList<LexicalConstruct>> getConnections() {
        return this.connections;
    }
}

/*
HashMap does not allow duplicate keys, but duplicate values can be added to it. The Hashmap does not maintain the order
of insertion of the objects. The HashMap methods are not thread-safe as well and are also not synchronized.
 */

// >>> will cast when actual type is different from return type and returning a value e.g. get entries.

// TODO: When an exception is thrown does the rest of the program not run at all? Finally statement?
// TODO: default qualifiers for class members?