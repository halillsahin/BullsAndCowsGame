import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class BullsAndCowsGame {
    
    
    public static void main(String[] args) {
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();


        while (true) {
            int randomNumber = random.nextInt(10);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            } else {
                continue;
            }
            if (numbers.size() == 4) {
                break;
            }
        }

        StringBuilder numberString = new StringBuilder();
        for (int number : numbers) {
            numberString.append(number);
        }

        String secretnumber = numberString.toString();


        JFrame jf = new JFrame("BullsandCroweGame");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 500);
        jf.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        JPanel jp = new JPanel(new GridLayout(1, 10));
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        jf.add(jp, gbc);

        JPanel panel2 = new JPanel();
        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 1;
        jf.add(panel2, gbc);

        JPanel panel1 = new JPanel();
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        jf.add(panel1, gbc);

        JTextArea sonuc = new JTextArea(60, 30);
        sonuc.setEditable(false);
        panel2.add(sonuc);

        JLabel label = new JLabel("00:00");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel1.add(label);
        Timer timer = new Timer(1000, new ActionListener() {
            int seconds = 0;
            int minutes = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
                String time = String.format("%02d:%02d", minutes, seconds);
                label.setText(time);
            }
        });
        timer.start();

        JTextField ekran = new JTextField(5);
        ekran.setEditable(true);
        ekran.setPreferredSize(new Dimension(60, 30));


        JButton check = new JButton("CHECK");
        check.setPreferredSize(new Dimension(100, 30));
        check.setEnabled(false);
        panel1.add(ekran);
        panel1.add(check);

        ekran.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

            private void updateButtonState() {
                if (ekran.getText().length() == 4) {
                    check.setEnabled(true);
                } else {
                    check.setEnabled(false);
                }
            }
        });

        JButton clear = new JButton("Clear");
        panel1.add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ekran.setText("");
            }
        });

        JButton exit=new JButton("Exit");
        JButton restart=new JButton("restart");


        JLabel denemesayısı = new JLabel("0");
        denemesayısı.setFont(new Font("Arial", Font.BOLD, 20));
        panel1.add(denemesayısı);

        check.addActionListener(new ActionListener() {
            int sayı = 0;

            @SuppressWarnings("unlikely-arg-type")
            @Override
            public void actionPerformed(ActionEvent e) {
                sayı++;
                denemesayısı.setText(Integer.toString(sayı));
                
                String guessStr = ekran.getText() + "";
                int[] guess = new int[4];
                for (int i = 0; i < 4; i++) {
                    guess[i] = Character.getNumericValue(guessStr.charAt(i));
                }

                int bulls = 0;

                HashSet<Integer> set1 = new HashSet<>();
                HashSet<Integer> set2 = new HashSet<>();

                for (int a = 0; a < 4; a++) {
                    set1.add(guess[a]);
                    set2.add(Integer.parseInt(String.valueOf(secretnumber.charAt(a))));
                }
                int kesişim = findIntersection(set1, set2);
                for (int i = 0; i < 4; i++) {
                    if (guess[i] == Integer.parseInt(String.valueOf(secretnumber.charAt(i)))) {
                        bulls++;
                    }
                }
                int cows = kesişim - bulls;
                sonuc.append(guessStr + " - Bulls: " + bulls + ", Cows: " + cows + "\n");
                if (secretnumber.equals(guessStr)){
                    JOptionPane.showMessageDialog(null,"Congratulations! You guessed in "+label.getText() +" and "+ denemesayısı.getText() + " time");
                    int choice = JOptionPane.showOptionDialog(jf, "Do you want to play again?", "Game Over",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"Restart", "Exit"}, "Restart");

            if (choice == JOptionPane.YES_OPTION) {
                    main(args);

                } else if (choice == JOptionPane.NO_OPTION) {
                    System.exit(0); // Çıkış yap
}
                }
                
                
                ekran.setText("");
            }
        });

        JButton sifir = new JButton("0");
        JButton bir = new JButton("1");
        JButton iki = new JButton("2");
        JButton uc = new JButton("3");
        JButton dort = new JButton("4");
        JButton bes = new JButton("5");
        JButton alti = new JButton("6");
        JButton yedi = new JButton("7");
        JButton sekiz = new JButton("8");
        JButton dokuz = new JButton("9");

        sifir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("0")){
                    ekran.setText(ekran.getText() + "0");
                }
            }
        });

        bir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (!ekran.getText().contains("1")){
                    ekran.setText(ekran.getText() + "1");
                }
            }
        });

        iki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("2")){
                    ekran.setText(ekran.getText() + "2");
                }
            }
        });

        uc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("3")){
                    ekran.setText(ekran.getText() + "3");
                }
            }
        });

        dort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("4")){
                    ekran.setText(ekran.getText() + "4");
                }
            }
        });

        bes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("5")){
                    ekran.setText(ekran.getText() + "5");
                }
            }
        });

        alti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("6")){
                    ekran.setText(ekran.getText() + "6");
                }
            }
        });

        yedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("7")){
                    ekran.setText(ekran.getText() + "7");
                }
            }
        });

        sekiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("8")){
                    ekran.setText(ekran.getText() + "8");
                }
            }
        });

        dokuz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ekran.getText().contains("9")){
                    ekran.setText(ekran.getText() + "9");
                }
            }
        });

  


        jp.add(bir);
        jp.add(iki);
        jp.add(uc);
        jp.add(dort);
        jp.add(bes);
        jp.add(alti);
        jp.add(yedi);
        jp.add(sekiz);
        jp.add(dokuz);
        jp.add(sifir);
        jf.setVisible(true);
    }

    public static int findIntersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection.size();
   
    }

}

