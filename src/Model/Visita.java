package Model;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by murilo on 04/05/2017.
 */
public class Visita {

    private int id;
    private int id_pet;
    private int id_veterinario;
    private LocalDate dataVisita;
    private List<String> examesEMedicamentos;
    private String anaminese;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pet() {
        return id_pet;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    public LocalDate getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(LocalDate dataVisita) {
        this.dataVisita = dataVisita;
    }

    public List<String> getExamesEMedicamentos() {
        return examesEMedicamentos;
    }

    public void setExamesEMedicamentos(List<String> examesEMedicamentos) {
        this.examesEMedicamentos = examesEMedicamentos;
    }

    public String getAnaminese() {
        return anaminese;
    }

    public void setAnaminese(String anaminese) {
        this.anaminese = anaminese;
    }
}
