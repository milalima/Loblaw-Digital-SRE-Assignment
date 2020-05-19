package dto;

public class WriteCSV {

    public ReadCSV readCSV;
    public Double similar;
    public long elapsed;

    public WriteCSV(){

    }

    public WriteCSV(ReadCSV readCSV, Double similar, long elapsed){
        this.readCSV = readCSV;
        this.similar = similar;
        this.elapsed = elapsed;
    }

    public void setReadCSV(ReadCSV readCSV) {
        this.readCSV = readCSV;
    }

    public void setSimilar(Double similar) {
        this.similar = similar;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    public ReadCSV getReadCVS() { return readCSV; }

    public Double getSimilar() {
        return similar;
    }

    public long getElapsed() {
        return elapsed;
    }

}
