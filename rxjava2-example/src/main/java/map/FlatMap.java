package map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlatMap {
    public static void main(String[] args) {
        List list = createPerson();
//        map(list);
        flatMap(list);
    }

    public static List<Person> createPerson() {
        Plan planA = new Plan("2019.7.29", "plan A");
        planA.actionList = Arrays.asList("吃", "喝", "玩", "乐");
        Plan planB = new Plan("2019.7.30", "plan B");
        planB.actionList = Arrays.asList("写会代码", "抽会烟");
        Person p = new Person("taven", Collections.singletonList(planB));
        Person p2 = new Person("hmj", Collections.singletonList(planA));
        return Arrays.asList(p, p2);
    }

    private static void flatMap(List<Person> list) {
        Observable.fromIterable(list)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) {
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .flatMap(new Function<Plan, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Plan plan) throws Exception {
                        return Observable.fromIterable(plan.getActionList());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("==================action: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private static void map(List<Person> list) {
        Observable.fromIterable(list)
                .map(new Function<Person, List<Plan>>() {
                    @Override
                    public List<Plan> apply(Person person) throws Exception {
                        return person.getPlanList();
                    }
                })
                .subscribe(new Observer<List<Plan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Plan> plans) {
                        for (Plan plan : plans) {
                            List<String> planActionList = plan.getActionList();
                            for (String action : planActionList) {
                                System.out.println("==================action " + action);
                            }
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

    static class Person {
        private String name;
        private List<Plan> planList = new ArrayList<>();

        public Person(String name, List<Plan> planList) {
            this.name = name;
            this.planList = planList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Plan> getPlanList() {
            return planList;
        }

        public void setPlanList(List<Plan> planList) {
            this.planList = planList;
        }

    }


    static class Plan {
        private String time;
        private String content;
        private List<String> actionList = new ArrayList<>();

        public Plan(String time, String content) {
            this.time = time;
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getActionList() {
            return actionList;
        }

        public void setActionList(List<String> actionList) {
            this.actionList = actionList;
        }
    }

}
