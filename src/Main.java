import com.elvin.model.College;
import com.elvin.model.Department;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
        try {
            mainProgram();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void mainProgram() throws IOException
    {
        System.out.println("Choose one option ( 1 or 2 ):");
        System.out.println("1. Add college\n" + "2. Add department");
        int choice = scanner.nextInt();

        if(choice == 1)
        {
            System.out.println("Enter college name:");
            //String collegeName = scanner.next();
            String collegeName = bufferedReader.readLine();
            addCollege(collegeName);
            continueOrNot();
        }
        else if(choice == 2)
        {
            displayColleges();
            int collegeId = scanner.nextInt();
            System.out.println("Enter department name:");
            //String departmentName = scanner.next();
            String departmentName = bufferedReader.readLine();
            addDepartment(collegeId,departmentName);
            continueOrNot();
        }
        else
        {
            System.out.println("Provide correct data as input !!!");
            continueOrNot();
        }
    }

    private static void continueOrNot(){
        System.out.println("Do you want to continue? (Y/N)");
        char ch = scanner.next().charAt(0);
        if(ch == 'Y' || ch == 'y')
        {
            try {
                mainProgram();
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Program terminated");
        }
    }

    private static SessionFactory getSessionFactory()
    {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }

    private static void addCollege(String collegeName){

        // local sessionFactory
        SessionFactory localSessionFactory = getSessionFactory();
        Session session = localSessionFactory.openSession();
        session.beginTransaction();

        College college = new College(collegeName);
        session.save(college);
        session.getTransaction().commit();
        session.close();

    }

    private static void addDepartment(int collegeId, String departmentName)
    {
        SessionFactory localSessionFactory = getSessionFactory();
        Session session = localSessionFactory.openSession();
        session.beginTransaction();
        College college = session.get(College.class,collegeId);

        // ManyToOne
        Department department = new Department(departmentName, college);
        session.save(department);
        session.getTransaction().commit();
        session.close();
    }

    private static void displayColleges()
    {
        SessionFactory localSessionFactory = getSessionFactory();
        Session session = localSessionFactory.openSession();

        session.beginTransaction();
        ArrayList<College> arrayList = (ArrayList<College>) session.createCriteria(College.class).list();
        session.getTransaction().commit();
        session.close();

        System.out.println("Choose college to add department for:");
        for(int i = 0; i < arrayList.size(); i ++)
        {
            int collegeId = arrayList.get(i).getCollegeId();
            String collegeName = arrayList.get(i).getCollegeName();
            System.out.println(collegeId+" "+collegeName);
        }
    }


//    private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }
//
//    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }
//    }
}