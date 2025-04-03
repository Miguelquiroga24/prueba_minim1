package edu.upc.dsa.util;

import net.moznion.random.string.RandomStringGenerator;

/**
 * Clase de utilidad para generar identificadores aleatorios.
 */
public class RandomUtil {

    /**
     * Genera una cadena aleatoria que puede usarse como ID único.
     * @return una cadena aleatoria alfanumérica
     */
    public static String getId() {
        RandomStringGenerator generator = new RandomStringGenerator();
        return generator.generateByRegex("\\w+\\d*[0-9]{0,8}");
    }
}
