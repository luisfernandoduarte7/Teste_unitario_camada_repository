package com.teste.hospede.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.hospede.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
