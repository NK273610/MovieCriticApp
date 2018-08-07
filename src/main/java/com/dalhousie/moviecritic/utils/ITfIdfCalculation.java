package com.dalhousie.moviecritic.utils;

import java.util.List;

public interface ITfIdfCalculation {

    public List<List<String>> textToWord(List<String> reviews);
    public  float tf(List<String> doc, String term);
    public  float idf(List<List<String>> docs, String term);
}
