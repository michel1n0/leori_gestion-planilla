package tech.leori.gestionplanilla.employee.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import tech.leori.gestionplanilla.employee.domain.exception.EmployeeDomainException;

public final class EmployeeContractFactory {

  private static final String DEFAULT_CONTRACT_TYPE = "INDEFINITE";

  private EmployeeContractFactory() {
  }

  public static EmployeeContract createContract(Employee employee, LocalDate startDate, BigDecimal salary) {
    Employee validatedEmployee = validateEmployee(employee);
    LocalDate validatedStartDate = validateStartDate(startDate);
    BigDecimal validatedSalary = validateSalary(salary);

    return EmployeeContract.builder()
        .withEmployeeId(validatedEmployee.getId())
        .withStartDate(validatedStartDate)
        .withSalary(validatedSalary)
        .withContractType(DEFAULT_CONTRACT_TYPE)
        .build();
  }

  private static Employee validateEmployee(Employee employee) {
    if (employee == null) {
      throw new EmployeeDomainException("Employee must not be null");
    }
    return employee;
  }

  private static LocalDate validateStartDate(LocalDate startDate) {
    if (startDate == null) {
      throw new EmployeeDomainException("Start date must not be null");
    }
    if (startDate.isAfter(LocalDate.now())) {
      throw new EmployeeDomainException("Start date must not be in the future");
    }
    return startDate;
  }

  private static BigDecimal validateSalary(BigDecimal salary) {
    if (salary == null) {
      throw new EmployeeDomainException("Salary must not be null");
    }
    if (salary.compareTo(BigDecimal.ZERO) <= 0) {
      throw new EmployeeDomainException("Salary must be greater than zero");
    }
    return salary;
  }
}
