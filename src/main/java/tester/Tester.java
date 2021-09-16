package tester;

import entity.Employee;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();

            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions

            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries


            //SELECT e FROM Employee e WHERE e.id = 1234
            TypedQuery<Employee> q1 = em.createQuery("SELECT e from Employee e WHERE e.salary > 100000 ", Employee.class);
            List<Employee> employeeList = q1.getResultList();
            for (Employee e : employeeList) {
                System.out.println(e.getFirstName());
                System.out.println(e.getSalary());
            }
            //print get name + salary

            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            System.out.println("Query 2");
            TypedQuery<Employee> q2 = em.createQuery("select e from Employee e WHERE e.id = 'klo999'", Employee.class);
            Employee employee = q2.getSingleResult();
            System.out.println(employee.getFirstName());


            //3) Create a query to fetch the highest salary and print the value
            System.out.println("Query 3");
            TypedQuery<BigDecimal> q3 = em.createQuery("SELECT MAX(e.salary) FROM Employee e", BigDecimal.class);
            BigDecimal big = q3.getSingleResult();
            System.out.println(big);

            //4) Create a query to fetch the firstName of all Employees and print the names

            System.out.println("Query 4");
            TypedQuery<Employee> q4 = em.createQuery("SELECT e.firstName FROM Employee e ", Employee.class);
            List<Employee> ee = q4.getResultList();
            System.out.println(ee);

            //5 Create a query to calculate the number of employees and print the number

            System.out.println("Query 5");
            TypedQuery<Long> q5 = em.createQuery("SELECT count(employee.id) FROM Employee employee", Long.class);
            Long numberOfEmployee = q5.getSingleResult();
            System.out.println("This is the number of employees: " + numberOfEmployee);


            //6 Create a query to fetch the Employee with the higest salary and print all his details
            System.out.println("Query 6");
            TypedQuery<Employee> q6 = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary)FROM Employee e)", Employee.class);
            Employee employeeWithHighestSalary = q6.getSingleResult();
            System.out.println(employeeWithHighestSalary);

            System.out.println("The id of the employee with the highest salary " + employeeWithHighestSalary.getId());
            System.out.println("The first name of the employee with the highest salary " + employeeWithHighestSalary.getFirstName());
            System.out.println("The last name of the employee with the highest salary "+ employeeWithHighestSalary.getLastName());
            System.out.println("The salary of the employee with the highest salary " +employeeWithHighestSalary.getSalary());


        } finally {
            em.close();
            emf.close();
        }
    }

}
