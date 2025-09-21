
data "aws_caller_identity" "current" {}
data "aws_region" "current" {}

locals {
  image_uri = "${data.aws_caller_identity.current.account_id}.dkr.ecr.${data.aws_region.current.name}.amazonaws.com/${var.ecr_repo_name}:${var.image_tag}"
}

resource "aws_lambda_function" "this" {
  function_name = var.app_name
  package_type  = "Image"
  role          = var.lambda_role
  image_uri     = local.image_uri

  timeout      = 10
  memory_size  = 512
  architectures = ["x86_64"] # o ["arm64"] si compilas para arm64
  environment {
    variables = {
      QUARKUS_PROFILE = "prod"
    }
  }
}

# Function URL (simple y usa payload v2)
resource "aws_lambda_function_url" "url" {
  count                 = var.create_function_url ? 1 : 0
  function_name         = aws_lambda_function.this.function_name
  authorization_type    = "NONE"
  cors {
    allow_origins = ["*"]
    allow_methods = ["*"]
    allow_headers = ["*"]
  }
}

# Permiso público (demo). Endurece esto en prod real.
resource "aws_lambda_permission" "public_url" {
  count               = var.create_function_url ? 1 : 0
  statement_id        = "AllowPublicFunctionUrl"
  action              = "lambda:InvokeFunctionUrl"
  function_name       = aws_lambda_function.this.function_name
  principal           = "*"
  function_url_auth_type = "NONE"
}

output "lambda_name" { value = aws_lambda_function.this.function_name }
output "function_url" {
  value       = var.create_function_url ? aws_lambda_function_url.url[0].function_url : null
  description = "Invoca GET .../v0/operations, etc."
}
