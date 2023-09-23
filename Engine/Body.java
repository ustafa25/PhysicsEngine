package Engine;


public class Body {

    public Shape ShapeObject;
    public double[] Pos;
    public double Angle;
 
    public double Mass;
    public double[] Velocity;
    public double AngularVelocity;
    public double AngularInertia;

    public Body(Shape shape , double[] position , double mass){
        this.ShapeObject = shape;
        this.Pos = position;
        this.Angle = 0;
        this.Mass = mass;
        this.AngularInertia = Body.CalculateAngularInertia(shape, mass*(1d/shape.ShapeObject.length));

        this.Velocity = Vector.create(0 , 0);
        this.AngularVelocity = 0;
    }

    public static double CalculateAngularInertia(Shape shape , double mass){
        double Inertia = 0.0;
        for(double[] Vertex : shape.ShapeObject){
            double radius = Vector.MAG(Vertex);
            Inertia = Inertia +  ((mass) * Math.pow(radius, 2)/4);
        }

        return Inertia;
    }

    public static double[][] Update(Body body , double deltaTime){
        body.Pos = Vector.ADD(body.Pos , Vector.SCALE(body.Velocity, deltaTime));
        body.Angle = body.Angle + (body.AngularVelocity*deltaTime);

        return Shape.Operation(body.ShapeObject, body.Pos, body.Angle);
    }

}
