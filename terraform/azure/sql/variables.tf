# AZURE
variable "az_project_name" {
  description = "Nombre base del proyecto"
  type        = string
}

variable "az_location" {
  description = "Región de Azure donde desplegar"
  type        = string
}

variable "az_sql_admin_user" {
  description = "Usuario administrador de SQL Server"
  type        = string
}

variable "az_sql_admin_password" {
  description = "Contraseña del administrador de SQL Server"
  type        = string
  sensitive   = true
}