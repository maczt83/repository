/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progmatic.bookingmanager.databaseEntity.PaymentGateway;
import progmatic.bookingmanager.repositories.PaymentGatewayRepository;

/**
 *
 * @author Anna
 */
@Service
public class PaymentGatewaySearchService {
    
    @Autowired
    PaymentGatewayRepository pgRepo;
    
    @PersistenceContext
    EntityManager em;
    
    private boolean firstCondition;
    
    private StringBuilder setStringBuilder(StringBuilder sb, Object parameter, String name){
        if (parameter != null){
            if(firstCondition){
                sb.append(" where ");
                firstCondition = false;
            }
            else{
                sb.append(" and ");
            }
            sb.append(" pg.").append(name).append("= :").append(name.replaceAll("\\.", "_")).append(" ");
        }
        return sb;
    }
    
    private Query setQueryParameter(Query query, Object parameter, String name) {
        if (parameter != null) {
            query.setParameter(name.replaceAll("\\.", "_"), parameter);
        }        
        return query;
    }
    
    public List<PaymentGateway> searchPaymentGatewayByParameters(Long bookingId, String linkname, String status, Date creationDate, Date lastUpdate) {
        LocalDate localDateCreation = creationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int creationYear  = localDateCreation.getYear();
        LocalDate localDateUpdate = lastUpdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int lastUpdateYear  = localDateUpdate.getYear();
        if(creationYear == -1){
            creationDate = null;
        }
        if(lastUpdateYear == -1){
            lastUpdate = null;
        }
        if(linkname == ""){
            linkname = null;
        }
        if(status == ""){
            status = null;
        }
        StringBuilder sb = new StringBuilder("select pg from PaymentGateway pg");
        firstCondition=true;
        sb = setStringBuilder(sb, bookingId, "bookingId.id");
        sb = setStringBuilder(sb, linkname, "paymentLinkName");
        sb = setStringBuilder(sb, status, "paymentLinkStatus");
        sb = setStringBuilder(sb, creationDate, "creationDate");
        sb = setStringBuilder(sb, lastUpdate, "lastUpdate");

        String sql = sb.toString();
        System.out.println(sql);
        Query query = em.createQuery(sql);
        
        query = setQueryParameter(query, bookingId, "bookingId.id");
        query = setQueryParameter(query, linkname, "paymentLinkName");
        query = setQueryParameter(query, status, "paymentLinkStatus");
        query = setQueryParameter(query, creationDate, "creationDate");
        query = setQueryParameter(query, lastUpdate, "lastUpdate");

        return query.getResultList();
    }
}
