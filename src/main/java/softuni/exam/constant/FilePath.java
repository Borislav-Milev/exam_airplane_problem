package softuni.exam.constant;

public class FilePath {

    private FilePath(){}

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\";
    private static final String XML_FILE_PATH = BASE_FILE_PATH + "xml\\";
    private static final String JSON_FILE_PATH = BASE_FILE_PATH + "json\\";

    public static final String PLANES_FILE_PATH = XML_FILE_PATH + "planes.xml";
    public static final String TICKETS_FILE_PATH = XML_FILE_PATH + "tickets.xml";
    public static final String PASSENGERS_FILE_PATH = JSON_FILE_PATH + "passengers.json";
    public static final String TOWNS_FILE_PATH = JSON_FILE_PATH + "towns.json";
}
