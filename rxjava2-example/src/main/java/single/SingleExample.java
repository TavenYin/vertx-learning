package single;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class SingleExample {
    public static void main(String[] args) throws InterruptedException {
        Disposable d = Single.just("Hello World")
                .delay(10, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onStart() {
                        System.out.println("Started");
                    }

                    @Override
                    public void onSuccess(String value) {
                        System.out.println("Success: " + value);
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }
                });

        Thread.sleep(5000);

        d.dispose();
    }
}
