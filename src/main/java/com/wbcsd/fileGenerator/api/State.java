package com.wbcsd.fileGenerator.api;

/**
 * The Enum State.
 * 
 * @author Aashish
 * @version $Id: $
 */
public enum State {

	/** The andaman. */
	ANDAMAN("01", "Andaman and Nicobar Island"),

	/** The andhra pradesh. */
	ANDHRA_PRADESH("02", "Andhra Pradesh"),

	/** The arunachal pradesh. */
	ARUNACHAL_PRADESH("03", "Arunachal Pradesh"),
	/** The assam. */
	ASSAM("04", "Assam"),
	/** The bihar. */
	BIHAR("05", "Bihar"),
	/** The chandigarh. */
	CHANDIGARH("06", "Chandigarh"),
	/** The chhattisgarh. */
	CHHATTISGARH("07", "Chhattisgarh"),
	/** The dadar. */
	DADAR("08", "Dadra And Nagar Haveli"),
	/** The daman diu. */
	DAMAN_DIU("09", "Daman and Diu"),
	/** The delhi. */
	DELHI("10", "Delhi"),
	/** The goa. */
	GOA("11", "Goa"),
	/** The gujrat. */
	GUJARAT("12", "Gujarat"),
	/** The haryana. */
	HARYANA("13", "Haryana"),
	/** The himachal pradesh. */
	HIMACHAL_PRADESH("14", "Himachal Pradesh"),
	/** The jammu kashmir. */
	JAMMU_KASHMIR("15", "Jammu and Kashmir"),
	/** The jharkhand. */
	JHARKHAND("16", "Jharkhand"),
	/** The karnatka. */
	KARNATAKA("17", "Karnataka"),
	/** The kerala. */
	KERALA("18", "Kerala"),
	/** The lakshadweep. */
	LAKSHADWEEP("19", "Lakshadweep"),
	/** The madhya pradesh. */
	MADHYA_PRADESH("20", "Madhya Pradesh"),
	/** The maharashtra. */
	MAHARASHTRA("21", "Maharashtra"),
	/** The manipur. */
	MANIPUR("22", "Manipur"),
	/** The meghalaya. */
	MEGHALAYA("23", "Meghalaya"),
	/** The mizoram. */
	MIZORAM("24", "Mizoram"),
	/** The nagaland. */
	NAGALAND("25", "Nagaland"),
	/** The odisha. */
	ORRISA("26", "Orissa"),
	/** The puducherry. */
	PUDUCHERRY("27", "Pondicherry"),
	/** The punjab. */
	PUNJAB("28", "Punjab"),
	/** The rajasthan. */
	RAJASTHAN("29", "Rajasthan"),
	/** The sikkim. */
	SIKKIM("30", "Sikkim"),
	/** The tamil nadu. */
	TAMIL_NADU("31", "Tamil Nadu"),
	/** The telengana. */
	TELENGANA("32", "Telangana"),
	/** The tripura. */
	TRIPURA("33", "Tripura"),
	/** The uttarakhand. */
	UTTARANCHAL("34", "UTTARAKHAND"),
	/** The uttar pradesh. */
	UTTAR_PRADESH("35", "UttarPradesh"),
	/** The west bengal. */
	WEST_BENGAL("36", "West Bengal");

	/** The code. */
	private final String code;

	/** The name. */
	private final String name;

	/**
	 * Instantiates a new site type.
	 * 
	 * @param pCode
	 *            the code
	 * @param pName
	 *            the description
	 */
	private State(final String pCode, final String pName) {
		this.code = pCode;
		this.name = pName;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getName() {
		return name;
	}

	/**
	 * Value.
	 * 
	 * @param name
	 *            the name
	 * @return the state
	 */
	public static State value(String name) {
		for (State state : State.values()) {
			if (state.getName().equalsIgnoreCase(name)) {
				return state;
			}
		}
		return null;
	}

	public static State valueByCode(String code) {
		for (State state : State.values()) {
			if (state.getCode().equalsIgnoreCase(code)) {
				return state;
			}
		}
		return null;
	}

}
