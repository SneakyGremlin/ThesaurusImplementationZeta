package tests;

import classes.entries.MultipleWord;
import classes.entries.SingleWord;
import classes.thesaurus.EntryType;
import classes.thesaurus.Thesaurus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// I wrote these perfunctorily (they aren't THAT strenuous...); hence, the assertEquals and assertTrue fiasco...
public class TestThesaurus {

    private Thesaurus thesaurus;
//    private SingleWord onus;
//    private SingleWord incumbent;
//    private SingleWord mandatory;
//    private MultipleWord thou_must_dost_this;
//    private MultipleWord thou_absolutely_must_dost_this;

    @BeforeEach
    public void initialise() {
        thesaurus = new Thesaurus();
    }

    @Test
    public void testConstructor() {
        // literally nothing...
    }

    // ideally should be deconstructed into 4 test cases (4 MAIN cases)
    @Test
    public void testAddEntry() {
        // case 1: single, not inheritor
        // onus = new SingleWord("onus", "onus");
        assertTrue(thesaurus.addEntry("onus", "onus def", EntryType.SINGLE));
        assertTrue(thesaurus.contains("onus"));

        // case 2: single, inheritor
        // incumbent = new SingleWord("incumbent", "incumbent", onus);
        assertTrue(thesaurus.addEntry("incumbent", "incumbent", EntryType.SINGLE, "onus"));
        assertTrue(thesaurus.contains("incumbent"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(0).getName().equals("onus"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(1).getName().equals("incumbent"));

        // case 3: multiple, not inheritor
        // thou_must_dost_this = new MultipleWord("thou_must_dost_this", "thou_must_dost_this", onus);
        assertTrue(thesaurus.addEntry("thou_must_dost_this", "thou_must_dost_this", EntryType.MULTIPLE));
        assertTrue(thesaurus.contains("thou_must_dost_this"));

        // case 4: multiple, inheritor
        // thou_absolutely_must_dost_this = new MultipleWord("thou_absolutely_must_dost_this", "thou_absolutely_must_dost_this",
        // thou_must_dost_this);
        assertTrue(thesaurus.addEntry("thou_absolutely_must_dost_this", "thou_absolutely_must_dost_this",
                EntryType.MULTIPLE, "thou_must_dost_this"));
        assertTrue(thesaurus.contains("thou_absolutely_must_dost_this"));


        assertFalse(thesaurus.addEntry("onus", "onus def", EntryType.SINGLE));

    }

    @Test
    public void testDelete() {
        // onus = new SingleWord("onus", "onus");
        assertTrue(thesaurus.addEntry("onus", "onus def", EntryType.SINGLE));
        assertTrue(thesaurus.contains("onus"));

        // incumbent = new SingleWord("incumbent", "incumbent", onus);
        assertTrue(thesaurus.addEntry("incumbent", "incumbent", EntryType.SINGLE, "onus"));
        assertTrue(thesaurus.contains("incumbent"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(0).getName().equals("onus"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(1).getName().equals("incumbent"));

        // above contains redundancy but can just call it thorough testing...

        thesaurus.delete("onus");
        assertFalse(thesaurus.contains("onus"));
        assertFalse(thesaurus.getEntry("incumbent").getDirectRelated().get(0).getName().equals("onus"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(0).getName().equals("incumbent"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().size() == 1);

    }

    @Test
    public void testOverwrite() {
        assertTrue(thesaurus.addEntry("not-onus", "onus", EntryType.SINGLE));
        assertTrue(thesaurus.contains("not-onus"));

        assertTrue(thesaurus.addEntry("incumbent", "incumbent", EntryType.SINGLE, "not-onus"));
        assertTrue(thesaurus.contains("incumbent"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(0).getName().equals("not-onus"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(1).getName().equals("incumbent"));

        assertTrue(thesaurus.addEntry("onus", "onus def", EntryType.SINGLE, "incumbent"));
        assertTrue(thesaurus.contains("onus"));
        assertTrue(thesaurus.getEntry("onus").getDirectRelated().get(2).getName().equals("onus"));


        // check that the entry exists, the definition is updated and pre-update constructs no longer hold a reference to the overwritten construct
        // check no inheritor
        assertEquals("onus", thesaurus.getEntry("not-onus").getDefinition());
        thesaurus.overwrite("not-onus", "not-onus", EntryType.SINGLE, null);
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(0).getName().equals("incumbent"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().get(1).getName().equals("onus"));
        assertTrue(thesaurus.getEntry("incumbent").getDirectRelated().size() == 2);
        assertEquals("not-onus", thesaurus.getEntry("not-onus").getDefinition());
        assertEquals(1, thesaurus.getEntry("not-onus").getDirectRelated().size());
        assertTrue(thesaurus.getEntry("not-onus").getDirectRelated().get(0).getName().equals("not-onus"));

        // check that the entry exists, the definition is updated and pre-update constructs STILL hold a reference to the overwritten construct
        // TODO
    }
}
// TODO test for uniqueness of values
