package drzazgadaniel.springbootcirculararc;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

import java.util.Collection;

@Route
public class DivisionArcGui extends VerticalLayout {

    private Label description;
    private Label information;
    private Label result;
    private Grid<Coordinate> coordinateTable;

    private NumberField beginningOfTheArcX;
    private NumberField beginningOfTheArcY;
    private HorizontalLayout beginningArc;

    private NumberField radius;
    private NumberField azimuth;
    private NumberField obtuseAngle;
    private NumberField equalSectionsN;
    private Button buttondivisionArc;
    private Calculations calculations;

    public DivisionArcGui(Calculations calculations) {
        this.calculations = calculations;
        this.description = new Label("Wygenerować listę współrzędnych x,y kolejnych punktów na łuku powstałych przez podział łuku na N równych odcinków");
        this.information = new Label("Uwaga wartości proszę podawać w metrach oraz stopniach oddzielone przecinkiem ");

        this.beginningOfTheArcX = new NumberField("Początek łuku X");
        this.beginningOfTheArcY = new NumberField("Początek łuku Y");
        this.beginningArc = new HorizontalLayout(beginningOfTheArcX, beginningOfTheArcY);

        this.radius = new NumberField("Promień łuku");
        this.radius.setWidth("275px");

        this.azimuth = new NumberField("Azymut początku łuku");
        this.azimuth.setWidth("275px");

        this.obtuseAngle = new NumberField("Kąt rozwarty");
        this.obtuseAngle.setWidth("275px");

        this.equalSectionsN = new NumberField("Liczba odcinków N>1");
        this.equalSectionsN.setWidth("275px");

        this.buttondivisionArc = new Button("Oblicz");

        this.result = new Label();
        this.coordinateTable = new Grid<>();

        add(description, information, beginningArc, radius, azimuth, obtuseAngle, equalSectionsN, buttondivisionArc, result);

        buttondivisionArc.addClickListener(buttonClickEvent -> {
            Coordinate coordinatesBeginningArc = new Coordinate(beginningOfTheArcX.getValue(), beginningOfTheArcY.getValue());
            Coordinate coordinatesCenterArc = calculations.calculationCoordinates(coordinatesBeginningArc, radius.getValue(), azimuth.getValue() + 180);

            Collection<Coordinate> archDivisionEqualSections = calculations.archDivisionEqualSections(coordinatesCenterArc,
                    radius.getValue(),
                    azimuth.getValue(),
                    obtuseAngle.getValue(),
                    equalSectionsN.getValue());


            coordinateTable.removeAllColumns();
            coordinateTable.setItems(archDivisionEqualSections);
            coordinateTable.addColumn(Coordinate::getNr);
            coordinateTable.addColumn(Coordinate::getX);
            coordinateTable.addColumn(Coordinate::getY);
            add(coordinateTable);
        });

    }
}
