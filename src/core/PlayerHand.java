package core;

import java.util.ArrayList;

public class PlayerHand extends PlayerZone{
    
    public PlayerHand(PlayerDeck deck)
    {
        for(int i = 0; i < 5; i++)
        {
            Card c = deck.giveTopCard();
            zone.add(c);
        }
    }
    
    public synchronized void fill(PlayerDeck deck)
    {
        while(zone.size() < 5)
        {
            Card c = deck.giveTopCard();
            zone.add(c);
        }
    }
    
    public synchronized Card getCard(int id)
    {
        Card c = zone.get(id);
        zone.remove(id);
        return c;
    }
    
    public synchronized Card peekCard(int id)
    {
        Card c = zone.get(id);
        return c;
    }
    
}
