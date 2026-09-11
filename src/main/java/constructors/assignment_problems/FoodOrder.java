package main.java.constructors.assignment_problems;

import java.util.HashSet;
import java.util.Set;

public class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    public void markDelivered() {
        if (delivered) {
            System.out.println("Order already delivered");
        } else {
            delivered = true;
            System.out.println("Order marked delivered");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        Set<String> deliveredOrders = new HashSet<>();

        for (String[] order : rawOrders) {
            try {
                if (order == null || order.length < 2) {
                    throw new IllegalArgumentException();
                }

                FoodOrder foodOrder = new FoodOrder(order[0], order[1]);
                String key = foodOrder.studentName + "|" + foodOrder.dishName;

                if (deliveredOrders.contains(key)) {
                    foodOrder.markDelivered();
                } else {
                    deliveredOrders.add(key);
                    foodOrder.markDelivered();
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(orders);
    }
}
