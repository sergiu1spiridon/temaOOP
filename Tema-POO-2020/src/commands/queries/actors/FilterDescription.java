package commands.queries.actors;

import actor.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class FilterDescription {
    private static FilterDescription instance;

    public FilterDescription() {
    }

    public static FilterDescription getInstance() {
        if (instance == null) {
            instance = new FilterDescription();
        }
        return instance;
    }

    public LinkedList<Actor> getFilteredActors(ArrayList<Actor> actorArrayList, List<String> wordsToSearch, int ascending) {
        LinkedList<Actor> newList = new LinkedList<>();
        AtomicBoolean canAdd = new AtomicBoolean(true);
        AtomicInteger start = new AtomicInteger();
        AtomicInteger finish = new AtomicInteger();
        String[] splitDescription;

        newList.add(null);

        for (Actor actor:actorArrayList) {
            canAdd.set(true);
            splitDescription = actor.getCareerDescription().toLowerCase().split("\\s+|\\,|\\.");
            String[] finalSplitDescription = splitDescription;
            wordsToSearch.forEach(word -> {
                if (!Arrays.stream(finalSplitDescription).anyMatch(word.toLowerCase()::equals)) {
                    canAdd.set(false);
                }
            });
            if (canAdd.get()) {
                int i = 0;
                while (true) {
                    Actor actorFromList = newList.get(i);
                    if (actorFromList == null)
                        break;
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
                newList.add(i, actor);
            }
        }
        newList.remove(null);
        return newList;
    }
}
