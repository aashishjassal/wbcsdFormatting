package com.wbcsd.fileGenerator.application.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.wbcsd.fileGenerator.api.SiteInfo;

/**
 * @author Aashish
 * @version $Id: $
 */
public class DistrictInfoUtils {

	private static final String UNDERSCORE = "_";

	private static final Map<String, String> explicitConversions = new HashMap<String, String>();
	static {
		explicitConversions.put("gandhinagr", "Gandhinagar");
		explicitConversions.put("puducherry", "PUDUCHERY");
		explicitConversions.put("pondicherry", "PUDUCHERY");
		explicitConversions.put("mehaboobnagar", "MAHBUBNAGAR");
		explicitConversions.put("mehboobnagar", "MAHBUBNAGAR");
		explicitConversions.put("mehbubnagar", "MAHBUBNAGAR");

		explicitConversions.put("chittor", "Chittoor");
		explicitConversions.put("vishakhapatnam", "VISAKHAPATNAM");
		explicitConversions.put("east  godavari", "EAST GODAVARI");
		explicitConversions.put("marigaon", "MORIGAON");
		explicitConversions.put("papumpara", "PAPUM PARE");
		explicitConversions.put("purnea", "Purnia");
		explicitConversions.put("kamrup metro", "KAMRUP");

		explicitConversions.put("n.a.lbari", "NALBARI");
		explicitConversions.put("east champaran", "PURBA CHAMPARAN");

		explicitConversions.put("jahanabad", "JEHANABAD");
		explicitConversions.put("monghyr", "MUNGER");
		explicitConversions.put("west champaran", "PASHCHIM CHAMPARAN");
		explicitConversions.put("kowardha", "KAWARDHA");
		explicitConversions.put("dadra & nagar haveli", "Dadra And Nagar Haveli");
		explicitConversions.put("broach", "Bharuch");
		explicitConversions.put("dahod", "Dohad");
		explicitConversions.put("junagarh", "Junagadh");
		explicitConversions.put("kutch", "Kachchh");
		explicitConversions.put("mehsana", "Mahesana");
		explicitConversions.put("panchmahal", "Panchmahals");
		explicitConversions.put("kulu", "Kullu");
		explicitConversions.put("sirmur", "SIRMAUR");
		explicitConversions.put("sirmour", "SIRMAUR");
		explicitConversions.put("north baramula", "BARAMULA");
		explicitConversions.put("palamau", "PALAMU");
		explicitConversions.put("west singbhum", "PASCHIM SINGHBHUMI");
		explicitConversions.put("west singhbhum", "PASCHIM SINGHBHUMI");
		explicitConversions.put("pashchimi singhbhum", "PASCHIM SINGHBHUMI");
		
		explicitConversions.put("belgam", "Belgaum");
		explicitConversions.put("alapuzha", "ALAPPUZHA");
		explicitConversions.put("kasargode", "KASARAGOD");
		explicitConversions.put("malapuram", "MALAPPURAM");
		explicitConversions.put("palakked", "PALAKKAD");
		explicitConversions.put("wynad", "WAYANAD");
		explicitConversions.put("chindwara", "CHHINDWARA");
		explicitConversions.put("amravati", "Amaravati");
		explicitConversions.put("amraoti", "Amaravati");
		explicitConversions.put("dhulie", "Dhule");
		explicitConversions.put("mumbai", "MUMBAI CITY");
		explicitConversions.put("nashik", "NASIK");
		explicitConversions.put("sholapur", "SOLAPUR");
		explicitConversions.put("churachandpur", "CHURACHANPUR");

		explicitConversions.put("imphal", "IMPHAL EAST");
		explicitConversions.put("balasore", "BALESHWAR");
		explicitConversions.put("deogarh", "DEBAGARH");

		explicitConversions.put("keonjhargarh", "KENDUJHAR");
		explicitConversions.put("khurda", "KHORDHA");
		explicitConversions.put("nawapara", "NUAPADA");
		explicitConversions.put("sonepur", "Sonapur");
		explicitConversions.put("bhatinda", "BATHINDA");
       explicitConversions.put("fateh garh", "FATEHGARH SAHIB");
		explicitConversions.put("sawai madhopur", "SWAIMADHOPUR");
		explicitConversions.put("nagapatinam", "Nagapattinam");
		explicitConversions.put("perambulur", "Perambalur");
		explicitConversions.put("pudukottai", "Pudukkottai");
		explicitConversions.put("tiruvannamalai", "Thiruvannamalai");
		explicitConversions.put("virudunagar", "Virudhunagar");
		explicitConversions.put("budaun", "BADAUN");
		explicitConversions.put("kanpur city", "KANPUR NAGAR");
		explicitConversions.put("raebareilly", "RAE BARELI");
		explicitConversions.put("shrawasti nagar", "SHRAWASTI");
		explicitConversions.put("siddharth nagar", "SIDDHARTHNAGAR");
		explicitConversions.put("burdwan", "BARDDHAMAN");
		explicitConversions.put("hooghly", "HUGLI");
		explicitConversions.put("south dinajpur", "SOUTH  DINAJPUR");
		explicitConversions.put("dhulie", "Dhule");
		explicitConversions.put("dhulie", "Dhule");
		explicitConversions.put("dhulie", "Dhule");
		explicitConversions.put("dhulie", "Dhule");

		explicitConversions.put("sheikhpura", "SEIKHPURA");
		explicitConversions.put("sheikhpura", "SHEIHKPURA");
		explicitConversions.put("baster", "BASTAR");
		explicitConversions.put("janjgir champa", "JANJGIR - CHAMPA");
		explicitConversions.put("janjgir-champa", "JANJGIR - CHAMPA");
		explicitConversions.put("hissar", "Hisar");
		explicitConversions.put("sonepat", "Sonipat");
		explicitConversions.put("una valley", "UNA");
		explicitConversions.put("baramulla", "BARAMULA");
		explicitConversions.put("rajouri", "Rajauri");
		explicitConversions.put("e-singhbhum", "PURBI SINGHBHUM");
		explicitConversions.put("east singhbhum", "PURBI SINGHBHUM");

		explicitConversions.put("koderma", "Kodarma");
		explicitConversions.put("hazaribagh", "HAZARIBAG");
		explicitConversions.put("pakur", "Pakaur");
		explicitConversions.put("sahebganj", "Sahibganj");

		explicitConversions.put("bagalkote", "Bagalkot");
		explicitConversions.put("chamrajnagara", "Chamarajanagar");
        explicitConversions.put("davanagere", "DAVANGERE");
        explicitConversions.put("uttara kannada", "Uttar Kannada");
        explicitConversions.put("kasaragod", "KASARGOD");
		explicitConversions.put("waynad", "WAYANAD");
		explicitConversions.put("narsinghpur", "NARSHIMAPURA");

        explicitConversions.put("buldana", "Buldhana");
		explicitConversions.put("sindhudurg", "Sindudurg");
		explicitConversions.put("yeotmal", "Yavatmal");
		explicitConversions.put("thoubal block", "THOUBAL");
		explicitConversions.put("churachandpur district", "CHURACHANPUR");
		explicitConversions.put("east khasi hills", "EAST KHASI HIL");
		explicitConversions.put("west garo hills", "WEST GARO HILL");
		explicitConversions.put("east garo hills", "EAST GORA HILL");
		explicitConversions.put("West Khasi Hills", "WEST KHASI HIL");
		explicitConversions.put("ri-bhoi", "Ri Bhoi");

		explicitConversions.put("south garo hills", "SOUTH GARO HILL");
		explicitConversions.put("angul", "ANUGUL");
		explicitConversions.put("bolangir", "BALANGIR");
		explicitConversions.put("boudh", "BAUDA");
		explicitConversions.put("baudh", "BAUDA");

		explicitConversions.put("jagatsinghpur", "JAGATSINGHAPUR");
		explicitConversions.put("jajpur", "JAJAPUR");
		explicitConversions.put("sundergarh", "SUNDARGARH");
		explicitConversions.put("fateh garh sahib", "FATEH GARH");
		explicitConversions.put("ferozepur", "FIROZPUR");
		explicitConversions.put("nawan shahr", "NAWASHAHR");
		explicitConversions.put("nawanshahar", "NAWASHAHR");
		explicitConversions.put("nawanshahr", "NAWASHAHR");

		explicitConversions.put("chittorgarh", "CHITTAURGARH");
		explicitConversions.put("dholpur", "DHAULPUR");
		explicitConversions.put("jalore", "JALOR");
		explicitConversions.put("jhunjhunu", "JHUNJHUNUN");

		explicitConversions.put("kancheepuram", "KANCHIPURAM");

		explicitConversions.put("kanniyakumari", "Kanyakumari");
		explicitConversions.put("nilgiri", "Nilgiris");
		explicitConversions.put("sivagangai", "Sivaganga");
		explicitConversions.put("thiruvallur", "Tiruvallur");
		explicitConversions.put("baghapat", "BAGHPAT");
		explicitConversions.put("basti.", "BASTI");
		explicitConversions.put("bulandshahar", "BULANDSAHAR");
		explicitConversions.put("bulandshahr", "BULANDSAHAR");
		explicitConversions.put("g.b. nagar", "GAUTAMBUDHNAGAR");
		explicitConversions.put("kaushambhi", "KAUSHAMBI");

		explicitConversions.put("kushi nagar", "KUSHINAGAR");
		explicitConversions.put("maharajganj", "MAHRAJGANJ");
		explicitConversions.put("raebarely", "RAE BARELI");
		explicitConversions.put("sant kabeer nagar", "SANTKABIRNAGAR");
		explicitConversions.put("shahjahnpur", "SHAHJAHANPUR");
		explicitConversions.put("shajahanpur", "SHAHJAHANPUR");

		explicitConversions.put("siddhrth nagar", "SIDDHARTHNAGAR");
        explicitConversions.put("maldah", "MALDA");
		explicitConversions.put("uttar dinajpur", "UTTAR_DINAJPUR");
		explicitConversions.put("north dinajpur", "UTTAR_DINAJPUR");
		explicitConversions.put("south  dinajpur", "DAKSHIN DINAJPUR");

		explicitConversions.put("howrah", "HAORA");
		explicitConversions.put("purulia", "PURULIYA");
		explicitConversions.put("darjeeling", "DARJILING");
		explicitConversions.put("hoogly", "HUGLI");
		explicitConversions.put("purba medi`pur", "EAST MEDINIPUR");
		explicitConversions.put("east midnapore", "EAST MEDINIPUR");
		explicitConversions.put("west midnapore", "PASHCHIM MEDINIPUR");
		explicitConversions.put("west medinipur", "PASHCHIM MEDINIPUR");
		explicitConversions.put("chandigarh ut", "Chandigarh");
		explicitConversions.put("ut of dadra & nagar haveli", "Dadra And Nagar Haveli");
		explicitConversions.put("ysr kadapa", "CUDDAPAH");
		explicitConversions.put("kadapa", "CUDDAPAH");
		explicitConversions.put("anantapur", "ANANTHAPUR");
		explicitConversions.put("sabarkantha", "SABAR KANTHA");
		explicitConversions.put("ramanagara", "RAMNAGAR");
		explicitConversions.put("kozhikode", "KOZIKOD");
		explicitConversions.put("trivandrum", "THIRUVANANTHPURAM");
		explicitConversions.put("thiruvananthapuram", "THIRUVANANTHPURAM");

		explicitConversions.put("thrissur", "TRISHUR");
		explicitConversions.put("neemuch", "NIMACH");
		explicitConversions.put("jaisalmer", "JAISELMER");
		explicitConversions.put("rajsamand", "RAJSMAND");
		explicitConversions.put("dharmapuri", "DHARAMPURI");
		explicitConversions.put("theni", "TENI");
		explicitConversions.put("thirunelveli", "TIRUNELVELI");
		explicitConversions.put("thiruvannamalai", "TIRUVANNAMALAI");
		explicitConversions.put("trichy", "TIRUCHIRAPPALLI");
		explicitConversions.put("tiruchy", "TIRUCHIRAPPALLI");
		explicitConversions.put("mahaboob-nagar", "MAHABUBNAGAR");
		explicitConversions.put("mahbubnagar", "MAHABUBNAGAR");
		explicitConversions.put("ambedkar nagar", "AMBEDKARNAGAR");
		explicitConversions.put("ghazipur", "GAZIPUR");
		explicitConversions.put("kanpur nagar", "KANPUR");
		explicitConversions.put("muzaffarnagar", "MUZAFARNAGAR");
		explicitConversions.put("raibareli", "RAIBEARELI");
		
		explicitConversions.put("raebareli", "RAIBEARELI");
		explicitConversions.put("murshidabad", "MURSIDABAD");
		explicitConversions.put("north 24-parganas", "NORTH 24 PRAGANA");
		explicitConversions.put("north 24 parganas", "NORTH 24 PRAGANA");
		
		explicitConversions.put("burdwan", "BARDAMAN");
		explicitConversions.put("barddhaman", "BARDAMAN");

		explicitConversions.put("purba medinipore", "EASTMEDNIPUR");
		explicitConversions.put("east medinipur", "EASTMEDNIPUR");
		
		explicitConversions.put("paschim medinipore", "PASHCHIM MEDINIPUR");
		explicitConversions.put("papum pare", "PAPUM-PARE");
		explicitConversions.put("karbi anglong", "KARBI ANALOG");

		explicitConversions.put("araria", "ARARIYA");
		explicitConversions.put("bhabhua", "BHABUA");
		explicitConversions.put("darbhanga", "DRABHANGA");
		explicitConversions.put("madhubani", "MADUBANI");
		explicitConversions.put("koriya", "KOREA");
		explicitConversions.put("dang", "DANGS");
		explicitConversions.put("khera", "KHEDA");
		explicitConversions.put("indaura", "INDAURA VALLEY");
		explicitConversions.put("nurpur", "NURPUR VALLEY");
		explicitConversions.put("balh", "BALH VALLEY");
		explicitConversions.put("paonta", "PAONTA VALLEY");
		explicitConversions.put("kala amb", "KALA AMB VALLEY");
		explicitConversions.put("nalagarh", "NALAGARH VALLEY");
		explicitConversions.put("hum", "HUM VALLEY");
		explicitConversions.put("poonch", "PUNCH");
		explicitConversions.put("leh", "LEH AND LADAKH");
		explicitConversions.put("deoghar", "DEVGARH");
		explicitConversions.put("godda", "GOODA");
		explicitConversions.put("saraikela - kharsawan", "SARAIKELA");
		explicitConversions.put("bangalore urban", "BANGLORE URBAN");
		explicitConversions.put("mandya", "MANDHYA");
		explicitConversions.put("kottayam", "KOTTYAM");
		explicitConversions.put("pathanamthitta", "PATTANAMITTIA");
		explicitConversions.put("anupur", "ANUPPUR");
		explicitConversions.put("vidisha", "VIDESHA");
		explicitConversions.put("ahmednagar", "AHMADNAGAR");
		explicitConversions.put("beed", "BID");
		explicitConversions.put("gondia", "GONDIYA");
		explicitConversions.put("raigad", "RAIGARH");
		explicitConversions.put("bishnupur block", "BISHNUPUR");
		explicitConversions.put("lawngtalai", "LAWNGTLAI");
		explicitConversions.put("serchipp", "SERCHHIP");
		explicitConversions.put("tuenchung", "TUENSANG");
		explicitConversions.put("bargarh", "BARAGARH");
		explicitConversions.put("gajapati", "GAJAPATHI");
		explicitConversions.put("kendrapara", "KENDRAPARHA");
		explicitConversions.put("keonjhar", "KENDUJHAR");
		explicitConversions.put("nuapada", "NUAPARHA");
		explicitConversions.put("rayagada", "RAYAGARHA");
		explicitConversions.put("mohali", "SAS NAGAR");
		explicitConversions.put("amroha", "JYOTIBAPHULE NAGAR");
		explicitConversions.put("hathras", "MAHAMAYANAGAR");
		explicitConversions.put("lakhimpur khiri", "LAKHIMPUR KHERI");
		explicitConversions.put("lucknow", "LACKNOW");
		explicitConversions.put("muzzafarnagar", "MUZAFARNAGAR");
		explicitConversions.put("pilibhit", "PILHIBHIT");
		explicitConversions.put("saharanpur", "SHARANPUR");
		explicitConversions.put("sant ravidas nagar", "SANTRAVIDASNAGAR");
		explicitConversions.put("nainital", "NANITAL");
		explicitConversions.put("coochbehar", "KOCHBIHAR");
		explicitConversions.put("andaman & nicobar", "ANDAMAN NICOBAR");
		explicitConversions.put("chandigarh ut (ham)", "CHANDIGARH");
		explicitConversions.put("ut of dadra & nagar haveli", "DADAR & NAGAR HAVELI");
		explicitConversions.put("rangareddy & hyderabad", "RANGA REDDY");
		explicitConversions.put("dima hasao", "N.C HILLS");
		explicitConversions.put("coochbehar", "KOCHBIHAR");
		explicitConversions.put("east", "EAST DELHI");
		explicitConversions.put("north", "NORTH DELHI");
		explicitConversions.put("north west", "NORTH WEST DELHI");
		explicitConversions.put("ahmedabad", "AHMADABAD");
		explicitConversions.put("narsimhapur", "NARSHIMAPURA");

		explicitConversions.put("mumbai suburban", "SUBURBAN MUMBAI");

	}

