package com.chagu.bookinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookInfoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookInfoServiceApplication.class, args);
    }

	/*@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Employee emp1 = new Employee("Ram", "Gurram");
			Employee emp2 = new Employee("Sai", "Nidamanuri");
			Employee emp3 = new Employee("Siva", "Ponnam");
			Employee emp4 = new Employee("Lahari", "Gurram");

			employeeRepo.save(emp1);
			employeeRepo.save(emp2);
			employeeRepo.save(emp3);
			employeeRepo.save(emp4);

			List<Employee> emps = employeeRepo.all();

			System.out.println("-------------------------");
			System.out.println("Printing Employees");
			System.out.println("-------------------------");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

		};
	}*/

}
