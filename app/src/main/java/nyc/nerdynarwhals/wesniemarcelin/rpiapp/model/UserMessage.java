package nyc.nerdynarwhals.wesniemarcelin.rpiapp.model;

/**
 * Created by wesniemarcelin on 6/3/17.
 */

public class UserMessage {
    public String greenButton;
    public String redButton;
    public String blueButton;

    public UserMessage() {
    }

    public UserMessage(String greenButton, String redButton, String blueButton) {
        this.greenButton = greenButton;
        this.redButton = redButton;
        this.blueButton = blueButton;
    }
    public String getGreenButton() {
        return greenButton;
    }

    public void setGreenButton(String greenButton) {
        this.greenButton = greenButton;
    }

    public String getRedButton() {
        return redButton;
    }

    public void setRedButton(String redButton) {
        this.redButton = redButton;
    }

    public String getBlueButton() {
        return blueButton;
    }

    public void setBlueButton(String blueButton) {
        this.blueButton = blueButton;
    }
}
