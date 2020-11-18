package commands.queries.actors;

import actor.Actor;

import java.util.ArrayList;
import java.util.LinkedList;

public class Average {
    private static Average instance;

    private Average() {
    }

    public static Average getInstance() {
        if (instance == null) {
            instance = new Average();
        }
        return instance;
    }

    public LinkedList<Actor> getRatingList(ArrayList<Actor> actorsArray, int ascending) {
        LinkedList<Actor> newList = new LinkedList<>();
        newList.add(null);
        actorsArray.forEach(actor -> {
            Actor actorFromList;
            int i = 0;
            while (true) {
                actorFromList = newList.get(i);
                if (actorFromList == null)
                    break;
                if ((actorFromList.getRating() * ascending) < (actor.getRating() * ascending)) {
                    i++;
                }
                else if (actorFromList.getRating() == actor.getRating()) {
                    if (ascending == 1) {
                        if (actorFromList.getName().compareTo(actor.getName()) < 0) {
                            i++;
                        }
                        else
                            break;
                    }
                    else {
                        if (actorFromList.getName().compareTo(actor.getName()) > 0) {
                            i++;
                        }
                        else
                            break;
                    }
                }
                else {
                    break;
                }
            }
            newList.add(i, actor);
        });
        newList.remove(null);
        return newList;
    }
}
