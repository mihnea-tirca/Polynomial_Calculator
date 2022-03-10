package GUI;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
        private JPanel contentPanel;

        private JPanel inputPanel;
        private JLabel firstPolynomialLabel;
        private JTextField firstPolynomialTextField;
        private JLabel secondPolynomialLabel;
        private JTextField secondPolynomialTextField;
        private JLabel operationsLabel;
        private JComboBox operationsComboBox;
        private JButton computeButton;

        private JPanel resultPanel;
        private JLabel resultLabel;
        private JLabel resultValueLabel;
        private JLabel remainderLabel;
        private JLabel remainderValueLabel;


        Controller controller = new Controller(this);

        public View(String name){
                super(name);
                this.initGUI();
                firstPolynomialTextField.setText("1x^3-2x^2+6x^1-5x^0");
                secondPolynomialTextField.setText("1x^2-1x^0");
        }

        public void initGUI(){
                this.setSize(500, 300);
                this.contentPanel = new JPanel(new GridLayout(2, 1));
                this.setContentPane(contentPanel);
                this.initPolynomialsPanel();
                this.initResultPanel();
                this.setVisible(true);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void initPolynomialsPanel(){
                inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(4, 2));

                firstPolynomialLabel = new JLabel("First Polynomial", JLabel.CENTER);
                inputPanel.add(firstPolynomialLabel);

                firstPolynomialTextField = new JTextField();
                inputPanel.add(firstPolynomialTextField);

                secondPolynomialLabel = new JLabel("Second Polynomial", JLabel.CENTER);
                inputPanel.add(secondPolynomialLabel);

                secondPolynomialTextField = new JTextField();
                inputPanel.add(secondPolynomialTextField);

                operationsLabel = new JLabel("Select Operation", JLabel.CENTER);
                inputPanel.add(operationsLabel);

                String[] operations = new String[]{"Add", "Subtract", "Multiply", "Divide", "Derive", "Integrate"};
                operationsComboBox = new JComboBox(operations);
                inputPanel.add(operationsComboBox);

                computeButton = new JButton("COMPUTE");
                computeButton.setActionCommand("COMPUTE");
                computeButton.addActionListener(controller);
                inputPanel.add(computeButton);

                contentPanel.add(inputPanel);
        }

        public void initResultPanel(){
                this.resultPanel = new JPanel();
                this.resultPanel.setLayout(new GridLayout(2, 2));

                this.resultLabel = new JLabel("Result:", JLabel.CENTER);
                this.resultPanel.add(resultLabel);

                this.resultValueLabel = new JLabel("");
                this.resultPanel.add(resultValueLabel);

                this.remainderLabel = new JLabel("", JLabel.CENTER);
                this.resultPanel.add(remainderLabel);

                this.remainderValueLabel = new JLabel("");
                this.resultPanel.add(remainderValueLabel);

                this.contentPanel.add(this.resultPanel);
        }

        public JTextField getFirstPolynomialTextField() {return firstPolynomialTextField;}
        public JTextField getSecondPolynomialTextField() {return secondPolynomialTextField;}
        public JComboBox getOperationsComboBox() {return operationsComboBox;}
        public JLabel getResultLabel() {return resultLabel;}
        public JLabel getResultValueLabel() {return resultValueLabel;}
        public JLabel getRemainderLabel() {return remainderLabel;}
        public JLabel getRemainderValueLabel() {return remainderValueLabel;}
}
