package arcadestore.models;

import arcadestore.models.ArcadeEnums.Machine_Color;
import arcadestore.models.ArcadeEnums.Image_Quality;
import arcadestore.models.ArcadeEnums.Processor_Type;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author Catalina Ariza Ardila
 * 
 */
public class Machine implements IMachine {
    protected final int WITH_BATTERY = 100000;
    protected final int NO_BATTERY = 50000;
    protected Machine_Material material; 
    protected int basePrice;
    protected int price; 
    protected boolean battery;
    protected List<Game> gameList;
    protected Image_Quality imageQuality;
    protected int[] dimensions;
    protected float weight;
    protected float powerConsumption;
    protected int memory;
    protected Processor_Type processor;  
    protected Machine_Color color;
    
    protected Machine(MachineBuilder builder) {
        this.material = builder.material;
        this.basePrice = builder.basePrice;
        this.price = builder.price;
        this.battery = builder.hasBattery;
        this.gameList = builder.gameList;
        this.imageQuality = builder.imageQuality;
        this.weight = builder.weight;
        this.powerConsumption = builder.powerConsumption;
        this.memory = builder.memory;
        this.processor = builder.processor;
        this.dimensions = builder.dimensions;
        this.color = builder.color;
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

    public boolean isBattery() {
        return battery;
    }

    public void setBattery(boolean battery) {
        this.battery = battery;
    }

    public Image_Quality getImageQuality() {
        return imageQuality;
    }

    public void setImageQuality(Image_Quality imageQuality) {
        this.imageQuality = imageQuality;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public int[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(float powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public Processor_Type getProcessor() {
        return processor;
    }

    public void setProcessor(Processor_Type processor) {
        this.processor = processor;
    }   

    public Machine_Color getColor() {
        return color;
    }

    public void setColor(Machine_Color color) {
        this.color = color;
    }    
    
    @Override
    public void estimatePrice() {
        price = mainCalculation();
    }

    @Override
    public String toString() {       
        DecimalFormat format = new DecimalFormat("##,###,###.00");

        return String.format("%-12s | %-6s | %-12s | %-20s | %-2s GB "
                + "| Processor: %-20s | %4.2s Wh | %3.2s Kg | Dim. (%-3s cm x %-3s cm x %-3s cm) | $...%14s", 
                material.getValue(),
                color.getValue(),
                (battery ? "With battery" : "No battery"),
                imageQuality.getValue(),
                memory,
                processor.getValue(),
                powerConsumption,
                weight,
                dimensions[0],
                dimensions[1],
                dimensions[2],
                format.format(price));
    }
    
    /**
     * Modiffies the value of all attributes affected by material:
     * - Power Consumption
     * - Base price
     * - Weight
     */
    protected void modifyAttributesAffectedByMaterial() {
        powerConsumption += (powerConsumption * material.getIncreasePowerPercent());
        basePrice += (basePrice * material.getIncreasePricePercent());
        weight += (weight) * material.getIncreaseWeighPercent();
    }
    
    /**
     * Calculates the price of the Machine class
     * @return total price
     */
    protected int mainCalculation() {
        basePrice += (basePrice * imageQuality.getPercentage());
        modifyAttributesAffectedByMaterial();
        int gamesPrice = getAllGamesPrice();
        int total = basePrice 
                + processor.getPrice()
                + (battery ? WITH_BATTERY : NO_BATTERY)
                + gamesPrice;
        return total;
    }
    
    /**
     * Gets the price of all games in the machine
     * @return games total price
     */
    protected int getAllGamesPrice() {
        int total = 0;
        for (Game game : gameList) {
            total += game.getPrice();
        }
        
        return total;
    }
    
    public static class MachineBuilder {
        private final int basePrice;
        private final int memory;
        private final Processor_Type processor;
        private final float weight;
        private Machine_Material material;         
        private int price; 
        private boolean hasBattery;
        private List<Game> gameList;
        private Image_Quality imageQuality;        
        private float powerConsumption;
        private int[] dimensions;
        private Machine_Color color;
        
        
        public MachineBuilder(int basePrice, float weight, int memory, Processor_Type processor) {
            this.basePrice = basePrice;
            this.weight = weight;
            this.memory = memory;
            this.processor = processor;
        }
        
        public MachineBuilder material(Machine_Material material) {
            this.material = material;
            return this;
        }
        
        public MachineBuilder price(int price) {
            this.price = price;
            return this;
        }
        
        public MachineBuilder hasBattery(boolean hasBattery) {
            this.hasBattery = hasBattery;
            return this;
        }
        
        public MachineBuilder gameList(List<Game> games) {
            this.gameList = games;
            return this;
        }
        
        public MachineBuilder imageQuality(Image_Quality imageQuality) {
            this.imageQuality = imageQuality;
            return this;
        }
        
        public MachineBuilder powerConsumption(int powerConsumption) {
            this.powerConsumption = powerConsumption;
            return this;
        }
        
        public MachineBuilder dimensions(int[] dimensions) {
            this.dimensions = dimensions;
            return this;
        }
        
        public Machine build() {
            return new Machine(this);
        }
        
        public MachineBuilder color(Machine_Color color){
            this.color = color;
            return this;
        }
    }
}
