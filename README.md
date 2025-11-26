# devops-rules-semgrep - Semgrep Security Ruleset

[![Semgrep](https://img.shields.io/badge/Semgrep-1.50+-blue?logo=semgrep)](https://semgrep.dev)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Rules](https://img.shields.io/badge/Rules-40+-purple)](.)
[![OWASP](https://img.shields.io/badge/OWASP-Top%2010-orange?logo=owasp)](https://owasp.org/www-project-top-ten/)

![C#](https://img.shields.io/badge/C%23-8%20rules-239120?logo=csharp&logoColor=white)
![Terraform](https://img.shields.io/badge/Terraform-8%20rules-844FBA?logo=terraform&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-13%20rules-F7DF1E?logo=javascript&logoColor=black)
![TypeScript](https://img.shields.io/badge/TypeScript-13%20rules-3178C6?logo=typescript&logoColor=white)
![Python](https://img.shields.io/badge/Python-13%20rules-3776AB?logo=python&logoColor=white)

A comprehensive security ruleset for C#, Terraform, JavaScript/TypeScript, and Python.

## Directory Structure

```
semgrep-rules/
├── csharp/
│   ├── sql-injection.yaml
│   ├── command-injection.yaml
│   ├── hardcoded-secret.yaml
│   ├── insecure-deserialization.yaml
│   ├── path-traversal.yaml
│   ├── weak-crypto.yaml
│   ├── xxe-vulnerability.yaml
│   └── open-redirect.yaml
├── terraform/
│   ├── aws-s3-public-acl.yaml
│   ├── aws-security-group-open-ingress.yaml
│   ├── aws-rds-security.yaml
│   ├── aws-iam-admin-policy.yaml
│   └── cloud-provider-security.yaml
├── javascript/
│   ├── xss.yaml
│   ├── injection.yaml
│   ├── security-misconfig.yaml
│   └── path-redirect.yaml
├── python/
│   ├── injection.yaml
│   ├── deserialization.yaml
│   ├── security-misconfig.yaml
│   └── web-security.yaml
└── README.md
```

## Usage

### Scan entire codebase with all rules
```bash
semgrep --config ./semgrep-rules /path/to/code
```

### Scan with specific language rules
```bash
# Python only
semgrep --config ./semgrep-rules/python /path/to/python/code

# JavaScript/TypeScript only
semgrep --config ./semgrep-rules/javascript /path/to/js/code

# Terraform only
semgrep --config ./semgrep-rules/terraform /path/to/terraform/code

# C# only
semgrep --config ./semgrep-rules/csharp /path/to/csharp/code
```

### Scan with specific rule file
```bash
semgrep --config ./semgrep-rules/python/injection.yaml /path/to/code
```

### CI/CD Integration
```yaml
# GitHub Actions example
- name: Semgrep Security Scan
  uses: returntocorp/semgrep-action@v1
  with:
    config: ./semgrep-rules
```

## Rule Categories

| Category | Languages | Rules |
|----------|-----------|-------|
| Injection (SQL, Command, Code) | All | 12 |
| Hardcoded Secrets | C#, JS, Python | 3 |
| Insecure Deserialization | C#, Python | 3 |
| Path Traversal | C#, JS, Python | 3 |
| Weak Cryptography | C#, Python | 2 |
| XSS | JS, Python | 2 |
| Infrastructure Security | Terraform | 8 |
| Security Misconfiguration | All | 8+ |

## Severity Levels

- **ERROR**: Critical security vulnerabilities requiring immediate attention
- **WARNING**: Security concerns that should be reviewed
- **INFO**: Best practice recommendations

## Metadata

Each rule includes:
- `category`: Rule category (security, best-practice)
- `cwe`: Common Weakness Enumeration reference
- `owasp`: OWASP Top 10 reference (where applicable)

## Customization

To disable specific rules, use Semgrep's exclusion features:
```bash
semgrep --config ./semgrep-rules --exclude-rule python-assert-usage
```

Or create a `.semgrepignore` file in your project root.

## Contributing

To add new rules:
1. Create a new YAML file in the appropriate language directory
2. Follow the existing naming convention: `category-name.yaml`
3. Include proper metadata (category, cwe, owasp)
4. Test your rule before committing
