# BoredCalculatorJava
The same scuffed Haskell Calculator, but with Java (and more scuffed)

---

## DISCLAIMER

I do not know how many problems this code can cause on your system, especially if you are not careful with whatever you are doing. Please, be cautious while editing the code, distributing it, or making any changes since my code is **scuffed and over-engineered**.

---

## Known Issues
- **Factorial Result Display**: Large factorials may display in an awkward way. Fix is applied, but results might still look "off" in some cases. Improvements planned for the next release.
- **Missing Parentheses Handling**: Some functions (like `sin(x)`) may not gracefully handle malformed input. Planned with next release.

---

## Features

- **Basic Arithmetic**: Supports `+`, `-`, `*`, `/`, and parentheses.
- **Variable Assignment**: Assign values to variables (e.g., `x = 10`) and use them in expressions.
- **Advanced Mathematical Functions**:
  - **Trigonometric**: `sin(x)`, `cos(x)`, `tan(x)`
  - **Hyperbolic**: `sinh(x)`, `cosh(x)`, `tanh(x)`
  - **Logarithmic**: `log(x)` (base 10), `ln(x)` (natural log)
  - **Factorials**: `fact(x)` supports large integers without losing precision
  - **Miscellaneous**: `abs(x)`, `ceil(x)`, `floor(x)`, `sqrt(x)`
- **Built-in Constants**: Predefined constants like `PI`, `E`, and `G` (gravitational constant).
- **Command Support**:
  - `help`: Display available commands and examples.
  - `exit`: Exit the calculator.

---

## Compiling Guide

Follow these instructions to compile and run the calculator on your system.

### Prerequisites

1. **Java Development Kit (JDK)**:
   - [Download and install JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
   - Ensure the `javac` and `java` commands are available in your system's PATH.

2. **Git**:
   - [Download and install Git](https://git-scm.com/downloads).

---

### Cloning the Repository

1. Open a terminal or command prompt.
2. Clone the repository using Git:
   ```bash
   git clone https://github.com/BoredTurkishGuy/BoredCalculatorJava.git
   cd BoredCalculatorJava
   ```

---

### Compiling the Code

1. Navigate to the directory where the `BoredCalculator.java` file is located.
2. Compile the code using `javac`:
   ```bash
   javac BoredCalculator.java
   ```

---

### Running the Calculator

1. Run the compiled program using `java`:
   ```bash
   java BoredCalculator
   ```

---

### Common Issues

1. **Java Version**:
   - Ensure you are using Java 8 or newer. Use the following command to check your version:
     ```bash
     java -version
     ```

2. **Compilation Errors**:
   - If you encounter errors, ensure you are in the correct directory and the file name matches exactly (`BoredCalculator.java`).

3. **PATH Issues**:
   - If `javac` or `java` is not recognized, ensure your JDK is correctly installed and added to the PATH.

---

## Examples

```text
>> x = 5
Assigned - x = 5.0

>> fact(x)
Factorial Result > 120
Result > 120.0

>> sin(30)
Result > 0.5

>> y = 10
Assigned - y = 10.0

>> x + y * 2
Result > 25.0
```

---

## Notes

1. **Editing the Code**:
   - If you modify the code, you’ll need to recompile it using the steps above.

2. **Platform Compatibility**:
   - The program is tested on Windows but should work on Linux and macOS as long as the JDK is correctly installed.

3. **Future Plans**:
   - Implement an equation solver.
   - Improve handling of malformed input and error messages.

---

## Contributing

Feel free to fork this repository, make changes, and submit a pull request. Contributions are welcome, but please test your changes thoroughly to maintain the calculator’s stability.

---

## License

This project is licensed under the [Apache 2.0 LICENSE](https://github.com/BoredTurkishGuy/BoredCalculatorJava/blob/main/LICENSE). See the LICENSE file for details.
