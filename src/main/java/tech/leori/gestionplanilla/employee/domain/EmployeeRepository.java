package tech.leori.gestionplanilla.employee.domain;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository {

  Employee save(Employee employee);

  Optional<Employee> findById(UUID id);
}
