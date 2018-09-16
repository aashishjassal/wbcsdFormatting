package com.wbcsd.fileGenerator.application.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

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
        explicitConversions.put("puducherry", "Pondicherry");
        explicitConversions.put("mehaboobnagar", "MAHBUBNAGAR");
        explicitConversions.put("mehboobnagar", "MAHBUBNAGAR");
        explicitConversions.put("mehbubnagar", "MAHBUBNAGAR");

        explicitConversions.put("chittor", "Chittoor");
        explicitConversions.put("vishakhapatnam", "VISAKHAPATNAM");
        explicitConversions.put("east  godavari", "EAST GODAVARI");
        explicitConversions.put("morigaon", "MARIGAON");
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
        explicitConversions.put("north baramula", "BARAMULA");
        explicitConversions.put("palamau", "PALAMU");
        explicitConversions.put("west singbhum", "PASHCHIMI SINGHBHUM");
        explicitConversions.put("belgam", "Belgaum");
        explicitConversions.put("alapuzha", "ALAPPUZHA");
        explicitConversions.put("kasargode", "KASARAGOD");
        explicitConversions.put("malapuram", "MALAPPURAM");
        explicitConversions.put("palakked", "PALAKKAD");
        explicitConversions.put("wynad", "WAYANAD");
        explicitConversions.put("chindwara", "CHHINDWARA");
        explicitConversions.put("amraoti", "Amravati");
        explicitConversions.put("dhulie", "Dhule");
        explicitConversions.put("mumbai", "MUMBAI CITY");
        explicitConversions.put("nasik", "Nashik");
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
        explicitConversions.put("fatehgarh sahib", "FATEH GARH");
        explicitConversions.put("Sawaimadhopur", "SAWAI MADHOPUR");
        explicitConversions.put("nagapatinam", "Nagapattinam");
        explicitConversions.put("perambulur", "Perambalur");
        explicitConversions.put("pudukottai", "Pudukkottai");
        explicitConversions.put("tiruvannamalai", "Thiruvannamalai");
        explicitConversions.put("virudunagar", "Virudhunagar");
        explicitConversions.put("badaun", "BUDAUN");
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
        explicitConversions.put("baster", "BASTAR");
        explicitConversions.put("janjgir champa", "JANJGIR - CHAMPA");

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
        explicitConversions.put("davangere", "Davanagere");
        explicitConversions.put("uttar kannada", "Uttara Kannada");
        explicitConversions.put("kasargod", "KASARAGOD");
        explicitConversions.put("waynad", "WAYANAD");
        explicitConversions.put("narsinghpur", "NARSIMHAPUR");

        explicitConversions.put("buldhana", "Buldana");
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
        explicitConversions.put("boudh", "Baudh");
        explicitConversions.put("jagatsinghpur", "JAGATSINGHAPUR");
        explicitConversions.put("jajpur", "JAJAPUR");
        explicitConversions.put("sundergarh", "SUNDARGARH");
        explicitConversions.put("fateh garh sahib", "FATEH GARH");
        explicitConversions.put("ferozepur", "FIROZPUR");
        explicitConversions.put("nawan shahr", "NAWANSHAHR");
        explicitConversions.put("Nawanshahar", "NAWANSHAHR");

        explicitConversions.put("chittorgarh", "CHITTAURGARH");
        explicitConversions.put("dholpur", "DHAULPUR");
        explicitConversions.put("kanchipuram", "Kancheepuram");
        explicitConversions.put("kanniyakumari", "Kanyakumari");
        explicitConversions.put("nilgiri", "Nilgiris");
        explicitConversions.put("sivagangai", "Sivaganga");
        explicitConversions.put("thiruvallur", "Tiruvallur");
        explicitConversions.put("baghapat", "BAGHPAT");
        explicitConversions.put("basti.", "BASTI");
        explicitConversions.put("bulandshahar", "BULANDSHAHR");
        explicitConversions.put("g.b.nagar", "GAUTAM BUDDHA NAGAR");
        explicitConversions.put("kaushambhi", "KAUSHAMBI");

        explicitConversions.put("kushi nagar", "KUSHINAGAR");
        explicitConversions.put("maharajganj", "MAHRAJGANJ");
        explicitConversions.put("raebarely", "RAE BARELI");
        explicitConversions.put("sant kabeer nagar", "SANT KABIR NAGAR");
        explicitConversions.put("shahjahnpur", "SHAHJAHANPUR");
        explicitConversions.put("siddhrth nagar", "SIDDHARTHNAGAR");
        explicitConversions.put("malda", "Maldah");
        explicitConversions.put("uttar dinajpur", "NORTH DINAJPUR");
        explicitConversions.put("dakshin dinajpur", "SOUTH DINAJPUR");

        explicitConversions.put("howrah", "HAORA");
        explicitConversions.put("purulia", "PURULIYA");
        explicitConversions.put("darjeeling", "DARJILING");
        explicitConversions.put("hoogly", "HUGLI");
        explicitConversions.put("purba medinipur", "EAST MEDINIPUR");
        explicitConversions.put("east midnapore", "EAST MEDINIPUR");
        explicitConversions.put("west midnapore", "WEST MEDINIPUR");
        explicitConversions.put("paschim medinipur", "WEST MEDINIPUR");
        explicitConversions.put("chandigarh ut", "Chandigarh");
        explicitConversions.put("ut of dadra & nagar haveli", "Dadra And Nagar Haveli");

    }

    public static String getOverriddenNameIfApplicable(String name) {
        if (explicitConversions.containsKey(name.toLowerCase())) {
            return explicitConversions.get(name.toLowerCase());
        }

        return name;

    }

    public static final Set<String> getStatePlusDistrictKey(Map<String, SiteInfo> map) {
        Set<String> set = new HashSet<String>();
        for (Map.Entry<String, SiteInfo> entry : map.entrySet()) {
            final SiteInfo siteInfoImpl = entry.getValue();
            set.add(siteInfoImpl.getState().getName() + UNDERSCORE + siteInfoImpl.getDistrict());
        }
        return set;
    }

    public static String getBestMatchDistrictName(final String stateName, final String districtName, Set<String> allKeys) {
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
