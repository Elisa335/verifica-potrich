package it.marconi.verificapotrich.domains;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmForm {
    
    @NotEmpty
    @Size(max = 50)
    private String titolo;

    @Enumerated
    private GenereEnum genere;

    @NotNull
    private int anno;

    @NotNull
    //@Pattern(regexp = "([1-5]{1})") // regular expression per un numero di 1 cifra da 1 a 5
    @Min(value=1, message="Il voto minimo deve essere 1!")  
    @Max(value=5, message="Il voto massimo deve essere 5!")  
    private int voto;
}
