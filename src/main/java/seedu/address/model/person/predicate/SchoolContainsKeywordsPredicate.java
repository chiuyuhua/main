package seedu.address.model.person.predicate;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} matches any of the keywords given.
 */
public class SchoolContainsKeywordsPredicate extends PredicateManager {
    private final List<String> keywords;

    public SchoolContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return (keywords == null) || keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getSchool().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SchoolContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((SchoolContainsKeywordsPredicate) other).keywords)); // state check
    }

}
