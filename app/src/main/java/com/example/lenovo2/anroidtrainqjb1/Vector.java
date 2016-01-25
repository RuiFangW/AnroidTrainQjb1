package com.example.lenovo2.anroidtrainqjb1;

/**
 * Vector
 *
 * @author: lenovo
 * @time: 2016/1/22 14:17
 */
public class Vector {
   public  float x;
    float y;

    public Vector() {
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }



    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    /*
    加法
     */
    public Vector add(Vector v){
        x+=v.x;
        y+=v.y;
        return this;
    }
    public static Vector add(Vector v1,Vector v2){
        return new Vector(v1.x+v2.x,v1.y+v2.y);
    }
    /*
    减法
     */
    public Vector sub(Vector v){
        x-=v.x;
        y-=v.y;
        return this;
    }
    public static Vector sub(Vector v1,Vector v2){
        return new Vector(v1.x-v2.x,v1.y-v2.y);
    }
    /*
    乘法
     */
    public Vector mult(float n){
        x*=n;
        y*=n;
        return this;
    }
    /*
    除法
     */
    public Vector div(float n){
        if(n!=0){
            x/=n;
            y/=n;
        }
        return this;
    }

    /**
     模长
     */
    public float mag(){
        return (float) Math.sqrt(x*x+y*y);
    }
    public  void limit(float max){
        if(max*max<mag()*mag()){
            normalize();
            mult(max);
        }
    }

    /**
     * 单位化
     * @return
     */
    public void normalize(){
       div(mag());
    }
}
