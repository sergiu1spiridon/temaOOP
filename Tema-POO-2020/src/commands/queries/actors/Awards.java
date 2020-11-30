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

    public static Awards getInstance() {
        if (instance == null) {
            instance = new Awards();
        }
        return instance;
    }

    public LinkedList<Actor> getAwardsList(final ArrayList<Actor> actorsArray, final List<String>
            awardsToSearch, final int ascending) {
        LinkedList<Actor> newList = new LinkedList<>();
        newList.add(null);
        AtomicReference<Actor> actorFromList = new AtomicReference<>();

        actorsArray.forEach(actor -> {
            AtomicBoolean canAdd = new AtomicBoolean(true);

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
                    actorFromList.set(newList.get(i));

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
        newList.remove(null);

        return newList;
    }
}
