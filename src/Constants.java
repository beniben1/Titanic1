public class Constants {
    public static final String PATH_TO_DATA_FILE = "src\\data\\titanic.csv";
    public static final String PATH_TO_FILE_COUNT = "src/data/currentFileToWrite.txt";
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 700;
    public static final String[] PASSENGER_CLASS_OPTIONS = { "All", "1st", "2nd", "3rd"};
    public static final String[] PASSENGER_GENDER_OPTIONS = { "all", "male", "female"};
    public static final String[] PORTS_EMBARKED_OPTIONS = {"All","C","Q","S"};
    public static final String[] STATISTICS_PARAMETER_OPTIONS = {"Class","Gender","Age Group","Has Family Members","Fare","Port"};
    public static final int MARGIN_FROM_TOP = 10;
    public static final int MARGIN_FROM_LEFT = 5;
    public static final int LABEL_WIDTH = 190;
    public static final int LABEL_HEIGHT = 30;
    public static final int COMBO_BOX_WIDTH = 80;
    public static final int COMBO_BOX_HEIGHT = 30;
    public static final int BUTTON_WIDTH = 80;
    public static final int BUTTON_HEIGHT = 30;
    public static final int AGE_GROUP_DIFFERENCE = 10;
    public static final int MAX_AGE_GROUP = AGE_GROUP_DIFFERENCE * 5 + 1;
    public static final int FARE_GROUP_DIFFERENCE = 10;
    public static final int MAX_FARE_GROUP = FARE_GROUP_DIFFERENCE * 3 + 1;
}
