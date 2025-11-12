package tech.leori.gestionplanilla.contracts.command;

import java.time.LocalDate;

public class CreateEmployeeContractCommand {

    private final Long employeeId;
    private final LocalDate contractStart;
    private final LocalDate contractEnd;
    private final String regime;

    public CreateEmployeeContractCommand(Long employeeId, LocalDate contractStart, LocalDate contractEnd, String regime) {
        this.employeeId = employeeId;
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
        this.regime = regime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDate getContractStart() {
        return contractStart;
    }

    public LocalDate getContractEnd() {
        return contractEnd;
    }

    public String getRegime() {
        return regime;
    }
}
