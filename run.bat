javac -d "build" geometry2d/Circ.java
javac -d "build" geometry2d/Rect.java
javac -d "build" geometry2d/Figure.java
javac -d "build" --module-path "javafx-sdk-11.0.2\lib" --add-modules javafx.controls Main.java
java -cp "build" --module-path "javafx-sdk-11.0.2\lib" --add-modules javafx.controls Main.java