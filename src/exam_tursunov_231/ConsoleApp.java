package exam_tursunov_231;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleApp {
    Scanner sc = new Scanner(System.in);
    Scanner scOrderId = new Scanner(System.in);
    Scanner scCustomerName = new Scanner(System.in);
    Scanner scTotalAmount = new Scanner(System.in);

    private int valueUser;

    public ConsoleApp() throws SQLException, ClassNotFoundException {
        start();
    }

    int start() throws SQLException, ClassNotFoundException {
        DataBase db = new DataBase();
        db.isConnection();
        db.createDbOrders("orders_db");

        while (true) {
            System.out.println("***Инструкция***");
            System.out.println("1. Добавить заказ");
            System.out.println("2. Показать все заказы");
            System.out.println("3. Показать заказы с суммой больше 50 000 рублей");
            System.out.println("4. Закрыть приложение");

            valueUser = sc.nextInt();

            if (valueUser == 1) {
                System.out.println("Введите ID заказа: ");
                int orderId = scOrderId.nextInt();
                System.out.println("Введите имя клиента: ");
                String customerName = scCustomerName.nextLine();
                System.out.println("Введите сумму заказа: ");
                double totalAmount = scTotalAmount.nextDouble();
                db.addOrder("orders_db", orderId, customerName, totalAmount);
            }

            if (valueUser == 2) {
                db.selectAllOrders("orders_db");
            }

            if (valueUser == 3) {
                db.selectOrdersAboveAmount("orders_db", 50000);
            }

            if (valueUser == 4) {
                return 1;
            }
        }
    }
}
