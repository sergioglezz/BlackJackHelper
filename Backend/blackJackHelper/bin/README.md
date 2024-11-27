# **Blackjack Helper**

¡Bienvenido al proyecto **Blackjack Helper**! 🎴 Esta es una aplicación sencilla creada con **Spring Boot** para jugar al blackjack mientras practico programación y desarrollo web.

---

## **¿Qué hace esta aplicación?**
1. **Backend:**  
   - Gestiona las reglas básicas del blackjack.
   - Permite al jugador pedir cartas o plantarse.  
   - Determina si el jugador gana, pierde o empata contra la banca.

2. **Frontend:**  
   - Interfaz web básica para interactuar con el juego.  
   - Muestra las cartas y el resultado en pantalla.

---

## **Tecnologías usadas**
- **Java 17**
- **Spring Boot** (Framework Backend)
- **HTML, CSS y JavaScript** (Interfaz web básica)
- **Maven** (Gestión de dependencias)

---

## **Cómo instalar y ejecutar el proyecto**
### **Requisitos previos**
- Tener **Java 17** o superior instalado.
- Tener **Maven** configurado en tu sistema.
- Un navegador para acceder a la interfaz web.

### **Pasos para ejecutarlo**
1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu_usuario/tu_repositorio.git
   ```
2. **Navegar al directorio del proyecto**:
   ```bash
   cd tu_repositorio
   ```
3. **Compilar y ejecutar el proyecto con Maven**:
   ```bash
   mvn spring-boot:run
   ```
4. **Abrir en el navegador**:
   Visita [http://localhost:9090/prueba/contador](http://localhost:9090/prueba/contador) para ver la interfaz web.

---

## **EndPoints disponibles**
### **Backend**
#### ⚠ TODOS LOS ENDPOINTS SON PROVISIONALES ACTUALMENTE NO RELACIONADO CON EL JUEGO ⚠
- **`/prueba/hola`**: Devuelve un saludo de prueba.
- **`/prueba/pene/(numero a tu elección)`**: Devuelve un arte ascii de prueba.
- **`/prueba/sumar`**: Suma un contador para pruebas.  
- **Próximos endpoints:** Añadiré funciones del juego como **iniciar partida**, **pedir carta**, etc.

### **Frontend**
- Página web para jugar:  
  [http://localhost:9090/prueba/contador](http://localhost:9090/prueba/contador)  
  Desde aquí puedes interactuar con la aplicación (sólo pruebas por ahora).

---

## **Próximos pasos**
- Añadir la lógica completa del blackjack al backend.
- Mejorar la interfaz web con cartas gráficas y mensajes de resultado.
- Posible implementación de un sistema de apuestas y puntuaciones.

---

¡Cualquier sugerencia o mejora es bienvenida! 😄
