package geometry2d;

public class Rect extends Figure{
    
    private int a;
    private int b;

    public int[] getData() {
        int AB[] = {this.a, this.b};
        return AB;
    }

    public void setData(int[] Data) {
        this.a = Data[0];
        this.b = Data[0];
    }

    public void setA(int i) {this.a = i;}
    public void setB(int i) {this.b = i;}

    public Rect(int a, int b, int x, int y) {
        this.a = a;
        this.b = b;
        this.x = x;
        this.y = y;
    }
    public void Show() {
        System.out.println(this.getData()[0]);
        System.out.println(this.getData()[1]);
    }

    public boolean isin(int X, int Y) {return (X < this.x + this.a && X > this.x) && (Y < this.y + this.b && Y > this.y);}
}
