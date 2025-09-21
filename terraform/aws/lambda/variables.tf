variable "aws_region" {
  type        = string
}

variable "app_name" {
  type        = string
}

variable "image_tag" {
  type        = string
}

variable "ecr_repo_name" {
  type        = string
}

variable "create_function_url" {
  type    = bool
}

variable "lambda_role" {
  type        = string
}
