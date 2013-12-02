package se.whn.kistamarathon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public abstract class ButtonPanel extends JPanel implements ActionListener {
    public ButtonPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setupComponents();
    }

    private void setupComponents() {
        for(String name: getButtons()) {
            JButton btn = new JButton(name);
            add(btn);
            btn.addActionListener(this);
        }
    }

    protected abstract void dispatchEvent(String name);
    protected abstract String[] getButtons();

    public void actionPerformed(ActionEvent e) {
        dispatchEvent(e.getActionCommand());
    }
}
