package HelloJava;

public abstract class hellojava {

    public abstract void test1(); //抽象方法一定在抽象类中

    public  void hello(){  //抽象类中可以有非抽象方法
        System.out.println("我是暗裔");
    }

    public static void main(String[] args) {
        System.out.println("hello idea and java");
    }
}
