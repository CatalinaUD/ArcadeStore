package arcadestore.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Catalina
 */
public class Customer {
    
    private String name; 
    private String lastname; 
    private String adress;
    private String phone;
    private List<Machine> machines; 
    
    public Customer(String name, String lastname, String adress, String phone){
        this.name = name;
        this.lastname = lastname;
        this.adress = adress;
        this.phone = phone; 
        this.machines = new ArrayList<>(); //Lista vacía
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
   
    public void addMachine(Machine machine) {
        machines.add(machine);
    }
}
