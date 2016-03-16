package by.it.knyazev.JD02.less_01.A;

import by.it.novik.JD01_14.B;

/**
 * Created by Mac on 09.03.16.
 */
public class Buyer extends Thread implements Runnable, IBuyer,IUseBacket {

    int num; //номер покупателя

    //конструктор покупателя с его номером
    public Buyer(int num) {
        this.num = num;
        this.setName("Покупатель № "+ num+" ");
        start();
    }

    @Override //покупатель приходит в зал и выбирает товары.
    public void run() {
        enterToMarket();
        takeBacket();
        chooseGoods();
        putGoodsToBacket();
        goToOut();
    }

    //при получение строкового значения для экземпляра покупателя
    @Override
    public String toString() {
        return this.getName();
    }

    //реализация интерфейсов
    @Override
    public void enterToMarket() {
        System.out.println(this + "вошел в магазин");
    }

    @Override
    public void chooseGoods() {
        try {
            //вызываем свой генератор случайных чисел
            int pause = Rnd.fromTo(500, 2000);
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            System.out.println(this+" //некорректное завершение ожидания");
        }
        //ожидание окончено
        System.out.println(this + "выбрал товар");
    }

    @Override
    public void goToOut() {
        System.out.println(this + "вышел из магазина");
    }

    @Override
    public void takeBacket() {
        int pause = Rnd.fromTo(500, 2000);
        System.out.println(this+" взял корзину");
    }

    @Override
    public void putGoodsToBacket() {
        int pause = Rnd.fromTo(500, 2000);
        System.out.println();
    }
}
