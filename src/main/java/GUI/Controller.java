package GUI;

import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ListIterator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements ActionListener {
    private View view;

    public Controller(View v) {
        this.view = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (Objects.equals(command, "COMPUTE")) {
            Polynomial firstPolynomial = Controller.stringToPolynomial(view.getFirstPolynomialTextField().getText());
            Polynomial secondPolynomial = Controller.stringToPolynomial(view.getSecondPolynomialTextField().getText());
            String operation = String.valueOf(view.getOperationsComboBox().getSelectedItem());
            Polynomial result;
            Polynomial[] results;
            switch (operation) {
                case "Add":
                    view.getResultLabel().setText("Result:");
                    view.getRemainderLabel().setText("");
                    view.getRemainderValueLabel().setText("");
                    result = firstPolynomial.add(secondPolynomial);
                    view.getResultValueLabel().setText(result.toString());
                    break;
                case "Subtract":
                    view.getResultLabel().setText("Result:");
                    view.getRemainderLabel().setText("");
                    view.getRemainderValueLabel().setText("");
                    result = firstPolynomial.subtract(secondPolynomial);
                    view.getResultValueLabel().setText(result.toString());
                    break;
                case "Multiply":
                    view.getResultLabel().setText("Result:");
                    view.getRemainderLabel().setText("");
                    view.getRemainderValueLabel().setText("");
                    result = firstPolynomial.multiply(secondPolynomial);
                    view.getResultValueLabel().setText(result.toString());
                    break;
                case "Divide":
                    view.getResultLabel().setText("Quotient:");
                    view.getRemainderLabel().setText("Remainder:");
                    results = firstPolynomial.divide(secondPolynomial);
                    result = results[0];
                    if (results[1] != null)
                        view.getRemainderValueLabel().setText(results[1].toString());
                    else
                        view.getRemainderValueLabel().setText("0");
                    view.getResultValueLabel().setText(result.toString());
                    break;
                case "Derive":
                    view.getResultLabel().setText("Result:");
                    view.getRemainderLabel().setText("");
                    view.getRemainderValueLabel().setText("");
                    result = firstPolynomial.derive();
                    view.getResultValueLabel().setText(result.toString());
                    break;
                case "Integrate":
                    view.getResultLabel().setText("Result:");
                    view.getRemainderLabel().setText("");
                    view.getRemainderValueLabel().setText("");
                    result = firstPolynomial.integrate();
                    view.getResultValueLabel().setText(result.doubleToString());
                    break;
            }
        }
    }

    public static Polynomial stringToPolynomial(String polynomialString){
        String TERM_PATTERN = "(-?\\b\\d+)x\\^(-?\\d+\\b)";
        Pattern termPattern = Pattern.compile(TERM_PATTERN);
        Matcher termMatcher = termPattern.matcher(polynomialString);

        Polynomial polynomial = new Polynomial();

        while (termMatcher.find()) {
            Monomial monomial = new Monomial(Integer.parseInt(termMatcher.group(1)), Integer.parseInt(termMatcher.group(2)));
            ListIterator<Monomial> listIterator = polynomial.monomials.listIterator(0);
            while (listIterator.hasNext()) {
                int nextIndex = listIterator.nextIndex();
                Monomial nextMonomial = listIterator.next();
                if (monomial.grad < nextMonomial.grad) {
                    polynomial.monomials.add(nextIndex, monomial);
                    break;
                } else if (nextMonomial.grad == monomial.grad) {
                    nextMonomial.coeficient += monomial.coeficient;
                    break;
                }
            }
            if (!listIterator.hasNext()) {
                polynomial.monomials.add(monomial);
            }
        }
        return polynomial;
    }

    public static String format(double num) {
        DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
        decimalSymbols.setDecimalSeparator('.');
        return new DecimalFormat("0.00", decimalSymbols).format(num);
    }

}
