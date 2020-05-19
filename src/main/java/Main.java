import CSV.ManipulateFile;
import enumerator.StatusProcess;
import common.CommonFunctions;



public class Main {

    private static final String CSV_FILE_PATH_READ =  System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop" + System.getProperty("file.separator") + "images.csv";
    private static final String CSV_FILE_PATH_WRITE =  System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop" + System.getProperty("file.separator") + "imagesResult.csv";


    public static void main(String[] args) {

        ManipulateFile manipulateFile = new ManipulateFile();
        StatusProcess messageStatusReturn = manipulateFile.ReadCSV(CSV_FILE_PATH_READ,CSV_FILE_PATH_WRITE);

        String path = " ";

        if (messageStatusReturn == StatusProcess.FILEGENERATE){
            path = CSV_FILE_PATH_WRITE;
        }else if (messageStatusReturn == StatusProcess.FILEDONTEXIST){
            path = CSV_FILE_PATH_READ;
        }

        CommonFunctions showAlert = new CommonFunctions();
        showAlert.showMessage(messageStatusReturn, path);

    }













}


