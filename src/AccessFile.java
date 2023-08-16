import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AccessFile implements Acess{
    private List <RegistroDoTempo> registers;
    private String file;

    public AccessFile(String file){
        registers = new LinkedList<>();
        this.file = file;
        // this.file = "poa_temps.txt";
        this.carregaDados();
    }

    public void carregaDados(){
        String currDir = Paths.get("").toAbsolutePath().toString();

        String completeName = currDir+"/"+file;


        Path path = Paths.get(completeName);

        String line = "";

        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){

            sc.nextLine();

            while(sc.hasNext()){
                line = sc.nextLine();
                String data[] = line.split(" ");

                String dma[] = data[0].split("/");
                int day = Integer.parseInt(dma[0]);
                int month = Integer.parseInt(dma[1]);
                int year = Integer.parseInt(dma[2]);

                double precipitacao = Double.parseDouble(data[1]);
                double tempMaxima = Double.parseDouble(data[2]);
                double tempMinima = Double.parseDouble(data[3]);
                double horasInsolacao = Double.parseDouble(data[4]);
                double temperaturaMedia = Double.parseDouble(data[5]);
                double umidadeRelativaDoAr = Double.parseDouble(data[6]);
                double velocidadeDoVento = Double.parseDouble(data[7]);

                RegistroDoTempo reg = new RegistroDoTempo(day, month, year, precipitacao, tempMaxima, tempMinima, horasInsolacao, temperaturaMedia, umidadeRelativaDoAr, velocidadeDoVento);
                registers.add(reg);
            }
  
         }catch (IOException x){
             System.err.format("Erro de E/S: %s%n", x);
         }
    }    


    public List<RegistroDoTempo> getRegistros(){
        return registers;
    }
    
}
