package core;
import cards.Basic.Weakness;
import java.util.ArrayList;

public class WeaknessStack {
    ArrayList<Card> list = new ArrayList();
    
    public WeaknessStack()
    {
        Weakness weakness = new Weakness();
        for(int i = 0; i < 20; i++)
            list.add(weakness);
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
    
    public ArrayList<String> getString()
    {
        ArrayList<String> s = new ArrayList();
        s.add(""+list.size());
        return s;
    }
    
}