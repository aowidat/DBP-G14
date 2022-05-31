package com.dpb.store.services.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to parse all xml files using Jackson, and csv files using BufferReader
 */
@Slf4j(topic = "Parser")
@Getter
@Setter
@NoArgsConstructor
public class Parser {
    private Shop leipzig;
    private Shop test;
    private Shop dresden;
    private List<Review> review;
    private Categories categories;

    /**
     * a function to start the parsing, it has the source to all files and call other methods to parse xml and csv files
     * @throws IOException
     */
    public void lunchParser() throws IOException {
        log.info("{} has started ", Parser.class.getSimpleName());
        File fileLeipzig = new File("src/main/resources/data/leipzig_transformed.xml");
        File fileTest = new File("src/main/resources/data/test.xml");
        File fileDresden = new File("src/main/resources/data/dresden.xml");
        File fileCategories = new File("src/main/resources/data/categories.xml");
        File fileReviews = new File("src/main/resources/data/reviews.csv");
        leipzig = deserializeShopFromXML(fileLeipzig);
        dresden = deserializeShopFromXML(fileDresden);
        test = deserializeShopFromXML(fileTest);
        review = deserializeReviewFromCSV(fileReviews);
        categories = deserializeCategoriesFromXML(fileCategories);
    }

    /**
     * a function to deserialize Shop from xml file
     * @param file source of xml shop
     * @return Shop with all its parameters
     * @throws IOException
     */
    public Shop deserializeShopFromXML(File file) throws IOException {
        log.info("deserializing file {}", file.getName());
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String xml = input(new FileInputStream(file));
        return xmlMapper.readValue(xml, Shop.class);
    }

    /**
     * a function to deserialize categories from xml file
     * @param file source of xml categories
     * @return Categories
     * @throws IOException
     */
    public Categories deserializeCategoriesFromXML(File file) throws IOException {
        log.info("deserializing file {}", file.getName());
        XmlMapper xmlMapper = new XmlMapper();
        String xml = input(new FileInputStream(file));
        return xmlMapper.readValue(xml, Categories.class);
    }

    /**
     * a function to read all review from csv fil
     * @param file source of Reviews
     * @return List of reviews
     * @throws IOException
     */
    public List<Review> deserializeReviewFromCSV(File file) throws IOException {
        log.info("deserializing file {}", file.getName());
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

    /**
     * help function to convert xml file to one string
     * @param is as InputStream
     * @return xml file as one String
     * @throws IOException
     */
    public String input(InputStream is) throws IOException {
        log.info("Converting File to String ..");
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
