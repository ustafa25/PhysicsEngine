package Engine;

public class Shape {
    public final double[][] ShapeObject;

    public Shape(double[][] shape){
        double[] CenterOffset = Shape.MassCenter(shape);
        this.ShapeObject = Shape.Translate(shape, Vector.SCALE(CenterOffset , -1));
    }

    public static double[] MassCenter(double[][] shape){
        double[] Center = {0 , 0};

        for(double[] Vertex : shape){
            Center = Vector.ADD(Vertex, Center);
        }

        return Vector.SCALE(Center, 1d/shape.length);
    }

    public static double[][] Translate(double[][] shape , double[] vector){

        double[][] OperatedShape = shape.clone();

        for(int I = 0; I < OperatedShape.length; I++){
            OperatedShape[I] = Vector.ADD(OperatedShape[I] , vector);
        }

        return OperatedShape;
    }

    public static double[][] Rotate(double[][] shape , double angle){
        double[][] OperatedShape = shape.clone();

        for(int I = 0; I < OperatedShape.length; I++){
            OperatedShape[I] = Vector.ROTATE(OperatedShape[I], angle);
        }

        return OperatedShape;
    }

    public static double[][] Operation(Shape shape , double[] position , double angle){
        return Shape.Translate(Shape.Rotate(shape.ShapeObject, angle), position);
    }

    public static double[][] Operation(double[][] shape , double[] position , double angle){
        return Shape.Translate(Shape.Rotate(shape, angle), position);
    }
}
