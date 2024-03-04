import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class URLShortenerApp extends JFrame {
    private JTextField longURLField;
    private JTextField shortURLField;
    private JButton shortenButton;
    private JButton expandButton; // Button to expand short URL
    private Map<String, String> urlMap;
    private JPanel inputPanel;
    private JLabel longURLLabel;

    public URLShortenerApp() {
        urlMap = new HashMap<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("URL Shortener");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        longURLLabel = new JLabel("Long URL:");
        longURLField = new JTextField(20);

        shortenButton = new JButton("Shorten");
        shortenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shortenURL();
            }
        });

        expandButton = new JButton("Expand"); // Initialize expandButton
        expandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expandURL();
            }
        });

        inputPanel.add(longURLLabel);
        inputPanel.add(longURLField);
        inputPanel.add(shortenButton);
        inputPanel.add(expandButton); // Add expandButton to inputPanel

        shortURLField = new JTextField(20);
        shortURLField.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(shortURLField, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void shortenURL() {
        String longURL = longURLField.getText().trim();
        if (!longURL.isEmpty()) {
            String shortURL = generateShortURL();
            urlMap.put(shortURL, longURL);
            shortURLField.setText(shortURL);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid URL.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void expandURL() {
        String shortURL = shortURLField.getText().trim();
        if (!shortURL.isEmpty() && urlMap.containsKey(shortURL)) {
            String longURL = urlMap.get(shortURL);
            JOptionPane.showMessageDialog(this, "Expanded URL: " + longURL, "Expanded URL", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Short URL not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateShortURL() {
        // Implementation for generating short URLs goes here
        // For simplicity, let's use a fixed length of 6 characters
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = characters.length();
        for (int i = 0; i < 6; i++) {
            int randomIndex = (int) (Math.random() * length);
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }

   
}
