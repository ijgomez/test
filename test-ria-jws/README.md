

COMANDO PARA GENERAR CERTIFICADO PARA FIRMAR LA APLICACION JNLP
-------------------------------------------------------------------------------------------------------------------------------------------------------
D:\>keytool -genkey -keystore testKeystore.jks -alias publisher
Introduzca la contraseña del almacén de claves: temporal
Volver a escribir la contraseña nueva: temporal
¿Cuáles son su nombre y su apellido?
  [Unknown]:  John Smith
¿Cuál es el nombre de su unidad de organización?
  [Unknown]:  The Unit
¿Cuál es el nombre de su organización?
  [Unknown]:  The Organization
¿Cuál es el nombre de su ciudad o localidad?
  [Unknown]:  City
¿Cuál es el nombre de su estado o provincia?
  [Unknown]:  State
¿Cuál es el código de país de dos letras de la unidad?
  [Unknown]:  US
¿Es correcto CN=John Smith, OU=The Unit, O=The Organization, L=City, ST=State, C=US?
  [no]:  si

Introduzca la contraseña de clave para <publisher>
        (INTRO si es la misma contraseña que la del almacén de claves): temporal
Volver a escribir la contraseña nueva: temporal


URLs
-------------------------------------------------------------------------------------------------------------------------------------------------------
https://docs.oracle.com/javase/tutorial/deployment/deploymentInDepth/deployingWithoutCodebase.html

https://docs.oracle.com/javase/tutorial/deployment/deploymentInDepth/createWebStartLaunchButtonFunction.html

https://docs.oracle.com/javase/8/docs/technotes/guides/deploy/deployment_toolkit.html#BABDJDEC

http://www.mojohaus.org/webstart/webstart-maven-plugin/faq.html

https://stackoverflow.com/questions/19659134/how-do-i-fix-missing-codebase-permissions-and-application-name-manifest-attri

https://gist.github.com/cemartins/dd10d2a666322bfe87d3