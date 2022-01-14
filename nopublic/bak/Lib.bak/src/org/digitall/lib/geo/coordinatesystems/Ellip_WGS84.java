package org.digitall.lib.geo.coordinatesystems;
/*

WGS84: Acrónimo de World Geodetic System 1984. Sistema de coordenadas mundiales,
data de 1984 y es la base para sistemas de posicionamiento globales como el GPS.
Está pendiente una próxima revisión para 2010.

El WGS84 usa como elipsoide de referencia el GRS 80.

Sus parámetros son los siguientes:

    * Semieje Mayor: 6378137.0 m
    * Semieje Menor: 6356752.31424518 m
    * Aplanamiento: 298.257223563

*/

public class Ellip_WGS84 {

    private double majorAxis = 6378137.0;
    private double minorAxis = 6356752.31424518;
    private double flattening = 298.257223563;

    public Ellip_WGS84() {
    }

}
