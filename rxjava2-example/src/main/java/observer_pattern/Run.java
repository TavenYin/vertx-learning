package observer_pattern;

import java.util.Observable;
import java.util.Observer;

public class Run {
    public static void main(String[] args) {
        OfficialAccount officialAccount = new OfficialAccount();
        User ytw = new User("ytw");
        User hmj = new User("hmj");
        officialAccount.addObserver(ytw);
        officialAccount.addObserver(hmj);
        officialAccount.publishArticle("SpringBoot");
        officialAccount.publishArticle("Java 理解观察者模式");
    }

}

/**
 * 公众号
 * （被观察者）
 */
class OfficialAccount extends Observable {
    // 发布文章
    public void publishArticle(String article) {
        setChanged();
        notifyObservers(article);
    }
}

/**
 * 用户
 * （观察者）
 */
class User implements Observer {
    private String username;

    User(String username) {
        this.username = username;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("用户：" + username + ", 收到订阅文章：" + arg);
    }
}
