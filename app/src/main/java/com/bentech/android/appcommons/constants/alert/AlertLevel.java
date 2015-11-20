package com.bentech.android.appcommons.constants.alert;

/**
 * Created by Daniel on 20/11/2015.
 */
public abstract class AlertLevel {

    public static AlertLevel INFO = new InfoAlertLevel();
    public static AlertLevel WARN = new WarnAlertLevel();
    public static AlertLevel ERROR = new ErrorAlertLevel();
    public static AlertLevel SUCCESS = new SuccessAlertLevel();

    protected int bgColor;
    protected int fgColor;

    AlertLevel(int bgColor, int fgColor) {
        this.bgColor = bgColor;
        this.fgColor = fgColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getFgColor() {
        return fgColor;
    }

    public void setFgColor(int fgColor) {
        this.fgColor = fgColor;
    }
}
