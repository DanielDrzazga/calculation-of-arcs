package drzazgadaniel.springbootcirculararc;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class NumberOfSegmentsGui extends VerticalLayout {

    private Label description;
    private Label information;
    private Label result;
    private NumberField radius;
    private NumberField precision;
    private NumberField obtuseAngle;
    private Button buttonCalculateSegments;
    private Calculations calculations;

    @Autowired
    public NumberOfSegmentsGui(Calculations calculations) {
        this.calculations = calculations;
        this.description = new Label("Obliczyć jaka musi być minimalna liczba N, aby polilinia łączaca kolejne wierzchołki " +
                "aproksymowała kształt łuku z dokładnością nie większą niż zadana wartość D");
        this.information = new Label("Uwaga wartości proszę podawać w metrach oraz stopniach oddzielone przecinkiem ");

        this.radius = new NumberField("Promień łuku");
        this.precision = new NumberField("D");
        this.obtuseAngle = new NumberField("Kąt rozwarcia");
        this.buttonCalculateSegments = new Button("Oblicz");

        this.result = new Label();

        add(description, information, radius, obtuseAngle, precision, buttonCalculateSegments, result);

        buttonCalculateSegments.addClickListener(buttonClickEvent -> {
            Integer numberOfSection = calculations.numberOfSegments(radius.getValue(), obtuseAngle.getValue(), precision.getValue());
            result.setText("Minimalna liczba odcinków wynosi: " + numberOfSection.toString());
        });
            
    }
}
