package Package_StoreGame;

public class User {
    private final String name;
    private int appleAmount;
    private int balance;

    public User(String name, int appleAmount, int balance) {
        this.name = name;
        this.appleAmount = appleAmount;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getAppleAmount() {
        return appleAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void AddApples(int amount, int price)
    {
        this.appleAmount += amount;
        this.balance -= (amount*price);
    }

    public void RemoveApples(int amount, int price)
    {
        this.appleAmount -= amount;
        this.balance += (amount * price);
    }
}
