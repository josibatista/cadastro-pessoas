/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cp.data.crud.interfaces;

import com.cp.util.AppLog;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author utfpr
 */
public class EMNames implements Serializable {

    public static final String EMN1 = "default";

    //Substua número mágino
    //Técnica de Substituir número mágico por constante simbólica
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String LOCAL_URL = "jdbc:postgresql://localhost:5432/localdb";
    private static final String LOCAL_USER = "postgres";
    private static final String LOCAL_PASSWORD = "postgres";

    //Chamadas do método simplificador
    //Renomear método: getEMN1Props() -> getEntityManagerProperties()
    public static Map<String, String> getEntityManagerProperties()  {

        //compondo métodos
        //Variável temporária dividida
        String databaseUrl = System.getenv("DATABASE_URL");
        Map<String, String> properties = new HashMap<>();

        if (databaseUrl == null) {
            configureLocalDatabase(properties);
        } else {
            configureProductionDatabase(properties, databaseUrl);
        }
        return properties;
    }
    
    //compondo métodos
    //extrair método
    private static void configureLocalDatabase(Map<String, String> properties) {
        AppLog.getInstance().info("Configurar banco de dados para acesso local");
        properties.put("jakarta.persistence.jdbc.url", LOCAL_URL);
        properties.put("jakarta.persistence.jdbc.user", LOCAL_USER);
        properties.put("jakarta.persistence.jdbc.driver", JDBC_DRIVER);
        properties.put("jakarta.persistence.jdbc.password", LOCAL_PASSWORD);
    }

    //extrair método
    private static void configureProductionDatabase(Map<String, String> properties, String databaseUrl) {
        AppLog.getInstance().info("#### Configurar banco de dados em produção ####");
    
        //Variável temporária dividida
        String username = System.getenv("DATABASE_USERNAME");
        String password = System.getenv("DATABASE_PASSWORD");

        AppLog.getInstance().info("Variáveis carregadas: " + username + ":" + password);

        properties.put("jakarta.persistence.jdbc.user", username);
        properties.put("jakarta.persistence.jdbc.driver", JDBC_DRIVER);
        properties.put("jakarta.persistence.jdbc.password", password);
        properties.put("jakarta.persistence.jdbc.url", databaseUrl);

        try {
            Class.forName(JDBC_DRIVER);
            AppLog.getInstance().info("Driver JDBC carregado com sucesso");
        } catch (Exception e) {
            AppLog.getInstance().warn("Erro ao carregar driver JDBC: " + e.getMessage());
        }
    }
}
