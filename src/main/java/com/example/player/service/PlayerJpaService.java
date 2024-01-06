package com.example.player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.player.repository.PlayerJpaRepository;
import com.example.player.repository.PlayerRepository;
import com.example.player.model.Player;

@Service
public class PlayerJpaService implements PlayerRepository {
	@Autowired
	private PlayerJpaRepository playerJpaRepository;

	@Override
	public ArrayList<Player> getPlayers() {
		List<Player> playersList = playerJpaRepository.findAll();
		ArrayList<Player> players = new ArrayList<>(playersList);
		return players;
	}

	@Override
	public Player getPlayerById(int playerId) {
		try {
			return playerJpaRepository.findById(playerId).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Player addPlayer(Player player) {
		playerJpaRepository.save(player);
		return player;
	}

	@Override
	public void deletePlayer(int playerId) {
		try {
			playerJpaRepository.deleteById(playerId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Player updatePlayer(int playerId, Player player) {
		try {
			Player newPlayer = playerJpaRepository.findById(playerId).get();
			if (player.getPlayerName() != null) {
				newPlayer.setPlayerName(player.getPlayerName());
			}
			if (player.getJerseyNumber() != 0) {
				newPlayer.setJerseyNumber(player.getJerseyNumber());
			}
			if (player.getPlayerName() != null) {
				newPlayer.setRole(player.getRole());
			}
			return newPlayer;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}