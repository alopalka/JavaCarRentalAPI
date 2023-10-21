package com.rental.car.client.model;

import com.rental.car.rental.model.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "clients")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "update clients set deleted = true where id = ?1 and version = ?2")
public class Client{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String pesel;

    @Column(unique = true)
    private String email;

    private boolean deleted;

    @Version
    private int version;

    @OneToMany(mappedBy = "client")
    @Builder.Default
    private Set<Rental> rentals = new HashSet<>();
}
