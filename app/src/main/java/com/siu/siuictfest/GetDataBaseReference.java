package com.siu.siuictfest;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nuhel on 10/24/2017.
 */

public class GetDataBaseReference {

    private static FirebaseDatabase firebaseDatabase;

    public static FirebaseDatabase GetDataBaseInstance() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
            return firebaseDatabase;
        }
        return firebaseDatabase;
    }
}
