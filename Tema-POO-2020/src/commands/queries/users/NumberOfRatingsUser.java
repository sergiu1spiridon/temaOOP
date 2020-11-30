package commands.queries.users;

import user.User;

import java.util.Hashtable;
import java.util.LinkedList;

public final class NumberOfRatingsUser {
    private static NumberOfRatingsUser instance;

    private NumberOfRatingsUser() {
    }

    public static NumberOfRatingsUser getInstance() {
        if (instance == null) {
            instance = new NumberOfRatingsUser();
        }
        return instance;
    }

    public LinkedList<User> getUserList(final Hashtable<String, User> userArray,
                                         final int ascending) {
        LinkedList<User> newList = new LinkedList<>();
        newList.add(null);

        userArray.forEach((userName, user) -> {
            User userFromList;
            int i = 0;

            while (true) {
                userFromList = newList.get(i);

                if (userFromList == null) {
                    break;
                }
                 if ((userFromList.getNumberOfRatings() * ascending)
                         < (user.getNumberOfRatings() * ascending)) {
                     i++;
                 } else if (userFromList.getNumberOfRatings() == user.getNumberOfRatings()) {
                     if (ascending == 1) {
                         if (userFromList.getUserName().compareTo(user.getUserName()) < 0) {
                             i++;
                         } else {
                             break;
                         }
                     } else {
                         if (userFromList.getUserName().compareTo(user.getUserName()) > 0) {
                             i++;
                         } else {
                             break;
                         }
                     }
                 } else {
                     break;
                 }
            }
            if (user.getNumberOfRatings() != 0) {
                newList.add(i, user);
            }
        });
        newList.remove(null);
        return newList;
    }
}
