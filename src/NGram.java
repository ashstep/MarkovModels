import java.util.Arrays;
import java.util.List;

public class NGram implements Comparable<NGram> {
	private String[] contents;
	private String separator;
	//n gram holds previous "n" characters/words -> u can see where it occurs in the text 
	//use it determine next possible states
	public NGram(List<String> source, String sep) {
		separator = sep;
		contents = new String[source.size()];    
		contents = Arrays.copyOf(source.toArray(new String[source.size()]), source.size());
	}
	
	//comparing each item to see if they're the same (return true) or not (false)
	public int compareTo(NGram other) {		
		for (int i = 0; i < this.contents.length && i<other.contents.length; i++){
			if (!(this.contents[i].compareTo(other.contents[i]) == 0)){
				return this.contents[i].compareTo(other.contents[i]);
			}
		}
		return this.contents.length - other.contents.length;
		}

	public boolean equals(Object o) {
		if (o instanceof NGram){
			NGram var = (NGram) o;
			if (var.contents.length == this.contents.length){
				//loop over each words
				for (int i = 0; i < var.contents.length; i++){
					if (var.contents[i].equals(this.contents[i])) {
						continue;
					}
					else{
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		int numCode = 0;
		for (int i = 0; i < this.contents.length; i++) {
			numCode += this.contents[i].hashCode()*(i * i * i % 30);
		}
		return numCode;
	}

	public String toString() {
		String end = this.contents[(contents.length)-1];
		return end + separator;
	}
}