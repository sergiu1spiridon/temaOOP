package commands.queries.actors;

import actor.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class FilterDescription {
    private static FilterDescription instance;

    public FilterDescription() {
    }

    /**
     * Method to get instance for singleton class FilterDescription
     * @return
     */
    public static FilterDescription getInstance() {
        if (instance == null) {
            instance = new FilterDescription();
        }
        return instance;
    }

    /**
     * Returns actors that have all the words in their description, sorted by name
     * @param actorArrayList
     * @param wordsToSearch
     * @param ascending
     * @return
     */
    public LinkedList<Actor> getFilteredActors(final ArrayList<Actor> actorArrayList,
                             final List<String> wordsToSearch, final int ascending) {
        LinkedList<Actor> newList = new LinkedList<>();
        AtomicBoolean canAdd = new AtomicBoolean(true);
        AtomicInteger start = new AtomicInteger();
        AtomicInteger finish = new AtomicInteger();
        String[] splitDescription;

        newList.add(null);

        for (Actor actor:actorArrayList) {
            canAdd.set(true); // will be false if a word is not found in the description
            splitDescription = actor.getCareerDescription().toLowerCase().split("\\s+|\\,|\\.|\\-");
            String[] finalSplitDescription = splitDescription;
            // check for the words in description
            wordsToSearch.forEach(word -> {
                if (!Arrays.stream(finalSplitDescription).anyMatch(word.toLowerCase()::equals)) {
                    canAdd.set(false);
                }
            });
            // sorting
            if (canAdd.get()) {
                int i = 0;
                while (i < newList.size()) {
                    Actor actorFromList = newList.get(i);
                    if (actorFromList == null) {
                        break;
                    }
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

                }
                newList.add(i, actor);
            }
        }
        newList.remove(null);
        return newList;
    }
}
