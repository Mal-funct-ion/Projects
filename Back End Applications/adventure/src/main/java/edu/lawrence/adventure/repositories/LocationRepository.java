package edu.lawrence.adventure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.adventure.entities.Location;

public interface LocationRepository extends JpaRepository<Location,String> {
	Optional<Location> findByLocationName(String locationName);
	Location findByLocationid(String id);

}
