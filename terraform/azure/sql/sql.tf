# Grupo de recursos
resource "azurerm_resource_group" "rg" {
  name     = "${var.az_project_name}-rg"
  location = var.az_location
}

# Servidor SQL
resource "azurerm_mssql_server" "sql_server" {
  name                         = "${var.az_project_name}-sqlsrv"
  resource_group_name          = azurerm_resource_group.rg.name
  location                     = azurerm_resource_group.rg.location
  version                      = "12.0"
  administrator_login          = var.az_sql_admin_user
  administrator_login_password = var.az_sql_admin_password

  minimum_tls_version = "1.2"
}

# Base de datos
resource "azurerm_mssql_database" "sql_db" {
  name           = "${var.az_project_name}-db"
  server_id      = azurerm_mssql_server.sql_server.id
  sku_name       = "S0" # Standard
  collation      = "SQL_Latin1_General_CP1_CI_AS"
  max_size_gb    = 10
}