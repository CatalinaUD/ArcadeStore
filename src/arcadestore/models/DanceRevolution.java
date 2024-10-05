package arcadestore.models;

import arcadestore.models.ArcadeEnums.ArrowCardinalities_Type;
import java.text.DecimalFormat;

/**
 *
 * @author Catalina
 */
public class DanceRevolution extends Machine {
    private ArrowCardinalities_Type arrowCardinalities;
    
    public DanceRevolution(MachineBuilder builder){
        super(builder);
    }

    public ArrowCardinalities_Type getArrowCardinalities() {
        return arrowCardinalities;
    }

    public void setArrowCardinalities(ArrowCardinalities_Type arrowCardinalities) {
        this.arrowCardinalities = arrowCardinalities;
    }
    
        
    
    @Override
    public void estimatePrice() {
        price = mainCalculation() + arrowCardinalities.getPrice();
    }
     
    @Override
    public String toString() {       
        DecimalFormat format = new DecimalFormat("##,###,###.00");

        return "Dance Revolution:\n" + String.format("%-12s | %-6s | %-12s | %-20s | %-2s GB "
                + "| Processor: %-20s | %4.2s Wh | %3.2s Kg "
                + "| Dim. (%-3s cm x %-3s cm x %-3s cm) | %-15s | $...%14s", 
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
                arrowCardinalities.getValue(), 
                format.format(price));
    }
}
