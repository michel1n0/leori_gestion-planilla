package tech.leori.gestionplanilla.employee.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import tech.leori.gestionplanilla.employee.domain.Employee;
import tech.leori.gestionplanilla.employee.domain.EmployeeRepository;

public class InMemoryEmployeeRepository implements EmployeeRepository {

  private final Map<UUID, Employee> storage = new ConcurrentHashMap<>();

  @Override
  public Employee save(Employee employee) {
    storage.put(employee.getId(), employee);
    return employee;
  }

  @Override
  public Optional<Employee> findById(UUID id) {
    return Optional.ofNullable(storage.get(id));
  }
}
