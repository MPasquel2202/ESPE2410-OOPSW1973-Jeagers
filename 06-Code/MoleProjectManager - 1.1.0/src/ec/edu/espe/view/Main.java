package ec.edu.espe.view;

import java.util.Scanner;

/**
 *
 * @author Dennis Paucar
 */

public class Main {
    
    public static void mostrarMenu() {
        System.out.println("\nGestor de Proyectos");
        System.out.println("1. Crear Proyecto");
        System.out.println("2. Registrar Cambio de Presupuesto");
        System.out.println("3. Ver Fechas Restantes de Soporte de los Proyectos");
        System.out.println("4. Generar Reporte Mensual de Proyectos");
        System.out.println("5. Busqueda de Proyectos");
        System.out.println("6. Generar Reporte Individual de Proyecto");
        System.out.println("7. Visualizar Lista de Clientes/Consumidores");
        System.out.println("8. Administrar Estatus de Proyecto");
        System.out.println("9. Registrar Actividades del Proyecto");
        System.out.println("10. Generar Recordatorio de Fechas Importantes");
        System.out.println("11. Generacion de Soporte Posventa");
        System.out.println("12. Salir");
        System.out.print("Seleccione una opcion: ");
    }

   
    public static void mostrarSubmenuBusqueda() {
        System.out.println("\nSubmenu: Busqueda de Proyectos");
        System.out.println("1. Busqueda por Fechas");
        System.out.println("2. Busqueda por RUC");
        System.out.println("3. Busqueda por Estado de Proyecto");
        System.out.println("4. Volver al Menu Principal");
        System.out.print("Seleccione una opcion: ");
    }

  
    public static void mostrarSubmenuEstatus() {
        System.out.println("\nSubmenu: Administrar Estatus de Proyecto");
        System.out.println("1. Visualizar Estatus de los Proyectos");
        System.out.println("2. Cambiar Estatus de Proyecto");
        System.out.println("3. Ver Historial de Cambios de Estatus");
        System.out.println("4. Volver al Menu Principal");
        System.out.print("Seleccione una opcion: ");
    }

   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Opcion 1: Crear Proyecto");
              
                    break;
                case 2:
                    System.out.println("Opcion 2: Registrar Cambio de Presupuesto");
                 
                    break;
                case 3:
                    System.out.println("Opcion 3: Ver Fechas Restantes de Soporte de los Proyectos");
                  
                    break;
                case 5:
                    mostrarSubmenuBusqueda();
                    int opcionBusqueda = scanner.nextInt();
                    switch (opcionBusqueda) {
                        case 1:
                            System.out.println("Busqueda por Fechas");
                            
                            break;
                        case 2:
                            System.out.println("Busqueda por RUC");
                        
                            break;
                        case 3:
                            System.out.println("Busqueda por Estado de Proyecto");
                           
                            break;
                        case 4:
                            System.out.println("Volviendo al Menú Principal...");
                            break;
                        default:
                            System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                            break;
                    }
                    break;
                case 8:
                    mostrarSubmenuEstatus();
                    int opcionEstatus = scanner.nextInt();
                    switch (opcionEstatus) {
                        case 1:
                            System.out.println("Visualizar Estatus de los Proyectos");
                            
                            break;
                        case 2:
                            System.out.println("Cambiar Estatus de Proyecto");
                            
                            break;
                        case 3:
                            System.out.println("Ver Historial de Cambios de Estatus");
                           
                            break;
                        case 4:
                            System.out.println("Volviendo al Menu Principal...");
                            break;
                        default:
                            System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                            break;
                    }
                    break;
                case 12:
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opcion valida.");
                    break;
            }
        }
        scanner.close();
    }
}
