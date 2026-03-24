# TDSE_PARCIAL

En este repositorio se encuentra los servicios para la Realizacion de la secuencia de Tribonacci:
- Esta el servico de **Math** que se encarga de realizar la secuencia y exponerla en el endpoint **/tribonacci**, para este servicio se necesita un valor **n** el cuál es el índice hasta donde debe ir dicha secuencia.
- En el servicio de **ProxyServer** se encuentra la lógica para la lectura de activo-pasivo de las dos instancias de math. Ademas que cuenta con un formulario básico en HTML, para la facilidad de uso del usuario.

## Como usar?

Para el uso de esto, podemos realizarlo de manera local o en la nube. A continuación se explicara las dos maneras:

### Local 

- 1. Clonar el repositorio y entrar a la carpeta correspondiente, esto se realiza, mediante los siguientes comandos:
    - git clone https://github.com/Karol2905/TDSE_PARCIAL
    - cd TDSE_PARCIAL
- 2. Debemos correr las instancias tanto de proxy como de math
- 3. Debemos modificar el archivo index.html para colocarlo en direccion local, este archivo esta en la ruta : **"TDSE_PARCIAL\ProxyServer\src\main\resources\static\index.html"**
Ahi cambiamos la linea **xhttp.open("GET", "http://3.91.213.89:8087/tribonacci?n="+ n);** de tal manera, que nos quede así -> **xhttp.open("GET", "http://localhost:8087/tribonacci?n="+ n);**
(El puerto se puedo cambiar a preferencia, desde el application.properties de ProxyServer)

- 4. Ahora ya podemos probarlo, para esto gracias al formulario que se realizo solo es necesario entrar a:
http://localhost:8087/

![alt text](/images/image.png)

Ahora podemos digitar el número deseado y obtendremos la secuencia


De igual manera podemos correr sola la instancia  de math y probarlo asi:
http://localhost:8080/tribonacci?n=5

![alt text](/images/local1.png)


