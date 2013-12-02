package se.whn.kistamarathon;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public abstract class Dialog extends JFrame {
    private JLabel error;

    public Dialog(String title) {
        super(title);
        setLayout(new BorderLayout());
    }

    protected GridBagConstraints defaultGBC(int n) {
	GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5; // There are two things sharing space on the xaxis
        c.weighty = 1.0/getLabels().length;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = n;
        return c;
    }

    protected GridBagConstraints label(int n) {
        GridBagConstraints c = defaultGBC(n);
        c.gridx = 0;
        return c;
    }

    protected GridBagConstraints value(int n) {
        GridBagConstraints c = defaultGBC(n);
        c.gridx = 1;
        c.gridwidth = 2;
        return c;
    }

    protected void setupLabels(JPanel form) {
        String[] labels = getLabels();
        for(int i = 0; i < labels.length; i++) {
            form.add(new JLabel(labels[i] + ": "), label(i));
        }
    }

    protected void setupComponents() {
        // The actual form
        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        add(form, BorderLayout.CENTER);

        setupLabels(form);
        setupInputs(form);

        // The error message
        error = new JLabel();
        add(error, BorderLayout.NORTH);

        // Th2e buttons
        DialogResponse resp = new DialogResponse(this);
        add(resp, BorderLayout.SOUTH);
    }

    protected void displayError(Exception ex) {
        displayError(ex.toString());
    }

    protected void displayError(String s) {
        error.setText(s);
    }

    protected abstract String[] getLabels();
    protected abstract void setupInputs(JPanel form);

    public abstract void handleOk();
    public void handleCancel() {
        dispose();
    }
}
