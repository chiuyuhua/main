package seedu.address.model.job;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {

    private static final int NUMBER_OF_LISTS = 4;
    private static final String[] LIST_NAMES = {"All Applicants", "KIV", "Interview", "Shortlist"};
    // Identity fields
    private final JobName name;

    // Data fields
    private ArrayList<UniquePersonList> personsHash = new ArrayList<> (NUMBER_OF_LISTS);
    private ArrayList<List<Person>> personsList = new ArrayList<>(NUMBER_OF_LISTS);


    /**
     * Every field must be present and not null.
     */
    public Job(JobName name) {
        requireAllNonNull(name);

        this.name = name;
        for (int i = 0; i < 4; i++) {
            personsHash.add(new UniquePersonList());
        }
    }

    public JobName getName() {
        return name;
    }


    /**
     * Returns an immutable  people set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public final List<Person> getPeople(int listNumber) {
        return personsHash.get(listNumber).asUnmodifiableObservableList();
    }

    public final ArrayList<Name> getPeopleNames(List<Person> peopleList) {
        ArrayList<Name> names = new ArrayList<> (peopleList.size());
        for (int i = 0; i < peopleList.size(); i++) {
            names.add(peopleList.get(i).getName());
        }
        return names;
    }

    /**
     * Returns true if both jobs have the same name.
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        for (int i = 0; i < NUMBER_OF_LISTS; i++) {
            builder.append(LIST_NAMES[i]);
            getPeopleNames(getPeople(i)).forEach(builder::append);
        }
        return builder.toString();
    }

}
