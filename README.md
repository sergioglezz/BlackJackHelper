# **Blackjack Helper**

¡Bienvenido al proyecto **Blackjack Helper**! 🎴 Esta es una aplicación para jugar al blackjack creada como práctica de programación y desarrollo web, combinando **Spring Boot** para el backend y **React** para el frontend.

---

## **¿Qué hace esta aplicación?**

1. **Backend:**  
   - Gestiona las reglas básicas del blackjack.  
   - Proporciona una API REST para interactuar con el juego desde el frontend.  
   - Determina si el jugador gana, pierde o empata contra la banca.

2. **Frontend:**  
   - Interfaz web desarrollada con **React** para interactuar con el juego.  
   - Muestra las cartas, los botones de interacción y los resultados en pantalla.

---

## **Tecnologías usadas**

### Backend:
- **Java 17**
- **Spring Boot** (Framework Backend)
- **Maven** (Gestión de dependencias)

### Frontend:
- **React**
- **JavaScript (ES6)**  
- **CSS** (Estilos para el tablero)

---

## **Cómo instalar y ejecutar el proyecto**

### **Requisitos previos**

- Tener **Java 17** o superior instalado.
- Tener **Maven** configurado en tu sistema.
- Tener **Node.js** instalado (para el frontend).

---

### **Pasos para ejecutar el proyecto**

#### **1. Clonar el repositorio**
```bash
git clone https://github.com/tu_usuario/tu_repositorio.git
```

#### **2. Configurar y ejecutar el backend**
1. Navega al directorio del backend:
   ```bash
   cd backend
   ```
2. Compila y ejecuta el backend con Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. El backend estará disponible en [http://localhost:8080](http://localhost:8080).

---

#### **3. Configurar y ejecutar el frontend**
1. Navega al directorio del frontend:
   ```bash
   cd frontend/blackjack-frontend
   ```
2. Instala las dependencias del proyecto:
   ```bash
   npm install
   ```
3. Ejecuta el servidor de desarrollo:
   ```bash
   npm start
   ```
4. El frontend estará disponible en [http://localhost:3000](http://localhost:3000).

---

## **Uso de la aplicación**

### Funcionalidades:
- **Pedir Carta:** Permite al jugador solicitar una nueva carta.  
- **Mostrar Carta del Dealer:** Revela la carta oculta del dealer.  
- **Reiniciar Juego:** Reinicia la partida desde cero.

### Interacción entre frontend y backend:
El frontend realiza llamadas a los siguientes endpoints del backend:

- **`GET /api/blackjack`**: Obtiene el estado actual del juego.
- **`POST /api/blackjack/pedir`**: Permite al jugador pedir una carta.  
- **`POST /api/blackjack/mostrar`**: Muestra la carta oculta del dealer.  
- **`POST /api/blackjack/reiniciar`**: Reinicia la partida.

---

## **Estructura del proyecto**

```
project-root
├── backend                   # Backend del proyecto
│   ├── src                   # Código fuente del backend
│   ├── pom.xml               # Archivo de configuración de Maven
│   └── README.md             # Instrucciones específicas del backend
├── frontend                  # Frontend del proyecto
│   ├── public
│   │   └── assets
│   │       └── cartas        # Imágenes de las cartas
│   ├── src
│   │   ├── components        # Componentes de React
│   │   │   └── Tablero.js    # Lógica del tablero y la interfaz
│   │   ├── services          # Servicios para interactuar con el backend
│   │   │   └── blackjackService.js
│   │   ├── styles            # Estilos CSS
│   │   │   └── Tablero.css
│   │   └── index.js          # Punto de entrada de React
│   ├── package.json          # Configuración de npm
│   └── README.md             # Instrucciones específicas del frontend
└── README.md                 # Este archivo
```

---

## **Recursos importantes**

### **Imágenes de las cartas**
- Las imágenes de las cartas están ubicadas en `frontend/public/assets/cartas` y deben seguir el formato:  
  `valor_palo.png`.  
  Ejemplo:
  - `10_treboles.png` (10 de tréboles)
  - `a_corazones.png` (As de corazones)

### **Configuración del puerto**
- El backend usa el puerto `8080` por defecto.  
- El frontend usa el puerto `3000` por defecto.  

Si necesitas cambiar estos puertos, asegúrate de ajustar las URLs en el archivo `blackjackService.js`.

---

## **Próximos pasos**
- Mejorar los estilos del frontend para un diseño más atractivo.
- Implementar un sistema de apuestas y puntuaciones.
- Añadir mensajes personalizados al finalizar el juego.

---

¡Cualquier sugerencia o mejora es bienvenida! 😄
