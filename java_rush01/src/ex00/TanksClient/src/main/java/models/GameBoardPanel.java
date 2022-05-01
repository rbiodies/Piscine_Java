package models;

import server.Client;

import javax.swing.*;

public class GameBoardPanel extends JPanel {

    private Tank    tank;
    private boolean gameStatus;

    public GameBoardPanel(Tank tank, Client client, boolean gameStatus) {
        this.tank = tank;
        this.gameStatus = gameStatus;
        setSize(609, 523);
        setBounds(-50, 0, 609, 523);
//        addKeyListener(new InputManager(tank));
    }
}
