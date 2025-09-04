import accidentes.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void mostrarMenu(){
        System.out.println("-------------Bienvenido a la venta de carros-------------");
        System.out.println("Para comenzar eliga una de las siguientes opciones");
        System.out.println("1. Mostrar Incidentes");
        System.out.println("2. País de origen más común");
        System.out.println("3. Marca de carros con más incidentes");
        System.out.println("4. Marca más vendida");
        System.out.println("5. Añadir marca");
        System.out.println("6. Añadir dueño");
        System.out.println("7. Añadir carro");
        System.out.println("8. Añadir comentario");
        System.out.println("9. Añadir incidente");
        System.out.println("10. Salir");
    }
    public static void main(String[] args) {

        ArrayList <Marca> marcas = new ArrayList<>();
        ArrayList <Dueno> duenos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        boolean seguir = true;
        mostrarMenu();

        while (seguir){
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
                case 1:
                    //Mostar incidentes
                    if (duenos.size() > 0){
                        for (int i = 0; i < duenos.size(); i++) {
                            if (duenos.get(i).getIncidentes().size() > 0){
                                System.out.println(duenos.get(i).mostrarIncidentes());
                            } else {
                                System.out.println("El dueño " + duenos.get(i).getNombre() + " no tiene incidentes");
                            }

                        }
                    } else {
                        System.out.println("Aun no hay dueños registrados");
                    }

                    mostrarMenu();
                    break;
                case 2:
                    //País de origen más común
                    ArrayList <String> paisesMarcas = new ArrayList<>();
                    for (int i = 0; i < marcas.size(); i++) {
                        for (int j = 0; j < marcas.get(i).getCarros().size(); j++) {
                            paisesMarcas.add(marcas.get(i).getPais());
                        }
                    }
                    if (paisesMarcas.size() > 0){
                        paisesMarcas.sort(null); // tengo que confesarlo, googlee como ordenar un arraylist
                        String paisMasRepetido = paisesMarcas.get(0);
                        int maxRepeticiones = 0;
                        String paisActual = paisesMarcas.get(0);
                        int repeticionesActuales = 1;

                        for (int i = 0; i < paisesMarcas.size()-1; i++) {
                            if (paisesMarcas.get(i).equals(paisesMarcas.get(i+1))) { // se va sumando a i hasta que el que sigue ya no es
                                repeticionesActuales++;
                            } else {
                                if (repeticionesActuales > maxRepeticiones) { //cuando ya no es se guarda en caso de ser mayor
                                    paisMasRepetido = paisActual;
                                    maxRepeticiones = repeticionesActuales;
                                }
                                paisActual = paisesMarcas.get(i+1); //se cambia el pais actual
                                repeticionesActuales = 1; // volvemos a empezar a contar
                            }
                        }

                        if (repeticionesActuales > maxRepeticiones) { // se ve feo pero sin esto se olvida de la ultima
                            paisMasRepetido = paisActual;
                            maxRepeticiones = repeticionesActuales;
                        }

                        System.out.println("El pais más repetido es: " + paisMasRepetido + " con " + maxRepeticiones + " repeticiones");
                    } else {
                        System.out.println("Todavia no hay marcas registradas");
                    }
                    mostrarMenu();
                    break;
                case 3:
                    //Marca de carros con más incidentes
                    //Una marca -> tiene un carro tiene un -> tiene un dueno -> incidente
                    if (marcas.size() > 0){
                        int[] incidentes= new int[marcas.size()];
                        for (int i = 0; i < marcas.size(); i++) {
                            int counter = 0;
                            for (int j = 0; j < marcas.get(i).getCarros().size(); j++) {
                                for (int k = 0; k < marcas.get(i).getCarros().get(j).getDuenos().size(); k++) {
                                    counter += marcas.get(i).getCarros().get(j).getDuenos().get(k).getIncidentes().size();
                                }
                            }
                            incidentes[i] = counter;
                        }
                        int maxIncid = 0;
                        for (int i = 1; i < marcas.size(); i++) {
                            if(incidentes[i] > incidentes[maxIncid]){
                                maxIncid = i;
                            }
                        }
                        System.out.println("La marca con más incidentes es: " + marcas.get(maxIncid).getNombre());
                    } else {
                        System.out.println("Aun no hay suficientes marcas");
                    }
                    mostrarMenu();
                    break;
                case 4:
                    if (marcas.size()> 0){
                        int maxIn = 0;
                        for (int i = 1; i < marcas.size(); i++) {
                            if (marcas.get(maxIn).getCarros().size() < marcas.get(i).getCarros().size()) {
                                maxIn = i;
                            }
                        }
                        System.out.println("La marca con más carros vendidos es: " + marcas.get(maxIn).getNombre());
                    } else {
                        System.out.println("Aun no hay suficientes marcas");
                    }
                    //Marca mas vendida

                    mostrarMenu();
                    break;
                case 5:
                    //Añadir marca
                    System.out.println("Usted va a añadir una marca, por favor proporcione los siguientes datos en orden: ");
                    System.out.println("Id, debe ser un numero: ");
                    long idMarca = Long.parseLong(sc.nextLine());
                    System.out.println("Marca:");
                    String nombreMarca = sc.nextLine();
                    System.out.println("Pais:");
                    String paisMarca = sc.nextLine();
                    //Verificar que el id no este en uso
                    boolean idEnUso = false;
                    for (int i = 0; i < marcas.size(); i++) {
                        if (marcas.get(i).getId() == idMarca){
                            System.out.println("Este id ya esta en uso, intente de nuevo");
                            idEnUso = true;
                            break;
                        }
                    }
                    if (!idEnUso){
                        marcas.add(new Marca(idMarca,nombreMarca,paisMarca));
                        System.out.println("Marca añadida exitosamente");
                    }
                    mostrarMenu();
                    break;
                case 6:
                    //Añadir dueñoo
                    System.out.println("Usted va a añadir un nuevo dueño, por favor proporcione los siguientes datos en orden:");
                    System.out.println("Cédula, debe ser un numero: ");
                    long cedula = Long.parseLong(sc.nextLine());
                    System.out.println("Nombre dueño:");
                    String nombreDueno = sc.nextLine();
                    System.out.println("Apellido dueño:");
                    String apellidoDueno = sc.nextLine();
                    System.out.println("Telefono dueño:");
                    String telefonoDueno = sc.nextLine();
                    duenos.add(new Dueno(cedula,nombreDueno,apellidoDueno,telefonoDueno));
                    System.out.println("Dueño añadido exitosamente");
                    mostrarMenu();
                    break;
                case 7:
                    //añadir carro
                    System.out.println("Usted va a añadir un nuevo carro, por favor proporcione los siguientes datos en orden:");
                    System.out.println("Placa:");
                    String placaCarro = sc.nextLine();
                    System.out.println("Modelo:");
                    String modeloCarro = sc.nextLine();
                    System.out.println("Año:");
                    int anoCarro = Integer.parseInt(sc.nextLine());
                    System.out.println("Marca, proporcione el id de la marca:");
                    long idMarcaCarro = Long.parseLong(sc.nextLine());
                    boolean exitoso = false;
                    for (int i = 0; i < marcas.size(); i++) {
                        if(marcas.get(i).getId() == idMarcaCarro){
                            marcas.get(i).agregarCarro(new Carro(placaCarro,modeloCarro,anoCarro));
                            exitoso = true;
                        }
                    }
                    if (exitoso){
                        System.out.println("Carro añadido exitosamente");
                    } else {
                        System.out.println("La marca proporcionada no existe, intente de nuevo");
                    }
                    mostrarMenu();
                    break;
                case 8:
                    //anadir comentario
                    System.out.println("Usted va a añadir un comentario, por favor proporcione los siguientes datos en orden:");
                    System.out.println("Escriba su comentario");
                    String comentario = sc.nextLine();
                    System.out.println("A que carro desea añadir el comentario, escriba su placa: ");
                    String placaCarroComentario = sc.nextLine();
                    boolean comentarioExitoso = false;
                    for (int i = 0; i < marcas.size(); i++) {
                        for (int j = 0; j < marcas.get(i).getCarros().size(); j++) {
                            if(marcas.get(i).getCarros().get(j).getPlaca().equals(placaCarroComentario)){
                                marcas.get(i).getCarros().get(j).agregarComentario(comentario,new Date());
                                comentarioExitoso = true;
                                break;
                            }
                        }
                    }
                    if (comentarioExitoso){
                        System.out.println("Comentario añadido exitosamente");
                    } else {
                        System.out.println("Placa invalida, vuelva a intentar");
                    }
                    mostrarMenu();
                    break;
                case 9:
                    //añadir incidente
                    System.out.println("Usted va a añadir un nuevo incidente, proporcione");
                    System.out.println("Código del incidente");
                    long codigoIncidente = Long.parseLong(sc.nextLine());
                    System.out.println("Descripcion");
                    String descripcionIncidente = sc.nextLine();
                    System.out.println("Cedula del dueño");
                    long cedulaIncidente = Long.parseLong(sc.nextLine());
                    boolean incidenteExitoso = false;
                    for (int i = 0; i < duenos.size(); i++) {
                        if (duenos.get(i).getCedula() == cedulaIncidente){
                            duenos.get(i).agregarIncidente(new Incidente(codigoIncidente,descripcionIncidente,new Date(),duenos.get(i).getTelefono()));
                            incidenteExitoso = true;
                            break;
                        }
                    }
                    if (incidenteExitoso){
                        System.out.println("Incidente añadido exitosamente");
                    } else {
                        System.out.println("Cédula inválida vuelva a intentar");
                    }
                    mostrarMenu();
                    break;
                case 10:
                    seguir = false;
                    break;
                default:
                    System.out.println("Opcion incorrecta, intente de nuevo");
                    mostrarMenu();
            }
        }

    }
}

