package me.diademiemi.adventageous.gui.menu;

public enum MenuSize {
    HALF_ROW(5),
    ONE_ROW(9),
    TWO_ROWS(18),
    THREE_ROWS(27),
    FOUR_ROWS(36),
    FIVE_ROWS(45),
    SIX_ROWS(54);

    private int size;

    /**
     * @param size the size of the GUI
     */
    private MenuSize(int size) {
        this.size = size;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
