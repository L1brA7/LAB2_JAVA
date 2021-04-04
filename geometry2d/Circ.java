package geometry2d;

public class Circ extends Figure {

    private int r;

    public int[] getData() {
        int r[] = {this.r};
        return r;
    }
    public void setData(int[] Data) {this.r = Data[0];}
    
    public Circ(int r, int x, int y) {
        this.r = r;
        this.x = x;
        this.y = y;
    }

    public void Show() {
        System.out.println("R = ");
        System.out.println(this.getData());
        System.out.println(this.getData());
    }

    public boolean isin(int X, int Y) {return (X - this.x - this.r) * (X - this.x - this.r) + (Y - this.y - this.r) * (Y - this.y - this.r) < this.r * this.r;}
}
