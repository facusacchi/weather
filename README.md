- Facundo Sacchi
- faq.ndo.n.s@gmail.com

# Configuracion del proyecto con Eclipse

 1. Clonar el proyecto.
 2. Instalar dependencias: Click derecho en el proyecto, Maven, Update project.
 3. Habilitar anotaciones para lombok: Click derecho en el proyecto, Properties, Java Compiler, Annotation Processing, aquí checkear la opcion "Enable annotation processing".
 4. Levantar server local: Click derecho en el archivo WeatherAplication, Run as, Java application. El servidor levantará en el puerto 8080.
 5. Abrir un navegador y entrar a la siguiente url: http://localhost:8080/swagger-ui/

Para este momento ya estará lista la ui de swagger para probar la api.

# Arquitectura
La arquitectura planteada es una arquitectura en capas donde el controller expone la api a consuir como entry point a nuestro sistema. El service es la capa de lógica de negocio y también orquesta los llamados hacia un segundo nivel de servicio (http) y al repository. El repository encapsula los llamados de acceso a datos.

![image](https://github.com/facusacchi/weather/assets/62066887/0a68ddf6-ee4c-4e40-8205-b94c942bb06a)

# Diagramas de Secuencia
Aquí se plantean los diagramas de secuencia para los casos posibles de toda la casuística:

![image](https://github.com/facusacchi/weather/assets/62066887/7b7d8d3e-c207-4da8-80f4-ecbc10e7faa7)
![image](https://github.com/facusacchi/weather/assets/62066887/4bccfb35-dd1e-4554-b815-ea01d7c7954f)
![image](https://github.com/facusacchi/weather/assets/62066887/cb9d5bd5-392c-4ec0-8f63-7b49980a994b)
![image](https://github.com/facusacchi/weather/assets/62066887/5296d319-8486-4f98-ac69-2d09c2e66bc1)



