package arcadestore.models;

import arcadestore.models.ArcadeEnums.Game_Category;
import arcadestore.models.ArcadeEnums.Game_Type;


/**
 *
 * @author Catalina
 */
public class Game {        
    private String name;
    private Game_Category category;
    private Game_Type type;
    private boolean multiplayer;
    private int nbCoins;
    
    public Game(Game_Category category, String name, boolean multiplayer, Game_Type type, int nbCoins) {
        this.category = category;
        this.multiplayer = multiplayer;
        this.name = name;
        this.type = type;
        this.nbCoins = nbCoins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game_Category getCategory() {
        return category;
    }

    public void setCategory(Game_Category category) {
        this.category = category;
    }

    public Game_Type getType() {
        return type;
    }

    public void setType(Game_Type type) {
        this.type = type;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public int getNbCoins() {
        return nbCoins;
    }

    public void setNbCoins(int nbCoins) {
        this.nbCoins = nbCoins;
    }

    @Override
    public String toString() {
        return String.format("%-32s | Category: %-8s | Type: %-7s | Multiplayer : %-3s | %-2s", 
                name,
                category.getValue(),
                type.getValue(),
                (multiplayer ? "yes" : "no"),
                nbCoins);
    }   
}
