package com.dalhousie.moviecritic;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalhousie.moviecritic.utils.MovieRecommend;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieRecommendTestClass {

    @Spy
    MovieRecommend movierec;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(movierec);

    }

    @After
    public void tearDown() {
        movierec = null;

    }

    @Test
    public void compareToTest()
    {

        HashMap<String, Integer> compareTo = new HashMap<String, Integer>() {{
            put("foo",1);put("bazz",0);put("barr",1);}};
        HashMap<String, Integer> compareFrom = new HashMap<String, Integer>() {{
            put("barrk",1);put("bazz",0); put("foo",1);}};
        Mockito.doReturn(4).when(movierec).getUniqueCount(Mockito.anyListOf(String.class),Mockito.anyListOf(String.class));
        float expectedoutput=0.5f;
        assertEquals(expectedoutput,movierec.Compute_Scores(compareTo,compareFrom),0.01);

    }

    @Test
    public void getUniqueCount()
    {
        List<String> list1 = Arrays.asList("foo","foo2","foo3","xyz","hello");
        List<String> list2 = Arrays.asList("foo","foo2","foo3e","xyzc","hello");
        int expectedoutput=7;
        assertEquals(expectedoutput,movierec.getUniqueCount(list1,list2));
    }

    @Test(expected=NullPointerException.class)
    public void testNull() {
        movierec.getUniqueCount(null,null);
    }

}
