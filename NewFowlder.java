public class NewFowlder{
    public static void main(String[] args){

        // Finishes the Equation for s1 - 3 and Prints
        double[] a1 = {0, 0 ,0};
        a1 = tR(15, 20.5, 15.25);
        System.out.println(a1[0]);
        System.out.println(a1[1]);
        System.out.println(a1[2]);
    }
    public static double[] tR(double s1, double s2, double s3){

        // Gets Array
        double[] theta = {0, 0, 0};

        // Equation 1 & Turning Radians to Degrees
        theta[0] = Math.acos( (s1 * s1 - s2 * s2 - s3 * s3) / (-2 * s2 * s3) );
        theta[0] = theta[0] * 180 / Math.PI;

        // Equation 2 & Turning Radians to Degrees
        theta[1] = Math.acos( (s2 * s2 - s1 * s1 - s3 * s3) / (-2 * s1 * s3) );
        theta[1] = theta[1] * 180 / Math.PI;

        // Equation 3 & Turning Radians to Degrees
        theta[2] = Math.acos( (s3 * s3 - s2 * s2 - s1 * s1) / (-2 * s2 * s1) );
        theta[2] = theta[2] * 180 / Math.PI;

        // Returning the Theta
        return theta;
    }
    public static boolean inside(double x, double y){
        if(y > 0){
            if((x*x) + (y*y) <= Math.pow((distanceArm1 + distanceArm2), 2)){
                return true;
            }else return false;
        }else return false;
    }
}

