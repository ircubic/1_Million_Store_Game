package Package_StoreGame;

public class Merchant {
    private final String name;
    private final int applePrice;
    private int appleAmount;

    public Merchant(String name, int appleAmount, int applePrice) {
        this.name = name;
        this.appleAmount = appleAmount;
        this.applePrice = applePrice;
    }

    public String GetName() {
        return name;
    }

    public int getAppleAmount() {
        return appleAmount;
    }

    public int getApplePrice() {
        return applePrice;
    }

    public void RemoveApples(int amount) {
        this.appleAmount -= amount;
    }

    public void AddApples(int amount) {
        this.appleAmount += amount;
    }
}
