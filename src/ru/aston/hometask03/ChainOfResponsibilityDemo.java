package ru.aston.hometask03;

// Вспомогательный класс — заявка
class PurchaseRequest {
    private final double amount;
    private final String purpose;

    public PurchaseRequest(double amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public String getPurpose() {
        return purpose;
    }
}

// Базовый класс звена цепочки
abstract class ApprovalHandler {
    protected ApprovalHandler next;

    public ApprovalHandler setNext(ApprovalHandler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(PurchaseRequest request);

    // Передать запрос дальше, если текущее звено не может его обработать
    protected void passToNext(PurchaseRequest request) {
        if (next != null) {
            next.handle(request);
        } else {
            System.out.println("Заявка на " + request.getAmount()
                    + " руб. (" + request.getPurpose() + ") отклонена — превышен лимит согласования");
        }
    }
}

// Конкретное звено 1
class ChainOneHandler extends ApprovalHandler {
    private static final double LIMIT = 1000;

    @Override
    public void handle(PurchaseRequest request) {
        if (request.getAmount() <= LIMIT) {
            System.out.println("Звено 1 одобрило заявку на " + request.getAmount()
                    + " д.е. (" + request.getPurpose() + ")");
        } else {
            passToNext(request);
        }
    }
}
// Конкретное звено 2
class ChainTwoHandler extends ApprovalHandler {
    private static final double LIMIT = 10000;

    @Override
    public void handle(PurchaseRequest request) {
        if (request.getAmount() <= LIMIT) {
            System.out.println("Звено 2 одобрило заявку на " + request.getAmount()
                    + " руб. (" + request.getPurpose() + ")");
        } else {
            passToNext(request);
        }
    }
}
// Конкретное звено 3
class ChainThreeHandler extends ApprovalHandler {
    private static final double LIMIT = 25000;

    @Override
    public void handle(PurchaseRequest request) {
        if (request.getAmount() <= LIMIT) {
            System.out.println("Звено 3 одобрило заявку на " + request.getAmount()
                    + " руб. (" + request.getPurpose() + ")");
        } else {
            passToNext(request);
        }
    }
}

public class ChainOfResponsibilityDemo {

    public static void run() {
        ApprovalHandler с1 = new ChainOneHandler();
        ApprovalHandler с2 = new ChainTwoHandler();
        ApprovalHandler с3 = new ChainThreeHandler();

        // цепочка: звено 1 -> звено 2 -> звено 3
        с1.setNext(с2).setNext(с3);

        с1.handle(new PurchaseRequest(500, "Канцтовары"));
        с1.handle(new PurchaseRequest(21000, "Монитор"));
        с1.handle(new PurchaseRequest(150000, "Сервер"));
    }

    public static void main(String[] args) {
        run();
    }
}
