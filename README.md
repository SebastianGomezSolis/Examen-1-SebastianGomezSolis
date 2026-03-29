# 💊 Plan Paciente — Examen Parcial #1

**EIF209 – Programación 4 | I Ciclo 2026**  
**Universidad Nacional — Escuela de Informática**  
**Estudiante:** Sebastián Gómez Solís

---

## 📋 Descripción

Aplicación web para la gestión del programa **"Plan Paciente"** de una compañía farmacéutica. El sistema permite a funcionarios de farmacias registrar compras de medicamentos y entregar regalías a pacientes según el plan acumulado de cada medicamento.

**Ejemplo:** En un plan `3+1`, por cada 3 dosis compradas el paciente recibe 1 dosis gratis.

---

## ⚙️ Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.4 |
| Spring MVC + Thymeleaf | — |
| Spring Data JPA | — |
| Spring Security Crypto (BCrypt) | — |
| MySQL | — |
| Bootstrap | 5.3.3 |
| Lombok | — |
| Maven | 3.9.x |

---

## 🗄️ Base de Datos

Ejecutar el script `PlanPaciente.sql` para crear y poblar la base de datos.

```sql
CREATE DATABASE PlanPaciente;
```

### Tablas principales

- **Usuario** — Funcionarios de farmacia (autenticación con BCrypt)
- **Farmacia** — Farmacias asociadas a cada usuario
- **Paciente** — Pacientes registrados en el sistema
- **Medicamento** — Medicamentos con su plan (ej: plan=3 → "3+1")
- **PacienteMedicamento** — Relación paciente-medicamento con dosis acumuladas

### Usuarios de prueba

| ID | Clave | Farmacia |
|---|---|---|
| Farma10 | 111 | FarmaValue Sabanilla |
| Bomba07 | 222 | La Bomba San Pedro |

### Pacientes de prueba

| Cédula | Nombre |
|---|---|
| 111 | Juan Perez |
| 222 | Maria Mata |

---

## 🚀 Configuración y Ejecución

### 1. Clonar el repositorio

```bash
git clone <url-del-repo>
cd Examen-1-SebastianGomezSolis
```

### 2. Configurar `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/PlanPaciente
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=none
```

### 3. Ejecutar la base de datos

```bash
mysql -u root -p < PlanPaciente.sql
```

### 4. Correr la aplicación

```bash
./mvnw spring-boot:run
```

Acceder en: [http://localhost:8080](http://localhost:8080)

---

## 🧭 Funcionalidades

### 1. Login (`/`)
Ingreso de funcionario con ID y clave. Autenticación manual con BCrypt sin Spring Security completo.

### 2. Buscar Paciente (`/presentation/plan/show`)
- Búsqueda por cédula
- Muestra nombre del paciente y sus medicamentos asociados
- Formato del dropdown: `Nombre (plan+1) [regalías disponibles]`
- Si no existe: muestra "Paciente no encontrado"

### 3. Registrar Compra
- Suma la cantidad de dosis al acumulado del paciente para el medicamento seleccionado
- Mantiene el contexto del usuario y paciente activo

### 4. Entregar Regalía
- Valida disponibilidad: `dosisAfavor >= plan * cantidadRegalías`
- Descuenta del acumulado: `acumuladas - (plan * cantidadRegalías)`
- Muestra error si no hay dosis suficientes

### 5. Logout (`/logout`)
Invalida la sesión y redirige al login.

---

## 🏗️ Arquitectura MVC

```
src/main/java/una/sistema/
├── data/                        # Repositorios JPA
│   ├── FarmaciaRepository
│   ├── MedicamentoRepository
│   ├── PacienteRepository
│   ├── PacientemedicamentoRepository
│   └── UsuarioRepository
├── logic/
│   ├── modelo/                  # Entidades JPA
│   │   ├── Farmacia
│   │   ├── Medicamento
│   │   ├── Paciente
│   │   ├── Pacientemedicamento
│   │   └── Usuario
│   ├── service/                 # Lógica de negocio
│   │   ├── FarmaciaService
│   │   ├── MedicamentoService
│   │   ├── PacienteService
│   │   ├── PacientemedicamentoService
│   │   ├── UsuarioService
│   │   └── PasswordHash
│   └── ModeloDatos              # Fachada de servicios
└── presentation/                # Controladores
    ├── GlobalController         # @ControllerAdvice (usuario/farmacia activa)
    ├── InicioController         # Flujo principal del plan paciente
    └── LoginController          # Login / Logout

src/main/resources/templates/
├── head.html                    # Fragmento <head>
├── header.html                  # Fragmento header + nav
├── footer.html                  # Fragmento footer
├── inicio.html                  # Página de login
└── show.html                    # Página principal del plan paciente
```

---
