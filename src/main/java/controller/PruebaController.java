package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class PruebaController {

	@GetMapping("/hola")
	public String hola() {
		return "hola";
	}
	
	@GetMapping("/pene/{n}")
	public String pene(@PathVariable int n) {
		String longitud = "";
		for(int i=0; i<n; i++){
			longitud=longitud+"=";
		}
		return "8"+longitud+"D";
	}
	
	 private int contador = 0; 
	
	 @GetMapping("/sumar")
	 public String sumar() {
		 contador++; // Incrementar el contador
		 return "Contador actual: " + contador;
	 }
	
	@GetMapping("/contador")
	@ResponseBody
	public String contadorHtml() {
	    return """
	            <!DOCTYPE html>
	            <html lang="en">
	            <head>
	                <meta charset="UTF-8">
	                <meta name="viewport" content="width=device-width, initial-scale=1.0">
	                <title>Contador</title>
	                <style>
	                    body {
	                        font-family: Arial, sans-serif;
	                        text-align: center;
	                        margin-top: 50px;
	                    }
	                    button {
	                        padding: 10px 20px;
	                        font-size: 16px;
	                        cursor: pointer;
	                        background-color: #4CAF50;
	                        color: white;
	                        border: none;
	                        border-radius: 5px;
	                    }
	                    button:hover {
	                        background-color: #45a049;
	                    }
	                    .contador {
	                        font-size: 24px;
	                        margin-top: 20px;
	                    }
	                </style>
	            </head>
	            <body>
	                <h1>Contador</h1>
	                <button onclick="sumar()">Suma al contador</button>
	                <div class="contador" id="contador">Contador actual: 0</div>

	                <script>
	                    async function sumar() {
	                        try {
	                            const response = await fetch('/prueba/sumar');
	                            const text = await response.text();
	                            document.getElementById('contador').innerText = text;
	                        } catch (error) {
	                            console.error('Error al sumar:', error);
	                        }
	                    }
	                </script>
	            </body>
	            </html>
	            """;
	}
	
}
