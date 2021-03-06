package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Grade;
import seedu.address.model.person.InterviewScores;
import seedu.address.model.person.JobsApply;
import seedu.address.model.person.KnownProgLang;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.PastJob;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Race;
import seedu.address.model.person.School;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Nric("S9671597H"), new Gender("Male"), new Race("Chinese"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new School("NUS"), new Major("CS"),
                    new Grade("4.56"), getKnownProgLangSet("python"), getPastJobSet("Professor", "SDE"),
                    getJobsApplySet("Software-Engineer"), new InterviewScores("5,5,1,1,3"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Nric("S9412345A"), new Gender("Female"), new Race("Chinese"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new School("NTU"), new Major("CS"),
                    new Grade("3.59"), getKnownProgLangSet("python"), getPastJobSet("Lawyer"),
                    getJobsApplySet("Lawyer-Intern"), new InterviewScores("10,9,8,7,6"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Nric("S9354321R"), new Gender("Female"), new Race("Others"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new School("SMU"), new Major("CS"),
                    new Grade("4.00"), getKnownProgLangSet("python"), getPastJobSet("Doctor"),
                    getJobsApplySet("Data-Analyst"), new InterviewScores("5,6,7,8,10"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Nric("S9212345Z"), new Gender("Male"), new Race("Chinese"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new School("SUSS"), new Major("CS"),
                    new Grade("5.00"), getKnownProgLangSet("python"), getPastJobSet("Data Scientist"),
                    getJobsApplySet("Investment-Analyst"), new InterviewScores("10,10,10,10,10"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Nric("S9123456G"), new Gender("Male"), new Race("Malay"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new School("SIT"), new Major("CS"),
                    new Grade("4.96"), getKnownProgLangSet("python"), getPastJobSet("Software Engineer"),
                    getJobsApplySet("Software-Engineer"), new InterviewScores("9,8,2,1,10"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Nric("S9012312T"), new Gender("Male"), new Race("Indian"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new School("SUTD"), new Major("CS"),
                    new Grade("3.29"), getKnownProgLangSet("python"), getPastJobSet("Professor"),
                    getJobsApplySet("Security"), new InterviewScores("1,1,1,1,1"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a past job set containing the list of strings given.
     */
    public static Set<KnownProgLang> getKnownProgLangSet(String... strings) {
        return Arrays.stream(strings)
            .map(KnownProgLang::new)
            .collect(Collectors.toSet());
    }

    /**
     * Returns a past job set containing the list of strings given.
     */
    public static Set<PastJob> getPastJobSet(String... strings) {
        return Arrays.stream(strings)
            .map(PastJob::new)
            .collect(Collectors.toSet());


    }

    /**
     * Returns a jobs applying for set containing the list of strings given.
     */
    public static Set<JobsApply> getJobsApplySet(String... strings) {
        return Arrays.stream(strings)
                .map(JobsApply::new)
                .collect(Collectors.toSet());


    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
