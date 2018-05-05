package com.demo.jdk;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @author Don
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: TODO
 * @Creation Date:2018-04-29
 */
interface IMoneyFormat {
    String format(int i);
}

class MyMoney {
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer,String> moneyFormat) {
        System.out.println("我的存款：" + moneyFormat.apply(this.money));
    }
}
public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney me = new MyMoney(99999999);
        Function<Integer, String> format = i -> new DecimalFormat("#,###").format(i);
        //function interface chain operator
        me.printMoney(
                format.andThen
                        (s ->
                                "RMB" + s));
    }
}