package utils;

public class UserData 
{
    public static int coinsCollected = 0;
    public static int distanceTraveled = 0;
    public static int timeSurvived = 0;
    
    public UserData()
    {

    }

    public static void printData()
    {
        System.out.println("coins collected: "+coinsCollected);
        System.out.println("distance traveled: "+ distanceTraveled);
        System.out.println("time survived: "+timeSurvived);
    }
}
