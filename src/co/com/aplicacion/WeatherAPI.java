package co.com.aplicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherAPI {
    private static final  String API_KEY = "d2d583bcf9fc667780df51c53896122a";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre de su ciudad: ");
        String city = sc.nextLine(); // nombre de la ciudad para la cual se desea obtener el clima actual

        try {
            URL url = new URL(String.format(API_URL, city, API_KEY));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            if (status == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null){
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();
                System.out.println(content.toString());
            } else {
                System.out.println("Error al obtener el clima de la ciudad " + city);
            }

        } catch (IOException e) {
            System.out.println("Error en la conexión a la API de OpenWeatherMap");
            e.printStackTrace();
        }
    }
}
