package com.wbcsd.fileGenerator.application.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Aashish
 * @version $Id: $
 */
public class SiteInfoUtils {

	private static final Map<String, String> explicitConversions = new HashMap<String, String>();

	static {
		explicitConversions.put("Gandhinagr", "Gandhinagar");
		explicitConversions.put("Sarkarsammakulam", "Sarkar samakulam");
		explicitConversions.put("Katretikona", "KATRENIKONA");
		explicitConversions.put("Malkipuram", "MALIKIPURAM");

		explicitConversions.put("Mamadikuduru", "mamidikuduru");
		explicitConversions.put("Sakhinetipalli", "sakhinetipalle");
		explicitConversions.put("TNarsapuram", "narasapur");
		explicitConversions.put("Akiveedu", "akividu");
		explicitConversions.put("Mogalturu", "mogalturru");
		explicitConversions.put("Nandiwada", "nandigama");
		explicitConversions.put("Kruthivennu", "kruttivennu");
		explicitConversions.put("Bantumilli", "bantummilli");
		explicitConversions.put("Mudinepalli", "mudinepalle");
		explicitConversions.put("Bollapalle", "bollapalli");
		explicitConversions.put("Bestavaripeta", "bestavarapupeta");
		explicitConversions.put("Baireddypalli", "baireddipalle");
		explicitConversions.put("Gudupalli", "gudipalli");
		explicitConversions.put("Piler", "pileru");
		explicitConversions.put("Karvetinagaram", "karvetnagar");
		explicitConversions.put("Madanapalli", "madanapalle");
		explicitConversions.put("Thavanampalli", "thavanampalle");
		explicitConversions.put("Molakalacheruvu", "mulakalacheruvu");
		explicitConversions.put("PeddaThippaSamudram", "pedda tippa samudram");
		explicitConversions.put("Punganur", "pungunuru");
		explicitConversions.put("Yadamarri", "yadamari");
		explicitConversions.put("Pulivendula", "pulivendla");
		explicitConversions.put("Rayachoty", "rayachoti");
		explicitConversions.put("Vempalle", "vempalli");
		explicitConversions.put("Atmakur", "atmakuru");
		explicitConversions.put("Amadugu", "amadagur");
		explicitConversions.put("Chilmatur", "chilamathur");
		explicitConversions.put("Kanaganapalli", "kanaganapalle");
		explicitConversions.put("Bathalapalli", "bathalapalle");
		explicitConversions.put("Bramhasamudram", "brahmasamudram");
		explicitConversions.put("Roddam", "ruddam");
		explicitConversions.put("Gorantla", "gorentla");
		explicitConversions.put("Somandapalle", "somandepalle");
		explicitConversions.put("Puttaparthi", "puttaparti");
		explicitConversions.put("Settur", "setturu");
		explicitConversions.put("Peddakadabur", "peddakadaburu");
		explicitConversions.put("GayaSadar", "gaya town");
		explicitConversions.put("Mushari", "mushahari");
		explicitConversions.put("Dhamdha", "dhamda");
		explicitConversions.put("Malkhroda", "malkhurada");
		explicitConversions.put("Pandariya", "pandaria");
		explicitConversions.put("Detroj-Rampura", "detroj rampura");
		explicitConversions.put("Dhanduka", "dhandhuka");
		explicitConversions.put("Bhabar", "bhabhar");
		explicitConversions.put("Dhanera", "dhanner");
		explicitConversions.put("Bechraji", "becharaji");
		explicitConversions.put("Satlasna", "satlasana");
		explicitConversions.put("Siddhpur", "siddhapur");
		explicitConversions.put("Santhalpur", "santalpur");
		explicitConversions.put("Wadali", "vadali");
		explicitConversions.put("Naraingarh", "narayangarh");
		explicitConversions.put("BhawaniKhera", "bhiwani");
		explicitConversions.put("Ballabhgarh", "ballabgarh");
		explicitConversions.put("Shahbad", "shahabad");
		explicitConversions.put("Thaneswar", "thanesar");
		explicitConversions.put("Chandrapura", "chandankiyari");
		explicitConversions.put("Bailahongal", "bailhongal");
		explicitConversions.put("Saundatti", "savadatti");
		explicitConversions.put("Yelandur", "yalandur");
		explicitConversions.put("Sidlaghata", "sidlaghatta");
		explicitConversions.put("Ranibennur", "ranibennuru");
		explicitConversions.put("Bangarpet", "bangarapet");
		explicitConversions.put("Srinivaspur", "srinivasapura");
		explicitConversions.put("Lingsugur", "linga sugur (mudgal)");
		explicitConversions.put("Channapatana", "channapatna");
		explicitConversions.put("Kasaragod", "kasargod");
		explicitConversions.put("Mathilakom", "mathilakam");
		explicitConversions.put("Barod", "baroud");
		explicitConversions.put("Thikari", "thikri");
		explicitConversions.put("Nowgaon", "nowgoan");
		explicitConversions.put("Patheriya", "patharia");
		explicitConversions.put("Khategaon", "khategoan");
		explicitConversions.put("Sonkutch", "sonkach");
		explicitConversions.put("Gotegaon", "gotecaon");
		explicitConversions.put("Narsinghpur", "narsimhapur");
		explicitConversions.put("Ajaygarh", "ajaigarh");
		explicitConversions.put("Alote", "alotot");
		explicitConversions.put("Sirmour", "sirmaur");
		explicitConversions.put("Khanniyadhana", "khaniyadhan");
		explicitConversions.put("Pichor", "pichhor");
		explicitConversions.put("Badnagar", "barnagar");
		explicitConversions.put("Mahidpur", "mehidpur");
		explicitConversions.put("Ghatia", "ghatiya");
		explicitConversions.put("Chandwad", "chandvad");
		explicitConversions.put("Khatav", "khatao");
		explicitConversions.put("Malshiras", "malsiras");
		explicitConversions.put("Chandbali", "chandabali");
		explicitConversions.put("BassiPathana", "bassi pathanan");
		explicitConversions.put("Khamanon", "khamanoh");
		explicitConversions.put("Ferozpur", "firozpur");
		explicitConversions.put("NawanShahr", "nawashahr");
		explicitConversions.put("KhadurSahib", "khadoor sahib");
		explicitConversions.put("Bhinai", "bhinay");
		explicitConversions.put("Behror", "bahror");
		explicitConversions.put("Kishanganj", "kishangunj");
		explicitConversions.put("Sindhari", "sindhri");
		explicitConversions.put("Jahazpur", "jahajpur");
		explicitConversions.put("Bhainsrogarh", "bhainsrorgarh");
		explicitConversions.put("Sikrai", "sakrai");
		explicitConversions.put("Sambhar", "sambher");
		explicitConversions.put("Jaisalmer", "jaiselmer");
		explicitConversions.put("Mandor", "mandore");
		explicitConversions.put("Ladnu", "ladnun");
		explicitConversions.put("Parbatsar", "parvatsar");
		explicitConversions.put("Dhariyabad", "dhariyawad");
		explicitConversions.put("DantaRamgarh", "danta ramgarg");
		explicitConversions.put("Khandela", "khandella");
		explicitConversions.put("Lachhmangarh", "lachhmangarg");
		explicitConversions.put("Bhindar", "bhinder");
		explicitConversions.put("Salumbar", "salumber");
		explicitConversions.put("Egmore-Nungambakkam--I", "egmore nungambakkam");
		explicitConversions.put("Egmore-Nungambakkam--Ii", "egmore nungambakkam");
		explicitConversions.put("Egmore-Nungambakkam--Iii", "egmore nungambakkam");
		explicitConversions.put("Egmore-Nungambakkam-Iv", "egmore nungambakkam");
		explicitConversions.put("Mambalam-Guindy-I", "mambalam guindy");
		explicitConversions.put("Mambalam-Guindy-Ii", "mambalam guindy");
		explicitConversions.put("Mambalam-Guindy-Iii", "mambalam guindy");
		explicitConversions.put("Mambalam-Guindy-Iv", "mambalam guindy");
		explicitConversions.put("Mylapore-Tiruvallikeni--I", "mylapore tiruvallikkeni");
		explicitConversions.put("Mylapore-Tiruvallikeni--Ii", "mylapore tiruvallikkeni");
		explicitConversions.put("Mylapore-Tiruvallikeni--Iii", "mylapore tiruvallikkeni");
		explicitConversions.put("Mylapore-Tiruvallikeni--Iv", "mylapore tiruvallikkeni");
		explicitConversions.put("Madukkarai", "madukarai");
		explicitConversions.put("Kinathukatavu", "kinathukodavu");
		explicitConversions.put("Tittagudi(E)", "tittakudi");
		explicitConversions.put("Virudhachalam(S)", "vriddhachalam");
		explicitConversions.put("Pappireddipatty", "papparapatty");
		explicitConversions.put("Palacode", "palakodu");
		explicitConversions.put("Palacode", "palayam");
		explicitConversions.put("Oddanchathram", "oddanchattram");
		explicitConversions.put("Reddiarchatram", "reddiapatti");
		explicitConversions.put("Reddiarchatram", "reddiarchattiram");
		explicitConversions.put("Vatlagundu", "batlagundu");
		explicitConversions.put("Uthiramerur", "uttiramerur");
		explicitConversions.put("Uthangarai", "uttangarai");
		explicitConversions.put("Kalligudi", "kallikudi");
		explicitConversions.put("Mayiladuthurai", "mayiladuturai");
		explicitConversions.put("Nagappattinam", "nagapattinam");
		explicitConversions.put("Erumaipatti", "erumapatti");
		explicitConversions.put("Tiruchengode", "tiruchengodu");
		explicitConversions.put("Paramathi", "paramathivelur");
		explicitConversions.put("Thiruvidamarudur", "thiruvidai marudur");
		explicitConversions.put("Alankulam", "alanguam");
		explicitConversions.put("Veerakeralampudur", "veerasigamani");
		explicitConversions.put("Sankarankoil", "sankarankovil");
		explicitConversions.put("Gummidipoondi", "gummidipundi");
		explicitConversions.put("Uthukkottai", "uthukottai");
		explicitConversions.put("Thandarampat", "thandarampattu");
		explicitConversions.put("Avinashi(E)", "avanashi");
		explicitConversions.put("Avinashi(W)", "avinashipalayam(s)");
		explicitConversions.put("Udumalpet", "udumalaippettai");
		explicitConversions.put("Kangeyam", "kangayam");
		explicitConversions.put("Arakonam(South)", "arakkonam");
		explicitConversions.put("Kandhili", "kandili");
		explicitConversions.put("Kaniyambadi", "vaniyambadi");
		explicitConversions.put("Gudiyatham(West)", "gudiyattam");
		explicitConversions.put("Gudiyatham(East)", "gudiyattam");
		explicitConversions.put("Walajah", "walajapet");
		explicitConversions.put("Tirupathur", "tiruppattur");
		explicitConversions.put("Chinnaselam", "chinnasalem");
		explicitConversions.put("Kallakurichi", "kallakurichchi");
		explicitConversions.put("Thirukoilur", "tirukovilur");
		explicitConversions.put("Melmalayanur", "melmalaiyanur");
		explicitConversions.put("Rajapalayam", "rajapalaiyam");
		explicitConversions.put("Chandurthi", "chandurti");
		explicitConversions.put("Bheemadevarpalle", "bheemadevarpally");
		explicitConversions.put("Elkathurthi", "elkathurthy");
		explicitConversions.put("Chigurmamidi", "chigurumamidi");
		explicitConversions.put("Sircilla", "sirsilla");
		explicitConversions.put("Ellanthakunta", "illanthakunta");
		explicitConversions.put("Velgatoor", "velgatur");
		explicitConversions.put("Kathlapur", "kathalapur");
		explicitConversions.put("Kalwakurthy", "kalwakurthi");
		explicitConversions.put("Talakondapalle", "talakondapalli");
		explicitConversions.put("Uppununthala", "uppununtala");
		explicitConversions.put("Doulthabad", "doultabad");
		explicitConversions.put("Nanganur", "nangnur");
		explicitConversions.put("Kowdipally", "kowdipalle");
		explicitConversions.put("ChinthaPalle", "chintapalli");
		explicitConversions.put("Kanagal", "kanagallu");
		explicitConversions.put("Nampalle", "nampalli");
		explicitConversions.put("Narayanapur", "narayanpur");
		explicitConversions.put("Biknoor", "biknur");
		explicitConversions.put("Sadashivanagar", "sadasivanagar");
		explicitConversions.put("Velpoor", "velpur");
		explicitConversions.put("Ibrahimpatnam", "ibrahimpatan");
		explicitConversions.put("Shankarpally", "shankarpalle");
		explicitConversions.put("LingalaGhanpur", "lingal ghanpuram");
		explicitConversions.put("Bachannapet", "bachchannapet");
		explicitConversions.put("Dharmasagar", "dharmasagaram");
		explicitConversions.put("Narmetta", "narmeta");
		explicitConversions.put("Maddur", "madduru");
		explicitConversions.put("Raghunathpally", "raghunathpalli");
		explicitConversions.put("Hasanparthy", "hasanparti");
		explicitConversions.put("Mogullapally", "mogullapalli");
		explicitConversions.put("Palakurthy", "palakurti");
		explicitConversions.put("Sangem", "sangam");
		explicitConversions.put("Bulandshahar", "bulandshahr");
		explicitConversions.put("Sikandrabad", "sikandarabad");
		explicitConversions.put("Muhammadabad", "muhammadbad");
		explicitConversions.put("Kerakat", "kirakat");
		explicitConversions.put("Machhara", "machhra");
		explicitConversions.put("Shahabad", "shahbad");
		explicitConversions.put("Rampur", "rampur maniharran");
		explicitConversions.put("Ghorawal", "gharawal");
		explicitConversions.put("Barwan", "burwan");
		explicitConversions.put("Bhagabangola-I", "bhagawangola -i");
		explicitConversions.put("Bhagabangola-Ii", "bhagawangola -ii");
		explicitConversions.put("Kaligunj", "kaliganj");
		explicitConversions.put("Goghat-II", "goghat ii");
		explicitConversions.put("Chanditala-I", "chanditala i");
		explicitConversions.put("Chanditala-Ii", "chanditala ii");
		explicitConversions.put("Goghat-I", "goghat i");
		explicitConversions.put("Khanakul-I", "khanakul i");
		explicitConversions.put("Polba-Dadpur", "polba dadpur");
		explicitConversions.put("Purshura", "pursura");
		explicitConversions.put("Mongalkote", "mangolkote");
		explicitConversions.put("Monteswar", "manteswar");
		explicitConversions.put("Bishnupur", "vishnupur");

		explicitConversions.put("Bimavaram", "bhimavaram");
		explicitConversions.put("Veeravasaram", "viravasaram");
		explicitConversions.put("Pedanandipadu", "pedda nandipadu");
		explicitConversions.put("Tharlapadu", "tarlupadu");
		explicitConversions.put("Pedaravidu", "peddaravidu");
		explicitConversions.put("Cggallu", "chinnagottigallu");
		explicitConversions.put("BkSamudram", "bukkarayasamudram");
		explicitConversions.put("Tadipatri", "tadpatri");
		explicitConversions.put("Oravakal", "orvakal");
		explicitConversions.put("Deodar", "diyodar");
		explicitConversions.put("Punhana", "punahana");
		explicitConversions.put("Devenhalli", "devanahalli");
		explicitConversions.put("Dodaballapur", "doddaballapur");
		explicitConversions.put("CRPatna", "channarayapatna");
		explicitConversions.put("Arsikere", "arasikere");
		explicitConversions.put("Chicknayakanhalli", "chiknayakanhalli");
		explicitConversions.put("Ballussery", "balussery");
		explicitConversions.put("Thriurangadi", "tirurangadi");
		explicitConversions.put("Chindwara", "chhindwara");
		explicitConversions.put("Malahargarh", "malhargarh");
		explicitConversions.put("Ashta", "aashta");
		explicitConversions.put("Shujalpur", "sujalpur");
		explicitConversions.put("Khachrod", "kachrod");
		explicitConversions.put("Laxmangarh", "lachmangarh");
		explicitConversions.put("Dholpur", "dhaulpur");
		explicitConversions.put("NeemKaThana", "nim ka thana");
		explicitConversions.put("SriMadhopur", "shri madhopur");
		explicitConversions.put("Shanarpatti", "sanarpatti");
		explicitConversions.put("Sathyamangalam", "satyamangalam");
		explicitConversions.put("Acchirupakkam", "acharapakkam");
		explicitConversions.put("Kattalai", "kulithalai");
		explicitConversions.put("Thirumangalam", "tirumangalam");
		explicitConversions.put("MudukulathurSouth", "mudukulattur");
		explicitConversions.put("Thiruvaiyaru", "tiruvaiyaru");
		explicitConversions.put("Chennavaram", "chengam");
		explicitConversions.put("Mullipattu", "melpallipattu");
		explicitConversions.put("Peranamallur", "pernamallur");
		explicitConversions.put("Kodavasal", "kudavasal");
		explicitConversions.put("Mannachanallur", "manachanallur");
		explicitConversions.put("Thottiyam", "tottiyam");
		explicitConversions.put("Thuraiyur", "turaiyur");
		explicitConversions.put("Natrampalli", "nattrampalli");
		explicitConversions.put("Madhanur", "madanur");
		explicitConversions.put("Vikkiravandi", "vikravandi");
		explicitConversions.put("Srivilliputtur", "sirivilliputtur");
		explicitConversions.put("Laxmanchanda", "lakshmanchanda");
		explicitConversions.put("Tirumalayapalem", "thirumalayapalem");
		explicitConversions.put("Kodimial", "kodmial");
		explicitConversions.put("Shankarapatnam_Keshavapatnam", "kesavapatnam");
		explicitConversions.put("RcPuram", "ramachandrapuram");
		explicitConversions.put("Mothey", "mothkur");
		explicitConversions.put("Nuthankal", "nutankal");
		explicitConversions.put("SaliGouraram", "shaligouraram");
		explicitConversions.put("Thirumalgiri", "tirmalgiri");
		explicitConversions.put("Shabad", "shahabad");
		explicitConversions.put("Geesugonda", "gisgonda");
		explicitConversions.put("Wardhannapet", "vardannapet");
		explicitConversions.put("Shayampet", "sayampet");
		explicitConversions.put("Kheragarh", "khairigarh");
		explicitConversions.put("Shikohabad", "sikohabad");
		explicitConversions.put("Ghazipur", "gazipur");
		explicitConversions.put("Mur-Jiaganj", "murshidabad jiaganj");

		explicitConversions.put("Veldurthi", "veludurti");
		explicitConversions.put("Cumbum", "kambham");
		explicitConversions.put("KvPalli", "kambhamvaripalle");
		explicitConversions.put("Atloor", "atlur");
		explicitConversions.put("Veeraballi", "viraballe");
		explicitConversions.put("DHirehal", "hirehallu hoshahalli");
		explicitConversions.put("Kudair", "kuderu");
		explicitConversions.put("CkPalli", "chennekothapalle");
		explicitConversions.put("OdCheruvu", "obuladevaracheruvu");
		explicitConversions.put("Parigi", "paragi");
		explicitConversions.put("Belha", "bilha");
		explicitConversions.put("Nagari", "nagri");
		explicitConversions.put("Virmagam", "viramgam");
		explicitConversions.put("Dahegam", "dehgam");
		explicitConversions.put("Tauru", "taoru");
		explicitConversions.put("Gulha", "guhla");
		explicitConversions.put("Kalyat", "kalayat");
		explicitConversions.put("Sonepat", "sonipat");
		explicitConversions.put("Chachrauli", "chhachhraulii");
		explicitConversions.put("Athani", "athni");
		explicitConversions.put("Gauribidalur", "gowribidanur");
		explicitConversions.put("Yelbarga", "yalburga");
		explicitConversions.put("Ranmanagara", "ramanagaram");
		explicitConversions.put("Thalassery", "talasseri");
		explicitConversions.put("Chirayinkil", "chirayinkeezhu");
		explicitConversions.put("Panesmal", "pansemal");
		explicitConversions.put("Porsa", "poras");
		explicitConversions.put("Neemuch", "nimach");
		explicitConversions.put("Biora", "biyavra");
		explicitConversions.put("Jaora", "jaura");
		explicitConversions.put(".MohanBerodia", "moman barodiy");
		explicitConversions.put("Niwari", "nivari");
		explicitConversions.put("Rhata", "rahta");
		explicitConversions.put("Newasa", "nevasa");
		explicitConversions.put("Morshi", "tah-morsi");
		explicitConversions.put("Amloh", "almoh");
		explicitConversions.put("Umren", "umrain");
		explicitConversions.put("Chohtan", "chauthan");
		explicitConversions.put("Baetu", "baytoo");
		explicitConversions.put("Sheo", "shiv");
		explicitConversions.put("Rupbas", "rupwas");
		explicitConversions.put("Sewar", "sewer");
		explicitConversions.put("Pirawa", "piddawa");
		explicitConversions.put("Chhotisadri", "choti sadari");
		explicitConversions.put("Bonli", "bauli");
		explicitConversions.put("Reodar", "revdar");
		explicitConversions.put("Gogunda", "goganda");
		explicitConversions.put("Purasawalkam-Perambur-I", "perambur purasavakkam");
		explicitConversions.put("Purasawalkam-Perambur-Ii", "perambur purasavakkam");
		explicitConversions.put("Purasawalkam-Perambur-Iii", "perambur purasavakkam");
		explicitConversions.put("Purasawalkam-Perambur-Iv", "perambur purasavakkam");

		explicitConversions.put("Natham", "nattam");
		explicitConversions.put("Thirukazhukundram", "tirukkalukkunram");
		explicitConversions.put("Keelaiyur", "kilvelur");
		explicitConversions.put("Vazhappadi", "valappadi");
		explicitConversions.put("Kurichi", "kuruchi");
		explicitConversions.put("Theni", "teni");
		explicitConversions.put("Uthamapalayam", "uttamapalaiyam");
		explicitConversions.put("Anakavoor", "annakkavur");
		explicitConversions.put("T.V.Malai(South)", "tiruvannamalai");
		explicitConversions.put("T.V.Malai(North)", "paragi");
		explicitConversions.put("Thiruthuraipoondi", "tirutturaipundi");
		explicitConversions.put("Medipalle", "maidpalli");
		explicitConversions.put("Bijinapalle", "bijnapalli");
		explicitConversions.put("Kothur", "kottur");
		explicitConversions.put("Tadoor", "tadur");
		explicitConversions.put("Thimmajipeta", "timmajipet");
		explicitConversions.put("Munugode", "mungod");
		explicitConversions.put("Thungathurthi", "tungaturti");
		explicitConversions.put("Devaruppula", "evaruppal");
		explicitConversions.put("Rayaparthy", "raiparthi");
		explicitConversions.put("StnGhanpur", "ghanpur(station)");
		explicitConversions.put("Zaffergadh", "zafargarh");
		explicitConversions.put("Thorrur", "torur");
		explicitConversions.put("Bhadar", "bahadurpur");
		explicitConversions.put("Rasara", "rasra");
		explicitConversions.put("Debai", "dibai");
		explicitConversions.put("Karvi", "karwi");
		explicitConversions.put("Jewar", "jevar");
		explicitConversions.put("Swar", "suar");
		explicitConversions.put("Nowda", "nawda");

	}

