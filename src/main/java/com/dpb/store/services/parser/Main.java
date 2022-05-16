package com.dpb.store.services.parser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.lunchParser();
        int i = 0;
        //  System.out.println(parser.getDresden().getItem().get(i).getBookspec().getPages());
        // System.out.println(parser.getLeipzig().getItem().get(i).getBookspec().getPages());
//        System.out.println(parser.getTest().getItem().get(i).getMusicspec().getBinding());
//        System.out.println(parser.getTest().getItem().get(i).getMusicspec().getFormat().getValue());
//        System.out.println(parser.getTest().getItem().get(i).getMusicspec().getFormat().getName());
//        System.out.println(parser.getTest().getItem().get(i).getMusicspec().getNum_discs());
//        System.out.println(parser.getTest().getItem().get(i).getMusicspec().getReleasedate());
//        System.out.println(parser.getTest().getItem().get(i).getMusicspec().getUpc());
//        System.out.println(parser.getTest().getItem().get(i).getPrice().getPrice());
//        System.out.println(parser.getTest().getItem().get(i).getPrice().getCurrency());
//        System.out.println(parser.getTest().getItem().get(i).getPrice().getMult());
//        System.out.println(parser.getTest().getItem().get(i).getPrice().getState());
//        System.out.println(parser.getTest().getItem().get(i).getPrice().getTitle());
//        System.out.println(parser.getTest().getItem().get(i).getDetails().toString());
//        System.out.println(parser.getTest().getItem().get(i).getDetailpage());
//        System.out.println(parser.getTest().getItem().get(i).getTitle());
//        System.out.println(parser.getTest().getItem().get(i).getPublishers().get(0).getName());
//        System.out.println(parser.getTest().getItem().get(i).getPublishers().get(0).getValue());
//        System.out.println(parser.getTest().getItem().get(i).getPublishers().get(1).getName());
//        System.out.println(parser.getTest().getItem().get(i).getPublishers().get(1).getValue());
        System.out.println(parser.getTest().getItem().get(i).getSimilars().get(1).getValue());
        System.out.println(parser.getTest().getItem().get(i).getSimilars().get(1).getValue());
    }
}
