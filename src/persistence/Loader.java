package persistence;

import classes.entries.LexicalConstruct;
import classes.entries.MultipleWord;
import classes.thesaurus.EntryType;
import classes.thesaurus.Thesaurus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


// TODO: introduce choice in the constructor
// TODO documentation
public class Loader {

    private Thesaurus thesaurus;
    private FileReader fileReader;

    public Loader(Thesaurus thesaurus) {
        this.thesaurus = thesaurus;
        try {
            fileReader = new FileReader("./src/persistence/storage_files/storage.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // TODO
        }
    }

    public void load() {
        String s = loadString();
        loadIntoThesaurus(s);
//        String final_string = "";
//        char c;
//        int i;
//        // TODO: isolate try catch
//        try {
//            i = fileReader.read();
//            c = (char) i;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        while (i != -1) {
//            final_string = final_string + c;
//            try {
//                i = fileReader.read();
//                c = (char) i;
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        // final_string = final_string + "]]";
//
//        JSONArray primary_container = new JSONArray(final_string);
//        for (Object object : primary_container) {
//            JSONArray secondary_container = (JSONArray) object;
//            // ArrayList<LexicalConstruct> related;
//
//            Boolean inheritance_active = false;
//            String inheritFrom = null;
//
//            for (Object o : secondary_container) {
//                JSONObject lcjson = (JSONObject) o;
//                String name = (String) lcjson.get("name"); // >>> cast to string...
//                String def = (String) lcjson.get("definition");
//                // LexicalConstruct lc; //TODO is actual type passed to thesaurus?
//                if (!inheritance_active) {
//                    if (name.contains(" ")) {
//                        // is multiple
//                        thesaurus.addEntry(name, def, EntryType.MULTIPLE);
//                    } else {
//                        thesaurus.addEntry(name, def, EntryType.SINGLE);
//                    }
//                    inheritance_active = true;
//                    inheritFrom = name;
//                } else {
//                    if (name.contains(" ")) {
//                        // is multiple
//                        thesaurus.addEntry(name, def, EntryType.MULTIPLE, inheritFrom);
//                    } else {
//                        thesaurus.addEntry(name, def, EntryType.SINGLE, inheritFrom);
//                    }
//                }
//            }
//
//        }
    }

    public String loadString() {
        String final_string = "";
        char c;
        int i;
        // TODO: isolate try catch
        try {
            i = fileReader.read();
            c = (char) i;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (i != -1) {
            final_string = final_string + c;
            try {
                i = fileReader.read();
                c = (char) i;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // final_string = final_string + "]]";
        return final_string;
    }

    public void loadIntoThesaurus(String basis) {
        JSONArray primary_container = new JSONArray(basis);
        for (Object object : primary_container) {
            JSONArray secondary_container = (JSONArray) object;
            // ArrayList<LexicalConstruct> related;

            Boolean inheritance_active = false;
            String inheritFrom = null;

            for (Object o : secondary_container) {
                JSONObject lcjson = (JSONObject) o;
                String name = (String) lcjson.get("name"); // >>> cast to string...
                String def = (String) lcjson.get("definition");
                // LexicalConstruct lc; //TODO is actual type passed to thesaurus?
                if (!inheritance_active) {
                    if (name.contains(" ")) {
                        // is multiple
                        thesaurus.addEntry(name, def, EntryType.MULTIPLE);
                    } else {
                        thesaurus.addEntry(name, def, EntryType.SINGLE);
                    }
                    inheritance_active = true;
                    inheritFrom = name;
                } else {
                    if (name.contains(" ")) {
                        // is multiple
                        thesaurus.addEntry(name, def, EntryType.MULTIPLE, inheritFrom);
                    } else {
                        thesaurus.addEntry(name, def, EntryType.SINGLE, inheritFrom);
                    }
                }
            }

        }

    }
}

// >>> JSONArray is iterable :)
// HOWEVER it contains objects as opposed to JSONObjects
// ... TODO

// using JSON... TODO
// >>> Java does not have a default constructor if other constructors are present
