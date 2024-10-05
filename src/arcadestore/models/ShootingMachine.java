package arcadestore.models;

import arcadestore.models.ArcadeEnums.WeaponType;
import java.text.DecimalFormat;

/**
 *
 * @author Catalina
 */
public class ShootingMachine extends Machine {
        
    private WeaponType weaponType;
    private int numberOfWeapons;
    private boolean hasBluetooth;
    
    public ShootingMachine(MachineBuilder builder){
        super(builder);      
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType typeOfWeapon) {
        this.weaponType = typeOfWeapon;
    }

    public int getNumberOfWeapons() {
        return numberOfWeapons;
    }

    public void setNumberOfWeapons(int numberOfWeapons) {
        this.numberOfWeapons = numberOfWeapons;
    }    

    public boolean hasBluetooth() {
        return hasBluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.hasBluetooth = bluetooth;
    }   
    
    @Override
    public void estimatePrice() {
        price = mainCalculation() + (weaponType.getPrice() * numberOfWeapons);
    }
    
    @Override
    public String toString() {       
        DecimalFormat format = new DecimalFormat("##,###,###.00");

        return "Dance Revolution:\n" + String.format("%-12s | %-6s | %-12s | %-20s | %-2s GB "
                + "| Processor: %-20s | %4.2s Wh | %3.2s Kg "
                + "| Dim. (%-3s cm x %-3s cm x %-3s cm) | %15s x %-2s "
                + "| %-15s | $...%14s", 
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
                weaponType.getValue(),
                numberOfWeapons,
                (hasBluetooth ? "With bluetooth" : "No bluetooth"),
                format.format(price));
    }
}
