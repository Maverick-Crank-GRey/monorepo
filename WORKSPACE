

load("//third_party/maven_deps:generate_workspace.bzl", "generated_maven_jars")
generated_maven_jars()


rules_scala_version="5cdae2f034581a05e23c3473613b409de5978833" # update this as needed

http_archive(
             name = "io_bazel_rules_scala",
             url = "https://github.com/bazelbuild/rules_scala/archive/%s.zip"%rules_scala_version,
             type = "zip",
             strip_prefix= "rules_scala-%s" % rules_scala_version
             )

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()
