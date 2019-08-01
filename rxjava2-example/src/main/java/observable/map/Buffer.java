package observable.map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.List;

public class Buffer {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(2, 2) // count:缓冲区大小，skip下次事件指针移动几个位置，看不懂？看不懂就run
                .subscribe(new Observer<List< Integer >>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List < Integer > integers) {
                        System.out.println("================缓冲区大小： " + integers.size());
                        for (Integer i: integers) {
                            System.out.println("================元素： " + i);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
