# EntregasLaJustaAPI
Se diseño y desarrollo un sistema de entregas o repartos de pedidos para La Justa (comercializadora universitaria 
que promociona un compre de proximidad de alimentos y otros bienes elaborados por productores locales) que funciona 
como sistema independiete, pero se comunica con el sistema de compras.

# Consignas cumplidas en el desarrollo del proyecto:

PRIMERA PARTE:

De acuerdo al documento de análisis y al prototipo del “Sistema de repartos de
pedidos para la comercializadora La Justa” acordados, se
desea realizar para esta segunda etapa, la modelización de los objetos pertenecientes a la
capa de la lógica de negocio. Esta modelización debe contemplar toda la información que
resulte necesaria para lograr la funcionalidad completa del sistema.


SEGUNDA PARTE:

1) Para el desarrollo de la capa de persistencia del sistema, deberán utilizar:
- el patrón de diseño DAO (Data Access Object)
- el framework Hibernate, implementación del estándar JPA (Java Persistence API)
2) Para esta entrega se deberán desarrollar:
- las 4 operaciones de ABM: alta, baja, modificación y recuperación sobre todas las
entidades. Asimismo se valorará el desarrollo de operaciones sobre entidades
complejas, que contengan atributos de tipo colección.
- un conjunto de casos de prueba que invoquen estas operaciones provistas por los
DAOs, con el propósito de probar el funcionamiento de las operaciones
implementadas. Estos casos de prueba pueden ser escritos en un método “main” o en
un servlet.

TERCERA PARTE:

De acuerdo al documento de análisis y al prototipo del Sistema de repartos de pedidos
para la comercializadora “La Justa” acordados en la primera entrega, y al enunciado
publicado, se desea desarrollar parcialmente la API REST del sistema usando Jersey.
Específicamente para esta entrega se debe implementar una API REST para los siguientes
servicios:
- ABM y listado de repartidores, incluyendo el tipo de transporte que utiliza.
- Listar los pedidos no entregados del sistema de compras de La Justa, recuperando la
información a través de su API. Incluir un filtro opcional por fecha de pedido. 
Requerimientos para el desarrollo de la API REST:
1. Integración con la capa de persistencia desarrollada en la entrega anterior.
2. Uso del formato JSON para el envío y recepción de datos en los casos que sea
necesario.
3. Utilización de framework HK2 para la inyección del EntityManager en cada uno de los
DAOs con un alcance a nivel requerimiento (RequestScoped).
4. Usar Swagger para documentar la API y probar los servicios desarrollados.

CUARTA PARTE:

Se espera que al finalizar esta entrega se cuente una versión funcionalmente completa
del “Sistema de repartos de pedidos para la comercializadora La Justa”. Para ello
esta entrega está organizada en 3 etapas.
Los objetivos de esta entrega son:

- Desarrollar la capa de presentación del sistema en Angular: etapa 1, etapa 2 y etapa 3.
- Completar la capa de servicios REST: etapa 3.
- Integrar la capa de presentación en Angular a la de servicios REST: etapa 3.

El objetivo de las etapas 1 y 2 es desarrollar en Angular la capa de presentación de:

- ABM y listado de repartidores, incluyendo el tipo de transporte que utiliza.
- Asignar entregas a los repartidores (solo para grupos de 2 integrantes).

Etapa 1 (Con datos de prueba):
Con el propósito de facilitar las pruebas debe crear una clase llamada RepartidoresMock
que emule la capa de servicios. Esta clase contendrá el listado de repartidores en una
propiedad estática (variable de clase) con métodos estáticos de consulta y modificación.
1. Cree un proyecto Angular con la herramienta angular-cli.
2. Genere una pantalla home que contiene el menú completo de funcionalidades
(aunque no todas desarrolladas), según la especificación funcional del sistema.
3. Implemente el listado de repartidores. La funcionalidad debe estar accesible desde el
menú.
4. Diseñe e implemente el alta de un repartidor. La funcionalidad debe estar accesible
desde el menú.
5. Diseñe e implemente el detalle de un repartidor.
6. Diseñe e implemente la baja de un repartidor.
7. Diseñe e implemente la asignación de entregas a repartidores.

Etapa 2 (Con capa de servicios y conexión a servicios REST):
1. Genere un servicio de nombre RepartidorService con la misma funcionalidad de
RepartidoresMock, pero implementado mediante propiedades y métodos de instancia.
2. Modifique los componentes Angular generados en la Etapa 1, de manera que utilicen
RepartidorService mediante inyección de Angular.
3. Modifique el servicio RepartidorService para que acceda a los servicios REST.

En esta etapa deberá entregarse la implementación del servicio REST pendiente: 
“Listar los pedidos no entregados del sistema de compras de La Justa,
recuperando la información a través de su API”

Etapa 3 (final):
El objetivo de esta entrega es desarrollar el sistema completo, incluyendo la vista y su
integración con la capa de servicios.
El sistema completo debe quedar publicado y funcionando en el servidor Tomcat de la
cátedra (Java y Aplicaciones Avanzadas sobre Internet).



