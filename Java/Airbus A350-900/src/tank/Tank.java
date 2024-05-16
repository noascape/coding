package tank;
    public abstract class Tank {
        protected float level;
        protected float reserve = 0;
        protected boolean needsReserve;

        public Tank(float capacity, boolean needsReserve){
            this.level = capacity;
            this.needsReserve = needsReserve;
            if (needsReserve){this.reserve = capacity/10;}
        }

        public boolean canConsumeFuel(float amount){
            return level+reserve > amount;
        }
        public void consumeFuel(float amount) throws Exception {
            if (level+reserve > amount){
                level -= amount;
            }else{
                throw new Exception("Cant consume any more fuel!");
            }
        }
}