	/**
	 * 
	 */
	private static final String DOT = ".";

	private static final String UNDERSCORE = "_";

	public static String getPrimaryKey(final String siteName, final String stateName, final String districtName,
			final String blockName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(siteName.toLowerCase()).append(UNDERSCORE).append(stateName.toLowerCase()).append(UNDERSCORE)
				.append(districtName.toLowerCase()).append(UNDERSCORE).append(blockName.toLowerCase())
				.append(UNDERSCORE);

		return buffer.toString();
	}
	public static String getStateDistrictBlockKey(final String stateName, final String districtName,
			final String blockName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(stateName.toLowerCase()).append(UNDERSCORE).append(districtName.toLowerCase()).append(UNDERSCORE)
				.append(blockName.toLowerCase());

		return buffer.toString();
	}

	public static String getStateDistrictKey(final String stateName, final String districtName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(stateName.toLowerCase()).append(UNDERSCORE).append(districtName.toLowerCase());

		return buffer.toString();
	}

	public static String getStateKey(final String stateName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(stateName.toLowerCase());

		return buffer.toString();
	}

	public static String getBestMatchBlockName(final String stateName, final String districtName,
			final String blockName, Set<String> allKeys) {
		for (final String k : allKeys) {
			String[] tokens = StringUtils.split(k, UNDERSCORE);
			String mapKeyState = tokens[1].trim();
			String mapKeyDistrict = tokens[2].trim();
			String mapKeyBlock = tokens[3].trim();

			if (StringUtils.equalsIgnoreCase(mapKeyState, stateName)) {
				if (StringUtils.equalsIgnoreCase(mapKeyDistrict, districtName)) {
					if (StringUtils.equalsIgnoreCase(mapKeyBlock, blockName)) {
						// Its best case scenario
						return blockName;
					} else if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, " "),
							StringUtils.remove(mapKeyBlock, " "))) {
						// Cases like, Sarkarsammakulam in block file and Sarkar
						// Sammakulam in siteInfo file, return
						// site info name
						return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "North")) {
						// Cases like, POLLACHI(N) in block file and POLLACHI
						// NORTH in siteInfo file, return site info
						// name.

						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "North").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_N"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(N)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "South")) {
						// Cases like, POLLACHI(S) in block file and POLLACHI
						// SOUTH in siteInfo file, return site info
						// name.
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "South").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_S"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(S)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "East")) {
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "East").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_E"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(E)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "West")) {
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "West").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_W"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(W)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					}

					else if (StringUtils.containsIgnoreCase(StringUtils.remove(blockName, " "),
							StringUtils.remove(mapKeyBlock, " "))) {
						// Cases like, Boath(NC) in block file and Boath in
						// siteInfo file, return site info name.
						return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.containsIgnoreCase(blockName, DOT)) {
						// Cases like, B.K. Samudram in block file and
						// BUKKARAYASAMUDRAM in siteInfo file.
						String endOfBlockName = StringUtils.substringAfterLast(blockName, DOT);
						if (StringUtils.containsIgnoreCase(StringUtils.remove(mapKeyBlock, " "),
								StringUtils.remove(endOfBlockName, " "))) {
							return StringUtils.capitalize(mapKeyBlock);
						}
					}
				}
			}
		}
		// Return name with no space in between and converted
		String name = StringUtils.remove(blockName, " ");
		if (StringUtils.endsWith(name, "_N")) {
			return StringUtils.removeEnd(name, "_N").trim() + "(N)";
		} else if (StringUtils.endsWith(name, "-N")) {
			return StringUtils.removeEnd(name, "-N").trim() + "(N)";
		} else if (StringUtils.endsWith(name, "_S")) {
			return StringUtils.removeEnd(name, "_S").trim() + "(S)";
		} else if (StringUtils.endsWith(name, "-S")) {
			return StringUtils.removeEnd(name, "-S").trim() + "(S)";
		} else if (StringUtils.endsWith(name, "_E")) {
			return StringUtils.removeEnd(name, "_E").trim() + "(E)";
		} else if (StringUtils.endsWith(name, "-E")) {
			return StringUtils.removeEnd(name, "-E").trim() + "(E)";
		} else if (StringUtils.endsWith(name, "_W")) {
			return StringUtils.removeEnd(name, "_W").trim() + "(W)";
		} else if (StringUtils.endsWith(name, "-W")) {
			return StringUtils.removeEnd(name, "-W").trim() + "(W)";
		}
		return getOverriddenNameIfApplicable(name);
	}

	private static String getOverriddenNameIfApplicable(String name) {
		if (explicitConversions.containsKey(name)) {
			return explicitConversions.get(name);
		}
		return name;

	}

	public static String getBestMatchBlockNameWithoutCountryInKey(final String stateName, final String districtName,
			final String blockName, Set<String> allKeys) {
		for (final String k : allKeys) {
			String[] tokens = StringUtils.split(k, UNDERSCORE);
			String mapKeyState = tokens[0].trim();
			String mapKeyDistrict = tokens[1].trim();
			String mapKeyBlock = tokens[2].trim();
			if (StringUtils.equalsIgnoreCase(mapKeyState, stateName)) {
				if (StringUtils.equalsIgnoreCase(mapKeyDistrict, districtName)) {
					if (StringUtils.equalsIgnoreCase(mapKeyBlock, blockName)) {
						// Its best case scenario
						return blockName;
					} else if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, " "),
							StringUtils.remove(mapKeyBlock, " "))) {
						// Cases like, Sarkarsammakulam in block file and Sarkar
						// Sammakulam in siteInfo file, return
						// site info name
						return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "North")) {
						// Cases like, POLLACHI(N) in block file and POLLACHI
						// NORTH in siteInfo file, return site info
						// name.

						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "North").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_N"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(N)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "South")) {
						// Cases like, POLLACHI(S) in block file and POLLACHI
						// SOUTH in siteInfo file, return site info
						// name.
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "South").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_S"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(S)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "East")) {
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "East").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_E"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(E)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "West")) {
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "West").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_W"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(W)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					}

					else if (StringUtils.containsIgnoreCase(StringUtils.remove(blockName, " "),
							StringUtils.remove(mapKeyBlock, " "))) {
						// Cases like, Boath(NC) in block file and Boath in
						// siteInfo file, return site info name.
						return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.containsIgnoreCase(blockName, DOT)) {
						// Cases like, B.K. Samudram in block file and
						// BUKKARAYASAMUDRAM in siteInfo file.
						String endOfBlockName = StringUtils.substringAfterLast(blockName, DOT);
						if (StringUtils.containsIgnoreCase(StringUtils.remove(mapKeyBlock, " "),
								StringUtils.remove(endOfBlockName, " "))) {
							return StringUtils.capitalize(mapKeyBlock);
						}
					}
				}
			}
		}
		// Return name with no space in between and converted
		String name = StringUtils.remove(blockName, " ");
		if (StringUtils.endsWith(name, "_N")) {
			return StringUtils.removeEnd(name, "_N").trim() + "(N)";
		} else if (StringUtils.endsWith(name, "-N")) {
			return StringUtils.removeEnd(name, "-N").trim() + "(N)";
		} else if (StringUtils.endsWith(name, "_S")) {
			return StringUtils.removeEnd(name, "_S").trim() + "(S)";
		} else if (StringUtils.endsWith(name, "-S")) {
			return StringUtils.removeEnd(name, "-S").trim() + "(S)";
		} else if (StringUtils.endsWith(name, "_E")) {
			return StringUtils.removeEnd(name, "_E").trim() + "(E)";
		} else if (StringUtils.endsWith(name, "-E")) {
			return StringUtils.removeEnd(name, "-E").trim() + "(E)";
		} else if (StringUtils.endsWith(name, "_W")) {
			return StringUtils.removeEnd(name, "_W").trim() + "(W)";
		} else if (StringUtils.endsWith(name, "-W")) {
			return StringUtils.removeEnd(name, "-W").trim() + "(W)";
		}
		return getOverriddenNameIfApplicable(name);
	}

}
