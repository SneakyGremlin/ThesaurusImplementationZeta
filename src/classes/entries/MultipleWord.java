package classes.entries;

import classes.entries.LexicalConstruct;

// Multiple Word encompasses idioms and compound words without a hyphen.
public class MultipleWord extends LexicalConstruct {
    /**
     * @inheritDoc
     */

    public MultipleWord(String name, String def, LexicalConstruct inheritFrom) {
        super(name, def, inheritFrom);
    }

    public MultipleWord(String name, String def) {
        super(name, def);
    }
}
