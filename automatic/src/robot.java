public class robot {
    public static void main(String[] args) {
        double a, b, c, d, e, f;

        //set the value
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;


        for(double v : new robot().ROBOPP(a, b, c, d, e, f)) {
            System.out.println(v);
        }
    }

    public double[] ROBOPP(double a, double b, double c, double d, double e, double f) {
        // code here
        double UsersP[] = {a,b,c,d,e,f};
        double RobotP[] = {24,-5,-13,-20,-23,-9};

        return new double[] {RobotP[0]+UsersP[0],RobotP[1]+UsersP[1],RobotP[2]+UsersP[2],RobotP[3]+UsersP[3],RobotP[4]+UsersP[4],RobotP[5]+UsersP[5]};
    }
}
