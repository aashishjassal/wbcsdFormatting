package com.wbcsd.fileGenerator.application.util;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.wbcsd.fileGenerator.api.LatLong;
import com.wbcsd.fileGenerator.api.impl.LatLongImpl;

public class LatLongCalculator {

    private static boolean ISOVERQUERY = false;

    private static final String GEOCODE_REQUEST_URL = "http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&";

    private static HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());

    public static void main(String[] args) throws Exception {
        LatLongCalculator.getLongitudeLatitude("BK");
        // LatLongCalculator.getLongitudeLatitude("CHIKHALBEED");
        // LatLongCalculator.getLongitudeLatitude("SINDGAON");
        // LatLongCalculator.getLongitudeLatitude("MAW PHLANG DAM");
        // LatLongCalculator.getLongitudeLatitude("MANJORE");
        // LatLongCalculator.getLongitudeLatitude("KALAKH SAGAR");
        // LatLongCalculator.getLongitudeLatitude("PHUTOON");
        // LatLongCalculator.getLongitudeLatitude("Pegumbahallah Forebay");
        // LatLongCalculator.getLongitudeLatitude("Pilavukkal Project Kovilar");
        // LatLongCalculator.getLongitudeLatitude("Pilavukkal Project Periyar");
        // LatLongCalculator.getLongitudeLatitude("Pykara New Forebay");
        // LatLongCalculator.getLongitudeLatitude("Vattamalaikarai Odai");
        // LatLongCalculator.getLongitudeLatitude("Noyyal Orathupalayam");
        // LatLongCalculator.getLongitudeLatitude("Varattar Vallimadurai");

        // Western Catchment Weir-I
        // Western Catchment Weir-II
        // Western Catchment Weir-III

    }

    public static LatLong getLongitudeLatitude(String address) {
        LatLongImpl latLong = new LatLongImpl();
        try {
            if (ISOVERQUERY) {
                System.err.println("Daily limit breached. Ignoring further requests");
                return latLong;
            }
            TimeUnit.MILLISECONDS.sleep(100);
            if (StringUtils.contains(address, ",")) {
                System.out.println("Address contains place and city, removing city");
                address = StringUtils.split(address, ",")[0].trim();
            }
            StringBuilder urlBuilder = new StringBuilder(GEOCODE_REQUEST_URL);
            if (StringUtils.isNotBlank(address)) {
                urlBuilder.append("&address=").append(URLEncoder.encode(address, "UTF-8"));
            }

            final GetMethod getMethod = new GetMethod(urlBuilder.toString());
            try {
                httpClient.executeMethod(getMethod);
                Reader reader = new InputStreamReader(getMethod.getResponseBodyAsStream(),
                        getMethod.getResponseCharSet());

                int data = reader.read();
                char[] buffer = new char[1024];
                Writer writer = new StringWriter();
                while ((data = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, data);
                }

                String result = writer.toString();
                System.out.println(result.toString());

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader("<" + writer.toString().trim()));
                Document doc = db.parse(is);

                String status = getXpathValue(doc, "//GeocodeResponse/status/text()");
                if (StringUtils.equalsIgnoreCase(status, "OVER_QUERY_LIMIT")) {
                    ISOVERQUERY = true;
                }

                // System.out.println(address);
                String strLatitude = getXpathValue(doc, "//GeocodeResponse/result/geometry/location/lat/text()");
                // System.out.println("Latitude:" + strLatitude);

                String strLongtitude = getXpathValue(doc, "//GeocodeResponse/result/geometry/location/lng/text()");
                // System.out.println("Longitude:" + strLongtitude);

                latLong.setLatitude(getInDMSFormat(strLatitude));
                latLong.setLongitude(getInDMSFormat(strLongtitude));
                System.out.println("AASHISH " + address + " " + getInDMSFormat(strLatitude) + " "
                        + getInDMSFormat(strLongtitude));
            } finally {
                getMethod.releaseConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latLong;

    }

    private static String getInDMSFormat(String decimalFormat) {
        double dd = Double.parseDouble(decimalFormat);
        int d = (int) dd; // Truncate the decimals
        double t1 = (dd - d) * 60;
        int m = (int) t1;
        double s = (t1 - m) * 60;

        return d + "°" + m + "'" + s + "\"";

    }

    private static String getXpathValue(Document doc, String strXpath) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xPath.compile(strXpath);
        String resultData = null;
        Object result4 = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result4;
        for (int i = 0; i < nodes.getLength(); i++) {
            resultData = nodes.item(i).getNodeValue();
        }
        return resultData;
    }

}
