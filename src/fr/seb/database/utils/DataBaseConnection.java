/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.seb.database.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette Classe retourne une connection à une base de données MySQL Elle
 * implémente le pattern Singleton
 *
 * @author formation
 */
public class DataBaseConnection {

    /**
     * Varible destinée à stoker l'instance de la connection
     */
    private static Connection instance;

    /**
     * Constructeur privé pour éviter de pouvoir instancier la classe depuiis
     * l'extérieur
     */
    public static Connection getInstance() throws SQLException {

        FileInputStream fis = null;

        //Instanciation d'un objet Properties qui contiendra la configuration
        Properties config = new Properties();
        try {
            //ouverture du fichier qui contient les infos
            fis = new FileInputStream("./config/newproperties.properties");
            //Chargement des donnees du fichier dans l'objet Properties
            config.load(fis);
            fis.close();

            //Récuperation des informations de configuration dans des variables
            String dbHost = config.getProperty("db.host", "localhost");
            String dbName = config.getProperty("db.name", "bibliotheque");
            String dbUser = config.getProperty("db.user", "root");
            String dbPass = config.getProperty("dbpass", "");

            //Si l'instance est null on instancie une nouvelle connection
            if (instance == null) {
                instance = DriverManager.getConnection(
                        "jdbc:mysql://" + dbHost + "/" + dbName,
                        dbUser,
                        dbPass
                );

            }
        } catch (IOException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return instance;
    }

}
