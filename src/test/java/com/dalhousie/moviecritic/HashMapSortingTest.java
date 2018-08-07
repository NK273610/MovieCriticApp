package com.dalhousie.moviecritic;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import com.dalhousie.moviecritic.utils.HashMapSorting;

public class HashMapSortingTest {



    @Test
    public void hashMapSort()
    {
        HashMap<String, Float> input = new HashMap<String, Float>() {{
            put("foo",0.25f);put("bazz",0.5f);put("barr",1.0f);}};
        HashMap<String, Float> expectedoutput = new HashMap<String, Float>() {{
           put("barr",1.0f);put("bazz",0.5f); put("foo",0.25f);}};

        assertEquals(expectedoutput,HashMapSorting.sortByComparator(input));
    }

}
