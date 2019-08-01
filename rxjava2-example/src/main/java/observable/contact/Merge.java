package observable.contact;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import java.util.concurrent.TimeUnit;

public class Merge {
    public static void main(String[] args) {
        new Thread(() -> {
            Observable.merge( // concat() 是串行发送事件，而 merge() 并行发送事件
                    Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
                        @Override
                        public String apply(Long aLong) throws Exception {
                            return "A" + aLong;
                        }
                    }),
                    Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
                        @Override
                        public String apply(Long aLong) throws Exception {
                            return "B" + aLong;
                        }
                    }))
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String s) {
                            System.out.println("=====================onNext " + s);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
