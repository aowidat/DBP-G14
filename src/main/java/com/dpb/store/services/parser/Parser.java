package com.dpb.store.services.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "Parser")
@Getter
@Setter
public class Parser {
    private Shop leipzig;
    private Shop test;
    private Shop dresden;
    private List<Review> review;
    private Categories categories;
//    private Categories categories;
//    private Category cat;


    public Parser() {
    }

    public void lunchParser() throws IOException, JAXBException {
        log.info("{} has started ", Parser.class.getSimpleName());
        File fileLeipzig = new File("src/main/resources/data/leipzig_transformed.xml");
        File test = new File("src/main/resources/data/test.xml");
        File fileDresden = new File("src/main/resources/data/dresden.xml");
        File fileCategories = new File("src/main/resources/data/categories.xml");
        File fileReviews = new File("src/main/resources/data/reviews.csv");
        leipzig = deserializeShopFromXML(fileLeipzig);
        this.test = deserializeShopFromXML(test);
        dresden = deserializeShopFromXML(fileDresden);
        review = deserializeReviewFromCSV(fileReviews);
//        categories = deserializeCategoriesFromXML(fileCategories);
    }

    public Shop deserializeShopFromXML(File file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        xmlMapper.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        String xml = input(new FileInputStream(file));
        return xmlMapper.readValue(xml, Shop.class);
    }

    public void deserializeCategoriesFromXML(File file) throws IOException, JAXBException, ParserConfigurationException, SAXException {
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
////        xmlMapper.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        String xml = input(new FileInputStream(file));
//        System.out.println(xml.);
//        JAXBContext jc = JAXBContext.newInstance(Categories.class);
//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        return (Categories) unmarshaller.unmarshal(file);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList list = doc.getElementsByTagName("category");
        for (int temp = 0; temp < list.getLength(); temp++) {

            Node node = list.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
            }
        }
        System.out.println(doc.getDocumentElement().getNodeName());
//        for (int i = 0; i < nd.getLength(); i++) {
//            System.out.println(nd.item(i).getChildNodes().item(0).getLocalName());
//        }

    }

    public List<Review> deserializeReviewFromCSV(File file) throws IOException {
        List<Review> reviews = new ArrayList<>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            Review rv = new Review(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
            reviews.add(rv);
        }
        br.close();
        reviews.remove(0);
        return reviews;
    }

    public String input(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
