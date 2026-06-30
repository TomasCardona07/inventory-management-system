Inventory Management System
Contexto

La empresa Nova Inventory Solutions administra el inventario de varias pequeñas tiendas.

Actualmente el control se realiza mediante hojas de cálculo y registros en papel.

Debido al crecimiento de la empresa, se necesita un sistema que permita administrar el inventario de forma organizada.

La primera versión será una aplicación de consola.

En futuras versiones el sistema será migrado a base de datos y posteriormente a una API REST.

Objetivo

Desarrollar un sistema que permita administrar el inventario de una empresa.

Gestión de productos

El sistema deberá permitir registrar productos.

Cada producto debe almacenar:

Código único.
Nombre.
Categoría.
Precio.
Cantidad disponible.

No podrán existir dos productos con el mismo código.

El sistema deberá permitir:

Registrar producto.
Buscar producto por código.
Mostrar todos los productos.
Modificar el stock.
Eliminar un producto.
Gestión de proveedores

La empresa compra productos a distintos proveedores.

Cada proveedor deberá almacenar:

Identificador único.
Nombre.
Teléfono.

El sistema deberá permitir:

Registrar proveedor.
Buscar proveedor.
Mostrar proveedores.
Eliminar proveedor.
Entradas de inventario

Cuando llega mercancía desde un proveedor se registra una entrada.

Para registrar una entrada el sistema solicitará:

Producto.
Proveedor.
Cantidad recibida.

Si el producto o proveedor no existen, la operación no deberá realizarse.

Si la operación es válida:

El stock del producto deberá aumentar.

Salidas de inventario

Cuando una tienda solicita mercancía se registra una salida.

Se deberá indicar:

Producto.
Cantidad.

El sistema verificará:

Que el producto exista.
Que haya suficiente stock.

Si no hay suficiente cantidad, la salida no deberá realizarse.

Si la operación es válida:

El stock disminuirá.

Reportes

El sistema deberá permitir consultar:

Total de productos registrados.
Total de proveedores registrados.
Producto con mayor stock.
Producto con menor stock.
Valor total del inventario.
Productos agotados.
Productos con menos de cinco unidades disponibles.
Validaciones

El sistema nunca deberá finalizar por una entrada incorrecta del usuario.

Todos los datos numéricos deberán validarse.

No se permitirán valores negativos.

No podrán existir códigos repetidos.

No podrán existir identificadores repetidos.

Restricciones

Durante esta primera versión únicamente podrán utilizarse POO

No utilizar bases de datos.

No utilizar archivos.

No utilizar HashMap, HashSet ni enum.

Toda la información permanecerá únicamente en memoria mientras el programa esté en ejecución.

