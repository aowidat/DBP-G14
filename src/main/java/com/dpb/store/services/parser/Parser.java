package com.dpb.store.services.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {
    public static void deserializeFromXML() throws IOException {
        File file = new File("/Users/mahmoud/University/8. Semester/DB Praktikum/DPB-G14/src/main/resources/data/leipzig_transformed.xml");
        XmlMapper xmlMapper = new XmlMapper();
        //xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        //xmlMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String xml = inputt(new FileInputStream(file));
        Shop shop = xmlMapper.readValue(xml,Shop.class);
        System.out.println(shop.getName());
        System.out.println(shop.getStreet());
        System.out.println(shop.getZip());
        for (int i=0; i< shop.getItem().size(); i++){
            System.out.println(shop.getItem().get(i).getTitle());
        }


    }

    public static String inputt(InputStream is) throws IOException{
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine())!= null){
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Deserializing from XML...");
        deserializeFromXML();
    }

}
