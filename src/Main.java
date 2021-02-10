import Package_StoreGame.Merchant;
import Package_StoreGame.User;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean shouldExit = false; //Flag is used in the while section

        int Day = 0;
        String Day_Message;

        String merchantName = "Teh Merchant";
        Random R = new Random();
        int Price_Apple_Low = 1;
        int Price_Apple_High = 100;
        int QTY_Apple_Low = 0;
        int QTY_Apple_High = 10_000;

        //Get the username
        ClearScreen();

        System.out.println("--------------------------------------------------------");
        System.out.println("Hello new Player, please enter your name below");
        System.out.println(" ");
        System.out.println("Also, to win the game, you must reach 1 million dollars");
        System.out.println("--------------------------------------------------------");

        SkipLines(7);

        Scanner input = new Scanner(System.in);
        String userName = input.nextLine();

        User user = new User(userName, 0, 100);

        //########################################################################################################
        //  Main Section - Every time a day passes, the merchant will come across a store with random pricing/apple count
        //  The user's goal is to gain the biggest profits and reach 1 million dollars!
        //--------------------------------------------------------------------------------------------------------
        while (!shouldExit) {
            //Day Counter
            Day = Day + 1;

            if (Day == 1) {
                Day_Message = "Welcome to the Apple Game, " + user.getName() + "! It is the first day of your journey.";
            } else {
                Day_Message = "Apple Gainz Day Number: " + Day;
            }

            // Get the Store's Apple pricing today and qty
            QTY_Apple_High = Math.max(user.getBalance() / 2, 10000);

            int merchantApples = GetRandomNumber(R, QTY_Apple_Low, QTY_Apple_High);
            int applePrice = GetRandomNumber(R, Price_Apple_Low, Price_Apple_High);
            var merchant = new Merchant(merchantName, merchantApples, applePrice);

            // Day_Message Portion
            while (true) {
                ClearScreen();

                System.out.println("-------------------------------------------------");
                System.out.println(Day_Message);
                System.out.println(" ");

                //Output the Merchant's Attributes
                System.out.println(merchant.GetName() + "'s total apples are " + merchant.getAppleAmount());
                System.out.println("The price of the Apple today is $" + merchant.getApplePrice());

                //  Finish the remainder of the message with the User's Input/Details
                System.out.println(" ");
                System.out.println("Enter a value below to represent your action:");
                System.out.println("1 = Purchase Apples");
                System.out.println("2 = Sell Apples");
                System.out.println("3 = Next Day");
                System.out.println("4 = Exit Game");
                System.out.println(" ");
                System.out.println("You currently have " + user.getAppleAmount() + " apples!");
                System.out.println("Your current balance is $" + user.getBalance());
                System.out.println("-------------------------------------------------");
                System.out.println(" ");
                System.out.println("Please enter your next action");

                //Prompt User
                int actionChoice = input.nextInt();

                //Take a look at the input
                if (actionChoice == 1) {   //This is the Purchase Option
                    int maxAmount = Math.min(merchant.getAppleAmount(), (user.getBalance() / merchant.getApplePrice()));
                    var applesToTransfer = getApplesTransferred("purchase", maxAmount, user, merchant, input);
                    merchant.RemoveApples(applesToTransfer);
                    user.AddApples(applesToTransfer, merchant.getApplePrice());
                } else if (actionChoice == 2) {    //This is the sell option
                    var applesToTransfer = getApplesTransferred("sell", user.getAppleAmount(), user, merchant, input);
                    merchant.AddApples(applesToTransfer);
                    user.RemoveApples(applesToTransfer, merchant.getApplePrice());
                } else if (actionChoice == 3) {
                    System.out.println("Proceeding to the next day...");
                    break;
                } else if (actionChoice == 4) {
                    shouldExit = true;
                    System.out.println("Exiting the Apple game");
                    System.out.println("Thanks for playing, " + user.getName() + "!");
                    break;
                } else {
                    System.out.println("[[The entered value is not a valid input!]]");
                }
            }

            //Check to see if the User has more than 1 million bucks, if so - end game!
            if (user.getBalance() >= 1_000_000) {
                shouldExit = true;
                ClearScreen();
                System.out.println("-------------------------------------------------");
                System.out.println("Congrats! You have beaten the game by having more than 1 mill!");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("Ending balance is $" + user.getBalance());
                System.out.println("Ending Apple count is " + user.getAppleAmount());
                System.out.println(" ");
                System.out.println("Exiting the Apple game");
                System.out.println("Thanks for playing, " + user.getName() + "!");
                System.out.println("-------------------------------------------------");
            }
        }
    }

    private static int getApplesTransferred(String action, int maxAmount, User user, Merchant merchant, Scanner input) {
        while (true) {
            ClearScreen();
            PrintDayIntro(merchant, user);

            System.out.println("You may " + action + " up to " + maxAmount);
            System.out.println("-------------------------------------------------");
            System.out.println("How many Apples would you like to " + action + "? Enter 0 to return...");

            int appleAmount = input.nextInt();

            if (appleAmount > maxAmount) {    //if the entered qty is higher than what the merchant has, cant
                System.out.println("Merchant or you cannot afford that... Please try another value.");
            } else {
                return appleAmount;
            }
        }
    }

    private static int GetRandomNumber(Random r, int lowerBound, int higherBound) {
        return r.nextInt(higherBound - lowerBound) + lowerBound;
    }

    private static void PrintDayIntro(Merchant merchant, User user) {
        ClearScreen();
        System.out.println("-------------------------------------------------");
        System.out.println(merchant.GetName() + " has " + merchant.getAppleAmount() + " apples.");
        System.out.println("The current price is $" + merchant.getApplePrice());
        System.out.println(" ");
        System.out.println("Your balance is: $" + user.getBalance());
        System.out.println("Apple balance is: " + user.getAppleAmount());
        System.out.println("-------------------------------------------------");
    }

    private static void ClearScreen() {
        SkipLines(100);
    }

    public static void SkipLines(int j) {
        for (int i = 1; i <= j; i++) {
            System.out.println(" ");
        }
    }
}
