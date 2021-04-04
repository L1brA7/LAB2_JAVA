package geometry2d;

public abstract class Figure {

    protected int x;
    protected int y;

    protected int R;
    protected int G;
    protected int B;

    public int[] getCoords() {
        int cd[] = {this.x, this.y};
        return cd;
    }
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setRGB(int r, int g, int b) {
        this.R = r;
        this.G = g;
        this.B = b;
    }
    public int[] getRGB() {
        int rgb[] = {this.R, this.G, this.B};
        return rgb;
    }
    abstract public int[] getData();
    abstract public void setData(int[] Data);
    abstract public boolean isin(int X, int Y);
    
}
