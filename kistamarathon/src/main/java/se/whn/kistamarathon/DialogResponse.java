package se.whn.kistamarathon;

public class DialogResponse extends ButtonPanel {
    private static final String[] NAMES = {"Ok", "Cancel"};
    private Dialog dialog;

    public DialogResponse(Dialog dialog) {
        super();
        this.dialog = dialog;
    }

    @Override
    protected String[] getButtons() {
        return NAMES;
    }

    @Override
    protected void dispatchEvent(String name) {
        if(name.equals("Ok")) {
            dialog.handleOk();
        } else if(name.equals("Cancel")) {
            dialog.handleCancel();
        }
    }
}
