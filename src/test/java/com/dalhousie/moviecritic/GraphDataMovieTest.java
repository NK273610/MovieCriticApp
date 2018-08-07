package com.dalhousie.moviecritic;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.utils.GraphDataMovie;
import com.dalhousie.moviecritic.utils.MovieRecommend;
import com.dalhousie.moviecritic.utils.TfIdfCalculation;

@RunWith(SpringJUnit4ClassRunner.class)
public class GraphDataMovieTest {

    @InjectMocks
    GraphDataMovie graphdata;

    @Spy
    MovieRecommend movierec;

    @Spy
    TfIdfCalculation tfidf;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        graphdata = null;

    }

    @Test
    public void getAgeGroupCount()
    {
        List<String>input_list = Arrays.asList("10-20", "10-20", "20-30","40-50");
        HashMap<String, Float> expectedoutput = new HashMap<String, Float>() {{
            put("10-20",2.0f);put("20-30",1.0f);put("40-50",1.0f);}};
        assertEquals(expectedoutput, graphdata.getAgeGroupCount(input_list));
    }

    @Test
    public void getAverageGroup()
    {
        List<String>input_list1 = Arrays.asList("10-20", "10-20", "20-30","40-50");
        List<Float>input_list2 = Arrays.asList(3.0f,4.0f,5.0f,6.0f);
        HashMap<String, Float> expectedoutput = new HashMap<String, Float>() {{
            put("10-20",3.5f);put("20-30",5.0f);put("40-50",6.0f);}};
        assertEquals(expectedoutput, graphdata.getAverageAgeGroupRating(input_list1,input_list2));
    }

    @Test
    public void getRating()
    {
        List<Float>input_list = Arrays.asList(1.2f,2.3f,3.0f,4.2f,5.1f);
        float expectedoutput=3.15f;
        assertEquals(expectedoutput, graphdata.CalculateRating(input_list),0.01);
    }

    @Test
    public void getMovie()
    {
        HashMap<String,Integer> moviedata_user1=new HashMap<String,Integer>(){{
            put("movie1",1);put("movie2",0);put("mvoie3",1);}};
        HashMap<String,Integer> moviedata_user2=new HashMap<String,Integer>(){{
            put("movie1",1);put("movie2",0);put("mvoie4",1);}};
        HashMap<String,Integer> moviedata_user3=new HashMap<String,Integer>(){{
            put("movie1",1);put("movie4",0);put("mvoie5",1);}};
        HashMap<String,Integer> moviedata_user4=new HashMap<String,Integer>(){{
            put("movie1",1);put("movie2",0);put("mvoie3",1);}};
        HashMap<String,HashMap<String,Integer>> fulllist=new HashMap<String,HashMap<String,Integer>>()
        {{
            put("user1",moviedata_user1);put("user2",moviedata_user2);put("user3",moviedata_user3);
        put("user4",moviedata_user4);}};
        HashMap<String,Float>expectedoutput=new HashMap<String,Float>()
        {{
            put("user2",0.5f);put("user3",0.2f);put("user4",1.0f);}};

        assertEquals(expectedoutput,graphdata.getMovie(fulllist,"user1"));

    }

    @Test
    public void calUniqueness()
    {
        List<String> inputdoc = Arrays.asList("foo barr bazz","barr bask solve","bazz solve host");
        HashMap<String, Float> expectedoutput = new HashMap<String, Float>() {{
            put("barr",0.5f);put("bazz",0.5f);put("foo",1.0f);put("solve",0.5f);put("host",1.0f);put("bask",1.0f);}};

        assertEquals(expectedoutput,graphdata.CalculateUniqueness(inputdoc));
    }
}
