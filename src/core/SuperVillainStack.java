package core;
import cards.SuperVillain.*;
import java.util.ArrayList;

public class SuperVillainStack {
    ArrayList<Card> list = new ArrayList();
    
    public SuperVillainStack()
    {
        RasAlGhul rasalghul = new RasAlGhul();
        list.add(rasalghul);
        
        //add the rest, shuffle, and append to cardStack
    }
    
    public Card getCard()
    {
        Card c = list.get(0);
        list.remove(0);
        return c;
    }
    
    public Card peek()
    {
        return list.get(0);
    }
    
    public int count()
    {
        return list == null ? 0 : list.size();
    }
    
    public ArrayList<String> getString()
    {
        ArrayList<String> list2 = new ArrayList();
        for(Card c:list)
        {
            list2.add(c.name);
        }
        return list2;
    }
    
}
