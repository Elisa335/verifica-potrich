package it.marconi.verificapotrich.domains;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity     // specifico che la classe è entità del database
@Table(name = "films")   // associo la classe a una tabella del db
public class Film {

    @Id     // primary key
    @GeneratedValue //generata in automatico
    @Column(name = "codice")
    private UUID codice;

    @Column(name = "titolo")
    private String titolo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "genere")
    private GenereEnum genere;

    @Column(name = "anno")
    private int anno;

    @Column(name = "voto")
    private int voto;

}
