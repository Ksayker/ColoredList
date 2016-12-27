package ksayker.coloredlist.data;

/**
 * @author ksayker
 * @version 0.0.1
 * @since 27.12.16
 */
public class ItemColor {
    private boolean isSelected;
    private int mColor;
    private String mName;

    public ItemColor(boolean selected, int color, String name) {
        this.isSelected = selected;
        this.mColor = color;
        this.mName = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public int getColor() {
        return mColor;
    }

    public String getName() {
        return mName;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
