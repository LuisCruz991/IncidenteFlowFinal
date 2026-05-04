package com.incidentflow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "credenciales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credencial {

    @Id
    @Column(name = "usuario_id")
    private Long usuarioId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "mfa_secret")
    private String mfaSecret;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;
}