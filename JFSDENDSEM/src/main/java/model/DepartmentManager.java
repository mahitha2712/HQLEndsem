package model;



import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

public class Controller {
    public static void main(String[] args) {
       
        Session session = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";

            
            Query query = session.createQuery(hql);

           
            query.setParameter(1, "Updated Department Name");
            query.setParameter(2, "Updated Location");
            query.setParameter(3, 1); // Update Department with ID 1

            
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

