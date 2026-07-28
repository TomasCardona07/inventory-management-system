Versión 3.0 — Persistencia de la información
Contexto

Después de la optimización realizada en la versión anterior, el sistema de inventario ya es capaz de administrar grandes volúmenes de productos y proveedores con un buen rendimiento.

Sin embargo, la empresa ha identificado un problema importante: toda la información se pierde cuando la aplicación se cierra.

Esto obliga a registrar nuevamente productos, proveedores e historial de movimientos cada vez que el sistema inicia, lo que dificulta su uso diario y aumenta el riesgo de pérdida de información.

Como primer paso antes de migrar a una base de datos relacional, la empresa ha decidido incorporar un mecanismo de persistencia local utilizando archivos.

El objetivo es que la información permanezca disponible entre ejecuciones sin modificar la experiencia del usuario.

Objetivo

Implementar un sistema de persistencia que permita almacenar y recuperar toda la información del inventario utilizando archivos.

La aplicación deberá continuar funcionando como una aplicación de consola y mantener todas las funcionalidades desarrolladas en versiones anteriores.

Funcionalidades

Todas las funcionalidades desarrolladas en la versión 2 deberán continuar funcionando exactamente igual.

Registrar productos.
Registrar proveedores.
Registrar entradas de inventario.
Registrar salidas de inventario.
Eliminar productos.
Eliminar proveedores.
Consultar reportes.
Consultar historial de movimientos.
Nuevas funcionalidades
Persistencia automática

Toda la información del sistema deberá conservarse entre ejecuciones de la aplicación.

Al iniciar el programa:

El sistema deberá verificar si existen archivos previamente almacenados.
Si existen, deberá cargar automáticamente toda la información.
Si no existen, deberá iniciar con las colecciones vacías sin generar errores.
Guardado de información

Cada modificación realizada por el usuario deberá quedar almacenada.

Como mínimo deberán persistirse:

Productos.
Proveedores.
Historial de movimientos.

La información almacenada deberá permitir reconstruir completamente el estado del sistema al iniciar nuevamente la aplicación.

Integridad de los datos

Durante la carga de información el sistema deberá evitar estados inconsistentes.

Si ocurre un error durante la lectura de un archivo:

La aplicación no deberá finalizar inesperadamente.
Deberá informar el problema al usuario mediante un mensaje claro.
El resto del sistema deberá continuar funcionando siempre que sea posible.
Organización del proyecto

El crecimiento del sistema comienza a generar responsabilidades demasiado grandes en algunas clases.

Durante esta versión el desarrollador deberá reorganizar el código para mejorar su mantenimiento sin modificar el comportamiento de la aplicación.

La organización interna podrá cambiar siempre que la funcionalidad permanezca igual.

Rendimiento

La incorporación de archivos no deberá afectar significativamente el rendimiento logrado en la versión anterior.

Las búsquedas de productos y proveedores deberán seguir utilizando estructuras de datos eficientes en memoria después de cargar la información desde los archivos.

Restricciones

Durante esta versión únicamente podrán utilizarse los conocimientos adquiridos hasta el momento junto con los nuevos temas estudiados.

Está permitido utilizar:

Lectura de archivos.
Escritura de archivos.
Excepciones.
Java Collections.

No utilizar todavía:

Bases de datos.
JDBC.
Frameworks.
Spring Boot.

Criterios de aceptación

La versión se considerará finalizada cuando se cumplan todos los siguientes puntos:

El sistema conserva productos entre ejecuciones.
El sistema conserva proveedores entre ejecuciones.
El sistema conserva el historial de movimientos.
La aplicación carga automáticamente la información al iniciar.
La aplicación continúa funcionando si los archivos aún no existen.
La información permanece consistente después de cerrar y volver a abrir la aplicación.
Todas las funcionalidades de la versión anterior continúan funcionando sin cambios para el usuario.