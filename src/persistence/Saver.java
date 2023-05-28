package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import classes.entries.LexicalConstruct;
import classes.thesaurus.Thesaurus;
import org.json.JSONArray;
import org.json.JSONObject;
// TODO

//TODO
// classpath and path
// how to compile from shell
// !!! set up ssh


// TODO close

public class Saver {

    private FileWriter file;
    private Thesaurus thesaurus;

    public Saver(Thesaurus thesaurus) {
        try {
            file = new FileWriter("./src/persistence/storage_files/storage.json"); //TODO: introduce choice in the constructor
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO
        }
        this.thesaurus =  thesaurus;
    }

    public void save() {

        JSONArray arrayOfConstructs = new JSONArray();
        ArrayList<ArrayList<LexicalConstruct>> entries = thesaurus.getConnections();

        // HAS BEEN OPTIMISED
        for (ArrayList<LexicalConstruct> entry : entries) {
            JSONArray arrayOfRelated = new JSONArray();
            for (LexicalConstruct lc : entry) {
                JSONObject object = new JSONObject();
                object.put("name", lc.getName());
                // TODO extend for arrayOfRelations
                object.put("definition", lc.getDefinition());
                // object.put("directRelated", entry.getValue().getDirectRelated()); // this is maintained implicitly
                arrayOfRelated.put(object);
            }
            arrayOfConstructs.put(arrayOfRelated);
        }

        try {
            file.write(arrayOfConstructs.toString());
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO
        }
        try {
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO
        }
    }

}

// >>> HashMap does not have foreach

// File paths are written relative to the root with ./

// Having downloaded this file, drag it to your project's lib folder in IntelliJ.  Right-click it and select
// "Add as library..." and then click OK.

// Alternatively, download the jar file. go to project struct via ctrlaltshift+s and use add libraries.

// the ./bin has the compiler; only one of these is in the path variable at one time

// TODO
// when added to the external libraries is it auto added to the lib folder as well? or is it the opposite !!!


//        JSONArray arrayOfConstructs = new JSONArray();
//        Set<Map.Entry<String, LexicalConstruct>> entries = thesaurus.getEntries().entrySet();
//        // HashMap<String, LexicalConstruct> entries = thesaurus.getEntries();
//
//        // TODO: huge potential for optimisation // HAS BEEN OPTIMISED
//        for (Map.Entry<String, LexicalConstruct> entry : entries) {
//            JSONObject object = new JSONObject();
//            object.put("name", entry.getKey());
//            // TODO extend for arrayOfRelations
//            object.put("definition", entry.getValue().getDefinition());
//            //JSONObject subobject = new JSONObject();
//            // object.put("directRelated", entry.getValue().getDirectRelated()); // !!!
//            arrayOfConstructs.put(object);
//        }

//        entries.forEach((key, value) -> {
//            JSONObject object = new JSONObject();
//            object.put("name", key);
//            // TODO extend for arrayOfRelations
//            object.put("definition", value.getDefinition());
//            object.put("directRelated", value.getDirectRelated()); // !!!
//            arrayOfConstructs.
//        });