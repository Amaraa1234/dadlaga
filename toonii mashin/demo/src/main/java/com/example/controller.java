package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Stack;

public class controller {

    @FXML
    private TextField displayField;

    private String operator = "";
    private double firstOperand = 0;
    private boolean startOfNumber = true;

    // Хаалт нээгдсэн эсэхийг тоолох хувьсагч
    private int openParenthesesCount = 0;

    // 0-9 хүртэлх тоонууд болон цэг дарагдахад ажиллана
    @FXML
    void onNumberClick(ActionEvent event) {
        String number = ((Button) event.getSource()).getText();

        // Хэрэв дэлгэц "0" эсвэл шинэ тоо эхэлж байвал солих
        if (startOfNumber || "0".equals(displayField.getText())) {
            displayField.setText(number);
            startOfNumber = false;
        } else {
            // Цэг дарахад тухайн тоонд аль хэдийн цэг орсон эсэхийг шалгах
            if (".".equals(number)) {
                String currentText = displayField.getText();
                String[] parts = currentText.split("[\\+\\-\\*\\/\\(\\)]");
                String lastPart = parts.length > 0 ? parts[parts.length - 1] : "";
                if (lastPart.contains(".")) {
                    return; // Текстэд цэг аль хэдийн байвал дахин цэг тавихгүй
                }
            }
            displayField.appendText(number);
        }
    }

    // Хаалт () товчлуур дарагдахад ажиллах функц
    @FXML
    void onParenthesisClick(ActionEvent event) {
        String currentText = displayField.getText();

        if (startOfNumber || "0".equals(currentText) || currentText.isEmpty()) {
            displayField.setText("(");
            openParenthesesCount++;
            startOfNumber = false;
        } else {
            char lastChar = currentText.charAt(currentText.length() - 1);

            // Хэрэв хамгийн сүүлийн тэмдэг үйлдэл эсвэл нээх хаалт байвал шууд нээх хаалт
            // тавина
            if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/' || lastChar == '(') {
                displayField.appendText("(");
                openParenthesesCount++;
            }
            // Хэрэв нээсэн хаалт байгаа бөгөөд сүүлийн тэмдэг тоо эсвэл хаах хаалт бол
            // хаана
            else if (openParenthesesCount > 0) {
                displayField.appendText(")");
                openParenthesesCount--;
            }
            // Бусад тохиолдолд үржүүлэх үйлдэл гэж үзэж үржих тэмдэгтэй хаалт нээнэ
            else {
                displayField.appendText("*(");
                openParenthesesCount++;
            }
        }
    }

    // Үйлдлийн тэмдэгүүд дарагдахад ажиллана
    @FXML
    void onOperatorClick(ActionEvent event) {
        String currentOp = ((Button) event.getSource()).getText();
        String currentText = displayField.getText().trim();

        if (currentText.isEmpty() || "0".equals(currentText)) {
            // Эхлээд хасах тэмдэг орж ирвэл сөрөг тоо гэж үзнэ
            if ("-".equals(currentOp)) {
                displayField.setText("-");
                startOfNumber = false;
            }
            return;
        }

        try {
            double currentVal = Double.parseDouble(currentText);

            if ("sin".equals(currentOp)) {
                double result = Math.sin(Math.toRadians(currentVal));
                displayField.setText(formatResult(result));
                startOfNumber = true;
                return;
            } else if ("cos".equals(currentOp)) {
                double result = Math.cos(Math.toRadians(currentVal));
                displayField.setText(formatResult(result));
                startOfNumber = true;
                return;
            }

            operator = currentOp;
            firstOperand = currentVal;
            displayField.appendText(currentOp);
            startOfNumber = false;

        } catch (NumberFormatException e) {
            // Хэрэв сүүлийн тэмдэг аль хэдийн оператор байвал дахин залгахгүй, солино
            char lastChar = currentText.charAt(currentText.length() - 1);
            if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
                displayField.setText(currentText.substring(0, currentText.length() - 1) + currentOp);
            } else {
                displayField.appendText(currentOp);
            }
            startOfNumber = false;
        }
    }

    // Тэнцүү (=) дарахад үр дүнг тооцоолно
    @FXML
    void onEqualClick(ActionEvent event) {
        String expression = displayField.getText().trim();

        if (expression.isEmpty() || "0".equals(expression)) {
            return;
        }

        try {
            // Хаалтууд дутуу хаагдсан бол автомат хааж өгөх хамгаалалт
            while (openParenthesesCount > 0) {
                expression += ")";
                openParenthesesCount--;
            }

            // Текстэн илэрхийллийг бодож хариуг гаргах
            double result = evaluateExpression(expression);

            // Үр дүнг дэлгэцэнд хэвлэх
            displayField.setText(formatResult(result));

            // Төлөвийг шинэчлэх
            startOfNumber = true;
            operator = "";

        } catch (ArithmeticException e) {
            displayField.setText("Алдаа: 0-д хувааж болохгүй");
            clearState();
        } catch (Exception e) {
            displayField.setText("Буруу илэрхийлэл");
            clearState();
        }
    }

    // Илэрхийлэл бодогч үндсэн алгоритм
    private double evaluateExpression(String expr) {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        char[] tokens = expr.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            // Сөрөг тоог таних дэмжлэг (Илэрхийллийн эхэнд эсвэл хаалтны ард хасах тэмдэг
            // байвал)
            if (tokens[i] == '-' && (i == 0 || tokens[i - 1] == '(' || tokens[i - 1] == '+' || tokens[i - 1] == '-'
                    || tokens[i - 1] == '*' || tokens[i - 1] == '/')) {
                StringBuilder sbuf = new StringBuilder("-");
                i++;
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    sbuf.append(tokens[i++]);
                }
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
                continue;
            }

            // Олон оронтой болон бутархай тоог салгах
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
                StringBuilder sbuf = new StringBuilder();
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    sbuf.append(tokens[i++]);
                }
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
            } else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    if (values.size() < 2)
                        throw new IllegalArgumentException();
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                if (!ops.isEmpty())
                    ops.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.isEmpty() && hasPrecedence(tokens[i], ops.peek())) {
                    if (values.size() < 2)
                        throw new IllegalArgumentException();
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(tokens[i]);
            }
        }

        while (!ops.isEmpty()) {
            if (values.size() < 2)
                throw new IllegalArgumentException();
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.isEmpty() ? 0 : values.pop();
    }

    // Үйлдлүүдийн эрэмбийг шалгах
    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        return true;
    }

    // Математик тооцоолол хийх
    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new ArithmeticException("Division by zero");
                return a / b;
        }
        return 0;
    }

    @FXML
    void onClearClick(ActionEvent event) {
        displayField.setText("0");
        clearState();
    }

    private void clearState() {
        operator = "";
        firstOperand = 0;
        openParenthesesCount = 0;
        startOfNumber = true;
    }

    // Бүхэл тооны ард байгаа .0-ийг арилгах формат
    private String formatResult(double result) {
        if (result % 1 == 0) {
            return String.valueOf((int) result);
        }
        return String.valueOf(result);
    }
}
