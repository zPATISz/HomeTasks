package ru.aston.hometask03;

interface PaymentStrategy{
    void pay(int amount);
}

class CardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CardPayment (String cardNumber){
        this.cardNumber = cardNumber;
    }

    private String cardMask(String card) {
        return "**** **** **** " + card.substring(card.length() - 4);
    }

    @Override
    public void pay(int amount) {
        System.out.println("Оплата картой. Сумма: " + amount + " д.е. Номер карты: " + cardMask(cardNumber));
    }
}

class PaypalPayment implements PaymentStrategy {
    private final String email;

    public PaypalPayment (String email){
        this.email = email;
    }

    private String emailMask(String email) {
        return email.substring(0, 3) + "****" + email.substring(email.indexOf("@"));
    }

    @Override
    public void pay(int amount) {
        System.out.println("Оплата PayPal. Сумма: " + amount + " д.е. e-mail: " + emailMask(email));
    }
}


class ShoppingCart {
    private PaymentStrategy ps;

    public void setPaymentStrategy(PaymentStrategy ps) {
        this.ps = ps;
    }

    public void checkout(int amount) {
        if (ps == null) {
            throw new IllegalStateException("Способ оплаты не выбран");
        }
        ps.pay(amount);
    }
}

public class StrategyDemo {

    public static void run() {

        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CardPayment("1111222233334444"));
        cart.checkout(900);

        cart.setPaymentStrategy(new PaypalPayment("example@e-mail.net"));
        cart.checkout(10500);
    }

    static void main(String[] args) {
        run();
    }
}
