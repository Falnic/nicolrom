package com.nicolrom.dao.impl;

import com.nicolrom.dao.EmployeeDao;
import com.nicolrom.entities.Employee;
import com.nicolrom.enums.EmployeePositionEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Queue;

@Repository("employeeDao")
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Employee as E").list();
    }

    @Override
    public List<Employee> getEmployeesByPosition(EmployeePositionEnum positionEnum) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Employee as E where E.position = :positionEnum");
        query.setParameter("positionEnum", positionEnum);
        return query.list();
    }

    @Override
    public List<Employee> getEmployeesByPosition(List<EmployeePositionEnum> positionEnums) {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "FROM Employee as E where";
        for (int i = 1; i <= positionEnums.size(); i++) {
            if (i != positionEnums.size()){
                queryString += " E.position = :positionEnum" + i + " OR";
            } else {
                queryString += " E.position = :positionEnum" + i;
            }
        }

        Query query = session.createQuery(queryString);
        for (int i = 1; i <= positionEnums.size(); i++){
            String queryParam = "positionEnum" + i;
            query.setParameter(queryParam, positionEnums.get(i - 1));
        }
        return query.list();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Employee as E where E.id = :id");
        query.setParameter("id", id);
        return (Employee) query.getSingleResult();
    }


}
