package com.dalhousie.moviecritic;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.dalhousie.moviecritic.utils.BadLanguage;

@RunWith(SpringJUnit4ClassRunner.class)
public class BadLanguageTest {

    BadLanguage badLanguage;

    @Test
    public void badLanguageWordsTest() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {

        badLanguage =new BadLanguage();
            assertFalse(badLanguage.badLanguageWords("dog"));

    }

    @Test
    public void badLanguageWordsTest2() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {

        badLanguage =new BadLanguage();
        assertTrue(badLanguage.badLanguageWords("bitch"));

    }

    @Test
    public void badLanguageWordsTest3() throws ParserConfigurationException, SAXException, IOException, JSONException, SQLException {

        badLanguage =new BadLanguage();
        assertFalse(badLanguage.badLanguageWords("good"));

    }



}


