package com.luxoft.springcore;

import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.Country;
import com.luxoft.springcore.objects.UsualPerson;
import com.luxoft.springcore.travel.Connection;
import com.luxoft.springcore.travel.TravellingOpportunities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:locations.properties")
public class BeanConfig {

    private static int count = 0;

    @Autowired
    private Environment env;

    @Bean
    public UsualPerson russianPerson() {
        return new UsualPerson(getId(), env.getProperty("russian.name"), getIntProperty("russian.age"), moscow());
    }

    @Bean
    public UsualPerson bulgarianPerson() {
        return new UsualPerson(getId(), env.getProperty("bulgarian.name"), getIntProperty("bulgarian.age"), sofia());
    }

    @Bean
    public City moscow() {
        return new City(getId(), env.getProperty("moscow.name"), russia());
    }

    @Bean
    public City sofia() {
        return new City(getId(), env.getProperty("sofia.name"), bulgaria());
    }

    @Bean
    public City warsaw() {
        return new City(getId(), env.getProperty("warsaw.name"), poland());
    }

    @Bean
    public City krakow() {
        return new City(getId(), env.getProperty("krakow.name"), poland());
    }

    @Bean
    public City vienna() {
        return new City(getId(), env.getProperty("vienna.name"), austria());
    }

    @Bean
    public Country russia() {
        return new Country(getId(), env.getProperty("russia.name"), env.getProperty("russia.codeName"));
    }

    @Bean
    public Country bulgaria() {
        return new Country(getId(), env.getProperty("bulgaria.name"), env.getProperty("bulgaria.codeName"));
    }

    @Bean
    public Country poland() {
        return new Country(getId(), env.getProperty("poland.name"), env.getProperty("poland.codeName"));
    }

    @Bean
    public Country austria() {
        return new Country(getId(), env.getProperty("austria.name"), env.getProperty("austria.codeName"));
    }

    @Bean
    public TravellingOpportunities travellingOpportunities() {
        TravellingOpportunities opportunities = new TravellingOpportunities();

        Connection moscowToWarsaw = getConnection(moscow(), warsaw());
        Connection warsawToKrakow = getConnection(warsaw(), krakow());
        Connection sofiaToVienna = getConnection(sofia(), vienna());
        Connection viennaToKrakow = getConnection(vienna(), krakow());

        List<Connection> connections = Arrays.asList(moscowToWarsaw, warsawToKrakow, sofiaToVienna, viennaToKrakow);
        opportunities.setConnectionsList(connections);

        return opportunities;
    }

    private int getId() {
        return count++;
    }

    private Connection getConnection(City source, City destination) {
        String propName = source.getName().toLowerCase() + ".to." + destination.getName().toLowerCase();
        int distance = getIntProperty(propName);
        return new Connection(source, destination, distance);
    }

    private int getIntProperty(String s) {
        try {
            return Integer.parseInt(env.getProperty(s));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
