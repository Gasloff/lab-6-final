package com.luxoft.springcore.events;

import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.Country;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TravelEventListener {

    @EventListener
    public void arrivalToDestination(TravelEvent travelEvent) {
        String personName = travelEvent.getPerson().getName();
        City city = travelEvent.getTravellingDestination();
        Country country = city.getCountry();
        System.out.printf("%s has arrived to %s, %s (%s)%n",
                personName, city.getName(), country.getName(), country.getCodeName());
    }

}
