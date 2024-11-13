package com.example.employeeperformance.services;

import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.exceptions.notfound.ChangesRegistryNotFoundException;
import com.example.employeeperformance.exceptions.notfound.EmployeeNotFoundException;
import com.example.employeeperformance.exceptions.notfound.EmployeeSituationAlreadySetted;
import com.example.employeeperformance.repositories.ChangesRegistryRepository;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChangesRegistryService {

    @Autowired
    private ChangesRegistryRepository changesRegistryRepository;

    /**
     * Método de busca de registros de mudança pelo id
     * @param id
     * @return
     */
    public ChangesRegistry findById(Long id){
        Optional<ChangesRegistry> changesRegistry = changesRegistryRepository.findById(id);

        if(changesRegistry.isPresent())
            return changesRegistry.get();

        throw new ChangesRegistryNotFoundException("O registro de mudança não foi encontrado");
    }

    /**
     * Método de retorno completo
     * @return
     */
    public List<ChangesRegistry> findAll(){
        return changesRegistryRepository.findAll();
    }

    /**
     * Método que irá retornar uma lista de registros de mundanças por funcionário
     * @return
     */
    public List<ChangesRegistry> findByEmployee(Employee employee){
        return changesRegistryRepository.findByEmployee(employee);
    }

    /**
     * Método que irá retornar uma lista de registros de mudanças por performance de funcionário
     * @return
     */
    public List<ChangesRegistry> findByEmployeePerformance(EmployeePerformance employeePerformance){
        return changesRegistryRepository.findByEmployeePerformance(employeePerformance);
    }

    /**
     * Método que irá retornar uma lista de registros de mudançás por performance de funcionário
     * @return
     */
    public List<ChangesRegistry> findByDate(LocalDate date){
        return changesRegistryRepository.findByData(date);
    }

    /**
     * Método para retorno de funcionário inativos por setor
     * @return
     */
    public ChangesRegistry findLastChangesRegistryByEmployee(Employee employee){
        return changesRegistryRepository.findLastChangesRegistryByEmployee(employee);
    }
}
