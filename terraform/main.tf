locals {
  fullname = var.app_name
}

# AWS
module "ecr" {
  source = "./aws/ecr"
  ecr_repo_name = var.ecr_repo_name
}

module "lambda" {
  source = "./aws/lambda"
  image_tag     = var.image_tag
  lambda_role   = module.iam.lambda_exec_role_arn
  app_name = var.app_name
  aws_region = var.aws_region
  create_function_url = var.create_function_url
  ecr_repo_name = var.ecr_repo_name
}

module "iam" {
  source = "./aws/iam"
  app_name = var.app_name
}

# AZURE
module "database" {
  source = "./azure/sql"

  az_project_name       = var.az_project_name
  az_sql_admin_password = "test___test23123"
  az_sql_admin_user     = var.az_sql_admin_user
  az_location           = var.az_location
}