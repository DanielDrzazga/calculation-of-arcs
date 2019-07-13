package drzazgadaniel.springbootcirculararc;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class Calculations {

    //Obliczenie współrzędnych metodą biegunową
    Coordinate calculationCoordinates(Coordinate coordinatesBeginningArc, Double radius, Double azimuth) {
        double dX = radius * Math.cos(Math.toRadians(azimuth));
        double dY = radius * Math.sin(Math.toRadians(azimuth));

        double x = coordinatesBeginningArc.getX() + dX;
        double y = coordinatesBeginningArc.getY() + dY;

        return new Coordinate(x, y);
    }

    //Lista współrzędnych na łuku o równych odległościach
    Collection<Coordinate> archDivisionEqualSections(Coordinate coordinatesCenterArc, Double radius, Double azimuth, Double obtuseAngle, Double equalSectionsN) {
        Collection<Coordinate> coordinatesOnArc = new LinkedList<>();

        double azimuthInitial = azimuth;
        double azimuthFinal = azimuth + obtuseAngle;
        double equalAngles = obtuseAngle / equalSectionsN;
        int pointNumber = 0;

        while (azimuthInitial <= azimuthFinal) {
            Coordinate coordinatePointOnArc = calculationCoordinates(coordinatesCenterArc, radius, azimuthInitial);
            coordinatePointOnArc.setNr(pointNumber);
            coordinatesOnArc.add(coordinatePointOnArc);
            pointNumber = pointNumber + 1;
            azimuthInitial = azimuthInitial + equalAngles;
        }

        return coordinatesOnArc;
    }


    //Obliczenie minimalna liczby odcinków łuku o zadanej strzałce
    Integer numberOfSegments(Double radius, Double obtuseAngle, Double precision) {

        Double arcAngleSection = (Math.acos((radius - precision) / (radius)) * 2);

        Double arcLengthSection = (arcAngleSection * 2 * Math.PI * radius) / (Math.toRadians(360));

        Double arcLength = (Math.toRadians(obtuseAngle) * 2 * Math.PI * radius) / (Math.toRadians(360));

        double n = arcLength / arcLengthSection;
        int result = 0;

        if (n % 1 == 0.0f) {
            result = (int) n;
        } else {
            result = (int) (n + 1);
        }

        return result;
    }

}