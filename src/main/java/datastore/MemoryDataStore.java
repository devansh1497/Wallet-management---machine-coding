package datastore;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryDataStore {

    public Map<String, User> nameToUser;

    public MemoryDataStore() {
        this.nameToUser = new HashMap<>();
    }
}
