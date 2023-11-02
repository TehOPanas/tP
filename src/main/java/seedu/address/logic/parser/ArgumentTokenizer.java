package seedu.address.logic.parser;

//import static java.util.Objects.requireNonNull;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_INSURANCE_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TAG_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_INSURANCE_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_TAG_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPTY;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK_UPPER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_UPPER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tokenizes arguments string of the form: {@code preamble <prefix>value <prefix>value ...}<br>
 *     e.g. {@code some preamble text t/ 11.00 t/12.00 k/ m/ July}  where prefixes are {@code t/ k/ m/}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code k/} in the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated e.g. the value of {@code t/}
 *    in the above example.<br>
 */
public class ArgumentTokenizer {

    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object that maps prefixes to their
     * respective argument values. Only the given prefixes will be recognized in the arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to tokenize the arguments string with
     * @return           ArgumentMultimap object that maps prefixes to their arguments
     */
    public static ArgumentMultimap tokenize(String argsString, Prefix... prefixes) {
        List<PrefixPosition> positions = findAllPrefixPositions(argsString, prefixes);
        return extractArguments(argsString, positions);
    }

    /**
     * Converts all uppercase {@code Prefix} to lowercase {@code Prefix}
     *
     * @param argsString arguments that may contain uppercase prefixes
     * @return processed arguments containing only lowercase prefixes
     */
//    public static String preprocessArgsString(String argsString) {
//        requireNonNull(argsString);
//
//        Prefix[] uppercasePrefixes = new Prefix[] { PREFIX_NAME_UPPER, PREFIX_PHONE_UPPER,
//            PREFIX_EMAIL_UPPER, PREFIX_PRIORITY_UPPER, PREFIX_TAG_UPPER, PREFIX_REMARK_UPPER,
//            PREFIX_INSURANCE_UPPER, PREFIX_ADDRESS_UPPER, PREFIX_ADD_INSURANCE_UPPER,
//            PREFIX_DELETE_INSURANCE_UPPER, PREFIX_ADD_TAG_UPPER, PREFIX_DELETE_TAG_UPPER, PREFIX_APPOINTMENT_UPPER,
//            PREFIX_APPOINTMENT_TIME_UPPER, PREFIX_APPOINTMENT_VENUE_UPPER};
//
//        String processedArgs = argsString;
//
//        for (Prefix p : uppercasePrefixes) {
//            String upperPrefix = p.getPrefix();
//            String lowerPrefix = upperPrefix.toLowerCase();
//
//            processedArgs = processedArgs.replaceAll(" " + upperPrefix, " " + lowerPrefix);
//        }
//
//        return processedArgs;
//    }

    /**
     * Finds all zero-based prefix positions in the given arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to find in the arguments string
     * @return           List of zero-based prefix positions in the given arguments string
     */
    private static List<PrefixPosition> findAllPrefixPositions(String argsString, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .flatMap(prefix -> findPrefixPositions(argsString, prefix).stream())
                .collect(Collectors.toList());
    }

    /**
     * {@see findAllPrefixPositions}
     */
    private static List<PrefixPosition> findPrefixPositions(String argsString, Prefix prefix) {
        List<PrefixPosition> positions = new ArrayList<>();

        prefix.useShortPrefix();
        int prefixPosition = findPrefixPosition(argsString, prefix, 0);
        while (prefixPosition != -1) {
            PrefixPosition extendedPrefix = new PrefixPosition(prefix, prefixPosition,
                    prefixPosition + prefix.getPrefixLength());

            positions.add(extendedPrefix);

            prefix.useShortPrefix();
            prefixPosition = findPrefixPosition(argsString, prefix, prefixPosition);
        }

        return positions;
    }

