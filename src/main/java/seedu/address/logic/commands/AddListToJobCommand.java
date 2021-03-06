package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBNAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobListName;
import seedu.address.model.job.JobName;


/**
 * Adds a person to the address book.
 */
public class AddListToJobCommand extends Command {

    public static final String COMMAND_WORD = "addAll";
    public static final String COMMAND_ALIAS = "aa";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds all shown people to job . "
            + "Parameters: "
            + "DESTINATION-LIST-NAME "
            + "SOURCE-LIST-NAME (Optional. Entire database if omitted.)"
            + PREFIX_JOBNAME + "JobName (Optional. Uses activeDisplayedJob if omitted.)"
            + "Example: " + COMMAND_WORD + " "
            + "kiv "
            + "applicant "
            + PREFIX_JOBNAME + "Helper "
            + "The alias \"aa\" can be used instead.\n"
            + "Example: " + COMMAND_ALIAS + " "
            + "kiv "
            + "applicant "
            + PREFIX_JOBNAME + "Helper ";

    public static final String MESSAGE_SUCCESS = "All non-duplicated people added to job: %1$s.";
    public static final String MESSAGE_MISSING_JOB = "This job does not exist";
    public static final String MESSAGE_NO_ACTIVE_JOB = "No active Job, please provide a JobName with prefix jn/";
    public static final String MESSAGE_NO_DESTINATION = "Please provide a destination list\n";
    public static final String MESSAGE_IDENTICAL_LISTS = "Destination and source lists cannot be the same\n";

    private final JobListName to;
    private final JobListName from;
    private JobName toAdd;

    /**
     * Creates an AddCommand to add the specified {@code job}
     */
    public AddListToJobCommand(JobName name, JobListName toName, JobListName fromName) {
        toAdd = name;
        to = toName;
        from = fromName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (toAdd == null) {
            if (model.getIsAllJobScreen()) {
                throw new CommandException(MESSAGE_NO_ACTIVE_JOB);
            }
            toAdd = model.getActiveJob().getName();
        }

        Job tempJob = new Job(toAdd);
        if (!model.hasJob(tempJob)) {
            throw new CommandException(MESSAGE_MISSING_JOB);
        }

        if (to == from) {
            throw new CommandException(MESSAGE_IDENTICAL_LISTS);
        }

        model.addFilteredPersonsToJob(toAdd, from, to);

        model.commitAddressBook();
        if (toAdd == null) {

        }
        String command = String.format(MESSAGE_SUCCESS, toAdd);
        return new CommandResult(command);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddListToJobCommand // instanceof handles nulls
            && to.equals(((AddListToJobCommand) other).to)
            && from.equals(((AddListToJobCommand) other).from)
            && (toAdd == null || toAdd.equals(((AddListToJobCommand) other).toAdd)));
    }
}
