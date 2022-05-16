package com.dpb.store.services.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.List;

@Getter
@Setter
public class Parser {
    private Shop leipzig;
    private Shop test;
    private Shop dresden;
    private List<Review> review;
    private Categories categories;


    public Parser() {
    }

    public void lunchParser() throws IOException {
        File fileLeipzig = new File("src/main/resources/data/leipzig_transformed.xml");
        File test = new File("src/main/resources/data/test.xml");
        File fileDresden = new File("src/main/resources/data/dresden.xml");
        File fileCategories = new File("src/main/resources/data/categories.xml");
        File fileReviews = new File("src/main/resources/data/reviews.csv");
        leipzig = deserializeShopFromXML(fileLeipzig);
        this.test = deserializeShopFromXML(test);
        dresden = deserializeShopFromXML(fileDresden);
    }

    public Shop deserializeShopFromXML(File file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String xml = input(new FileInputStream(file));
        return xmlMapper.readValue(xml, Shop.class);
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