    /**
     * Returns the index of the first occurrence of {@code prefix} in
     * {@code argsString} starting from index {@code fromIndex}. An occurrence
     * is valid if there is a whitespace before {@code prefix}. Returns -1 if no
     * such occurrence can be found.
     *
     * E.g if {@code argsString} = "e/hip/900", {@code prefix} = "p/" and
     * {@code fromIndex} = 0, this method returns -1 as there are no valid
     * occurrences of "p/" with whitespace before it. However, if
     * {@code argsString} = "e/hi p/900", {@code prefix} = "p/" and
     * {@code fromIndex} = 0, this method returns 5.
     */
    private static int findPrefixPosition(String argsString, Prefix prefix, int fromIndex) {
        String shortPrefix = prefix.getPrefix();
        String longPrefix = prefix.getLongPrefix();

        int shortPrefixIndex = argsString.toLowerCase().indexOf(" " + shortPrefix, fromIndex);
        int longPrefixIndex = argsString.toLowerCase().indexOf(" " + longPrefix, fromIndex);

        if (shortPrefixIndex == -1 && longPrefixIndex == -1) {
            return -1;
        }

        if (shortPrefixIndex != -1 && longPrefixIndex != -1) {
            if (longPrefixIndex < shortPrefixIndex) {
                prefix.useLongPrefix();
            }
            return Math.min(shortPrefixIndex + 1, longPrefixIndex + 1);
        }
        if (longPrefixIndex == -1) {
            return shortPrefixIndex + 1;
        }

        prefix.useLongPrefix();
        return longPrefixIndex + 1;
    }

    /**
     * Extracts prefixes and their argument values, and returns an {@code ArgumentMultimap} object that maps the
     * extracted prefixes to their respective arguments. Prefixes are extracted based on their zero-based positions in
     * {@code argsString}.
     *
     * @param argsString      Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixPositions Zero-based positions of all prefixes in {@code argsString}
     * @return                ArgumentMultimap object that maps prefixes to their arguments
     */
    private static ArgumentMultimap extractArguments(String argsString, List<PrefixPosition> prefixPositions) {

        // Sort by start position
        prefixPositions.sort((prefix1, prefix2) -> prefix1.getStartPosition() - prefix2.getStartPosition());

        // Insert a PrefixPosition to represent the preamble
        PrefixPosition preambleMarker = new PrefixPosition(PREFIX_EMPTY, 0, 1);
        prefixPositions.add(0, preambleMarker);

        // Add a dummy PrefixPosition to represent the end of the string
        PrefixPosition endPositionMarker = new PrefixPosition(PREFIX_EMPTY, argsString.length(),
                argsString.length() + 1);

        prefixPositions.add(endPositionMarker);

        // Map prefixes to their argument values (if any)
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            // Extract and store prefixes and their arguments
            Prefix argPrefix = prefixPositions.get(i).getPrefix();
            String argValue = extractArgumentValue(argsString, prefixPositions.get(i), prefixPositions.get(i + 1));
            argMultimap.put(argPrefix, argValue);
        }

        return argMultimap;
    }

    /**
     * Returns the trimmed value of the argument in the arguments string specified by {@code currentPrefixPosition}.
     * The end position of the value is determined by {@code nextPrefixPosition}.
     */
    private static String extractArgumentValue(String argsString,
                                        PrefixPosition currentPrefixPosition,
                                        PrefixPosition nextPrefixPosition) {
        int valueStartPos = currentPrefixPosition.getEndPosition();
        int valueEndPos = nextPrefixPosition.getStartPosition();
        String value = argsString.substring(valueStartPos, valueEndPos);

        return value.trim();
    }

    /**
     * Represents a prefix's position in an arguments string.
     */
    private static class PrefixPosition {
        private int startPosition;
        private int endPosition;
        private final Prefix prefix;


        PrefixPosition(Prefix prefix, int startPosition, int endPosition) {
            this.prefix = prefix;
            this.startPosition = startPosition;
            this.endPosition = endPosition;
        }

        int getStartPosition() {
            return startPosition;
        }

        Prefix getPrefix() {
            return prefix;
        }

        int getEndPosition() {
            return endPosition;
        }
    }

}
