package arcadestore.models;

import java.util.stream.Stream;

/**
 *
 * @author Catalina Ariza Ardila
 */
public class ArcadeEnums {
    public enum Image_Quality implements IEnumeration {
        SD(1, "Standard definition", 0),
        HD(2, "High definition", 0.1f);
               
        private final int index;
        private final String value;
        private final float percentage;
        private Image_Quality(int index, String value, float percentage) {
            this.index = index;
            this.value = value;
            this.percentage = percentage;
        }

        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public String getValue() {
            return value;
        }
        
        public float getPercentage() {
            return percentage;//
        }
    }
    
    public enum Game_Category implements IEnumeration {
        FIGHTING(1, "Fight"),
        PLATFORM(2, "Platform"),
        RACING(3, "Racing"),
        SHOOTING(4, "Shooting"),
        VIRTUAL_REALITY(5, "Virtual Reality"),
        DANCE(6, "Dance");
        
        private final int index;
        private final String value;
        private Game_Category(int index, String value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getIndex() {
            return index;
        }
    }
    
    public enum Game_Type implements IEnumeration {
        CLASSIC(1, "Classic"),
        MODERN(2, "Modern");
        
        private final int index;
        private final String value;
        private Game_Type(int index, String value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getIndex() {
            return index;
        }
    }
     
    public enum Processor_Type implements IEnumeration {
        INTEL(1, "Intel core i7-13700", 500000),
        RYZEN(2, "AMD Ryzen 5 5560U", 300000);
        
        private final int price;
        private int index;
        private String value;
        
        private Processor_Type(int index, String value, int price){
            this.index = index;
            this.value = value;
            this.price = price;
        }
                
        @Override
        public int getIndex() {
            return index;           
        }
        
        public int getPrice() {
            return price;
        }

        @Override
        public String getValue() {
            return value;
        }
    }
    
    public enum Controller_Type implements IEnumeration {            
        WHEEL(1,"Wheel", 100000),
        JOYSTICK(2, "Joystick", 80000 ),
        BUTTONS(3, "Buttons", 50000  );

        private final int price;
        private final int index;
        private final String value;

        private Controller_Type(int index, String value, int price){
            this.index = index;
            this.value = value;
            this.price = price;
        }


        @Override
        public int getIndex() {              
            return index;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String getValue() {
            return value;
        }

    }

    public enum WeaponType implements IEnumeration {            
        LIGHT_GUN(1,"Light gun", 30, 20000),
        SUBMACHINE_GUN(2, "Submachine gun", 60, 40000 ),
        CANNON(3, "Cannon", 80, 60000  );

        private final int price;
        private final int index;
        private final String value;
        private final int weaponSize;

        private WeaponType(int index, String value, int weaponSize, int price){
            this.index = index;
            this.value = value;
            this.price = price;
            this.weaponSize = weaponSize;
        }


        @Override
        public int getIndex() {              
            return index;
        }

        public int getPrice() {
            return price;
        }
        public int getWeaponSize() {
            return weaponSize;
        }

        @Override
        public String getValue() {
            return value;
        }

    }
    
    public enum Glasses_Type implements IEnumeration {
        OCULUS_QUEST_2(1, "Oculus Quest 2 (1832 x 1920)", 80000),
        VALVE_INDEX(2, "Valve Index (1440 x 1600)", 100000),
        PICO_NEO_4(3, "Pico Neo 4 (2160 x 2160)", 50000);

        private final int index;
        private final String value;
        private final int price;

        private Glasses_Type(int index, String value,int price){
            this.index = index;
            this.value = value;
            this.price = price;            
        }
        
        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public String getValue() {
            return value;
        }
        
        public int getPrice() {
            return price;
        }            
    }
    
    public enum ArrowCardinalities_Type implements IEnumeration {
        CROSS(1, "Cross type", 30000),
        DIAGONAL(2, "Diagonal type", 20000),
        FULL(3, "Full type", 50000);

        private final int index;
        private final String value;
        private final int controlsPrice;
        
        private ArrowCardinalities_Type(int index, String value,int controlsPrice){
            this.index = index;
            this.value = value;
            this.controlsPrice = controlsPrice;            
        }
        
        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public String getValue() {
            return value;
        }
        
        public int getPrice() {
            return controlsPrice;
        }            
    }
    
    public enum Machine_Color implements IEnumeration {
        BLUE(1, "Blue"),
        GREEN(2, "Green"),
        BLACK(3, "Black"),
        RED(4, "Red"),
        WHITE(5, "White");

        private final int index;
        private final String value;        
        
        private Machine_Color(int index, String value){
            this.index = index;
            this.value = value;                       
        }
        
        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public String getValue() {
            return value;
        }                        
    }
    
    /**
     * Gets the desired Enum based on the values and the index in parameters 
     * @param values - Values to read
     * @param index - Index of the Enum
     * @return the Enum
     * @throws IllegalArgumentException when the Enum does not exists
     */
    public static IEnumeration getEnumFromIndex(IEnumeration[] values, int index) {
        return Stream.of(values)
                .filter(v -> v.getIndex() == index)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }    
    
    /**
     * Gets the desired Enum based on the values and the value in parameters 
     * @param values - Values to read
     * @param value - Value of the enum
     * @return the Enum
     * @throws IllegalArgumentException when the Enum does not exists
     */
    public static IEnumeration getEnumFromValue(IEnumeration[] values, String value) {
        return Stream.of(values)
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