	public static String getOverriddenNameIfApplicable(String name) {
		if (explicitConversions.containsKey(name.toLowerCase())) {
			return explicitConversions.get(name.toLowerCase()).toUpperCase();
		}

		return name;

	}

	public static List<String> getReverseOverriddenNameIfApplicable(String name) {
		List<String> list = Lists.newArrayList();
		for (Map.Entry<String, String> entry : explicitConversions.entrySet()) {
			if (entry.getValue().equalsIgnoreCase(name)) {
				list.add(entry.getKey());
			}
		}
		if (list.isEmpty()) {
			list.add(name);
		}
		return list;

	}

	public static final Set<String> getStatePlusDistrictKey(Map<String, SiteInfo> map) {
		Set<String> set = new HashSet<String>();
		for (Map.Entry<String, SiteInfo> entry : map.entrySet()) {
			final SiteInfo siteInfoImpl = entry.getValue();
			set.add(siteInfoImpl.getState().getName() + UNDERSCORE + siteInfoImpl.getDistrict());
		}
		return set;
	}

	public static String getBestMatchDistrictName(final String stateName, final String districtName,
			Set<String> allKeys) {
		boolean districtFoundInSiteData = false;
		for (final String k : allKeys) {
			String[] tokens = StringUtils.split(k, UNDERSCORE);
			String mapKeyState = tokens[0].trim();
			String mapKeyDistrict = tokens[1].trim();

			if (StringUtils.equalsIgnoreCase(mapKeyState, stateName)) {
				if (StringUtils.equalsIgnoreCase(mapKeyDistrict, districtName)) {
					districtFoundInSiteData = true;
					return StringUtils.capitalize(mapKeyDistrict.toLowerCase());
				}
			}
		}
		if (!districtFoundInSiteData) {
			if (districtName.trim().equalsIgnoreCase("Chittoor")) {
				System.out.println("ye");
			}
			System.err.println("This district is not found in site info sheet, still treating it as valid: "
					+ districtName + " in state: " + stateName);
		}
		return StringUtils.capitalize(districtName.toLowerCase());
	}
}
