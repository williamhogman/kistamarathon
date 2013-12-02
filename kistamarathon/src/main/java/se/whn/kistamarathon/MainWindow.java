package se.whn.kistamarathon;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class MainWindow extends JFrame {

    // Domain objects
    private Marathon marathon;

    // GUI objects
    private SortOrderSelection sortOrder;
    private ActionPanel panel;
    private ReportView report;

    public MainWindow(Marathon marathon) {
        super("KistaMarathon");
        this.marathon = marathon;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setupComponents();
    }

    private void setupComponents() {
        sortOrder = new SortOrderSelection();
        panel = new ActionPanel(this);
        report = new ReportView(marathon, sortOrder);

        add(new JLabel("Kista Marathon"), BorderLayout.NORTH);
        add(report, BorderLayout.CENTER);
        add(sortOrder, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);
    }

    private void updateReport() {
        report.update();
    }

    private void openPopup(JFrame frame) {
        frame.setVisible(true);
    }

    public void transitionTo(String what) {
        if(what.equals("Show")) {
            updateReport();
        } else if (what.equals("New")) {
            openPopup(new CreateRunnerFrame(marathon));
        } else if (what.equals("Time")) {
            openPopup(new TimeFrame(marathon));
        }
    }
}
