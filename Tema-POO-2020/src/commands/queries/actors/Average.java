package commands.queries.actors;

import actor.Actor;

import java.util.ArrayList;
import java.util.LinkedList;

public final class Average {
    private static Average instance;

    private Average() {
    }

    /**
     * Averange is a singleton class. This static function
     * returns the instance for the Average class
     * @return
     */
    public static Average getInstance() {
        if (instance == null) {
            instance = new Average();
        }
        return instance;
    }

    /**
     * Returns a list of sorted actors by their rating.
     * @param actorsArray
     * @param ascending
     * @return
     */
    public LinkedList<Actor> getRatingList(final ArrayList<Actor> actorsArray,
                                           final int ascending) {
        LinkedList<Actor> newList = new LinkedList<>(); // list that will store sorted actors
        newList.add(null); // add null so the last element in list is null
        actorsArray.forEach(actor -> {
            Actor actorFromList;
            int i = 0;
            while (true) {
                actorFromList = newList.get(i);
                // check for end of list
                if (actorFromList == null) {
                    break;
                }
                // sorting
                // ascending is 1 or -1. if 1 the order is ascending and descending for -1
                if ((actorFromList.getRating() * ascending) < (actor.getRating() * ascending)) {
                    i++;
                } else if (actorFromList.getRating() == actor.getRating()) {
                    if (ascending == 1) {
                        if (actorFromList.getName().compareTo(actor.getName()) < 0) {
                            i++;
                        } else {
                            break;
                        }
                    } else {
                        if (actorFromList.getName().compareTo(actor.getName()) > 0) {
                            i++;
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            // only add if the actor has a rating
            if (actor.getRating() != 0) {
                newList.add(i, actor);
            }
        });
        newList.remove(null); // end of list still has null
        return newList;
    }
}
