package core;
import cards.Basic.Punch;
import cards.Basic.Vulnerability;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Collections;

public class PlayerDeck extends Deck{
    
    public PlayerDeck()
    {
        Punch punch = new Punch();
        Vulnerability vulnerability = new Vulnerability();
        
        for(int i = 0; i < 7; i++)
        {
            deck.add(punch);
        }
        
        for(int i = 0; i < 3; i++)
        {
            deck.add(vulnerability);
        }
        
        Collections.shuffle(deck);
    }
 
}
