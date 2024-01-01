import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DHClientGUI {

    private JFrame frame;
    private JTextField hostField;
    private JTextArea messageArea;

    public DHClientGUI() {
        frame = new JFrame("DHClient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(255, 255, 255));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        JLabel titleLabel = new JLabel("Secure Communication with DHClient");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(10, 10, 380, 25);
        panel.add(titleLabel);

        JLabel hostLabel = new JLabel("Enter host name or IP of server:");
        hostLabel.setBounds(10, 40, 200, 25);
        panel.add(hostLabel);

        hostField = new JTextField(20);
        hostField.setBounds(220, 40, 150, 25);
        panel.add(hostField);

        JLabel messageLabel = new JLabel("Enter the message to be encrypted:");
        messageLabel.setBounds(10, 70, 250, 25);
        panel.add(messageLabel);

        messageArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBounds(10, 100, 360, 80);
        panel.add(scrollPane);

        JButton encryptButton = new JButton("Encrypt and Send");
        encryptButton.setBounds(10, 200, 150, 30);
        encryptButton.setBackground(new Color(0, 123, 255));
        encryptButton.setForeground(Color.WHITE);
        encryptButton.setFocusPainted(false);
        panel.add(encryptButton);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connectToServer();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void connectToServer() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String host = hostField.getText();

        // Ensure the host field is not empty
        if (host.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter the host name or IP of the server.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String messageToEncrypt = messageArea.getText();

        // Ensure the message field is not empty
        if (messageToEncrypt.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter the message to be encrypted.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DHClient.connectToServer(host, messageToEncrypt);

        // Optional: You can add code to handle the response from the server if needed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DHClientGUI();
            }
        });
    }
}
