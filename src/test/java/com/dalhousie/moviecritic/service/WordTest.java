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

public class WordTest {

    @InjectMocks
    WordData worddata;

    @Mock
    WordData wrdat;
    @Spy
    GraphDataMovie moviedata;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDataVisualizationTest()  {

        Mockito.doReturn(new HashMap<String,Float>()).when(wrdat).getDataVisualization(Mockito.any());
        assertEquals(new HashMap<String,Float>(), wrdat.getDataVisualization(new ArrayList<Review>()));
    }

    @Test(expected = NullPointerException.class)
    public void getDataVisualizationTest2()  {
        MockData data=new MockData();
        worddata.getDataVisualization(data.getReviewData());
    }
}
