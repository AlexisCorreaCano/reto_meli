# reto_meli

Este repositorio contiene el código fuente de una aplicación móvil desarrollada en Android con java, la función principal de la aplicación es que el usuario ingrese y pueda buscar a través de una palabra clave un elemento deseado,  luego los resultados relacionados a esta búsqueda se listaran y posteriormente el usuario podrá visualizar los detalles de los elementos.

La aplicación está desarrollada bajo los parámetros de arquitectura limpia, cuenta con los siguientes módulos:  Dominio, infraestructura y presentación. 

Cada uno de los módulos cuenta con sus respectivas pruebas, las cuales menciono a continuación: 

El dominio cuenta con pruebas unitarias, las cuales validan el comportamiento de los diferentes flujos que pueden tener el método del caso de uso.

La infraestructura cuenta con pruebas de integración las cuales validan la integración con las APIs remotas de Mercado libre.

Por último el módulo de presentación tiene las pruebas UI, las cuales permiten validar el comportamiento gráfico de la aplicación.

Entre las librerías utilizadas podemos encontrar

* AppCenter
* Retrofit
* Mockito
* Junit
* Gson
* Espresso
* Hilt
