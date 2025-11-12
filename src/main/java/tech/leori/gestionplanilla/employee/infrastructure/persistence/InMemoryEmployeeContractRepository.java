package tech.leori.gestionplanilla.employee.infrastructure.persistence;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import tech.leori.gestionplanilla.employee.domain.EmployeeArea;
import tech.leori.gestionplanilla.employee.domain.EmployeeContract;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;
import tech.leori.gestionplanilla.employee.domain.exception.EmployeeContractNotFoundException;

public class InMemoryEmployeeContractRepository implements EmployeeContractRepository {

  private final Map<UUID, EmployeeContract> storage = new ConcurrentHashMap<>();
  private final Map<UUID, EmployeeArea> contractAreas = new ConcurrentHashMap<>();

  @Override
  public EmployeeContract save(EmployeeContract employeeContract) {
    storage.put(employeeContract.getContractId(), employeeContract);
    return employeeContract;
  }

  @Override
  public Optional<EmployeeContract> findById(UUID contractId) {
    return Optional.ofNullable(storage.get(contractId));
  }

  @Override
  public void addArea(EmployeeArea employeeArea) {
    Objects.requireNonNull(employeeArea, "employeeArea must not be null");

    UUID contractId =
        Objects.requireNonNull(employeeArea.getContractId(), "contractId must not be null");

    if (!storage.containsKey(contractId)) {
      throw new EmployeeContractNotFoundException(
          "Employee contract with id " + contractId + " was not found");
    }

    contractAreas.put(contractId, employeeArea);
  }
}
