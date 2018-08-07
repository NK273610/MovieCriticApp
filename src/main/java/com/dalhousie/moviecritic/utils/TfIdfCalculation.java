package com.dalhousie.moviecritic.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TfIdfCalculation implements ITfIdfCalculation {

    public List<List<String>> textToWord(List<String> reviews)
    {
        List<List<String>> docToword=new ArrayList<>();
        try {
            for (String word : reviews) {
                docToword.add(Arrays.asList(word.replaceAll("\\d","")
                        .split("\\W+")));
            }
        }
        catch ( NullPointerException e)
        {
            e.printStackTrace();
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }

        return docToword;
    }
    public  float tf(List<String> doc, String term) {
        float result = 0;
        try {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word))
                    result++;
            }
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public  float idf(List<List<String>> docs, String term) {
        float n = 0;
        try {
            for (List<String> doc : docs) {
                for (String word : doc) {
                    if (term.equalsIgnoreCase(word)) {
                        n++;
                        break;
                    }
                }
            }
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        return n;
    }

}
