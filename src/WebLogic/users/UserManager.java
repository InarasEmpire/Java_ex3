package  WebLogic.users;

import javafx.util.Pair;

import java.util.*;

/*
Adding and retrieving users is synchronized and for that manner - these actions are thread safe
Note that asking if a user exists (isUserExists) does not participate in the synchronization and it is the responsibility
of the user of this class to handle the synchronization of isUserExists with other methods here on it's own
 */
public class UserManager {
    private final List<Pair<String, Boolean>> usersList ;

    public UserManager() {
        usersList = new ArrayList<Pair<String, Boolean>>();
    }

    public synchronized void addUser(String username, Boolean type) {
        usersList.add(new Pair(username, type));
    }

    public synchronized void removeUser(String username) {
        for (Iterator<Pair<String, Boolean>> iter = usersList.listIterator(); iter.hasNext(); ) {
            Pair<String, Boolean> a = iter.next();
            if (a.getKey() == username) {
                iter.remove();
                return;
            }
        }
    }

    public synchronized List<Pair<String, Boolean>> getUsers()
    {
        return (List<Pair<String, Boolean>>) Collections.unmodifiableList(usersList);
    }

    public boolean isUserExists(String username) {
        for (Iterator<Pair<String, Boolean>> iter = usersList.listIterator(); iter.hasNext(); ) {
            Pair<String, Boolean> a = iter.next();
            if (a.getKey() == username) {
                return true;
            }
        }
        return false;
    }

    public boolean getUserType(String username)
    {
        for (Iterator<Pair<String, Boolean>> iter = usersList.listIterator(); iter.hasNext(); ) {
            Pair<String, Boolean> a = iter.next();
            if (a.getKey() == username) {
                return a.getValue();
            }
        }

        return false;
    }
}
