# CENSO 21 - TAMBIEN CONOCIDO COMO TP 5

En el presente se detallan las cosas a considerar para el funcionamiento del proyecto

## Necesarios 🚀
### Pre-requisitos 📋

El proyecto esta desarrollado con los siguentes lenguajes y aplicativos

mysql  Ver 8.0.25 for Win64 on x86_64 (MySQL Community Server - GPL)

java 15.0.2 2021-01-19
Java(TM) SE Runtime Environment (build 15.0.2+7-27)
Java HotSpot(TM) 64-Bit Server VM (build 15.0.2+7-27, mixed mode, sharing)
_esto fue un error debio  ser con java 8 pero al generar el proyecto tomo la ultima version_
_no hay nada de ultima generacion deberia correr con java 8 sin problemas pero lo anoto por las duadas_

Eclipse IDE for Enterprise Java Developers (includes Incubating components)
Version: 2020-12 (4.18.0)
Build id: 20201210-1552

MySQL Workbench 8. Version 8.0.25 build 788958 CE (64 bits) Community

_lo comento por si hay problemas de compatibilidad pero no creo que sea el caso_



**Deployment**
la estructura de la base de datos esta detallada en el archivo


_Que cosas necesitas para instalar el software y como instalarlas_

```
tp5_person.sql
```

_los detalles para la conexion a la bdd estan en la clase_
```
src/com/censo21/repository/Connector.java
```

## Ejecutando las pruebas ⚙️

_En cuanto al codigo_

hay varios acotaciones:

-la base de dato NO ESTA normalizar pero para el ejemplo considere que una sola tabla con todos los datos funcionaria bien

-la ruta para comenzar la prueba es _/index.html_  por _get_

-se uso jsp

-las librerias principales a agregar estan en: _/WebContent/WEB-INF/lib_ las agrege en propiedades del proyecto

-para la _src_ prantilla html estan en la ruta _/WebContent/utils_

-las notificaciones _response.getWriter().println()_ aunque la idea era devolver un booleano al jsp y se mostrar un _div class="alert alert-success" role="alert"_ o  _div class="alert alert-danger" role="alert"_ Segun el estado del booleano

-pero el redireccionamiento con _request.getRequestDispatcher("*.jsp");_ esta usado por ejemplo en el proceso de agregar o acutalizar datos

-la ruta donde se generan los _.txt_ de reporte es _C:/reportes_censo/_ por lo que se debe crear la carpeta antes de la prueba

-esta ruta esta en la ruta del punto anterior esta detallada en la clase _/src/com/censo21/repository/PersonRepository.java_ linea 366
```
    File file = new File("C:/reportes/", fileName);
```
#### Ruta del repositorio 📋
```
https://github.com/Ramon-Arias25/tp5-crud-java-web-jsp
```