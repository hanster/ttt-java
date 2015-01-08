package com.samhan;

/**
 * Created by sam on 08/01/15.
 * todo: define interface for the game view for multiple GUI implementations
 */
public interface View {
    public void displayBoard();
    public void choosePlayerMove();
    public boolean hasGameEnded();
    public String getUserInput();
}
