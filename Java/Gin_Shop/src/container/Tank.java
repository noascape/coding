package container;

public class Tank {
    public String content;
    private final int length = 20;
    private final int width = 25;
    private final int height = 27;

    public Tank(){
        content = "g".repeat(1000000);
    }      //13500

    public boolean drainTankAmount(){
        int drainAmount = content.length()-500;

        if(drainAmount < 0){
            return false;
        }

        content = content.substring(500);
        return true;
    }
}
