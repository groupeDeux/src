package CyberComp_G2.listener;

import CyberComp_G2.utils.ConnexionBD;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * Sert à initialiser le datasource au démarrage de l'application afin d'éviter de le faire pour chaque requête
 * @author oprisora
 */
public class DataSourceInitializer implements ServletContextListener {
    @Resource (name="jdbc/BDCyberCompettion")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnexionBD.INSTANCE.setDataSource(dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}