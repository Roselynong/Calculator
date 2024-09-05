/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package school;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedCalculator extends JFrame implements ActionListener {
    // Display field
    private JTextField display;
    
    // Variables to store the current state
    private double A = 0, B = 0, C = 0, D = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public AdvancedCalculator() {
        // Initialize the frame
        setTitle("Advanced Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the display field
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Create the panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 6, 5, 5));
        add(panel, BorderLayout.CENTER);

        // Add buttons
        String[] buttons = {
                "DEL", "AC", "+/-", "FLR", "CEIL", "INT",
                "7", "8", "9", "/", "%", "N!",
                "4", "5", "6", "*", "√", "X^2",
                "1", "2", "3", "-", "x^y", "log",
                "0", ".", "=", "+", "Σ", "∫",
                "SET", "A", "B", "C", "D", "Cx",
                "logx", "logy", "al+b!", "x+y", "Cxy", "x!"
        };

        for (String button : buttons) {
            JButton b = new JButton(button);
            b.setFont(new Font("Arial", Font.BOLD, 18));
            b.addActionListener(this);
            panel.add(b);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case "DEL":
                    if (display.getText().length() > 0) {
                        display.setText(display.getText().substring(0, display.getText().length() - 1));
                    }
                    break;
                case "AC":
                    display.setText("0");
                    startNewNumber = true;
                    break;
                case "+/-":
                    if (!display.getText().equals("0")) {
                        display.setText(String.valueOf(Double.parseDouble(display.getText()) * -1));
                    }
                    break;
                case "FLR":
                    display.setText(String.valueOf(Math.floor(Double.parseDouble(display.getText()))));
                    break;
                case "CEIL":
                    display.setText(String.valueOf(Math.ceil(Double.parseDouble(display.getText()))));
                    break;
                case "INT":
                    display.setText(String.valueOf((int) Double.parseDouble(display.getText())));
                    break;
                case "N!":
                    display.setText(String.valueOf(factorial((int) Double.parseDouble(display.getText()))));
                    break;
                case "=":
                    B = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(calculate(A, B, operator)));
                    startNewNumber = true;
                    break;
                case "SET":
                    A = B = C = D = Double.parseDouble(display.getText());
                    break;
                case "A":
                case "B":
                case "C":
                case "D":
                    display.setText(String.valueOf(getVariableValue(command)));
                    break;
                case "/":
                case "*":
                case "-":
                case "+":
                case "x^y":
                case "log":
                    A = Double.parseDouble(display.getText());
                    operator = command;
                    startNewNumber = true;
                    break;
                case "x!":
                    display.setText(String.valueOf(factorial((int) Double.parseDouble(display.getText()))));
                    break;
                case "Σ":
                    display.setText(String.valueOf(sumSeries((int) A, (int) B)));
                    break;
                case "∫":
                    display.setText("Integral Functionality"); // Placeholder
                    break;
                default:
                    if (startNewNumber) {
                        display.setText(command);
                        startNewNumber = false;
                    } else {
                        display.setText(display.getText() + command);
                    }
                    break;
            }
        } catch (Exception ex) {
            display.setText("Error");
            startNewNumber = true;
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "/":
                return a / b;
            case "*":
                return a * b;
            case "-":
                return a - b;
            case "+":
                return a + b;
            case "x^y":
                return Math.pow(a, b);
            case "log":
                return Math.log(b) / Math.log(a); // log_a(b)
            default:
                return 0;
        }
    }

    private double factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    private double sumSeries(int a, int b) {
        double sum = 0;
        for (int i = a; i <= b; i++) {
            sum += i;
        }
        return sum;
    }

    private double getVariableValue(String var) {
        switch (var) {
            case "A":
                return A;
            case "B":
                return B;
            case "C":
                return C;
            case "D":
                return D;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        AdvancedCalculator calculator = new AdvancedCalculator();
        calculator.setVisible(true);
    }
}

