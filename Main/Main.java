import Engine.*;

class Main{

    // what should we do to make a very good observative introductory course 

    public static void main(String[] args) {
        double[][] shape = {{0 , 10} , {-5 , -5} , {5 , -5}};
        double[] Point = {1 , 2};

        double[] CollisionOffset = Collision.PointInShape(Point, shape);

        if (CollisionOffset != null){
            System.out.println(CollisionOffset[0] + " " + CollisionOffset[1]);
        }

    }
}