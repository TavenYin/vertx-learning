package observable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObservableExample {
    public static void main(String[] args) {
        create();
//        just();
//        from();
    }

    private static void create() {
        // 被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("=========================observable：" + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        // 观察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("=========================observer: " + Thread.currentThread().getName());
                System.out.println("======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("======================onError");
            }

            @Override
            public void onComplete() {
                System.out.println("======================onComplete");
            }
        };

        observable.subscribe(observer);
    }

    private static void just() {
        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("=================onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("=================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("=================onComplete ");
                    }
                });
    }

    private static void from() {
        Integer array[] = {1, 2, 3, 4};
        Observable.fromArray(array)
                .subscribe(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("=================onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("=================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("=================onComplete ");
                    }
                });

    }
}
