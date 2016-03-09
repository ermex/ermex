/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ermex.atc.sesion;

import ermex.atc.entidad.Documentosnotas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FABY
 */
@Stateless
public class DocumentosnotasFacade extends AbstractFacade<Documentosnotas> {

    @PersistenceContext(unitName = "ermex")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentosnotasFacade() {
        super(Documentosnotas.class);
    }
    public void crerarDocumentoNota(String idnota, String path)
    {
       String sentencia =" insert into documentosnotas(notaword,idnota) values(?,?)";
       Query query=getEntityManager().createNativeQuery(sentencia, Documentosnotas.class);
        try {
            query.setParameter(1, path);
            query.setParameter(2, idnota);
            query.executeUpdate();
            getEntityManager().flush();
        } catch (Exception e) {
        }
    }
}
