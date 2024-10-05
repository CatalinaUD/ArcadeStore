package arcadestore.models;

import arcadestore.models.ArcadeEnums.Controller_Type;
import java.text.DecimalFormat;

/**
 *
 * @author Catalina
 */
public class RacingMachine extends Machine { 
    private Controller_Type controller; 
    private int numberOfControllers;
    
    public RacingMachine(MachineBuilder builder){
        super(builder);
    }

    public Controller_Type getController() {
        return controller;
    }

    public void setController(Controller_Type controller) {
        this.controller = controller;
    }

    public int getNumberOfController() {
        return numberOfControllers;
    }

    public void setNumberOfControllers(int numberOfControllers) {
        this.numberOfControllers = numberOfControllers;
    }
    
    @Override
    public void estimatePrice() {
        price = mainCalculation() + (controller.getPrice() * numberOfControllers);
    }
    
    @Override
    public String toString() {       
        DecimalFormat format = new DecimalFormat("##,###,###.00");

        return "Dance Revolution:\n" + String.format("%-12s | %-6s | %-12s | %-20s | %-2s GB "
                + "| Processor: %-20s | %4.2s Wh | %-3.2s Kg "
                + "| Dim. (%-3s cm x %-3s cm x %-3s cm) | %8s x %-2s | $...%14s", 
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
                controller.getValue(),
                numberOfControllers,
                format.format(price));
    }
}

