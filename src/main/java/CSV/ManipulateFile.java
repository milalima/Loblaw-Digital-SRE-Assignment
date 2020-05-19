package CSV;

import enumerator.StatusProcess;
import processImage.ScoreImage;
import com.opencsv.CSVWriter;
import dto.ReadCSV;
import dto.WriteCSV;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import common.CommonFunctions;


public class ManipulateFile {


    public ManipulateFile(){

    }

    public StatusProcess ReadCSV(String fileRead, String fileWrite)   {

        List<ReadCSV> rowList = new ArrayList<ReadCSV>();
        ReadCSV readDto;
        CommonFunctions validation = new CommonFunctions();

        try {

            if (validation.ValidateFileExit(fileRead)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileRead)));
                String line = null;
                reader.readLine(); // skip first line

                while ((line = reader.readLine()) != null) {
                    readDto = new ReadCSV();
                    String[] object = line.split(",");

                    // Only will be add in the object files with the proper extension
                    if ((isImage(object[0])) && (isImage(object[0]))) {
                        readDto.image1 = object[0];
                        readDto.image2 = object[1];
                        rowList.add(readDto);
                    }else
                        return StatusProcess.WRONGEXTENSION;
                }
                reader.close();
                startProcessImage(rowList, fileWrite);

            }else
                return StatusProcess.FILEDONTEXIST;

        } catch (IOException ex) {
             return StatusProcess.PROBLEMDURINGTHEPROCESS;
        }

        return StatusProcess.FILEGENERATE;
    }

    public Boolean writeCSV(List<WriteCSV> listWriteCVS, String fileWrite){

        /* first create file object for file placed at location specified by filepath */
        File file = new File(fileWrite);


        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();
            // Header
            data.add(new String[] { "Image1", "Image2", "Similar", "Elapsed" });

            for (WriteCSV item: listWriteCVS) {
                data.add(new String[] { item.readCSV.image1, item.readCSV.image2, item.similar.toString(), Long.toString(item.elapsed) });
            }

            writer.writeAll(data);
            // closing writer connection
            writer.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    public StatusProcess startProcessImage(List<ReadCSV> readDto, String fileWrite) {

        ScoreImage image = new ScoreImage();

        List<WriteCSV> writeList = new ArrayList<WriteCSV>();

        for (int i = 1; i < readDto.size(); i++) {
            try {
                writeList.add(image.processingImage(readDto.get(i)));
            } catch (Exception ex) {
                return StatusProcess.PROBLEMDURINGTHEPROCESS;
            }
        }

        if (writeCSV(writeList, fileWrite))
            return StatusProcess.FILEGENERATE;
        else
            return StatusProcess.PROBLEMDURINGTHEPROCESS;
    }

    public static boolean isImage(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');

        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("png"))
            return true;
        return false;
    }
}
