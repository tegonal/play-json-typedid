scala2 := "2.13.18"
scala3 := "3.3.6"

# List available recipes
default:
    @just --list

# Run tests on both Scala 2 and Scala 3
test: test-scala2 test-scala3

# Run tests on Scala 2
test-scala2:
    sbt "++{{scala2}}" test

# Run tests on Scala 3
test-scala3:
    sbt "++{{scala3}}" test

# Publish locally for both Scala 2 and Scala 3
publish-local: publish-local-scala2 publish-local-scala3

# Publish locally for Scala 2
publish-local-scala2:
    sbt "++{{scala2}}" publishLocal

# Publish locally for Scala 3
publish-local-scala3:
    sbt "++{{scala3}}" publishLocal

# Check for available updates of dependencies
update-dependencies:
    sbt dependencyUpdates
