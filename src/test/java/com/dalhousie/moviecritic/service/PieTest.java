package com.dalhousie.moviecritic.service;

import com.dalhousie.moviecritic.Data.Review;
import com.dalhousie.moviecritic.MockData.MockData;
import com.dalhousie.moviecritic.utils.GraphDataMovie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PieTest {

    @InjectMocks
    PieData piedata;

    @Mock
    PieData piedat;

    @Spy
    GraphDataMovie moviedata;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDataVisualizationTest()  {

        Mockito.doReturn(new HashMap<String,Float>()).when(piedat).getDataVisualization(Mockito.any());
        assertEquals(new HashMap<String,Float>(), piedat.getDataVisualization(new ArrayList<Review>()));
    }

    @Test
    public void getDataVisualizationTest2()  {

        MockData data=new MockData();
        assertEquals(new HashMap<String,Float>(){{
            put("21-30",2.0f);}}, piedata.getDataVisualization(data.getReviewData()));
    }
}
