package core;
import java.util.ArrayList;

public class LineUp {
    ArrayList<Card> lineUp = new ArrayList();
    
    public LineUp(MainDeck maindeck)
    {
        for(int i = 0; i < 5; i++)
        {
            Card c = maindeck.getCard();
            lineUp.add(c);
        }
    }
    
    public void fill(MainDeck maindeck)
    {
        while(lineUp.size() < 5 && maindeck.count() > 0)
        {
            Card c = maindeck.getCard();
            lineUp.add(c);
        }
    }
    
    public Card buyCard(int id)
    {
        Card c = lineUp.get(id);
        lineUp.remove(id);
        return c;  
    }
    
    public Card peekCard(int id)
    {
        return lineUp.get(id);
    }
    
    public ArrayList<Card> getLineUp() {
        return lineUp;
    }
    
    public ArrayList<String> getString()
    {
        ArrayList<String> list = new ArrayList();
        for(Card c:lineUp)
        {
            list.add(c.name);
        }
        return list;
    }
            
    
}
