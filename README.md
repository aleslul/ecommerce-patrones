# E-Commerce - Patrones de Diseño de Software

Aplicación de consola en Java que simula un sistema de comercio electrónico, desarrollada con el objetivo de aplicar patrones de diseño GoF (Gang of Four) en un contexto real y cohesivo.

---

## Descripción

El sistema permite gestionar usuarios, productos, carritos de compra, pedidos y facturación desde una interfaz de consola. Cuenta con dos roles: **Cliente** y **Administrador**, cada uno con sus propios menús y permisos. El proyecto integra **10 patrones de diseño** distribuidos en las categorías creacional, estructural y de comportamiento.

---

## Patrones de Diseño Implementados

### Creacionales

| Patrón | Dónde se aplica |
|---|---|
| **Singleton** | `Configuracion` (IGV, tipo de cambio) y `SesionActual` (usuario autenticado) |
| **Abstract Factory** | `FactoryComprobante` → genera `Boleta` o `Factura` según el tipo de cliente |
| **Factory Method** | `FactoryPago` → crea instancias de `PagoTarjeta`, `PagoYape` o `PagoPlin` |
| **Builder** | `ProductoBuilder` → construcción fluida de productos con atributos opcionales |
| **Prototype** | `ProductoPrototype` → clonación de productos existentes |

### Estructurales

| Patrón | Dónde se aplica |
|---|---|
| **Bridge** | `MetodoPago` (abstracción) + `Moneda` (implementor) → desacopla método de pago de la moneda (Soles / Dólares) |
| **Decorator** | Añade información de `Correo` o `Teléfono` a comprobantes; también extiende reportes con logs y resúmenes |
| **Facade** | `SeguridadFacade` → simplifica el flujo de login (validación, autenticación, inicio de sesión) |
| **Proxy** | `InventarioProxy` y `PedidoProxy` → control de acceso por rol antes de delegar al servicio real |

### De Comportamiento

| Patrón | Dónde se aplica |
|---|---|
| **Observer** | `InventarioSubject` notifica a `InventarioConcreteObserver` y `BuzonAlertas` cuando el stock de un producto es bajo |

---

## Estructura del Proyecto

```
src/
├── model/                  # Entidades del dominio (Producto, Usuario, Pedido, Carrito…)
├── patrones/
│   ├── abstract_factory/   # Generación de comprobantes (Boleta / Factura)
│   ├── bridge/             # Métodos de pago con soporte multidivisa
│   ├── builder/            # Construcción de productos
│   ├── decorator/          # Extensión de comprobantes y reportes
│   ├── facade/             # Flujo de autenticación simplificado
│   ├── factory/            # Creación de métodos de pago
│   ├── observer/           # Alertas de inventario
│   ├── prototype/          # Clonación de productos
│   ├── proxy/              # Control de acceso a inventario y pedidos
│   └── singleton/          # Configuración global y sesión activa
├── presentation/           # Menús de consola (Login, Cliente, Admin)
├── repository/             # Acceso a datos (interfaces + implementaciones en memoria)
├── service/                # Lógica de negocio
└── Main.java               # Punto de entrada
```

---

## Tecnologías

- **Java** (sin frameworks externos)
- **Arquitectura en capas**: Presentation → Service → Repository → Model
- **Almacenamiento**: en memoria (listas Java), sin base de datos

---

## Cómo ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/aleslul/ecommerce-patrones.git
   ```
2. Abrir el proyecto en IntelliJ IDEA u otro IDE compatible con Java.
3. Compilar y ejecutar `Main.java`.

> No se requieren dependencias externas ni configuración adicional.

---

## Roles y funcionalidades

### Cliente
- Iniciar sesión / registrarse
- Explorar catálogo y agregar productos al carrito
- Realizar pedidos y elegir método de pago (Yape, Plin, Tarjeta) y moneda (Soles / Dólares)
- Solicitar boleta o factura al finalizar la compra
- Consultar historial de pedidos propios

### Administrador
- Todo lo anterior
- Gestionar productos (crear, editar, eliminar)
- Consultar y actualizar estado de todos los pedidos
- Ver reportes de pagos, logs y resumen de ventas
- Recibir alertas de stock bajo

---

## Configuración global (Singleton)

El sistema arranca con los siguientes valores predeterminados gestionados por `Configuracion.getInstance()`:

| Parámetro | Valor por defecto |
|---|---|
| IGV | 18% |
| Tipo de cambio | S/ 3.80 por dólar |
| Moneda por defecto | Soles |

---

## Integrantes

- **Alessandro Becerra Montrengro**
- **Enzo Giovanny Becerra Montejo**
- **Santiago Benjamin Huamán Reyes**
- **Carlos Wilmer Martinez Cruz**
- **Fernando Jacob Wong Diaz**