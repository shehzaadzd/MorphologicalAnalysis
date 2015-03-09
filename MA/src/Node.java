import java.io.Serializable;
import java.util.HashMap;

class Node implements Serializable {           
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Node(char value)  {
        this.value = value;
        children = new HashMap<>();
        bIsEnd = false;
    }    
    public HashMap<Character,Node> getChildren() 
    {   
    	return children;  
    }    
    public char getValue()                           
    {   
    	return value;     
    }    
    public void setIsEnd(boolean val)                
    {   
    	bIsEnd = val;     
    }    
    public boolean isEnd()                           
    {   
    	return bIsEnd;    
    }
       
    private char value;    
    private HashMap<Character,Node> children;
    private boolean bIsEnd;  
}