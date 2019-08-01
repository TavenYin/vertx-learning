package observable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Amp {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Observable<Long>> list = new ArrayList<>();

        // 延迟2秒
        list.add(Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS));
        list.add(Observable.intervalRange(6, 5, 0, 1, TimeUnit.SECONDS));

        Observable.amb(list)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("========================aLong " + aLong);
                    }
                });

        Thread.sleep(10000);
    }
}
