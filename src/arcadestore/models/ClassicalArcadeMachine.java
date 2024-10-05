/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcadestore.models;

/**
 *
 * @author Catalina
 */
public class ClassicalArcadeMachine extends Machine {    
    public ClassicalArcadeMachine(MachineBuilder builder) {
        super(builder);
    }

    public void makeVibration() {
        System.out.println("bzz bzz bzz");
    }
    
    public void soundRecordAlert() {
        System.out.println("Alert!");
    }

    @Override
    public String toString() {
        return "Classical Arcade Machine: " + super.toString();
    }
}
