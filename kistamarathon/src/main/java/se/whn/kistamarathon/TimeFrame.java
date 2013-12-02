package se.whn.kistamarathon;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComponent;

public class TimeFrame extends Dialog {
    private final static String[] LABELS = {"Starting number", "Time"};
    private Marathon marathon;

    private JTextField startingNoBox, timeBox;

    public TimeFrame(Marathon marathon) {
        super("Register time");
        this.marathon = marathon;
        setupComponents();
    }

    @Override
    protected void setupInputs(JPanel form) {
        startingNoBox = new JTextField();
        timeBox = new JTextField();

        JComponent[] components = {startingNoBox, timeBox};
        for(int i = 0; i < components.length; i++) {
            form.add(components[i], value(i));
        }
    }

    @Override
    protected String[] getLabels() {
        return LABELS;
    }

    private int parseEmptyIsZero(String s) {
        if (s.length() == 0) {
            return 0;
        }
        return Integer.parseInt(s);
    }

    @Override
    public void handleOk() {
        String timeS = timeBox.getText();

        Integer startingNo;
        try {
            startingNo = Integer.parseInt(startingNoBox.getText());
        } catch (NumberFormatException ex) {
            displayError(ex);
            return;
        }


        int i = timeS.indexOf(".");
        
        if(i == -1) {
            i = timeS.length();
        }


        int min, hrs;

        try {
            min = parseEmptyIsZero(timeS.substring(i + i));
        } catch (NumberFormatException ex) {
            displayError(ex);
            return;
        }
        
        try {
            hrs = Integer.parseInt(timeS.substring(0, i));
        } catch (NumberFormatException ex) {
            displayError(ex);
            return;
        }

        try {
            marathon.registerTime(startingNo, hrs, min);
        } catch (IllegalArgumentException ex) {
            displayError(ex);
            return;
        } catch (IllegalStateException ex) {
            displayError(ex);
            return;
        }

        dispose();
    }
}

