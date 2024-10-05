package arcadestore.models;

import arcadestore.models.ArcadeEnums.Glasses_Type;
import java.text.DecimalFormat;

/**
 *
 * @author Catalina
 */
public class VirtualRealityMachine extends Machine {    
    private Glasses_Type glassesType;

    public VirtualRealityMachine(MachineBuilder builder) {
       super(builder);
    }

    public Glasses_Type getGlassesType() {
        return glassesType;
    }

    public void setGlassesType(Glasses_Type glassesType) {
        this.glassesType = glassesType;
    }
    
    @Override
    public void estimatePrice() {
        price = mainCalculation() + glassesType.getPrice();
    }
    
    @Override
    public String toString() {       
        DecimalFormat format = new DecimalFormat("##,###,###.00");

        return "Virtual Reality Machine:\n" + String.format("%-12s | %-6s | %-12s | %-20s | %-2s GB "
                + "| Processor: %-20s | %4.2s Wh | %3.2s Kg "
                + "| Dim. (%-3s cm x %-3s cm x %-3s cm) | %-30s | $...%14s", 
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
                glassesType.getValue(),
                format.format(price));
    }
}
