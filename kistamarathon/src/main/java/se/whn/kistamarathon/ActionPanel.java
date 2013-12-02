package se.whn.kistamarathon;

public class ActionPanel extends ButtonPanel {
    private static final String[] NAMES = {"New", "Show", "Time"};
    private MainWindow mainWindow;

    public ActionPanel(MainWindow mainWindow) {
        super();
        this.mainWindow = mainWindow;
    }

    @Override
    protected String[] getButtons() {
        return NAMES;
    }

    @Override
    protected void dispatchEvent(String name) {
        mainWindow.transitionTo(name);
    }

}
