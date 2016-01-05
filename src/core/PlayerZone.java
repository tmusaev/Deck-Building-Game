package core;

import java.util.ArrayList;

public class PlayerZone {
    ArrayList<Card> zone = new ArrayList();
    
    public synchronized void addCard(Card c)
    {
        zone.add(c);
    }
    
    public synchronized Card peek(int i)
    {
        return zone.get(i);
    }
    
    public synchronized void remove(int i)
    {
        zone.remove(i);
    }
    
    public synchronized ArrayList<Card> getZone() {
        return zone;
    }
    
    public synchronized void removeAll()
    {
        zone.removeAll(zone);
    }
    
    public synchronized int count()
    {
        return zone == null ? 0 : zone.size();
    }
    
    public synchronized ArrayList<String> getString()
    {
        ArrayList<String> list = new ArrayList();
        for(Card c:zone)
        {
            list.add(c.name);
        }
        return list;
    }
    
}
