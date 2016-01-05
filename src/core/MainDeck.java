package core;
import cards.Equipment.*;
import cards.Hero.*;
import cards.SuperPower.*;
import cards.Villain.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainDeck {
    ArrayList<Card> list = new ArrayList();
    
    public MainDeck()
    {
        TheFastestManAlive thefastestmanalive = new TheFastestManAlive();
        Catwoman catwoman = new Catwoman();
        TheBatSignal thebatsignal = new TheBatSignal();
        Bane bane = new Bane();
        SuperSpeed superspeed = new SuperSpeed();
        
        for(int i = 0; i < thefastestmanalive.quantity; i++)
            list.add(thefastestmanalive);
        for(int i = 0; i < catwoman.quantity; i++)
            list.add(catwoman);
        for(int i = 0; i < thebatsignal.quantity; i++)
            list.add(thebatsignal);
        for(int i = 0; i < bane.quantity; i++)
            list.add(bane);
        for(int i = 0; i < superspeed.quantity; i++)
            list.add(superspeed);
        
        
        Collections.shuffle(list);
    }
    
    public Card getCard()
    {
        Card c = list.get(0);
        list.remove(0);
        return c;
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
