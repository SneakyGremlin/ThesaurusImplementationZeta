package tests;

import classes.entries.LexicalConstruct;
import classes.entries.MultipleWord;
import classes.entries.SingleWord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestLexicalConstruct {

    private SingleWord onus;
    private SingleWord incumbent;
    private SingleWord mandatory;


    @BeforeEach
    public void initialise() {
        onus = new SingleWord("onus", "onus");
        incumbent = new SingleWord("incumbent", "incumbent", onus);
        mandatory = new SingleWord("mandatory", "mandatory", onus);
    }

    @Test
    public void testFormatter() {
        assertEquals("abc", LexicalConstruct.formatter("    abc     "));
        assertEquals("AbCdCCCe".toLowerCase(), LexicalConstruct.formatter("AbCdCCCe"));
        assertEquals("a g c.", LexicalConstruct.formatter("a     g  c.   "));
    }

    @Test
    public void testConstructor() {
        SingleWord something = new SingleWord("something", "cuz i ran out of words...");
        assertNotNull(something.getDirectRelated());

        MultipleWord morior_invictus = new MultipleWord("    MoRiOR      InvicTus    ", " DeatH  BefoRe   Defeat     ",
                onus);
        // testing formatting
        assertEquals("morior invictus", morior_invictus.getName());
        assertEquals("death before defeat", morior_invictus.getDefinition());

        // testing connections
        ArrayList<LexicalConstruct> related = mandatory.getDirectRelated();
        assertTrue(morior_invictus.getDirectRelated() == onus.getDirectRelated()); // they are the SAME object

        SingleWord exigent = new SingleWord("exigent", "somewhat related anyway", incumbent);
        related.add(exigent);
        assertTrue(morior_invictus.getDirectRelated().contains(exigent));
    }

}
