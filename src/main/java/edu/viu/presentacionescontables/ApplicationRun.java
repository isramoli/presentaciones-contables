package edu.viu.presentacionescontables;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase para lanzar la aplicación
 */
@SpringBootApplication
public class ApplicationRun {

	/**
	 * Lanzador de la aplicación
	 *
	 * @param args Argumentos de la línea de comendos
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationRun.class, args);
	}
}
