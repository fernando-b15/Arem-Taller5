# Arem-Taller5

Este taller fue hecho para comprender el uso y las funcionalidades de los dockers ,ademas entender el funcionamiento de los balanceadores round robin ,como tambien entender como se puede montar una arquitectura de servicios contenidos en dokers y desplegarlos en un ambiente  cloud en este caso Aws,en este taller cuenta con tres log services cada uno almacenados en dockers independientes  y estos log services a su vez se comunican con otro docker que contiene la base de datos mongoDB que es donde se almacenan los logs y los log services insertan los nuevos logs y consulta los logs registrados y selecciona los ultimos 10 insertados y se los retorna al docker que contiene el balancedador round robin y este balanacedor se encarga de balancear las cargas de trabajo de los log services, ya que despues de cada operacion ya sea de consulta o de insercion cambia a otro log service y asi sucesivamente va enviadoles peticiones a tres log services y por ultimo se llevaron los 5 dockers a una instancia ec2 de aws y se comprobo su funcionamiento de esta arquitectura de dockers de registro y consulta de logs desde la la ip publica de la instacia ec2 de aws

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

Posteriormente desde linea comandos ingrese al directorio de la aplicacion con el siguinete comando :

~~~
cd Arem-Taller2
~~~

![image3](https://github.com/fernando-b15/Arem-Taller2/blob/master/img/clone.PNG)

Ahora proceda primero a entrar al directorio que contiene el servicio LogSercice:

~~~
cd Log Service
~~~

Despues se realiza la compilacion y empaquetacion del  servicio log service con el siguinte comando:

~~~
cd package
~~~

Pero adicionalmente vuelva al directorio principal de la aplicacion e ingrese al directorio que contiene el servicio Balanceador Round Robin:

~~~
cd BalanceadorRoundRobin
~~~

Despues se realiza la compilacion y empaquetacion del  servicio Balanceador Round Robin con el siguinte comando:

~~~
mvn package
~~~


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

Asi podemos ver que se desplegaron localmente los 5 docker ,los cuales 1 corresponde a la base de datos mongoDB,otro al balanceador y los tres ultimos corresponden a los tres logservices:   

![image5](https://github.com/fernando-b15/Arem-Taller2/blob/master/img/web1.PNG)

Ahora ya podemos ver que podemos hacer uso de los servicios del log localmente desde nuestro en localhost en el buscador como se vera a continuacion:

# Informe del taller

Para conocer mas a fondo el desarrollo del taller acceda al [Informe](https://github.com/fernando-b15/Arem-Taller2/blob/master/Arem_Taller2.pdf)

# Licencia

La aplicacion cuenta con la siguiente [MIT LICENCE](https://github.com/fernando-b15/Arem-Taller5/blob/master/LICENSE) 

# Autor

   * [Fernanado Barrera Barrera](https://github.com/fernando-b15) :guitar:
   
