package com.rental.car.client;

import com.rental.car.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
