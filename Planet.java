import java.lang.Math;
public class Planet{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
                mass = m;
                imgFileName = img;
              }
  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  // calculate the distance between two planet;
  public double calcDistance(Planet p){
    double sq_dis = (xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos);
    double distance = Math.sqrt(sq_dis);
    return distance;
  }

  // calculate the force exerted on this plante by the given planet.
  public double calcForceExertedBy(Planet p){
    final double G = 6.67e-11;
    double force = (G * p.mass * mass)/(this.calcDistance(p)* this.calcDistance(p));
    return force;
  }

  // calculate the force exerted on this plante in X direction by the given planet.
  public double calcForceExertedByX(Planet p){
    double force_x = (this.calcForceExertedBy(p) * (p.xxPos - xxPos))/this.calcDistance(p);
    return force_x;
  }

  // calculate the force exerted on this plante in Y direction by the given planet.
  public double calcForceExertedByY(Planet p){
    double force_y = (this.calcForceExertedBy(p) * (p.yyPos - yyPos))/this.calcDistance(p);
    return force_y;
  }

  //take in an array of Planets and calculate the net X force exerted by all planets upon current planet;
  public double calcNetForceExertedByX(Planet[] s){
    double net_forcex = 0;
    for (Planet p : s){
      if (this.equals(p)){
        continue;
      }
      else{
        net_forcex += calcForceExertedByX(p);
      }
    }
    return net_forcex;
  }

  //take in an array of Planets and calculate the net Y force exerted by all planets upon current planet;
  public double calcNetForceExertedByY(Planet[] s){
    double net_forcey = 0;
    for (Planet p : s){
      if (this.equals(p)){
        continue;
      }
      else{
        net_forcey += calcForceExertedByY(p);
      }
    }
    return net_forcey;
  }

  public void update(double dt, double fX, double fY){
    double a_x = fX/mass;
    double a_y = fY/mass;
    xxVel = xxVel + dt*a_x;
    yyVel= yyVel + dt*a_y;
    xxPos = xxPos + dt*xxVel;
    yyPos = yyPos + dt*yyVel;
  }

  public void draw(){
    StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
  }
}

























//ddd
