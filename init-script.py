#!/usr/bin/env python3
import os
import sys
from pathlib import Path


def find_base_dir():
    """Encuentra la ruta base del proyecto (donde está src/main/java)"""
    current_dir = Path.cwd()

    while current_dir != Path('/'):
        if (current_dir / 'src' / 'main' / 'java').exists():
            return current_dir
        current_dir = current_dir.parent

    return None


def find_java_package_dir(java_dir):
    """Encuentra el directorio del paquete más profundo"""
    # Buscar todos los directorios bajo src/main/java
    java_dir_path = Path(java_dir)

    # Primero, encontrar la carpeta 'com'
    com_dir = java_dir_path / 'com'
    if not com_dir.exists():
        return None

    # Ahora, recorrer la estructura de directorios para encontrar el paquete más profundo
    for root, dirs, _ in os.walk(com_dir):
        # Si este directorio no tiene subdirectorios, hemos llegado al paquete más profundo
        if not dirs:
            return root

    # Si llegamos aquí, devolvemos la carpeta com como último recurso
    return str(com_dir)


def main():
    base_dir = find_base_dir()
    if not base_dir:
        print("Error: No se pudo encontrar la estructura src/main/java en ningún directorio padre")
        sys.exit(1)

    java_dir = base_dir / 'src' / 'main' / 'java'
    package_dir = find_java_package_dir(str(java_dir))

    if not package_dir:
        print("Error: No se pudo encontrar el paquete bajo src/main/java/com")
        sys.exit(1)

    print(f"Creando carpetas en: {package_dir}")

    # Crear las carpetas requeridas
    for folder in ['application', 'domain', 'infrastructure', 'service']:
        folder_path = os.path.join(package_dir, folder)
        os.makedirs(folder_path, exist_ok=True)
        print(f"- {folder_path}")

    print("Carpetas creadas exitosamente")


if __name__ == "__main__":
    main()