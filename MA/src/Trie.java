
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

class Trie implements Serializable{        
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -809544764276347097L;

	public Trie()   {     
    	root = new Node('^');       
    	}
	class comp implements Comparator<String> {
		  public int compare(String o1, String o2) {
		    if (o1.length() > o2.length()) {
		      return 1;
		    } else if (o1.length() < o2.length()) {
		      return -1;
		    } else {
		      return 0;
		    }
		  }
		}
   
    
    public void insert(String word)  {
           
        
        int length = word.length();        
        Node crawl = root;
           
        
        for( int level = 0; level < length; level++)
        {
            HashMap<Character,Node> child = crawl.getChildren();            
            char ch = word.charAt(level);
               
            
            if( child.containsKey(ch))
                crawl = child.get(ch);
            else   
            {              
                Node temp = new Node(ch);
                child.put( ch, temp );
                crawl = temp;
            }
        }
           
        
        crawl.setIsEnd(true);
    }
    
    public void crawl(Node currNode, String resString, String word, int len, HashSet<String> result, boolean matching, int[] firstNotmatch)
    {
    	
    	if(currNode.isEnd() && len >0)
		{
			result.add(resString+ currNode.getValue());
			
		}
    	if(len > 1)
    	{
    		
    		
    		
    		HashMap<Character, Node> children = currNode.getChildren();
    		if(matching && word.length() > 1)
    		{
    		firstNotmatch[0]++;
    		String newWord = word.substring(1);
    		Character nextChar = newWord.charAt(0);
    		if(children.containsKey(nextChar) )
    		{
    			crawl(children.get(nextChar), resString+ currNode.getValue(), newWord, len-1, result, true, firstNotmatch);
    		}
    		else
    		{
    			Set<Character> keySet = children.keySet();
    			for(Character i : keySet)
    			{
    				crawl(children.get(i), resString+ currNode.getValue(), newWord, len-1, result, false, firstNotmatch);
    			}
    		}
    		}
    		else
    		{
    			Set<Character> keySet = children.keySet();
    			for(Character i : keySet)
    			{
    				crawl(children.get(i), resString+ currNode.getValue(), word, len-1,  result, false, firstNotmatch);
    			}
    		}
    			
    			
    		
    	}
    }
    @SuppressWarnings("unchecked")
	public List<String> directSet(String word)
    {
    	HashSet<String> result = new HashSet<String>();
    	int[] firstNoMatch = {1};
    	HashMap<Character, Node> children = root.getChildren();
    	crawl(children.get(word.charAt(0)),/*String.valueOf(word.charAt(0))*/ "", word, word.length(), result, true, firstNoMatch);
    	//System.out.println(firstNoMatch[0]);
    	List sortedList = new ArrayList(result);
    	Collections.sort(sortedList, new comp());
    	
    	return sortedList;
    }
    @SuppressWarnings("unchecked")
	public List<String> backtrackSet(String word, int level)
    {
    	HashSet<String> result = new HashSet<String>();
    	int[] firstNoMatch = {1};
    	
    	HashMap<Character, Node> children = root.getChildren();
    	crawl(children.get(word.charAt(0)),/*String.valueOf(word.charAt(0))*/ "", word, word.length(), result, true, firstNoMatch);
    	int fnom=0;
    	
    	while(level > 0)
    	{
    		fnom=firstNoMatch[0];
    		firstNoMatch[0]=1;
    		//System.out.println(word.substring(0, fnom));
    	//crawl(children.get(word.charAt(0)),/*String.valueOf(word.charAt(0))*/ "", word, word.length(), result, true, firstNoMatch);
    	crawl(children.get(word.charAt(0)),/*String.valueOf(word.charAt(0))*/ "", word.substring(0, fnom-1), word.length(),  result, true, firstNoMatch);
    	
    	level--;
    	}
    	List sortedList = new ArrayList(result);
    	Collections.sort(sortedList, new comp());
    	
    	return sortedList;
    }
       
    

       
    private Node root;      
}