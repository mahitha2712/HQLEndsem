package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jakarta.persistence.Query;

public class DepartmentManager {

    // Singleton SessionFactory for performance improvement
    private static SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Department.class)
            .buildSessionFactory();

    // Method to update department details
    public String updateDepartment(int deptId, String newName, String newLocation) throws Exception {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // HQL query to update department details
        String hql = "UPDATE Department d SET d.name = :name, d.location = :location WHERE d.deptId = :deptId";
        Query query = session.createQuery(hql);
        query.setParameter("name", newName);
        query.setParameter("location", newLocation);
        query.setParameter("deptId", deptId);

        // Execute the update query
        int rowsAffected = query.executeUpdate();

        // Commit transaction
        session.getTransaction().commit();

        // Close session
        session.close();

        // Return the result
        return rowsAffected > 0 ? "Update successful" : "No records updated";
    }

    // Static method to close SessionFactory when application is shutting down
    public static void shutdown() {
        sessionFactory.close();
    }
}
