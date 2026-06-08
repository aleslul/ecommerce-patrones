# Sistema de Gestión de Tienda Online - Patrones de Diseño de Software

## Descripción

Este proyecto consiste en el desarrollo de un sistema de gestión de tienda online implementado en Java mediante una aplicación de consola. El objetivo principal es aplicar diversos patrones de diseño de software estudiados durante el curso de Patrones de Diseño de Software y Arquitectura, siguiendo los principios de programación orientada a objetos y buenas prácticas de desarrollo.

El sistema permitirá gestionar productos, inventario, carritos de compra, pedidos y métodos de pago, simulando el funcionamiento básico de una plataforma de comercio electrónico. La persistencia de datos se realizará utilizando estructuras de datos en memoria, principalmente ArrayList, sin emplear sistemas gestores de bases de datos.

## Objetivos

### Objetivo General

Desarrollar una aplicación de comercio electrónico en consola aplicando múltiples patrones de diseño para mejorar la mantenibilidad, escalabilidad y reutilización del software.

### Objetivos Específicos

* Aplicar patrones creacionales para la creación flexible de objetos.
* Aplicar patrones estructurales para mejorar la organización del sistema.
* Implementar una arquitectura en capas que facilite el mantenimiento del proyecto.
* Gestionar productos, pedidos y pagos mediante estructuras orientadas a objetos.
* Utilizar Git y GitHub para el control de versiones y trabajo colaborativo.

## Funcionalidades Principales

* Registro y gestión de productos.
* Administración de inventario.
* Gestión de categorías de productos.
* Carrito de compras.
* Generación de pedidos.
* Procesamiento de pagos.
* Gestión de usuarios y permisos.
* Generación de comprobantes y reportes.
* Simulación de compra completa desde consola.

## Patrones de Diseño Implementados

### Patrones Creacionales

* Factory Method
* Abstract Factory
* Singleton
* Builder
* Prototype
* Object Pool

### Patrones Estructurales

* Adapter
* Bridge
* Composite
* Decorator
* Facade
* Proxy

## Arquitectura del Proyecto

El sistema sigue una arquitectura en capas para separar responsabilidades y facilitar la evolución del software.

```text
Presentación (Consola)
        ↓
Servicios (Lógica de Negocio)
        ↓
Repositorios (Persistencia)
        ↓
Colecciones (ArrayList)
```

## Estructura del Proyecto

```text
src/
├── presentation/
├── model/
├── service/
├── repository/
├── patrones.factory/
├── patrones.builder/
├── patrones.prototype/
├── patrones.adapter/
├── patrones.bridge/
├── patrones.composite/
├── patrones.decorator/
├── patrones.facade/
├── patrones.proxy/
├── patrones.singleton/
├── patrones.pool/
└── Main.java
```

## Tecnologías Utilizadas

* Java
* Git
* GitHub
* UML

## Integrantes

* Becerra Montejo Enzo
* Becerra Montenegro Alessandro
* Huaman Reyes Santiago Benjamin
* Martinez Cruz Carlos Wilmer
* Wong Diaz Fernando Jacob


