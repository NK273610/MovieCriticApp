package com.dalhousie.moviecritic;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dalhousie.moviecritic.utils.TfIdfCalculation;

public class TfIdfTestClass {


    TfIdfCalculation tfidfcal;

    @Before
    public void setUp() {
        tfidfcal = new TfIdfCalculation();

    }

    @After
    public void tearDown() {
        tfidfcal = null;

    }
    @Test
    public void textToWord1()
    {
        List<List<String>> expectedOutput = Arrays.asList(Arrays.asList("foo", "bar", "baz"),
                Arrays.asList("foo", "bar", "baz"));
        List<String> reviewstr = Arrays.asList("foo bar baz","foo2 bar2 baz2");
        assertEquals(expectedOutput,tfidfcal.textToWord(reviewstr));
    }
    @Test
    public void textToWord2()
    {
        List<String> expectedOutput = Arrays.asList("foo", "bar", "baz");
        List<String> reviewstr = Arrays.asList("foo  bar  , , , !!! @@@@ 3214 baz");
        assertEquals(expectedOutput,tfidfcal.textToWord(reviewstr).get(0));
    }
    @Test
    public void idf1()
    {
        List<List<String>> inputdoc = Arrays.asList(Arrays.asList("foo","bar","baz"),
                Arrays.asList("foo", "bar", "baz"));

        assertEquals(2.0,tfidfcal.idf(inputdoc,"foo"),0.0001);
    }

    @Test
    public void idf2()
    {
        List<List<String>> inputdoc = Arrays.asList(Arrays.asList("foo","bar","baz"),
                Arrays.asList("bvbvb", "baghfr", "baz"));

        assertEquals(1.0,tfidfcal.idf(inputdoc,"foo"),0.0001);
    }

    @Test
    public void tf1()
    {
        List<String> inputdoc = Arrays.asList("foo","foo","baz","foo");

        assertEquals(3.0,tfidfcal.tf(inputdoc,"foo"),0.0001);
    }

    @Test
    public void tf2()
    {
        List<String> inputdoc = Arrays.asList("foo","foo","baz","foo");

        assertEquals(0.0,tfidfcal.tf(inputdoc,"zoo"),0.0001);
    }
}

