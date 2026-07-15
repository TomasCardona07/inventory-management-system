Versión 2.0 — Optimización del rendimiento
Contexto

Después de varios meses en funcionamiento, el sistema de inventario ha sido adoptado por un mayor número de tiendas.

Actualmente la aplicación administra miles de productos y proveedores.

Aunque todas las funcionalidades desarrolladas en la primera versión continúan funcionando correctamente, el equipo de soporte ha detectado que algunas operaciones tardan más de lo esperado cuando la cantidad de información aumenta.

La empresa ha decidido realizar una actualización enfocada en mejorar el rendimiento interno del sistema sin modificar la experiencia del usuario.

El objetivo es preparar la aplicación para seguir creciendo antes de incorporar persistencia en archivos y bases de datos.

Objetivo

Optimizar la estructura interna del sistema manteniendo todas las funcionalidades desarrolladas en la versión anterior.

El usuario deberá seguir utilizando la aplicación de la misma manera.

La actualización estará enfocada principalmente en mejorar el tiempo de búsqueda y acceso a la información.

Funcionalidades

Todas las funcionalidades existentes deberán seguir funcionando exactamente igual.

Registrar productos.
Registrar proveedores.
Registrar entradas de inventario.
Registrar salidas de inventario.
Buscar productos.
Buscar proveedores.
Eliminar productos.
Eliminar proveedores.
Generar reportes.

Nuevas funcionalidades
Historial de movimientos

Cada vez que se registre una entrada o una salida de inventario, el sistema deberá almacenar un movimiento.

Cada movimiento deberá conservar como mínimo:

Tipo de movimiento.
Código del producto.
Cantidad.
Fecha y hora del movimiento.

El usuario podrá consultar posteriormente el historial completo de movimientos.

Durante esta versión el historial permanecerá únicamente en memoria.

Reportes

Agregar los siguientes reportes.

Cantidad total de entradas realizadas.
Cantidad total de salidas realizadas.
Últimos movimientos registrados.
Productos que nunca han recibido entradas.
Productos que nunca han tenido salidas.
Rendimiento

La empresa espera que las búsquedas de productos y proveedores continúen siendo rápidas incluso cuando el sistema almacene decenas de miles de registros.

El desarrollador deberá analizar qué estructuras de datos permiten cumplir este objetivo.

No será suficiente con que el sistema funcione.

También deberá ser eficiente.

Restricciones

Durante esta versión únicamente podrán utilizarse los conocimientos adquiridos hasta el momento junto con los nuevos temas estudiados en esta etapa.

Está permitido utilizar las estructuras de datos vistas durante esta versión.

No utilizar bases de datos.

No utilizar archivos.

Toda la información continuará almacenándose únicamente en memoria.
