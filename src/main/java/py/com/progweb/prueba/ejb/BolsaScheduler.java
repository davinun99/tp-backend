package py.com.progweb.prueba.ejb;


import py.com.progweb.prueba.model.BolsaPuntos;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Singleton
public class BolsaScheduler {
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

    //aca seteamos cada cuando tiempo queremos correr este proceso
    @Schedule(dayOfWeek="*" ,hour = "*/1",persistent = false)
    public  void updateBolsaPuntos(){
        Date currentDate= new Date(); //aca obtenemos la fecha de hoy
        List<BolsaPuntos> PuntosVencidos= bolsaPuntosDAO.getBolsaPuntosVencidos(currentDate); // aca obtenemos todas las Bolsa cuya fechaCaducidad<fechaActual
        for(BolsaPuntos bolsaPuntos: PuntosVencidos){//iteramos todos los Puntos Vencidos
            //bolsaPuntos.setSaldoPuntos(0); //En esta funcion setteamos el estado de la Bolsa en cero
            System.out.println("Aca imprimo las BolsasPuntos vencidas"+bolsaPuntos.toString());
        }
        System.out.print("Si ejecuta cada 5 segundos");
    }
}
