package se.whn.kistamarathon;

/**
 * Main class for running the kistamarathon app
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("booting up");
        // The only object used by the GUI
        Marathon marathon = new Marathon();
        // The root of the GUI itself
        MainWindow mw = new MainWindow(marathon);
        mw.setVisible(true);
    }
}
