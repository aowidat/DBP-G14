package com.dpb.store.services.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
    //    private List<Category> categories;
//    private Categories categories;
//    private Category cat;


    public Parser() {
    }

    public void lunchParser() throws IOException {
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
//        cat = deserializeCategoriesFromXML(fileCategories);
    }

    public Shop deserializeShopFromXML(File file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String xml = input(new FileInputStream(file));
        return xmlMapper.readValue(xml, Shop.class);
    }

//    public Category deserializeCategoriesFromXML(File file) throws IOException {
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        String xml = input(new FileInputStream(file));
//        return xmlMapper.readValue(xml, Category.class);
//    }

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
