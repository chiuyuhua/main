package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.predicate.UniqueFilterList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {

    private static final int NUMBER_OF_LISTS = 4;
    // Identity fields
    private final JobName name;

    // Data fields
    private UniquePersonList personsInJob = new UniquePersonList();
    private ArrayList<UniquePersonList> personsList = new ArrayList<>(NUMBER_OF_LISTS);
    private ArrayList<Set<Nric>> personsNricList = new ArrayList<>(NUMBER_OF_LISTS);
    private ArrayList<UniqueFilterList> predicateList = new ArrayList<>(NUMBER_OF_LISTS);


    /**
     * Every field must be present and not null.
     */
    public Job(JobName name) {
        requireAllNonNull(name);

        this.name = name;
        for (int i = 0; i < 4; i++) {
            personsList.add(new UniquePersonList());
            personsNricList.add(new HashSet<>());
        }
    }

    public Job(JobName name, ArrayList<UniquePersonList> personList, ArrayList<Set<Nric>> nricList,
               UniquePersonList personsInJob) {
        requireAllNonNull(name, personList, nricList, personsInJob);

        this.name = name;
        this.personsList = personList;
        this.personsNricList = nricList;
        this.personsInJob = personsInJob;
    }

    public JobName getName() {
        return name;
    }

    /**
     * Adds all persons on displayed filter list to first list of job.
     * Only adds if not already in job.
     */
    public void addFilteredList(FilteredList<Person> filteredPersons, Integer to) {
        for (int i = 0; i < filteredPersons.size(); i++) {
            if (personsList.get(to).contains(filteredPersons.get(i))) {
                continue;
            }
            if (!personsInJob.contains(filteredPersons.get(i))) {
                personsInJob.add(filteredPersons.get(i));
            }
            add(filteredPersons.get(i), to);
        }
    }

    /**
     * Removes a person from a job
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!personsInJob.contains(toRemove)) {
            throw new PersonNotFoundException();
        }
        for (int i = 0; i < 4; i++) {
            if (personsList.get(i).contains(toRemove)) {
                personsList.get(i).remove(toRemove);
                personsNricList.get(i).remove(toRemove.getNric());
            }
        }
        personsInJob.remove(toRemove);
    }

    /**
     * Removes a person from a job list
     */
    public void removeFromList(Person toRemove, Integer listNumber) {
        requireNonNull(toRemove);
        boolean shouldStillExist = false;
        if (!personsInJob.contains(toRemove)) {
            throw new PersonNotFoundException();
        }
        for (int i = 0; i < 4; i++) {
            if (i == listNumber) {
                continue;
            }
            if (personsList.get(i).contains(toRemove)) {
                shouldStillExist = true;
            }
        }
        personsList.get(listNumber).remove(toRemove);
        if (!shouldStillExist) {
            personsInJob.remove(toRemove);
        }
    }

    /**
     * Adds a person to a job.
     * Goes to the first list
     */
    public void add(Person person, Integer destination) {
        if (personsList.get(destination).contains(person)) {
            throw new DuplicatePersonException();
        }
        if (!personsInJob.contains(person)) {
            personsInJob.add(person);
        }
        personsList.get(destination).add(person);
        personsNricList.get(destination).add(person.getNric());
    }

    /**
     * Returns one of the four UniqurePredicateLists
     */
    public UniqueFilterList getPredicateList(Integer listNumber) {
        return predicateList.get(listNumber);
    }

    /**
     * Returns one of the four UniqurePersonLists
     */
    public UniquePersonList getList(Integer listNumber) {
        return personsList.get(listNumber);
    }


    /**
     * Moves a person from one list to another
     */
    public int move(Person target, Integer source, Integer dest) {

        if (!(personsList.get(source).contains(target))) {
            return 0;
        }

        if (personsList.get(dest).contains(target)) {
            return 1;
        }

        personsList.get(dest).add(target);
        personsNricList.get(dest).add(target.getNric());
        return 2;
    }

    /**
     * Returns an UniquePerson list using {@code listNumber}
     */
    public final UniquePersonList getPeople(Integer listNumber) {
        return personsList.get(listNumber);
    }

    /**
     * Returns true if Job contains Person
     */
    public final boolean contains(Person person) {
        return personsInJob.contains(person);
    }

    /**
     * Returns an immutable known programming language set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public final Set<Nric> getPersonsNric(Integer listNumber) {
        return Collections.unmodifiableSet(personsNricList.get(listNumber));
    }

    public final ArrayList<Name> getPeopleNames(List<Person> peopleList) {
        ArrayList<Name> names = new ArrayList<>(peopleList.size());
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
        requireNonNull(otherJob);
        if (otherJob == this) {
            return true;
        }

        return ((otherJob.getName()).equals(this.getName()));
    }

    /**
     * Returns true if both jobs have the same identity and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return otherJob.getName().equals(getName());
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
        return builder.toString();
    }

}
