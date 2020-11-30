package commands.queries.users;

import user.User;

import java.util.Hashtable;
import java.util.LinkedList;

public final class NumberOfRatingsUser {
    private static NumberOfRatingsUser instance;

    private NumberOfRatingsUser() {
    }

    /**
     * Method to get instance of singleton class NumberOfRatings
     * @return
     */
    public static NumberOfRatingsUser getInstance() {
        if (instance == null) {
            instance = new NumberOfRatingsUser();
        }
        return instance;
    }

    /**
     * Returns a list of the usres sorted by the number of ratings they have given
     * @param userArray
     * @param ascending
     * @return
     */
    public LinkedList<User> getUserList(final Hashtable<String, User> userArray,
                                         final int ascending) {
        LinkedList<User> newList = new LinkedList<>(); // the list that will contain the users
        newList.add(null);

        userArray.forEach((userName, user) -> {
            User userFromList;
            int i = 0;

            while (true) {
                userFromList = newList.get(i);
                // check for end of list
                if (userFromList == null) {
                    break;
                }
                // ascending == 1 => asc
                // ascending == -1 => desc
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
            // the users must have given at least one rating
            if (user.getNumberOfRatings() != 0) {
                newList.add(i, user);
            }
        });
        newList.remove(null);
        return newList;
    }
}
