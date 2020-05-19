package dto;

public class ReadCSV {

    public String image1;
    public String image2;

    public ReadCSV(){

    }

    public ReadCSV(String image1, String image2){
        this.image1 = image1;
        this.image2 = image2;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage1(){
        return image1;
    }

}
