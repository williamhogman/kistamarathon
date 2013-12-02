package se.whn.kistamarathon;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class SortOrderSelection extends JPanel {
    private ButtonGroup group;

    public SortOrderSelection() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setupComponents();
    }

    private void setupComponents() {
        group = new ButtonGroup();
        SortOrder[] orders = SortOrder.values();
        for(SortOrder so : orders) {
            JRadioButton btn = new JRadioButton(so.toString());
            btn.getModel().setActionCommand(so.toString());
            group.add(btn);
            add(btn);
        }

        // Select the first one
        group.setSelected(group.getElements().nextElement().getModel(), true);
    }

    public SortOrder getOrder() {
        return SortOrder.valueOf(group.getSelection().getActionCommand());
    }
}
