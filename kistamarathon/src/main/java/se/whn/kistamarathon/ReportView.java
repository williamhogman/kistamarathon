package se.whn.kistamarathon;

import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class ReportView extends JTextArea {
    Marathon marathon;
    SortOrderSelection soSel;

    public ReportView(Marathon marathon, SortOrderSelection soSel) {
        super();
        this.soSel = soSel;
        this.marathon = marathon;
    }

    public void update() {
        setText(marathon.generateReport(soSel.getOrder()));
    }
}
