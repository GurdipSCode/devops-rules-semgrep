import jetbrains.buildServer.configs.kotlin.v2019_2.*

version = "2022.10"

project {

    buildType(SemgrepRulesAuthoring)
}

object SemgrepRulesAuthoring : BuildType({
    name = "Semgrep Rules Authoring"

    vcs {
        root(DslContext.settingsRoot)
        cleanCheckout = true
    }

    steps {
        // Install Semgrep (fast path: via pip)
        script {
            name = "Install Semgrep"
            scriptContent = """
                pip install --upgrade pip
                pip install semgrep
                semgrep --version
            """.trimIndent()
        }

        // Validate your custom rules syntax
        script {
            name = "Validate Semgrep Rules"
            scriptContent = """
                echo "Validating custom rules in ./semgrep-rules/"
                semgrep --validate --config ./semgrep-rules
            """.trimIndent()
        }

        // Scan your codebase using your authored rules
        script {
            name = "Run Semgrep Scan"
            scriptContent = """
                echo "Running Semgrep Scan"
                semgrep scan \
                  --config ./semgrep-rules \
                  --error \
                  --json --output semgrep-report.json \
                  .
            """.trimIndent()
        }
    }

    // Publish the Semgrep results
    artifactRules = """
        semgrep-report.json
    """.trimIndent()

    // Fail the build clearly on rule violations
    failureConditions {
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "error:"
            failureMessage = "Semgrep rule violation detected"
        }
    }

    features {
        // Optional: annotate GitHub pull requests
        // Remove this block if not using GitHub integration
        feature {
            type = "githubPullRequests"
            param("authType", "token")
            param("token", "%github.token%")
            param("serverUrl", "https://api.github.com")
        }
    }
})
