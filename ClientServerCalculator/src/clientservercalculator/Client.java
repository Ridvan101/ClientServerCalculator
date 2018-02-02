package clientservercalculator;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
//Creating user interface
        Frame f = new Frame("Klijent-Server Plus Kalkulator");
        f.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        f.setSize(400, 250);

        Label prviOperand = new Label("Unesi prvi broj:  ");
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        f.add(prviOperand, c);

        TextField textF1 = new TextField();
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 0;
        f.add(textF1, c);

        Label drugiOperand = new Label("Unesi drugi broj:  ");
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        f.add(drugiOperand, c);

        TextField textF2 = new TextField();
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 1;
        f.add(textF2, c);

        Label rezultat = new Label("Rezultat je:           ");
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 3;
        f.add(rezultat, c);

        Button button = new Button("Saberi");
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 2;
        f.add(button, c);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if ((textF1.getText().isEmpty()) || (textF2.getText().isEmpty())) {
                    rezultat.setText("Unesi sve brojeve! ");
                } else {
                    try (Socket socket = new Socket("localhost", 7777);
                            InputStream input = socket.getInputStream();
                            OutputStream output = socket.getOutputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(input));) {
                        String poruka = textF1.getText().trim() + "," + textF2.getText().trim();
                        output.write((poruka + "\r\n").getBytes());

                        rezultat.setText("Rezultat je:   " + br.readLine());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            }
        });

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        f.setVisible(true);

    }
}
