package cards.SuperVillain;

import core.Card;
import core.Player;

public class RasAlGhul extends Card{
    
    public RasAlGhul()
    {
        name = "Ra's Al Ghul";
        cost = 8;
        victoryPoints = 4;
        type = "Villain";
    }
    
    public void play(Player player)
    {
        player.power = player.power + 3;
        //figure out a way to put it on the bottom of the deck 
        //instead of discard pile
    }
    
}

