package com.wbcsd.fileGenerator.api;

/**
 * @author Aashish
 * @version $Id: $
 */
public enum SiteType {

    DUG_WELL("Dug Well"), BORE_WELL("Bore Well"), DUG_CUM_BORE_WELL("Dug Cum Bore Well"), TUBE_WELL("Tube Well");

    private final String description;

    /**
     * Instantiates a new site type.
     * @param pDescription the description
     */
    private SiteType(final String pDescription) {
        this.description = pDescription;
    }

    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    public static SiteType value(String description) {
        for (SiteType type : SiteType.values()) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return type;
            }
        }
        return null;
    }

}
