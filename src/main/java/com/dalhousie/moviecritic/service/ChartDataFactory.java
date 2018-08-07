package com.dalhousie.moviecritic.service;

import com.dalhousie.moviecritic.utils.IMovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class ChartDataFactory
{
    @Autowired
    IMovieData movieData;

    public IChartData getData(String graphtype)
    {
        if(getMapForCommand().containsKey(graphtype))
        {
            return getMapForCommand().get(graphtype);
        }
        return null;

    }

    public HashMap<String,IChartData> getMapForCommand()
    {
        HashMap<String,IChartData> getMap=new HashMap<>();
        getMap.put("PieChart",new PieData(movieData));
        getMap.put("BarChart",new BarData(movieData));
        getMap.put("WordCloud",new WordData(movieData));
        return getMap;
    }
}
