package com.dalhousie.moviecritic.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.MockData.MockData;
import com.dalhousie.moviecritic.utils.GraphDataMovie;

public class BarTest {

    @InjectMocks
    BarData bardata;

    @Mock
    BarData bardat;

    @Spy
    GraphDataMovie moviedata;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDataVisualizationTest()  {

        Mockito.doReturn(new HashMap<String,Float>()).when(bardat).getDataVisualization(Mockito.any());
        assertEquals(new HashMap<String,Float>(), bardat.getDataVisualization(new ArrayList<Review>()));
    }

    @Test
    public void getDataVisualizationTest2()  {

        MockData data=new MockData();
        assertEquals(new HashMap<String,Float>(){{
            put("21-30",1.0f);}}, bardata.getDataVisualization(data.getReviewData()));
    }
}
