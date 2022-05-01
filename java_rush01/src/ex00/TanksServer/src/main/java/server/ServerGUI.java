package server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener {

    private JButton startServerButton;
    private JButton stopServerButton;
    private JLabel  statusLabel;

    private Server  server;

    public ServerGUI() {
        setTitle("Game Server GUI");
        setBounds(350,200,300,200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        startServerButton = new JButton("Start Server");
        startServerButton.setBounds(20,30,120,25);
        startServerButton.addActionListener(this);

        stopServerButton = new JButton("Stop Server");
        stopServerButton.setBounds(150,30,120,25);
        stopServerButton.addActionListener(this);

        statusLabel = new JLabel();
        statusLabel.setBounds(80,90,200,25);

        getContentPane().add(statusLabel);
        getContentPane().add(startServerButton);
        getContentPane().add(stopServerButton);

        server = new Server();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startServerButton) {
            server.start();
            stopServerButton.setEnabled(false);
            statusLabel.setText("Server is running...");
        }
        if (e.getSource() == stopServerButton) {
            server.stopServer();
        }
    }
}
