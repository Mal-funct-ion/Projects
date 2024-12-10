package edu.lawrence.adventure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.adventure.entities.Player;

public interface PlayerRepository extends JpaRepository<Player,UUID> {
	Optional<Player> findByPlayerid(Player player);
}
