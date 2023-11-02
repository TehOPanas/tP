package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_EMPTY = new Prefix("", "");
    public static final Prefix PREFIX_NAME = new Prefix("n/", "name/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/", "phone/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/", "email/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/", "address/");
    public static final Prefix PREFIX_TAG = new Prefix("t/", "tag/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/", "remark/");
    public static final Prefix PREFIX_ADD_TAG = new Prefix("at/", "addtag/");
    public static final Prefix PREFIX_DELETE_TAG = new Prefix("dt/", "deletetag/");
    public static final Prefix PREFIX_INSURANCE = new Prefix("i/", "insurance/");
    public static final Prefix PREFIX_ADD_INSURANCE = new Prefix("ai/","addinsurance/");
    public static final Prefix PREFIX_DELETE_INSURANCE = new Prefix("di/","deleteinsurance/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("pr/", "priority/");
    public static final Prefix PREFIX_APPOINTMENT = new Prefix("d/", "date/");
    public static final Prefix PREFIX_APPOINTMENT_TIME = new Prefix("t/", "time/");
    public static final Prefix PREFIX_APPOINTMENT_VENUE = new Prefix("v/", "venue/");

    // Lower case prefixes
//    public static final Prefix PREFIX_NAME_UPPER = new Prefix("N/");
//    public static final Prefix PREFIX_PHONE_UPPER = new Prefix("P/");
//    public static final Prefix PREFIX_EMAIL_UPPER = new Prefix("E/");
//    public static final Prefix PREFIX_ADDRESS_UPPER = new Prefix("A/");
//    public static final Prefix PREFIX_TAG_UPPER = new Prefix("T/");
//    public static final Prefix PREFIX_REMARK_UPPER = new Prefix("R/");
//    public static final Prefix PREFIX_ADD_TAG_UPPER = new Prefix("AT/");
//    public static final Prefix PREFIX_DELETE_TAG_UPPER = new Prefix("DT/");
//    public static final Prefix PREFIX_INSURANCE_UPPER = new Prefix("I/");
//    public static final Prefix PREFIX_ADD_INSURANCE_UPPER = new Prefix("AI/");
//    public static final Prefix PREFIX_DELETE_INSURANCE_UPPER = new Prefix("DI/");
//    public static final Prefix PREFIX_PRIORITY_UPPER = new Prefix("PR/");
//    public static final Prefix PREFIX_APPOINTMENT_UPPER = new Prefix("D/");
//    public static final Prefix PREFIX_APPOINTMENT_TIME_UPPER = new Prefix("T/");
//    public static final Prefix PREFIX_APPOINTMENT_VENUE_UPPER = new Prefix("V/");
}
