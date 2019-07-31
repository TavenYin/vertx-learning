package contact;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 同contact作用相同，但是可以操作多个Observable
 */
public class ConcatArray {
    public static void main(String[] args) {
//        contactArray();
//        contactArrayOnError();
        concatArrayDelayError();
    }

    /**
     * 在 concatArray() 和 mergeArray() 两个方法当中，如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件，
     * 如果你想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话，
     * 就可以使用  concatArrayDelayError() 和 mergeArrayDelayError()
     *
     */
    private static void concatArrayDelayError() {
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe < Integer > () {
                    @Override
                    public void subscribe(ObservableEmitter < Integer > e) throws Exception {
                        e.onNext(1);
                        e.onError(new NumberFormatException());
                    }
                }), Observable.just(2, 3, 4))
                .subscribe(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("===================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("===================onError ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 在 concatArray() 和 mergeArray() 两个方法当中，如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件
     */
    private static void contactArrayOnError() {
        Observable.concatArray(
                Observable.create(new ObservableOnSubscribe< Integer >() {
                    @Override
                    public void subscribe(ObservableEmitter< Integer > e) throws Exception {
                        e.onNext(1);
                        e.onError(new NumberFormatException());
                    }
                }), Observable.just(2, 3, 4))
                .subscribe(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("===================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("===================onError ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    
    private static void contactArray() {
        Observable.concatArray(Observable.just(1, 2),
                Observable.just(3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8),
                Observable.just(9, 10))
                .subscribe(new Observer< Integer >() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("================onNext " + integer);
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
