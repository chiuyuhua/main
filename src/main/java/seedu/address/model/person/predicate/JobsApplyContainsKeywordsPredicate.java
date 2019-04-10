package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Gender} matches any of the keywords given.
 */
public class JobsApplyContainsKeywordsPredicate extends PredicateManager {
    private final List<String> keywords;

    public JobsApplyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {

        return (keywords == null) || keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(StringUtil.getSetString
                (person.getJobsApply()), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobsApplyContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((JobsApplyContainsKeywordsPredicate) other).keywords)); // state check
    }

}
