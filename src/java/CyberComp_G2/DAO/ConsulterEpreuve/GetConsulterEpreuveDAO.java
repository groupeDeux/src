/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CyberComp_G2.DAO.ConsulterEpreuve;

import CyberComp_G2.DAO.ConsituerEquipe.GetConsulterEquipeDAO;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author vivi, oprisora
 */
public class GetConsulterEpreuveDAO {

    /*
     Requête qui retourne la liste des épreuves individuelles avec les informations
     suivante : ...
     */
    public static final String lesEpreuvesInv
            = "SELECT  idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbDePlaceAchetées FROM viewEpreuve JOIN "
            + "lesEpreuvesIndividuelles USING (idEpreuve) "
            + "order by nomDiscipline";

    /*
     /!\ Dans cette requete on spécifie le nom de la discipline
     Requête qui retourne la liste des épreuves individuelles correspondant à un nom
     de discipline avec un avec les informations
     suivante : ...
     */
    public static final String lesEpreuvesParDisciplineInv
            = "SELECT  idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbDePlaceAchetées FROM viewEpreuve JOIN "
            + "lesEpreuvesIndividuelles USING (idEpreuve) where (nomDiscipline = '%s') "
            + "order by nomEpreuve";

    /*
     Requête qui retourne la liste des épreuves par equipe avec les informations
     suivante :...
    
     */
    public static final String lesEpreuvesEquipe
            = "SELECT idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbPersonneFixe,0 FROM viewEpreuve"
            + " JOIN lesEpreuvesParEquipe USING (idEpreuve)"
            + " order by nomDiscipline";
    
    /*
    /!\ Dans cette requete on spécifie le nom de la discipline
    Requête qui retourne la liste des épreuves par équipe correspondant à une 
    discipline spécifique.
    */
    public static final String lesEpreuvesParDisciplineEquipe
            = "SELECT idEpreuve,nomDiscipline,nomEpreuve,"
            + "to_char(dateDebut,'DD-MM-YYYY HH24'),"
            + "to_char(dateFin,'DD-MM-YYYY HH24'),urlVideo,tarif,nbDePlace, "
            + "categorie,nbPersonneFixe,0 FROM viewEpreuve "
            + "JOIN lesEpreuvesParEquipe USING (idEpreuve) where (nomDiscipline = '%s') "
            + "order by nomEpreuve";

    /*
     Requete qui retourne la liste des disciplines
     */
    public static final String lesDisciplines
            = "SELECT nomDiscipline FROM viewEpreuve GROUP BY (nomDiscipline)";

    /*
     Requete qui retourne la liste des medailles associé à chaque sportif.
     */
    public static final String lesMedaillesDesSportifs
            = "SELECT nomEpreuve, nom, prenom, pays, valeur FROM LesMedailles "
            + "natural join LesParticipants P join LesSportifs S "
            + "ON (P.idParticipant=S.idSportif) WHERE idEpreuve = % "
            + "ORDER BY valeur asc ";

    /*
     Requete qui retourne la liste des medailles par equipes.
     */
    public static final String lesMedaillesDesEquipes
            = "SELECT nomEquipe, categorie, valeur FROM LesMedailles M "
            + "join LesEquipes E ON (M.idParticipant=E.idEquipe) "
            + "WHERE idEquipe = %d "
            + "ORDER BY valeur asc ";

    /*
    
     */
    public static CachedRowSet getEpreuvesParDisciplineInv(String nomDiscipline) throws SQLException {
        return getConsulterEpreuveParDiscipline(lesEpreuvesParDisciplineInv, nomDiscipline);
    }

    public static CachedRowSet getEpreuvesParDisciplineEquipe(String nomDiscipline) throws SQLException {
        return getConsulterEpreuveParDiscipline(lesEpreuvesParDisciplineEquipe, nomDiscipline);
    }

    public static CachedRowSet getEpreuvesInv() throws SQLException {
        return getConsulterEpreuve(lesEpreuvesInv);
    }

    public static CachedRowSet getEpreuvesEquipe() throws SQLException {
        return getConsulterEpreuve(lesEpreuvesEquipe);
    }

    public static CachedRowSet getDisciplines() throws SQLException {
        return getConsulterEpreuve(lesDisciplines);
    }

    public static CachedRowSet getMedaillesDesSportifs(int idEpreuve)
            throws SQLException {
        return getConsulterMedailleParEpreuve(lesMedaillesDesSportifs, idEpreuve);
    }

    public static CachedRowSet getMedaillesDesEquipes(int idEquipe)
            throws SQLException {
        return getConsulterMedailleParEpreuve(lesMedaillesDesEquipes, idEquipe);
    }

    private static CachedRowSet getConsulterEpreuve(String query)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query));
        crs.execute();
        return crs;
    }

    private static CachedRowSet getConsulterMedailleParEpreuve(String query,
            int selecteur)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, selecteur));
        crs.execute();
        return crs;
    }

    private static CachedRowSet getConsulterEpreuveParDiscipline(String query, String nomDiscipline)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName("java:comp/env/jdbc/BDCyberCompetition");
        crs.setCommand(String.format(query, nomDiscipline));
        crs.execute();
        return crs;
    }
}
