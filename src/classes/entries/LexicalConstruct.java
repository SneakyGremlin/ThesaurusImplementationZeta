package classes.entries;

import java.util.ArrayList;

/**
 * primary contained class
 * does NOT implement composite pattern
 * is self-referential
 *
 * @author Ch Muhammad Daud Virk
 *
 */
public abstract class LexicalConstruct {

    /**
     * name: is the LexicalConstruct itself e.g. consider an object instantiated with name string "all is fair in love and
     *      war"; its name will be the string itself.
     * definition: is the definition associated with the LexicalConstruct (analogous to a literal dictionary's definition;
     *      is properly formatted (see method defFormatter)
     * directRelated:
     *      is an ArrayList Object of references to Lexical Constructs that it is directly synonymous to e.g. chide and
     *          reproach should both have each other in their respective related fields.
     *      A lexical construct does have itself in its related field
     *      All directly synonymous lexical constructs have a reference to the same object
     *      The relationship is intended to be reflexive (a has b iff b has a).
     *      related is automatically maintained: i.e. if one introduces a new LexicalConstruct which is a direct synonym
     *          of a LexicalConstruct that already exists then they will share the same... TODO:
     *
     * arrayOfRelations:
     *      is an array of arrays where each array maintains references to other Lexical Construct objects based on a
     *      criteria set by the user. ... TODO:
     *      several options are possible ... TODO
     *      may or may not be reflexive (e.g. word length criteria needn't be reflexive)
     *
     */

    protected String name;
    protected String definition;

    protected ArrayList<LexicalConstruct> directRelated;
    protected ArrayList<ArrayList<LexicalConstruct>> arrayOfRelations;

    /** Constructor 1; for when entries pre-exist.
     *
     * @param name
     * @param def
     * @param inheritFrom
     */
    public LexicalConstruct(String name, String def, LexicalConstruct inheritFrom) {
        this.name = formatter(name);
        this.definition = formatter(def);
        // >>> in java all new keyword objects are dynamic and thus do not exist on the stack
        inheritFrom.directRelated.add(this);
        this.directRelated = inheritFrom.directRelated;
    }

    /** Constructor 2; for when entries are not extant.
     *
     * @param name
     * @param def
     */
    public LexicalConstruct(String name, String def) {
        this.name = formatter(name);
        this.definition = formatter(def);
        this.directRelated = new ArrayList<>();
        this.directRelated.add(this);
    }


    /**  method for formatting the name and definition of the LexicalConstruct. Modifications are applicable to both subclasses.
     *      no trailing or leading spaces.
     *      single spacing between words in the same entry
     *      all are in small case
     *
     * @param orig
     * @return modified orig string
     */
    public static String formatter(String orig) {
        //>>> String methods do not modify the original string.
        String mod = orig.trim();
        mod = mod.toLowerCase();
        if (mod.contains(" ")) {
            // is a multiple word
            mod = mod.replaceAll("\s+", " ");
        } else {
            // is single
        }
        return mod;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    /**
     *
     * @return returns reference to relatedDirect object.
     */
    public ArrayList<LexicalConstruct> getDirectRelated() {
        return this.directRelated;
    }

    // should be extensible
    public void delete() {
        directRelated.remove(this);
        // other places wherefrom it should be removed.
    }
}

/*
TODO:: self and mutual
TODO:: public private protected...
TODO:: location and time of instantiaon
TODO:: arguments to super. Does a super class constructor even need to exist? when does a problem exist?
public LexicalConstruct() {

}
TODO:: when do you get a non static usage error
>>> static methods can be used in non-static contexts but not the other way around
TODO
//            String[] modStrArray = mod.split("");
//            List<String> modArray = Arrays.asList(modStrArray);
//            while (modArray.contains("")) modArray.remove("");
//            mod = String.join(" ", modArray);

 // >>> static methods cannot be abstract.
 // >>> Arrays.asList()
 // >>> String.join("", array)
 // >>> string.split("");
 */
