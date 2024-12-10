package edu.lawrence.adventure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.adventure.entities.*;

public interface InventoryRepository extends JpaRepository<Inventory,UUID>{
	Inventory findByPlayer(Player player);

}
