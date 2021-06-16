package com.bitmain.demo;

public  class Circle {
    private double radius = 0;

    public Circle(double radius) {
        this.radius = radius;
        getDrawInstance().drawSahpe();   //必须先创建成员内部类的对象，再进行访问
    }

    private Draw getDrawInstance() {
        return new Draw();
    }

    class Draw {     //内部类
        public void drawSahpe() {
            System.out.println(radius);  //外部类的private成员
        }
    }
    public static void  main(String[] args){
        Circle circle = new Circle(2.2);
        Circle.Draw draw = new Circle(1).new  Draw();
    }
}
