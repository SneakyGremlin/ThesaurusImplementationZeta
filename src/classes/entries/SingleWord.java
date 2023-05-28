package classes.entries;

import classes.entries.LexicalConstruct;

// Single Word encompasses single words and compound words with a hyphen (check-in, go-getter etc.)
public class SingleWord extends LexicalConstruct {
    /**
     * @inheritDoc
     */

    public SingleWord(String name, String def, LexicalConstruct inheritFrom) {
        super(name, def, inheritFrom);
    }

    public SingleWord(String name, String def) {
        super(name, def);
    }
}
