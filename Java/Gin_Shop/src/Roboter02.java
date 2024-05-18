public class Roboter02 {
    private boolean isOn;
    private Box box;
    private StorageArea storageArea;

    public Roboter02(StorageArea storageArea) {
        this.storageArea = storageArea;
    }

    public void on() {
        isOn = true;
        System.out.println("Robot 02 turned on!");
    }

    public void off() {
        isOn = false;
        System.out.println("Robot 02 turned off!");
    }

    public void takeBox(Box box){
        System.out.println("Robot 02 recieved box " + box.hashCode());

        storageArea.addBox(box);
        System.out.println("Robot 02 put Box " + box.hashCode() + " into Storage" );
    }
}
