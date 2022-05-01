package server;

import models.GameBoardPanel;
import models.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ClientGUI extends JFrame implements WindowListener, ActionListener {

    private JPanel          registerPanel;
    public static JPanel    gameStatusPanel;

    private JLabel  ipaddressLabel;
    private JLabel  portLabel;
    private JLabel  scoreLabel;

    private JTextField  ipaddressText;
    private JTextField  portText;

    private JButton registerButton;

    private Client  client;
    private Tank clientTank;

    private GameBoardPanel  boardPanel;

    public ClientGUI() {
        setTitle("Multi-clients Tanks Game");
        setSize(790, 580);
        setLocation(60,100);
        getContentPane().setBackground(Color.BLACK);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addWindowListener(this);
        registerPanel = new JPanel();
        registerPanel.setBackground(Color.YELLOW);
        registerPanel.setSize(200,140);
        registerPanel.setBounds(560,50,200,140);
        setLayout(null);

        gameStatusPanel = new JPanel();
        gameStatusPanel.setBackground(Color.YELLOW);
        gameStatusPanel.setSize(200,300);
        gameStatusPanel.setBounds(560,210,200,311);
        gameStatusPanel.setLayout(null);

        ipaddressLabel = new JLabel("IP address: ");
        ipaddressLabel.setBounds(10,25,70,25);

        portLabel = new JLabel("Port: ");
        portLabel.setBounds(10,55,50,25);

        scoreLabel = new JLabel("Score : 0");
        scoreLabel.setBounds(10,90,100,25);

        ipaddressText = new JTextField("localhost");
        ipaddressText.setBounds(90,25,100,25);

        portText = new JTextField("11111");
        portText.setBounds(90,55,100,25);

        registerButton = new JButton("Register");
        registerButton.setBounds(60,100,90,25);
        registerButton.addActionListener(this);
        registerButton.setFocusable(true);


        registerPanel.add(ipaddressLabel);
        registerPanel.add(portLabel);
        registerPanel.add(ipaddressText);
        registerPanel.add(portText);
        registerPanel.add(registerButton);

        gameStatusPanel.add(scoreLabel);

        client = Client.getGameClient();

        clientTank = new Tank();
        boardPanel = new GameBoardPanel(clientTank, client, false);

        getContentPane().add(registerPanel);
        getContentPane().add(gameStatusPanel);
        getContentPane().add(boardPanel);
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object  obj = e.getSource();

        if (obj == registerButton) {
            registerButton.setEnabled(false);
        }
    }
}
