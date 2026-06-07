Hola, esto va para Fernando que probablemente no ha tocado un repositorio en su vida.

# Como trabajar en paralelo usando Git
Si te encuentras en una situacion de escasez neuronal por el consumo excesivo de TikTok y no sabes cómo se trabaja en paralelo en un mismo proyecto usando git y github pues sigue leyendo (subnromal).

---

## PreRequisitos
* Tener instalado Git (obligatorio)
* Tener cuenta en GitHub (https://guthib.com/)
* Tener una consola (obligatorio)
* Tener el Intellij IDEA (o cualquier otro)
* Saber pensar (opcional)

---

## Funcionamiento
En git, para que cada <a href="https://tenor.com/es/view/davopuerro-gif-15134701544029671534">programador programe</a> y las cosas no se vayan al caño se han inventado cositas llamadas ramas o "branches" que te permiten trabajar en lo que tu quieras en el proyecto sin tocar el trabajo que hacen los demás. <br>

Al crear un repositorio se crea la rama principal, en nuestro caso es la rama <b>"main"</b>, esta rama es la version final del proyecto y para nada se puede trabajar sobre ella, solo se suben aquí los cambios despúes de haber sido probados y ver que funcionen bien, para ello se generan ramas paralelas a la principal, donde puedes hacer el cagadón que tu quieras sin dañar la versión final.

<img src="https://res.cloudinary.com/snyk/images/f_auto,q_auto/w_1240,h_384,c_scale/v1/wordpress-sync/image1-11/image1-11-1240x384.png">

He aquí una representación gráfica para que tu cerebro no se queme.

---

### Ramas del proyecto
Las ramas que se van a utilizar en el proyecto son las siguientes:
 
``` text
main (Rama principal)
├── inventario (Alessandro)
├── pagos (Fernando)
├── infraestructura (Enzo)
```

Cada quien trabaja sobre su propia rama, para nada del mundo se atrevan a usar la rama principal u otra rama que no sea la suya.

---

### Configuración inicial
#### Paso 01: Clonar el repositorio
> [!IMPORTANT]
> Esto solo lo tendrán que hacer una vez, al inicio, no se les vaya a ocurrir hacer esto más de una vez así que más les vale hacerlo bien.

En un cmd abierto, en la carpeta de su preferencia ejecutan el siguiente comando:
```bash
git clone https://github.com/aleslul/ecommerce-patrones.git
```

Luego, en la misma consola, ingresan al proyecto ejecutando:
```bash
cd ecommerce-patrones
```

#### Paso 02: Ver las ramas disponibles
Las ramas ya las creé con anticipación, para ver que ramas existen en el repositorio ejecutan este comando en consola:
```bash
git branch -a
```
Les tendría que salir algo como:
```text
origin/main
origin/inventario
origin/pagos
origin/infraestructura
```
---
#### Paso 03: Cambiar de rama
Para seleccionar la rama en la que van a trabajar ejecutan el siguiente código en consola:
```bash
git checkout NOMBRE_DE_LA_RAMA
```
---
Para Fernando:
```bash
git checkout pagos
```

Para Enzo:
```bash
git checkout infraestructura
```
---
Pueden verificar la rama ejecutando:
```bash
git branch
```
y les tendría que salir algo como:
```text
* pagos
```
Por poner un ejemplo.

---

### Flujo de trabajo diario
Una vez que hicieron todo lo anterior ya pueden abrir el proyecto en el IDE de su preferencia y para su trabajo diario tienen que seguir estos pasos:
#### 1. Actualizar la rama antes de comenzar

Cada vez que quieras empezar a trabajar (Ojala que trabajasen malditos putos) tienen que ejecutar en la consola:

```bash
git pull
```

Esto descarga los cambios más recientes de la rama remota, es decir estas actualizao, pero solo en tu rama.

> Si ves cambios en la rama principal (main) que no tienes, ejecuta esto siempre, antes de programar y despues del pull:

```bash
git merge main
```

Esto hace que los cambios más actuales del main se descarguen en tu rama, asegurate de estar en tu rama tambien.

---

#### 2. Programar normalmente

Aqui preguntas a chagepete o a clauded o a gemini o miras en stackoverflow (porque w?) y programas todo lo que tengas que programar.

---

#### 3. Revisar archivos modificados
Para que veas que archivos has modificado (por si no sabes ni que has hecho), ejecutas:
```bash
git status
```

---

#### 4. Agregar cambios
Agregas todos los cambios que hiciste "programando" ejecutando:
```bash
git add .
```

---

#### 5. Crear commit

El mensaje debe describir claramente el cambio realizado.

Ejemplos:

```bash
git commit -m "Implementado Builder para pedidos"
```

```bash
git commit -m "Agregado Adapter para pagos con Yape"
```

```bash
git commit -m "Creado Facade para proceso de compra"
```

Por favor escriban mensajes normales TT

---

#### 6. Subir cambios
Finalmente ejecutas: 
```bash
git push
```

Así subes todos los cambios que realizaste listos para que yo (Alessandro) los revise, te de feedback retroalimentativo (te puteo), lo arregles siguiendo los mismos pasos anteriores (le lloras a chatgpt) y así hasta que esté bien hecho para que yo lo pueda incluir en la rama principal.

---

#### 7. Pull Request
Si bien con el git push tus cambios ya estan en github, todavía no estan en el main, es decir que no estan en el producto final, pero tampoco puedes agregarlos tu al main porque yo se que eres una persona sin cabeza que probablemente hizo todo mal, es ahí donde entra el Pull Request.

---
7.1. ¿Cuándo hacer un pull request?

No, no tienes que hacer siempre un pull request no seas mongol yo no quiero revisar tus wbdas cada 3 horas.
    
* <b>Crear Pull Request cuando: </b>
  *   La funcionalidad asignada está terminada.
  * El proyecto compila correctamente.
  * Los cambios fueron probados.
  * Ya se realizó git push.

Ejemplos:

✅ Builder de Pedido terminado.

✅ Adapter para Yape terminado.

✅ Facade de Compra terminado.

---
* <b>NO crear pull request cuando: </b>
  * Solo se ha avanzado una pequeña parte.
  *  El código tiene errores de compilación.
  *  La funcionalidad está incompleta.
  *  Se está experimentando o probando ideas.

Ejemplos:

❌ Solo creé las clases.

❌ El programa todavía no funciona.

❌ Falta terminar el patrón.

❌ Si tu código no compila.

❌ Si el programa lanza 84 errores.



---

7.2. ¿Cómo hacer pull request?

Fasilisimo, te vas al repositorio en github ahi en internet despues de hacer tu push, te vas a tu rama y ahi te sale un boton en amarillo que dice: Compare & Pull Request y le picas ahí xd.

Si por alguna razon no te sale te vas a

```text
Pull request
↓
New Pull Request
```
---
Desues de eso tienes que seleccionar las ramas, github te mostrara
```text
base: main
compare: pagos
```
Que significa practicamente: Quiero llevar cambios de pagos hacia main.

Luego escribes la descripccion, que es, escribir una descripccion de lo que hiciste xd y finalmente le das a donde dice "Create Pull Request" y ya, tu chamba termina ahi y yo vere si te acepto lo que hiciste o no

--- 

7.3 ¿Qué pasa cuando Alessandro acepta un Pull Request?

Cuando yo haga merge de cambios a main, ustedes tienen que actualizar su rama, ejecutando:

```bash
git checkout NOMBRE_DE_SU_RAMA_ASIGNADA
git pull
```

Así tendrán la versión más reciente del proyecto.

Si algo explota, me avisan
Si no explota, sigan programando

## Integración de funcionalidades

Cuando una funcionalidad esté terminada:

1. Subir todos los cambios a la rama correspondiente.
2. Informar al responsable de integración (osea a mi xdxd)
3. Crear un Pull Request hacia la rama `main`. (Solo si terminaste funcionalidades importantes)
4. Esperar revisión antes de realizar el merge. (dejamelo a mi papi)

---

## Reglas importantes

### NO trabajar en main

Incorrecto:

```bash
git checkout main
```

```bash
git add .
git commit -m "cambios"
git push
```

La rama main solo se utilizará para integrar funcionalidades terminadas, NO TRABAJES EN EL PUTO MAIN.

---

### Archivos que NO se suben

Si ves alguno de estos archivos:

```text
.idea/
.vscode/
out/
target/
*.class
```

No los subas directamente

Ya existe un archivo llamado `.gitignore` que se encarga de eso, tampoco toques el gitignore.

### Hacer commits frecuentes

Incorrecto:

```text
Trabajar durante 5 días sin hacer commits.
```

Correcto:

```text
Realizar commits pequeños y frecuentes.
```

> [!WARNING] No te confundas entre commit y pull request, que ya te vi Fernando.

---

### No modificar módulos ajenos

Cada integrante debe trabajar únicamente en los módulos asignados.

Si es necesario modificar código de otro integrante, primero coordinar con él.

---

### Antes de terminar el día

Ejecutar siempre:

```bash
git status
```

Si existen cambios sin guardar:

```bash
git add .
git commit -m "avance del día"
git push
```

Nunca dejar varios días de trabajo sin subir al repositorio

---

# LEEME FORRO:
## Ayuda, Git explotó

Si ejecutas algo raro y no sabes qué está pasando HAZ ESTO YA:

```bash
git status
```

Haz captura de pantalla.

Mándamela.

NO ejecutes comandos aleatorios que te da ChatGPT sin entenderlos.

NO hagas:

```bash
git reset --hard
```

```bash
git push --force
```

```bash
git branch -D
```

a menos que yo te lo diga

---

# Comandos que usarás el 90% del tiempo

```bash
git pull
git status
git add .
git commit -m "mensaje"
git push
```
