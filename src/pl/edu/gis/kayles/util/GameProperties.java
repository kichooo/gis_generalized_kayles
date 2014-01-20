package pl.edu.gis.kayles.util;

/**
 * Created with IntelliJ IDEA.
 * Date: 20.01.14
 * Time: 00:29
 */
public class GameProperties {
    private Float wa1;
    private Float wb1;
    private Float wc1;
    private Float wa2;
    private Float wb2;
    private Float wc2;
    private boolean singlePlayer;
    private int graphSize;

    public Float getWa1() {
        return wa1;
    }

    public void setWa1(String wa1) {
        this.wa1 = Float.parseFloat(wa1);
    }

    public Float getWb1() {
        return wb1;
    }

    public void setWb1(String wb1) {
        this.wb1 = Float.parseFloat(wb1);
    }

    public Float getWc1() {
        return wc1;
    }

    public void setWc1(String wc1) {
        this.wc1 = Float.parseFloat(wc1);
    }

    public Float getWa2() {
        return wa2;
    }

    public void setWa2(String wa2) {
        this.wa2 = Float.parseFloat(wa2);
    }

    public Float getWb2() {
        return wb2;
    }

    public void setWb2(String wb2) {
        this.wb2 = Float.parseFloat(wb2);
    }

    public Float getWc2() {
        return wc2;
    }

    public void setWc2(String wc2) {
        this.wc2 = Float.parseFloat(wc2);
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public int getGraphSize() {
        return graphSize;
    }

    public void setGraphSize(String graphSize) {
        this.graphSize = Integer.parseInt(graphSize);
    }
}
