import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class BoredCalculator {
    private static final Map<String, Double> variables = new HashMap<>();
    private static final Set<String> supportedFunctions = new HashSet<>(Arrays.asList(
            "sqrt", "sin", "cos", "tan", "log", "ln", "fact", "abs", "sinh", "cosh", "tanh", "ceil", "floor"
    ));

    private static void initializeConstants() {
        variables.put("PI", Math.PI);
        variables.put("E", Math.E);
        variables.put("G", 6.67430e-11);
    }

    private static void printHelp() {
        System.out.println("""
        === Bored Calculator -- Help ===
        1. Basic Arithmetic: Use +, -, *, /, and parentheses.
           Example: 3 + 5 * (2 - 1)
        2. Variables: Assign values with '=', and use them in calculations.
           Example: x = 5, y = 10, x + y * 2
        3. Built-in Constants: Use constants like PI, E, G (gravitational constant).
        4. Advanced Functions:
           - Trigonometry: sin(x), cos(x), tan(x)
           - Hyperbolic: sinh(x), cosh(x), tanh(x)
           - Logarithms: log(x) (base 10), ln(x) (natural log)
           - Factorials: fact(x)
           - Roots and Powers: sqrt(x), pow(x, y)
           - Miscellaneous: abs(x), ceil(x), floor(x)
        5. Equation Solver: Coming soon!
        6. Commands: Type 'help' for instructions, 'exit' to quit.
        """);
    }

    private static String replaceVariables(String expression) {
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            expression = expression.replaceAll("\\b" + Pattern.quote(entry.getKey()) + "\\b", entry.getValue().toString());
        }
        return expression;
    }

    private static BigInteger factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial not defined for negative numbers.");
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    private static double parseExpression(String expression) {
        try {
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < expression.length()) throw new RuntimeException("Unexpected - " + (char) ch);
                    return x;
                }

                double parseExpression() {
                    double x = parseTerm();
                    while (true) {
                        if (eat('+')) x += parseTerm();
                        else if (eat('-')) x -= parseTerm();
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    while (true) {
                        if (eat('*')) x *= parseFactor();
                        else if (eat('/')) x /= parseFactor();
                        else return x;
                    }
                }

                double parseFactor() {
                    if (eat('+')) return parseFactor();
                    if (eat('-')) return -parseFactor();

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')'");
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(expression.substring(startPos, this.pos));
                    } else if (ch >= 'a' && ch <= 'z') {
                        while (ch >= 'a' && ch <= 'z') nextChar();
                        String func = expression.substring(startPos, this.pos);
                        if (!supportedFunctions.contains(func) && !variables.containsKey(func)) {
                            throw new RuntimeException("Unknown function or variable - " + func);
                        }
                        x = variables.containsKey(func) ? variables.get(func) : parseFactor();
                        x = switch (func) {
                            case "sqrt" -> Math.sqrt(x);
                            case "sin" -> Math.sin(Math.toRadians(x));
                            case "cos" -> Math.cos(Math.toRadians(x));
                            case "tan" -> Math.tan(Math.toRadians(x));
                            case "sinh" -> Math.sinh(x);
                            case "cosh" -> Math.cosh(x);
                            case "tanh" -> Math.tanh(x);
                            case "log" -> Math.log10(x);
                            case "ln" -> Math.log(x);
                            case "fact" -> {
                                if (x < 0 || x != Math.floor(x)) throw new IllegalArgumentException("Factorial is only defined for non-negative integers.");
                                BigInteger factResult = factorial((int) x);
                                System.out.println("Factorial Result > " + factResult);
                                yield factResult.doubleValue();
                            }
                            case "abs" -> Math.abs(x);
                            case "ceil" -> Math.ceil(x);
                            case "floor" -> Math.floor(x);
                            default -> x;
                        };

                    } else {
                        throw new RuntimeException("Unexpected - " + (char) ch);
                    }

                    return x;
                }
            }.parse();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid expression - " + expression);
        }
    }

    private static double evaluateExpression(String expression) {
        expression = replaceVariables(expression);
        return parseExpression(expression);
    }

    private static void assignVariable(String input) {
        String[] parts = input.split("=", 2);
        if (parts.length != 2) throw new IllegalArgumentException("Invalid assignment syntax.");
        String variableName = parts[0].trim();
        if (!variableName.matches("[a-zA-Z]+")) throw new IllegalArgumentException("Invalid variable name.");
        double value = evaluateExpression(parts[1].trim());
        variables.put(variableName, value);
        System.out.println("Assigned - " + variableName + " = " + value);
    }

    public static void main(String[] args) {
        initializeConstants();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bored Calculator - The same Haskell calculator, but even worse (somehow)");
        System.out.println("Type 'help' for instructions or 'exit' to quit.");

        while (true) {
            System.out.print("\n>> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            if (input.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }

            try {
                if (input.contains("=")) {
                    assignVariable(input);
                } else {
                    double result = evaluateExpression(input);
                    System.out.println("Result > " + result);
                }
            } catch (Exception e) {
                System.out.println("Error - " + e.getMessage());
            }
        }
    }
}
