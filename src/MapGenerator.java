import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class MapGenerator implements TextGenerator{
	Random rand = new Random();
	MapGenerator() {
		rand = new Random();
		}
	 MapGenerator(int n) {
		    rand = new Random(n);
		    }
	 //create text and the map we will use
	 TrainingText text;
	 HashMap<NGram, ArrayList<NGram>> map;
	 public int train(Scanner source, String delimiter, int k){
		 text = new TrainingText(source, delimiter, k);
		 map = new HashMap<NGram, ArrayList<NGram>>();
		 //loop through each item in the text
		 for (int i = 0 ; i < text.size()-1 ; i++){
			if(i<text.size()-1){    
				if (!(map.containsKey(text.get(i)))){
					ArrayList<NGram> list = new ArrayList<NGram>();
				 list.add(text.get(i+1));
				 map.put(text.get(i), list);
			 }
			 else{
				 //if its not there already, add it to the map
				 ArrayList<NGram> list = map.get(text.get(i));
					 list.add(text.get(i+1));
					 map.put(text.get(i), list);
					 }
				}
			}
		 return map.keySet().size();
		 }
	 
	 //add each thing to the arraylist and append the next item to the final string
	 public String generateText(int length){
		 NGram seed = text.get(rand.nextInt(text.size()));
		 StringBuilder string1 = new StringBuilder(); 
			for(int i = 0; i < length; i++){
				ArrayList<NGram> seedlist = map.get(seed);				
				int randomNum = rand.nextInt(seedlist.size());
				seed = seedlist.get(randomNum);
				string1.append(seed.toString());
				}
			return string1.toString();
			}
	 }
