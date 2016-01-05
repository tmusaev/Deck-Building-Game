package cards.Basic;

import core.Card;
import core.Player;

public class Kick extends Card{
    public Kick()
    {
        name = "Kick";
        cost = 3;
        victoryPoints = 1;
        type = "Super Power";
    }
    
    public void play(Player player)
    {
        player.power = player.power + 2;
    }
}
