package testmix;

class Animal {

    public int a = 10;

    public void run()  {
        System.out.println("Run animal");
    }

    public void play() {
        System.out.println("This is animal play");
    }

}

class Dog extends Animal {


    public void test() {

        //super.a = 11;
        System.out.println(a);
        System.out.println("DOg");
    }

    public void play() {
        System.out.println("DOg Play");
    }
}


public class Demo {

    public static void main(String[] args) {
        Dog obj = new Dog();
        obj.test();
        obj.play();
    }
}
