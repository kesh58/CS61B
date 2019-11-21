/** Project 0.
 * @author keshang
 */

public class NBody{

  /** given a file name, return the radius of the universe.
   * @param s Given file name*/
  public static double readRadius(String s) {
    In in = new In(s);
    int firstItemInFile = in.readInt();
    double secondItemInFile = in.readDouble();
    return secondItemInFile;
  }

  //given a file name, return an array of planets.
  public static Planet[] readPlanets(String s){
    In in = new In(s);
    int num_planet = in.readInt();
    Planet[] list_planet = new Planet[num_planet];
    double radius_p = in.readDouble();
    int i = 0;
    while (i<num_planet){
      double xxPos = in.readDouble();
  		double yyPos = in.readDouble();
      double xxVel = in.readDouble();
  		double yyVel = in.readDouble();
      double mass = in.readDouble();
  		String img_name = in.readString();
      Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img_name);
      list_planet[i] = p;
      i = i +1;
    }
    return list_planet;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);

    StdDraw.setScale(-radius, radius);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    Planet[] p_list = readPlanets(filename);
    int i = 0;
    while (i < p_list.length) {
      p_list[i].draw();
      i = i + 1;
    }
    StdDraw.enableDoubleBuffering();
    int time_v = 0;
    int waitTimeMilliseconds = 10;

    while (time_v <= T) {
      double[] xForce = new double[p_list.length];
      double[] yForce = new double[p_list.length];

      for(int j = 0;j < p_list.length; j += 1) {
        xForce[j] = p_list[j].calcNetForceExertedByX(p_list);
        yForce[j] = p_list[j].calcNetForceExertedByY(p_list);
        p_list[j].update(dt, xForce[j], yForce[j]);
      }

      StdDraw.picture(0, 0, "images/starfield.jpg");
      int k = 0;

      while (k < p_list.length) {
        p_list[k].draw();
        k = k + 1;
      }

      StdDraw.show();
      StdDraw.pause(waitTimeMilliseconds);
      time_v += dt;
    }

    StdOut.printf("%d\n", p_list.length);
    StdOut.printf("%.2e\n", radius);

    for (int m = 0; m < p_list.length; m++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
              p_list[m].xxPos, p_list[m].yyPos, p_list[m].xxVel,
              p_list[m].yyVel, p_list[m].mass, p_list[m].imgFileName);
    }
  }
}
