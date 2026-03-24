# TDSE_PARCIAL

En este repositorio se encuentra los servicios para la Realizacion de la secuencia de Tribonacci:
- Esta el servico de **Math** que se encarga de realizar la secuencia y exponerla en el endpoint **/tribonacci**, para este servicio se necesita un valor **n** el cuál es el índice hasta donde debe ir dicha secuencia.
- En el servicio de **ProxyServer** se encuentra la lógica para la lectura de activo-pasivo de las dos instancias de math. Ademas que cuenta con un formulario básico en HTML, para la facilidad de uso del usuario.

## Como usar?

Para el uso de esto, podemos realizarlo de manera local o en la nube. A continuación se explicara las dos maneras:

### Local 

1. Clonar el repositorio y entrar a la carpeta correspondiente, esto se realiza, mediante los siguientes comandos:
    - git clone https://github.com/Karol2905/TDSE_PARCIAL
    - cd TDSE_PARCIAL
2. Debemos correr las instancias tanto de proxy como de math
3. Debemos modificar el archivo index.html para colocarlo en direccion local, este archivo esta en la ruta : **"TDSE_PARCIAL\ProxyServer\src\main\resources\static\index.html"**
Ahi cambiamos la linea **xhttp.open("GET", "http://3.91.213.89:8087/tribonacci?n="+ n);** de tal manera, que nos quede así -> **xhttp.open("GET", "http://localhost:8087/tribonacci?n="+ n);**
(El puerto se puedo cambiar a preferencia, desde el application.properties de ProxyServer)

4. Ahora ya podemos probarlo, para esto gracias al formulario que se realizo solo es necesario entrar a:
http://localhost:8087/

![alt text](/images/image.png)

Ahora podemos digitar el número deseado y obtendremos la secuencia


De igual manera podemos correr sola la instancia  de math y probarlo asi:
http://localhost:8080/tribonacci?n=5

![alt text](/images/local1.png)


## Nube

Para poderlo probar usando la nnube en este caso AWs, necesitamos desplegarlo. Para esto podemos seguir los siguientes pasos:

1. Crear 3 Instancias en EC2 (1 para proxy, dos para math)
2. Empaquetar nuestros servicios, podemos ayudarnos de maven usando el comando:
    - mvn package
Esto nos genera el jar, lo realizamos tanto en proxy como en math.
3. Ahora nos conectamos a cada una de nuestras instancias por medio de sftp y subimos nuestro .jar correspondiente segun la instancia.
4. Nos volvemos a conectar pero esta vez desde ssh.
5. Ahora en cada una de nuestras  maquinas, instalamos java por medio del comando:
    - sudo yum remove java-17-amazon-corretto-headless
6. Al momento de ya tener java y nuestros .jar en las 3 instancias, vamos a activar los puertos de seguridad.
    - En proxy necesitamos el puerto 8087
    - En las dos instancias de math necesitamos el puerto 8080  
7. Antes de ejecutarlos, necesitamos en la instancia de proxy, exportar las dos variables de las ips publicas de las dos instancias de math. Esto se hace mediante:
    -  export MATH_SERVICE_1=http://44.201.119.102:8080
    -  export MATH_SERVICE_2=http://54.157.43.100:8080
    (Se debe cambiar las ips, a las dadas en la sinstancias propias, igual que se debe cambiar la ip del proxy en el index.html igual que en local, pero con la ip pública del proxy)
8. Y ahora procedemos a ejecutar estos archivos. Con el siguiente comando:
    - java -jar archivo.jar
9. Para probarlo podemos entrar a:
    - http://3.91.213.89:8087/   (ip del proxy)
    ![alt text](/images/formulario.png)
    - http://44.201.119.102:8080/tribonacci?n=5 (ip instancia de un math)
    ![alt text](/images/instancia.png)
    - http://54.157.43.100:8080/tribonacci?n=5 (ip instancia del otro math)
    ![alt text](/images/instancia2.png)

# Video
https://pruebacorreoescuelaingeduco-my.sharepoint.com/:v:/g/personal/karol_estupinan-v_mail_escuelaing_edu_co/IQB_5CQoJXgnTbOYDe145uJ6AYsPzwZV0bmXyEc7Mah9h-o?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=Opo6C3