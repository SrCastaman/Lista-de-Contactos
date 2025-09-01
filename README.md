# Gestor de Contactos

**Gestor de contactos** es una aplicación de escritorio en Java usando JavaFX que permite crear, modificar, eliminar y listar contactos. La aplicación utiliza SQLite como base de datos local.

---

## Características

- Crear nuevos contactos con nombre, teléfono y correo electrónico.
- Editar contactos existentes.
- Eliminar contactos.
- Listar todos los contactos.
- Base de datos SQLite local integrada.
- Interfaz gráfica intuitiva usando JavaFX.

---

## Tecnologías

- Java 21 (JDK 21)
- JavaFX 21
- SQLite
- Gradle para gestión de dependencias y compilación

---

## Requisitos

- Tener instalado Java 21 (o superior).
- Tener instalado JavaFX 21.0.8.
- Tener Gradle instalado (opcional si quieres compilar desde el código fuente).

---

## Ejecución

### Desde código fuente

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/SrCastaman/Lista-de-Contactos.git
2. Entrar en el directorio del proyecto
   cd Lista-de-contactos-JAVA
3. Construir el proyecto con Gradle
   gradle clean build
4. Ejecutar el JAR generado:
   java --module-path "ruta/a/javafx-sdk-21.0.8/lib" --add-modules javafx.controls,javafx.fxml -jar build/libs/GestorContactos-1.0-all.jar
   **NOTA: En "ruta/a/javafx-sdk-21.0.8/lib" deberas de poner la ruta donde has instalado el JavaFX SDK 21.0.8**


