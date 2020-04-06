package com.moderndrummer.entity.exceptions;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class ModernDrummerException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public final static String SYSTEM_ERROR = "Error in System";
    public final static String Member_CERTIFICATE = "Member Certificate error";
    public final static String Member_CERTIFICATE_NOT_FOUND = "Member Certificate not found";
    public final static String Member_CERTIFICATE_UNIQUE = "Member Certificate already exists";
    public final static String Member_MISSING = "Member not found";
    public final static String Member_SITE_MISSING = "Dive site not found";
    public final static String USER_MISSING = "User missing";
    public final static String USER_NOT_CREATED = "User was not created correctly";
    public final static String COMMENTS_MISSING = "Comments missing";
    public final static String Member_SITE_RANK_MISSING = "Dive site ranking not found";
    public final static String CERTIFICATE = "Certificate not found";
    public final static String CERTIFICATE_TITLE_EXISTS = "Certificate title already exists";
    public final static String PRODUCT_COMPANY_EXISTS = "Product company already exists";
    public final static String DIVEEQUIPMENT_EXISTS = "Diveequipment already exists";
    public final static String DIVEEQUIPMENT_TYPE_EXISTS = "Diveequipment type already exists";
    public final static String MODEL_EXISTS = "Model already exists";

    public final static String INSTRUCTOR_NUMBER = "Instructor Number incorrect";
    public final static String DIVELOG = "Divelog not found";
    public final static String CERTIFICATE_UNIQUE = "Certificate already exists";
    public final static String COUNTRY_UNIQUE = "Country already exists";
    public final static String PRODUCTCOMPANY_UNIQUE = "Company already exists";
    public final static String COUNTRY_MISSING = "Country missing";
    public final static String MemberSCHOOL_UNIQUE = "Memberschool already exists";
    public final static String MemberSCHOOL_NAME_MISSING = "Memberschool name missing";
    public final static String MemberSCHOOL_CODE_MISSING = "Memberschool code missing";
    public final static String MemberSCHOOL_NON_EXISTING = "Memberschool does not exist";
    public final static String MemberSCHOOL_PHONE_MAX = "phonenumber too long";
    public final static String SCHOOL_INSTRUCTORS_EXISTS = "Instructors exists, please delete all instructors first!";
    public final static String SCHOOL_LOGS_EXISTS = "Divelogs exists, please delete or move all divelogs first!";
    public final static String COUNTRY_DELETED = "Country does not exist anylonger";
    public final static String Member_CERTIFICATE_EXISTS = "Member Certificate does not exist";
    public final static String DIVE_SITE_GRAPHICS_EXISTS = "Dive site graphics exist";
    public final static String DIVE_SITE_GRAPHICS_NOT_FOUND = "Dive site graphics does not exist";
    public final static String DIVE_SITE_AUTHENTICATION = "Permission missing for delete";
    public final static String DIVE_LOG_GRAPHICS_NOT_FOUND = "Dive log graphics does not exist";
    public final static String DIVE_LOG_AUTHENTICATION = "Permission missing for delete";
    public final static String USER_GRAPHICS_NOT_FOUND = "User graphics does not exist";
    public final static String USER_GRAPHICS_NOT_DELETED = "User graphics not deleted";
    public final static String USER_AUTHENTICATION = "Permission missing for delete";
    public final static String USER_EXISTS = "Username already exists";
    public final static String USER_NO_EXISTS = "User does not exist exist";
    public final static String USER_GRAPHIC_NO_EXIST = "Image does not exist";
    public final static String INSTRUCTOR_NOT_EXISTS = "Diveinstructor does not exist";
    public final static String INSTRUCTOR_IN_SCHOOL_NOT_EXISTS = "Diveinstructor in school does not exist";
    public final static String INSTRUCTOR_AND_SCHOOL_NOT_EXISTS = "Please choose school and instructor before add certification";
    public final static String SCHOOL_NOT_EXISTS = "Member school does not exist";

    public final static String Member_CERTIFICATE_MISSING = "Member certificate missing";
    public final static String Member_CERTIFICATE_DUPLICATES = "Member certificate duplicates";

    public final static String Member_CERTIFICATE_NUMBER_EXISTS = "Member certificate with Member number already exists";
    public final static String Member_CERTIFICATE_NR_MISSING = "Member certificate number missing";
    public final static String XML_GENERATE_DIVELOGS_EMPTY = "No Divelogs to generate";

    public final static String EMPTY_NUMBER_VALUE = "Value missing ";
    public final static String NO_NUMBER_VALUE = "Value is not a number ";
    public final static String ZERO_NUMBER_VALUE = "zero number value ";
    public final static String STRING_VALUE_MISSING = "value missing for";
    public final static String DUPLICATE_EMAIL = "Email already exists";
    public final static String DUPLICATE_INSTRUCTORNUMBER = "Instructornumber already exists";
    public final static String WRONG_EMAIL_ADDRESS = "incorrect email address";
    public final static String INVALID_PASSWORD = "incorrect password minimum size 5";
    public final static String VALUES_MISSING = "Field values missing";
    public final static String INVALID_OPERATOR = "Invalid operator or value";

    /**
     * Constructor for DiveMasterException.
     * 
     * @param s
     *            String
     */
    public ModernDrummerException(final String s) {
        super(s);

    }

    public ModernDrummerException() {

    }

}
