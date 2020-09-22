# Arem-Taller5

Este taller fue hecho para comprender el uso y las funcionalidades de los dockers ,ademas entender el funcionamiento de los balanceadores round robin ,como tambien entender como se puede montar una arquitectura de servicios contenidos en dokers y desplegarlos en un ambiente  cloud en este caso Aws,en este taller cuenta con tres log services cada uno almacenados en dockers independientes  y estos log services a su vez se comunican con otro docker que contiene la base de datos mongoDB que es donde se almacenan los logs.

Los log services se encragan de insertar los nuevos logs y consulta los logs registrados en la base de datos y selecciona los ultimos 10 logs insertados y se los retorna al docker que contiene el balancedador round robin y este balanacedor se encarga de balancear las cargas de trabajo de los log services equitativamente , ya que despues de cada operacion ya sea de consulta o de insercion cambia a otro log service y asi sucesivamente va enviadoles peticiones a los tres log services y por ultimo se llevaron los 5 dockers a una instancia ec2 y se comprobo su correcto funcionamiento.

# Pre-Requisitos

Para el uso de la aplicacion se requiere que el computador tenga instalados los siguientes prerequisitos:

   * Java 8
   * Maven
   * Git
   * Docker

# Despliegues

   * Circle Ci LogService
   
   * [![CircleCI](https://circleci.com/gh/fernando-b15/LogService.svg?style=svg&circle-token=ccbceb8116e8666c36b14bb54f9cb7b166f1b68f)](https://app.circleci.com/pipelines/github/fernando-b15/LogService/1/workflows/70c6b01b-02af-42bc-bcf5-6158a5247afd)

   
   * Circle Ci BalanceadorRoundRobin
   * [![CircleCI](https://circleci.com/gh/fernando-b15/BalanceadorRoundRobin.svg?style=svg&circle-token=f58a82718accbf45bc84402705e4e9945ccbeaf1)](https://app.circleci.com/pipelines/github/fernando-b15/BalanceadorRoundRobin/1/workflows/ae061c63-26a3-42b8-bcee-6754b962b0ba) 
   
 # Instalacion

Para comenzar la instalacion porfavor copie el siguiente comando en su linea de comandos :

~~~
git clone https://github.com/fernando-b15/Arem-Taller5
~~~

![image1](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/clone.PNG)

Posteriormente desde linea comandos ingrese al directorio de la aplicacion con el siguinete comando :

~~~
cd Arem-Taller2
~~~
Ahora proceda primero a entrar al directorio que contiene el servicio LogSercice:

~~~
cd LogService
~~~

Despues se realiza la compilacion y empaquetacion del  servicio log service con el siguinte comando:

~~~
mvn package
~~~

![image2](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/packagelog.PNG)

Pero adicionalmente vuelva al directorio principal de la aplicacion e ingrese al directorio que contiene el servicio Balanceador Round Robin:

~~~
cd BalanceadorRoundRobin
~~~

Despues se realiza la compilacion y empaquetacion del  servicio Balanceador Round Robin con el siguinte comando:

~~~
mvn package
~~~

![image3](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/packagebalance.PNG)

# Generacion de Imagenes de containers

El  primer paso para generar las imagenes correspondientes a los container es usar el siguiente comando desde los directorios que contienene el log service y el balancedao round robin:

~~~
docker build --tag logservice .
~~~
![image4](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/imagelogservice1.PNG)
~~~
docker build --tag balanceador .
~~~
![image5](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/imagebalance1.PNG)

Posteriormenete se proceder a asignarle un tag a cada imagen correspondiente al repositiorio docker hub donde se subira la imagen con los siguiente comando:
~~~
docker tag balanceador fernando15/logservice
~~~
![image6](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/imagelogservice2.PNG)
~~~
docker tag balanceador fernando15/balanceadorroundrobin
~~~
![image7](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/imagebalance2.PNGG)

Por ultimo procedemos a realizar el push de cada imagen dentro del repositorio docker hub con el siguiente comando:
~~~
docker push fernando15/logservice:latest
~~~
![image8](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/imagelogservice3.PNG)
~~~
docker push fernando15/balanceadorroundrobin:latest
~~~
![image9](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/imagebalance3.PNG)

# Imagenes de Servicios en Doker Hub

La imagen del servicio log service se encuentra en el siguiente link es:
   * [ImageLogService](https://hub.docker.com/repository/docker/fernando15/logservice)
   * ![image10](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/imagelogservice4.PNG)
    
La imagen del servicio balanceador round robin se encuentra en el siguiente link es:
   * [ImageBalanceRoundRobin](https://hub.docker.com/repository/docker/fernando15/balanceadorroundrobin)
   * ![image11](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/imagebalance4.PNG)

# Pruebas





# Documentacion

Para obtener la documentacion del servicios del log service y el balanceador round robin, ingrese el siguinete codigo en el respectivo directorio que contiene el log service  y tambien en el que contiene el balanceador round robin

~~~
mvn javadoc:javadoc
~~~

![image6](https://github.com/fernando-b15/Arem-Taller2/blob/master/img/javadoc.PNG)

para acceder a la documentacion de la apliacacion ingrese al siguiente enlace [apidocs](https://github.com/fernando-b15/Arem-Taller2/tree/master/apidocs) 

# Ejecucion

Para la ejecucion de toda la arquitectura de servcios dockers se hace desde el directorio principal de la aplicacion, construimos la arquitectura de servicios que incluye los 5 dockers haciendo uso del docker-compose.yml con el siguiente comando:

~~~
docker-compose up -d --scale web=3
~~~

![image12](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/docker-compose1.PNG)

![image13](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/docker-compose2.PNG)

Asi podemos ver que se desplegaron localmente los 5 docker ,los cuales 1 corresponde a la base de datos mongoDB,otro al balanceador y los tres ultimos corresponden a los tres logservices:   

![image14](https://github.com/fernando-b15/Arem-Taller5/blob/master/img/docker-compose2.PNG)


Ahora ya podemos ver que podemos hacer uso de los servicios del log localmente desde nuestro en localhost en el buscador como se vera a continuacion:

# Informe del taller

Para conocer mas a fondo el desarrollo del taller acceda al [Informe](https://github.com/fernando-b15/Arem-Taller2/blob/master/Arem_Taller2.pdf)

# Licencia

La aplicacion cuenta con la siguiente [MIT LICENCE](https://github.com/fernando-b15/Arem-Taller5/blob/master/LICENSE) 

# Autor

   * [Fernanado Barrera Barrera](https://github.com/fernando-b15) :guitar:
   
