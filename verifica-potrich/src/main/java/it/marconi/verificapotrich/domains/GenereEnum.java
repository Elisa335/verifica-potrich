package it.marconi.verificapotrich.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GenereEnum {
    AZIONE("AZIONE"), // all these enums give error, for no constructor
    COMMEDIA("COMMEDIA"),
    SCI_FI("SCI_FI"),
    FANTASY("FANTASY"),
    HORROR("HORROR");

    @Getter private String value;
}