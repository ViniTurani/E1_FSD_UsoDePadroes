
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class Consultas {
    private List<RegistroDoTempo> registers;
    private String file;

    public Consultas(){
        registers = new LinkedList<>();
        this.file = "poa_temps.txt";
    }

    public void carregaDados(){
      AccessFile access = new AccessFile(file);
      registers = access.getRegisters();
    }    

    public List<String> datasEmQueChouveuMaisDe(double milimetros){
        return registers
            .stream()
            .filter(r->r.getPrecipitacao() > milimetros)
            .map(r->r.getDia()+"/"+r.getMes()+"/"+r.getAno())
            .toList();
    }

    public String diaQueMaisChoveuNoAno(int ano){
        RegistroDoTempo registro = registers
        .stream()
        .filter(reg->reg.getAno() == ano)
        .max(Comparator.comparing(RegistroDoTempo::getPrecipitacao))
        .orElseThrow(IllegalArgumentException::new);
        String resp = registro.getDia()+"/"+registro.getMes()+"/"+registro.getAno()+", "+registro.getPrecipitacao();
        return resp;
    }
}
