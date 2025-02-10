package com.example.employeeperformance.services;

import com.example.employeeperformance.entities.ChangesRegistry;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.entities.EmployeePerformance;
import com.example.employeeperformance.exceptions.notfound.ChangesRegistryNotFoundException;
import com.example.employeeperformance.mappers.ChangesRegistryVoMapper;
import com.example.employeeperformance.repositories.ChangesRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChangesRegistryService {

    @Autowired
    private ChangesRegistryRepository changesRegistryRepository;

    @Autowired
    private ChangesRegistryVoMapper changesRegistryVoMapper;

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
    public ChangesRegistry findByEmployeePerformance(EmployeePerformance employeePerformance){
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
     * Método para retorno do ultimo ChangesRegistry do funcionário
     * @return
     */
    public ChangesRegistry findLastChangesRegistryByEmployee(Employee employee){
        return changesRegistryRepository.findLastChangesRegistryByEmployee(employee);
    }

    /**
     * Método para salvar um novo registro do ChangesRegistry
     * @param changesRegistry
     */
    public ChangesRegistry saveChangesRegistry(ChangesRegistry changesRegistry){
        return changesRegistryRepository.save(changesRegistry);
    }
}
