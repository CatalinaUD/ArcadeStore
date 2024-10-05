package arcadestore.models;

/**
 *
 * @author Catalina
 */
public enum Machine_Material implements IEnumeration {
        ALLUMINIUM(1, "Alluminium", -0.05f, 0.1f, 0),
        CARBON_FIVER(2, "Carbon fiver", -0.15f, 0.2f, -0.1f),
        PLASTIC(3, "Plastic", -0.03f, 0.1f, -0.25f),
        WOOD(4, "Wood", 0.1f, -0.05f, 0.15f);
        
        private final int index;
        private final String value;
        private final float increaseWeighPercent;
        private final float increasePricePercent;
        private final float increasePowerPercent;
        
        private Machine_Material(int index, String value, float increaseWeighPercent, 
                float increasePricePercent, float increasePowerPercent) {
            this.index = index;
            this.value = value;
            this.increaseWeighPercent = increaseWeighPercent;
            this.increasePricePercent = increasePricePercent;
            this.increasePowerPercent = increasePowerPercent;
        }               

        @Override
        public String getValue() {
            return value;
        }

        public float getIncreaseWeighPercent() {
            return increaseWeighPercent;
        }

        public float getIncreasePricePercent() {
            return increasePricePercent;
        }

        public float getIncreasePowerPercent() {
            return increasePowerPercent;
        }                

        @Override
        public int getIndex() {
            return index;
        }
    }
