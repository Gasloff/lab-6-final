package com.luxoft.springcore.travel;

import com.luxoft.springcore.objects.City;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class TravellingOpportunities {
	
	private List<Connection> connections;

	public List<Connection> getConnectionsList() {
		return Collections.unmodifiableList(connections);
	}

	public void setConnectionsList(List<Connection> connections) {
		this.connections = connections;
	}

	public int getDistance(City source, City destination) {
		return connections.stream()
				.filter(connection -> Objects.equals(connection.getSource(), source))
				.filter(connection -> Objects.equals(connection.getDestination(), destination))
				.map(Connection::getDistance)
				.findFirst()
				.orElse(0);
	}

}
