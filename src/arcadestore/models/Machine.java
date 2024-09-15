package arcadestore.models;

import arcadestore.models.ArcadeEnums.Image_Quality;
import arcadestore.models.ArcadeEnums.Machine_Material;
import arcadestore.models.ArcadeEnums.Machine_Storage;
import java.text.DecimalFormat;

/**
 *
 * @author Catalina Ariza Ardila
 * 
 */
public class Machine {
    private final int BASE_VALUE_WITH_GAMES = 50000;
    private final int WITH_BATTERY = 100000;
    private final int NO_BATTERY = 50000;
    private Machine_Material material; 
    private int price; 
    private Machine_Storage storage;
    private boolean battery;
    private Game game;
    private Image_Quality imageQuality;

    public Machine() {
        this.material = null;
        this.imageQuality = null;
        this.game = null;
    }
    
    public Machine(Machine_Material material, int price, Machine_Storage storage, boolean battery, Game game, Image_Quality imageQuality) {
        this.material = material;
        this.price = price;
        this.storage = storage;
        this.battery = battery;
        this.game = game;
        this.imageQuality = imageQuality;
    }

    public Machine_Material getMaterial() {
        return material;
    }

    public void setMaterial(Machine_Material material) {
        this.material = material;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Machine_Storage getStorage() {
        return storage;
    }

    public void setStorage(Machine_Storage storage) {
        this.storage = storage;
    }

    public boolean isBattery() {
        return battery;
    }

    public void setBattery(boolean battery) {
        this.battery = battery;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Image_Quality getImageQuality() {
        return imageQuality;
    }

    public void setImageQuality(Image_Quality imageQuality) {
        this.imageQuality = imageQuality;
    }
    
    public void estimatePrice() {
        price = material.getPrice() 
                + storage.getPrice() 
                + imageQuality.getPrice()
                + (battery ? WITH_BATTERY : NO_BATTERY)
                + BASE_VALUE_WITH_GAMES; 
    }

    @Override
    public String toString() {       
        DecimalFormat format = new DecimalFormat("##,###,###.00");

        return String.format("%-12s | %-4s | %-12s | Game: %-32s | %-5s | $...%14s", 
                material.getValue(),
                storage.getValue(),
                (battery ? "With battery" : "No battery"),
                game.getName(),
                imageQuality.getValue(),
                format.format(price));
    }
}
