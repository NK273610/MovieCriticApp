package com.dalhousie.moviecritic.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class MovieRecommend implements IMovieRecommend {

    public float Compute_Scores(HashMap<String,Integer>compare_to,HashMap<String,Integer>compare_from)
    {
        List<String> list1 = new ArrayList<>(compare_from.keySet());
        List<String> list2 = new ArrayList<>(compare_to.keySet());
        float total=0,num_common=0;
        total=getUniqueCount(list1,list2);
        try {
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (list1.get(i).equalsIgnoreCase(list2.get(j)) &&
                            (compare_from.get(list1.get(i)) == 1)
                            && (compare_to.get(list2.get(j)) == 1)) {
                        num_common = num_common + 1;
                    } else if (list1.get(i).equalsIgnoreCase(list2.get(j))
                            && (compare_from.get(list1.get(i)) == 0)
                            && (compare_to.get(list2.get(j)) == 0)) {

                        num_common = num_common + 1;
                    } else if (list1.get(i).equalsIgnoreCase(list2.get(j)) == false) {
                        continue;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return num_common/total;
    }

    public int getUniqueCount(List<String> list1, List<String> list2) throws NullPointerException
    {
        Set<String> union = new HashSet<String>(list1);
        Set<String> intersection = new HashSet<String>(list1);

        try {
            union.addAll(list2);
            intersection.retainAll(list2);
            union.removeAll(intersection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return union.size()+intersection.size();
    }
}
