package codealpha;
import java.util.Scanner;
import java.util.Random;

class Stock {
    String name;
    double price;

    Stock(String name, double initialPrice) {
        this.name = name;
        this.price = initialPrice;
    }

    void updatePrice() {
        Random rand = new Random();
        double change = (rand.nextDouble() * 10) - 5; //Price Change between -5 to +5
        price += change;
        if (price < 1) {
            price = 1;
        }
    }
}

class Portfolio { //shows portfolio
    private int[] holdings = new int[3]; //Stock Index: 0=Toyota, 1=Xiaomi, 2=Gigabyte
    private double cash = 50000.0;

    void buy(Stock[] stocks, int index, int quantity) { // purchases stock
        double totalCost = stocks[index].price * quantity;
        if (totalCost > cash) {
            System.out.println("Not enough balance to buy.");
            return;
        }
        holdings[index] += quantity;
        cash -= totalCost;
        System.out.println("Bought " + quantity + " shares of " + stocks[index].name);
    }

    void sell(Stock[] stocks, int index, int quantity) { // sells stock
        if (holdings[index] < quantity) {
            System.out.println("You don't have enough shares to sell.");
            return;
        }
        holdings[index] -= quantity;
        double amount = stocks[index].price * quantity;
        cash += amount;
        System.out.println("Sold " + quantity + " shares of " + stocks[index].name);
    }

    void show(Stock[] stocks) { // shows stock
        System.out.println("Your Portfolio:");
        double totalValue = 0;
        for (int i = 0; i < stocks.length; i++) {
            double value = holdings[i] * stocks[i].price;
            System.out.println(stocks[i].name + ": " + holdings[i] + " shares (Value: PKR " + value + ")");
            totalValue += value;
        }
        System.out.println("Cash: PKR " + cash);
        System.out.println("Total Portfolio Value: PKR " + (cash + totalValue));
    }
}


public class CodeAlpha_Task02 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        Stock[] stocks = {
            new Stock("Toyota", 1500.0),
            new Stock("Xiaomi", 500.0),
            new Stock("Gigabyte", 900.0)
        };
        Portfolio user = new Portfolio();

        while (true) {
            System.out.println();
            System.out.println("==== STOCK MENU ====");
            System.out.println("1 - View Market");
            System.out.println("2 - Buy Stock");
            System.out.println("3 - Sell Stock");
            System.out.println("4 - View Portfolio");
            System.out.println("5 - Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            } catch (Exception e) {
                System.out.println("Please enter a number only.");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                for (Stock stock : stocks) {
                    stock.updatePrice();
                    System.out.println(stock.name + " current price: PKR " + stock.price);
                }
            } else if (choice == 2) {
                System.out.println("Enter stock number to buy:");
                for (int i = 0; i < stocks.length; i++) {
                    System.out.println((i + 1) + " - " + stocks[i].name + " (PKR " + stocks[i].price + ")");
                }
                int index = scanner.nextInt() - 1;
                System.out.print("Enter quantity: ");
                int qty = scanner.nextInt();
                if (index >= 0 && index < stocks.length && qty > 0) {
                    user.buy(stocks, index, qty);
                } else {
                    System.out.println("Invalid input.");
                }

            } else if (choice == 3) {
                System.out.println("Enter stock number to sell:");
                for (int i = 0; i < stocks.length; i++) {
                    System.out.println((i + 1) + " - " + stocks[i].name);
                }
                int index = scanner.nextInt() - 1;
                System.out.print("Enter quantity: ");
                int qty = scanner.nextInt();
                if (index >= 0 && index < stocks.length && qty > 0) {
                    user.sell(stocks, index, qty);
                } else {
                    System.out.println("Invalid input.");
                }

            } else if (choice == 4) {
                user.show(stocks);

            } else if (choice == 5) {
                System.out.println("Thanks for trading with us.");
                break;

            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}