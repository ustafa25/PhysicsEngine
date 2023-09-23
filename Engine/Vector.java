package Engine;

public class Vector {

    public static double[] create(double x , double y){
        double[] v = {x , y};
        return v;
    }

    public static double[] ADD(double[] a , double[] b){
        double[] Sum = {a[0]+b[0] , a[1]+b[1]};
        return Sum;
    }

    public static double[] SUBTRACT(double[] a , double[] b){
        double[] Difference = {a[0]-b[0] , a[1]-b[1]};
        return Difference;
    }

    public static double MAG(double[] vector){
        return Math.sqrt((vector[0]*vector[0] + vector[1]*vector[1]));
    }

    public static double[] SCALE(double[] vector , double scaler){
        double[] scaled = {vector[0]*scaler , vector[1]*scaler};
        return scaled;
    }

    public static double DOT(double[] A , double[] B){
        return (A[0]*B[0] + A[1]*B[1]);
    }

    public static double ANGLE(double[] vector){
        return Math.atan2(vector[1] , vector[0]);
    }

    public static double[] Normalize(double[] vector){
        return Vector.SCALE(vector , 1/Vector.MAG(vector));
    }

    public static double[] MultiplyMatrix(double[][] Matrix , double[] vector){
        return Vector.ADD(
            Vector.SCALE(Matrix[0], vector[0]),
            Vector.SCALE(Matrix[1], vector[1])
        );
    }

    public static double[][] InverseMatrix(double[][] Matrix){
        double DET = (Matrix[0][0]*Matrix[1][1] - Matrix[0][1]*Matrix[1][0]);
        
        if (DET == 0){
            return null;
        }
        
        double[][] Inverse = {
            {Matrix[1][1]/DET , -Matrix[0][1]/DET},
            {-Matrix[1][0]/DET , Matrix[0][0]/DET}
        };
        return Inverse;
    }

    public static double[] ROTATE(double[] vector , double angle){
        double[][] Matrix = {
            {Math.cos(angle) , Math.sin(angle)}, 
            {-Math.sin(angle) , Math.cos(angle)}
        };

        return Vector.MultiplyMatrix(Matrix, vector);
    }

    public static double[] NORMAL(double[] vector){
        double[] Normal = Vector.Normalize(Vector.create(-vector[1] , vector[0]));
        return Normal;
    }

}
