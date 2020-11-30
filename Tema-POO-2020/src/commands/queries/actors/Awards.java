package commands.queries.actors;

import actor.Actor;
import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class Awards {
    private static Awards instance;

    public Awards() {
    }

    /**
     * method to get instance of singleton class Awards
     * @return
     */
    public static Awards getInstance() {
        if (instance == null) {
            instance = new Awards();
        }
        return instance;
    }

    /**
     * Method returns a Linked List with the actors sorted by number of awards
     * @param actorsArray
     * @param awardsToSearch
     * @param ascending
     * @return
     */
    public LinkedList<Actor> getAwardsList(final ArrayList<Actor> actorsArray, final List<String>
            awardsToSearch, final int ascending) {
        LinkedList<Actor> newList = new LinkedList<>(); // List that will be returned
        newList.add(null);
        AtomicReference<Actor> actorFromList = new AtomicReference<>();

        actorsArray.forEach(actor -> {
            // will be true if actor has all the awards the query requires
            AtomicBoolean canAdd = new AtomicBoolean(true);

            // checking the actor's aeards
            awardsToSearch.forEach((String searchAward) -> {
               AtomicInteger ok = new AtomicInteger();
               actor.getAwards().forEach((ActorsAwards k, Integer i) -> {
                   if (searchAward.equalsIgnoreCase(String.valueOf(k))) {
                       ok.set(1);
                   }
               });
               if (ok.intValue() == 0) {
                   canAdd.set(false);
               }
            });
            if (canAdd.get()) {
                int i = 0;
                while (true) {
                    // all the actors in the list are sorted and the new actor is compared to
                    // the ones in the list and put at the right index
                    actorFromList.set(newList.get(i));
                    // checking for end of list
                    if (actorFromList.get() == null) {
                        break;
                    }

                    if ((actorFromList.get().getNumberOfAwards() * ascending)
                            < (actor.getNumberOfAwards() * ascending)) {
                        i++;
                    } else if (actorFromList.get().getNumberOfAwards()
                            == actor.getNumberOfAwards()) {
                        if (ascending == 1) {
                            if (actorFromList.get().getName().compareTo(actor.getName()) < 0) {
                                i++;
                            } else {
                                break;
                            }
                        } else {
                            if (actorFromList.get().getName().compareTo(actor.getName()) > 0) {
                                i++;
                            } else {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
                newList.add(i, actor);
            }
        });
        newList.remove(null); // removal of last element that is always null

        return newList;
    }
}
