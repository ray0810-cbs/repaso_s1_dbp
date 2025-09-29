#!/usr/bin/env python3
import os
import sys
from pathlib import Path
import re


def find_base_dir():
    """Encuentra la ruta base del proyecto (donde está src/main/java)"""
    current_dir = Path.cwd()

    while current_dir != Path('/'):
        if (current_dir / 'src' / 'main' / 'java').exists():
            return current_dir
        current_dir = current_dir.parent

    return None


def find_package_name(java_dir):
    """Encuentra el nombre del paquete base (por ejemplo, com.example.petWorldTest)"""
    java_dir_path = Path(java_dir)

    # Primero encontrar la carpeta 'com'
    com_dir = java_dir_path / 'com'
    if not com_dir.exists():
        return None

    # Luego construir el nombre del paquete explorando la estructura
    package_parts = ['com']
    current_dir = com_dir

    while True:
        subdirs = [d for d in current_dir.iterdir() if d.is_dir()]
        if not subdirs:
            break

        # Tomar el primer subdirectorio
        subdir = subdirs[0]
        package_parts.append(subdir.name)
        current_dir = subdir

        # Verificar si ya llegamos a la carpeta donde están application, domain, etc.
        if any((subdir / folder).exists() for folder in ['application', 'domain', 'infrastructure', 'service']):
            break

    return '.'.join(package_parts)


def create_domain_class(package_name, entity_name, base_path):
    """Crea la clase de dominio"""
    domain_dir = base_path / 'domain'
    os.makedirs(domain_dir, exist_ok=True)

    file_path = domain_dir / f"{entity_name}.java"

    content = f"""package {package_name}.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "{entity_name.lower()}")
@Getter
@Setter
@NoArgsConstructor
public class {entity_name} {{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // === Attributes and Constructor === 
}}
"""

    with open(file_path, 'w') as f:
        f.write(content)

    return file_path


def create_repository(package_name, entity_name, base_path):
    """Crea el repositorio"""
    infra_dir = base_path / 'infrastructure'
    os.makedirs(infra_dir, exist_ok=True)

    file_path = infra_dir / f"{entity_name}Repository.java"

    content = f"""package {package_name}.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import {package_name}.domain.{entity_name};

public interface {entity_name}Repository extends JpaRepository<{entity_name}, Long> {{}}
"""

    with open(file_path, 'w') as f:
        f.write(content)

    return file_path


def create_service(package_name, entity_name, base_path):
    """Crea el servicio"""
    service_dir = base_path / 'service'
    os.makedirs(service_dir, exist_ok=True)

    file_path = service_dir / f"{entity_name}Service.java"

    content = f"""package {package_name}.service;

import {package_name}.domain.{entity_name};
import {package_name}.infrastructure.{entity_name}Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class {entity_name}Service {{
    private final {entity_name}Repository {entity_name}Repository;

    @Autowired
    public {entity_name}Service({entity_name}Repository {entity_name.lower()}Repository) {{
        this.{entity_name}Repository = {entity_name}Repository;
    }}
    
    // === Functions of service ===
}}
"""

    with open(file_path, 'w') as f:
        f.write(content)

    return file_path


def create_controller(package_name, entity_name, base_path):
    """Crea el controlador"""
    app_dir = base_path / 'application'
    os.makedirs(app_dir, exist_ok=True)

    file_path = app_dir / f"{entity_name}Controller.java"

    content = f"""package {package_name}.application;

import {package_name}.domain.{entity_name};
import {package_name}.service.{entity_name}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{entity_name.lower()}")
public class {entity_name}Controller {{
    private final {entity_name}Service {entity_name}Service;

    @Autowired
    public {entity_name}Controller({entity_name}Service {entity_name.lower()}Service) {{
        this.{entity_name}Service = {entity_name}Service;
    }}

    // === Endpoints ===
}}
"""

    with open(file_path, 'w') as f:
        f.write(content)

    return file_path


def main():
    # Encuentra la ruta base
    base_dir = find_base_dir()
    if not base_dir:
        print("Error: No se pudo encontrar la estructura src/main/java")
        sys.exit(1)

    java_dir = base_dir / 'src' / 'main' / 'java'

    # Encuentra el nombre del paquete
    package_name = find_package_name(str(java_dir))
    if not package_name:
        print("Error: No se pudo determinar el nombre del paquete base")
        sys.exit(1)

    # Solicitar el nombre de la entidad
    entity_name = input("Introduce el nombre del servicio/entidad (ej. Student): ").strip()

    # Validar el nombre (debe empezar con mayúscula y ser alfanumérico)
    if not re.match(r'^[A-z][a-zA-Z0-9]*$', entity_name):
        print("Error: El nombre debe contener solo letras y números")
        sys.exit(1)

    # Construir la ruta base para los archivos
    package_path = package_name.replace('.', '/')
    base_path = java_dir / Path(package_path)

    # Crear las plantillas
    domain_file = create_domain_class(package_name, entity_name, base_path)
    repo_file = create_repository(package_name, entity_name, base_path)
    service_file = create_service(package_name, entity_name, base_path)
    controller_file = create_controller(package_name, entity_name, base_path)

    print(f"\nArchivos creados exitosamente:")
    print(f"- Dominio: {domain_file}")
    print(f"- Repositorio: {repo_file}")
    print(f"- Servicio: {service_file}")
    print(f"- Controlador: {controller_file}")


if __name__ == "__main__":
    main()