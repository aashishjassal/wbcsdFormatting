package com.wbcsd.fileGenerator.api;

/**
 * The Enum State.
 * @author Aashish
 * @version $Id: $
 */
public enum Country {

    /** The andaman. */
    INDIA("01", "India");

    /** The code. */
    private final String code;

    /** The name. */
    private final String name;

    /**
     * Instantiates a new site type.
     * @param pCode the code
     * @param pName the description
     */
    private Country(final String pCode, final String pName) {
        this.code = pCode;
        this.name = pName;
    }

    /**
     * Gets the code.
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the description.
     * @return the description
     */
    public String getName() {
        return name;
    }

}
