import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BruteGenerator implements TextGenerator {
	Random rand;
	TrainingText text;
	public BruteGenerator(){
		rand = new Random();
	}
	public BruteGenerator(int n) {
		rand = new Random(n);
		}	
	//create training text based on imported class and find the size
	public int train(Scanner source, String delimiter, int k){
		text = new TrainingText(source, delimiter, k);
		return text.size();
	}
	//find the length that we will use to add the character that comes after the given pattern
	public String generateText(int length){
		int num = rand.nextInt(text.size());
		NGram seed = text.get(num);
		StringBuilder string1 = new StringBuilder();
		//for each item in the text and each character -> double for loop
		for(int i = 0; i < length; i++){
			ArrayList<NGram> afterTheSeed = new ArrayList<NGram>();
			for (int j = 0; j < text.size()-1; j++){
				NGram abc = text.get(j);
					if (abc.equals(seed)){
						NGram a = text.get(j+1);
						afterTheSeed.add(a);
					}
				}
			num = rand.nextInt(afterTheSeed.size());
			seed = afterTheSeed.get(num);
			string1.append(seed.toString());
			}
		return string1.toString();
		}
}