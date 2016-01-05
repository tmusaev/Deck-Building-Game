package core;
import cards.Basic.Kick;
import java.util.ArrayList;

public class KickStack {
    ArrayList<Card> list = new ArrayList();
    
    public KickStack()
    {
        Kick kick = new Kick();
        for(int i = 0; i < 16; i++)
            list.add(kick);
    }
    
    public Card getCard()
    {
        Card c = list.get(0);
        list.remove(0);
        return c;
    }
    
    public int count()
    {
        return list.size();
    }
    
    public Card peek()
    {
        return list.get(0);
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
