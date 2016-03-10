package by.it.predkel.JD0201Thread;

import by.it.predkel.SimplyUsefulClasses.Rnd;

class Buyer extends Thread implements Runnable, IBuyer,IUseBasket {

    int num; //номер покупателя
    Basket basket;
    boolean pensioneer=false;

    //конструктор покупателя с его номером
    public Buyer(int num) {
        this.num = num;
        if (Rnd.fromTo(0,3)==0)
        pensioneer=true;
        this.setName("Покупатель № "+ num+" ");
        start();
    }

    @Override //покупатель приходит в зал и выбирает товары.
    public void run() {
        enterToMarket();
        takeBacket();
        chooseGoods();
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
                int pause= Rnd.fromTo(100, 200);
                Thread.sleep(pensioneer?pause:pause*2);
            } catch (InterruptedException e) {
                System.out.println(this+" //некорректное завершение ожидания");
            }
        basket.putToBasket(MiniHelper.chooseGoods());
        //ожидание окончено
        System.out.println(this + "выбрал товар");
        putGoodsToBacket();
    }

    @Override
    public void goToOut() {
        try {
            //вызываем свой генератор случайных чисел
            int pause= Rnd.fromTo(100, 200);
            Thread.sleep(pensioneer?pause:pause*2);
        } catch (InterruptedException e) {
            System.out.println(this+" //некорректное завершение ожидания");
        }
        System.out.println(this+""+ basket);
        returnBasket();
        this.basket=null;
        System.out.println(this + "вышел из магазина "+(pensioneer?"Шустрик":"Пенсионер"));
    }

    @Override
    public void takeBacket() {
        try {
            //вызываем свой генератор случайных чисел
            int pause= Rnd.fromTo(100, 200);
            Thread.sleep(pensioneer?pause:pause*2);
        } catch (InterruptedException e) {
            System.out.println(this+" //некорректное завершение ожидания");
        }
        this.basket=new Basket();
        System.out.println(this + "Взял корзину");
    }

    @Override
    public void putGoodsToBacket() {
        try {
            //вызываем свой генератор случайных чисел
            int pause= Rnd.fromTo(100, 200);
            Thread.sleep(pensioneer?pause:pause*2);
        } catch (InterruptedException e) {
            System.out.println(this+" //некорректное завершение ожидания");
        }
        System.out.println(this+" положил товары в корзину");
    }

    @Override
    public void returnBasket() {
        try {
            //вызываем свой генератор случайных чисел
            int pause= Rnd.fromTo(100, 200);
            Thread.sleep(pensioneer?pause:pause*2);
        } catch (InterruptedException e) {
            System.out.println(this+" //некорректное завершение ожидания");
        }
        System.out.println(this+" вернул корзину");
    }
}
