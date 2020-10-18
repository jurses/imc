package imc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Main extends Application{

	private static double masa;
	private static double altura;
	
	public static void main(String[] args) {
		masa = 0;
		altura = 0;
		launch(args);
	}
	
	public double calcularIMC(double masa, double altura)
	{
		return masa / Math.pow(altura / 100, 2);
	}
	
	public String obtenerObservacion(double imc)
	{
		if (imc < 18.5)
			return "Bajo peso";
		else if (imc < 25)
			return "Normal";
		else if (imc < 30)
			return "Sobrepeso";
		else
			return "Obesidad";
	}

	@Override
	public void start(Stage s)
	{
		s.setTitle("Calculadora de IMC");
		VBox vertElem = new VBox();
		Scene sc = new Scene(vertElem, 400, 200);
		Text t_masa = new Text("Masa:");
		TextField tf_masa = new TextField();
		TextField tf_altura = new TextField();
		Text t_masaunidad = new Text("kg");
		Text t_alturaunidad = new Text("cm");
		Text t_altura = new Text("Altura:");
		Text t_resultado = new Text("IMC: masa / altura^2");
		Text t_observacion = new Text("Subpeso | Normal | Sobrepeso | Obeso");
		HBox hor_masaelementos = new HBox();
		HBox hor_alturaelementos = new HBox();
		HBox hor_resultado = new HBox();
		HBox hor_observacion = new HBox();
		
		hor_masaelementos.getChildren().add(t_masa);
		hor_masaelementos.getChildren().add(tf_masa);
		hor_masaelementos.getChildren().add(t_masaunidad);
		hor_alturaelementos.getChildren().add(t_altura);
		hor_alturaelementos.getChildren().add(tf_altura);
		hor_alturaelementos.getChildren().add(t_alturaunidad);
		hor_resultado.getChildren().add(t_resultado);
		hor_observacion.getChildren().add(t_observacion);
		
		vertElem.getChildren().add(hor_masaelementos);
		vertElem.getChildren().add(hor_alturaelementos);
		vertElem.getChildren().add(hor_resultado);
		vertElem.getChildren().add(hor_observacion);
		
		hor_masaelementos.setAlignment(Pos.CENTER);
		hor_alturaelementos.setAlignment(Pos.CENTER);
		hor_resultado.setAlignment(Pos.CENTER);
		hor_observacion.setAlignment(Pos.CENTER);
		vertElem.setAlignment(Pos.CENTER);
		
		StringProperty resultado = new SimpleStringProperty();
		StringProperty observacion = new SimpleStringProperty();
		
		t_resultado.textProperty().bind(resultado);
		t_observacion.textProperty().bind(observacion);
		
		tf_masa.textProperty().addListener((o, ov, nv) -> {
			masa = Double.parseDouble(nv);
			if (altura > 0)
			{
				double imc = calcularIMC(masa, altura);
				resultado.set(String.valueOf(imc));
				observacion.set(obtenerObservacion(imc));
			}
			else
			{
				resultado.set("La altura tiene que ser un valor mayor que 0.");
			}
		});
		
		tf_altura.textProperty().addListener((o, v, nv) -> {
			altura = Double.parseDouble(nv);
			if (altura > 0)
			{
				double imc = calcularIMC(masa, altura);
				resultado.set(String.valueOf(imc));
				observacion.set(obtenerObservacion(imc));
			}
			else
			{
				resultado.set("La altura tiene que ser un valor mayor que 0.");
			}
		});
		
		
		s.setScene(sc);
		s.show();
	}
}
