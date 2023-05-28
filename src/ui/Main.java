package ui;

import classes.entries.LexicalConstruct;
import classes.entries.SingleWord;
import classes.thesaurus.EntryType;
import classes.thesaurus.Thesaurus;
import persistence.Loader;
import persistence.Saver;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Thesaurus thesaurus = new Thesaurus();
//        LexicalConstruct onus = new SingleWord("onus", "onus");
//        LexicalConstruct incumbent = new SingleWord("incumbent", "incumbent", onus);
//        LexicalConstruct mandatory = new SingleWord("mandatory", "mandatory", onus);
//        thesaurus.addEntry("onus", "onus", EntryType.SINGLE);
//        thesaurus.addEntry("incumbent", "incumbent", EntryType.SINGLE, "onus");
//        thesaurus.addEntry("mandatory", "mandatory", EntryType.SINGLE, "onus");
        Loader loader = new Loader(thesaurus);
        loader.load();
//
//        thesaurus.addEntry("obligatory", "obligatory", EntryType.SINGLE, "onus");
//        thesaurus.addEntry("dog", "dog", EntryType.SINGLE);
//        thesaurus.addEntry("man's best", "man's best", EntryType.MULTIPLE, "dog");
//
//        System.out.println(thesaurus.getEntry("man's best").getClass());
//
        Saver saver = new Saver(thesaurus);
        saver.save();
    }
}

//printf
//println

// class classes.entries.MultipleWord is returned by getClass