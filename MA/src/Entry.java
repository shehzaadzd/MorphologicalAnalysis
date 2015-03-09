import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;


public class Entry {
	 public static String input() throws IOException{
	        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
	        String s = br.readLine();
	        return s;
	        }
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Trie dict = null;
		//InputStream is = Entry.class.getResourceAsStream("Trie.txt");
        FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir")+"\\"+"Trie.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        dict = (Trie) in.readObject();
        in.close();
        fileIn.close();
       String s, b;
       int levelBacktrack=0;
        
		while(true)
		{
			levelBacktrack=1;
			System.out.println("Enter word");
			s=input();
			System.out.println(dict.directSet(s));
			System.out.println("Backtrack (y/n)");
			b=input();
			while(b.equals("y"))
			{
				levelBacktrack++;
				System.out.println(dict.backtrackSet(s, levelBacktrack));
				System.out.println("Backtrack (y/n)");
				b=input();
			}
			
			
		}
	}

}
