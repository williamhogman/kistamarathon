package se.whn.kistamarathon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateRunnerFrame extends Dialog {
    private final static String[] LABELS = {"Starting number", "Name", "Country", "Age"};
    private Marathon marathon;

    private JTextField namebox, countrybox, agebox;

    public CreateRunnerFrame(Marathon marathon) {
        super("Register new runner");
        this.marathon = marathon;
        setupComponents();
    }

    @Override
    protected void setupInputs(JPanel form) {
        String nextNo = Integer.toString(marathon.getNextStartingNumber());

        namebox = new JTextField(15);
        countrybox = new JTextField(15);
        agebox = new JTextField(15);

        JComponent[] components = {
            new JLabel(nextNo),
            namebox,
            countrybox,
            agebox,
        };
        
        for(int i = 0; i < components.length; i++) {
            form.add(components[i], value(i));
        }
    }

    @Override
    protected String[] getLabels() {
        return LABELS;
    }

    @Override
    public void handleOk() {
        String name = namebox.getText();
        String country = countrybox.getText();
        int age;

        try {
            age = Integer.parseInt(agebox.getText());
        } catch (NumberFormatException ex) {
            displayError(ex);
            return;
        }

        try {
            marathon.addRunner(name, country, age);
        } catch (IllegalArgumentException ex) {
            displayError(ex);
            return;
        }

        // If we made it this far, close down the frame :)
        this.dispose();
    }
}
