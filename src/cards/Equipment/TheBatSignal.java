package cards.Equipment;

import core.Card;
import core.Player;
import java.util.ArrayList;

public class TheBatSignal extends Card{
    
    public TheBatSignal()
    {
        name = "The Bat-Signal";
        cost = 5;
        victoryPoints = 1;
        type = "Equipment";
        expansion = "1DBG";
        quantity = 3;
    }
    
    public void play(Player player)
    {
        player.power = player.power + 1;
        if(hasHero(player.discardPile.getZone()))
        {
            //player.batSignal();
            player.listener.output.println("BATSIGNAL");
        }
    }
    
    public static boolean hasHero(ArrayList<Card> discardPile)
    {
        for(Card c:discardPile)
        {
            if(c.type.equals("Hero"))
                return true;
        }      
        return false;
    }

}
