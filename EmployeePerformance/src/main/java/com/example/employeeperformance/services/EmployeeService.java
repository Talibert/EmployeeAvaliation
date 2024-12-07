package com.example.employeeperformance.services;

import com.example.employeeperformance.VOs.EmployeeVO;
import com.example.employeeperformance.entities.Employee;
import com.example.employeeperformance.exceptions.invalid.InvalidAttributeException;
import com.example.employeeperformance.exceptions.notfound.EmployeeNotFoundException;
import com.example.employeeperformance.exceptions.EmployeeSetorAlreadySettedException;
import com.example.employeeperformance.mappers.EmployeeVoMapper;
import com.example.employeeperformance.repositories.EmployeeRepository;
import com.example.employeeperformance.types.SetorType;
import com.example.employeeperformance.types.SituationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeVoMapper employeeVoMapper;

    /**
     * Método de busca de funcionários pelo id
     * @param id
     * @return
     */
    public Employee findById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent())
            return employee.get();

        throw new EmployeeNotFoundException("O funcionário não foi encontrado");
    }

    /**
     * Método de retorno completo
     * @return
     */
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    /**
     * Método que irá retornar uma lista de funcionários ativos
     * @return
     */
    public List<Employee> findAllActive(){
        return employeeRepository.findBySituationType(SituationType.ATIVO);
    }

    /**
     * Método que irá retornar uma lista de funcionários inativos
     * @return
     */
    public List<Employee> findAllInactive(){
        return employeeRepository.findBySituationType(SituationType.INATIVO);
    }

    /**
     * Método para retorno de funcionário ativos por setor
     * @param setorType
     * @return
     */
    public List<Employee> findBySetorTypeActive(SetorType setorType){
        return employeeRepository.findBySetorTypeAndSituationType(setorType, SituationType.ATIVO);
    }

    /**
     * Método para retorno de funcionário inativos por setor
     * @param setorType
     * @return
     */
    public List<Employee> findBySetorTypeInactive(SetorType setorType){
        return employeeRepository.findBySetorTypeAndSituationType(setorType, SituationType.ATIVO);
    }

    /**
     * Esse metodo vai converter o VO recebido para criar um novo funcionário
     * @param employeeVO
     * @return
     */
    public EmployeeVO createEmployee(EmployeeVO employeeVO){
        Employee employee = employeeVoMapper.getEntityToCreate(employeeVO);

        return employeeVoMapper.getVO(saveEmployee(employee));
    }

    /**
     * Esse metodo vai recuperar o funcionário antigo, realizar a atualização dos atributos com base no VO recebido e chamar o método de save
     * @param employeeVO
     * @return
     */
    public EmployeeVO updateEmployee(EmployeeVO employeeVO){
        Employee oldEmployee = findById(employeeVO.getId());

        Employee newEmployee = employeeVoMapper.getEntityToUpdate(employeeVO, oldEmployee);

        return employeeVoMapper.getVO(saveEmployee(newEmployee));
    }

    /**
     * Metodo que valida as informações do usuário antes de salvar
     * @param employeeVO
     */
    public void validatesEmployeeAttributes(EmployeeVO employeeVO){
        String message = null;

        if(employeeVO.getCpf() == null)
            message = "cpf";

        if(employeeVO.getNome() == null)
            message = "nome";

        if(employeeVO.getObservacao() == null)
            message = "observação";

        if(message != null)
            throw new InvalidAttributeException("O atributo " + message + " está inválido!");
    }

    /**
     * Metodo que chama o save do repository
     * @param employee
     * @return
     */
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    /**
     * Método que muda a situação do funcionário (pode ser ATIVA ou INATIVA)
     * @param id
     */
    public void toogleEmployeeSituation(Long id){
        Employee employee = findById(id);

        if(employee.getSituationType().equals(SituationType.ATIVO)){
            employee.setSituationType(SituationType.INATIVO);
            saveEmployee(employee);
        } else {
            employee.setSituationType(SituationType.ATIVO);
            saveEmployee(employee);
        }
    }

    /**
     * Metodo utilizado para mudar o SetorType do funcionário
     * @param id
     * @param setorType
     */
    public void changeEmployeeSetorType(Long id, SetorType setorType){
        Employee employee = findById(id);

        if(!employee.getSetorType().equals(setorType)){
            employee.setSetorType(setorType);
            saveEmployee(employee);
        } else {
            throw new EmployeeSetorAlreadySettedException("O funcionário já possui a função informada!");
        }
    }
}
