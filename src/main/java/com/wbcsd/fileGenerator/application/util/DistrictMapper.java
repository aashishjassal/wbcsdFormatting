package com.wbcsd.fileGenerator.application.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Aashish
 * @version $Id: $
 */
public class DistrictMapper {

    private static final Map<String, String> explicitConversions = new HashMap<String, String>();
    static {
        explicitConversions.put("MURSHIDABAD", "MURSIDABAD");
        explicitConversions.put("NORTH 24 PARGANAS", "NORTH 24 PRAGANA");
        explicitConversions.put("NORTH DINAJPUR", "UTTAR_DINAJPUR");
        explicitConversions.put("SOUTH  DINAJPUR", "DAKSHIN DINAJPUR");
        explicitConversions.put("SOUTH DINAJPUR", "DAKSHIN DINAJPUR");
        explicitConversions.put("WEST MEDINIPUR", "PASHCHIM MEDINIPUR");
        explicitConversions.put("THRISSUR", "TRISHUR");
        explicitConversions.put("FATEH GARH", "FATEHGARH SAHIB");
        explicitConversions.put("NAWANSHAHR", "NAWASHAHR");
        explicitConversions.put("NAWANSHAHAR", "NAWASHAHR");
        explicitConversions.put("JALORE", "JALOR");

        explicitConversions.put("ANANTAPUR", "ANANTHAPUR");
        explicitConversions.put("MAHBUBNAGAR", "MAHABUBNAGAR");
        explicitConversions.put("VISAKHAPATNAM", "VISHAKHAPATNAM");
        explicitConversions.put("PAPUM PARE", "PAPUM-PARE");
        explicitConversions.put("KARBI ANGLONG", "KARBI ANALOG");
        explicitConversions.put("MARIGAON", "MORIGAON");
        explicitConversions.put("ARARIA", "ARARIYA");
        explicitConversions.put("DARBHANGA", "DRABHANGA");
        explicitConversions.put("JEHANABAD", "JAHANABAD");
        explicitConversions.put("MADHUBANI", "MADUBANI");
        explicitConversions.put("PASHCHIM CHAMPARAN", "PACHIM CHAMPARAN");
        explicitConversions.put("E.CHAMPARAN", "PURBA CHAMPARAN");
        explicitConversions.put("SEIKHPURA", "SHEIKHPURA");
        explicitConversions.put("JANJGIR - CHAMPA", "JANJGIR_CHAMPA");
        explicitConversions.put("KORIYA", "KOREA");
        explicitConversions.put("CENTRAL", "CENTRAL DELHI");
        explicitConversions.put("EAST", "EAST DELHI");
        explicitConversions.put("NORTH", "NORTH DELHI");
        explicitConversions.put("NORTH EAST", "NORTH EAST DELHI");
        explicitConversions.put("NORTH WEST", "NORTH WEST DELHI");
        explicitConversions.put("SOUTH", "SOUTH DELHI");
        explicitConversions.put("SOUTH WEST", "SOUTH WEST DELHI");

        explicitConversions.put("WEST", "WEST DELHI");
        explicitConversions.put("AHMEDABAD", "AHMADABAD");
        explicitConversions.put("DOHAD", "DAHOD");
        explicitConversions.put("JUNAGADH", "JUNAGARH");
        explicitConversions.put("KHEDA", "KHERA");
        explicitConversions.put("PANCHMAHALS", "PANCHMAHAL");
        explicitConversions.put("SABARKANTHA", "SABAR KANTHA");
        explicitConversions.put("DEOGHAR", "DEVGARH");
        explicitConversions.put("GODDA", "GOODA");
        explicitConversions.put("HAZARIBAG", "HAZARIBAGH");
        explicitConversions.put("PAKAUR", "PAKUR");
        explicitConversions.put("PASHCHIMI SINGHBHUM", "PASCHIM SINGHBHUMI");
        explicitConversions.put("W-SINGHBHUM", "PASCHIM SINGHBHUMI");
        explicitConversions.put("SAHIBGANJ", "SAHEBGANJ");
        explicitConversions.put("BANGALORE URBAN", "BANGLORE URBAN");
        explicitConversions.put("DAVANAGERE", "DAVANGERE");
        explicitConversions.put("MANDYA", "MANDHYA");
        explicitConversions.put("UTTARA KANNADA", "UTTAR KANNADA");
        explicitConversions.put("KASARAGOD", "KASARGOD");
        explicitConversions.put("KOTTAYAM", "KOTTYAM");
        explicitConversions.put("KOZHIKODE", "KOZIKOD");
        explicitConversions.put("PATHANAMTHITTA", "PATTANAMITTIA");
        explicitConversions.put("THIRUVANANTHAPURAM", "THIRUVANANTHPURAM");
        explicitConversions.put("NARSIMHAPUR", "NARSHIMAPURA");
        explicitConversions.put("NEEMUCH", "NIMACH");
        explicitConversions.put("VIDISHA", "VIDESHA");
        explicitConversions.put("AHMEDNAGAR", "AHMADNAGAR");
        explicitConversions.put("AMRAVATI", "AMARAVATI");
        explicitConversions.put("BEED", "BID");
        explicitConversions.put("BULDANA", "BULDHANA");
        explicitConversions.put("GONDIA", "GONDIYA");
        explicitConversions.put("MUMBAI SUBURBAN", "SUBURBAN MUMBAI");
        explicitConversions.put("NASHIK", "NASIK");
        explicitConversions.put("RAIGAD", "RAIGARH");
        explicitConversions.put("SINDUDURG", "SINDHUDURG");
        explicitConversions.put("EAST GORA HILL", "EAST GARO HILLS");
        explicitConversions.put("SOUTH GARO HILL", "SOUTH GARO HILLS");
        explicitConversions.put("WEST GARO HILL", "WEST GARO HILLS");
        explicitConversions.put("BARGARH", "BARAGARH");
        explicitConversions.put("BAUDH", "BAUDA");
        explicitConversions.put("DEBAGARH", "DEOGARH");
        explicitConversions.put("GAJAPATI", "GAJAPATHI");
        explicitConversions.put("KENDRAPARA", "KENDRAPARHA");
        explicitConversions.put("NUAPADA", "NUAPARHA");
        explicitConversions.put("RAYAGADA", "RAYAGARHA");
        explicitConversions.put("PONDICHERRY", "PUDUCHERY");
        explicitConversions.put("DHAULPUR", "DHOLPUR");
        explicitConversions.put("JAISALMER", "JAISELMER");
        explicitConversions.put("JHUNJHUNU", "JHUNJHUNUN");
        explicitConversions.put("RAJSAMAND", "RAJSMAND");
        explicitConversions.put("SAWAI MADHOPUR", "SWAIMADHOPUR");
        explicitConversions.put("SAWAIMADHOPUR", "SWAIMADHOPUR");
        explicitConversions.put("DHARMAPURI", "DHARAMPURI");
        explicitConversions.put("KANCHEEPURAM", "KANCHIPURAM");
        explicitConversions.put("NILGIRIS", "NILGIRI");
        explicitConversions.put("THENI", "TENI");
        explicitConversions.put("THIRUVANNAMALAI", "TIRUVANNAMALAI");
        explicitConversions.put("TIRUVARUR", "THIRUVARUR");
        explicitConversions.put("AMBEDKAR NAGAR", "AMBEDKARNAGAR");
        explicitConversions.put("BUDAUN", "BADAUN");
        explicitConversions.put("BULANDSHAHR", "BULANDSAHAR");
        explicitConversions.put("GAUTAM BUDDHA NAGAR", "GAUTAMBUDHNAGAR");
        explicitConversions.put("G B NAGAR", "GAUTAMBUDHNAGAR");
        explicitConversions.put("GHAZIPUR", "GAZIPUR");
        explicitConversions.put("JYOTIBA PHULE NAGAR", "JYOTIBAPHULE NAGAR");
        explicitConversions.put("KANPUR NAGAR", "KANPUR");
        explicitConversions.put("LUCKNOW", "LACKNOW");
        explicitConversions.put("MAHRAJGANJ", "MAHARAJGANJ");
        explicitConversions.put("MUZAFFARNAGAR", "MUZAFARNAGAR");
        explicitConversions.put("PILIBHIT", "PILHIBHIT");
        explicitConversions.put("RAE BARELI", "RAIBEARELI");
        explicitConversions.put("SAHARANPUR", "SHARANPUR");
        explicitConversions.put("SANT KABIR NAGAR", "SANTKABIRNAGAR");
        explicitConversions.put("SANT RAVIDAS NAGAR", "SANTRAVIDASNAGAR");
        explicitConversions.put("ST. RAVIDAS NAGAR", "SANTRAVIDASNAGAR");
        explicitConversions.put("HARDWAR", "HARIDWAR");
        explicitConversions.put("NAINITAL", "NANITAL");
        explicitConversions.put("BARDDHAMAN", "BARDAMAN");
        explicitConversions.put("DARJILING", "DARJEELING");
        explicitConversions.put("EAST MEDINIPUR", "EASTMEDNIPUR");
        explicitConversions.put("MALDAH", "MALDA");

        explicitConversions.put("EAST KHASI HIL", "EAST KHASI HILLS");

        explicitConversions.put("EASE GODAVARI", "EAST GODAVARI");
        explicitConversions.put("KADAPA", "CUDDAPAH");
        explicitConversions.put("VIJAYNAGARAM", "VIZIANAGARAM");
        explicitConversions.put("POBANDAR", "PORBANDAR");
        explicitConversions.put("SIRMOUR", "SIRMAUR");
        explicitConversions.put("RAMANAGARAM", "RAMNAGAR");
        explicitConversions.put("RAMANAGARA", "RAMNAGAR");
        explicitConversions.put("TRICHUR", "TRISHUR");
        explicitConversions.put("TRIVENDUM", "THIRUVANANTHPURAM");
        explicitConversions.put("BUHANPUR", "BURHANPUR");
        explicitConversions.put("CHURACHANPUR", "CHURACHANDPUR");
        explicitConversions.put("WEST KHASI HIL", "WEST KHASI HILLS");
        explicitConversions.put("UT OF PUDUCHERRY", "PUDUCHERY");
        explicitConversions.put("TIRUVALLUVAR", "TIRUVALLUR");
        explicitConversions.put("BULAND SAHAR", "BULANDSAHAR");
        explicitConversions.put("CHITRAKOOT DHAM", "CHITRAKOOT");
        explicitConversions.put("HATHRAS (MAHAMAYA NAGAR)", "MAHAMAYANAGAR");
        explicitConversions.put("MAHAMAYA NAGAR", "MAHAMAYANAGAR");
        explicitConversions.put("HATHRAS", "MAHAMAYANAGAR");
        explicitConversions.put("J P NAGAR", "JYOTIBAPHULE NAGAR");
        explicitConversions.put("AMROHA", "JYOTIBAPHULE NAGAR");
        explicitConversions.put("AMROHA (J P NAGAR)", "JYOTIBAPHULE NAGAR");
        explicitConversions.put("KASHIRAM NAGAR", "KANSHIRAMNAGAR");
        explicitConversions.put("KANSHIRAM NAGAR", "KANSHIRAMNAGAR");
        explicitConversions.put("MANIPRI", "MAINPURI");
        explicitConversions.put("MUZAFFAR NAGAR", "MUZAFARNAGAR");
        explicitConversions.put("RAIBARELI", "RAIBEARELI");
        explicitConversions.put("UDAMSINGH NAGAR", "UDHAM SINGH NAGAR");
        explicitConversions.put("MEDINIPUR (E)", "EASTMEDNIPUR");
        explicitConversions.put("MEDINIPUR (W)", "PASHCHIM MEDINIPUR");
        explicitConversions.put("RANGAREDDY", "RANGA REDDY");
        explicitConversions.put("N.C. HILLS", "N.C HILLS");
        explicitConversions.put("BHABHUA", "BHABUA");
        explicitConversions.put("DANG", "DANGS");
        explicitConversions.put("LEH", "LEH AND LADAKH");
        explicitConversions.put("ANNUPUR", "ANUPPUR");
        explicitConversions.put("BISHNUPUR DISTRICT", "BISHNUPUR");
        explicitConversions.put("LAWNGTALAI", "LAWNGTLAI");
        explicitConversions.put("SERCHIPP", "SERCHHIP");

        explicitConversions.put("KEONJHAR", "KENDUJHAR");
        explicitConversions.put("TIRVANNAMALAI", "TIRUVANNAMALAI");
        explicitConversions.put("KAMRUP METRO.", "KAMRUP");
        explicitConversions.put("N. C. HILLS", "N.C HILLS");
        explicitConversions.put("NOWGONG", "NAGAON");
        explicitConversions.put("JANJGIR", "JANJGIR_CHAMPA");
        explicitConversions.put("BARODA", "VADODARA");
        explicitConversions.put("LAHAUL AND SPITI", "LAHUL&SPITI");
        explicitConversions.put("SIMLA", "SHIMLA");
        explicitConversions.put("LADAKH (LEH)", "LEH AND LADAKH");
        explicitConversions.put("POONCH", "PUNCH");
        explicitConversions.put("REASI", "RIASI");
        explicitConversions.put("EAST SINGBHUM", "PURBI SINGHBHUM");
        explicitConversions.put("CHICKBALLAPUR", "CHIKBALLAPUR");
        explicitConversions.put("CANNUR", "KANNUR");
        explicitConversions.put("AIZWAL", "AIZAWL");
        explicitConversions.put("KEPHIRE", "KIPHIRE");
        explicitConversions.put("PAREN", "PEREN");
        explicitConversions.put("NAWARANGPUR", "NABARANGAPUR");

        explicitConversions.put("MOHALI", "SAS NAGAR");
        explicitConversions.put("SAS NAGAR MOHALI", "SAS NAGAR");
        explicitConversions.put("EAST SIKKIM", "EAST");
        explicitConversions.put("NORTH SIKKIM", "NORTH");
        explicitConversions.put("SOUTH SIKKIM", "SOUTH");
        explicitConversions.put("WEST SIKKIM", "WEST");
        explicitConversions.put("TIRUPUR", "TIRUPPUR");
        explicitConversions.put("GARHWAL PAURI", "PAURI GARHWAL");
        explicitConversions.put("GARHWAL TEHRI", "TEHRI GARWAL");
        explicitConversions.put("COOCH BEHAR", "KOCHBIHAR");
        explicitConversions.put("FEROZPUR", "FIROZPUR");
        explicitConversions.put("ANGUL TALCHER", "ANUGUL");
        explicitConversions.put("ANKLESHWAR", "BHARUCH");
        explicitConversions.put("ASANSOLE", "BARDAMAN");
        explicitConversions.put("BHADRAVATHI", "SHIMOGA");
        explicitConversions.put("DOMBIVALLI", "THANE");
        explicitConversions.put("HALDIA", "EASTMEDNIPUR");
        explicitConversions.put("IB VALLEY", "JHARSUGUDA");
        explicitConversions.put("MANALI", "TIRUVALLUR");
        explicitConversions.put("MANDI GOBINDGARH", "FATEHGARH SAHIB");
        explicitConversions.put("MANGALORE", "DAKSHIN KANNADA");
        explicitConversions.put("NAVI MUMBAI", "MUMBAI CITY");
        explicitConversions.put("NOIDA", "GAUTAMBUDHNAGAR");
        explicitConversions.put("PATANCHERU & BOLLARAM", "MEDAK");
        explicitConversions.put("TARAPUR", "THANE");
        explicitConversions.put("VAPI", "VALSAD");
        explicitConversions.put("VATVA", "AHMADABAD");
        explicitConversions.put("TRICHY", "TIRUCHIRAPPALLI");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
        explicitConversions.put("SERCHIPP", "SERCHHIP");
    }

    public static String getOverriddenNameIfApplicable(String state, String districtName, List<String> masterDistricts) {
        if (explicitConversions.containsKey(districtName)) {
            return explicitConversions.get(districtName);
        }
        for (String masterDist : masterDistricts) {
            if (StringUtils.endsWithIgnoreCase(districtName, masterDist)) {
                return districtName.toUpperCase();
            }
        }
        System.err.println("In state: " + state + " district: " + districtName + " not found in masterDistricts: "
                + masterDistricts);
        return districtName;

    }

}
