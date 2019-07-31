package map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

public class GroupBy {
    public static void main(String[] args) {
        Observable.just(5, 2, 3, 4, 1, 6, 8, 9, 7, 10)
                .groupBy(new Function< Integer, Integer >() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer % 3;
                    }
                })
                .subscribe(new Observer <GroupedObservable< Integer, Integer >> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("====================onSubscribe ");
                    }

                    @Override
                    public void onNext(GroupedObservable < Integer, Integer > groupedObservable) {
                        System.out.println("====================onNext ");
                        groupedObservable.subscribe(new Observer< Integer >() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("====================GroupedObservable onSubscribe ");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                System.out.println("====================GroupedObservable onNext  groupName: " + groupedObservable.getKey() + " value: " + integer);
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println("====================GroupedObservable onError ");
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("====================GroupedObservable onComplete ");
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("====================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("====================onComplete ");
                    }
                });

    }
}
