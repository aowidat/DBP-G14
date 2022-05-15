package com.dpb.store.services.parser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.lunchParser();
        System.out.println(parser.getDresden().getItem().get(700).getBookspec().toString());
        System.out.println(parser.getLeipzig().getItem().get(100).getBookspec().toString());
    }
}
