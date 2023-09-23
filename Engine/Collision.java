package Engine;

public class Collision{

    public static double[] LineIntersection(double[][] A , double[][] B){
        double[] ab = Vector.SUBTRACT(A[1] , A[0]);
        double[] cd = Vector.SUBTRACT(B[1] , B[0]);
        double[] ac = Vector.SUBTRACT(B[0] , A[0]);

        double[][] Matrix = {ab , cd};

        double[][] Inverse = Vector.InverseMatrix(Matrix);

        if (Inverse == null){
            return null;
        }

        double T =  Vector.MultiplyMatrix(Inverse, ac)[0];
        double S =  Vector.SCALE(Vector.MultiplyMatrix(Inverse, ac) , -1)[1];

        if (T <= 1 && T >= 0 && S <= 1 && S >= 0){
            return Vector.ADD(Vector.SCALE(ab, T) , A[0]);
        }

        return null;

    }

    public static double[] RayCast(double[] POS , double[] DIR , double[][] Line){
        double[] ab = Vector.SUBTRACT(Line[1] , Line[0]);
        double[] ac = Vector.SUBTRACT(Vector.ADD(POS, DIR) , Line[0]);

        double[][] Matrix = {ab , DIR};

        double[][] Inverse = Vector.InverseMatrix(Matrix);

        if (Inverse == null){
            return null;
        }

        double T =  Vector.MultiplyMatrix(Inverse, ac)[0];
        double S =  Vector.SCALE(Vector.MultiplyMatrix(Vector.InverseMatrix(Matrix), ac) , -1)[1];

        if (T <= 1 && T >= 0 && S >= 0){
            return Vector.ADD(Vector.SCALE(ab, T) , Line[0]);
        }

        return null;
    }

    public static double[] ClosestPointToLine(double[] Point , double[][] Line){
        double[] NormalAxis = Vector.Normalize(Vector.SUBTRACT(Line[1] , Line[0]));
        double scaler = Vector.DOT(NormalAxis ,  Vector.SUBTRACT(Point , Line[0]));
        double[] ClosestPoint = Vector.ADD(Vector.SCALE(NormalAxis, scaler) , Line[0]);

        return Vector.SUBTRACT(Point , ClosestPoint);
    }

    public static double[] PointInShape(double[] Point , double[][] shape){

        int counter = 0;
        double[] ClosestOffset = Vector.create(0,0);


        for (int I = 1; I < shape.length; I++){

            double[][] Line = {shape[I] , shape[I-1]};
            double[] Intersection = Collision.RayCast(Point, Vector.create(1 , 0), Line);
            double[] offset = Collision.ClosestPointToLine(Point, Line);

            if(Vector.MAG(offset) > Vector.MAG(ClosestOffset)){
                ClosestOffset = offset;
            }

            if (Intersection != null){
                counter++;
            }
        }

        double[][] Line = {shape[shape.length-1] , shape[0]};
        double[] Intersection = Collision.RayCast(Point, Vector.create(1 , 0), Line);
        double[] offset = Collision.ClosestPointToLine(Point, Line);

        if(Vector.MAG(offset) > Vector.MAG(ClosestOffset)){
            ClosestOffset = offset;
        }

        if (Intersection != null){counter++;}
        if (counter % 2 == 0){return null;}
        return ClosestOffset;
    }
}