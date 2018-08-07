package com.dalhousie.moviecritic.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
@Service
public class BadLanguage  implements IBadLanguage {

	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("swearWords.csv").getFile());
	
	public boolean badLanguageWords(String input) throws IOException {

		input = input.toLowerCase();
		String[] inputarr=input.split(" ");
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);
		String line = "";
		ArrayList<String> words=new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			String[] content = line.split(",");;

			for(String badword:content)
			{
				words.add(badword);
			}
		}
		reader.close();
		for(String giveninput:inputarr)
		{
			for(int i=0;i<words.size();i++)
			{
				if(giveninput.equalsIgnoreCase(words.get(i))) {
                    return true;
                }
			}
		}

		return false;
		}
}
