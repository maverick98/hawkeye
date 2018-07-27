/*
 * This file is part of j-hawk
 * CopyLeft (C) BigBang<->BigCrunch Manoranjan Sahu, All Rights are left.
 *
 */
package org.cricket.hawkeye.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.cricket.hawkeye.dao.exception.DAOException;

/**
 *
 * @author manosahu
 */
//@Component("cricWebSiteDAO")
//@Qualifier("default")
public class CricDataDAOImplProxy extends DefaultCricDataDAOImpl {

    private static final Logger logger = Logger.getLogger(CricDataDAOImplProxy.class);

    
    private ICricDataDAO  cricDataProviderDAO;

    public ICricDataDAO getCricDataProviderDAO() {
        return cricDataProviderDAO;
    }

    public void setCricDataProviderDAO(ICricDataDAO cricDataProviderDAO) {
        this.cricDataProviderDAO = cricDataProviderDAO;
    }
    
    public boolean injectDependenciesInProviderDAO(){
        
        DefaultCricDataDAOImpl providerDAO = (DefaultCricDataDAOImpl)this.getCricDataProviderDAO();
        providerDAO.setCommonService(this.getCommonService());
        providerDAO.setFileService(this.getFileService());
        providerDAO.setStringService(this.getStringService());
        providerDAO.setUrlService(this.getUrlService());
        return true;
    }
    
    public boolean isProviderPresent(){
        return this.getCricDataProviderDAO() != null;
    }
    
   
    
    

    /**
     * This is the source of everything of hawkEye
     * @return
     * @throws org.cricket.hawkeye.dao.exception.DAOException
     */
    @Override
    public String findCountrysPath() throws DAOException{

       if(!this.isProviderPresent()){
           throw new DataProviderUnavailabilityException("Could not find any data provider");
       }  
       return this.getCricDataProviderDAO().findCountrysPath();
    }

    /**
     * This has to be an online operation
     * @return
     * @throws DAOException
     */
    @Override
    public String findCountrysHTML() throws DAOException {
     
       if(!this.isProviderPresent()){
           throw new DataProviderUnavailabilityException("Could not find any data provider");
       } 
       return this.getCricDataProviderDAO().findCountrysHTML();
    }

   

    /**
     * This parses the country name from the countriesHTML
     * @param countryName
     * @return
     * @throws DAOException
     */
    @Override
    public List<String> findCountryPath(String countryName) throws DAOException {
        if(!this.isProviderPresent()){
           throw new DataProviderUnavailabilityException("Could not find any data provider");
        }
        return this.getCricDataProviderDAO().findCountryPath(countryName);

    }

    @Override
    public String findCountryHTML(String countryName) throws DAOException {
        if(!this.isProviderPresent()){
           throw new DataProviderUnavailabilityException("Could not find any data provider");
        }

       return this.getCricDataProviderDAO().findCountryHTML(countryName);
    }

    @Override
    public String findPlayerPath(String countryName, String playerName) throws DAOException {
        if(!this.isProviderPresent()){
           throw new DataProviderUnavailabilityException("Could not find any data provider");
        }

       return this.getCricDataProviderDAO().findPlayerPath(countryName, playerName);

    }

    @Override
    public String findPlayerHTML(String countryName, String playerName) throws DAOException {
        if(!this.isProviderPresent()){
           throw new DataProviderUnavailabilityException("Could not find any data provider");
        }

       return this.getCricDataProviderDAO().findPlayerHTML(countryName, playerName);

    }

    @Override
    public DataPattern getPlayerPattern() throws DAOException{
        if(!this.isProviderPresent()){
           throw new DataProviderUnavailabilityException("Could not find any data provider");
        }

        return this.getCricDataProviderDAO().getPlayerPattern();
    }

    @Override
    public DataPattern getCountryPattern() throws DAOException{
        if(!this.isProviderPresent()){
           throw new DataProviderUnavailabilityException("Could not find any data provider");
        }

        return this.getCricDataProviderDAO().getCountryPattern();
    }

   
    
    
    
    


}
