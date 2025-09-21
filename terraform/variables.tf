# AWS
variable "aws_region" {
  type        = string
  description = "AWS region"
  default     = "us-east-2"
}

variable "app_name" {
  type        = string
  default     = "ops-lambda"
}

variable "image_tag" {
  type        = string
  description = "Image tag to deploy (ej: sha o versión)"
  default = "latest"
}

variable "ecr_repo_name" {
  type        = string
  default     = "360/operation"
}

variable "create_function_url" {
  type    = bool
  default = true
}

variable "use_apigw" {
  type    = bool
  default = false # pon true si quieres API Gateway v2
}


# AZURE
variable "az_project_name" {
  description = "Nombre base del proyecto"
  type        = string
  default = "db-360-operation-1"
}

variable "az_location" {
  description = "Región de Azure donde desplegar"
  type        = string
  default     = "Central US"
}

variable "az_sql_admin_user" {
  description = "Usuario administrador de SQL Server"
  type        = string
  default = "chaboxx"
}

variable "az_sql_admin_password" {
  description = "Contraseña del administrador de SQL Server"
  type        = string
  sensitive   = true
}