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
    private String storyTellingCreator;//?
    private int year;
    private String graphicsCreator;
    private int price;
   
    
    public Game() {}
    
    public Game(Game_Category category, String name, boolean multiplayer, Game_Type type, int nbCoins, int year, String storyTellingCreator, String graphicsCreator,  int price) {
        this.category = category;
        this.multiplayer = multiplayer;
        this.name = name;
        this.type = type;
        this.nbCoins = nbCoins;
        this.price = price;
        this.year = year;
        this.storyTellingCreator = storyTellingCreator;
        this.graphicsCreator = graphicsCreator;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }    

    public String getStoryTellingCreator() {
        return storyTellingCreator;
    }

    public void setStoryTellingCreator(String storyTellingCreator) {
        this.storyTellingCreator = storyTellingCreator;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGraphicsCreator() {
        return graphicsCreator;
    }

    public void setGraphicsCreator(String graphicsCreator) {
        this.graphicsCreator = graphicsCreator;
    }
    
    public String showBasicInformation() {
        return String.format("%-32s | %-4s | Category: %-16s | Storytelling Creator: %-30s "
                + "| Graphics Creator: %-30s", 
                name,
                year,
                category.getValue(),
                storyTellingCreator,
                graphicsCreator);
    }
    
    @Override
    public String toString() {
        return String.format("%-32s | Category: %-16s | Type: %-7s | Multiplayer : %-3s | %-2s", 
                name,
                category.getValue(),
                type.getValue(),
                (multiplayer ? "yes" : "no"),
                nbCoins);
    }   
}

