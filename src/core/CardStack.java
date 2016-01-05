package core;
import java.util.ArrayList;

public class CardStack {
    ArrayList<Card> cardStack = new ArrayList();
    
    public Card giveCard()
    {
        Card c = cardStack.get(0);
        cardStack.remove(0);
        return c;  
    }
    
    public Card peekCard()
    {
        Card c = cardStack.get(0);
        return c;
    }
    
    public int countStack()
    {
        return cardStack.size();
    }
    
}
