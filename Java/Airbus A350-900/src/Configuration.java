public enum Configuration {
    INSTANCE;

    public final String fileSeparator = System.getProperty("file.separator");
    public final String userDirectory = System.getProperty("user.dir");
    public final String lineSeparator = System.getProperty("line.separator");

    public final String dataFile = userDirectory + fileSeparator + "data" + fileSeparator + "data.csv";

    public final double MPHtoKMHfactor = 1.609344;
    public final short wingTankCapacity = 27500;
    public final int centralTankCapacity = 86000;
}
