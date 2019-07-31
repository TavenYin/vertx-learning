import io.reactivex.Flowable;

public class HelloWorld {
    public static void hello(String... args) {
        Flowable.fromArray(args).subscribe(s -> System.out.println("Hello " + s + "!"));
    }

    public static void main(String[] args) {
        hello("taven", "hmj");
    }
}
