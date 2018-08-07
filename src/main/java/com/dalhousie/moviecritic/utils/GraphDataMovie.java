package com.dalhousie.moviecritic.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphDataMovie implements IMovieData {

    @Autowired
    IMovieRecommend movierecommend;

    @Autowired
    ITfIdfCalculation tfidfcal;

    public GraphDataMovie() {
    }

    public float CalculateRating(List<Float> rating)
    {
        float result=0;
        for (float x :rating)
        {
            result=result+x;
        }

        return result/rating.size();
    }

    public HashMap<String, Float> getAgeGroupCount(List<String> age_group)
    {
        HashMap<String,Float> result=new HashMap<String, Float>();

        for (String age_grp:age_group)
        {
            if(result.containsKey(age_grp))
            {
                float temp=result.get(age_grp);
                result.remove(age_grp);
                result.put(age_grp,temp+1);
            }
            else
            {
                result.put(age_grp,1.0f);
            }
        }
        return result;
    }

    public HashMap<String,Float> getAverageAgeGroupRating(List<String> age_group,List<Float> rating)
    {
        HashMap<String,Float> result=new HashMap<String, Float>();
        HashMap<String,Float> count=getAgeGroupCount(age_group);
        float temp=0.0f;
        for (int i=0;i<age_group.size();i++)
        {
            if(result.containsKey(age_group.get(i)))
            {
                temp=temp+rating.get(i);
                result.remove(age_group.get(i));
                result.put(age_group.get(i),temp);
            }
            else
            {
                result.put(age_group.get(i),rating.get(i));
                temp=rating.get(i);
            }
        }

        for(String age_grp : count.keySet())
        {
            for(String agecount:result.keySet())
            {
                if(age_grp.equalsIgnoreCase(agecount))
                {
                    float tempvar=result.get(age_grp);
                    result.remove(age_grp);
                    result.put(age_grp,tempvar/count.get(age_grp));
                    break;
                }
            }
        }
        return result;
    }


    public HashMap<String,Float> getMovie(HashMap<String,HashMap<String,Integer>> fulllist, String user_id)
    {
        List<String> users= new ArrayList<>(fulllist.keySet());
        HashMap<String,Float> result=new HashMap<>();
        HashMap<String,Integer>compare_from=fulllist.get(user_id);
        if(compare_from != null) {
        	for (int i=0;i<users.size();i++)
            {
                if(user_id.equalsIgnoreCase(users.get(i)))
                {
                    continue;
                }
                else
                {
                    HashMap<String,Integer>compare_to=fulllist.get(users.get(i));
                    float val=movierecommend.Compute_Scores(compare_to,compare_from);
                    result.put(users.get(i),val);
                }
            }
        }

        return result;
    }

    public HashMap<String,Float> CalculateUniqueness(List<String> docs)
    {
        HashMap<String,Float> result=new HashMap<String,Float>();
        List<List<String>> textToWord=tfidfcal.textToWord(docs);
        try {
            for (List<String> doc : textToWord) {
                for (String word : doc) {
                    float tf = tfidfcal.tf(doc, word);
                    float idf = tfidfcal.idf(textToWord, word);
                    float score=tf * (1/idf);
                    if (result.containsKey(word)) {
                        if(result.get(word)<score)
                        {
                            result.remove(word);
                            result.put(word,score);
                        }
                        else
                        {
                            continue;
                        }
                    } else {

                        result.put(word,score);
                    }
                }
            }
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
