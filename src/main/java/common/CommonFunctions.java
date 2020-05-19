package common;

import enumerator.StatusProcess;

import javax.swing.*;
import java.io.File;


public class CommonFunctions {

    public Boolean ValidateFileExit(String file)
    {
        if (new File(file).isFile()) {
            return true;
        }else
            return false;
    }

    public String returnMessage(StatusProcess messageStatus, String path){

        switch (messageStatus) {
            case FILEDONTEXIST:
                return "The file does not exit, please verify the path: " + path;
            case FILEGENERATE:
                return "Success, file generated: " + path;
            case WRONGEXTENSION:
                return "One of your images is with an incorrect extension, please verify.";
            case PROBLEMDURINGTHEPROCESS:
                return "Occurred a problem during the process, please call the support.";
            default:
                return "Unknown operations ";
        }

    }

    public void showMessage(StatusProcess messageStatus, String path){
        JOptionPane.showMessageDialog(null, returnMessage(messageStatus, path));
    }
}
