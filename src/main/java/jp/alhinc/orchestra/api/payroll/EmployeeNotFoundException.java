package jp.alhinc.orchestra.api.payroll;

public class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id) {
        super("Could not finde employee " + id);
    }
}