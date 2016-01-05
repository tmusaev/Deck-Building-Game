package core;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> deck = new ArrayList();
    
    public Card giveTopCard()
    {
        Card c = deck.get(0);
        deck.remove(0);
        return c; 
    }
    
    public Card peekTopCard()
    {
        Card c = deck.get(0);
        return c; 
    }
    
    public void shuffle()
    {
        Collections.shuffle(deck);
    }
    
    public int count()
    {
        return deck == null ? 0 : deck.size();
    }
    
    public void refill(ArrayList<Card> pile)
    {
        for(Card c:pile)
            deck.add(c);
        Collections.shuffle(deck);
    }
    
    public ArrayList<String> getString()
    {
        ArrayList<String> list = new ArrayList();
        for(Card c:deck)
        {
            list.add(c.name);
        }
        return list;
        
        
        //ArrayList<String> s = new ArrayList();
        //s.add(""+deck.size());
        //return s;
    }
    
}
