package com.wbcsd.fileGenerator.api;

/**
 * The Enum State.
 * @author Aashish
 * @version $Id: $
 */
public enum Month {

    /** The andaman. */
    JAN(1, "January", "Jan"),
    /** The jan. */
    FEB(2, "Feburary", "Feb"),
    /** The jan. */
    MAR(3, "March", "Mar"),
    /** The jan. */
    APR(4, "April", "Apr"),
    /** The jan. */
    MAY(5, "May", "May"),
    /** The jan. */
    JUNE(6, "June", "Jun"),
    /** The jan. */
    JULY(7, "July", "Jul"),
    /** The jan. */
    AUG(8, "August", "Aug"),
    /** The jan. */
    SEPT(9, "September", "Sep"),
    /** The jan. */
    OCT(10, "October", "Oct"),
    /** The jan. */
    NOV(11, "November", "Nov"),
    /** The jan. */
    DEC(12, "December", "Dec");
    /** The name. */
    private final String name;

    /** The short name. */
    private final String shortName;

    /** The code. */
    private final Integer code;

    /**
     * Instantiates a new site type.
     * @param pCode the code
     * @param pName the description
     * @param shortName the short name
     */
    private Month(final Integer pCode, final String pName, final String shortName) {
        this.code = pCode;
        this.name = pName;
        this.shortName = shortName;
    }

    /**
     * Gets the code.
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Gets the description.
     * @return the description
     */
    public String getName() {
        return name;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Value.
     * @param name the name
     * @return the state
     */
    public static Month value(String name) {
        for (Month state : Month.values()) {
            if (state.getName().equalsIgnoreCase(name) || state.getShortName().equalsIgnoreCase(name)) {
                return state;
            }
        }
        return null;
    }

}
