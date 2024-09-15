package arcadestore.models;

import java.util.stream.Stream;

/**
 *
 * @author Catalina Ariza Ardila
 */
public class ArcadeEnums {
    public enum Image_Quality implements IEnumeration {
        Q_480_P(1, "480p", 12000),
        Q_540_P(2, "540p", 24000),
        Q_720_P(3, "720p", 50000),
        Q_1080_P(4, "1080p", 8000);
        
        private final int index;
        private final String value;
        private final int price;
        private Image_Quality(int index, String value, int price) {
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
    
    public enum Game_Category implements IEnumeration {
        FIGHTING(1, "Fight"),
        PLATFORM(2, "Platform"),
        RACING(3, "Racing"),
        SHOOTING(4, "Shooting");
        
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
    
    public enum Machine_Storage implements IEnumeration {
        ONE_GB(1, "1 GB", 16000),
        THREE_GB(2, "3 GB", 30000),
        FIVE_GB(3, "5 GB", 50000);
        private final int index;
        private final String value;
        private final int price;
        private Machine_Storage(int index, String value, int price) {
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
    
    public enum Machine_Material implements IEnumeration {
        ALLUMINIUM(1, "Alluminium", 400000),
        CARBON_FIVER(2, "Carbon fiver", 500000),
        PLASTIC(3, "Plastic", 250000),
        WOOD(4, "Wood", 300000);
        
        private final int index;
        private final String value;
        private final int price;
        /*Constructor*/
        private Machine_Material(int index, String value, int price) {
            this.index = index;
            this.value = value;
            this.price = price;
        }

        @Override
        public String getValue() {
            return value;
        }
        
        public int getPrice() {
            return price;
        }

        @Override
        public int getIndex() {
            return index;
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
}
