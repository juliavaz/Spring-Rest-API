package com.example.payroll.demo.Api;

import com.example.payroll.demo.Exception.EmployeeNotFoundException;
import com.example.payroll.demo.Model.Employee;
import com.example.payroll.demo.Repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // recuperar informação da url
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    // inserir um employee
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // itens individuais

    // recuperar informação de um employee específico, /employees/{tal}
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // alterar informações de um employee específico
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        // caso encontre o id pega o valor de name e role e atualiza salvando os dados do emplooye,
        // senão ele cria um novo employee.
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee);
        })
        .orElseGet(() -> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    // método sem retorno que executa a ação de apagar o employee específico
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
