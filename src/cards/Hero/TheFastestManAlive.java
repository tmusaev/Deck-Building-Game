package cards.Hero;

import core.Card;
import core.Player;

public class TheFastestManAlive extends Card{
    
    public TheFastestManAlive()
    {
        name = "The Fastest Man Alive";
        cost = 5;
        victoryPoints = 1;
        type = "Hero";
    }
    
    public void play(Player player)
    {
        player.drawCard();
        player.drawCard();
        //player.showHand();
    }
    
}
