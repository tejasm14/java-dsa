package java9;

public interface Sayable {
	
	default void say() {
		display();	
    }

    // Private method inside interface
	default void display() {
		System.out.println("private method");
	}
	
}


